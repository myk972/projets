//------------------------------------------------------------------------------
// <auto-generated>
//    Ce code a été généré à partir d'un modèle.
//
//    Des modifications manuelles apportées à ce fichier peuvent conduire à un comportement inattendu de votre application.
//    Les modifications manuelles apportées à ce fichier sont remplacées si le code est régénéré.
// </auto-generated>
//------------------------------------------------------------------------------

namespace ProjetC4
{
    using System;
    using System.Collections.Generic;
    
    public partial class Package
    {
        public int Code { get; set; }
        public string Name { get; set; }
        public string Sender { get; set; }
        public string SenderAddress { get; set; }
        public string Receiver { get; set; }
        public string DestinationAddress { get; set; }
        public string Comment { get; set; }
    
        public virtual Route Route { get; set; }
        public virtual Slot Slot { get; set; }
    }
}