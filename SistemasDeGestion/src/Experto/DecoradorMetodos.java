/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Experto;

import Excepciones.NoProductoExcepcion;
import Persistencia.FachadaInterna;
import java.util.List;

/**
 *
 * @author malayo
 */



/**
 *
 * @author diego
 */
public class DecoradorMetodos extends ExpertoMetodos{
    public List buscarProducto() throws NoProductoExcepcion {
        FachadaInterna.getInstancia().iniciatTX();
        return super.buscarProducto();
        }
    @Override
    public boolean confirmar(){
        boolean confirmado = super.confirmar();
        FachadaInterna.getInstancia().finalizarTX();
        return confirmado;
        }

}

