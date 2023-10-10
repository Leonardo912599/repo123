/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class VeterinarioDao {

    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    //Validar
    public Veterinario validar(String nombre, String contrasena) {

        String query = "SELECT * FROM veterinario WHERE nombre_usuario = ? AND contrasena = ?;";

        Veterinario veterinario = new Veterinario();

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, nombre);
            pst.setString(2, contrasena);
            rs = pst.executeQuery();

            if (rs.next()) {
                
                veterinario.setId_veterinario(rs.getInt(1));
                veterinario.setNombre_usuario(rs.getString(2));
                veterinario.setNombre(rs.getString(3));
                veterinario.setApellidos(rs.getString(4));
                veterinario.setCelular(rs.getString(5));
                veterinario.setContrasena(rs.getString(6));
               
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return veterinario;
    }

}
