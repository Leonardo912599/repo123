/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.DetalleServicio;
import modelos.DetalleServicioDao;
import modelos.MascotaDao;
import vistas.VistaBuscarMascota;
import vistas.VistaHistoriaClinica;
import vistas.VistaHistorialConsulta;
import vistas.VistaRegistrarClienteyMascota;
import vistas.VistaRegistrarHistoriaClinica;

public class ControladorHistoriaClinica implements ActionListener {

    private VistaHistoriaClinica vistaHistoriaClinica;
    private DetalleServicioDao detalleServicioDao;
    VistaBuscarMascota vistaBuscarMascota = new VistaBuscarMascota();
    VistaRegistrarClienteyMascota rdm = new VistaRegistrarClienteyMascota();
    MascotaDao mascotaDao = new MascotaDao(rdm);
    ControladorBuscarMascota cbm = new ControladorBuscarMascota(mascotaDao, vistaBuscarMascota);
    Cliente cliente = new Cliente();
    
    DefaultTableModel model = new DefaultTableModel();
   
    public ControladorHistoriaClinica(VistaHistoriaClinica vistaHistoriaClinica,DetalleServicioDao detalleServicioDao) {
        this.vistaHistoriaClinica = vistaHistoriaClinica;
        this.detalleServicioDao = detalleServicioDao;
        this.vistaHistoriaClinica.button_verRegistros.addActionListener(this);
        this.vistaHistoriaClinica.button_nuevaHistoria.addActionListener(this);
        this.vistaHistoriaClinica.button_modificarHistoria.addActionListener(this);
        this.vistaHistoriaClinica.button_buscarMascota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cbm.setResultadoSeleccionadoListener(new ControladorBuscarMascota.ResultadoSeleccionadoListener() {
                    public void resultadoSeleccionado(Object[] datosFila) {
                        // Llena los campos de la interfaz principal con los datos de la fila seleccionada
                        vistaHistoriaClinica.txt_idMascota.setText(datosFila[0].toString());
                        vistaHistoriaClinica.txt_nombre.setText(datosFila[1].toString());
                        vistaHistoriaClinica.txt_sexo.setText(datosFila[3].toString());
                        vistaHistoriaClinica.txt_tamano.setText(datosFila[4].toString());
                        vistaHistoriaClinica.txt_raza.setText(datosFila[6].toString());
                        vistaHistoriaClinica.txt_animal.setText(datosFila[2].toString());
                        vistaHistoriaClinica.txt_edad.setText(datosFila[7].toString());
                        vistaHistoriaClinica.txt_fechaNacimiento.setText(datosFila[8].toString());

                        cliente = mascotaDao.obtenerClienteDeMascota(datosFila[1].toString());

                        vistaHistoriaClinica.txt_nroDocumento.setText(cliente.getDni());
                        vistaHistoriaClinica.txt_nombreCliente.setText(cliente.getNombre());
                        vistaHistoriaClinica.txt_apellidos.setText(cliente.getApellidos());
                        vistaHistoriaClinica.txt_celular.setText(cliente.getCelular());
                        vistaHistoriaClinica.txt_correo.setText(cliente.getCorreo_electronico());

                    }

                });
                vistaBuscarMascota.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaHistoriaClinica.button_nuevaHistoria) {

            VistaRegistrarHistoriaClinica vistaRegistrarHistoriaClinica = new VistaRegistrarHistoriaClinica();
            String id = vistaHistoriaClinica.txt_idMascota.getText();
            if (!id.isEmpty()) {
                int id_mascota = Integer.parseInt(id);
                vistaRegistrarHistoriaClinica.txt_idMascota.setText(String.valueOf(id_mascota));
                 vistaRegistrarHistoriaClinica.setVisible(true);
            } else {
                
                 JOptionPane.showMessageDialog(null, "No ha seleccionado una mascota");
            }

        } else if (e.getSource() == vistaHistoriaClinica.button_modificarHistoria) {
            
            VistaRegistrarHistoriaClinica vistaRegistrarHistoriaClinica = new VistaRegistrarHistoriaClinica();
            String id = vistaHistoriaClinica.txt_idMascota.getText();
            if (!id.isEmpty()) {
                int id_mascota = Integer.parseInt(id);
                vistaRegistrarHistoriaClinica.txt_idMascota.setText(String.valueOf(id_mascota));
                 vistaRegistrarHistoriaClinica.setVisible(true);
            } else {
     
                 JOptionPane.showMessageDialog(null, "No ha seleccionado una mascota");
            }

        }else if(e.getSource() == vistaHistoriaClinica.button_verRegistros){
            
            String servicio = vistaHistoriaClinica.cmb_servicios.getSelectedItem().toString();
            int id_mascota = Integer.parseInt(vistaHistoriaClinica.txt_idMascota.getText());
            
            switch (servicio) {
                case "Consulta" -> {
                    
                    List<DetalleServicio> list = detalleServicioDao.listarConsultas(id_mascota);
                    
                    VistaHistorialConsulta vhc = new VistaHistorialConsulta();
                    
                    model = (DefaultTableModel) vhc.table_consultas.getModel();
                    
                    Object[] row = new Object[10];
                    
                    for (int i = 0; i < list.size(); i++) {
                        
                        row[0] = list.get(i).getConsulta().getId_consulta();
                        row[1] = list.get(i).getMascota().getAnimal().getNombre();
                        row[2] = list.get(i).getMascota().getNombre();
                        row[3] = list.get(i).getMascota().getRaza();
                        row[4] = list.get(i).getConsulta().getSintomas();
                        row[5] = list.get(i).getConsulta().getObservaciones();
                        row[6] = list.get(i).getConsulta().getDiagnostico();
                        row[7] = list.get(i).getConsulta().getReceta_medica();
                        row[8] = list.get(i).getFecha();
                        row[9] = list.get(i).getVeterinario().getNombre();
                        
                        model.addRow(row);
                    }
                    vhc.table_consultas.setModel(model);
                    
                    vhc.setVisible(true);
                }
                default -> throw new AssertionError();
            }
            
        }
    }

}
