using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using System.Data.Linq;
using System.ComponentModel;
using System.Data.Linq.Mapping;
using Microsoft.Xna.Framework.Media;

namespace MusicToRun.Modele
{
    public class MusiqueToClassify : DataContext
    {
        public MusiqueToClassify(string connectionString) : base (connectionString) 
        { }

        public Table<Musique> Musiques;
    }

    [Table]
    public class Musique : INotifyPropertyChanged, INotifyPropertyChanging
    {

        // Define ID: private field, public property, and database column.
        public int _musiqueId;

        [Column(IsPrimaryKey = true, IsDbGenerated = true, DbType = "INT NOT NULL Identity", CanBeNull = false, AutoSync = AutoSync.OnInsert)]
        public int ToDoItemId
        {
            get { return _musiqueId; }
            set
            {
                if (_musiqueId != value)
                {
                    NotifyPropertyChanging("MusiqueId");
                    _musiqueId = value;
                    NotifyPropertyChanged("MusiqueId");
                }
            }
        }

        // Define item name: private field, public property, and database column.
        public string _musique;

        [Column]
        public string Song
        {
            get { return _musique; }
            set
            {
                if (_musique != value)
                {
                    NotifyPropertyChanging("Song");
                    _musique = value;
                    NotifyPropertyChanged("Song");
                }
            }
        }

        // Define completion value: private field, public property, and database column.
        public string _rythme;

        [Column]
        public string IsComplete
        {
            get { return _rythme; }
            set
            {
                if (_rythme != value)
                {
                    NotifyPropertyChanging("IsComplete");
                    _rythme = value;
                    NotifyPropertyChanged("IsComplete");
                }
            }
        }

        // Version column aids update performance.
        [Column(IsVersion = true)]
        private Binary _version;


        // Internal column for the associated ToDoCategory ID value
        [Column]
        internal int _categoryId;

        #region INotifyPropertyChanged Members

        public event PropertyChangedEventHandler PropertyChanged;

        // Used to notify that a property changed
        private void NotifyPropertyChanged(string propertyName)
        {
            if (PropertyChanged != null)
            {
                PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
            }
        }

        #endregion

        #region INotifyPropertyChanging Members

        public event PropertyChangingEventHandler PropertyChanging;

        // Used to notify that a property is about to change
        private void NotifyPropertyChanging(string propertyName)
        {
            if (PropertyChanging != null)
            {
                PropertyChanging(this, new PropertyChangingEventArgs(propertyName));
            }
        }

        #endregion
    }
}
