/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Agentes.AgenteDetallePedido;
import Agentes.AgentePedido;
import Interfaces.DetallePedido;
import Interfaces.Pedido;
import Interfaces.Producto;
import Persistencia.Criterio;
import Persistencia.Fachada;
import Persistencia.FachadaInterna;
import Persistencia.ObjetoPersistente;
import java.util.List;

/**
 *
 * @author duende
 */
public class ExpertoRecibirPedido implements Experto{
    private Fachada fachada;

    public boolean Recibido(Pedido pedido,DetallePedido detalle,int cant,String fecha) {
        boolean guardado = false;
        int pendientes = 0;   
        fachada = Fachada.getInstancia();
        AgenteDetallePedido adp = (AgenteDetallePedido) detalle;        
        Producto p = (Producto) FabricaEntidad.getInstancia().FabricarEntidad(Producto.class);
        
        p = detalle.getProducto();
        int aux = p.getStock().getCantidad();
        p.getStock().setCantidad(aux + cant);
        detalle.setProducto(p);
        detalle.setPedido(pedido);
        if(detalle.getCantidad() > cant)
            detalle.setCantidad(detalle.getCantidad()-cant);
        detalle.setBaja(1);
        //guardo el nuevo stock del producto y doy de baja el detalle, si es necesario el pedido lo paso a no pendiente
        if(fachada.guardar((ObjetoPersistente)p.getStock()))
           guardado = fachada.guardar((ObjetoPersistente)detalle);
        if(guardado){
            //me fijo si tiene más productos por recibir            
            List<DetallePedido> detallePedido = pedido.getDetallePedido();
            for(int i=0;i<detallePedido.size();i++)
             if(detallePedido.get(i).getBaja() == 0)   
                pendientes = pendientes + 1;            
            }
                if(pendientes == 0){
                pedido.setPendiente(0);
                pedido.setFechaEntrega(fecha);
                pedido.setDetallePedido(detalle);
                guardado = fachada.guardar((ObjetoPersistente)pedido);
        }
        return guardado;    
    }
    
}
