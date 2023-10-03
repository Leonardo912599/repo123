/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vistas.BuscarCliente;

public class ClienteDao {

    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    public BuscarCliente bc;

    

    //Registrar Cliente
    public boolean registrarCliente(Cliente cliente) {

        String query = "INSERT INTO cliente(dni,nombre,apellido,celular,correo_electronico) values(?,?,?,?,?)";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, cliente.getDni());
            pst.setString(2, cliente.getNombre());
            pst.setString(3, cliente.getApellidos());
            pst.setString(4, cliente.getCelular());
            pst.setString(5, cliente.getCorreo_electronico());

            pst.execute();

            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al resgistrar al cliente" + e);
            return false;
        }
    }
    //Listar CLiente

    public List listarCliente() {

        List<Cliente> list_clientes = new ArrayList();

        String query = "SELECT * FROM cliente";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidos(rs.getString("apellido"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setCorreo_electronico(rs.getString("correo_electronico"));

                list_clientes.add(cliente);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list_clientes;
    }
    
//Listar CLiente por id

    public List<Cliente> listarClienteById(String value) {
    List<Cliente> list_cliente = new ArrayList<>();

    if (value == null) {
        return list_cliente; // O manejar la situación de valor nulo según tus necesidades.
    }

    String query = "SELECT * FROM cliente WHERE dni = ?";

    try (Connection conn = conexion.getConnection();
         PreparedStatement pst = conn.prepareStatement(query)) {

        pst.setString(1, value);
        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidos(rs.getString("apellido"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setCorreo_electronico(rs.getString("correo_electronico"));
                list_cliente.add(cliente);
            }
        }
    } catch (SQLException e) {
        // Manejo de excepciones aquí
        e.printStackTrace(); // O registra el error en un archivo de registro.
    }
    
    return list_cliente;
}

    
    //Modificar cliente
    public boolean modificarCliente(Cliente cliente) {

        String query = "UPDATE cliente SET nombre = ?,apellido = ?,celular = ?,correo_electronico = ? WHERE dni = ? ";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);

            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getApellidos());
            pst.setString(3, cliente.getCelular());
            pst.setString(4, cliente.getCorreo_electronico());
            pst.setString(5, cliente.getDni());

            pst.execute();

            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar los datos del cliente" + e);
            return false;
        }
    }

    public void buscarCliente(JComboBox<String> comboBox) {
        String query = "SELECT * FROM cliente AS cli";

        if (comboBox.getSelectedItem().equals("Nombre")) {
            String nombreBuscado = bc.txt_buscarCliente.getText();
            if (!nombreBuscado.isEmpty()) {
                query += " WHERE cli.nombre LIKE ?";
            }
        } else if (comboBox.getSelectedItem().equals("Dni")) {
            String dniBuscado = bc.txt_buscarCliente.getText();
            if (!dniBuscado.isEmpty()) {
                query += " WHERE cli.dni = ?";
            }
        }

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);

            if (comboBox.getSelectedItem().equals("Nombre")) {
                String nombreBuscado = bc.txt_buscarCliente.getText();
                if (!nombreBuscado.isEmpty()) {
                    pst.setString(1, "%" + nombreBuscado + "%");
                }
            } else if (comboBox.getSelectedItem().equals("Dni")) {
                String dniBuscado = bc.txt_buscarCliente.getText();
                if (!dniBuscado.isEmpty()) {
                    pst.setString(1, dniBuscado);
                }
            }

            rs = pst.executeQuery();

            // Aquí se procesan los resultados y se llenan la tabla con los datos obtenidos
            DefaultTableModel model = (DefaultTableModel) bc.table_cliente.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getString("dni");
                row[1] = rs.getString("nombre");
                row[2] = rs.getString("apellido");
                row[3] = rs.getString("celular");
                row[4] = rs.getString("correo_electronico");
                model.addRow(row);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
