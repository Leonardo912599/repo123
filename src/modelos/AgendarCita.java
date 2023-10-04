/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelos;

import java.util.Date;


public class AgendarCita {
    
    private int id_agendarCita;
    private String hora;
    private String tipo_sevicio;
    Date fecha;
    private String estado;
    private int id_mascota;
    private String detalle_cita;
    private String observaciones;
    private Mascota mascota;
    private Animal animal;

    public AgendarCita() {
    }

    public AgendarCita(int id_agendarCita, String hora, String tipo_sevicio, Date fecha, String estado, int id_mascota, String detalle_cita, String observaciones, Mascota mascota, Animal animal) {
        this.id_agendarCita = id_agendarCita;
        this.hora = hora;
        this.tipo_sevicio = tipo_sevicio;
        this.fecha = fecha;
        this.estado = estado;
        this.id_mascota = id_mascota;
        this.detalle_cita = detalle_cita;
        this.observaciones = observaciones;
        this.mascota = mascota;
        this.animal = animal;
    }

    public int getId_agendarCita() {
        return id_agendarCita;
    }

    public void setId_agendarCita(int id_agendarCita) {
        this.id_agendarCita = id_agendarCita;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipo_sevicio() {
        return tipo_sevicio;
    }

    public void setTipo_sevicio(String tipo_sevicio) {
        this.tipo_sevicio = tipo_sevicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public String getDetalle_cita() {
        return detalle_cita;
    }

    public void setDetalle_cita(String detalle_cita) {
        this.detalle_cita = detalle_cita;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

}
