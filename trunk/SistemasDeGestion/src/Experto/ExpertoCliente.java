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
public class ExpertoCliente implements Experto {

    private List<Cliente> vectorDTOCliente = new ArrayList();
    private List<Demanda> vectorDTODemanda = new ArrayList();

    public ExpertoCliente() {
    }

    public List<Cliente> buscarCliente(String valornombre, String valordni) throws NoClienteExcepcion {
        vectorDTOCliente.clear();
        List<Cliente> clientes = null;
        Criterio c1 = new Criterio();
        Criterio c2 = new Criterio();
        if (!valornombre.equalsIgnoreCase("")) {
            c1 = Fachada.getInstancia().crearCriterio("Apellido", "like", "%" + valornombre + "%");
        }
        if (!valordni.equalsIgnoreCase("")) {
            c2 = Fachada.getInstancia().crearCriterio("dni", "=", valordni);
        }
        Criterio c3 = Fachada.getInstancia().crearCriterioCompuesto(c1, "AND", c2);
        clientes = Fachada.getInstancia().buscar(Cliente.class, c3);
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

    public boolean insertarCliente(String nombre, String apellido, String dni, String cuit,
            String direccion, String telfijo, String celular, String email, int baja) {
        boolean resultado = false;
        Cliente cliente = (Cliente) FabricaEntidad.getInstancia().FabricarEntidad(Cliente.class);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setdni(dni);
        cliente.setCUIT(cuit);
        cliente.setDireccion(direccion);
        cliente.setTelefono_Fijo(telfijo);
        cliente.setCelular(celular);
        cliente.setemail(email);
        cliente.setbaja(baja);
        resultado = Fachada.getInstancia().guardar((ObjetoPersistente) cliente);
        return resultado;
    }

    public boolean modificarCliente(Cliente cliente) {
        boolean resultado = false;
        if (cliente == null) {
            return resultado;
        }

        resultado = Fachada.getInstancia().guardar((ObjetoPersistente) cliente);
        return resultado;
    }

    public boolean confirmar() {
        return true;
    }

    public boolean eliminarCliente(Cliente cliente) {
        boolean resultado = false;
        if (cliente == null) {
            return resultado;
        }
        cliente.setbaja(1);
        resultado = Fachada.getInstancia().guardar((ObjetoPersistente) cliente);
        return resultado;
    }

    
}
