using System;
using System.Collections.Generic;
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
using System.Windows.Shapes;

namespace ProjetWPF
{
    /// <summary>
    /// Logique d'interaction pour SuperAdminAddEmployee.xaml
    /// </summary>
    public partial class SuperAdminAddEmployee : Window
    {
        public SuperAdminAddEmployee()
        {
            InitializeComponent();
            this.EmployeeLevel_ComboBox.Items.Add("user");
            this.EmployeeLevel_ComboBox.Items.Add("admin");
            this.EmployeeLevel_ComboBox.SelectedItem = this.EmployeeLevel_ComboBox.Items.GetItemAt(0);
        }

        private void valider_OnClick(object sender, RoutedEventArgs e)
        {
            if (username_Input.Text.Equals("") || firstname_Input.Text.Equals("") || lastName_Input.Text.Equals("") ||
                password_Input.Text.Equals(""))
            {
                this.error_label.Content = "Vous devez remplir tous les champs!";
            }
            else
            {
                this.DialogResult = true;
            }
        }

        private void concel_onClick(object sender, RoutedEventArgs e)
        {
            this.DialogResult = false;
        }
    }
}
