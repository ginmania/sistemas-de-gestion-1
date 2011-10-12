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
import Interfaces.Producto;
import Pantalla.ModeloTablaProducto;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author matias
 */
public class ControladorProducto {

    private ControladorPrincipal controladorPrincipal;
    private ControladorMetodos controladormetodos;
    private ExpertoProducto expertoProducto;
    private PantallaProducto pantallaProducto;
    private PantallaNuevoProducto nuevoProducto;
    private static final Integer NUEVO = 0;
    private static final Integer ACTUALIZAR = 1;
    private static final Integer ACTUALIZAR2 = 2;
    private Integer actual;
    private Producto productoSeleccionado;

    public ControladorProducto(ControladorPrincipal controladorPrincipal) throws PropertyVetoException {
        this.controladorPrincipal = controladorPrincipal;
        expertoProducto = (ExpertoProducto) FabricaExperto.getInstancia().FabricarExperto("ExpertoProducto");
        pantallaProducto = new PantallaProducto();
        nuevoProducto = new PantallaNuevoProducto(null, true);
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
    }

    public void nuevoProducto() {
        nuevoProducto.getTxCodigo().setText("");
        nuevoProducto.getTxNombre().setText("");
        nuevoProducto.getTxDescripcion().setText("");
        nuevoProducto.getTxPrecioCompra().setText("");
        nuevoProducto.getTxPrecioVenta().setText("");
        nuevoProducto.getTxCantidadMinima().setText("");
        nuevoProducto.getTxCantidad().setText("");
        nuevoProducto.setTitle("NUEVO PRODUCTO");
        nuevoProducto.setVisible(true);

    }

    public void mostrarProducto(Producto producto) {
        nuevoProducto.getTxCodigo().setText(String.valueOf(producto.getCodigoProducto()));
        nuevoProducto.getTxNombre().setText(producto.getNombreProducto());
        nuevoProducto.getTxDescripcion().setText(producto.getDescripcionProducto());
        nuevoProducto.getTxPrecioCompra().setText(String.valueOf(producto.getPrecioCompra()));
        nuevoProducto.getTxPrecioVenta().setText(String.valueOf(producto.getPrecioVenta()));
        nuevoProducto.getTxCantidadMinima().setText(String.valueOf(producto.getStock().getCantidadMinima()));
        nuevoProducto.getTxCantidad().setText(String.valueOf(producto.getStock().getCantidad()));
        nuevoProducto.setTitle("MODIFICAR PRODUCTO");
        nuevoProducto.setVisible(true);
    }

    public void actualizarCampos() {
        productoSeleccionado.setCodigoProducto(Integer.parseInt(nuevoProducto.getTxCodigo().getText()));
        productoSeleccionado.setNombreProducto(nuevoProducto.getTxNombre().getText());
        productoSeleccionado.setDescripcionProducto(nuevoProducto.getTxDescripcion().getText());
        productoSeleccionado.setPrecioCompra(Double.parseDouble(nuevoProducto.getTxPrecioCompra().getText()));
        productoSeleccionado.setPrecioVenta(Double.parseDouble(nuevoProducto.getTxPrecioVenta().getText()));
        productoSeleccionado.getStock().setCantdidadMinima(Integer.parseInt(nuevoProducto.getTxCantidadMinima().getText()));
        productoSeleccionado.getStock().setCantdidad(Integer.parseInt(nuevoProducto.getTxCantidad().getText()));
    }

    private void registrarProducto() {
        switch (actual) {
            case 0:
                if (insertarProducto(
                        Integer.parseInt(nuevoProducto.getTxCodigo().getText()), nuevoProducto.getTxNombre().getText(),
                        nuevoProducto.getTxDescripcion().getText(), Double.parseDouble(nuevoProducto.getTxPrecioCompra().getText()),
                        Double.parseDouble(nuevoProducto.getTxPrecioVenta().getText()), 0,
                        Integer.parseInt(nuevoProducto.getTxCantidadMinima().getText()), Integer.parseInt(nuevoProducto.getTxCantidad().getText())) == true) {
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
            int baja, int cantidadminima, int cantidad) {
        boolean resultado = false;
        baja = 0;
        resultado = expertoProducto.insertarProducto(codigo, nombre, descripcion, precioCompra, precioVenta, baja, cantidadminima, cantidad);
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
