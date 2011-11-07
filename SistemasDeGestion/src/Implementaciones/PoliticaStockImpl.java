/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.PoliticaStock;

/**
 *
 * @author duende
 */
public class PoliticaStockImpl implements PoliticaStock{
    private String Descripcion;
    

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
    
}
