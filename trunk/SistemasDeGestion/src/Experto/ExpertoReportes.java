/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Excepciones.NoClienteExcepcion;
import Interfaces.Cliente;
import Interfaces.Pedido;
import Interfaces.Producto;
import Interfaces.Venta;
import Persistencia.Criterio;
import Persistencia.Fachada;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class ExpertoReportes implements Experto {

    private List<Cliente> vectorDTOCliente = new ArrayList();
    private List<Pedido> vectorDTOPedido = new ArrayList();
    private List<Producto> vectorDTOProducto = new ArrayList();
    private List<Venta> vectorDTOVenta = new ArrayList();

    public ExpertoReportes() {
    }

    public List<Cliente> buscarCliente() throws NoClienteExcepcion {
        vectorDTOCliente.clear();
        List<Cliente> clientes = null;
        Criterio c1 = new Criterio("1", "=", "1");
        clientes = Fachada.getInstancia().buscar(Cliente.class, c1);
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
    
    public List<Pedido> buscarPedido() throws NoClienteExcepcion {
        vectorDTOPedido.clear();
        List<Pedido> pedidos = null;
        Criterio c1 = new Criterio("1", "=", "1");
        pedidos = Fachada.getInstancia().buscar(Pedido.class, c1);
        if (pedidos.isEmpty()) {
            throw new NoClienteExcepcion();
        }
        for (Pedido pedido : pedidos) {
//            if (pedido.getbaja() == 0) {
                vectorDTOPedido.add(pedido);
  //          }
        }
        return vectorDTOPedido;
    }

    public boolean iniciar(){
        return true;
    }
    public boolean confirmar() {
        return true;
    }
}
