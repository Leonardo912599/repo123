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
import java.util.ArrayList;
import java.util.List;
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
            if (detalleServicio.getConsulta().getId_consulta() != null ) {
                pst.setInt(2, detalleServicio.getConsulta().getId_consulta());
            } else {
                pst.setNull(2, Types.INTEGER); // Establecer como NULL
            }

            // Verificar si id_vacunacion es null
            if (detalleServicio.getVacunacionDesparacitacion().getId_vacunacionDesparacitacion() != null) {
                pst.setInt(3, detalleServicio.getVacunacionDesparacitacion().getId_vacunacionDesparacitacion());
            } else {
                pst.setNull(3, Types.INTEGER); // Establecer como NULL
            }

            // Verificar si id_estetica es null
            if (detalleServicio.getEstetica().getId_estetica() != null) {
                pst.setInt(4, detalleServicio.getEstetica().getId_estetica());
            } else {
                pst.setNull(4, Types.INTEGER); // Establecer como NULL
            }

            pst.setInt(5, detalleServicio.getMascota().getId_mascota());
            pst.setInt(6, detalleServicio.getVeterinario().getId_veterinario());

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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

        return id_Veterinario;
    }
    public List<DetalleServicio> listarConsultas(int id) {

        List<DetalleServicio> list_consultas = new ArrayList<>();
        
        String query = """
                       SELECT con.id_consulta,an.nombre,ma.nombre,ma.raza,con.sintomas,con.observaciones,con.diagnostico,con.receta_medica,de.fecha,ve.nombre FROM consulta as con
                       inner join detalleservicio as de on con.id_consulta = de.id_consulta
                       inner join mascota as ma on de.id_mascota = ma.id_mascota
                       inner join animal as an on ma.id_animal = an.id_animal
                       inner join veterinario as ve on de.id_veterinario = ve.id_veterinario
                       where ma.id_mascota = ?""";
        
        try {
            conn = conexion.getConnection();
            pst=conn.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                
                Consulta consulta = new Consulta();
                
                consulta.setId_consulta(rs.getInt(1));
                consulta.setSintomas(rs.getString(5));
                consulta.setObservaciones(rs.getString(6));
                consulta.setDiagnostico(rs.getString(7));
                consulta.setReceta_medica(rs.getString(8));
                
                DetalleServicio detalleServicio = new DetalleServicio();
                detalleServicio.setConsulta(consulta);
                detalleServicio.setFecha(rs.getTime(9));
                
                Mascota mascota = new Mascota();
                mascota.setNombre(rs.getString(3));
                mascota.setRaza(rs.getString(4));
                detalleServicio.setMascota(mascota);
                
                Animal animal = new Animal();
                animal.setNombre(rs.getString(2));
                mascota.setAnimal(animal);
                
                Veterinario veterinario = new Veterinario();
                veterinario.setNombre(rs.getString(10));
                detalleServicio.setVeterinario(veterinario);
                
                list_consultas.add(detalleServicio);
            }
        } catch (Exception e) {
        }
        
      return list_consultas;
    }

}
