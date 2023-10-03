/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelos.Cliente;
import modelos.ClienteDao;
import modelos.Mascota;
import modelos.MascotaDao;
import vistas.RegistrarDueñoyMascota;

public class ControladorRegistrarDueñoyMascota implements ActionListener {

    private Cliente cliente;
    private ClienteDao clienteDao;
    private Mascota mascota;
    private MascotaDao mascotaDao;
    private RegistrarDueñoyMascota rdm;
   

    public ControladorRegistrarDueñoyMascota(Cliente cliente, Mascota mascota, MascotaDao mascotaDao, ClienteDao clienteDao, RegistrarDueñoyMascota registrarDueñoyMascota) {
        this.cliente = cliente;
        this.mascota = mascota;
        this.rdm = registrarDueñoyMascota;
        this.clienteDao = clienteDao;
        this.mascotaDao = mascotaDao;
        this.rdm.button_buscarDueno.addActionListener(this);
        this.rdm.button_editar.addActionListener(this);
        this.rdm.button_guardarDueno.addActionListener(this);
        this.rdm.button_guardarMascota.addActionListener(this);
        this.rdm.button_cancelarDueno.addActionListener(this);
        this.rdm.button_agregar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == rdm.button_guardarDueno) {

            if (rdm.txt_nroDocumento.getText().equals("")
                    || rdm.txt_nombre.getText().equals("")
                    || rdm.txt_apellidos.getText().equals("")
                    || rdm.txt_celular.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Es obligatorio rellenar los campos");
            } else {
                cliente.setDni(rdm.txt_nroDocumento.getText().trim());
                cliente.setNombre(rdm.txt_nombre.getText().trim());
                cliente.setApellidos(rdm.txt_apellidos.getText().trim());
                cliente.setCelular(rdm.txt_celular.getText().trim());
                cliente.setCorreo_electronico(rdm.txt_correoElectronico.getText().trim());

                if (clienteDao.registrarCliente(cliente)) {
                    limpiarCampos();
                    JOptionPane.showMessageDialog(null, "Cliente registrado con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar al cliente");
                }
            }

        } else if (e.getSource() == rdm.button_editar) {
            if (rdm.txt_nroDocumento.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "NO se ha seleccionado ningun cliente");
            } else {

                cliente.setDni(rdm.txt_nroDocumento.getText().trim());
                cliente.setNombre(rdm.txt_nombre.getText().trim());
                cliente.setApellidos(rdm.txt_apellidos.getText().trim());
                cliente.setCelular(rdm.txt_celular.getText().trim());
                cliente.setCorreo_electronico(rdm.txt_correoElectronico.getText().trim());

                if (clienteDao.modificarCliente(cliente)) {

                    limpiarCampos();
                    JOptionPane.showMessageDialog(null, "Datos del cliente modificados con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar los datos del cliente");
                }

            }
        } else if (e.getSource() == rdm.button_cancelarDueno) {
            limpiarCampos();
        } else if (e.getSource() == rdm.button_guardarMascota) {
            if (rdm.txt_nombreMascota.getText().equals("")
                    || rdm.txt_raza.getText().equals("")
                    || rdm.txt_edad.getText().equals("")
                    || rdm.txt_color.getText().equals("")
                    || rdm.txt_tamano.getText().equals("")
                    || rdm.txt_dniPropietario.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Es obligatorio rellenar los campos");

            } else {

                mascota.setNombre(rdm.txt_nombre.getText().trim());
                mascota.setEdad(rdm.txt_edad.getText().trim());
                mascota.setRaza(rdm.txt_raza.getText().trim());
                mascota.setColor(rdm.txt_color.getText().trim());
                mascota.setTamano(rdm.txt_tamano.getText().trim());
                mascota.setSexo((String) rdm.cmb_sexo.getSelectedItem());
                mascota.setEspecie((String) rdm.cmb_especie.getSelectedItem());
                mascota.setFecha_nacimiento(rdm.dateChooser_FechaNacimiento.getDate());
                mascota.setDni_cliente(rdm.txt_dniPropietario.getText().trim());

                if (mascotaDao.registrarMascota(mascota)) {
                    limpiarCamposMascota();
                    JOptionPane.showMessageDialog(null, "Datos de la mascota registrados con exito");
                }

            }
        }else if(e.getSource() == rdm.button_cancelarMascota){
            limpiarCamposMascota();
        }else if(e.getSource() == rdm.button_agregar){
             List<Cliente> list = clienteDao.listarClienteById(rdm.txt_dniPropietario.getText());
             
             rdm.txt_nroDocumento.setText(list.get(0).getDni());
             rdm.txt_nombre.setText(list.get(0).getNombre());
             rdm.txt_apellidos.setText(list.get(0).getApellidos());
             rdm.txt_celular.setText(list.get(0).getCelular());
             rdm.txt_correoElectronico.setText(list.get(0).getCorreo_electronico());
        }
    }

    private void limpiarCampos() {
        rdm.txt_nroDocumento.setText("");
        rdm.txt_nombre.setText("");
        rdm.txt_apellidos.setText("");
        rdm.txt_celular.setText("");
        rdm.txt_correoElectronico.setText("");
    }

    private void limpiarCamposMascota() {

        rdm.txt_nombreMascota.setText("");
        rdm.txt_edad.setText("");
        rdm.txt_raza.setText("");
        rdm.txt_color.setText("");
        rdm.txt_tamano.setText("");
        rdm.cmb_especie.setSelectedIndex(0);
        rdm.txt_dniPropietario.setText("");
    }

}
