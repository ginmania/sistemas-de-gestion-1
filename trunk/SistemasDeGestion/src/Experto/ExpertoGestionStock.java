/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;


import Agentes.AgenteProducto;
import Interfaces.Producto;
import Interfaces.Stock;
import Persistencia.Criterio;
import Persistencia.Fachada;
import Persistencia.FachadaInterna;
import Persistencia.ObjetoPersistente;
import java.util.ArrayList;

/**
 *
 * @author duende
 */
public class ExpertoGestionStock implements Experto{
    private Stock stock;
    
    public Stock buscarStock(Producto prod){
        AgenteProducto ap = (AgenteProducto) prod;
        /*int codigoProducto = prod.getCodigoProducto();        
        Criterio c1 = new Criterio();
        c1 = Fachada.getInstancia().crearCriterio("OIDStock", "=", ap.getOIDStock());*/
        stock = (Stock) FachadaInterna.getInstancia().buscarOID(Stock.class, ap.getOIDStock());
        return stock;
    }
    
    public void ActualizarStock(int nuevo, AgenteProducto prod){
        Criterio c1 = new Criterio();
        c1 = Fachada.getInstancia().crearCriterio("OIDStock", "=", prod.getStock());
        stock = (Stock) FachadaInterna.getInstancia().buscarOID(Stock.class, prod.getOIDStock());
        int cant = stock.getCantidad();
        stock.setCantidad(cant + nuevo);
        Fachada.getInstancia().guardar((ObjetoPersistente)stock);
    }
    
    public void DisminuirStock(int nuevo, AgenteProducto prod){
        Criterio c1 = new Criterio();
        c1 = Fachada.getInstancia().crearCriterio("OIDStock", "=", prod.getStock());
        stock = (Stock) FachadaInterna.getInstancia().buscarOID(Stock.class, prod.getOIDStock());
        int cant = stock.getCantidad();
        int nueva = cant - nuevo;
        if(nueva > 0)
            stock.setCantidad(nueva);
        else
            stock.setCantidad(0);
        Fachada.getInstancia().guardar((ObjetoPersistente)stock);
    }

    public void ActualizarStockPendiente(int nuevo, AgenteProducto prod){
        Criterio c1 = new Criterio();
        c1 = Fachada.getInstancia().crearCriterio("OIDStock", "=", prod.getStock());
        stock = (Stock) FachadaInterna.getInstancia().buscarOID(Stock.class, prod.getOIDStock());
        int cant = stock.getStockPendiente();
        stock.setStockPendiente(cant + nuevo);
        Fachada.getInstancia().guardar((ObjetoPersistente)stock);
    }
}
