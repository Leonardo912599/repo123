/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelos;

import java.util.Date;


public class DetalleServicio {

   private int id_detalleServicio;
    private Date Fecha;
    private Integer id_consulta;  
    private Integer id_vacunacion;  
    private Integer id_estetica;  
    private int id_mascota;
    private int id_veterinario;

    public DetalleServicio() {
    }

    public DetalleServicio(int id_detalleServicio, Date Fecha, Integer id_consulta, Integer id_vacunacion, Integer id_estetica, int id_mascota, int id_veterinario) {
        this.id_detalleServicio = id_detalleServicio;
        this.Fecha = Fecha;
        this.id_consulta = id_consulta;
        this.id_vacunacion = id_vacunacion;
        this.id_estetica = id_estetica;
        this.id_mascota = id_mascota;
        this.id_veterinario = id_veterinario;
    }

    public int getId_detalleServicio() {
        return id_detalleServicio;
    }

    public void setId_detalleServicio(int id_detalleServicio) {
        this.id_detalleServicio = id_detalleServicio;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public Integer getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(Integer id_consulta) {
        this.id_consulta = id_consulta;
    }

    public Integer getId_vacunacion() {
        return id_vacunacion;
    }

    public void setId_vacunacion(Integer id_vacunacion) {
        this.id_vacunacion = id_vacunacion;
    }

    public Integer getId_estetica() {
        return id_estetica;
    }

    public void setId_estetica(Integer id_estetica) {
        this.id_estetica = id_estetica;
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public int getId_veterinario() {
        return id_veterinario;
    }

    public void setId_veterinario(int id_veterinario) {
        this.id_veterinario = id_veterinario;
    }

    
    
   
    
    
}
