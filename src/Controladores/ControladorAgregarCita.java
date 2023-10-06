/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Controladores.ControladorBuscarMascota.ResultadoSeleccionadoListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelos.AgendarCita;
import modelos.AgendarCitaDao;
import modelos.Cliente;
import modelos.MascotaDao;
import vistas.VistaBuscarMascota;
import vistas.VistaAgregarCita;
import vistas.VistaRegistrarClienteyMascota;


public class ControladorAgregarCita implements ActionListener {

    private AgendarCita agendarCita;
    private AgendarCitaDao agendarCitaDao;
    private VistaAgregarCita vistaAgregarCita;
    VistaBuscarMascota vistaBuscarMascota = new VistaBuscarMascota();
    VistaRegistrarClienteyMascota rdm = new VistaRegistrarClienteyMascota();
    MascotaDao mascotaDao = new MascotaDao(rdm);
    ControladorBuscarMascota cbm = new ControladorBuscarMascota(mascotaDao, vistaBuscarMascota);
    

    public ControladorAgregarCita(AgendarCita agendarCita, AgendarCitaDao agendarCitaDao, VistaAgregarCita vistaAgregarCita) {
        this.agendarCita = agendarCita;
        this.agendarCitaDao = agendarCitaDao;
        this.vistaAgregarCita = vistaAgregarCita;
        this.vistaAgregarCita.button_buscarMascota.addActionListener(this);
        this.vistaAgregarCita.button_guardar.addActionListener(this);
        this.vistaAgregarCita.button_editar.addActionListener(this);
        this.vistaAgregarCita.button_buscarMascota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                cbm.setResultadoSeleccionadoListener(new ResultadoSeleccionadoListener() {
                    public void resultadoSeleccionado(Object[] datosFila) {
                        // Llena los campos de la interfaz principal con los datos de la fila seleccionada
                        vistaAgregarCita.txt_nombreMascota.setText(datosFila[1].toString());
                        vistaAgregarCita.txt_sexo.setText(datosFila[3].toString());
                        vistaAgregarCita.txt_animal.setText(datosFila[2].toString());
                        vistaAgregarCita.txt_raza.setText(datosFila[6].toString());
                        
                        Cliente cliente = mascotaDao.obtenerClienteDeMascota(datosFila[1].toString());
                        
                        vistaAgregarCita.txt_nroDocumento.setText(cliente.getDni());
                        vistaAgregarCita.txt_nombreCliente.setText(cliente.getNombre());
                        vistaAgregarCita.txt_apellidos.setText(cliente.getApellidos());
                        vistaAgregarCita.txt_celular.setText(cliente.getApellidos());
                        vistaAgregarCita.txt_correo.setText(cliente.getCorreo_electronico());
                        
                    }
                });
                vistaBuscarMascota.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaAgregarCita.button_buscarMascota) {
           // VistaBuscarMascota bm = new VistaBuscarMascota();
           // bm.setVisible(true);
            //vistaAgregarCita.setVisible(false);
        } else if (e.getSource() == vistaAgregarCita.button_guardar) {
            if (vistaAgregarCita.txt_nombreCliente.getText().equals("")
                    || vistaAgregarCita.txt_nombreMascota.getText().equals("")
                    || vistaAgregarCita.txt_horaCita.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            } else {
                agendarCita.setHora(vistaAgregarCita.txt_horaCita.getText());
                agendarCita.setTipo_sevicio((String) vistaAgregarCita.cmb_servicios.getSelectedItem());
                agendarCita.setFecha(vistaAgregarCita.dateChooser_fechaCita.getDate());
                String detalle_cita = vistaAgregarCita.txtArea_detalleCita.getText();
                if(!detalle_cita.isEmpty()){
                    agendarCita.setDetalle_cita(detalle_cita);
                }
                int id_mascota = agendarCitaDao.getIdMascota(vistaAgregarCita.txt_nombreMascota.getText());
                agendarCita.setId_mascota(id_mascota);
                
                if(agendarCitaDao.registrarCita(agendarCita)){
                    limpiarCampos();
                    JOptionPane.showMessageDialog(null, "Cita registrada con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                }
            }
        } else if (e.getSource() == vistaAgregarCita.button_cancelar) {
           limpiarCampos();
        }else if(e.getSource() ==vistaAgregarCita.button_editar){
             if (vistaAgregarCita.txt_nombreCliente.getText().equals("")
                    || vistaAgregarCita.txt_nombreMascota.getText().equals("")
                    || vistaAgregarCita.txt_id_cita.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            } else {
                agendarCita.setHora(vistaAgregarCita.txt_horaCita.getText());
                agendarCita.setTipo_sevicio((String) vistaAgregarCita.cmb_servicios.getSelectedItem());
                agendarCita.setEstado((String) vistaAgregarCita.cmb_estado.getSelectedItem());
                agendarCita.setFecha(vistaAgregarCita.dateChooser_fechaCita.getDate());
                agendarCita.setDetalle_cita(vistaAgregarCita.txtArea_detalleCita.getText());
                agendarCita.setObservaciones(vistaAgregarCita.textArea_observaciones.getText());
                agendarCita.setId_agendarCita(Integer.parseInt(vistaAgregarCita.txt_id_cita.getText()));
                int id_mascota = agendarCitaDao.getIdMascota(vistaAgregarCita.txt_nombreMascota.getText());
                agendarCita.setId_mascota(id_mascota);
                
                if(agendarCitaDao.modificarCita(agendarCita)){
                    limpiarCampos();
                    JOptionPane.showMessageDialog(null, "Cita modificada con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                }
            }
        }
    }
   
    private void limpiarCampos(){
         vistaAgregarCita.txt_nroDocumento.setText("");
            vistaAgregarCita.txt_nombreCliente.setText("");
            vistaAgregarCita.txt_apellidos.setText("");
            vistaAgregarCita.txt_celular.setText("");
            vistaAgregarCita.txt_correo.setText("");
            vistaAgregarCita.txt_nombreMascota.setText("");
            vistaAgregarCita.txt_animal.setText("");
            vistaAgregarCita.txt_raza.setText("");
             vistaAgregarCita.txt_horaCita.setText("");
            vistaAgregarCita.txt_sexo.setText("");
            vistaAgregarCita.txt_horaCita.setText("");
            vistaAgregarCita.txtArea_detalleCita.setText(""); 
            vistaAgregarCita.textArea_observaciones.setText("");
            vistaAgregarCita.cmb_estado.setSelectedIndex(0);
            vistaAgregarCita.cmb_servicios.setSelectedIndex(0);
      
    }
}
