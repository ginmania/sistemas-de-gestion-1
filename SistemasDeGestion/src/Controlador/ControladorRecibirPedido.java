/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Experto.ExpertoRealizarPedido;
import Experto.ExpertoRecibirPedido;
import Experto.FabricaEntidad;
import Experto.FabricaExperto;
import Interfaces.DetallePedido;
import Interfaces.Pedido;
import Interfaces.Producto;
import Pantalla.PantallaRecibirPedido;
import Persistencia.Criterio;
import Persistencia.Fachada;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import javax.swing.JOptionPane;

/**
 *
 * @author duende
 */
public class ControladorRecibirPedido {
    private ControladorPrincipal principal;
    private PantallaRecibirPedido pantalla;
    private ExpertoRecibirPedido experto; 
    private ArrayList<Pedido> pendientes;
    private ArrayList<DetallePedido> detalles;
    private Pedido pedido;
    private DetallePedido detalle;
    private Fachada fachada;
    private Hashtable det;

    public ControladorRecibirPedido(ControladorPrincipal ctrl) {
        this.principal = ctrl;
        fachada = Fachada.getInstancia();
        pantalla = new PantallaRecibirPedido();
        iniciar();
        pantalla.setVisible(true);
        principal.add(pantalla);
        experto = (ExpertoRecibirPedido) FabricaExperto.getInstancia().FabricarExperto("ExpertoRecibirPedido");
        det = new Hashtable();
        detalle= (DetallePedido) FabricaEntidad.getInstancia().FabricarEntidad(DetallePedido.class);
        
        pantalla.getJcPedidoPendiente().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pedidoSeleccionado(pantalla.getJcPedidoPendiente().getSelectedIndex());
            }
        });
        
       /* pantalla.getJcProductosPedidos().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(pantalla.getJcProductosPedidos().getItemCount()!=0)
                    productoSeleccionado((String) pantalla.getJcProductosPedidos().getSelectedItem());
            }
        });*/
        
        pantalla.getJbAceptar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ActualizarPedido();
            }
        });
    }

    private void iniciar() {
        Criterio c1 = fachada.crearCriterio("pend", "=", 1);
        pendientes = fachada.buscar(Pedido.class, c1);   
        for(int i=0; i<pendientes.size();i++)
         pantalla.getJcPedidoPendiente().addItem(pendientes.get(i).getNroPedido());
        pantalla.getJcPedidoPendiente().setSelectedIndex(1);
    }
    
    private void pedidoSeleccionado(int p){
        pedido = pendientes.get(p);
        detalles = (ArrayList<DetallePedido>) pedido.getDetallePedido();
        pantalla.getJtProveedor().setText(pedido.getProveedor().getNombre());
        pantalla.getJcProductosPedidos().removeAllItems();
        for(int i=0;i<detalles.size();i++){
            if(detalles.get(i).getBaja() == 0){
                Producto auxP = (Producto) FabricaEntidad.getInstancia().FabricarEntidad(Producto.class);
                auxP = detalles.get(i).getProducto();
                pantalla.getJcProductosPedidos().addItem(auxP.getNombreProducto());
                det.put(auxP.getNombreProducto(),detalles.get(i));
            }
        }
       // pantalla.getJcProductosPedidos().setSelectedIndex(1);
    }
    
    private void productoSeleccionado(){     
        String prodsel= (String) pantalla.getJcProductosPedidos().getSelectedItem();
        detalle = (DetallePedido) det.get(prodsel);
        pantalla.getJlDescripcion().setText(detalle.getProducto().getDescripcionProducto());
    }
    
    private boolean ActualizarPedido(){
        productoSeleccionado();
        String cb = pantalla.getJtCantidadRecibida().getText();        
        int cantidad = 0;
        cantidad = Integer.valueOf(cb);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");            
        String fechaEntrega = formato.format(pantalla.getJdFechaEntrega().getDate());
        String fechaE = fechaEntrega;
        experto = (ExpertoRecibirPedido) FabricaExperto.getInstancia().FabricarExperto("ExpertoRecibirPedido");
        if(experto.Recibido(pedido,detalle,cantidad,fechaE)){
            JOptionPane.showMessageDialog(pantalla, "pedido actualizado", "Recibir Pedido", JOptionPane.INFORMATION_MESSAGE);;
        return true;}
        else{
            JOptionPane.showMessageDialog(pantalla, "pedido no actualizado", "Recibir Pedido", JOptionPane.ERROR_MESSAGE);;
        }
        return false;
    }
    
}
