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
using ProjetC4;

namespace ProjetWPF
{
    /// <summary>
    /// Logique d'interaction pour NewPackage.xaml
    /// </summary>
    public partial class NewPackage : Window
    {
        public NewPackage()
        {
            InitializeComponent();
        }

        private void validate_OnClick(object sender, RoutedEventArgs e)
        {
            using (C4ModelContainer context = new C4ModelContainer())
            {
                if (!packageName_Input.Text.Equals("") && !senderName_Input.Text.Equals("") &&
                    !senderAddress_Input.Text.Equals("") && !receiverName_Input.Text.Equals("") &&
                    !receiverAddress_Input.Text.Equals("") && !actuelCenter_Input.Text.Equals(""))
                {
                    Center destinationCenter = RegisterPackageTraitement.generateRoute(receiverAddress_Input.Text,
                                                                                       actuelCenter_Input.Text, context);
                    if (destinationCenter != null)
                    {
                        this.DialogResult = true;
                    }
                    else errorAddPackage_Label.Content = "Impossible de trouver un SDC pour cette adresse de livraison";
                }
                else
                {
                    errorAddPackage_Label.Content = "Vous devez remplir tous les champs";
                }
            }
        }

        private void cancel_OnClick(object sender, RoutedEventArgs e)
        {
            this.DialogResult = false;
        }
    }
}
