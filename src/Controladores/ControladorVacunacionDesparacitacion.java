/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelos.Consulta;
import modelos.ConsultaDao;
import modelos.DetalleServicio;
import modelos.DetalleServicioDao;
import modelos.Estetica;
import modelos.Mascota;
import modelos.MascotaDao;
import modelos.VacunacionDesparacitacion;
import modelos.VacunacionDesparacitacionDao;
import modelos.Veterinario;
import vistas.VistaBuscarMascota;
import vistas.VistaRegistrarClienteyMascota;
import vistas.VistaVacuna_Desparasitacion;

public class ControladorVacunacionDesparacitacion implements ActionListener {

    private VacunacionDesparacitacion vd;
    private VacunacionDesparacitacionDao vacunacionDesparacitacionDao;
    private DetalleServicio detalleServicio;
    private DetalleServicioDao detalleServicioDao;
    private VistaVacuna_Desparasitacion vistaVacuna_Desparasitacion;
    private ConsultaDao consultaDao;
    VistaBuscarMascota vistaBuscarMascota = new VistaBuscarMascota();
    VistaRegistrarClienteyMascota rdm = new VistaRegistrarClienteyMascota();
    MascotaDao mascotaDao = new MascotaDao(rdm);
    ControladorBuscarMascota cbm = new ControladorBuscarMascota(mascotaDao, vistaBuscarMascota);

