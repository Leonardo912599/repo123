package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;
import modelos.Veterinario;
import modelos.VeterinarioDao;
import vistas.RegistrarVeterinarios;
import vistas.VistaVeterinarios;

public class ControladorRegistrarVet implements ActionListener, MouseListener {

    private Veterinario veterinario;
    private VeterinarioDao veterinarioDao;
    private RegistrarVeterinarios rv;
    private VistaVeterinarios vets;
    DefaultTableModel model = new DefaultTableModel();

    public ControladorRegistrarVet(Veterinario veterinario, VeterinarioDao veterinarioDao, RegistrarVeterinarios rv, VistaVeterinarios vets) {

        this.veterinario = veterinario;
        this.veterinarioDao = veterinarioDao;
        this.rv = rv;
        this.vets = vets;

        this.vets.btnEditar.addActionListener(this);
        this.vets.btnEliminar.addActionListener(this);
        this.vets.btnNuevoReg.addActionListener(this);
        this.vets.table_vets.addMouseListener(this);
        this.rv.btnGuardar.addActionListener(this);
        this.rv.btnVolver.addActionListener(this);
    }

    public void listarVeterinarios() {
        List<Veterinario> list = veterinarioDao.listarVet();

        model = (DefaultTableModel) vets.table_vets.getModel();

        Object[] row = new Object[6];

        for (int i = 0; i < list.size(); i++) {

            row[0] = list.get(i).getId_veterinario();
            row[1] = list.get(i).getNombre_user();
            row[2] = list.get(i).getNombre();
            row[3] = list.get(i).getApellidos();
            row[4] = list.get(i).getCelular();
            row[5] = list.get(i).getCorreo();
            model.addRow(row);
        }
        vets.table_vets.setModel(model);
    }

    public void cleanTable() {

        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vets.btnNuevoReg) {
            rv.txtID.setText("");
            rv.txtUsuario.setText("");
            rv.txtNombre.setText("");
            rv.txtApellidos.setText("");
            rv.txtCelular.setText("");
            rv.txtCorreo.setText("");
            rv.txtContrasena.setText("");
            rv.setVisible(true);
        }
        if (e.getSource() == vets.btnEditar) {

            try {
                int row = vets.table_vets.getSelectedRow();
                veterinario.setId_veterinario(Integer.parseInt(vets.table_vets.getValueAt(row, 0).toString()));
                if (veterinarioDao.obtenerDatos(veterinario)) {
                    rv.txtID.setText(String.valueOf(veterinario.getId_veterinario()));
                    rv.txtUsuario.setText(veterinario.getNombre_user());
                    rv.txtNombre.setText(veterinario.getNombre());
                    rv.txtApellidos.setText(veterinario.getApellidos());
                    rv.txtCelular.setText(veterinario.getCelular());
                    rv.txtCorreo.setText(veterinario.getCorreo());
                    rv.txtContrasena.setText(veterinario.getContrasena());

                    rv.setVisible(true);

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Selecciona un veterinario");

            }
        }
        if (e.getSource() == vets.btnEliminar) {

            try {
                int row = vets.table_vets.getSelectedRow();
                veterinario.setId_veterinario(Integer.parseInt(vets.table_vets.getValueAt(row, 0).toString()));
                veterinarioDao.eliminarVeterinario(veterinario);
                model.removeRow(row);
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Selecciona un veterinario");
            }

        }

        if (e.getSource() == rv.btnGuardar) {
            if (rv.txtID.getText().equals("")) {
                if (rv.txtUsuario.getText().equals("")
                        || rv.txtNombre.getText().equals("")
                        || rv.txtApellidos.getText().equals("")
                        || rv.txtCelular.getText().equals("")
                        || rv.txtCorreo.getText().equals("")
                        || rv.txtContrasena.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debes Ingesar todos los datos");
                } else {
                    veterinario.setNombre_user(rv.txtUsuario.getText());
                    veterinario.setNombre(rv.txtNombre.getText());
                    veterinario.setApellidos(rv.txtApellidos.getText());
                    veterinario.setCelular(rv.txtCelular.getText());
                    veterinario.setCorreo(rv.txtCorreo.getText());
                    veterinario.setContrasena(rv.txtContrasena.getText());
                    if (veterinarioDao.registrarVeterinario(veterinario)) {
                        JOptionPane.showMessageDialog(null, "Agregado Correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al agregar Veterinario");
                    }
                }
            } else {
                if (rv.txtUsuario.getText().equals("")
                        || rv.txtNombre.getText().equals("")
                        || rv.txtApellidos.getText().equals("")
                        || rv.txtCelular.getText().equals("")
                        || rv.txtCorreo.getText().equals("")
                        || rv.txtContrasena.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debes Ingesar todos los datos");
                } else {
                    veterinario.setId_veterinario(Integer.parseInt(rv.txtID.getText()));
                    veterinario.setNombre_user(rv.txtUsuario.getText());
                    veterinario.setNombre(rv.txtNombre.getText());
                    veterinario.setApellidos(rv.txtApellidos.getText());
                    veterinario.setCelular(rv.txtCelular.getText());
                    veterinario.setCorreo(rv.txtCorreo.getText());
                    veterinario.setContrasena(rv.txtContrasena.getText());

                    if (veterinarioDao.modificarVeterinario(veterinario)) {
                        JOptionPane.showMessageDialog(null, "Modificado Correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar Veterinario");
                    }
                }
            }
        }

        if (e.getSource() == rv.btnVolver) {
            cleanTable();
            listarVeterinarios();
            rv.setVisible(false);
            vets.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e
    ) {

    }

    @Override
    public void mouseReleased(MouseEvent e
    ) {
    }

    @Override
    public void mouseEntered(MouseEvent e
    ) {
    }

    @Override
    public void mouseExited(MouseEvent e
    ) {
    }

}
