﻿#pragma checksum "..\..\..\UserControls\RegisterPackage.xaml" "{406ea660-64cf-4c82-b6f0-42d48172a799}" "1CF8D9E16D1BD74B20861F247926B811"
//------------------------------------------------------------------------------
// <auto-generated>
//     Ce code a été généré par un outil.
//     Version du runtime :4.0.30319.17929
//
//     Les modifications apportées à ce fichier peuvent provoquer un comportement incorrect et seront perdues si
//     le code est régénéré.
// </auto-generated>
//------------------------------------------------------------------------------

using System;
using System.Diagnostics;
using System.Windows;
using System.Windows.Automation;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Markup;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Effects;
using System.Windows.Media.Imaging;
using System.Windows.Media.Media3D;
using System.Windows.Media.TextFormatting;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Shell;


namespace ProjetWPF.UserControls {
    
    
    /// <summary>
    /// RegisterPackage
    /// </summary>
    public partial class RegisterPackage : System.Windows.Controls.UserControl, System.Windows.Markup.IComponentConnector {
        
        
        #line 15 "..\..\..\UserControls\RegisterPackage.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox packageName_Input;
        
        #line default
        #line hidden
        
        
        #line 16 "..\..\..\UserControls\RegisterPackage.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox senderName_Input;
        
        #line default
        #line hidden
        
        
        #line 17 "..\..\..\UserControls\RegisterPackage.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox senderAddress_Input;
        
        #line default
        #line hidden
        
        
        #line 18 "..\..\..\UserControls\RegisterPackage.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox receiverName_Input;
        
        #line default
        #line hidden
        
        
        #line 19 "..\..\..\UserControls\RegisterPackage.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox receiverAddress_Input;
        
        #line default
        #line hidden
        
        
        #line 20 "..\..\..\UserControls\RegisterPackage.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox comments_Input;
        
        #line default
        #line hidden
        
        
        #line 24 "..\..\..\UserControls\RegisterPackage.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox actuelCenter_Input;
        
        #line default
        #line hidden
        
        
        #line 25 "..\..\..\UserControls\RegisterPackage.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Label error_Label;
        
        #line default
        #line hidden
        
        private bool _contentLoaded;
        
        /// <summary>
        /// InitializeComponent
        /// </summary>
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        public void InitializeComponent() {
            if (_contentLoaded) {
                return;
            }
            _contentLoaded = true;
            System.Uri resourceLocater = new System.Uri("/ProjetWPF;component/usercontrols/registerpackage.xaml", System.UriKind.Relative);
            
            #line 1 "..\..\..\UserControls\RegisterPackage.xaml"
            System.Windows.Application.LoadComponent(this, resourceLocater);
            
            #line default
            #line hidden
        }
        
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Never)]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Design", "CA1033:InterfaceMethodsShouldBeCallableByChildTypes")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Maintainability", "CA1502:AvoidExcessiveComplexity")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1800:DoNotCastUnnecessarily")]
        void System.Windows.Markup.IComponentConnector.Connect(int connectionId, object target) {
            switch (connectionId)
            {
            case 1:
            this.packageName_Input = ((System.Windows.Controls.TextBox)(target));
            return;
            case 2:
            this.senderName_Input = ((System.Windows.Controls.TextBox)(target));
            return;
            case 3:
            this.senderAddress_Input = ((System.Windows.Controls.TextBox)(target));
            return;
            case 4:
            this.receiverName_Input = ((System.Windows.Controls.TextBox)(target));
            return;
            case 5:
            this.receiverAddress_Input = ((System.Windows.Controls.TextBox)(target));
            return;
            case 6:
            this.comments_Input = ((System.Windows.Controls.TextBox)(target));
            return;
            case 7:
            
            #line 21 "..\..\..\UserControls\RegisterPackage.xaml"
            ((System.Windows.Controls.Button)(target)).Click += new System.Windows.RoutedEventHandler(this.createPackage_onClick);
            
            #line default
            #line hidden
            return;
            case 8:
            
            #line 22 "..\..\..\UserControls\RegisterPackage.xaml"
            ((System.Windows.Controls.Button)(target)).Click += new System.Windows.RoutedEventHandler(this.cancelPackage_onClick);
            
            #line default
            #line hidden
            return;
            case 9:
            this.actuelCenter_Input = ((System.Windows.Controls.TextBox)(target));
            return;
            case 10:
            this.error_Label = ((System.Windows.Controls.Label)(target));
            return;
            }
            this._contentLoaded = true;
        }
    }
}
