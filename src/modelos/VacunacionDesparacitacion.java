/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelos;


public class VacunacionDesparacitacion {

    private Integer id_vacunacionDesparacitacion;
    private int vacunacion;
    private int desparacitacion;
    private String proxima_vacuna;
    private String desparacitante;
    private double total;
    private int id_vacuna;

    public VacunacionDesparacitacion() {
    }

    public VacunacionDesparacitacion(Integer id_vacunacionDesparacitacion, int vacunacion, int desparacitacion, String proxima_vacuna, String desparacitante, double total, int id_vacuna) {
        this.id_vacunacionDesparacitacion = id_vacunacionDesparacitacion;
        this.vacunacion = vacunacion;
        this.desparacitacion = desparacitacion;
        this.proxima_vacuna = proxima_vacuna;
        this.desparacitante = desparacitante;
        this.total = total;
        this.id_vacuna = id_vacuna;
    }

    public Integer getId_vacunacionDesparacitacion() {
        return id_vacunacionDesparacitacion;
    }

    public void setId_vacunacionDesparacitacion(Integer id_vacunacionDesparacitacion) {
        this.id_vacunacionDesparacitacion = id_vacunacionDesparacitacion;
    }

    public int getVacunacion() {
        return vacunacion;
    }

    public void setVacunacion(int vacunacion) {
        this.vacunacion = vacunacion;
    }

    public int getDesparacitacion() {
        return desparacitacion;
    }

    public void setDesparacitacion(int desparacitacion) {
        this.desparacitacion = desparacitacion;
    }

    public String getProxima_vacuna() {
        return proxima_vacuna;
    }

    public void setProxima_vacuna(String proxima_vacuna) {
        this.proxima_vacuna = proxima_vacuna;
    }

    public String getDesparacitante() {
        return desparacitante;
    }

    public void setDesparacitante(String desparacitante) {
        this.desparacitante = desparacitante;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getId_vacuna() {
        return id_vacuna;
    }

    public void setId_vacuna(int id_vacuna) {
        this.id_vacuna = id_vacuna;
    }

    
    
}
