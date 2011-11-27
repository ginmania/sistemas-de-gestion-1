/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Interfaces.Parametros;
import Persistencia.Criterio;
import Persistencia.Fachada;
import Persistencia.ObjetoPersistente;
import java.util.List;

/**
 *
 * @author diego
 */
public class ExpertoParametros implements Experto {

    public ExpertoParametros() {
    }

    public List<Parametros> buscarParametros() {
        List<Parametros> parametros = null;
        Criterio c1 = new Criterio("1", "=", 1);
        parametros = Fachada.getInstancia().buscar(Parametros.class, c1);
        return parametros;
    }

    public void establecer() {
    }

    boolean confirmar() {
        return true;
    }
        public boolean guardarParametros(double a, double b, double g) {
        boolean resultado = false;
        Parametros parametros = (Parametros) FabricaEntidad.getInstancia().FabricarEntidad(Parametros.class);
        parametros.setAlfa(a);
        parametros.setBeta(b);
        parametros.setGama(g);
        resultado = Fachada.getInstancia().guardar((ObjetoPersistente) parametros);
        return resultado;
    }

}
