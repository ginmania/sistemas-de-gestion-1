package Metodo;

import Interfaces.Cliente;
import Interfaces.Pedido;
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
        nombreArchivo = fecha + ".pdf";
        cabecera = "                                             LA CABAÑA CHOCOLATES S.A. \n";
        cabecera+= "                                                Reporte de clientes " + fecha +"\n\n" ;
        cabecera+= "____________________________________________________________________________" +"\n\n";
        for (Cliente clientes : cliente) {
            mensaje += "Apellido: " + clientes.getApellido() + "  Nombre: " + clientes.getNombre() + "  Celular: " + clientes.getCelular() + "  Direccion: " +
                    clientes.getDireccion() + "  email: " + clientes.getemail() + "\n\n";
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
        nombreArchivo = fecha + ".pdf";
        cabecera = "                                             LA CABAÑA CHOCOLATES S.A. \n";
        cabecera+= "                                                Reporte de pedidos " + fecha +"\n\n" ;
        cabecera+= "____________________________________________________________________________" +"\n\n";
        for (Pedido pedidos : pedido) {
            mensaje += "Apellido: " + pedidos.getNroPedido() + "  Nombre: " + pedidos.getProveedor() + "  Celular: " + pedidos.getFechaEmision() + "  Direccion: " +
                    pedidos.getFechaEntrega()+ "  email: " + pedidos.getPendiente() + "\n\n";
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