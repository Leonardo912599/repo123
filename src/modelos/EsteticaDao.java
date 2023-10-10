/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class EsteticaDao {

    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    //Registrar Estetica
    public boolean registrarEstetica(Estetica estetica) {

        String query = "INSERT INTO estetica(bano,corte,observaciones,subtotal,descuento,total,descripcion) values(?,?,?,?,?,?,?)";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, estetica.getBano());
            pst.setInt(2, estetica.getCorte());
            pst.setString(3, estetica.getObservaciones());
            pst.setDouble(4, estetica.getSubtotal());
            pst.setString(5, estetica.getDescuento());
            pst.setDouble(6, estetica.getTotal());
            pst.setString(7, estetica.getDescripcion());

            pst.execute();

            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al resgistrar el servicio de estetica" + e);
            return false;
        }
    }
    //Obtener id de estetica
    public int esteticaId() {
        int id = 0;
        String query = "SELECT MAX(id_estetica) AS id FROM estetica";

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
}
