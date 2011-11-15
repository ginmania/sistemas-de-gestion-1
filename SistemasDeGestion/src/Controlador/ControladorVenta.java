/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Excepciones.NoClienteExcepcion;
import Excepciones.NoProductoExcepcion;
import Pantalla.PantallaVenta;
import Experto.ExpertoCliente;
import Experto.ExpertoProducto;
import Experto.ExpertoVentas;
import Experto.FabricaExperto;
import Interfaces.Cliente;
import Interfaces.DetalleVenta;
import Interfaces.Producto;
import Pantalla.ModeloTablaDetalleVenta;
import Pantalla.PantallaSeleccion;
import Pantalla.pantallaBuscarVta;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author duende
 */
public class ControladorVenta {

    private PantallaVenta pantallaVta;
    private PantallaSeleccion pantallaSel;
    private pantallaBuscarVta pantallaBus;
    private ControladorSeleccion ctrlSeleccion;
    private ControladorPrincipal ctrlPrincipal;
    private List<Cliente> clientes;
    private List<Producto> productos;
    final private ExpertoCliente expClientes;
    final private ExpertoProducto expProducto;
    // final private ExpertoGestionStock expStock;
    final private ExpertoVentas expVenta;
    private ArrayList<DetalleVenta> detalles;
    private DetalleVenta detalle;
    private ModeloTablaDetalleVenta tabla = new ModeloTablaDetalleVenta();
    private ModeloTablaVenta tablaV = new ModeloTablaVenta();
    private String seleccion;

    public ControladorVenta(ControladorPrincipal aThis) {
        this.ctrlPrincipal = aThis;
        this.expVenta = (ExpertoVentas) FabricaExperto.getInstancia().FabricarExperto("ExpertoVentas");
        this.pantallaVta = new PantallaVenta();
        this.expClientes = (ExpertoCliente) FabricaExperto.getInstancia().FabricarExperto("ExpertoCliente");
        this.expProducto = (ExpertoProducto) FabricaExperto.getInstancia().FabricarExperto("ExpertoProducto");
        //   this.expStock = (ExpertoGestionStock) FabricaExperto.getInstancia().FabricarExperto("ExpertoGestionStock");

        ctrlSeleccion = new ControladorSeleccion(ctrlPrincipal);
        pantallaSel = new PantallaSeleccion();
        pantallaBus = new pantallaBuscarVta();


        //SELECCIONAMOS UN cliente
        pantallaVta.getJbSelecCliente().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    seleccion = "Cliente";
                    clientes = expClientes.buscarCliente("", "");
                    pantallaSel = (PantallaSeleccion) ctrlSeleccion.IniciarPantallaSeleccion(clientes, "Cliente", "SELECT CLIENTE");
                } catch (NoClienteExcepcion ex) {
                    Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
                pantallaSel.getjBtnAceptar().addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try {
                            llenarFactura(seleccion);
                        } catch (NoProductoExcepcion ex) {
                            Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (PropertyVetoException ex) {
                            Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
            }
        ;

        });
        
