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
import modelos.Vacuna;
import modelos.VacunaDao;
import vistas.VistaVacunas;

public class ControladorVacunas implements ActionListener, MouseListener {

    private Vacuna vacuna;
    private VacunaDao vacunaDao;
    private VistaVacunas Vvac;
    DefaultTableModel model = new DefaultTableModel();

    public ControladorVacunas(Vacuna vacuna, VacunaDao vacunaDao, VistaVacunas Vvac) {
        this.vacuna = vacuna;
        this.vacunaDao = vacunaDao;
        this.Vvac = Vvac;

        this.Vvac.btnLimpiar.addActionListener(this);
        this.Vvac.btnGuardar.addActionListener(this);
        this.Vvac.btnEliminar.addActionListener(this);
        this.Vvac.tableVacunas.addMouseListener(this);
    }

    public void listarVacunas() {
        List<Vacuna> list = vacunaDao.listarVacunas();

        model = (DefaultTableModel) Vvac.tableVacunas.getModel();

        Object[] row = new Object[3];

        for (int i = 0; i < list.size(); i++) {

            row[0] = list.get(i).getId_vacuna();
            row[1] = list.get(i).getNombre();
            row[2] = list.get(i).getDescripcion();
            model.addRow(row);
        }
        Vvac.tableVacunas.setModel(model);
    }

    public void cleanTable() {

        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Vvac.btnGuardar) {
            if (Vvac.txtID.getText().equals("")) {
                if (Vvac.txtNombre.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "La Vacuna no puede estar en blanco");
                } else {
                    vacuna.setNombre(Vvac.txtNombre.getText());
                    vacuna.setDescripcion(Vvac.txtDescripcion.getText());
                    if (vacunaDao.registrarVacuna(vacuna)) {
                        cleanTable();
                        listarVacunas();
                        JOptionPane.showMessageDialog(null, "Agregado Correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al agregar la Vacuna");
                    }

                }
            } else {
                if (Vvac.txtNombre.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "La Vacuna no puede estar en blanco");
                } else {
                    vacuna.setNombre(Vvac.txtNombre.getText());
                    vacuna.setDescripcion(Vvac.txtDescripcion.getText());
                    if (vacunaDao.modificarVacuna(vacuna)) {
                        cleanTable();
                        listarVacunas();
                        JOptionPane.showMessageDialog(null, "Modificado Correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar Vacuna");
                    }

                }

            }
        }
        if (e.getSource() == Vvac.btnEliminar) {
            try {
                int row = Vvac.tableVacunas.getSelectedRow();
                vacuna.setId_vacuna(Integer.parseInt(Vvac.tableVacunas.getValueAt(row, 0).toString()));
                vacunaDao.eliminarVacuna(vacuna);
                model.removeRow(row);
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Selecciona un Registro");
            }
        }
        if (e.getSource() == Vvac.btnLimpiar) {
            Vvac.txtID.setText("");
            Vvac.txtNombre.setText("");
            Vvac.txtDescripcion.setText("");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = Vvac.tableVacunas.getSelectedRow();
        vacuna.setId_vacuna(Integer.parseInt(Vvac.tableVacunas.getValueAt(row, 0).toString()));
        if (vacunaDao.mostrarDatos(vacuna)) {
            Vvac.txtID.setText(String.valueOf(vacuna.getId_vacuna()));
            Vvac.txtNombre.setText(vacuna.getNombre());
            Vvac.txtDescripcion.setText(vacuna.getDescripcion());
        } else {
            JOptionPane.showMessageDialog(null, "Error al mostrar Vacuna");
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
