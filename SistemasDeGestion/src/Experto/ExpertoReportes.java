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
            vectorDTOPedido.add(pedido);
        }
        return vectorDTOPedido;
    }

    public List<Producto> buscarProducto() throws NoClienteExcepcion {
        vectorDTOProducto.clear();
        List<Producto> productos = null;
        Criterio c1 = new Criterio("1", "=", "1");
        productos = Fachada.getInstancia().buscar(Producto.class, c1);
        if (productos.isEmpty()) {
            throw new NoClienteExcepcion();
        }
        for (Producto producto : productos) {
            vectorDTOProducto.add(producto);
        }
        return vectorDTOProducto;
    }
    
    public List<Venta> buscarVenta() throws NoClienteExcepcion {
        vectorDTOVenta.clear();
        List<Venta> ventas = null;
        Criterio c1 = new Criterio("1", "=", "1");
        ventas = Fachada.getInstancia().buscar(Venta.class, c1);
        if (ventas.isEmpty()) {
            throw new NoClienteExcepcion();
        }
        for (Venta venta : ventas) {
            vectorDTOVenta.add(venta);
        }
        return vectorDTOVenta;
    }

    public boolean iniciar() {
        return true;
    }

    public boolean confirmar() {
        return true;
    }
}
