/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelos;


public class HistoriaClinica {

    private int id_historiaClinica;
    private String dieta;
    private String anamnesis;
    private String anormalidades;
    private String lista_problemas;
    private String pronostico;
    private String diagnostico;
    private String plan_terapeutico;
    private String fecha_registro;
    private int id_mascota;
    private int id_detalleServicio;

    public HistoriaClinica() {
    }

    
    
    public HistoriaClinica(int id_historiaClinica, String dieta, String anamnesis, String anormalidades, String lista_problemas, String pronostico, String diagnostico, String plan_terapeutico, String fecha_registro, int id_mascota, int id_detalleServicio) {
        this.id_historiaClinica = id_historiaClinica;
        this.dieta = dieta;
        this.anamnesis = anamnesis;
        this.anormalidades = anormalidades;
        this.lista_problemas = lista_problemas;
        this.pronostico = pronostico;
        this.diagnostico = diagnostico;
        this.plan_terapeutico = plan_terapeutico;
        this.fecha_registro = fecha_registro;
        this.id_mascota = id_mascota;
        this.id_detalleServicio = id_detalleServicio;
    }

    public int getId_historiaClinica() {
        return id_historiaClinica;
    }

    public void setId_historiaClinica(int id_historiaClinica) {
        this.id_historiaClinica = id_historiaClinica;
    }

    public String getDieta() {
        return dieta;
    }

    public void setDieta(String dieta) {
        this.dieta = dieta;
    }

    public String getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(String anamnesis) {
        this.anamnesis = anamnesis;
    }

    public String getAnormalidades() {
        return anormalidades;
    }

    public void setAnormalidades(String anormalidades) {
        this.anormalidades = anormalidades;
    }

    public String getLista_problemas() {
        return lista_problemas;
    }

    public void setLista_problemas(String lista_problemas) {
        this.lista_problemas = lista_problemas;
    }

    public String getPronostico() {
        return pronostico;
    }

    public void setPronostico(String pronostico) {
        this.pronostico = pronostico;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getPlan_terapeutico() {
        return plan_terapeutico;
    }

    public void setPlan_terapeutico(String plan_terapeutico) {
        this.plan_terapeutico = plan_terapeutico;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public int getId_detalleServicio() {
        return id_detalleServicio;
    }

    public void setId_detalleServicio(int id_detalleServicio) {
        this.id_detalleServicio = id_detalleServicio;
    }
    
    
    
    
}
