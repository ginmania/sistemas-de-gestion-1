/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Excepciones.NoClienteExcepcion;
import Interfaces.Cliente;
import Interfaces.Demanda;
import Persistencia.Criterio;
import Persistencia.Fachada;
import Persistencia.ObjetoPersistente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class ExpertoReportes implements Experto {

    private List<Cliente> vectorDTOCliente = new ArrayList();

    public ExpertoReportes() {
    }

    public List<Cliente> buscarCliente() throws NoClienteExcepcion {
        vectorDTOCliente.clear();
        List<Cliente> clientes = null;
        clientes = Fachada.getInstancia().buscarAll(Cliente.class);
        if (clientes.isEmpty()) {
            throw new NoClienteExcepcion();
        }
        for (Cliente cliente : clientes) {
            if (cliente.getbaja() == 0) {
                vectorDTOCliente.add(cliente);
            }
        }
        return vectorDTOCliente;
    }

    public boolean iniciar(){
        return true;
    }
    public boolean confirmar() {
        return true;
    }
}
