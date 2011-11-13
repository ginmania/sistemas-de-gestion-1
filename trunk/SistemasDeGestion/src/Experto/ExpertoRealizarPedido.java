/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Agentes.AgenteCatalogo;
import Agentes.AgenteDetallePedido;
import Agentes.AgentePedido;
import Agentes.AgenteProducto;
import Agentes.AgenteProveedor;
import Interfaces.Catalogo;
import Interfaces.DetallePedido;
import Interfaces.Pedido;
import Interfaces.Producto;
import Interfaces.Proveedor;
import Persistencia.Criterio;
import Persistencia.Fachada;
import Persistencia.FachadaInterna;
import Persistencia.ObjetoPersistente;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author duende
 */
public class ExpertoRealizarPedido implements Experto{
    private ArrayList<Producto> producto;
    private ArrayList<Proveedor> proveedor;
    private ArrayList<Catalogo> catalogo;
    private Fachada obFP;
    private ArrayList<Pedido> pedido;
    private ArrayList<Object> objRta;
    private DefaultTableModel dp;

    public DefaultTableModel iniciar(){
        obFP = Fachada.getInstancia();
        dp = null;
        this.buscarPedidosPendientes();
        if(!pedido.isEmpty()){
            this.buscarProductos();
            this.buscarProveedores();
            dp = this.mostrarPorProveedor();
        }
        return dp;
    }

    private void buscarPedidosPendientes(){ 
        //trae los pedidos que se encuentran pendientes
        Criterio c1 = obFP.crearCriterio("pend", "=", 1);
        pedido = obFP.buscar(Pedido.class,c1);          
    }

    private void buscarProductos(){        
        //traigo el producto y su catalogo para cada producto con pedido pendiente
        for(int i = 0; i < pedido.size();i++){   
            Criterio c = obFP.crearCriterio("OIDPedido", "=", ((AgentePedido)pedido.get(i)).getoid());
           //busco los detalles de los pedidos pendientes
            ArrayList<DetallePedido> dp = obFP.buscar(DetallePedido.class,c);
          for(int j=0;j<dp.size();j++){
              //trae los productos incluido en cada pedido
            Criterio c1 = obFP.crearCriterio("OIDProducto", "=", ((AgenteDetallePedido)dp.get(j)).getOIDProducto());
            producto = obFP.buscar(Producto.class,c1);  
            catalogo = obFP.buscar(Catalogo.class, c1);
        }}
    }

    private void buscarProveedores(){
        //busco los proveedores que esten activos
        Criterio c1 = obFP.crearCriterio("baja", "=",0);
        proveedor = obFP.buscar(Proveedor.class,c1);         
    }

    private DefaultTableModel mostrarPorProveedor(){
        String s = new String();
        DefaultTableModel pedidos = new DefaultTableModel();
        String[] fila = new String[3];
        pedidos.addColumn("Proveedor");
        pedidos.addColumn("Producto");
        pedidos.addColumn("Cantidad");
        s = "Se debe recibir los siguientes pedidos de productos: \n";
        for(int i = 0; i < proveedor.size(); i++){
            for(int j = 0; j < producto.size(); j++){
                String idProvCatalogo = ((AgenteCatalogo)catalogo.get(j)).getOIDProveedor();
                String idProveedor = ((AgenteProveedor)proveedor.get(i)).getoid();
                if(idProveedor.equals(idProvCatalogo)){ //si el proveedor trae ese producto
                    s.concat("Para el Proveedor " + proveedor.get(i).getNombre() + " se deben pedir del producto "
                            + producto.get(j).getCodigoProducto() + " " + producto.get(j).getDescripcionProducto() +
                            " la cantidad " + String.valueOf(producto.get(j).getStock().getStockPendiente()));
                    fila[0]=proveedor.get(i).getNombre();
                    fila[1]=producto.get(j).getCodigoProducto() + " " + producto.get(j).getDescripcionProducto();
                    fila[2]=String.valueOf(producto.get(j).getStock().getStockPendiente());
                    pedidos.addRow(fila);
                }
                s =  s + "\n";
            }
        }
        System.out.println(s);
        return pedidos;
    }
    
    public boolean CrearPedidoPendiente(Date fecha,Proveedor prov, ArrayList<Producto> prod, Hashtable cantidad){
        Pedido pedido = (Pedido) FabricaEntidad.getInstancia().FabricarEntidad(Pedido.class);        
        AgenteProveedor aprov = (AgenteProveedor) prov;
        AgentePedido aped = (AgentePedido) pedido;
        //armo el pedido
        aped.setOIDProveedor(aprov.getoid());
        pedido.setProveedor(prov);
        pedido.setFechaEmision(fecha.toString());
        pedido.setPendiente(1);
        //armo los detalles
        for(int i=0; i< prod.size();i++){
            DetallePedido dpedido = (DetallePedido) FabricaEntidad.getInstancia().FabricarEntidad(DetallePedido.class);
            AgenteDetallePedido adp = (AgenteDetallePedido) dpedido;
            AgenteProducto aProd = (AgenteProducto) prod.get(i);
            adp.setOIDPedido(aped.getoid());
            adp.setOIDProducto(aProd.getoid());
            dpedido.setPedido(pedido);
            dpedido.setProducto(aProd);            
            int cant = (Integer) cantidad.get(adp.getProducto().getCodigoProducto());
            adp.setCantidad(cant);
            adp.setBaja(0);
            pedido.setDetallePedido(dpedido);
        }
        //el pedido se encarga de guardar los detalles
        obFP = Fachada.getInstancia();
        return obFP.guardar((ObjetoPersistente) pedido);
    }

}
