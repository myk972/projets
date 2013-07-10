using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;

namespace ProjetC4
{
    public class GestionEmployeesTraitement
    {
        public static void deleteEmployee(ListView ListView, ObservableCollection<Employee> Employees, String accessLevel)
        {
            using (C4ModelContainer context = new C4ModelContainer())
            {
                Employee employe = (Employee)ListView.SelectedItem;
                if (employe == null || (employe.AccessLevel.Equals(accessLevel)) || employe.AccessLevel.Equals("superAdmin")) return;
                Employees.Remove(employe);
                employe = context.ListEmployee.Find(employe.ID);
                context.ListEmployee.Remove(employe);
                context.SaveChanges();
            }
        }

        public static void addEmployee(ObservableCollection<Employee> Employees, TextBox username_Input, TextBox firstname_Input, TextBox lastName_Input, TextBox password_Input, String accessLevel)
        {
            using (C4ModelContainer context = new C4ModelContainer())
            {
                Employee newEmployee = new Employee()
                                           {
                                               Username = username_Input.Text,
                                               FirstName = firstname_Input.Text,
                                               LastName = lastName_Input.Text,
                                               Password = password_Input.Text,
                                               AccessLevel = accessLevel
                                           };
                if (context.ListEmployee.FirstOrDefault(c => c.Username.Equals(newEmployee.Username)) == null)
                {
                    context.ListEmployee.Add(newEmployee);
                    Employees.Add(newEmployee);
                }
            

            context.SaveChanges();
        }
    }

        public static void editEmployee(Employee employeDb, ObservableCollection<Employee> Employees, TextBox username_Input, TextBox firstname_Input, TextBox lastName_Input, TextBox password_Input)
        {
            using (C4ModelContainer context = new C4ModelContainer())
            {
                employeDb = context.ListEmployee.Find(employeDb.ID);
                employeDb.Username = username_Input.Text;
                employeDb.FirstName = firstname_Input.Text;
                employeDb.LastName = lastName_Input.Text;
                employeDb.Password = password_Input.Text;


                context.SaveChanges();
                Employee employe = Employees.FirstOrDefault(emp => emp.ID == employeDb.ID);
                Employees.Remove(employe);
                employe = employeDb;
                Employees.Add(employe);
            }
        }

        public static void findEmployee(TextBox IdEmployee_Input, ListView ListViewEmployees,
                                        ObservableCollection<Employee> Employees)
        {
            String Id = IdEmployee_Input.Text;
            Employee employeeToSearch = Employees.FirstOrDefault(c => c.Username.Equals(Id));
            int index = Employees.IndexOf(employeeToSearch);
            ListViewEmployees.SelectedItem = ListViewEmployees.Items.GetItemAt(index);
        }
    }
}
