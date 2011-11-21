/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Excepciones.NoClienteExcepcion;
import Experto.ExpertoReportes;
import Experto.FabricaExperto;
import Interfaces.Cliente;
import Interfaces.Pedido;
import Interfaces.Producto;
import Interfaces.Venta;
import Metodo.ArmarPDF;
import Pantalla.PantallaPrincipal;
import java.awt.Desktop;
import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class ControladorReportes {

    private ControladorPrincipal controladorPrincipal;
    private PantallaPrincipal pantallaPrincipal;
    private ArmarPDF pdf;
    private ExpertoReportes expertoReportes;
    private String nombreArchivo;

    public ControladorReportes(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        this.expertoReportes = (ExpertoReportes) FabricaExperto.getInstancia().FabricarExperto("ExpertoReportes");
    }

    public void generarReporte(String nombreReporte) throws NoClienteExcepcion {
        pdf = new ArmarPDF();
        List<Cliente> clientes = null;
        List<Pedido> pedidos = null;
        List<Producto> productos = null;
        //List<ABC> abc = null;
        List<Venta> ventas = null;
        if (nombreReporte.equalsIgnoreCase("Cliente")) {
            clientes = expertoReportes.buscarCliente();
            nombreArchivo = pdf.armarCliente(clientes);
            JOptionPane.showMessageDialog(pantallaPrincipal, "Reporte de Clientes creado satisfactoriamente", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            abrir(nombreArchivo);
        } else if (nombreReporte.equalsIgnoreCase("Pedido")) {
            pedidos = expertoReportes.buscarPedido();
            nombreArchivo = pdf.armarPedido(pedidos);
            JOptionPane.showMessageDialog(pantallaPrincipal, "Reporte de Pedidos creado satisfactoriamente", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            abrir(nombreArchivo);
        } else if (nombreReporte.equalsIgnoreCase("Producto")) {
            productos = expertoReportes.buscarProducto();
            nombreArchivo = pdf.armarProducto(productos);
            JOptionPane.showMessageDialog(pantallaPrincipal, "Reporte de Productos creado satisfactoriamente", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            abrir(nombreArchivo);
        } else if (nombreReporte.equalsIgnoreCase("Venta")) {
            ventas = expertoReportes.buscarVenta();
            nombreArchivo = pdf.armarVenta(ventas);
            JOptionPane.showMessageDialog(pantallaPrincipal, "Reporte de Ventas creado satisfactoriamente", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            abrir(nombreArchivo);
        }

    }

    private void abrir(String archivo) {
        try {
            File file = new File(archivo);
            Desktop.getDesktop().open(file);

        } catch (Exception e) {
        }
    }
}