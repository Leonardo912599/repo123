/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelos.HistoriaClinica;
import modelos.HistoriaClinicaDao;
import vistas.VistaRegistrarHistoriaClinica;

public class ControladorRegistrarHistoriaClinica implements ActionListener {

    private HistoriaClinica historiaClinica;
    private HistoriaClinicaDao historiaClinicaDao;
    private VistaRegistrarHistoriaClinica vistaRegistrarHistoriaClinica;

    public ControladorRegistrarHistoriaClinica(HistoriaClinica historiaClinica, HistoriaClinicaDao historiaClinicaDao, VistaRegistrarHistoriaClinica vistaRegistrarHistoriaClinica) {
        this.historiaClinica = historiaClinica;
        this.historiaClinicaDao = historiaClinicaDao;
        this.vistaRegistrarHistoriaClinica = vistaRegistrarHistoriaClinica;
        this.vistaRegistrarHistoriaClinica.button_guardar.addActionListener(this);
        this.vistaRegistrarHistoriaClinica.button_modificar.addActionListener(this);
        this.vistaRegistrarHistoriaClinica.button_cancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaRegistrarHistoriaClinica.button_guardar) {

            String anamnesis = vistaRegistrarHistoriaClinica.txtArea_anamnesis.getText();
            historiaClinica.setAnamnesis(!anamnesis.isEmpty() ? anamnesis : null);

            String anormalidades = vistaRegistrarHistoriaClinica.txtArea_anormalidades.getText();
            historiaClinica.setAnormalidades(!anormalidades.isEmpty() ? anormalidades : null);

            String lista_problemas = vistaRegistrarHistoriaClinica.txtArea_listaProblemas.getText();
            historiaClinica.setLista_problemas(!lista_problemas.isEmpty() ? lista_problemas : null);

            String plan_terapeutico = vistaRegistrarHistoriaClinica.txtArea_planTerapeutico.getText();
            historiaClinica.setPlan_terapeutico(!plan_terapeutico.isEmpty() ? plan_terapeutico : null);

            String dieta = vistaRegistrarHistoriaClinica.txtArea_dieta.getText();
            historiaClinica.setDieta(!dieta.isEmpty() ? dieta : null);

            String pronostico = vistaRegistrarHistoriaClinica.txtArea_pronostico.getText();
            historiaClinica.setPronostico(!pronostico.isEmpty() ? pronostico : null);

            String diagnostico = vistaRegistrarHistoriaClinica.txtArea_diagnostico.getText();
            historiaClinica.setDiagnostico(!diagnostico.isEmpty() ? diagnostico : null);

            String id_mascotaString = vistaRegistrarHistoriaClinica.txt_idMascota.getText();

            if (!id_mascotaString.isEmpty()) {
                int id_mascota = Integer.parseInt(id_mascotaString);
                int id_detalleServicio = historiaClinicaDao.detalleServicioId(id_mascota);
                historiaClinica.setId_mascota(id_mascota);
                historiaClinica.setId_detalleServicio(id_detalleServicio);
            }

            if (historiaClinicaDao.registrarHistoriaClinica(historiaClinica)) {
                 limpiarCampos();
                JOptionPane.showMessageDialog(null, "La historia clinica se ha registrado con exito");
            } else {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar la historia clinica");
            }

        } else if (e.getSource() == vistaRegistrarHistoriaClinica.button_cancelar) {
            limpiarCampos();
        } else if (e.getSource() == vistaRegistrarHistoriaClinica.button_modificar) {

            String anamnesis = vistaRegistrarHistoriaClinica.txtArea_anamnesis.getText();
            historiaClinica.setAnamnesis(!anamnesis.isEmpty() ? anamnesis : null);

            String anormalidades = vistaRegistrarHistoriaClinica.txtArea_anormalidades.getText();
            historiaClinica.setAnormalidades(!anormalidades.isEmpty() ? anormalidades : null);

            String lista_problemas = vistaRegistrarHistoriaClinica.txtArea_listaProblemas.getText();
            historiaClinica.setLista_problemas(!lista_problemas.isEmpty() ? lista_problemas : null);

            String plan_terapeutico = vistaRegistrarHistoriaClinica.txtArea_planTerapeutico.getText();
            historiaClinica.setPlan_terapeutico(!plan_terapeutico.isEmpty() ? plan_terapeutico : null);

            String dieta = vistaRegistrarHistoriaClinica.txtArea_dieta.getText();
            historiaClinica.setDieta(!dieta.isEmpty() ? dieta : null);

            String pronostico = vistaRegistrarHistoriaClinica.txtArea_pronostico.getText();
            historiaClinica.setPronostico(!pronostico.isEmpty() ? pronostico : null);

            String diagnostico = vistaRegistrarHistoriaClinica.txtArea_diagnostico.getText();
            historiaClinica.setDiagnostico(!diagnostico.isEmpty() ? diagnostico : null);

            String id_mascotaString = vistaRegistrarHistoriaClinica.txt_idMascota.getText();

            if (!id_mascotaString.isEmpty()) {
                int id_mascota = Integer.parseInt(id_mascotaString);
                int id_detalleServicio = historiaClinicaDao.detalleServicioId(id_mascota);
                historiaClinica.setId_mascota(id_mascota);
                historiaClinica.setId_detalleServicio(id_detalleServicio);
                int id_historiaClinica = historiaClinicaDao.historiaClinicaId(id_mascota);

                historiaClinica.setId_historiaClinica(id_historiaClinica);
            }

            if (historiaClinicaDao.modificarHistoriaClinica(historiaClinica)) {
                  limpiarCampos();
                JOptionPane.showMessageDialog(null, "La historia clinica se ha modificado con exito");
            } else {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar la historia clinica");
            }
        }

    }

    private void limpiarCampos() {

        vistaRegistrarHistoriaClinica.txtArea_anamnesis.setText("");
        vistaRegistrarHistoriaClinica.txtArea_anormalidades.setText("");
        vistaRegistrarHistoriaClinica.txtArea_diagnostico.setText("");
        vistaRegistrarHistoriaClinica.txtArea_dieta.setText("");
        vistaRegistrarHistoriaClinica.txtArea_listaProblemas.setText("");
        vistaRegistrarHistoriaClinica.txtArea_planTerapeutico.setText("");
        vistaRegistrarHistoriaClinica.txtArea_pronostico.setText("");
        vistaRegistrarHistoriaClinica.txt_idMascota.setText("");
    }

}
