/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import Implementaciones.ParametrosImpl;
import Interfaces.Parametros;
import Persistencia.ObjetoPersistente;

/**
 *
 * @author diego
 */
public class AgenteParametros implements Parametros, ObjetoPersistente {

    private String idParametros;
    private ParametrosImpl impl;

    public ParametrosImpl getImpl() {
        return impl;
    }

    public void setImpl(ParametrosImpl impl) {
        this.impl = impl;
    }

    public double getAlfa() {
        return impl.getAlfa();
    }

    public void setAlfa(double alfa) {
        this.impl.setAlfa(alfa);
    }

    public double getBeta() {
        return impl.getBeta();
    }

    public void setBeta(double beta) {
        this.impl.setBeta(beta);
    }

    public double getGama() {
        return impl.getGama();
    }

    public void setGama(double gama) {
        this.impl.setGama(gama);
    }

    public void setoid(String oid) {
        this.idParametros = oid;
    }

    public String getoid() {
        return idParametros;
    }
}
