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
    /// Logique d'interaction pour NewCenter.xaml
    /// </summary>
    public partial class NewCenter : Window
    {
        public NewCenter()
        {
            InitializeComponent();
        }

        private void validate_OnClick(object sender, RoutedEventArgs e)
        {
            if (this.centerName_Input.Text.Equals("") || this.centerAdress_Input.Text.Equals("")
                  || this.centerAdress_Input.Text.Equals("") || this.centerGroup.Text.Equals(""))
            {
                this.errorCenterCreation.Content = "Vous devez remplir tous les champs";
            }
            else
            {
                this.DialogResult = true;
            }
        }

        private void cancel_OnClick(object sender, RoutedEventArgs e)
        {
            this.DialogResult = false;
        }
    }
}
