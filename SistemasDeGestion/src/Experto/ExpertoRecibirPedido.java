/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Agentes.AgenteDetallePedido;
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

    public void Recibido(DetallePedido detalle,int cant,String fecha) {
        boolean guardado;
        int pendientes = 0;
        Producto p = detalle.getProducto();
        int aux = p.getStock().getCantidad();
        p.getStock().setCantdidad(aux + cant);
        detalle.setBaja(1);
        //guardo el nuevo stock del producto y doy de baja el detalle, si es necesario el pedido lo paso a no pendiente
        guardado = fachada.guardar((ObjetoPersistente)p.getStock());
        if(guardado)
           guardado = fachada.guardar((ObjetoPersistente)detalle);
        if(guardado){
            //me fijo si tiene más productos por recibir
            AgenteDetallePedido adp = (AgenteDetallePedido) detalle;
            Pedido pedido = (Pedido) FachadaInterna.getInstancia().buscarOID(Pedido.class, adp.getOIDPedido());
            List<DetallePedido> detallePedido = pedido.getDetallePedido();
            for(int i=0;i<detallePedido.size();i++)
                pendientes = pendientes + 1;
            if(pendientes == 0){
                pedido.setPendiente(0);
                pedido.setFechaEntrega(fecha);
                guardado = fachada.guardar((ObjetoPersistente)pedido);
            }
                
        }
            
    }
    
}
