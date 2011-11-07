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
import java.util.ArrayList;
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

    private void buscarPedidosPendientes(){ //
        Criterio c1 = obFP.crearCriterio("pendiente", "=", 1);
        pedido = obFP.buscar(Pedido.class,c1);          
    }

    private void buscarProductos(){        
        //traigo el producto y su catalogo para cada producto con pedido pendiente
        for(int i = 0; i < pedido.size();i++){   
            Criterio c = obFP.crearCriterio("OIDPedido", "=", ((AgentePedido)pedido.get(i)).getoid());
            ArrayList<DetallePedido> dp = obFP.buscar(DetallePedido.class,c);
          for(int j=0;j<dp.size();j++){
            Criterio c1 = obFP.crearCriterio("OIDProducto", "=", ((AgenteDetallePedido)dp.get(j)).getOIDProducto());
            producto = obFP.buscar(Producto.class,c1);  
            catalogo = obFP.buscar(Catalogo.class, c1);
        }}
    }

    private void buscarProveedores(){
        Criterio c1 = obFP.crearCriterio("baja", "=", 0);
        proveedor = obFP.buscar(Proveedor.class,c1);         
    }

    private DefaultTableModel mostrarPorProveedor(){
        String s = new String();
        DefaultTableModel pedidos = new DefaultTableModel();
        String[] fila = null ;
        pedidos.addColumn("Proveedor");
        pedidos.addColumn("Producto");
        pedidos.addColumn("Cantidad");
        s = "Se realizarÃ¡n el siguiente pedido de productos: \n";
        for(int i = 0; i < proveedor.size(); i++){
            for(int j = 0; j < producto.size(); j++){
                if(((AgenteProveedor)proveedor.get(i)).getoid() == ((AgenteCatalogo)catalogo.get(j)).getOIDProveedor()){
                    s.concat("Para el Proveedor " + proveedor.get(i).getNombre() + " se deben pedir del producto "
                            + producto.get(i).getCodigoProducto() + " " + producto.get(i).getDescripcionProducto() +
                            " la cantidad " + String.valueOf(producto.get(i).getStock().getStockPendiente()));
                    fila[0]=proveedor.get(i).getNombre();
                    fila[1]=producto.get(i).getCodigoProducto() + " " + producto.get(i).getDescripcionProducto();
                    fila[2]=String.valueOf(producto.get(i).getStock().getStockPendiente());
                    pedidos.addRow(fila);
                }
                s =  s + "\n";
            }
        }
        System.out.println(s);
        return pedidos;
    }

}
