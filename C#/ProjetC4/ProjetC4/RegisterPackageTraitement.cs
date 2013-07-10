using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Data.Spatial;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using GoogleGeocoder;

namespace ProjetC4
{
    public class RegisterPackageTraitement 
    {
        public static Center generateRoute(String receiverAddress_Input, String actuelCenter_Input, C4ModelContainer context)
        {
                /*
                 * creation du point à partir de l'adresse du destinataire
                 */
                Coordinate coordinate = Geocode.GetCoordinates(receiverAddress_Input);
                string latitude = Convert.ToString(coordinate.Latitude);
                string longitude = Convert.ToString(coordinate.Longitude);
                latitude = latitude.Replace(',', '.');
                longitude = longitude.Replace(',', '.');
                DbGeography point =
                    DbGeography.PointFromText("POINT(" + latitude + " " + longitude + ")", 4326);

                /*
                 * On récupère la liste des gdz depuis la base de données
                 * puis on les compare avec le point obtenu. 
                 * On garde celle qui le contient
                 */
                Gdz gdz = context.ListGdz.FirstOrDefault(g => g.Coordinates.Intersects(point));

                if (gdz == null)
                {
                    return null;
                }

                /*
                 * Affichage des étapes
                 */
                Center destinationCenter =
                    context.ListCenter.FirstOrDefault(
                        c => gdz.Coordinates.Intersects(c.Location) && c.Type.Equals("SDC"));
   
                return destinationCenter;
            
        }

        public static void createPackage(TextBox receiverAddress_Input, TextBox packageName_Input, TextBox receiverName_Input,
                                         TextBox senderName_Input, TextBox senderAddress_Input, TextBox actuelCenter_Input,
                                         TextBox comments_Input, Label errorAddPackage_Label, ObservableCollection<Package> Packages)
        {
            using (C4ModelContainer context = new C4ModelContainer())
            {
                
                    Center destinationCenter = RegisterPackageTraitement.generateRoute(receiverAddress_Input.Text,
                                                                                       actuelCenter_Input.Text, context);


                    /*
                    * On génere la route
                    */
                    Package colis = new Package();
                    colis.Name = packageName_Input.Text;
                    colis.Receiver = receiverName_Input.Text;
                    colis.DestinationAddress = receiverAddress_Input.Text;
                    colis.Sender = senderName_Input.Text;
                    colis.SenderAddress = senderAddress_Input.Text;
                    colis.Comment = comments_Input.Text;

                    Center centreDeDepot =
                        context.ListCenter.First(center => center.Name.Equals(actuelCenter_Input.Text));
                    Slot slotARemplir = context.ListSlot.First(
                        slot =>
                        slot.Center.Code == centreDeDepot.Code &&
                        slot.FreeCapacity != 0);
                    if (slotARemplir == null)
                    {
                        errorAddPackage_Label.Content = "Place insuffisante dans le centre" + centreDeDepot.Name + " pour stocker le colis";
                        return;
                    }
                    slotARemplir.FreeCapacity--;
                    colis.Slot = slotARemplir;
                    Route newRoute = new Route();
                    newRoute.Package = colis;

                    colis.Slot = slotARemplir;

                    /*
                     * Generation et association des stages 
                     * à la derniere route crée
                     */

                    Stage firstStage = new Stage();
                    firstStage.Route = newRoute;
                    firstStage.DatePackageAtStage = DateTime.Today;
                    firstStage.Type = centreDeDepot.Name + ":DOC";
                    newRoute.ListStage.Add(firstStage);
                    context.ListStage.Add(firstStage);

                    Stage secondStage = new Stage();
                    secondStage.Route = newRoute;
                    secondStage.DatePackageAtStage = null;
                    if (centreDeDepot.Type.Equals("SDC")) secondStage.DatePackageAtStage = DateTime.Today;
                    secondStage.Type = destinationCenter.Name + "SDC:";
                    newRoute.ListStage.Add(secondStage);
                    context.ListStage.Add(secondStage);

                    Stage thirdStage = new Stage();
                    thirdStage.Route = newRoute;
                    thirdStage.DatePackageAtStage = null;
                    thirdStage.Type = "Delivery";
                    newRoute.ListStage.Add(thirdStage);
                    context.ListStage.Add(thirdStage);

                    Stage fourthStage = new Stage();
                    fourthStage.Route = newRoute;
                    fourthStage.DatePackageAtStage = null;
                    fourthStage.Type = "Delivered";
                    newRoute.ListStage.Add(fourthStage);
                    context.ListStage.Add(fourthStage);

                    colis.Route = newRoute;
                    context.ListPackage.Add(colis);
                    context.ListRoute.Add(newRoute);
                    context.SaveChanges();
                    Packages.Add(context.ListPackage.FirstOrDefault(c => c.Name.Equals(colis.Name)));
                
            }
        }

        public static void deletePackage(ListView ListViewPackage, ObservableCollection<Package> Packages)
        {
            using (C4ModelContainer context = new C4ModelContainer())
            {
                Package packageToDelete = (Package)ListViewPackage.SelectedItem;
                if (packageToDelete == null) return;
                Packages.Remove(packageToDelete);
                packageToDelete = context.ListPackage.Find(packageToDelete.Code);
                Slot slotToUpdate = packageToDelete.Slot;
                Package suppressFromSlot = slotToUpdate.ListPackage.First(c => c.Code == packageToDelete.Code);
                slotToUpdate.ListPackage.Remove(suppressFromSlot);
                slotToUpdate.FreeCapacity++;
                Route routeToDelete = packageToDelete.Route;
                List<Stage> stagesToDelete = routeToDelete.ListStage.ToList();
                foreach (var stage in stagesToDelete)
                {
                    context.ListStage.Remove(stage);
                }
                context.ListRoute.Remove(routeToDelete);
                context.ListPackage.Remove(packageToDelete);
                context.SaveChanges();
            }
        }

