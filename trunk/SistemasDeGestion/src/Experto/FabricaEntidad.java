package Experto;

import Agentes.AgenteCliente;
import Agentes.AgenteDetalleVenta;
import Agentes.AgenteProducto;
import Agentes.AgenteProductoProveedor;
import Agentes.AgenteProveedor;
import Agentes.AgenteStock;
import Agentes.AgenteVenta;
import Implementaciones.ClienteImpl;
import Implementaciones.DetalleVentaImpl;
import Implementaciones.ProductoImpl;
import Implementaciones.ProductoProveedorImpl;
import Implementaciones.ProveedorImpl;
import Implementaciones.StockImpl;
import Implementaciones.VentaImpl;
import Persistencia.ObjetoPersistente;

public class FabricaEntidad {

    private static FabricaEntidad instancia = null;

    private static enum Entidades {

        Cliente, Producto, Proveedor, DetalleVenta, Venta, ProductoProveedor,Stock
    }

    public static FabricaEntidad getInstancia() {
        if (instancia == null) {
            instancia = new FabricaEntidad();
        }
        return instancia;
    }

    public Object FabricarEntidad(Class c) {
        ObjetoPersistente obj = null;
        
        switch (Entidades.valueOf(c.getSimpleName())) {
            case Cliente:
                obj = new AgenteCliente();
                ((AgenteCliente) obj).setImpl(new ClienteImpl());
                return obj;
            case Proveedor:
                obj = new AgenteProveedor();
                ((AgenteProveedor) obj).setImpl(new ProveedorImpl());
                return obj;
            case Producto:
                obj = new AgenteProducto();
                ((AgenteProducto) obj).setImpl(new ProductoImpl());
                return obj;
            case Venta:
                obj = new AgenteVenta();
                ((AgenteVenta) obj).setImpl(new VentaImpl());
                return obj;    
            case DetalleVenta:
                obj = new AgenteDetalleVenta();
                ((AgenteDetalleVenta) obj).setImpl(new DetalleVentaImpl());
                return obj;   
            case ProductoProveedor:                
                obj = new AgenteProductoProveedor(new ProductoProveedorImpl());                
                return obj;   
            case Stock:                
                obj = new AgenteStock();
                ((AgenteStock) obj).setImpl(new StockImpl());                
                return obj;   
            default:
                System.out.println("no existe la entidad que desea crear");
                return "no existe la entidad que desea crear";

        }

    }
}
