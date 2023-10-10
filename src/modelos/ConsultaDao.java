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

public class ConsultaDao {

    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    //Registrar Consulta
    public boolean registrarConsulta(Consulta consulta) {

        String query = "INSERT INTO consulta(sintomas,observaciones,receta_medica,diagnostico,total) values(?,?,?,?,?)";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, consulta.getSintomas());
            pst.setString(2, consulta.getObservaciones());
            pst.setString(3, consulta.getReceta_medica());
            pst.setString(4, consulta.getDiagnostico());
            pst.setDouble(5, consulta.getTotal());

            pst.execute();

            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al resgistrar la oonsulta" + e);
            return false;
        }
    }

    //Obtener id de la consulta
    public int consultaId() {
        int id = 0;
        String query = "SELECT MAX(id_consulta) AS id FROM consulta";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public void llenarComboBoxVeterinario(JComboBox<String> cmb_bBox) {

        String query = "SELECT nombre FROM veterinario";

        cmb_bBox.removeAllItems();

        try {

            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                cmb_bBox.addItem(nombre);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    
}
