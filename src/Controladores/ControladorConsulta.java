/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelos.Consulta;
import modelos.ConsultaDao;
import modelos.DetalleServicio;
import modelos.DetalleServicioDao;
import modelos.Estetica;
import modelos.Mascota;
import modelos.MascotaDao;
import modelos.VacunacionDesparacitacion;
import modelos.Veterinario;
import vistas.VistaBuscarMascota;
import vistas.VistaConsulta;
import vistas.VistaRegistrarClienteyMascota;

public class ControladorConsulta implements ActionListener {

    private Consulta consulta;
    private ConsultaDao consultaDao;
    private VistaConsulta vistaConsulta;
    private DetalleServicio detalleServicio;
    private DetalleServicioDao detalleServicioDao;
    private MascotaDao mascotaDao;
    VistaBuscarMascota vistaBuscarMascota = new VistaBuscarMascota();
    VistaRegistrarClienteyMascota rdm = new VistaRegistrarClienteyMascota();
    ControladorBuscarMascota cbm = new ControladorBuscarMascota(mascotaDao, vistaBuscarMascota);

    public ControladorConsulta(Consulta consulta, ConsultaDao consultaDao, VistaConsulta vistaConsulta, DetalleServicio detalleServicio, DetalleServicioDao detalleServicioDao, MascotaDao mascotaDao) {
        this.consulta = consulta;
        this.consultaDao = consultaDao;
        this.vistaConsulta = vistaConsulta;
        this.detalleServicio = detalleServicio;
        this.detalleServicioDao = detalleServicioDao;
        this.mascotaDao = mascotaDao;
        this.consultaDao.llenarComboBoxVeterinario(vistaConsulta.cmb_veterinario);
        this.vistaConsulta.button_guardar.addActionListener(this);
        this.vistaConsulta.button_cancelar.addActionListener(this);
        this.vistaConsulta.button_buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                cbm.setResultadoSeleccionadoListener(new ControladorBuscarMascota.ResultadoSeleccionadoListener() {
                    public void resultadoSeleccionado(Object[] datosFila) {
                        // Llena los campos de la interfaz principal con los datos de la fila seleccionada
                        vistaConsulta.txt_idMascota.setText(datosFila[0].toString());
                        vistaConsulta.txt_nombre.setText(datosFila[1].toString());
                        vistaConsulta.txt_sexo.setText(datosFila[3].toString());
                        vistaConsulta.txt_tamaño.setText(datosFila[4].toString());
                        vistaConsulta.txt_raza.setText(datosFila[6].toString());
                        vistaConsulta.txt_animal.setText(datosFila[2].toString());
                        vistaConsulta.txt_edad.setText(datosFila[7].toString());
                               
                    }

                  
                   
                });
                vistaBuscarMascota.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaConsulta.button_guardar) {

            if (vistaConsulta.txt_idMascota.getText().equals("") ||
                    vistaConsulta.txt_total.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Estan vacios los campos");
            } else {
                 String sintomas = vistaConsulta.txtArea_sintomas.getText();
                 if(!sintomas.isEmpty()){
                     consulta.setSintomas(sintomas);
                 }
                 String observaciones = vistaConsulta.txtArea_observaciones.getText();
                 if(!observaciones.isEmpty()){
                     consulta.setObservaciones(observaciones);
                 }
                String receta_medica = vistaConsulta.txtArea_RecetaMedica.getText();
                if(!receta_medica.isEmpty()){
                    consulta.setReceta_medica(receta_medica);
                }
                String diagnostico = vistaConsulta.txtArea_RecetaMedica.getText();
                if(!diagnostico.isEmpty()){
                    consulta.setDiagnostico(diagnostico);
                }
                consulta.setTotal(Double.parseDouble(vistaConsulta.txt_total.getText()));

                if (consultaDao.registrarConsulta(consulta)) {
                    JOptionPane.showMessageDialog(null, "Se registro la consulta con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar la consulta");
                }
                int id_consulta = consultaDao.consultaId();
     
                int id_veterinario = detalleServicioDao.obtenerIdVeterinario((String) vistaConsulta.cmb_veterinario.getSelectedItem());

                
                consulta.setId_consulta(id_consulta);
                
                detalleServicio.setConsulta(consulta);
                
                Mascota mascota = new Mascota();
                mascota.setId_mascota(Integer.parseInt(vistaConsulta.txt_idMascota.getText()));
                detalleServicio.setMascota(mascota);
                
                Veterinario veterinario = new Veterinario();
                veterinario.setId_veterinario(id_veterinario);
                detalleServicio.setVeterinario(veterinario);
                
                VacunacionDesparacitacion vacunacionDesparacitacion = new VacunacionDesparacitacion();
                vacunacionDesparacitacion.setId_vacunacionDesparacitacion(null);
                detalleServicio.setVacunacionDesparacitacion(vacunacionDesparacitacion);
                
                Estetica estetica = new Estetica();
                estetica.setId_estetica(null);
                detalleServicio.setEstetica(estetica);

                boolean registroExitoso = detalleServicioDao.registrarDetalleServicio(detalleServicio);
                if (registroExitoso) {
                   limpiarCampos();
                }else{
                       JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                }
            }
        }else if(e.getSource() == vistaConsulta.button_cancelar){
             limpiarCampos();
        }
    }
    public void limpiarCampos(){
        vistaConsulta.txt_idMascota.setText("");
        vistaConsulta.txt_nombre.setText("");
        vistaConsulta.txt_animal.setText("");
        vistaConsulta.txt_edad.setText("");
        vistaConsulta.txtArea_RecetaMedica.setText("");
        vistaConsulta.txtArea_Diagnostico.setText("");
        vistaConsulta.txt_raza.setText("");
        vistaConsulta.txtArea_observaciones.setText("");
        vistaConsulta.txtArea_sintomas.setText("");
        vistaConsulta.txt_sexo.setText("");
        vistaConsulta.txt_tamaño.setText("");
        vistaConsulta.txt_total.setText("");
        vistaConsulta.cmb_veterinario.setSelectedIndex(0);
    }

}
