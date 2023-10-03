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
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.ClienteDao;
import vistas.BuscarCliente;
import vistas.RegistrarDueñoyMascota;

public class ControladorBuscarCliente implements ActionListener,MouseListener {

    private Cliente cliente;
    private ClienteDao clienteDao;
    private BuscarCliente bc;
    DefaultTableModel model = new DefaultTableModel();

    public ControladorBuscarCliente(Cliente cliente, ClienteDao clienteDao, BuscarCliente bc) {
        this.cliente = cliente;
        this.clienteDao = clienteDao;
        this.bc = bc;
        this.bc.button_buscar.addActionListener(this);
        this.bc.table_cliente.addMouseListener(this);
         
    }

    //Listar clientes
    public void listarClientes() {
        List<Cliente> list = clienteDao.listarCliente();

        model = (DefaultTableModel) bc.table_cliente.getModel();

        Object[] row = new Object[5];

        for (int i = 0; i < list.size(); i++) {

            row[0] = list.get(i).getDni();
            row[1] = list.get(i).getNombre();
            row[2] = list.get(i).getApellidos();
            row[3] = list.get(i).getCelular();
            row[4] = list.get(i).getCorreo_electronico();
            model.addRow(row);
        }
        bc.table_cliente.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bc.button_buscar) {
            JComboBox<String> comboBox = (JComboBox<String>) bc.cmb_cliente;
            clienteDao.buscarCliente(comboBox);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
        if(e.getSource() ==bc.table_cliente){
            int row = bc.table_cliente.rowAtPoint(e.getPoint());
            RegistrarDueñoyMascota rdm = new RegistrarDueñoyMascota();
            rdm.txt_dniPropietario.setText(bc.table_cliente.getValueAt(row,0).toString());
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
