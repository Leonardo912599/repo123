/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelos;

import java.util.Date;


public class DetalleServicio {

    private int id_detalleServicio;
    private Date Fecha;
    private Consulta consulta;
    private VacunacionDesparacitacion vacunacionDesparacitacion;
    private Estetica estetica;  
    private Mascota mascota;
    private Veterinario veterinario;

    public DetalleServicio() {
    }

    public DetalleServicio(int id_detalleServicio, Date Fecha, Consulta consulta, VacunacionDesparacitacion vacunacionDesparacitacion, Estetica estetica, Mascota mascota, Veterinario veterinario) {
        this.id_detalleServicio = id_detalleServicio;
        this.Fecha = Fecha;
        this.consulta = consulta;
        this.vacunacionDesparacitacion = vacunacionDesparacitacion;
        this.estetica = estetica;
        this.mascota = mascota;
        this.veterinario = veterinario;
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

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public VacunacionDesparacitacion getVacunacionDesparacitacion() {
        return vacunacionDesparacitacion;
    }

    public void setVacunacionDesparacitacion(VacunacionDesparacitacion vacunacionDesparacitacion) {
        this.vacunacionDesparacitacion = vacunacionDesparacitacion;
    }

    public Estetica getEstetica() {
        return estetica;
    }

    public void setEstetica(Estetica estetica) {
        this.estetica = estetica;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

}
