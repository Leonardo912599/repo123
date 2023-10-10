/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


public class VacunacionDesparacitacionDao {

     Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    //Registrar Vacunacion_Desparacitacion
    public boolean registrarVacunacionDesparacitacion(VacunacionDesparacitacion vd) {

        String query = "INSERT INTO vacunaciondesparacitacion(vacunacion,desparacitacion,proxima_vacuna,desparacitante,total,id_vacuna) values(?,?,?,?,?,?)";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, vd.getVacunacion());
            pst.setInt(2, vd.getDesparacitacion());
            pst.setString(3, vd.getProxima_vacuna());
            pst.setString(4, vd.getDesparacitante());
            pst.setDouble(5, vd.getTotal());
            pst.setInt(6,vd.getId_vacuna());

            pst.execute();

            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al resgistrar el servicio de vacunacion desparacitacion" + e);
            return false;
        }
    }
     //Obtener id de vacunacion desparacitacion
    public int vacunacionId() {
        int id = 0;
        String query = "SELECT MAX(id_vacunacionDesparacitacion) AS id FROM vacunaciondesparacitacion";

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
    public int obtenerIdVacuna(String nombre) {
        int id_Vacuna = 0;
        String sql = "SELECT id_vacuna FROM vacuna WHERE nombre = ?";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, nombre);
            rs = pst.executeQuery();

            if (rs.next()) {
                id_Vacuna = rs.getInt("id_vacuna");
            }
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, e.toString());
        }

        return id_Vacuna;
    }
     public void llenarComboBoxVacuna(JComboBox<String> cmb_bBox) {

        String query = "SELECT nombre FROM vacuna";

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
