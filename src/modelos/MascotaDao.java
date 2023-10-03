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
import javax.swing.JOptionPane;
import vistas.RegistrarDueñoyMascota;

public class MascotaDao {

    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    RegistrarDueñoyMascota rdm ;

    public MascotaDao(RegistrarDueñoyMascota rdm) {
        this.rdm = rdm;
    }
    
    
    //Registrar Mascota
    public boolean registrarMascota(Mascota mascota) {

        String query = "INSERT INTO mascota(nombre,edad,raza,color,tamano,especie,fecha_nacimiento,dni)  VALUES(?,?,?,?,?,?,?,?)";

        try {
            conn = conexion.getConnection();
            pst = conn.prepareStatement(query);

            pst.setString(1, mascota.getNombre());
            pst.setString(2, mascota.getEdad());
            pst.setString(3, mascota.getRaza());
            pst.setString(4, mascota.getColor());
            pst.setString(5, mascota.getTamano());
            pst.setString(6, mascota.getEspecie());
            
            Date date = rdm.dateChooser_FechaNacimiento.getDate();
            Timestamp timestamp = new Timestamp(date.getTime());
            
            pst.setTimestamp(7, timestamp);
            pst.setString(8, mascota.getDni_cliente());

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al resgistrar a la mascota" + e);
            return false;
        }
    }

}
