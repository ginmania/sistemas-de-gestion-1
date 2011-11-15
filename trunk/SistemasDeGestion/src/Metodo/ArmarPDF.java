package Metodo;

import Interfaces.Cliente;
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

    public void armarCliente(List<Cliente> cliente) {
        Document documento = new Document();
        Date fechasistema = new Date();
        //TODO lo genera en el directorio infind
        nombreArchivo = fechasistema + ".pdf";
        for (Cliente clientes : cliente) {
            mensaje = "Clientes: " + clientes.toString() + ":\n" + "bla bla bla";
        }
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();
            documento.add(new Paragraph(mensaje));

        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        documento.close();
    }
}