/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Agentes.AgenteStock;
import Interfaces.Stock;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class IntermediarioStock extends IntermediarioRelacional {

    public IntermediarioStock() {
        tabla = "Stock";
        oid = "OIDStock";
    }

    @Override
    public String generarSQLOID(String oid) {
        return "SELECT * FROM " + "Stock" + " WHERE OIDStock= " + oid;
    }

    @Override
    public Registro convertirObjetoRegistro(ObjetoPersistente objeto) {
        Registro rs = new Registro();
        AgenteStock stock = (AgenteStock) objeto;
        rs.addCampo(new Campo("OIDStock", "'" + stock.getoid() + "'"));
        rs.addCampo(new Campo("cantidadminima", "'" + String.valueOf(stock.getCantidadMinima()) + "'"));
        rs.addCampo(new Campo("cantidad", "'" + String.valueOf(stock.getCantidad()) + "'"));
        rs.addCampo(new Campo("stockPendiente", "'" + String.valueOf(stock.getStockPendiente()) + "'"));
        return rs;

    }

    @Override
    public ArrayList convertirRegistroObjeto(List<Registro> rs) {
        ArrayList<Stock> stock = new ArrayList<Stock>();
        Agentes.AgenteStock temp = null;
        try {
            for (Registro registro : rs) {
                temp = new Agentes.AgenteStock();
                temp.setImpl(new Implementaciones.StockImpl());
                temp.setoid(registro.getCampo("OIDStock").getValor());
                temp.setCantdidadMinima((Integer) (registro.getCampo("cantidadminima").getValor(Integer.class)));
                temp.setCantdidad((Integer) (registro.getCampo("cantidad").getValor(Integer.class)));
                temp.setCantdidad((Integer) (registro.getCampo("stockPendiente").getValor(Integer.class)));
                stock.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Exploto el Intermediario Stock");
        }
        return stock;

    }
}
