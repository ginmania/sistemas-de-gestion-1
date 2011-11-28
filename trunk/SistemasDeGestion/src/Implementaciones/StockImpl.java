/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.Stock;

/**
 *
 * @author diego
 */
public class StockImpl implements Stock {

    private int cantidadminima;
    private int cantidad;
    private int stockPendiente;

    public StockImpl() {
    }

    public StockImpl(StockImpl stock) {
        this.cantidadminima = stock.getCantidadMinima();
        this.cantidad = stock.getCantidad();
    }

    public int getCantidadMinima() {
        return cantidadminima;
    }

    public void setCantidadMinima(int cantidadminima) {
        this.cantidadminima = cantidadminima;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getStockPendiente() {
        return this.stockPendiente;
    }

    public void setStockPendiente(int auxSP) {
        this.stockPendiente = auxSP;
    }
}
