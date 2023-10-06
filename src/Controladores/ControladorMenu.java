/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controladores;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vistas.VistaAgendarCita;
import vistas.VistaConsulta;
import vistas.VistaEstetica;
import vistas.VistaHistoriaClinica;
import vistas.VistaMenu;
import vistas.VistaRegistrarClienteyMascota;
import vistas.VistaReporte;
import vistas.VistaVacuna_Desparasitacion;
import vistas.VistaVeterinarios;


public class ControladorMenu implements  MouseListener{

    private VistaMenu vistaMenu;
    private VistaEstetica vistaEstetica;
    private VistaConsulta vistaConsulta;
    private VistaRegistrarClienteyMascota vrcm;
    private VistaVeterinarios vistaVeterinarios;
    private VistaHistoriaClinica vistaHistoriaClinica;
    private VistaVacuna_Desparasitacion vistaVacuna_Desparasitacion;
    private VistaAgendarCita vistaAgendarCita;
    private VistaReporte vistaReporte;

    public ControladorMenu(VistaMenu vistaMenu, VistaEstetica vistaEstetica, VistaConsulta vistaConsulta, VistaRegistrarClienteyMascota vrcm, VistaVeterinarios vistaVeterinarios, VistaHistoriaClinica vistaHistoriaClinica, 
            VistaVacuna_Desparasitacion vistaVacuna_Desparasitacion,VistaAgendarCita vistaAgendarCita,VistaReporte vistaReporte) {
        this.vistaMenu = vistaMenu;
        this.vistaEstetica = vistaEstetica;
        this.vistaConsulta = vistaConsulta;
        this.vrcm = vrcm;
        this.vistaReporte = vistaReporte;
        this.vistaAgendarCita = vistaAgendarCita;
        this.vistaVeterinarios = vistaVeterinarios;
        this.vistaHistoriaClinica = vistaHistoriaClinica;
        this.vistaVacuna_Desparasitacion = vistaVacuna_Desparasitacion;
        this.vistaMenu.panel_agendarCitas.addMouseListener(this);
        this.vistaMenu.panel_consultas.addMouseListener(this);
        this.vistaMenu.panel_estetica.addMouseListener(this);
        this.vistaMenu.panel_registrarMascota_Cliente.addMouseListener(this);
        this.vistaMenu.panel_reportes.addMouseListener(this);
        this.vistaMenu.panel_historiaClinica.addMouseListener(this);
        this.vistaMenu.panel_veterinarios.addMouseListener(this);
        this.vistaMenu.panel_vacunacion_desparacitacion.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       if(e.getSource() == vistaMenu.panel_agendarCitas){
           vistaAgendarCita.setVisible(true);
       }else if(e.getSource() == vistaMenu.panel_consultas){
           vistaConsulta.setVisible(true);
       }else if(e.getSource() == vistaMenu.panel_estetica){
           vistaEstetica.setVisible(true);
       }else if(e.getSource() == vistaMenu.panel_historiaClinica){
           vistaHistoriaClinica.setVisible(true);
       }else if(e.getSource() == vistaMenu.panel_registrarMascota_Cliente){
           vrcm.setVisible(true);
       }else if(e.getSource() == vistaMenu.panel_reportes){
           vistaReporte.setVisible(true);
       }else if(e.getSource() == vistaMenu.panel_vacunacion_desparacitacion){
           vistaVacuna_Desparasitacion.setVisible(true);
       }else if(e.getSource() == vistaMenu.panel_veterinarios){
           vistaVeterinarios.setVisible(true);
       }
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
     
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }
    
    
    
}
