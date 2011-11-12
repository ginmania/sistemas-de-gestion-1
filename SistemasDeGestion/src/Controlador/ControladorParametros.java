/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Experto.ExpertoParametros;
import Experto.FabricaExperto;
import Pantalla.PantallaParametros;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class ControladorParametros {

    private ExpertoParametros expertoParametros;
    private ControladorPrincipal controladorPrincipal;
    private PantallaParametros pantallaParametros;
    private double alfa = 0.0;
    private double beta = 0.0;
    private double gama = 0.0;
    private String a;

    public ControladorParametros(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        expertoParametros = (ExpertoParametros) FabricaExperto.getInstancia().FabricarExperto("ExpertoParametros");
        pantallaParametros = new PantallaParametros(null, true);
        pantallaParametros.setLocationRelativeTo(null);


        pantallaParametros.getBotonCancelarParametros().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    pantallaParametros.dispose();

                } catch (Exception ex) {
                    Logger.getLogger(ControladorParametros.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        pantallaParametros.getBotonGuardarParametros().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    setAlfa(Double.parseDouble(pantallaParametros.getCampoAlfa().getText()));
                    setBeta(Double.parseDouble(pantallaParametros.getCampoBeta().getText()));
                    setGama(Double.parseDouble(pantallaParametros.getCampoGama().getText()));
                    System.out.println("Alfa: " + getAlfa() + "Beta: " + getBeta() + "Gama: " + getGama());
                    pantallaParametros.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(ControladorParametros.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        ///FOCO PARA EL BETA EN EL METODO TENDENCIA ////////
        pantallaParametros.getCampoBeta().addFocusListener(new java.awt.event.FocusAdapter() {

            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                a = pantallaParametros.getCampoAlfa().getText();
                alfa = Double.parseDouble(a);
                beta = alfa / 2;
                pantallaParametros.getCampoBeta().setText(String.valueOf(beta));

            }
        });


        ///FOCO PARA EL GAMMA EN EL METODO ESTACIONALIDAD ////////
        pantallaParametros.getCampoGama().addFocusListener(new java.awt.event.FocusAdapter() {

            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                a = pantallaParametros.getCampoAlfa().getText();
                alfa = Double.parseDouble(a);
                gama = alfa * 2;
                pantallaParametros.getCampoGama().setText(String.valueOf(gama));

            }
        });
    }

    public void agregarParametros() {
        pantallaParametros.setTitle("Parametros");
        pantallaParametros.setVisible(true);
    }

    public double getAlfa() {
        return alfa;
    }

    public void setAlfa(double alfa) {
        this.alfa = alfa;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getGama() {
        return gama;
    }

    public void setGama(double gama) {
        this.gama = gama;
    }
}
