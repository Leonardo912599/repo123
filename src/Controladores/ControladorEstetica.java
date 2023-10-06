/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelos.ConsultaDao;
import modelos.DetalleServicio;
import modelos.DetalleServicioDao;
import modelos.Estetica;
import modelos.EsteticaDao;
import modelos.MascotaDao;
import vistas.VistaBuscarMascota;
import vistas.VistaEstetica;
import vistas.VistaRegistrarClienteyMascota;


public class ControladorEstetica implements ActionListener{

    private Estetica estetica;
    private EsteticaDao esteticaDao;
    private VistaEstetica vistaEstetica;
    private DetalleServicioDao detalleServicioDao;
    private DetalleServicio detalleServicio;
    private ConsultaDao consultaDao;
    VistaBuscarMascota vistaBuscarMascota = new VistaBuscarMascota();
    VistaRegistrarClienteyMascota rdm = new VistaRegistrarClienteyMascota();
    MascotaDao mascotaDao = new MascotaDao(rdm);
    ControladorBuscarMascota cbm = new ControladorBuscarMascota(mascotaDao, vistaBuscarMascota);

    public ControladorEstetica(Estetica estetica, EsteticaDao esteticaDao, VistaEstetica vistaEstetica, DetalleServicioDao detalleServicioDao,
            DetalleServicio detalleServicio,ConsultaDao consultaDao) {
        this.estetica = estetica;
        this.detalleServicio = detalleServicio;
        this.esteticaDao = esteticaDao;
        this.consultaDao = consultaDao;
        this.vistaEstetica = vistaEstetica;
        this.detalleServicioDao = detalleServicioDao;
        this.consultaDao.llenarComboBoxVeterinario(vistaEstetica.cmb_veterinario);
        this.vistaEstetica.button_guardar.addActionListener(this);
        this.vistaEstetica.button_cancelar.addActionListener(this);
        this.vistaEstetica.button_buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                cbm.setResultadoSeleccionadoListener(new ControladorBuscarMascota.ResultadoSeleccionadoListener() {
                    public void resultadoSeleccionado(Object[] datosFila) {
                        // Llena los campos de la interfaz principal con los datos de la fila seleccionada
                        vistaEstetica.txt_idMascota.setText(datosFila[0].toString());
                        vistaEstetica.txt_nombre.setText(datosFila[1].toString());
                        vistaEstetica.txt_sexo.setText(datosFila[3].toString());
                        vistaEstetica.txt_tamano.setText(datosFila[4].toString());
                        vistaEstetica.txt_raza.setText(datosFila[6].toString());
                        vistaEstetica.txt_animal.setText(datosFila[2].toString());
                        vistaEstetica.txt_edad.setText(datosFila[7].toString());
                               
                    }
                });
                vistaBuscarMascota.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
         if(e.getSource() == vistaEstetica.button_guardar){
             if(vistaEstetica.txt_idMascota.getText().equals("") 
                     || vistaEstetica.txt_total.getText().equals("")
                     || vistaEstetica.txtArea_Descripcion.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Los campos estan vacios");
             }else{
                 
                boolean isSelectedBano=  vistaEstetica.radioButton_bano.isSelected();
                boolean isSelectedCorte = vistaEstetica.radioButton_corte.isSelected();
                
               estetica.setBano(isSelectedBano ? 1 : 0);
               estetica.setCorte(isSelectedCorte ? 1: 0);
               String observaciones = vistaEstetica.txtArea_observaciones.getText();
               if(!observaciones.isEmpty()){
                   estetica.setObservaciones(observaciones);
               }
               String text_subtotal = vistaEstetica.txt_subtotal.getText();
               if(!text_subtotal.isEmpty()){
                   double subtotal = Double.parseDouble(text_subtotal);
                   estetica.setSubtotal(subtotal);
               }
               String text_descuento = vistaEstetica.txt_descuento.getText();
               if(!text_descuento.isEmpty()){
                   double descuento = Double.parseDouble(text_subtotal);
                   estetica.setDescuento(descuento);
               }
               String descripcion = vistaEstetica.txtArea_Descripcion.getText();
               estetica.setDescripcion(descripcion);
               estetica.setTotal(Double.parseDouble(vistaEstetica.txt_total.getText()));
               
               if(esteticaDao.registrarEstetica(estetica)){
                   JOptionPane.showMessageDialog(null,"Se registro el servicio de estetica con exito");
               }else{
                   JOptionPane.showMessageDialog(null,"Ha ocurrido un error");
               }
             }
                int id_estetica = esteticaDao.esteticaId();
     
                int id_veterinario = detalleServicioDao.obtenerIdVeterinario((String) vistaEstetica.cmb_veterinario.getSelectedItem());

                detalleServicio.setId_consulta(null);
                detalleServicio.setId_mascota(Integer.parseInt(vistaEstetica.txt_idMascota.getText()));
                detalleServicio.setId_veterinario(id_veterinario);
                detalleServicio.setId_vacunacion(null);
                detalleServicio.setId_estetica(id_estetica);
                boolean registroExitoso = detalleServicioDao.registrarDetalleServicio(detalleServicio);
                if (registroExitoso) {
                   limpiarCampos();
                }else{
                       JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                }
         }else if(e.getSource() == vistaEstetica.button_cancelar){
             limpiarCampos();
         }
    }

    private void limpiarCampos() {
         vistaEstetica.txt_idMascota.setText("");
         vistaEstetica.txtArea_Descripcion.setText("");
         vistaEstetica.txtArea_observaciones.setText("");
         vistaEstetica.txt_descuento.setText("");
         vistaEstetica.txt_edad.setText("");
         vistaEstetica.txt_animal.setText("");
         vistaEstetica.txt_raza.setText("");
         vistaEstetica.txt_nombre.setText("");
         vistaEstetica.txt_sexo.setText("");
         vistaEstetica.txt_subtotal.setText("");
         vistaEstetica.txt_descuento.setText("");
         vistaEstetica.txt_tamano.setText("");
         vistaEstetica.txt_descuento.setText("");
         vistaEstetica.txt_total.setText("");
    }
    
    
    
    
}
