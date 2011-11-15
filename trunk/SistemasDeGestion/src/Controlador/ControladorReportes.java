/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Excepciones.NoClienteExcepcion;
import Experto.ExpertoReportes;
import Experto.FabricaExperto;
import Interfaces.Cliente;
import Metodo.ArmarPDF;
import Pantalla.PantallaPrincipal;
import Persistencia.Fachada;
import java.util.List;

/**
 *
 * @author diego
 */
public class ControladorReportes {

    private ControladorPrincipal controladorPrincipal;
    private PantallaPrincipal pantallaPrincipal;
    private ArmarPDF pdf;
    private ExpertoReportes expertoReportes;

    public ControladorReportes(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        this.expertoReportes = (ExpertoReportes) FabricaExperto.getInstancia().FabricarExperto("ExpertoReportes");
    }

    public void generarReporte(String nombreReporte) throws NoClienteExcepcion {
        pdf = new ArmarPDF();
        List<Cliente> resultadoBusqueda = null;
        if (nombreReporte.equalsIgnoreCase("Cliente")) {
            resultadoBusqueda = expertoReportes.buscarCliente();
        }
        pdf.armarCliente(resultadoBusqueda);
    }
}