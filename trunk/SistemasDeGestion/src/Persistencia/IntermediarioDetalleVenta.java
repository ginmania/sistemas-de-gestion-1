/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Agentes.AgenteDetalleVenta;
import Interfaces.DetalleVenta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class IntermediarioDetalleVenta extends IntermediarioRelacional {

    public IntermediarioDetalleVenta() {
        tabla = "DetalleVenta";
        oid = "OIDDetalleVenta";
    }

    @Override
    public String generarSQLOID(String oid) {
        return "SELECT * FROM " + tabla + " WHERE OIDDetallleVenta= " + oid;

    }

    @Override
    public Registro convertirObjetoRegistro(ObjetoPersistente objeto) {
        Registro rs = new Registro();
        AgenteDetalleVenta detalleventa = (AgenteDetalleVenta) objeto;
        rs.addCampo(new Campo("OIDDetalleVenta", "'" + detalleventa.getoid() + "'"));
        rs.addCampo(new Campo("OIDProducto", "'" + detalleventa.getOIDProducto() + "'"));
        rs.addCampo(new Campo("OIDVenta", "'" + detalleventa.getOIDVenta() + "'"));
        rs.addCampo(new Campo("cantidad", String.valueOf(detalleventa.getCantidad())));
        rs.addCampo(new Campo("PrecioUnitario", Double.toString(detalleventa.getPrecioUnitario())));
        rs.addCampo(new Campo("Precio", Double.toString(detalleventa.getPrecio())));
        return rs;

    }

    @Override
    public ArrayList convertirRegistroObjeto(List<Registro> rs) {
        ArrayList<DetalleVenta> detalleventa = new ArrayList<DetalleVenta>();
        Agentes.AgenteDetalleVenta temp = null;
        try {
            for (Registro registro : rs) {
                temp = new Agentes.AgenteDetalleVenta();
                temp.setImpl(new Implementaciones.DetalleVentaImpl());
                temp.setoid(registro.getCampo("OIDDetalleVenta").getValor());
                temp.setOIDProducto(registro.getCampo("OIDProducto").getValor());
                temp.setOIDVenta(registro.getCampo("OIDVenta").getValor());
                temp.setCantidad(Integer.parseInt(registro.getCampo("cantidad").getValor()));
                double pu = Double.parseDouble(registro.getCampo("PrecioUnitario").getValor());
                temp.setPrecioUnitario(pu);
                double p = Double.parseDouble(registro.getCampo("Precio").getValor());
                temp.setPrecio(p);
                detalleventa.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Exploto el Intermediario de DetalleVenta");
            ex.printStackTrace();
        }

        return detalleventa;
    }
    
    
}