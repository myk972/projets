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
    /// Logique d'interaction pour UpdatePackage.xaml
    /// </summary>
    public partial class UpdatePackage : Window
    {
        public UpdatePackage()
        {
            InitializeComponent();
        }

        private void updateSdc_OnClick(object sender, RoutedEventArgs e)
        {
            if(ArriveToSdcDate_Label.Content.Equals(""))
            ArriveToSdcDate_Label.Content = Convert.ToString(DateTime.Today);
        }

        private void updateDeliveringDate_OnClick(object sender, RoutedEventArgs e)
        {
            if (DeliveringDate_Label.Content.Equals(""))
                DeliveringDate_Label.Content = Convert.ToString(DateTime.Today);
        }

        private void updateDeliveredDate_OnClick(object sender, RoutedEventArgs e)
        {
            if (DeliveredDate_Label.Content.Equals(""))
                DeliveredDate_Label.Content = Convert.ToString(DateTime.Today);
        }

        private void validate_OnClick(object sender, RoutedEventArgs e)
        {
            this.DialogResult = true;
        }

        private void cancel_OnClick(object sender, RoutedEventArgs e)
        {
            this.DialogResult = false;
        }
    }
}
