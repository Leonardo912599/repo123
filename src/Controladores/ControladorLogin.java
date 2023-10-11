/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelos.Veterinario;
import modelos.VeterinarioDao;
import vistas.VistaLogin;
import vistas.VistaMenu;

public class ControladorLogin implements ActionListener {

    private VistaLogin vistaLogin;
    private VeterinarioDao veterinarioDao;
    private Veterinario veterinario;

    public ControladorLogin(VistaLogin vistaLogin, VeterinarioDao veterinarioDao, Veterinario veterinario) {
        this.vistaLogin = vistaLogin;
        this.veterinarioDao = veterinarioDao;
        this.veterinario = veterinario;
        this.vistaLogin.button_ingresar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String usuario = vistaLogin.txt_usuario.getText();
        String contrasena = String.valueOf(vistaLogin.txt_password.getPassword());
        
        if (e.getSource() == vistaLogin.button_ingresar) {

            
            if (usuario.equals("") || contrasena.equals("")) {

                JOptionPane.showMessageDialog(null, "Ambos campos son obligatorios");

            } else {
                veterinario = veterinarioDao.validar(usuario, contrasena);

                if (veterinario.getNombre_user()!= null) {

                    VistaMenu vistaMenu = new VistaMenu();

                    String nombre = veterinario.getNombre();
                    String apellidos = veterinario.getApellidos();

                    vistaMenu.label_nombre.setText(nombre + " " + apellidos);
                    vistaMenu.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos");
                }
            }
        }
    }

}
