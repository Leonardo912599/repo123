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
import javax.swing.JOptionPane;


public class VacunaDao {
    
    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    public List listarVacunas() {

        List<Vacuna> list_vacunas = new ArrayList();

        String query = "SELECT id_vacuna, nombre, descripcion FROM vacuna";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Vacuna vacuna = new Vacuna();

                vacuna.setId_vacuna(rs.getInt("id_vacuna"));
                vacuna.setNombre(rs.getString("nombre"));
                vacuna.setDescripcion(rs.getString("descripcion"));
                list_vacunas.add(vacuna);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list_vacunas;
    }
    
    public boolean registrarVacuna(Vacuna vac) {
        String query = "INSERT INTO vacuna (nombre,descripcion) VALUES(?,?)";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);

            pst.setString(1, vac.getNombre());
            pst.setString(2, vac.getDescripcion());

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al resgistrar Vacuna: " + e);
            return false;
        }
    }
    
    public boolean modificarVacuna(Vacuna vac) {
        String query = "UPDATE vacuna SET nombre=?, descripcion=? WHERE id_vacuna=?";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);

            pst.setString(1, vac.getNombre());
            pst.setString(2, vac.getDescripcion());
            pst.setInt(3, vac.getId_vacuna());

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar vacuna: " + e);
            return false;
        }
    }
    
    public boolean eliminarVacuna(Vacuna vac) {
        String query = "DELETE FROM vacuna WHERE id_vacuna=?";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);

            pst.setInt(1, vac.getId_vacuna());

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar vacuna: " + e);
            return false;
        }
    }
    
    public boolean mostrarDatos (Vacuna vac){
        String query = "SELECT * FROM vacuna WHERE id_vacuna=?";
        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, vac.getId_vacuna());
            rs = pst.executeQuery();
            
            if (rs.next()) {
                vac.setId_vacuna(Integer.parseInt(rs.getString("id_vacuna")));
                vac.setNombre(rs.getString("nombre"));
                vac.setDescripcion(rs.getString("descripcion"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos: " + e);
            return false;
        }
    }
    
}