        public static void editPackage(Package packageToUpdate, TextBox NamePackage_Input, TextBox NameSender_Input,
                                       TextBox SenderAddress_Input, TextBox NameReceiver_Input, TextBox DestinationAddress_Input,
                                        TextBox Comments_Input, Label ArriveToSdcDate_Label, Label DeliveringDate_Label,
                                        Label DeliveredDate_Label)
        {
            using (C4ModelContainer context = new C4ModelContainer())
            {
                packageToUpdate = context.ListPackage.Find(packageToUpdate.Code);
                    if (!NamePackage_Input.Text.Equals("")) packageToUpdate.Name = NamePackage_Input.Text;
                    if (!NameSender_Input.Text.Equals("")) packageToUpdate.Sender = NameSender_Input.Text;
                    if (!SenderAddress_Input.Text.Equals(""))
                        packageToUpdate.SenderAddress = SenderAddress_Input.Text;
                    if (!NameReceiver_Input.Text.Equals(""))
                        packageToUpdate.Receiver = NameReceiver_Input.Text;
                    Center destinationCenter = new Center();
                    if (!DestinationAddress_Input.Text.Equals(""))
                    {
                        destinationCenter =
                            RegisterPackageTraitement.generateRoute(DestinationAddress_Input.Text,
                                                                    packageToUpdate.Slot.Center.Name, context);

                        /*
                             * On essaie de trouver le centre qui heberge le colis. On va ensuite retirer la colis du slot
                             * concerné et ajouter 1 à son espace libre.
                             */
                        int slotCode = packageToUpdate.Slot.Code;
                        Slot slotToUpdate = context.ListSlot.Find(slotCode);
                        Package packageToDeleteFromSlot =
                            slotToUpdate.ListPackage.First(c => c.Code == packageToUpdate.Code);
                        slotToUpdate.ListPackage.Remove(packageToDeleteFromSlot);
                        slotToUpdate.FreeCapacity++;

                        /*
                             * Maintenant il ne nous reste plus qu'a trouver le slot qui va accueillir le colis
                             * pour decrementer son espace disponible. Si le colis est en mode Delivery il se trouve
                             * dans un vehicule et non dans un centre. On doit donc decrementer le slot du vehicule
                             * correspondant.
                             */
                        if (packageToUpdate.Route.ListStage.ElementAt(2).DatePackageAtStage == null && packageToUpdate.Route.ListStage.ElementAt(3).DatePackageAtStage == null)
                        {
                            Slot destinationSlot = destinationCenter.ListSlot.FirstOrDefault(c => c.FreeCapacity > 0);
                            destinationSlot.ListPackage.Add(packageToUpdate);
                            destinationSlot.FreeCapacity--;
                        }
                    }
                    else
                    {
                        Center actualCenter = packageToUpdate.Slot.Center;
                        destinationCenter = RegisterPackageTraitement.generateRoute(packageToUpdate.DestinationAddress,
                                                                                    actualCenter.Name, context);
                    }
                    if (packageToUpdate.Route.ListStage.ElementAt(2).DatePackageAtStage != null &&
                            packageToUpdate.Route.ListStage.ElementAt(3).DatePackageAtStage == null)
                    {
                        Vehicle destinationVehicle =
                            context.ListVehicle.FirstOrDefault(
                                c => c.Center.Code == destinationCenter.Code && c.Slot.FreeCapacity > 0);
                        destinationVehicle.Slot.FreeCapacity--;
                    }

                    if (!Comments_Input.Text.Equals("")) packageToUpdate.Comment = Comments_Input.Text;
                    if (!ArriveToSdcDate_Label.Content.Equals(""))
                        packageToUpdate.Route.ListStage.ElementAt(1).DatePackageAtStage =
                            Convert.ToDateTime(ArriveToSdcDate_Label.Content);
                    if (!DeliveringDate_Label.Content.Equals(""))
                        packageToUpdate.Route.ListStage.ElementAt(2).DatePackageAtStage =
                            Convert.ToDateTime(DeliveringDate_Label.Content);
                    if (!DeliveredDate_Label.Content.Equals(""))
                        packageToUpdate.Route.ListStage.ElementAt(3).DatePackageAtStage =
                            Convert.ToDateTime(DeliveredDate_Label.Content);

                    packageToUpdate.Route.ListStage.ElementAt(1).Type =
                            destinationCenter.Name + ":SDC";

                    packageToUpdate.DestinationAddress = DestinationAddress_Input.Text;
                    context.SaveChanges();
                


            }
        }

        public static void showStatus(ListView ListViewPackage)
        {
            using (C4ModelContainer context = new C4ModelContainer())
            {
                Package package = (Package)ListViewPackage.SelectedItem;
                if (package == null) return;
                package = context.ListPackage.Find(package.Code);
                Stage actualStage = package.Route.ListStage.LastOrDefault(s => s.DatePackageAtStage != null);
                MessageBox.Show("Position actuelle: " + actualStage.Type);
            }
        }

        public static void findPackage(TextBox codePackage_Input, ListView ListViewPackage, ObservableCollection<Package> Packages)
        {
            int code = Convert.ToInt32(codePackage_Input.Text);
            Package packageToSearch = Packages.FirstOrDefault(c => c.Code == code);
            int index = Packages.IndexOf(packageToSearch);
            ListViewPackage.SelectedItem = ListViewPackage.Items.GetItemAt(index);
        }
    }
}
