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
import java.util.ArrayList;
import java.util.Date;

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

    public ControladorRecibirPedido(ControladorPrincipal ctrl) {
        this.principal = ctrl;
        fachada = Fachada.getInstancia();
        pantalla = new PantallaRecibirPedido();
        iniciar();
        pantalla.setVisible(true);
        principal.add(pantalla);
        
        pantalla.getJcPedidoPendiente().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pedidoSeleccionado(pantalla.getJcPedidoPendiente().getSelectedIndex());
            }
        });
        
        pantalla.getJcProductosPedidos().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                productoSeleccionado((String) pantalla.getJcProductosPedidos().getSelectedItem());
            }
        });
        
        pantalla.getJbAceptar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IngresarCantidad();
            }
        });
    }

    private void iniciar() {
        Criterio c1 = fachada.crearCriterio("pendiente", "=", 1);
        pendientes = fachada.buscar(Pedido.class, c1);   
        for(int i=0; i<pendientes.size();i++)
         pantalla.getJcPedidoPendiente().addItem(pendientes.get(i).getNroPedido());
    }
    
    private void pedidoSeleccionado(int p){
        pedido = pendientes.get(p);
        detalles = (ArrayList<DetallePedido>) pedido.getDetallePedido();
        pantalla.getJtProveedor().setText(pedido.getProveedor().getNombre());
        for(int i=0;i<detalles.size();i++){
            if(detalles.get(i).getBaja() == 0){
                Producto auxP = (Producto) FabricaEntidad.getInstancia().FabricarEntidad(Producto.class);
                auxP = detalles.get(i).getProducto();
                pantalla.getJcProductosPedidos().addItem(auxP.getDescripcionProducto());
            }
        }
    
    }
    
    private void productoSeleccionado(String Producto){
        detalle= (DetallePedido) FabricaEntidad.getInstancia().FabricarEntidad(DetallePedido.class);
        for(int i=0;i<detalles.size();i++)
            if(detalles.get(i).getProducto().getNombreProducto().equals(Producto))
                detalle = detalles.get(i);
    }
    
    private boolean IngresarCantidad(){
        int cantidad = Integer.valueOf(pantalla.getJtProveedor().getText());
        Date fechaEntrega = pantalla.getJdFechaEntrega().getDate();
        String fechaE = fechaEntrega.toString();
        experto.Recibido(detalle,cantidad,fechaE);
        return true;
    }
    
}
