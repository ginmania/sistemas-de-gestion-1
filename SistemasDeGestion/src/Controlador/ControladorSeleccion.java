/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Pantalla.ModeloTablaDetalleVenta;
import Pantalla.PantallaSeleccion;
import Controlador.ControladorPrincipal;
import Interfaces.Cliente;
import Interfaces.Producto;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author duende
 */
public class ControladorSeleccion {
    private PantallaSeleccion pantalla = new PantallaSeleccion();
    private ControladorVenta ctrlVta;
    private ControladorPrincipal CtrlPrincipal;
    private ControladorProducto ctrlProducto;
    private ArrayList<Producto> prod;
    private Hashtable clientes;
    private Hashtable productos;
    
    public ControladorSeleccion(ControladorPrincipal principal) {        
        this.CtrlPrincipal = principal;
        this.clientes = new Hashtable();
        this.productos = new Hashtable();
    } 
    
       
    public JInternalFrame IniciarPantallaSeleccion (Object combo, String msj, String tarea){
        pantalla.getJlCombo().removeAllItems();
        pantalla.setJtCantidad(0);
        if (tarea.equalsIgnoreCase("SELECT PRODUCTO")){
            prod = (ArrayList<Producto>) combo;            
            String producto;            
            for (int i=0;i< prod.size();i++){
                producto = prod.get(i).getCodigoProducto()+"-"+prod.get(i).getNombreProducto();
                pantalla.getJlCombo().addItem(producto);      
                productos.put(producto,prod.get(i));
            }
            pantalla.setJlEtiqueta("Productos");            
            pantalla.setTitle(msj);      
            pantalla.getjLabelCantidad().setVisible(true);
            pantalla.getJtCantidad().setVisible(true);
        }else if(tarea.equalsIgnoreCase("SELECT CLIENTE")){
            ArrayList<Cliente> cli = (ArrayList<Cliente>) combo;            
            String cliente;            
            for (int i=0;i< cli.size();i++){
                cliente = cli.get(i).getdni()+"-"+cli.get(i).getNombre()+cli.get(i).getApellido();
                pantalla.getJlCombo().addItem(cliente);
                clientes.put(cliente, cli.get(i));
            }
            pantalla.setJlEtiqueta("Cliente");            
            pantalla.setTitle(msj);
            pantalla.getjLabelCantidad().setVisible(false);
            pantalla.getJtCantidad().setVisible(false);
        } 
        pantalla.setVisible(true);
        pantalla.transferFocus();
        pantalla.toFront();
        CtrlPrincipal.add(pantalla);      
        return pantalla;
    }

    public Cliente recuperarCliente (Object sel){
       Cliente clis = (Cliente) clientes.get(sel);
       return clis;
    }
    
    public Producto recuperarProducto (Object sel){
       Producto P = (Producto) productos.get(sel);
       return P;
    }
    
    
}
