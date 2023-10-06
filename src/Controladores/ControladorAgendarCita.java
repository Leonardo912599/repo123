/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.AgendarCitaDao;
import vistas.VistaAgendarCita;
import modelos.AgendarCita;
import modelos.Cliente;
import modelos.MascotaDao;
import vistas.VistaAgregarCita;

public class ControladorAgendarCita implements MouseListener, ActionListener {

    private VistaAgendarCita vistaAgendarCita;
    private AgendarCitaDao agendarCitaDao;
    private MascotaDao mascotaDao;

    DefaultTableModel model = new DefaultTableModel();

    public ControladorAgendarCita(VistaAgendarCita vistaAgendarCita, AgendarCitaDao agendarCitaDao, AgendarCita agendarCita,MascotaDao mascotaDao) {
        this.vistaAgendarCita = vistaAgendarCita;
        this.agendarCitaDao = agendarCitaDao;
        this.mascotaDao = mascotaDao;
        this.vistaAgendarCita.button_agendarCita.addMouseListener(this);
        this.vistaAgendarCita.button_buscar.addActionListener(this);
        this.vistaAgendarCita.button_editarCita.addMouseListener(this);
        this.vistaAgendarCita.table_cita.addMouseListener(this);
        this.vistaAgendarCita.button_borrarCita.addActionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vistaAgendarCita.button_agendarCita) {
            VistaAgregarCita ac = new VistaAgregarCita();
            ac.setVisible(true);
        } else if (e.getSource() == vistaAgendarCita.button_editarCita) {

            int row = vistaAgendarCita.table_cita.getSelectedRow();
            VistaAgregarCita vac = new VistaAgregarCita();
            
            vac.txt_id_cita.setText(vistaAgendarCita.table_cita.getValueAt(row, 0).toString());
            vac.txt_nombreMascota.setText(vistaAgendarCita.table_cita.getValueAt(row, 8).toString());
            vac.txt_animal.setText(vistaAgendarCita.table_cita.getValueAt(row, 7).toString());
            vac.txt_sexo.setText(vistaAgendarCita.table_cita.getValueAt(row, 9).toString());
            vac.txt_raza.setText(vistaAgendarCita.table_cita.getValueAt(row, 10).toString());
            Object detalle_cita = vistaAgendarCita.table_cita.getValueAt(row, 5);
            Object observaciones = vistaAgendarCita.table_cita.getValueAt(row, 6);
            if (detalle_cita != null ) {
                vac.txtArea_detalleCita.setText(detalle_cita.toString());
            }
            if(observaciones != null){
                vac.txtArea_detalleCita.setText(observaciones.toString());
            }
            vac.cmb_servicios.setSelectedItem(vistaAgendarCita.table_cita.getValueAt(row, 4).toString());
            vac.cmb_estado.setSelectedItem(vistaAgendarCita.table_cita.getValueAt(row, 3).toString());
            vac.txt_horaCita.setText(vistaAgendarCita.table_cita.getValueAt(row, 2).toString());
            vac.dateChooser_fechaCita.setDate((Date) vistaAgendarCita.table_cita.getValueAt(row, 1));
            
            Cliente cliente = mascotaDao.obtenerClienteDeMascota(vistaAgendarCita.table_cita.getValueAt(row, 8).toString());
            
            vac.txt_nombreCliente.setText(cliente.getNombre());
            vac.txt_nroDocumento.setText(cliente.getDni());
            vac.txt_apellidos.setText(cliente.getNombre());
            vac.txt_celular.setText(cliente.getCelular());
            vac.txt_correo.setText(cliente.getCorreo_electronico());
            
            vac.setVisible(true);
        }else if(e.getSource() == vistaAgendarCita.table_cita){
            
             int row = vistaAgendarCita.table_cita.rowAtPoint(e.getPoint());
             
             Object datos = vistaAgendarCita.table_cita.getValueAt(row, 6);
             
             if(datos != null){
                 vistaAgendarCita.textArea_datos.setText((String) datos);
             }
             vistaAgendarCita.txt_estadoCita.setText(vistaAgendarCita.table_cita.getValueAt(row, 3).toString());
             
        }
    }

    //Listar clientes
    public void listarCitas() {
        List<AgendarCita> list = agendarCitaDao.listarCitas();

        model = (DefaultTableModel) vistaAgendarCita.table_cita.getModel();
        Object[] row = new Object[11];

        for (int i = 0; i < list.size(); i++) {

         row[0] = list.get(i).getId_agendarCita();
                row[1] = list.get(i).getFecha();
                row[2] = list.get(i).getHora();
                row[3] = list.get(i).getEstado();
                row[4] = list.get(i).getTipo_sevicio();
                row[5] = list.get(i).getDetalle_cita();
                row[6] = list.get(i).getObservaciones();
                row[7] = list.get(i).getAnimal().getNombre();
                row[8] = list.get(i).getMascota().getNombre();
                row[9] = list.get(i).getMascota().getSexo();
                row[10] = list.get(i).getMascota().getRaza();

            model.addRow(row);
        }
        vistaAgendarCita.table_cita.setModel(model);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaAgendarCita.button_buscar) {
            cleanTable();
            List<AgendarCita> list = agendarCitaDao.listarCitas(vistaAgendarCita.dateChooser_buscarCita.getDate());

            model = (DefaultTableModel) vistaAgendarCita.table_cita.getModel();
            Object[] row = new Object[11];

            for (int i = 0; i < list.size(); i++) {

                row[0] = list.get(i).getId_agendarCita();
                row[1] = list.get(i).getFecha();
                row[2] = list.get(i).getHora();
                row[3] = list.get(i).getEstado();
                row[4] = list.get(i).getTipo_sevicio();
                row[5] = list.get(i).getDetalle_cita();
                row[6] = list.get(i).getObservaciones();
                row[7] = list.get(i).getAnimal().getNombre();
                row[8] = list.get(i).getMascota().getNombre();
                row[9] = list.get(i).getMascota().getSexo();
                row[10] = list.get(i).getMascota().getRaza();

                model.addRow(row);
            }
            vistaAgendarCita.table_cita.setModel(model);
        }else if(e.getSource() == vistaAgendarCita.button_borrarCita){
             int row = vistaAgendarCita.table_cita.getSelectedRow();
             
              if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un cita para eliminar");

            } else {
                int id_cita =(int) vistaAgendarCita.table_cita.getValueAt(row,0);
                int question = JOptionPane.showConfirmDialog(null, "¿En realidad quieres eliminar a esta cita?");

                if (question == 0) {
                 agendarCitaDao.deleteCita(id_cita);
                    cleanTable();  
                    listarCitas();
                    JOptionPane.showMessageDialog(null, "Cita eliminada con exito");
                }
            }
             
        }
    }

    public void cleanTable() {

        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }
}
