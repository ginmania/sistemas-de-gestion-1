/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Agentes.AgenteDetallePedido;
import Agentes.AgentePedido;
import Interfaces.DetallePedido;
import Interfaces.Pedido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class IntermediarioPedido extends IntermediarioRelacional {

    public IntermediarioPedido() {
        tabla = "Pedido";
        oid = "OIDPedido";
    }

    @Override
    public String generarSQLOID(String oid) {
        return "SELECT * FROM " + tabla + " WHERE OIDPedido= " + oid;

    }

    @Override
    public Registro convertirObjetoRegistro(ObjetoPersistente objeto) {
        Registro rs = new Registro();
        AgentePedido pedido = (AgentePedido) objeto;
        rs.addCampo(new Campo("OIDPedido", "'" + pedido.getoid() + "'"));
        rs.addCampo(new Campo("OIDProveedor", "'" + pedido.getOIDProveedor() + "'"));
        rs.addCampo(new Campo("fechaemision", "'" + pedido.getFechaEmision() + "'"));
        rs.addCampo(new Campo("fechaentrega", "'" + pedido.getFechaEntrega() + "'"));
        return rs;
    }

    @Override
    public ArrayList convertirRegistroObjeto(List<Registro> rs) {
        ArrayList<Pedido> pedido = new ArrayList<Pedido>();
        Agentes.AgentePedido temp = null;
        try {

            for (Registro registro : rs) {
                temp = new Agentes.AgentePedido();
                temp.setImpl(new Implementaciones.PedidoImpl());
                temp.setoid(registro.getCampo("OIDVenta").getValor());
                temp.setOIDProveedor(registro.getCampo("OIDProveedor").getValor());
                temp.setFechaEmision(registro.getCampo("fechaemision").getValor());
                temp.setFechaEntrega(registro.getCampo("fechaentrega").getValor());
                temp.setDetallePedidos(Fachada.getInstancia().buscar(DetallePedido.class, FabricaCriterio.getInstancia().crearCriterio("OIDPedido", "=", temp.getoid())));
                pedido.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Exploto el Intermediario de Pedidos");
            ex.printStackTrace();
        }
        return pedido;
    }

    @Override
    public boolean guardar(ObjetoPersistente objeto) {
        boolean guardado = true;
        if (super.guardar(objeto)) {
            for (DetallePedido detallepedidos : ((Pedido) objeto).getDetallePedido()) {
                ((AgenteDetallePedido) detallepedidos).setOIDPedido(objeto.getoid());
                if (!FachadaInterna.getInstancia().guardar((ObjetoPersistente) detallepedidos)) {
                    guardado = false;
                }
            }
        }
        return guardado;
    }
}