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

public class HistoriaClinicaDao {

    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    //Registrar Historia clinica
    public boolean registrarHistoriaClinica(HistoriaClinica historiaClinica) {

        String query = "INSERT INTO historiaclinica(dieta,anamnesis,anormalidades,lista_problemas,pronostico,diagnostico,fecha_registro,id_mascota,id_detalleServicio,plan_terapeutico) "
                + "values(?,?,?,?,?,?,?,?,?,?)";

        Timestamp datetime = new Timestamp(new Date().getTime());

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, historiaClinica.getDieta());
            pst.setString(2, historiaClinica.getAnamnesis());
            pst.setString(3, historiaClinica.getAnormalidades());
            pst.setString(4, historiaClinica.getLista_problemas());
            pst.setString(5, historiaClinica.getPronostico());
            pst.setString(6, historiaClinica.getDiagnostico());
            pst.setTimestamp(7, datetime);
            pst.setInt(8, historiaClinica.getId_mascota());
            pst.setInt(9, historiaClinica.getId_detalleServicio());
            pst.setString(10, historiaClinica.getPlan_terapeutico());

            pst.execute();

            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al resgistrar la historia clinica" + e);
            return false;
        }
    }

    //Modificar Historia clinica
    public boolean modificarHistoriaClinica(HistoriaClinica historiaClinica) {

        String query = "UPDATE historiaclinica SET dieta = ?,anamnesis = ?,anormalidades = ?,lista_problemas = ?,pronostico = ?,diagnostico = ?,id_mascota = ?,id_detalleServicio = ?,plan_terapeutico = ? WHERE id_historiaClinica = ?;";

        Timestamp datetime = new Timestamp(new Date().getTime());

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, historiaClinica.getDieta());
            pst.setString(2, historiaClinica.getAnamnesis());
            pst.setString(3, historiaClinica.getAnormalidades());
            pst.setString(4, historiaClinica.getLista_problemas());
            pst.setString(5, historiaClinica.getPronostico());
            pst.setString(6, historiaClinica.getDiagnostico());
            pst.setInt(7, historiaClinica.getId_mascota());
            pst.setInt(8, historiaClinica.getId_detalleServicio());
            pst.setString(9, historiaClinica.getPlan_terapeutico());
            pst.setInt(10, historiaClinica.getId_historiaClinica());

            pst.execute();

            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar la historia clinica" + e);
            return false;
        }
    }

    //Obtener id detalleServicio
    public int detalleServicioId(int id_mascota) {
        int id = 0;
        String query = """
                       SELECT MAX(id_detalleServicio) AS id FROM detalleservicio as de
                       inner join mascota as ma on  de.id_mascota = ma.id_mascota
                       where ma.id_mascota= ? and de.id_consulta is not null;""";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, id_mascota);
            rs = pst.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    //Obtener id historiaClinica
    public int historiaClinicaId(int id_mascota) {
        int id = 0;
        String query = """
                       SELECT id_historiaClinica AS id FROM historiaclinica as cli
                       where id_mascota= ?;""";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, id_mascota);
            rs = pst.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public HistoriaClinica listarHistoriaClinica(int id_mascota) {
        
          HistoriaClinica hc = new HistoriaClinica();
        String query = """
                       select his.* from historiaclinica as his
                       where id_mascota = ?;""";

        try {
            conn = conexion.getConnection();
            pst.setInt(1, id_mascota);
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {

                hc.setAnamnesis(rs.getString("anamnesis"));
                hc.setAnormalidades(rs.getString("anormalidades"));
                hc.setDiagnostico(rs.getString("diagnostico"));
                hc.setDieta(rs.getString("dieta"));
                hc.setLista_problemas(rs.getString("lista_problemas"));
                hc.setPlan_terapeutico(rs.getString("plan_terapeutico"));
                hc.setPronostico(rs.getString("pronostico"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hc;
    }
}
