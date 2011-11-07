/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Agentes.AgenteDetallePedido;
import Interfaces.DetallePedido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class IntermediarioDetallePedido extends IntermediarioRelacional{
      public IntermediarioDetallePedido() {
        tabla = "DetallePedido";
        oid = "OIDDetallePedido";
    }

    @Override
    public String generarSQLOID(String oid) {
        return "SELECT * FROM " + tabla + " WHERE OIDDetalllePedido= " + oid;

    }

    @Override
    public Registro convertirObjetoRegistro(ObjetoPersistente objeto) {
        Registro rs = new Registro();
        AgenteDetallePedido detallepedido = (AgenteDetallePedido) objeto;
        rs.addCampo(new Campo("OIDDetallePedido", "'" + detallepedido.getoid() + "'"));
        rs.addCampo(new Campo("OIDProducto", "'" + detallepedido.getOIDProducto() + "'"));
        rs.addCampo(new Campo("OIDPedido", "'" + detallepedido.getOIDPedido() + "'"));
        rs.addCampo(new Campo("cantidad", String.valueOf(detallepedido.getCantidad())));
        rs.addCampo(new Campo("baja", "'" + String.valueOf(detallepedido.getBaja() + "'")));
        return rs;

    }

    @Override
    public ArrayList convertirRegistroObjeto(List<Registro> rs) {
        ArrayList<DetallePedido> detallepedido = new ArrayList<DetallePedido>();
        Agentes.AgenteDetallePedido temp = null;
        try {
            for (Registro registro : rs) {
                temp = new Agentes.AgenteDetallePedido();
                temp.setImpl(new Implementaciones.DetallePedidoImpl());
                temp.setoid(registro.getCampo("OIDDetallePedido").getValor());
                temp.setOIDProducto(registro.getCampo("OIDProducto").getValor());
                temp.setOIDPedido(registro.getCampo("OIDPedido").getValor());
                temp.setCantidad(Integer.parseInt(registro.getCampo("cantidad").getValor()));
                temp.setBaja(Integer.parseInt(registro.getCampo("baja").getValor()));
                detallepedido.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Exploto el Intermediario de DetallePedido");
            ex.printStackTrace();
        }

        return detallepedido;
    }

    
}
