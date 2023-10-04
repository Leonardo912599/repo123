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
import javax.swing.JOptionPane;
import vistas.VistaAgregarCita;

public class AgendarCitaDao {

    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    VistaAgregarCita agregarCita;

    public AgendarCitaDao(VistaAgregarCita agregarCita) {
        this.agregarCita = agregarCita;
    }

    //Registrar cita
    public boolean registrarCita(AgendarCita agendarCita) {

        // Verificar si ya existe una cita con la misma fecha y hora
        if (existeCitaExistente(agendarCita)) {
            JOptionPane.showMessageDialog(null, "Ya existe una cita en la misma fecha y hora.");
            return false;
        }

        String query = "INSERT INTO agendarcita(hora,tipo_servicio,fecha,id_mascota,detalle_cita) values(?,?,?,?,?)";

        Date date = agregarCita.dateChooser_fechaCita.getDate();
        Timestamp timestamp = new Timestamp(date.getTime());

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, agendarCita.getHora());
            pst.setString(2, agendarCita.getTipo_sevicio());
            pst.setTimestamp(3, timestamp);
            pst.setInt(4, agendarCita.getId_mascota());
            pst.setString(5, agendarCita.getDetalle_cita());

            pst.execute();

            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al resgistrar la cita" + e);
            return false;
        }
    }
    //Modificar la cita
    public boolean modificarCita(AgendarCita agendarCita) {
        
        
        if (existeCitaExistente(agendarCita)) {
            JOptionPane.showMessageDialog(null, "Ya existe una cita en la misma fecha y hora.");
            return false;
        }

        String query = "UPDATE agendarcita SET hora = ?,tipo_servicio = ?,fecha = ?,estado = ?,id_mascota = ?,detalle_cita = ?,observaciones = ? WHERE id_AgendarCita = ?";

        Date date = agregarCita.dateChooser_fechaCita.getDate();
        Timestamp timestamp = new Timestamp(date.getTime());

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, agendarCita.getHora());
            pst.setString(2, agendarCita.getTipo_sevicio());
            pst.setTimestamp(3, timestamp);
            pst.setString(4, agendarCita.getEstado());
            pst.setInt(5, agendarCita.getId_mascota());
            pst.setString(6, agendarCita.getDetalle_cita());
            pst.setString(7, agendarCita.getObservaciones());
            pst.setInt(8, agendarCita.getId_agendarCita());
            pst.execute();

            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar la cita" + e);
            return false;
        }
    }

    // Método para verificar si ya existe una cita en la misma fecha y hora
    private boolean existeCitaExistente(AgendarCita agendarCita) {
        String query = "SELECT COUNT(*) FROM agendarcita WHERE DATE(fecha) = ? AND hora = ?";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            Date date = agregarCita.dateChooser_fechaCita.getDate();
            Timestamp timestamp = new Timestamp(date.getTime());
            pst.setDate(1, new java.sql.Date(timestamp.getTime())); // Obtener solo la fecha
            pst.setString(2, agendarCita.getHora());

            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Si count > 0, ya existe una cita en la misma fecha y hora
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return false;
    }

    public int getIdMascota(String nombre) {
        int id = 0;
        String query = "SELECT id_mascota FROM mascota  WHERE nombre = ?";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, nombre);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_mascota");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return id;
    }

    //Listar citas
    public List<AgendarCita> listarCitas() {

        List<AgendarCita> list_citas = new ArrayList();

        String query = """
                       select ac.id_Agendarcita,ac.fecha,ac.hora,ac.estado,ac.tipo_servicio,ac.detalle_cita,ma.nombre,ma.sexo,ma.raza,an.nombre as nombreAnimal from agendarcita as ac
                       inner join mascota as ma on ac.id_mascota = ma.id_mascota
                       inner join animal as an on ma.id_animal = an.id_animal""";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                AgendarCita agendarCita = new AgendarCita();

                agendarCita.setId_agendarCita(rs.getInt(1));
                agendarCita.setFecha(rs.getDate(2));
                agendarCita.setHora(rs.getString(3));
                agendarCita.setEstado(rs.getString(4));
                agendarCita.setTipo_sevicio(rs.getString(5));
                agendarCita.setDetalle_cita(rs.getString(6));

                Mascota mascota = new Mascota();
                mascota.setNombre(rs.getString(7));
                mascota.setSexo(rs.getString(8));
                mascota.setRaza(rs.getString(9));
                agendarCita.setMascota(mascota);

                Animal animal = new Animal();
                animal.setNombre(rs.getString(10));
                agendarCita.setAnimal(animal);

                list_citas.add(agendarCita);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list_citas;
    }

    public List<AgendarCita> listarCitas(Date fecha) {

        List<AgendarCita> list_citas = new ArrayList();

        String query = """
        select ac.id_Agendarcita,ac.fecha,ac.hora,ac.estado,ac.tipo_servicio,ac.detalle_cita,ma.nombre,ma.sexo,ma.raza,an.nombre as nombreAnimal from agendarcita as ac
                               inner join mascota as ma on ac.id_mascota = ma.id_mascota
                               inner join animal as an on ma.id_animal = an.id_animal
        WHERE Date(ac.fecha) = ?;
        """;

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);

            // Configura el parámetro de fecha
            pst.setDate(1, new java.sql.Date(fecha.getTime()));

            rs = pst.executeQuery();

            while (rs.next()) {
                AgendarCita agendarCita = new AgendarCita();

                agendarCita.setId_agendarCita(rs.getInt(1));
                agendarCita.setFecha(rs.getDate(2));
                agendarCita.setHora(rs.getString(3));
                agendarCita.setEstado(rs.getString(4));
                agendarCita.setTipo_sevicio(rs.getString(5));
                agendarCita.setDetalle_cita(rs.getString(6));

                Mascota mascota = new Mascota();
                mascota.setNombre(rs.getString(7));
                mascota.setSexo(rs.getString(8));
                mascota.setRaza(rs.getString(9));
                agendarCita.setMascota(mascota);
                
                Animal animal = new Animal();
                animal.setNombre(rs.getString(10));
                agendarCita.setAnimal(animal);
                
                list_citas.add(agendarCita);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list_citas;
    }

}
