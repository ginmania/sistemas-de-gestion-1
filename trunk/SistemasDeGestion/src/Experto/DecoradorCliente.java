/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Experto;

import Excepciones.NoClienteExcepcion;
import Persistencia.FachadaInterna;
import java.util.List;

/**
 *
 * @author diego
 */
public class DecoradorCliente extends ExpertoCliente{
    @Override
public List buscarCliente(String valornombre, String valordni) throws NoClienteExcepcion {
        FachadaInterna.getInstancia().iniciatTX();
        return super.buscarCliente(valornombre, valordni);
        }
    @Override
    public boolean confirmar(){
        boolean confirmado = super.confirmar();
        FachadaInterna.getInstancia().finalizarTX();
        return confirmado;
        }
}
