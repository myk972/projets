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
using System.ComponentModel;
using System.Data.Linq;
using System.Data.Linq.Mapping;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Media;

namespace MusicToRun.Model
{
    public class MusicClassification : DataContext
    {
        public MusicClassification(string connectionString)
            : base(connectionString)
        { }

        public Table<ToClassifyMusic> ClassifiedSongs;
    }

    [Table]
    public class ToClassifyMusic : INotifyPropertyChanged, INotifyPropertyChanging
    {

        // Define ID: private field, public property, and database column.
        private int _toClassifySongId;

        [Column(IsPrimaryKey = true, IsDbGenerated = true, DbType = "INT NOT NULL Identity", CanBeNull = false, AutoSync = AutoSync.OnInsert)]
        public int ToDoItemId
        {
            get { return _toClassifySongId; }
            set
            {
                if (_toClassifySongId != value)
                {
                    NotifyPropertyChanging("ToDoItemId");
                    _toClassifySongId = value;
                    NotifyPropertyChanged("ToDoItemId");
                }
            }
        }

        // Define item name: private field, public property, and database column.
        private Song _song;

        [Column]
        public Song Song
        {
            get { return _song; }
            set
            {
                if (_song != value)
                {
                    NotifyPropertyChanging("ItemName");
                    _song = value;
                    NotifyPropertyChanged("ItemName");
                }
            }
        }

        // Define completion value: private field, public property, and database column.
        private string _rythme;

        [Column]
        public bool IsComplete
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

        // Entity reference, to identify the ToDoCategory "storage" table
        private EntityRef<ToDoCategory> _category;

        // Association, to describe the relationship between this key and that "storage" table
        [Association(Storage = "_category", ThisKey = "_categoryId", OtherKey = "Id", IsForeignKey = true)]
        public ToDoCategory Category
        {
            get { return _category.Entity; }
            set
            {
                NotifyPropertyChanging("Category");
                _category.Entity = value;

                if (value != null)
                {
                    _categoryId = value.Id;
                }

                NotifyPropertyChanging("Category");
            }
        }


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
