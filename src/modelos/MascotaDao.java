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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import vistas.VistaRegistrarClienteyMascota;

public class MascotaDao {

    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    VistaRegistrarClienteyMascota rdm;

    public MascotaDao(VistaRegistrarClienteyMascota rdm) {
        this.rdm = rdm;

    }

    //Registrar Mascota
    public boolean registrarMascota(Mascota mascota) {

        String query = "INSERT INTO mascota(nombre,edad,raza,color,tamano,fecha_nacimiento,dni,sexo,id_animal)  VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);

            pst.setString(1, mascota.getNombre());
            pst.setString(2, mascota.getEdad());
            pst.setString(3, mascota.getRaza());
            pst.setString(4, mascota.getColor());
            pst.setString(5, mascota.getTamano());

            Date date = rdm.dateChooser_FechaNacimiento.getDate();
            Timestamp timestamp = new Timestamp(date.getTime());

            pst.setTimestamp(6, timestamp);
            pst.setString(7, mascota.getDni_cliente());
            pst.setString(8, mascota.getSexo());
            pst.setInt(9, mascota.getId_animal());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al resgistrar a la mascota" + e);
            return false;
        }
    }

    //Listar Mascotas
    public List listarMascota(String value) {

        List<Mascota> list_mascotas = new ArrayList();

        String query = """
                       select ma.*,an.nombre as nombreAnimal from mascota as ma
                                                             inner join animal as an on ma.id_animal = an.id_animal""";
        String query_search_mascota = """
                                      select ma.*,an.nombre as nombreAnimal from mascota as ma
                                      inner join animal as an on ma.id_animal = an.id_animal WHERE ma.nombre LIKE '%""" + value + "%'";

        try {
            conn = conexion.getConnection();
            if (value.equalsIgnoreCase("")) {
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
            } else {
                pst = conn.prepareStatement(query_search_mascota);
                rs = pst.executeQuery();
            }

            while (rs.next()) {
                Mascota mascota = new Mascota();

                mascota.setId_mascota(rs.getInt("id_mascota"));
                mascota.setNombre(rs.getString("nombre"));
                mascota.setEdad(rs.getString("edad"));
                mascota.setRaza(rs.getString("raza"));
                mascota.setColor(rs.getString("color"));
                mascota.setTamano(rs.getString("tamano"));
                mascota.setSexo(rs.getString("sexo"));
                mascota.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                
                Animal animal = new Animal();
                animal.setNombre(rs.getString("nombreAnimal"));
                mascota.setAnimal(animal);

                list_mascotas.add(mascota);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list_mascotas;
    }

    public Cliente obtenerClienteDeMascota(String nombreMascota) {

        Cliente cliente = new Cliente();
        
        String query = "SELECT c.* "
                + "FROM cliente c "
                + "INNER JOIN mascota m ON c.dni = m.dni "
                + "WHERE m.nombre = ?";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, nombreMascota);
            rs = pst.executeQuery();

            if (rs.next()) {
                
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidos(rs.getString("apellido"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setCorreo_electronico(rs.getString("correo_electronico"));

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
         return cliente;
    }
    public int obtenerIdAnimal(String nombre) {
        int id_Animal = 0;
        String sql = "SELECT id_animal FROM animal WHERE nombre = ?";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, nombre);
            rs = pst.executeQuery();

            if (rs.next()) {
                id_Animal = rs.getInt("id_animal");
            }
        } catch (SQLException e) {
          JOptionPane.showMessageDialog(null, e.toString());
        }

        return id_Animal;
    }
    public int obtenerIdMascota(String nombre) {
        int id_Mascota = 0;
        String sql = "SELECT id_mascota FROM mascota WHERE nombre = ?";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, nombre);
            rs = pst.executeQuery();

            if (rs.next()) {
                id_Mascota = rs.getInt("id_mascota");
            }
        } catch (SQLException e) {
          JOptionPane.showMessageDialog(null, e.toString());
        }

        return id_Mascota;
    }
    public void llenarComboBoxAnimal(JComboBox<String> cmb_bBox) {

        String query = "SELECT nombre FROM animal";

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
