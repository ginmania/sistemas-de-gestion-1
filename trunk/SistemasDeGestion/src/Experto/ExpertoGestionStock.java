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
        int codigoProducto = prod.getCodigoProducto();        
        Criterio c1 = new Criterio();
        c1 = Fachada.getInstancia().crearCriterio("CodigoProducto", "=", prod.getCodigoProducto());
        stock = (Stock) Fachada.getInstancia().buscar(Stock.class, c1);
        return stock;
    }
    
    public void ActualizarStock(int nuevo, AgenteProducto prod){
        Criterio c1 = new Criterio();
        c1 = Fachada.getInstancia().crearCriterio("OIDStock", "=", prod.getStock());
        stock = (Stock) FachadaInterna.getInstancia().buscarOID(Stock.class, prod.getOIDStock());
        stock.setCantdidad(nuevo);
        Fachada.getInstancia().guardar((ObjetoPersistente)stock);
    }
}
