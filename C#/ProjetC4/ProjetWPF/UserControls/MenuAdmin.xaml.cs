﻿using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Data.Spatial;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Web.SessionState;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using GoogleGeocoder;
using ProjetC4;

namespace ProjetWPF.UserControls
{
    /// <summary>
    /// Logique d'interaction pour MenuAdmin.xaml
    /// </summary>
    public partial class MenuAdmin : UserControl
    {

        public ObservableCollection<Employee> Employees;
        public ObservableCollection<Center> Centers;
        public ObservableCollection<Package> Packages; 
        
        public MenuAdmin()
        {
            InitializeComponent();
            using (C4ModelContainer context = new C4ModelContainer())
            {
                Employees = new ObservableCollection<Employee>(context.ListEmployee.ToList());
                Centers = new ObservableCollection<Center>(context.ListCenter.ToList());
                Packages = new ObservableCollection<Package>(context.ListPackage.ToList());
                
                this.ListView.DataContext = this.Employees;
                this.ListViewCenter.DataContext = this.Centers;
                this.ListViewPackage.DataContext = this.Packages;
            }
        }

        private void delete_OnClick(object sender, RoutedEventArgs e)
        {
            GestionEmployeesTraitement.deleteEmployee(ListView, Employees, "admin");
        }

        private void add_onClick(object sender, RoutedEventArgs e)
        {
           SuperAdminAddEmployee form = new SuperAdminAddEmployee();
                bool? result = form.ShowDialog();

            if (result.HasValue && result == true)
            {
                String accessLevel = Convert.ToString(form.EmployeeLevel_ComboBox.SelectedValue);
                GestionEmployeesTraitement.addEmployee(Employees, form.username_Input, form.firstname_Input, form.lastName_Input, form.password_Input, accessLevel);
            }
        }

        private void search_OnClick(object sender, RoutedEventArgs e)
        {
            
        }


        private void edit_OnClick(object sender, RoutedEventArgs e)
        {
            Employee employeDb = (Employee)this.ListView.SelectedItem;
            if (employeDb == null || (!employeDb.AccessLevel.Equals("user"))) return;
            NewUser form = new NewUser();
            bool? result = form.ShowDialog();

            if (result.HasValue && result == true)
            {
                GestionEmployeesTraitement.editEmployee(employeDb, Employees, form.username_Input, form.firstname_Input, form.lastName_Input, form.password_Input);
            }
        }

        private void addCenter_OnClick(object sender, RoutedEventArgs e)
        {
                NewCenter form = new NewCenter();
                bool? result = form.ShowDialog();

                if (result.HasValue && result == true)
                {
                    GestionCentersTraitement.addCenter(Centers, form.centerAdress_Input, form.centerName_Input, form.centerType_Input);
                }
            
        }

        private void editCenter_OnClick(object sender, RoutedEventArgs e)
        {
                Center centerDb = (Center)this.ListViewCenter.SelectedItem;
                if (centerDb == null) return;
                NewCenter form = new NewCenter();
                bool? result = form.ShowDialog();

            if (result.HasValue && result == true)
            {
                GestionCentersTraitement.editCenter(centerDb, Centers, form.centerAdress_Input, form.centerName_Input, form.centerType_Input, form.centerGroup);
            }
        }

        private void deleteCenter_OnClick(object sender, RoutedEventArgs e)
        {
            using (C4ModelContainer context = new C4ModelContainer())
            {
                Center center = (Center) ListViewCenter.SelectedItem;
                if (center == null) return;
                Centers.Remove(center);
                center = context.ListCenter.Find(center.Code);
                context.ListCenter.Remove(center);
                context.SaveChanges();
            }
        }

        private void showStatus_OnClick(object sender, RoutedEventArgs e)
        {
            RegisterPackageTraitement.showStatus(ListViewPackage);
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

        private void deletePackage_onClick(object sender, RoutedEventArgs e)
        {
           RegisterPackageTraitement.deletePackage(ListViewPackage, Packages);
        }

        private void updatePackage_OnClick(object sender, RoutedEventArgs e)
        {
            
                Package packageToUpdate = (Package)ListViewPackage.SelectedItem;
                if (packageToUpdate == null) return;
                UpdatePackage form = new UpdatePackage();
                String con = Convert.ToString(packageToUpdate.Route.ListStage.ElementAt(0).DatePackageAtStage);
                form.HandlingDate_Label.Content = con;
                bool? result = form.ShowDialog();
                

                if (result.HasValue && result == true)
                {
                    RegisterPackageTraitement.editPackage(packageToUpdate, form.NamePackage_Input, form.NameSender_Input,
                                                          form.SenderAddress_Input, form.NameReceiver_Input,
                                                          form.DestinationAddress_Input, form.Comments_Input,
                                                          form.ArriveToSdcDate_Label, form.DeliveringDate_Label,
                                                          form.DeliveredDate_Label);
                }


            
            }

        private void go_OnClick(object sender, MouseButtonEventArgs e)
        { 
            MainWindow login = new MainWindow();
            this.Content = login.Content;
        }

        private void searchEmployee_OnClick(object sender, RoutedEventArgs e)
        {
            GestionEmployeesTraitement.findEmployee(IdEmployee_Input, ListView, Employees);
        }

        private void searchPackage_OnClick(object sender, RoutedEventArgs e)
        {
            RegisterPackageTraitement.findPackage(codePackage_Input, ListViewPackage, Packages);
        }

        private void searchCenter_OnClick(object sender, RoutedEventArgs e)
        {
            GestionCentersTraitement.findCenter(codeCenter_Input, ListViewCenter, Centers);
        }
        }
    }

