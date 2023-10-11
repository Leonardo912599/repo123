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
import vistas.RegistrarVeterinarios;

public class VeterinarioDao {

    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    public RegistrarVeterinarios rv;

    public boolean registrarVeterinario(Veterinario vet) {
        String query = "INSERT INTO veterinario (nombre_usuario,nombre,apellidos,celular,correo,contrasena) VALUES(?,?,?,?,?,?)";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);

            pst.setString(1, vet.getNombre_user());
            pst.setString(2, vet.getNombre());
            pst.setString(3, vet.getApellidos());
            pst.setString(4, vet.getCelular());
            pst.setString(5, vet.getCorreo());
            pst.setString(6, vet.getContrasena());

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al resgistrar al veterinario: " + e);
            return false;
        }
    }

    public boolean modificarVeterinario(Veterinario vet) {
        String query = "UPDATE veterinario SET nombre_usuario=?, nombre=?, apellidos=?, celular=?, correo=?, contrasena=? WHERE id_veterinario=?";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);

            pst.setString(1, vet.getNombre_user());
            pst.setString(2, vet.getNombre());
            pst.setString(3, vet.getApellidos());
            pst.setString(4, vet.getCelular());
            pst.setString(5, vet.getCorreo());
            pst.setString(6, vet.getContrasena());
            pst.setInt(7, vet.getId_veterinario());

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar al veterinario: " + e);
            return false;
        }
    }

    public boolean eliminarVeterinario(Veterinario vet) {
        String query = "DELETE FROM veterinario WHERE id_veterinario=?";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);

            pst.setInt(1, vet.getId_veterinario());

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar al veterinario: " + e);
            return false;
        }
    }

    public List listarVet() {

        List<Veterinario> list_veterinarios = new ArrayList();

        String query = "SELECT id_veterinario, nombre_usuario, nombre, apellidos, celular, correo FROM veterinario";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Veterinario veterinario = new Veterinario();

                veterinario.setId_veterinario(rs.getInt("id_veterinario"));
                veterinario.setNombre_user(rs.getString("nombre_usuario"));
                veterinario.setNombre(rs.getString("nombre"));
                veterinario.setApellidos(rs.getString("apellidos"));
                veterinario.setCelular(rs.getString("celular"));
                veterinario.setCorreo(rs.getString("correo"));

                list_veterinarios.add(veterinario);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list_veterinarios;
    }

    public boolean obtenerDatos(Veterinario veterinario) {
        String query = "SELECT * FROM veterinario WHERE id_veterinario = ?  ";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, veterinario.getId_veterinario());
            rs = pst.executeQuery();

            if (rs.next()) {
                veterinario.setId_veterinario(Integer.parseInt(rs.getString("id_veterinario")));
                veterinario.setNombre_user(rs.getString("nombre_usuario"));
                veterinario.setNombre(rs.getString("nombre"));
                veterinario.setApellidos(rs.getString("apellidos"));
                veterinario.setCelular(rs.getString("celular"));
                veterinario.setCorreo(rs.getString("correo"));
                veterinario.setContrasena(rs.getString("contrasena"));
                return true;
            }
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos del veterinario: " + e);
            return false;
        }
    }

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
                veterinario.setNombre_user(rs.getString(2));
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
