/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelos;



public class Cliente {

    private String dni;
    private String nombre;
    private String apellidos;
    private String celular;
    private String correo_electronico;

    public Cliente() {
    }

    
    
    public Cliente(String dni, String nombre, String apellidos, String celular, String correo_electronico) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.celular = celular;
        this.correo_electronico = correo_electronico;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    

    
    
}
