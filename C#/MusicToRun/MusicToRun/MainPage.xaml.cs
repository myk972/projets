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
using System.Device.Location;
using SharpGIS.WinPhone.Gps;
using Microsoft.Xna.Framework.Media;
using System.Windows.Threading;
using Microsoft.Xna.Framework;
using System.Windows.Media.Imaging;
using System.Windows.Resources;
using MusicToRun.Modele;

namespace MusicToRun
{
    public partial class MainPage : PhoneApplicationPage
    {
        //GeoCoordinateWatcher geolocator;
        IGeoPositionWatcher<GeoCoordinate> geolocator;
        public static MediaLibrary mediaLibrairy;
        Song activeSong;
        double vitesseLente;
        double vitesseRapide;
        public static List<Song> musiquesLentes = new List<Song>();
        public static List<Song> musiquesModerees = new List<Song>();
        public static List<Song> musiquesRapides = new List<Song>();
        private int slowSongIndex;
        private int moderateSongIndex;
        private int fastSongIndex;
        public static Song selectedSong;
        static string DBConnectionString = "Data Source=isostore:/Musique.sdf";


        // Constructeur
        public MainPage()
        {
            InitializeComponent();

             string deviceName = Microsoft.Phone.Info.DeviceExtendedProperties.GetValue("DeviceName").ToString();
             if (deviceName == "XDeviceEmulator") //Use simulator for emulator
             {
                 geolocator = new GeoCoordinateSimulator(34.0568, -117.195);
                 vitesseLente = 50;
                 vitesseRapide = 150;
             }
             else
             {
                 geolocator = new GeoCoordinateWatcher(desiredAccuracy: GeoPositionAccuracy.High);
                 vitesseLente = 5;
                 vitesseRapide = 15;
             }
            //geolocator.MovementThreshold = 5;

            geolocator.PositionChanged += geolocator_PositionChanged;
            geolocator.Start();

            mediaLibrairy = new MediaLibrary();
            activeSong = mediaLibrairy.Songs.ElementAt(0);
            ListMusique.ItemsSource = mediaLibrairy.Songs;

            //musiquesLentes.Add(mediaLibrairy.Songs.ElementAt(0));
            //musiquesModerees.Add(mediaLibrairy.Songs.ElementAt(1));
            //musiquesRapides.Add(mediaLibrairy.Songs.ElementAt(2));

            //List<Musique> tempMusique = (List<Musique>) App.modele.Musiques.Where(s => s._rythme.Equals(Category.RythmeLent));
            using (MusiqueToClassify db = new MusiqueToClassify(DBConnectionString))
            {
                int a = db.Musiques.Count(c => c.Song != null);
                //foreach (Musique m in App.modele.Musiques)
                foreach (Musique m in db.Musiques)
                {
                    switch (m.IsComplete)
                    {
                        case Category.RythmeLent:
                            Song slowSong = mediaLibrairy.Songs.First(c => c.Name.Equals(m._musique));
                            musiquesLentes.Add(slowSong);
                            break;

                        case Category.RythmeModere:
                            Song moderateSong = mediaLibrairy.Songs.First(c => c.Name.Equals(m._musique));
                            musiquesModerees.Add(moderateSong);
                            break;

                        case Category.RythmeRapide:
                            Song fastSong = mediaLibrairy.Songs.First(c => c.Name.Equals(m._musique));
                            musiquesRapides.Add(fastSong);
                            break;
                    }

                }
            }

            slowSongIndex = 0;
            moderateSongIndex = 0;
            fastSongIndex = 0;
        }

        void geolocator_PositionChanged(object sender, GeoPositionChangedEventArgs<GeoCoordinate> e)
        {
            if (e.Position.Location.Speed > 0)
            {
                double actualSpeed = e.Position.Location.Speed * 3.6;
                actualSpeed = Math.Ceiling(actualSpeed);
                TextBlockVitesse.Text = actualSpeed.ToString() + " Km/h";
                if(!(MediaPlayer.State.Equals(MediaState.Playing)) && activeSong != null)
                {
                    if (MediaPlayer.PlayPosition >= activeSong.Duration || MediaPlayer.PlayPosition.TotalSeconds == 0)
                    {
                     playSong(actualSpeed);
                    }
                }
            }
            else
            {
                TextBlockVitesse.Text = "0.00";
                MediaPlayer.Stop();
            }
        }

        private void playSong(double speed)
        {
            FrameworkDispatcher.Update();
            checkAndPlay(speed);
            activeSong = MediaPlayer.Queue.ActiveSong;
            if (activeSong != null)
            {
                TextBlockTitre.Text = activeSong.Name;
                if (activeSong.Album.HasArt)
                {
                    BitmapImage image = new BitmapImage();
                    image.SetSource(activeSong.Album.GetAlbumArt());
                    SongImage.Source = image;
                }
                else
                {
                    BitmapImage image = new BitmapImage();
                    StreamResourceInfo albumArtPlaceholder = Application.GetResourceStream(new Uri("mediaplayer_default_album.png", UriKind.Relative));
                    //image.SetSource(albumArtPlaceholder.Stream);
                }
            }
        }

        private void selection_OnTap(object sender, System.Windows.Input.GestureEventArgs e)
        {
        	// TODO : ajoutez ici l’implémentation du gestionnaire d’événements.
            selectedSong = (Song) ListMusique.SelectedItem;
            NavigationService.Navigate(new Uri("/Category.xaml", UriKind.Relative));
        }

        private void checkAndPlay(double speed)
        {
            if (speed < vitesseLente)
            {
                if (slowSongIndex >= musiquesLentes.Count)
                {
                    slowSongIndex = 0;
                }
                if (musiquesLentes.Count != 0)
                {
                    MediaPlayer.Play(musiquesLentes.ElementAt(slowSongIndex));
                    slowSongIndex++;
                }
            }
            else if (speed > vitesseLente && speed < vitesseRapide)
            {
                if (moderateSongIndex >= musiquesModerees.Count)
                {
                    moderateSongIndex = 0;
                }
                if (musiquesModerees.Count != 0)
                {
                    MediaPlayer.Play(musiquesModerees.ElementAt(moderateSongIndex));
                    moderateSongIndex++;
                }
            }
            else
            {
                if (fastSongIndex >= musiquesRapides.Count)
                {
                    fastSongIndex = 0;
                }
                if (musiquesRapides.Count != 0)
                {
                    MediaPlayer.Play(musiquesRapides.ElementAt(fastSongIndex));
                    fastSongIndex++;
                }
            }
        }
    }
}