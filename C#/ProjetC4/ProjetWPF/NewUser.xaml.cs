using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web.UI.WebControls;
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
    /// Logique d'interaction pour NewUser.xaml
    /// </summary>
    public partial class NewUser : Window
    {
        public NewUser()
        {
            InitializeComponent();
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
