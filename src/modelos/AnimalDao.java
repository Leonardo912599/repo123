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


public class AnimalDao {
    
    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    public List listarAnimales() {

        List<Animal> list_animales = new ArrayList();

        String query = "SELECT id_animal, nombre, especie FROM animal";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Animal animal = new Animal();

                animal.setId_animal(rs.getInt("id_animal"));
                animal.setNombre(rs.getString("nombre"));
                animal.setEspecie(rs.getString("especie"));
                list_animales.add(animal);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list_animales;
    }
    
    public boolean registrarAnimal(Animal a) {
        String query = "INSERT INTO animal (nombre,especie) VALUES(?,?)";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);

            pst.setString(1, a.getNombre());
            pst.setString(2, a.getEspecie());

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al resgistrar animal: " + e);
            return false;
        }
    }
    
    public boolean modificarAnimal(Animal a) {
        String query = "UPDATE animal SET nombre=?, especie=? WHERE id_animal=?";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);

            pst.setString(1, a.getNombre());
            pst.setString(2, a.getEspecie());
            pst.setInt(3, a.getId_animal());

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar animal: " + e);
            return false;
        }
    }
    
    public boolean eliminarAnimal(Animal a) {
        String query = "DELETE FROM animal WHERE id_animal=?";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);

            pst.setInt(1, a.getId_animal());

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar animal: " + e);
            return false;
        }
    }
    
    public boolean mostrarDatos (Animal a){
        String query = "SELECT * FROM animal WHERE id_animal=?";
        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, a.getId_animal());
            rs = pst.executeQuery();
            
            if (rs.next()) {
                a.setId_animal(Integer.parseInt(rs.getString("id_animal")));
                a.setNombre(rs.getString("nombre"));
                a.setEspecie(rs.getString("especie"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos: " + e);
            return false;
        }
    }
    
}
