package Experto;

import Agentes.AgenteCatalogo;
import Agentes.AgenteCliente;
import Agentes.AgenteDemanda;
import Agentes.AgenteDetallePedido;
import Agentes.AgenteDetalleVenta;
import Agentes.AgenteParametros;
import Agentes.AgentePedido;
import Agentes.AgenteProducto;
import Agentes.AgenteProductoProveedor;
import Agentes.AgenteProveedor;
import Agentes.AgenteStock;
import Agentes.AgenteVenta;
import Implementaciones.CatalogoImpl;
import Implementaciones.ClienteImpl;
import Implementaciones.DemandaImpl;
import Implementaciones.DetallePedidoImpl;
import Implementaciones.DetalleVentaImpl;
import Implementaciones.ParametrosImpl;
import Implementaciones.PedidoImpl;
import Implementaciones.ProductoImpl;
import Implementaciones.ProductoProveedorImpl;
import Implementaciones.ProveedorImpl;
import Implementaciones.StockImpl;
import Implementaciones.VentaImpl;
import Persistencia.ObjetoPersistente;

public class FabricaEntidad {

    private static FabricaEntidad instancia = null;

    private static enum Entidades {

        Cliente, Producto, Proveedor, DetalleVenta, Venta, ProductoProveedor, Stock,
        Pedido, DetallePedido, Catalogo, Demanda, Parametros
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
            case Pedido:
                obj = new AgentePedido();
                ((AgentePedido) obj).setImpl(new PedidoImpl());
                return obj;
            case DetallePedido:
                obj = new AgenteDetallePedido();
                ((AgenteDetallePedido) obj).setImpl(new DetallePedidoImpl());
                return obj;
            case Catalogo:
                obj = new AgenteCatalogo();
                ((AgenteCatalogo) obj).setImpl(new CatalogoImpl());
                return obj;
            case Demanda:
                obj = new AgenteDemanda();
                ((AgenteDemanda) obj).setImpl(new DemandaImpl());
                return obj;
            case Parametros:
                obj = new AgenteParametros();
                ((AgenteParametros) obj).setImpl(new ParametrosImpl());
                return obj;
            default:
                System.out.println("no existe la entidad que desea crear");
                return "no existe la entidad que desea crear";

        }

    }
}
