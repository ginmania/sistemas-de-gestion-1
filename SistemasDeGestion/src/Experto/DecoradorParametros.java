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
public class DecoradorParametros extends ExpertoParametros {

    public void estableceer() {
        FachadaInterna.getInstancia().iniciatTX();
        }
    @Override
    public boolean confirmar(){
        boolean confirmado = super.confirmar();
        FachadaInterna.getInstancia().finalizarTX();
        return confirmado;
        }
}
