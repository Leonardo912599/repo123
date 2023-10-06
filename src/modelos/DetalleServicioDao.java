/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.sql.Types;
import javax.swing.JOptionPane;


public class DetalleServicioDao {

    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    
    //Registrar DetalleServicio
    
    public boolean registrarDetalleServicio(DetalleServicio detalleServicio) {
    String query = "INSERT INTO detalleservicio(fecha,id_consulta,id_vacunacion,id_estetica,id_mascota,id_veterinario) VALUES(?,?,?,?,?,?)";
    Timestamp datetime = new Timestamp(new Date().getTime());

    try {
        conn = conexion.getConnection();
        pst = conn.prepareStatement(query);
        pst.setTimestamp(1, datetime);

        // Verificar si id_consulta es null
        if (detalleServicio.getId_consulta() != null) {
            pst.setInt(2, detalleServicio.getId_consulta());
        } else {
            pst.setNull(2, Types.INTEGER); // Establecer como NULL
        }

        // Verificar si id_vacunacion es null
        if (detalleServicio.getId_vacunacion() != null) {
            pst.setInt(3, detalleServicio.getId_vacunacion());
        } else {
            pst.setNull(3, Types.INTEGER); // Establecer como NULL
        }

        // Verificar si id_estetica es null
        if (detalleServicio.getId_estetica() != null) {
            pst.setInt(4, detalleServicio.getId_estetica());
        } else {
            pst.setNull(4, Types.INTEGER); // Establecer como NULL
        }

        pst.setInt(5, detalleServicio.getId_mascota());
        pst.setInt(6, detalleServicio.getId_veterinario());

        pst.execute();

        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al registrar el detalle del servicio" + e);
        return false;
    }
}

    public int obtenerIdVeterinario(String nombre) {
        int id_Veterinario = 0;
        String sql = "SELECT id_veterinario FROM veterinario WHERE nombre = ?";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, nombre);
            rs = pst.executeQuery();

            if (rs.next()) {
                id_Veterinario = rs.getInt("id_veterinario");
            }
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, e.toString());
        }

        return id_Veterinario;
    }
    
}
