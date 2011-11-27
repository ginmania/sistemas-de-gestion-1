/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Persistencia.FachadaInterna;
import java.util.List;

/**
 *
 * @author diego
 */
public class DecoradorParametros extends ExpertoParametros {

    @Override
    public List buscarParametros() {
        FachadaInterna.getInstancia().iniciatTX();
        return super.buscarParametros();
    }

    @Override
    public boolean confirmar() {
        boolean confirmado = super.confirmar();
        FachadaInterna.getInstancia().finalizarTX();
        return confirmado;
    }
}
