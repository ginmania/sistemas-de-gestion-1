package Metodo;

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

    public void armar(List<String> herramientas) {
        Document documento = new Document();
        Date fechasistema = new Date();
        //TODO lo genera en el directorio infind
        nombreArchivo = fechasistema + ".pdf";
        for (String herramientas1 : herramientas) {
            mensaje = "Herramienta: " + herramientas1.toString() + ":\n" + "bla bla bla";
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

/*
 * private static File WORKING_DIRECTORY;
private static String PATH;

public static String getPath() {
String Recurso = WorkingDirectory.class.getSimpleName() + ".class";
if (PATH == null) {
try {
URL url = WorkingDirectory.class.getResource(Recurso);
if (url.getProtocol().equals("file")) {
File f = new File(url.toURI());
PATH = f.getParentFile().getPath();
System.out.println("1_ "+PATH);
} else if (url.getProtocol().equals("jar")) {
String expected = "!/" + WorkingDirectory.class.getCanonicalName()+".class";
String s = url.toString();
s = s.substring(4);
s = s.substring(0, s.length() - expected.length());
File f = new File(new URL(s).toURI());
PATH = f.getParentFile().getPath();
System.out.println("2_ "+PATH);
}
} catch (Exception ex) {
PATH=".";
}
}
return PATH;
}
 */
