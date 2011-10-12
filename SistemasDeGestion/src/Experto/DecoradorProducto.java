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
 * @author diego
 */
public class DecoradorProducto extends ExpertoProducto{
    @Override
public List buscarProducto(String valorCodigo, String valorNombre) throws NoProductoExcepcion {
        FachadaInterna.getInstancia().iniciatTX();
        return super.buscarProducto(valorCodigo, valorNombre);
        }
    @Override
    public boolean confirmar(){
        boolean confirmado = super.confirmar();
        FachadaInterna.getInstancia().finalizarTX();
        return confirmado;
        }
}
