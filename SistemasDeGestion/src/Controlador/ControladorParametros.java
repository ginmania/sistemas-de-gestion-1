/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Experto.ExpertoParametros;
import Experto.FabricaExperto;
import Interfaces.Parametros;
import Pantalla.PantallaParametros;
import Pantalla.PantallaPrincipal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class ControladorParametros {

    private ControladorPrincipal controladorPrincipal;
    private PantallaParametros pantallaParametros;
    private PantallaPrincipal pantallaPrincipal;
    public double alfa = 0.0;
    public double beta = 0.0;
    public double gama = 0.0;
    private String a;
    private ExpertoParametros expertoparametros;

    public ControladorParametros(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        expertoparametros = (ExpertoParametros) FabricaExperto.getInstancia().FabricarExperto("ExpertoParametros");
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
                    setBeta(Double.parseDouble(pantallaParametros.getCampoAlfa().getText()));
                    setGama(Double.parseDouble(pantallaParametros.getCampoGama().getText()));
                    guardarParametros(Double.parseDouble(pantallaParametros.getCampoAlfa().getText()), Double.parseDouble(pantallaParametros.getCampoAlfa().getText()), Double.parseDouble(pantallaParametros.getCampoGama().getText()));
                    pantallaParametros.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(ControladorParametros.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void agregarParametros(double a, double b, double g) {
        pantallaParametros.setTitle("Parametros");
         pantallaParametros.setVisible(true);
    }

    public void guardarParametros(double a, double b, double g) {
        expertoparametros.guardarParametros(a, b, g);
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

    public List<Parametros> buscarParametros() {
        List<Parametros> vectorParametros = new ArrayList<Parametros>();
        vectorParametros = expertoparametros.buscarParametros();
        alfa = vectorParametros.get(0).getAlfa();
        beta = vectorParametros.get(0).getBeta();
        gama = vectorParametros.get(0).getGama();
        return vectorParametros;
    }
}
