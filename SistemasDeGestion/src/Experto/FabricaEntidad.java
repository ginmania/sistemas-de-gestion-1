package Experto;

import Agentes.AgenteCliente;
import Agentes.AgenteDetalleVenta;
import Agentes.AgenteProducto;
import Agentes.AgenteProveedor;
import Agentes.AgenteVenta;
import Implementaciones.ClienteImpl;
import Implementaciones.DetalleVentaImpl;
import Implementaciones.ProductoImpl;
import Implementaciones.ProveedorImpl;
import Implementaciones.VentaImpl;
import Persistencia.ObjetoPersistente;

public class FabricaEntidad {

    private static FabricaEntidad instancia = null;

    private static enum Entidades {

        Cliente, Producto, Proveedor, DetalleVenta, Venta
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
            default:
                return "no existe la entidad que desea crear";

        }

    }
}
