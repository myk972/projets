   M S B A M L   `   `   `  ����  	ProjetWPF%    ProjetWPF.SuperAdminAddEmployee   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	     � Title$  SuperAdminAddEmployee��5   	   $	��400��6'   $	��300��64   .��5      � .+�5   
   �� $(��"Ajoutez un nouvel utilisateur ici:��6   $
��Left=�6=   $��	51,10,0,0q�6X   $	��Top=�6k   $	��194��6�   �� 5   
   -   5         ��Name$ username_Input��5      $
��Left=�6*   $��23��6E   $��
162,41,0,0q�6Q    �� TextWrapping$
 Wrap=�6e   $	��Top=�6y   $	��120��6�   �� 5   
   -   5        $ firstname_Input��5      $
��Left=�6+   $��23��6F   $��
162,79,0,0q�6R   $
 Wrap=�6f   $	��Top=�6z   $	��120��6�   �� 5	   
   -   5        $ lastName_Input��5	      $
��Left=�6*   $��23��6E   $��162,120,0,0q�6Q   $
 Wrap=�6f   $	��Top=�6z   $	��120��6�   �� 5
   
   -   5        $ password_Input��5
      $
��Left=�6*   $��23��6E   $��162,159,0,0q�6Q   $
 Wrap=�6f   $	��Top=�6z   $	��120��6�   �� 5   
   -   5        $��Valider��5      $
��Left=�6#   $��
51,252,0,0q�6>   $	��Top=�6R   $��75��6j   6u   �� 5   
   -   5        $��Annuler��5      $
��Left=�6#   $��170,252,0,0q�6>   $	��Top=�6S   $��75��6k    J� RenderTransformOrigin$ 0.353,0.546&�6v   6�   �� 5   
   $��
username :��6   $
��Left=�6%   $��	10,41,0,0q�6@   $	��Top=�6S   $��23��6k   �� 5   
   $��prenom :��6   $
��Left=�6#   $��	10,79,0,0q�6>   $	��Top=�6Q   $��23��6i   �� 5   
   $��nom :��6   $
��Left=�6    $��
10,120,0,0q�6;   $	��Top=�6O   $��23��6g   �� 5   
   $��mot de passe :��6   $
��Left=�6)   $��
10,159,0,0q�6D   $	��Top=�6X   $��23��6p   �� 5   
   -   5         ��Name$ error_label��5      $�� ��6%   $
��Left=�60   $��
30,289,0,0q�6K   $	��Top=�6_   $ -0.275,0.684&�6w   $	��252��6�   ���	#FFEC09096�   �� 5   
   $��Niveau :��6   $
��Left=�6#   $��
10,187,0,0q�6>   $	��Top=�6R   �� 5   
   -   5         ��Name$ EmployeeLevel_ComboBox��5      $
��Left=�63   $��162,191,0,0q�6N   $	��Top=�6c   $	��120��6{   5      5      