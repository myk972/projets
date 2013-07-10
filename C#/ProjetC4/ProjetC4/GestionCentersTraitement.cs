using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Data.Spatial;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using GoogleGeocoder;

namespace ProjetC4
{
    public class GestionCentersTraitement
    {
        public static void addCenter(ObservableCollection<Center> Centers, TextBox centerAdress_Input, TextBox centerName_Input, TextBox centerType_Input)
        {
            using (C4ModelContainer context = new C4ModelContainer())
            {
                /*
                        * On obtient les coordonnes du center depuis son adresse
                        */
                Coordinate coordinate = Geocode.GetCoordinates(centerAdress_Input.Text);
                string latitude = Convert.ToString(coordinate.Latitude);
                string longitude = Convert.ToString(coordinate.Longitude);
                latitude = latitude.Replace(',', '.');
                longitude = longitude.Replace(',', '.');
                DbGeography point =
                    DbGeography.PointFromText("POINT(" + latitude + " " + longitude + ")", 4326);

                Center newCenter = new Center()
                                       {
                                           Name = centerName_Input.Text,
                                           Location = point,
                                           Address = centerAdress_Input.Text,
                                           Type = centerType_Input.Text,
                                           Group = centerType_Input.Text
                                       };

                Center testCenter = context.ListCenter.FirstOrDefault(c => c.Name.Equals(newCenter.Name));
                if (testCenter == null)
                {
                    context.ListCenter.Add(newCenter);
                    context.SaveChanges();
                    Centers.Add(context.ListCenter.FirstOrDefault(c => c.Name.Equals(newCenter.Name)));
                }
            }
        }

        public static void editCenter(Center centerDb, ObservableCollection<Center> Centers, TextBox centerAdress_Input, TextBox centerName_Input, TextBox centerType_Input, TextBox centerGroup)
        {
            using (C4ModelContainer context = new C4ModelContainer())
            {
                centerDb = context.ListCenter.Find(centerDb.Code);
                Coordinate coordinate = Geocode.GetCoordinates(centerAdress_Input.Text);
                string latitude = Convert.ToString(coordinate.Latitude);
                string longitude = Convert.ToString(coordinate.Longitude);
                latitude = latitude.Replace(',', '.');
                longitude = longitude.Replace(',', '.');
                DbGeography point =
                    DbGeography.PointFromText("POINT(" + latitude + " " + longitude + ")", 4326);

                centerDb.Name = centerName_Input.Text;
                centerDb.Location = point;
                centerDb.Address = centerAdress_Input.Text;
                centerDb.Type = centerType_Input.Text;
                centerDb.Group = centerGroup.Text;


                context.SaveChanges();
                Center center = Centers.FirstOrDefault(c => c.Code == centerDb.Code);
                Centers.Remove(center);
                center = centerDb;
                Centers.Add(center);
            }
        }

        public static void findCenter(TextBox codeCenter_Input, ListView ListViewCenter, ObservableCollection<Center> Centers )
        {
            int code = Convert.ToInt32(codeCenter_Input.Text);
            Center centerToSearch = Centers.FirstOrDefault(c => c.Code == code);
            int index = Centers.IndexOf(centerToSearch);
            ListViewCenter.SelectedItem = ListViewCenter.Items.GetItemAt(index);
        }
    }
}
