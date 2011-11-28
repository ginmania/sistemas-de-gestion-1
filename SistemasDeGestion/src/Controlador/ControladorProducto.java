/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Experto.ExpertoProducto;
import Experto.FabricaExperto;
import Pantalla.PantallaNuevoProducto;
import Pantalla.PantallaProducto;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Excepciones.NoProductoExcepcion;
import Experto.ExpertoProveedor;
import Interfaces.Producto;
import Interfaces.Proveedor;
import Pantalla.ModeloTablaProducto;
import java.awt.event.ActionEvent;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author duende
 */
public class ControladorProducto {

    private ControladorPrincipal controladorPrincipal;
    
    private ExpertoProducto expertoProducto;
    private ExpertoProveedor expProveedor;
    
    private PantallaProducto pantallaProducto;
    private PantallaNuevoProducto nuevoProducto;
    private static final Integer NUEVO = 0;
    private static final Integer ACTUALIZAR = 1;
    private static final Integer ACTUALIZAR2 = 2;
    private Integer actual;
    private Producto productoSeleccionado;
    private Hashtable proveedores;

    public ControladorProducto(ControladorPrincipal controladorPrincipal) throws PropertyVetoException {
        this.controladorPrincipal = controladorPrincipal;
        expertoProducto = (ExpertoProducto) FabricaExperto.getInstancia().FabricarExperto("ExpertoProducto");
        expProveedor = (ExpertoProveedor) FabricaExperto.getInstancia().FabricarExperto("ExpertoProveedor");
        pantallaProducto = new PantallaProducto();
        nuevoProducto = new PantallaNuevoProducto(null, true);
        proveedores = new Hashtable();
        nuevoProducto.setLocationRelativeTo(null);
        pantallaProducto.columnasize();
        pantallaProducto.getTablaProductos().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        
            public void valueChanged(ListSelectionEvent lse) {
                pantallaProducto.getModificarProducto().setEnabled(true);
                pantallaProducto.getEliminarProducto().setEnabled(true);
            }
        });

        /////////BOTON ELIMINAR PRODUCTO//////////
        pantallaProducto.getEliminarProducto().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actual = ACTUALIZAR2;
                productoSeleccionado = ((ModeloTablaProducto) pantallaProducto.getTablaProductos().getModel()).getProducto(pantallaProducto.getTablaProductos().getSelectedRow());
                registrarProducto();
            }
        });

        /////////BOTON MODIFICAR PRODUCTO//////////
        pantallaProducto.getModificarProducto().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actual = ACTUALIZAR;
                productoSeleccionado = ((ModeloTablaProducto) pantallaProducto.getTablaProductos().getModel()).getProducto(pantallaProducto.getTablaProductos().getSelectedRow());
                mostrarProducto(productoSeleccionado);
            }
        });
        /////////BOTON BUSCAR PRODUCTO//////////
        pantallaProducto.getBuscarProducto().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String codigo;
                String nombre;
                try {
                    codigo = pantallaProducto.getTextCodigoProducto();
                    nombre = pantallaProducto.getTextNombreProducto();
                    buscarProducto(codigo, nombre);
                } catch (NoProductoExcepcion ex) {
                    ex.toString();
                    Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        /////////BOTON NUEVO PRODUCTO//////////
        pantallaProducto.getNuevoProducto().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    actual = NUEVO;
                    nuevoProducto();
                } catch (Exception ex) {
                    Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        /////////BOTON CANCELAR NUEVO PRODUCTO//////////
        nuevoProducto.getCancelar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nuevoProducto.dispose();
            }
        });
        /////////BOTON NUEVO CLIENTE//////////
        nuevoProducto.getNuevo().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarProducto();
            }
        });
        //// habilita el resto de los campos cuando selecciono proveedor
        nuevoProducto.getCbProveedor().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nuevoProducto.getTxNombre().setEditable(true);
                nuevoProducto.getTxCodigo().setEditable(true);                
                nuevoProducto.getTxCantidad().setEditable(true);
               // nuevoProducto.getTxCantidadMinima().setEditable(true);
                nuevoProducto.getTxDescripcion().setEditable(true);
                nuevoProducto.getTxPrecioCompra().setEditable(true);
                nuevoProducto.getTxPrecioVenta().setEditable(true);
                nuevoProducto.getNuevo().setEnabled(true);
            }
        });
    }

    //inicializamos la pantalla para agregar productos
    public void nuevoProducto() {
        ArrayList<Proveedor> provs = new ArrayList();
        
        nuevoProducto.getTxCodigo().setText("");
        nuevoProducto.getTxNombre().setText("");
        nuevoProducto.getTxDescripcion().setText("");
        nuevoProducto.getTxPrecioCompra().setText("");
        nuevoProducto.getTxPrecioVenta().setText("");
       // nuevoProducto.getTxCantidadMinima().setText("");
        nuevoProducto.getTxCantidad().setText("");
        nuevoProducto.setTitle("NUEVO PRODUCTO");
        provs = ExpertoProveedor.ListarProveedor();
        for(int i=0;i<provs.size();i++){
            nuevoProducto.getCbProveedor().addItem(provs.get(i).getNombre());            
            proveedores.put(provs.get(i).getNombre(),provs.get(i));
        }
        nuevoProducto.getTxCodigo().setEditable(false);
        nuevoProducto.getTxNombre().setEditable(false);
        nuevoProducto.getTxCantidad().setEditable(false);
       // nuevoProducto.getTxCantidadMinima().setEditable(false);
        nuevoProducto.getTxDescripcion().setEditable(false);
        nuevoProducto.getTxPrecioCompra().setEditable(false);
        nuevoProducto.getTxPrecioVenta().setEditable(false);
        nuevoProducto.getNuevo().setEnabled(false);
        nuevoProducto.setVisible(true);
    }

    public void mostrarProducto(Producto producto) {
        nuevoProducto.getTxCodigo().setText(String.valueOf(producto.getCodigoProducto()));
        nuevoProducto.getTxNombre().setText(producto.getNombreProducto());
        nuevoProducto.getTxDescripcion().setText(producto.getDescripcionProducto());
        nuevoProducto.getTxPrecioCompra().setText(String.valueOf(producto.getPrecioCompra()));
        nuevoProducto.getTxPrecioVenta().setText(String.valueOf(producto.getPrecioVenta()));
       // nuevoProducto.getTxCantidadMinima().setText(String.valueOf(producto.getStock().getCantidadMinima()));
        nuevoProducto.getTxCantidad().setText(String.valueOf(producto.getStock().getCantidad()));
        nuevoProducto.getCbClasificacion().addItem(producto.getClasifABC());
        nuevoProducto.setTitle("MODIFICAR PRODUCTO");
        nuevoProducto.setVisible(true);
    }

    public void actualizarCampos() {
        productoSeleccionado.setCodigoProducto(Integer.parseInt(nuevoProducto.getTxCodigo().getText()));
        productoSeleccionado.setNombreProducto(nuevoProducto.getTxNombre().getText());
        productoSeleccionado.setDescripcionProducto(nuevoProducto.getTxDescripcion().getText());
        productoSeleccionado.setPrecioCompra(Double.parseDouble(nuevoProducto.getTxPrecioCompra().getText()));
        productoSeleccionado.setPrecioVenta(Double.parseDouble(nuevoProducto.getTxPrecioVenta().getText()));
       // productoSeleccionado.getStock().setCantdidadMinima(Integer.parseInt(nuevoProducto.getTxCantidadMinima().getText()));
        productoSeleccionado.getStock().setCantidad(Integer.parseInt(nuevoProducto.getTxCantidad().getText()));
        String ABC = nuevoProducto.getCbClasificacion().getSelectedItem().toString();
        productoSeleccionado.setClasifABC(ABC.charAt(0));
    }

    private void registrarProducto() {
        switch (actual) {
            case 0:
                if (insertarProducto(
                        Integer.parseInt(nuevoProducto.getTxCodigo().getText()), nuevoProducto.getTxNombre().getText(),
                        nuevoProducto.getTxDescripcion().getText(), Double.parseDouble(nuevoProducto.getTxPrecioCompra().getText()),
                        Double.parseDouble(nuevoProducto.getTxPrecioVenta().getText()), 0,
                        0, 
                        Integer.parseInt(nuevoProducto.getTxCantidad().getText()),
			nuevoProducto.getCbClasificacion().getSelectedItem().toString(),
                        (String) nuevoProducto.getCbProveedor().getSelectedItem()) == true) {
                    System.out.println("Inserto!!!!");
                    JOptionPane.showMessageDialog(pantallaProducto, "Se grabo el producto", "Nuevo Producto", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        buscarProducto("", "");
                    } catch (NoProductoExcepcion ex) {
                        Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case 1:
                actualizarCampos();
                if (expertoProducto.modificarProducto(productoSeleccionado)) {
                    System.out.println("modifico!!!!");
                    JOptionPane.showMessageDialog(pantallaProducto, "Se modifico el producto", "Modificar Producto", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        buscarProducto("", "");
                    } catch (NoProductoExcepcion ex) {
                        Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case 2:
                actualizarCampos();
                if (expertoProducto.eliminarProducto(productoSeleccionado)) {
                    System.out.println("elimino!!!!");
                    JOptionPane.showMessageDialog(pantallaProducto, "Se elimino el producto", "Eliminar producto", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        buscarProducto("", "");
                    } catch (NoProductoExcepcion ex) {
                        Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
        }
        nuevoProducto.dispose();
    }

    private boolean insertarProducto(int codigo, String nombre, String descripcion, double precioCompra, double precioVenta,
            int baja, int cantidadminima, int cantidad,String ABC, String prov) {
        boolean resultado = false;
        char c = ABC.charAt(0);
        baja = 0;
        Proveedor p = (Proveedor) proveedores.get(prov);
        try {
            resultado = expertoProducto.insertarProducto(codigo, nombre, descripcion, precioCompra, precioVenta, baja, cantidadminima, cantidad,c,p);
        } catch (NoProductoExcepcion ex) {
            Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("no fue guardado el producto");
        }
        return resultado;
    }

    private List<Producto> buscarProducto(String valorCodigo, String valorNombre) throws NoProductoExcepcion {
        List<Producto> vectorProductos = new ArrayList<Producto>();

        vectorProductos = expertoProducto.buscarProducto(valorCodigo, valorNombre);
        ModeloTablaProducto mtp = new ModeloTablaProducto();
        //mtp.addAll para agregar todos los productos y seguir sumando las busquedas
        //mtp.setProducto para agregar solamente un producto
        mtp.setProducto(vectorProductos);
        pantallaProducto.getTablaProductos().setModel(mtp);
        pantallaProducto.getModificarProducto().setEnabled(false);
        pantallaProducto.getEliminarProducto().setEnabled(false);
        return vectorProductos;
    }

    public void agregarProducto() {

        pantallaProducto.setVisible(true);
        controladorPrincipal.add(pantallaProducto);
    }
}
