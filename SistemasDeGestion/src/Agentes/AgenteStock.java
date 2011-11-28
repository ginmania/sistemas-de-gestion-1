/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import Implementaciones.StockImpl;
import Interfaces.Stock;
import Persistencia.ObjetoPersistente;

/**
 *
 * @author diego
 */
public class AgenteStock implements Stock, ObjetoPersistente {

    private String OIDStock;
    private StockImpl impl;
    
    public StockImpl getImpl() {
        return impl;
    }

    public void setImpl(StockImpl impl) {
        this.impl = impl;
    }

    public int getCantidadMinima() {
        return impl.getCantidadMinima();
    }

    public void setCantidadMinima(int cantidadminima) {
        this.impl.setCantidadMinima(cantidadminima);
    }

    public int getCantidad() {
        return impl.getCantidad();
    }

    public void setCantidad(int cantidad) {
        this.impl.setCantidad(cantidad);
    }

    public void setoid(String oid) {
        this.OIDStock = oid;
    }

    public String getoid() {
        return OIDStock;
    }

    public int getStockPendiente() {
        return impl.getStockPendiente();
    }

    public void setStockPendiente(int auxSP) {
        impl.setStockPendiente(auxSP);
    }
}
