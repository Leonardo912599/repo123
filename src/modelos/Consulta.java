/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelos;


public class Consulta {

    private int id_consulta;
    private String sintomas;
    private String observaciones;
    private String receta_medica;
    private String diagnostico;
    private double total;

    public Consulta() {
    }

    public Consulta(int id_consulta, String sintomas, String observaciones, String receta_medica, String diagnostico, double total) {
        this.id_consulta = id_consulta;
        this.sintomas = sintomas;
        this.observaciones = observaciones;
        this.receta_medica = receta_medica;
        this.diagnostico = diagnostico;
        this.total = total;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getReceta_medica() {
        return receta_medica;
    }

    public void setReceta_medica(String receta_medica) {
        this.receta_medica = receta_medica;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
