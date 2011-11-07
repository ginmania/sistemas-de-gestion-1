/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import Implementaciones.PoliticaStockImpl;
import Implementaciones.ProductoImpl;
import Interfaces.PoliticaStock;
import Persistencia.ObjetoPersistente;


/**
 *
 * @author duende
 */
public class AgentePoliticaStock implements PoliticaStock,ObjetoPersistente{
    private String OIDPoliticaStock;
    private PoliticaStockImpl impl;

    public AgentePoliticaStock(){}

    public String getDescripcion() {
        return impl.getDescripcion();
    }

    public void setDescripcion(String descripcion) {
        impl.setDescripcion(descripcion);
    }

    public void setoid(String oid) {
        this.OIDPoliticaStock = oid;
    }

    public String getoid() {
        return OIDPoliticaStock;
    }

    public void setImpl(PoliticaStockImpl politicaImpl) {
        this.impl = politicaImpl;
    }
}
