/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private String database_name = "sistema_veterinaria";
    private String user = "root";
    private String password = "123456";
    private String url = "jdbc:mysql://localhost:3306/" + database_name;

    Connection conn = null;

    public Connection getConnection() {
        try {
            //Obtener valor del driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Obtener la conexion
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.err.println("Ha ocurrido un ClassNotFoundException:" + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Ha ocurrido un SQLException" + e.getMessage());
        }
        return conn;
    }

}
