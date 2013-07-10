using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Phone.Controls;
using MusicToRun.Modele;
using Microsoft.Xna.Framework.Media;

namespace MusicToRun
{
    public partial class Category : PhoneApplicationPage
    {
        public const string RythmeLent = "lent";
        public const string RythmeModere = "modere";
        public const string RythmeRapide = "rapide";
        static string DBConnectionString = "Data Source=isostore:/Musique.sdf";

        public Category()
        {
            InitializeComponent();
        }

        private void assignSlow_OnTap(object sender, System.Windows.Input.GestureEventArgs e)
        {
        	// TODO : ajoutez ici l’implémentation du gestionnaire d’événements.
            using (MusiqueToClassify db = new MusiqueToClassify(DBConnectionString))
            {
                Musique musiqueToChange = db.Musiques.FirstOrDefault(m => m.Song.Equals(MainPage.selectedSong.Name));
                musiqueToChange.IsComplete = RythmeLent;
                Song slowSong = MainPage.mediaLibrairy.Songs.First(c => c.Name.Equals(MainPage.selectedSong.Name));
                if (!MainPage.musiquesLentes.Contains(slowSong))
                {
                    MainPage.musiquesLentes.Add(slowSong);
                }
                MainPage.musiquesModerees.Remove(slowSong);
                MainPage.musiquesRapides.Remove(slowSong);
                db.SubmitChanges();
            }

            NavigationService.Navigate(new Uri("/MainPage.xaml", UriKind.Relative));
        }

        private void assignModerate_OnTap(object sender, System.Windows.Input.GestureEventArgs e)
        {
        	// TODO : ajoutez ici l’implémentation du gestionnaire d’événements.
            using (MusiqueToClassify db = new MusiqueToClassify(DBConnectionString))
            {
                Musique musiqueToChange = db.Musiques.First(m => m.Song.Equals(MainPage.selectedSong.Name));
                musiqueToChange.IsComplete = RythmeModere;
                Song moderateSong = MainPage.mediaLibrairy.Songs.First(c => c.Name.Equals(MainPage.selectedSong.Name));
                if (!MainPage.musiquesModerees.Contains(moderateSong))
                {
                    MainPage.musiquesModerees.Add(moderateSong);
                }
                MainPage.musiquesLentes.Remove(moderateSong);
                MainPage.musiquesRapides.Remove(moderateSong);
                db.SubmitChanges();
            }

            NavigationService.Navigate(new Uri("/MainPage.xaml", UriKind.Relative));
        }

        private void assignFast_OnTap(object sender, System.Windows.Input.GestureEventArgs e)
        {
        	// TODO : ajoutez ici l’implémentation du gestionnaire d’événements.
            using (MusiqueToClassify db = new MusiqueToClassify(DBConnectionString))
            {
                Musique musiqueToChange = db.Musiques.First(m => m.Song.Equals(MainPage.selectedSong.Name));
                musiqueToChange.IsComplete = RythmeRapide;
                Song fastSong = MainPage.mediaLibrairy.Songs.First(c => c.Name.Equals(MainPage.selectedSong.Name));
                if (!MainPage.musiquesRapides.Contains(fastSong))
                {
                    MainPage.musiquesRapides.Add(fastSong);
                }
                MainPage.musiquesLentes.Remove(fastSong);
                MainPage.musiquesModerees.Remove(fastSong);
                db.SubmitChanges();
            }

            NavigationService.Navigate(new Uri("/MainPage.xaml", UriKind.Relative));
        }
    }
}
