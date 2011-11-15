/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Persistencia.FachadaInterna;

/**
 *
 * @author diego
 */
public class DecoradorReportes extends ExpertoReportes {

    @Override
    public boolean iniciar() {
        FachadaInterna.getInstancia().iniciatTX();
        return super.iniciar();
    }

    @Override
    public boolean confirmar() {
        boolean confirmado = super.confirmar();
        FachadaInterna.getInstancia().finalizarTX();
        return confirmado;
    }
}
