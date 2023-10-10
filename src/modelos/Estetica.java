/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelos;

public class Estetica {

    private Integer id_estetica;
    private int bano;
    private int corte;
    private String observaciones;
    private double subtotal;
    private String descuento;
    private double total;
    private String descripcion;

    public Estetica() {
    }

    public Estetica(Integer id_estetica, int bano, int corte, String observaciones, double subtotal, String descuento, double total, String descripcion) {
        this.id_estetica = id_estetica;
        this.bano = bano;
        this.corte = corte;
        this.observaciones = observaciones;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.total = total;
        this.descripcion = descripcion;
    }

    public Integer getId_estetica() {
        return id_estetica;
    }

    public void setId_estetica(Integer id_estetica) {
        this.id_estetica = id_estetica;
    }

    public int getBano() {
        return bano;
    }

    public void setBano(int bano) {
        this.bano = bano;
    }

    public int getCorte() {
        return corte;
    }

    public void setCorte(int corte) {
        this.corte = corte;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