        //SELECCIONAMOS UN PROVEEDOR
        pantallaVta.getJbSelecProducto().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    productos = expProducto.buscarProducto("", "");
                    seleccion = "Producto";
                    pantallaSel = (PantallaSeleccion) ctrlSeleccion.IniciarPantallaSeleccion(productos, "Producto", "SELECT PRODUCTO");
                } catch (NoProductoExcepcion ex) {
                    Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
                pantallaSel.getjBtnAceptar().addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try {
                            llenarFactura(seleccion);
                        } catch (NoProductoExcepcion ex) {
                            Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (PropertyVetoException ex) {
                            Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });

        pantallaVta.getJbConfirma().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String seleccion = pantallaVta.getJlCliente();
                Cliente cliente = ctrlSeleccion.recuperarCliente(seleccion);
                int nroFactura = Integer.parseInt(pantallaVta.getNroFactura().getText());
                String fch = pantallaVta.getFecha().getText();
                boolean GuardarVenta = expVenta.GuardarVenta(nroFactura, fch, cliente);
                if (GuardarVenta == true) {
                    System.out.println("Se genero la venta");
                    JOptionPane.showMessageDialog(pantallaVta, "Se registro la venta", "Nueva Venta", JOptionPane.INFORMATION_MESSAGE);
                    LimpiarPantalla();
                }
            }
        });
        
                pantallaVta.getJbConfirma().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String seleccion = pantallaVta.getJlCliente();
                Cliente cliente = ctrlSeleccion.recuperarCliente(seleccion);
                int nroFactura = Integer.parseInt(pantallaVta.getNroFactura().getText());
                String fch = pantallaVta.getFecha().getText();
                boolean GuardarVenta = expVenta.GuardarVenta(nroFactura, fch, cliente);
                if (GuardarVenta == true) {
                    System.out.println("Se genero la venta");
                    JOptionPane.showMessageDialog(pantallaVta, "Se registro la venta", "Nueva Venta", JOptionPane.INFORMATION_MESSAGE);
                    LimpiarPantalla();
                }
            }
        });

        //////Boton CANCELAR
        pantallaVta.getJbCancela().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                        pantallaVta.dispose();
                    } catch (Exception ex) {
                    Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        pantallaBus.getJbBuscar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                SimpleDateFormat formato = new SimpleDateFormat("DD/mm/yyyy");
                formato.applyPattern("dd/MM/yyyy");
                //Date init = pantallaBus.getDateFchDesde();
                String fechaD = "01/09/2011";
                //Date fin = pantallaBus.getDateFchHasta();
                String fechaH = "31/09/2011";
                List<DTO_Venta> v = expVenta.buscarVentas(fechaD, fechaH);
                for (int i = 0; i < v.size(); i++) {
                    tablaV.add(v.get(i));
                }
                pantallaBus.getTablaVentas().setModel(tablaV);
            }
        });

        pantallaBus.getTablaVentas().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent lse) {
                int s = pantallaBus.getTablaVentas().getSelectedRow();
                System.out.println("buscar nro venta de la fila");
                System.out.println(s);
                int nroVenta = (Integer) tablaV.getValueAt(s, 0);

                List<DTO_DetalleVenta> dtoDV = expVenta.buscarDetallesVenta(nroVenta);
                for (int i = 0; i < dtoDV.size(); i++) {
                    tabla.add(dtoDV.get(i));
                }
                pantallaBus.getTablaDetalles().removeAll();
                pantallaBus.getTablaDetalles().setModel(tabla);
            }
        });
    }

    private void llenarFactura(String tarea) throws NoProductoExcepcion, PropertyVetoException {
        if (tarea.equals("Producto")) { //genera un nuevo detalle
            String productoSeleccionado = pantallaSel.getSeleccionCombo();
            Producto combo = ctrlSeleccion.recuperarProducto(productoSeleccionado);
            double totalVta = 0;
            int cantidad = Integer.parseInt(pantallaSel.getCantidad());
            DTO_DetalleVenta linea = expVenta.nuevoDetalle(combo, cantidad);
            tabla.add(linea);
            pantallaVta.setJtDetalleVenta(tabla);
            totalVta = expVenta.CalcularTotal();
            pantallaVta.setJtTotal(String.valueOf(totalVta));
            pantallaVta.getJbConfirma().setVisible(true);
            //actualizacion de stock
            /*
            Stock st = expStock.buscarStock(productos.get(0));
            int cantAnt = st.getCantidad(); 
            int cantActual= cantAnt - cantidad;
            expStock.ActualizarStock(cantActual, productos.get(0));
             */

        } else if (tarea.equals("Cliente")) { //asigna un cliente a la factura
            pantallaVta.setJlCliente(pantallaSel.getSeleccionCombo());
            pantallaVta.getJbSelecProducto().setVisible(true);
        }
        pantallaSel.dispose();
        ctrlPrincipal.remove(pantallaSel);
        seleccion = "";
    }

    void AdministrarVentas() {
        inicializarPantalla();
        pantallaVta.setVisible(true);
        pantallaVta.setAlignmentX(25);
        pantallaVta.setAlignmentY(5);
        ctrlPrincipal.add(pantallaVta);
    }

    private void inicializarPantalla() {
        Date fechaHoy = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        pantallaVta.setNroFactura(String.valueOf(expVenta.NroFactura()));
        pantallaVta.setFecha(formato.format(fechaHoy));
        pantallaVta.getJbSelecProducto().setVisible(false);
        pantallaVta.getJbConfirma().setVisible(false);

    }

    private void LimpiarPantalla() {
        Date fechaHoy = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        pantallaVta.setNroFactura(String.valueOf(expVenta.NroFactura()));
        pantallaVta.setFecha(formato.format(fechaHoy));
        pantallaVta.getJbSelecProducto().setVisible(false);
        pantallaVta.getJbConfirma().setVisible(false);
        pantallaVta.setJlCliente("");
        pantallaVta.setJtTotal("0");
        pantallaVta.setJtDetalleVenta(new DefaultTableModel());

    }

    private void inicializarPantallaBusqueda() throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        /*pantallaBus.setFchDesde(formato.parse("09/01/2011"));
        pantallaBus.setFchHasta(formato.parse("09/31/2011"));
        pantallaBus.getFchDesde().setVisible(false);
        pantallaBus.getFchHasta().setVisible(false);*/
    }
}
