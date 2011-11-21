package Metodo;

import Interfaces.Cliente;
import Interfaces.Pedido;
import Interfaces.Producto;
import Interfaces.Venta;
import Persistencia.ConvertirFechas;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author diego
 */
public class ArmarPDF {

    private String nombreArchivo;
    private String mensaje;
    private String cabecera;
    private String fecha;

    public String armarCliente(List<Cliente> cliente) {
        Date fechasistema = new Date();
        fecha = ConvertirFechas.fechaAString(fechasistema);
        mensaje = "";
        cabecera = "";
        nombreArchivo = "";
        nombreArchivo = fecha + "Clientes.pdf";
        cabecera = "                                             LA CABAÑA CHOCOLATES S.A. \n";
        cabecera += "                                                Reporte de clientes " + fecha + "\n\n";
        cabecera += "____________________________________________________________________________" + "\n\n";
        for (Cliente clientes : cliente) {
            mensaje += "Apellido: " + clientes.getApellido() + "  Nombre: " + clientes.getNombre() + "  Celular: " + clientes.getCelular() + "  Direccion: "
                    + clientes.getDireccion() + "  email: " + clientes.getemail() + "\n\n";
        }
        generar(nombreArchivo);
        return nombreArchivo;
    }

    public String armarPedido(List<Pedido> pedido) {
        Date fechasistema = new Date();
        fecha = ConvertirFechas.fechaAString(fechasistema);
        mensaje = "";
        cabecera = "";
        nombreArchivo = "";
        nombreArchivo = fecha + "Pedidos.pdf";
        cabecera = "                                             LA CABAÑA CHOCOLATES S.A. \n";
        cabecera += "                                                Reporte de pedidos " + fecha + "\n\n";
        cabecera += "____________________________________________________________________________" + "\n\n";
        for (Pedido pedidos : pedido) {
            mensaje += "Nº: " + pedidos.getNroPedido() + "  Fecha de entrega: " + pedidos.getFechaEntrega() + "  Fecha de emision: " + pedidos.getFechaEmision() + "  Pendiente: "
                    + pedidos.getPendiente() + "\n\n";
        }
        generar(nombreArchivo);
        return nombreArchivo;
    }

    public String armarProducto(List<Producto> producto) {
        Date fechasistema = new Date();
        fecha = ConvertirFechas.fechaAString(fechasistema);
        mensaje = "";
        cabecera = "";
        nombreArchivo = "";
        nombreArchivo = fecha + "Productos.pdf";
        cabecera = "                                             LA CABAÑA CHOCOLATES S.A. \n";
        cabecera += "                                                Reporte de productos " + fecha + "\n\n";
        cabecera += "____________________________________________________________________________" + "\n\n";
        for (Producto productos : producto) {
            mensaje += "Codigo: " + productos.getCodigoProducto() + "  Nombre: " + productos.getNombreProducto() + "  Descripcion: " + productos.getDescripcionProducto() + "  Precio compra: "
                    + productos.getPrecioCompra() + "  Precio Venta: " + productos.getPrecioVenta() + "  Stock: " + productos.getStock().getCantidad() + "\n\n";
        }
        generar(nombreArchivo);
        return nombreArchivo;
    }

    public String armarVenta(List<Venta> venta) {
        Date fechasistema = new Date();
        fecha = ConvertirFechas.fechaAString(fechasistema);
        mensaje = "";
        cabecera = "";
        nombreArchivo = "";
        nombreArchivo = fecha + "Ventas.pdf";
        cabecera = "                                             LA CABAÑA CHOCOLATES S.A. \n";
        cabecera += "                                                Reporte de ventas " + fecha + "\n\n";
        cabecera += "____________________________________________________________________________" + "\n\n";
        for (Venta ventas : venta) {
            mensaje += "Nº: " + ventas.getNumero() + "  Fecha venta: " + ventas.getFechaventa() + "  Total: " + ventas.getTotal() + "  Cliente: "
                    + ventas.getCliente().getApellido() + " " + ventas.getCliente().getNombre() + "\n\n";
        }
        generar(nombreArchivo);
        return nombreArchivo;
    }

    private void generar(String nombreArchivo) {
        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();
            documento.add(new Paragraph(cabecera + mensaje));

        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        documento.close();
    }
}