/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Animal;
import modelos.AnimalDao;
import vistas.VistaAnimal;

public class ControladorAnimales implements ActionListener, MouseListener {

    private Animal animal;
    private AnimalDao animalDao;
    private VistaAnimal val;
    DefaultTableModel model = new DefaultTableModel();

    public ControladorAnimales(Animal animal, AnimalDao animalDao, VistaAnimal val) {
        this.animal = animal;
        this.animalDao = animalDao;
        this.val = val;

        this.val.btnLimpiar.addActionListener(this);
        this.val.btnGuardar.addActionListener(this);
        this.val.btnEliminar.addActionListener(this);
        this.val.tableAnimal.addMouseListener(this);
    }

    public void listarAnimales() {
        List<Animal> list = animalDao.listarAnimales();

        model = (DefaultTableModel) val.tableAnimal.getModel();

        Object[] row = new Object[3];

        for (int i = 0; i < list.size(); i++) {

            row[0] = list.get(i).getId_animal();
            row[1] = list.get(i).getNombre();
            row[2] = list.get(i).getEspecie();
            model.addRow(row);
        }
        val.tableAnimal.setModel(model);
    }

    public void cleanTable() {

        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == val.btnGuardar) {
            if (val.txtID.getText().equals("")) {
                if (val.txtAnimal.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Animal no puede estar vacio");
                } else {
                    animal.setNombre(val.txtAnimal.getText());
                    animal.setEspecie(val.txtEspecie.getText());
                    if (animalDao.registrarAnimal(animal)) {
                        cleanTable();
                        listarAnimales();
                        JOptionPane.showMessageDialog(null, "Agregado Correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al agregar Animal");
                    }

                }
            } else {
                if (val.txtAnimal.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Animal no puede estar vacio");
                } else {
                    animal.setNombre(val.txtAnimal.getText());
                    animal.setEspecie(val.txtEspecie.getText());
                    if (animalDao.modificarAnimal(animal)) {
                        cleanTable();
                        listarAnimales();
                        JOptionPane.showMessageDialog(null, "Modificado Correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar Animal");
                    }

                }

            }
        }
        if (e.getSource() == val.btnEliminar) {
            try {
                int row = val.tableAnimal.getSelectedRow();
                animal.setId_animal(Integer.parseInt(val.tableAnimal.getValueAt(row, 0).toString()));
                animalDao.eliminarAnimal(animal);
                model.removeRow(row);
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Selecciona un Registro");
            }
        }
        if (e.getSource() == val.btnLimpiar){
            val.txtID.setText("");
            val.txtAnimal.setText("");
            val.txtEspecie.setText("");
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = val.tableAnimal.getSelectedRow();
        animal.setId_animal(Integer.parseInt(val.tableAnimal.getValueAt(row, 0).toString()));
        if (animalDao.mostrarDatos(animal)) {
            val.txtID.setText(String.valueOf(animal.getId_animal()));
            val.txtAnimal.setText(animal.getNombre());
            val.txtEspecie.setText(animal.getEspecie());
        } else {
            JOptionPane.showMessageDialog(null, "Error al mostrar Animal");
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
