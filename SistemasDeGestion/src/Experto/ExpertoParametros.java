/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Interfaces.Parametros;
import Interfaces.Venta;
import Persistencia.Criterio;
import Persistencia.Fachada;
import Persistencia.ObjetoPersistente;
import java.util.List;

/**
 *
 * @author diego
 */
public class ExpertoParametros implements Experto {
    
    private Parametros pm;

    public ExpertoParametros() {
        this.pm = (Parametros) FabricaEntidad.getInstancia().FabricarEntidad(Parametros.class); 
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
        pm.setAlfa(a);
        pm.setBeta(b);
        pm.setGama(g);
        resultado = Fachada.getInstancia().guardar((ObjetoPersistente) pm);
        return resultado;
    }

}
