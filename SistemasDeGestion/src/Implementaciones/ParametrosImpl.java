/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.Parametros;

/**
 *
 * @author diego
 */
public class ParametrosImpl implements Parametros {

    private double alfa;
    private double beta;
    private double gama;

    public ParametrosImpl() {
    }

    public ParametrosImpl(ParametrosImpl parametros) {
        this.alfa = parametros.getAlfa();
        this.beta = parametros.getBeta();
        this.gama = parametros.getGama();
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
