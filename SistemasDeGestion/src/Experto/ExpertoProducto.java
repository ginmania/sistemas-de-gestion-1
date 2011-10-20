/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Agentes.AgenteProducto;
import Agentes.AgenteProductoProveedor;
import Agentes.AgenteProveedor;
import Interfaces.Producto;
import Persistencia.Criterio;
import Persistencia.Fachada;
import java.util.ArrayList;
import Excepciones.NoProductoExcepcion;
import Interfaces.ProductoProveedor;
import Interfaces.Proveedor;
import Interfaces.Stock;
import Persistencia.ObjetoPersistente;
import java.util.List;

/**
 *
 * @author diego
 */
public class ExpertoProducto implements Experto {

    private List<Producto> vectorDTOProducto = new ArrayList();

    public ExpertoProducto() {
    }

    public List<Producto> buscarProducto(String valorCodigo, String valorNombre) throws NoProductoExcepcion {
        vectorDTOProducto.clear();
        List<Producto> productos = null;
        Criterio c1 = new Criterio();
        Criterio c2 = new Criterio();
        if (!valorNombre.equalsIgnoreCase("")) {
            c1 = Fachada.getInstancia().crearCriterio("Nombre", "like", "%" + valorNombre + "%");
        }
        if (!valorCodigo.equalsIgnoreCase("")) {
            c2 = Fachada.getInstancia().crearCriterio("codigo", "=", valorCodigo);
        }
        Criterio c3 = Fachada.getInstancia().crearCriterioCompuesto(c1, "AND", c2);
        productos = Fachada.getInstancia().buscar(Producto.class, c3);
        if (productos.isEmpty()) {
            throw new NoProductoExcepcion();
        }
        for (Producto producto : productos) {
            if (producto.getbaja() == 0) {
                System.out.println("Codigo: " + producto.getCodigoProducto() + "\n");
                System.out.println("Nombre: " + producto.getNombreProducto() + "\n");
                System.out.println("Descripcion: " + producto.getDescripcionProducto() + "\n");
                System.out.println("PrecioCompra: " + producto.getPrecioCompra() + "\n");
                System.out.println("PrecioVenta: " + producto.getPrecioVenta() + "\n");
              //  System.out.println("Cantidad Minima: " + producto.getStock().getCantidadMinima() + "\n");
              //  System.out.println("Cantidad: " + producto.getStock().getCantidad() + "\n");
                vectorDTOProducto.add(producto);
            }
        }
        return vectorDTOProducto;
    }

    public boolean insertarProducto(int codigo, String nombre, String descripcion, double precioCompra,
            double precioVenta, int baja, int cantidadminima, int cantidad) {
        boolean resultado = false;
        Producto producto = (Producto) FabricaEntidad.getInstancia().FabricarEntidad(Producto.class);
        producto.setCodigoProducto(codigo);
        producto.setNombreProducto(nombre);
        producto.setDescripcionProducto(descripcion);
        producto.setPrecioCompra(precioCompra);
        producto.setPrecioVenta(precioVenta);
        producto.setbaja(baja);
        Stock stock = (Stock) FabricaEntidad.getInstancia().FabricarEntidad(Stock.class);
        stock.setCantdidadMinima(cantidadminima);
        stock.setCantdidad(cantidad);
        resultado = Fachada.getInstancia().guardar((ObjetoPersistente) stock);
        producto.setStock(stock);
        resultado = Fachada.getInstancia().guardar((ObjetoPersistente) producto);

        return resultado;
    }
    
     public boolean insertarProducto(int codigo, String nombre, String descripcion, double precioCompra,
            double precioVenta, int baja, int cantidadminima, int cantidad, char ABC, Proveedor prov) throws NoProductoExcepcion {
        boolean resultado = false;
        Producto producto = (Producto) FabricaEntidad.getInstancia().FabricarEntidad(Producto.class);
        ProductoProveedor asocia = (ProductoProveedor) FabricaEntidad.getInstancia().FabricarEntidad(ProductoProveedor.class);
        Stock stock = (Stock) FabricaEntidad.getInstancia().FabricarEntidad(Stock.class);
        AgenteProducto ap = (AgenteProducto) producto;
        AgenteProveedor aP = (AgenteProveedor) prov;
        AgenteProductoProveedor APP= (AgenteProductoProveedor) asocia;
        producto.setCodigoProducto(codigo);
        producto.setNombreProducto(nombre);
        producto.setDescripcionProducto(descripcion);
        producto.setPrecioCompra(precioCompra);
        producto.setPrecioVenta(precioVenta);
        producto.setClasifABC(ABC);
        producto.setbaja(baja);        
        stock.setCantdidadMinima(cantidadminima);
        stock.setCantdidad(cantidad);
        asocia.setProducto(producto);
        asocia.setProveedor(prov);
        asocia.setbaja(baja);
        resultado = Fachada.getInstancia().guardar((ObjetoPersistente) stock);
        producto.setStock(stock);
        resultado = Fachada.getInstancia().guardar((ObjetoPersistente) producto);
        
        if(resultado)
            resultado = Fachada.getInstancia().guardar((ObjetoPersistente) asocia);
        else{
            resultado = false;
            throw new NoProductoExcepcion();
        }

        return resultado;
    }

    public boolean modificarProducto(Producto producto) {
        boolean resultado = false;
        if (producto == null) {
            return resultado;
        }
        resultado = Fachada.getInstancia().guardar((ObjetoPersistente) producto);
        return resultado;
    }

    public boolean confirmar() {
        return true;
    }

    public boolean eliminarProducto(Producto producto) {
        boolean resultado = false;
        if (producto == null) {
            return resultado;
        }
        producto.setbaja(1);
        resultado = Fachada.getInstancia().guardar((ObjetoPersistente) producto);
        return resultado;
    }

    
}
