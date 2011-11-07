/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Agentes.AgenteDetallePedido;
import Agentes.AgentePedido;
import Agentes.AgenteProducto;
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
        rs.addCampo(new Campo("pendiente", "'" + String.valueOf(pedido.getPendiente()) + "'"));
        rs.addCampo(new Campo("NroPedido", "'" + pedido.getNroPedido() + "'"));
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
                temp.setoid(registro.getCampo("OIDPedido").getValor());
                temp.setOIDProveedor(registro.getCampo("OIDProveedor").getValor());
                temp.setFechaEmision(registro.getCampo("fechaemision").getValor());
                temp.setFechaEntrega(registro.getCampo("fechaentrega").getValor());
                temp.setPendiente(Integer.parseInt(registro.getCampo("pendiente").getValor()));
                temp.setNroPedido(registro.getCampo("NroPedido").getValor());
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
            List<DetallePedido> detallespedido = ((Pedido) objeto).getDetallePedido();
            //para guardar el detalle necesito el OIDPedido y el OIDProducto
            for (int i=0; i<detallespedido.size();i++) {
                //asigno el OID relacionado del pedido
                String ped = objeto.getoid();
                AgenteDetallePedido detalle=(AgenteDetallePedido) detallespedido.get(i);
                detalle.setOIDPedido(ped);
                //asigno el OID relacionado del producto                
                AgenteProducto prod = (AgenteProducto)detalle.getProducto();
                detalle.setOIDProducto(prod.getoid());
                detalle.setCantidad(detallespedido.get(i).getCantidad());                
                //guardo un detalle
                boolean res = FachadaInterna.getInstancia().guardar((ObjetoPersistente) detalle);
                if (res == false) {
                    guardado = false;                    
                }
            }
        }
        return guardado;
    }
}