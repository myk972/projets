using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using ProjetC4;

namespace ProjetWPF
{
    /// <summary>
    /// Logique d'interaction pour MenuUser.xaml
    /// </summary>
    public partial class MenuUser : UserControl
    {
        public ObservableCollection<Employee> Employees;
        public ObservableCollection<Package> Packages;

        public MenuUser()
        {
            InitializeComponent();
            using (C4ModelContainer context = new C4ModelContainer())
            {
                Employees = new ObservableCollection<Employee>(context.ListEmployee.ToList());
                Packages = new ObservableCollection<Package>(context.ListPackage.ToList());

                this.ListViewEmployees.DataContext = this.Employees;
                this.ListViewPackage.DataContext = this.Packages;
            }
        }

        private void deletePackage_OnClick(object sender, RoutedEventArgs e)
        {
            RegisterPackageTraitement.deletePackage(ListViewPackage, Packages);
        }

        private void addPackage_OnClick(object sender, RoutedEventArgs e)
        {
            using (C4ModelContainer context = new C4ModelContainer())
            {
                NewPackage form = new NewPackage();
                bool? result = form.ShowDialog();

                if (result.HasValue && result == true)
                {
                    RegisterPackageTraitement.createPackage(form.receiverAddress_Input, form.packageName_Input, form.receiverName_Input, 
                                                            form.senderName_Input, form.senderAddress_Input, form.actuelCenter_Input,
                                                            form.comments_Input, form.errorAddPackage_Label, Packages);

                }
            }
        }

        private void editPackage_OnClick(object sender, RoutedEventArgs e)
        {
            using (C4ModelContainer context = new C4ModelContainer())
            {
                Package packageToUpdate = (Package)ListViewPackage.SelectedItem;
                if (packageToUpdate == null) return;
                packageToUpdate = context.ListPackage.Find(packageToUpdate.Code);
                UpdatePackage form = new UpdatePackage();
                bool? result = form.ShowDialog();
                form.HandlingDate_Label.Content = Convert.ToString(packageToUpdate.Route.ListStage.ElementAt(0).DatePackageAtStage);

                if (result.HasValue && result == true)
                {
                    RegisterPackageTraitement.editPackage(packageToUpdate, form.NamePackage_Input, form.NameSender_Input,
                                                          form.SenderAddress_Input, form.NameReceiver_Input,
                                                          form.DestinationAddress_Input, form.Comments_Input,
                                                          form.ArriveToSdcDate_Label, form.DeliveringDate_Label,
                                                          form.DeliveredDate_Label);
                }


            }
        }

        private void go_OnClick(object sender, MouseButtonEventArgs e)
        {
            MainWindow login = new MainWindow();
            this.Content = login.Content;
        }

        private void searchEmployee_OnClick(object sender, RoutedEventArgs e)
        {
           GestionEmployeesTraitement.findEmployee(username_Input_, ListViewEmployees, Employees);
        }

        private void searchPackage_OnClick(object sender, RoutedEventArgs e)
        {
            RegisterPackageTraitement.findPackage(codePackage_Input, ListViewPackage, Packages);
        }
    }
}
