using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;

namespace ProjetC4
{
    public class LoginTraitement  
    {
        public static void login(Window window, TextBox utilisateur_Input, PasswordBox password_Input, Label LoginError, UserControl menu, UserControl MenuAdmin, UserControl MenuSuperAdmin)
        {
            using (C4ModelContainer context = new C4ModelContainer())
            {
                String user = (String)utilisateur_Input.Text;
                String pass = (String)password_Input.Password;
                Employee employee = context.ListEmployee.FirstOrDefault(u => u.Username.Equals(user) && u.Password.Equals(pass));
                if (employee != null)
                {
                    switch (employee.AccessLevel)
                    {
                        case "user" :
                            window.Content = menu;
                            break;

                        case "admin" :
                            window.Content = MenuAdmin;
                            break;

                        case "superAdmin" :
                            window.Content = MenuSuperAdmin;
                            break;
                    }
                }
                else
                {
                    LoginError.Content = "Les donnees saisies sont invalides";
                }
            }
        }
    }
}
