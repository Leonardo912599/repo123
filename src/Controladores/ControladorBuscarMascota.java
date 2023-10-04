/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controladores;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Mascota;
import modelos.MascotaDao;
import vistas.VistaAgregarCita;
import vistas.VistaBuscarMascota;


public class ControladorBuscarMascota implements KeyListener ,MouseListener{

    private MascotaDao mascotaDao;
    private VistaBuscarMascota vistaBuscarMascota;
    
    DefaultTableModel model = new DefaultTableModel();
    
    public ControladorBuscarMascota(MascotaDao mascotaDao, VistaBuscarMascota vistaBuscarMascota) {
        this.mascotaDao = mascotaDao;
        this.vistaBuscarMascota = vistaBuscarMascota;
        this.vistaBuscarMascota.txt_buscarMascota.addKeyListener(this);
        this.vistaBuscarMascota.table_mascotas.addMouseListener(this);
    }

    
    
    public void listarMascota(){
        
        List<Mascota> list_mascota = mascotaDao.listarMascota(vistaBuscarMascota.txt_buscarMascota.getText());
        
        model = (DefaultTableModel) vistaBuscarMascota.table_mascotas.getModel();
        
        Object [] row = new Object[8];
        
        for (int i = 0; i < list_mascota.size(); i++) {
            
            row[0] = list_mascota.get(i).getId_mascota();
            row[1] = list_mascota.get(i).getNombre();
            row[2] = list_mascota.get(i).getAnimal().getNombre();
            row[3] = list_mascota.get(i).getSexo();
            row[4] = list_mascota.get(i).getTamano();
            row[5] = list_mascota.get(i).getColor();
            row[6] = list_mascota.get(i).getRaza();
            row[7] = list_mascota.get(i).getEdad();
            
            model.addRow(row);
        }
         vistaBuscarMascota.table_mascotas.setModel(model);
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
       if(e.getSource() == vistaBuscarMascota.txt_buscarMascota){
           cleanTable();
           listarMascota();
       }
    }
    
    public void cleanTable() {

        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vistaBuscarMascota.table_mascotas){
            int row = vistaBuscarMascota.table_mascotas.rowAtPoint(e.getPoint());
            VistaAgregarCita vac = new VistaAgregarCita();
            vac.txt_nombreMascota.setText(vistaBuscarMascota.table_mascotas.getValueAt(row,1).toString());
            vac.txt_animal.setText(vistaBuscarMascota.table_mascotas.getValueAt(row,2).toString());
            vac.txt_sexo.setText(vistaBuscarMascota.table_mascotas.getValueAt(row,3).toString());
            vac.txt_raza.setText(vistaBuscarMascota.table_mascotas.getValueAt(row,6).toString());
            
           Cliente cliente= mascotaDao.obtenerClienteDeMascota(vistaBuscarMascota.table_mascotas.getValueAt(row,1).toString());
           
           vac.txt_nroDocumento.setText(cliente.getDni());
           vac.txt_nombreCliente.setText(cliente.getNombre());
           vac.txt_apellidos.setText(cliente.getApellidos());
           vac.txt_celular.setText(cliente.getCelular());
           vac.txt_correo.setText(cliente.getCorreo_electronico());
            
           vac.setVisible(true);
        }
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
}