    public ControladorVacunacionDesparacitacion(VacunacionDesparacitacion vd, VacunacionDesparacitacionDao vacunacionDesparacitacionDao, DetalleServicio detalleServicio, DetalleServicioDao detalleServicioDao, VistaVacuna_Desparasitacion vistaVacuna_Desparasitacion, ConsultaDao consultaDao) {
        this.vd = vd;
        this.vacunacionDesparacitacionDao = vacunacionDesparacitacionDao;
        this.detalleServicio = detalleServicio;
        this.detalleServicioDao = detalleServicioDao;
        this.consultaDao = consultaDao;
        this.vistaVacuna_Desparasitacion = vistaVacuna_Desparasitacion;
        this.vistaVacuna_Desparasitacion.button_guardar.addActionListener(this);
        this.vistaVacuna_Desparasitacion.button_cancelar.addActionListener(this);
        this.vacunacionDesparacitacionDao.llenarComboBoxVacuna(vistaVacuna_Desparasitacion.cmb_proximaVacuna);
        this.vacunacionDesparacitacionDao.llenarComboBoxVacuna(vistaVacuna_Desparasitacion.cmb_vacunaAplicar);
        this.consultaDao.llenarComboBoxVeterinario(vistaVacuna_Desparasitacion.cmb_veterinario);
        this.vistaVacuna_Desparasitacion.button_buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cbm.setResultadoSeleccionadoListener(new ControladorBuscarMascota.ResultadoSeleccionadoListener() {
                    public void resultadoSeleccionado(Object[] datosFila) {
                        // Llena los campos de la interfaz principal con los datos de la fila seleccionada
                        vistaVacuna_Desparasitacion.txt_idMascota.setText(datosFila[0].toString());
                        vistaVacuna_Desparasitacion.txt_nombre.setText(datosFila[1].toString());
                        vistaVacuna_Desparasitacion.txt_sexo.setText(datosFila[3].toString());
                        vistaVacuna_Desparasitacion.txt_tamano.setText(datosFila[4].toString());
                        vistaVacuna_Desparasitacion.txt_raza.setText(datosFila[6].toString());
                        vistaVacuna_Desparasitacion.txt_animal.setText(datosFila[2].toString());
                        vistaVacuna_Desparasitacion.txt_edad.setText(datosFila[7].toString());

                    }
                });
                vistaBuscarMascota.setVisible(true);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaVacuna_Desparasitacion.button_guardar) {
            if (vistaVacuna_Desparasitacion.txt_idMascota.getText().equals("")
                    || vistaVacuna_Desparasitacion.txt_total.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Los campo estan vacios");
            } else {

                boolean isSelectedVacuna = vistaVacuna_Desparasitacion.radioButton_vacuna.isSelected();
                boolean isSelectedDesparacitacion = vistaVacuna_Desparasitacion.radioButton_desparacitante.isSelected();

                vd.setVacunacion(isSelectedVacuna ? 1 : 0);
                vd.setDesparacitacion(isSelectedDesparacitacion ? 1 : 0);

                String proxima_vacuna = (String) vistaVacuna_Desparasitacion.cmb_proximaVacuna.getSelectedItem();

                if (!proxima_vacuna.isEmpty()) {
                    vd.setProxima_vacuna(proxima_vacuna);
                }

                String desparacitante = (String) vistaVacuna_Desparasitacion.cmb_desparacitante.getSelectedItem();

                if (!proxima_vacuna.isEmpty()) {
                    vd.setDesparacitante(desparacitante);
                }
                vd.setTotal(Double.parseDouble(vistaVacuna_Desparasitacion.txt_total.getText()));

                String vacuna_aplicar = vistaVacuna_Desparasitacion.cmb_vacunaAplicar.getSelectedItem().toString();

                int id_vacuna = vacunacionDesparacitacionDao.obtenerIdVacuna(vacuna_aplicar);
                vd.setId_vacuna(id_vacuna);

                if (vacunacionDesparacitacionDao.registrarVacunacionDesparacitacion(vd)) {
                    JOptionPane.showMessageDialog(null, "Se registro el servicio de vacunacion-desparacitacion con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar el servicio de vacunacion-desparacitacion");
                }
                int id_vacunacionDesparacitacion = vacunacionDesparacitacionDao.vacunacionId();
                int id_mascota = Integer.parseInt(vistaVacuna_Desparasitacion.txt_idMascota.getText());
                int id_veterinario = detalleServicioDao.obtenerIdVeterinario(vistaVacuna_Desparasitacion.cmb_veterinario.getSelectedItem().toString());

                Consulta consulta = new Consulta();
                consulta.setId_consulta(null);
                detalleServicio.setConsulta(consulta);

                Mascota mascota = new Mascota();
                mascota.setId_mascota(id_mascota);
                detalleServicio.setMascota(mascota);

                Veterinario veterinario = new Veterinario();
                veterinario.setId_veterinario(id_veterinario);
                detalleServicio.setVeterinario(veterinario);

                Estetica estetica = new Estetica();
                estetica.setId_estetica(null);
                detalleServicio.setEstetica(estetica);
                
                vd.setId_vacunacionDesparacitacion(id_vacunacionDesparacitacion);
                detalleServicio.setVacunacionDesparacitacion(vd);
               
                boolean registroExitoso = detalleServicioDao.registrarDetalleServicio(detalleServicio);
                if (registroExitoso) {
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                }

            }
        } else if (e.getSource() == vistaVacuna_Desparasitacion.button_cancelar) {
            limpiarCampos();
        }
    }

    private void limpiarCampos() {

        vistaVacuna_Desparasitacion.txt_edad.setText("");
        vistaVacuna_Desparasitacion.txt_animal.setText("");
        vistaVacuna_Desparasitacion.txt_idMascota.setText("");
        vistaVacuna_Desparasitacion.txt_nombre.setText("");
        vistaVacuna_Desparasitacion.txt_raza.setText("");
        vistaVacuna_Desparasitacion.txt_sexo.setText("");
        vistaVacuna_Desparasitacion.txt_tamano.setText("");
        vistaVacuna_Desparasitacion.txt_total.setText("");
        vistaVacuna_Desparasitacion.cmb_proximaVacuna.setSelectedIndex(0);
        vistaVacuna_Desparasitacion.cmb_desparacitante.setSelectedIndex(0);
        vistaVacuna_Desparasitacion.cmb_vacunaAplicar.setSelectedIndex(0);
        vistaVacuna_Desparasitacion.cmb_veterinario.setSelectedIndex(0);

    }

}
