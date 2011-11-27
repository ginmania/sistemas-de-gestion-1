package Persistencia;

import java.util.HashMap;
import java.util.Map;

public class FabricaIntermediario {

    private static FabricaIntermediario instancia = null;
    private static Map<Class, IntermediarioPersistencia> mapaIntermediario = null;

    private static enum Intermediarios {

        Proveedor, AgenteProveedor, ProveedorImpl,
        Cliente, ClienteImpl, AgenteCliente,
        Producto, AgenteProducto, ProductoImpl,
        Categoria, AgenteCategoria, CategoriaImpl,
        Demanda, AgenteDemanda, DemandaImpl,
        Venta, AgenteVenta, VentaImpl,
        DetalleVenta, AgenteDetalleVenta, DetalleVentaImpl,
        Stock, AgenteStock, StockImpl,
        Pedido, AgentePedido, PedidoImpl,
        DetallePedido, AgenteDetallePedido, DetallePedidoImpl,
        ProductoProveedor, AgenteProductoProveedor, ProductoProveedorImpl,
        Catalogo, CatalogoImpl, AgenteCatalogo,
        PoliticaStock, PoliticaStockImpl, AgentePoliticaStock,
        Parametros, ParametrosImpl, AgenteParametros
    }

    public FabricaIntermediario() {
        mapaIntermediario = new HashMap<Class, IntermediarioPersistencia>();
    }

    public static FabricaIntermediario getInstancia() {
        if (instancia == null) {
            instancia = new FabricaIntermediario();
        }
        return instancia;
    }

    public IntermediarioPersistencia fabricarIntermediario(Class c) {
        IntermediarioPersistencia obj = null;
        if (mapaIntermediario.containsKey(c)) {
            return mapaIntermediario.get(c);
        }

        switch (Intermediarios.valueOf(c.getSimpleName())) {
            case Proveedor:
            case AgenteProveedor:
            case ProveedorImpl:
                obj = new IntermediarioProveedor();
                break;
            case Cliente:
            case AgenteCliente:
            case ClienteImpl:
                obj = new IntermediarioCliente();
                break;
            case Producto:
            case AgenteProducto:
            case ProductoImpl:
                obj = new IntermediarioProducto();
                break;
            case Categoria:
            case AgenteCategoria:
            case CategoriaImpl:
                obj = new IntermediarioCategoria();
                break;
            case Demanda:
            case AgenteDemanda:
            case DemandaImpl:
                obj = new IntermediarioDemanda();
                break;
            case DetalleVenta:
            case AgenteDetalleVenta:
            case DetalleVentaImpl:
                obj = new IntermediarioDetalleVenta();
                break;
            case Venta:
            case AgenteVenta:
            case VentaImpl:
                obj = new IntermediarioVenta();
                break;
            case Stock:
            case AgenteStock:
            case StockImpl:
                obj = new IntermediarioStock();
                break;
            case Pedido:
            case AgentePedido:
            case PedidoImpl:
                obj = new IntermediarioPedido();
                break;
            case DetallePedido:
            case AgenteDetallePedido:
            case DetallePedidoImpl:
                obj = new IntermediarioDetallePedido();
                break;
            case ProductoProveedor:
            case AgenteProductoProveedor:
            case ProductoProveedorImpl:
                obj = new IntermediarioProductoProveedor();
                break;
            case Catalogo:
            case AgenteCatalogo:
            case CatalogoImpl:
                obj = new IntermediarioCatalogo();
                break;
            case PoliticaStock:
            case AgentePoliticaStock:
            case PoliticaStockImpl:
                obj = new IntermediarioPoliticaStock();
                break;
            case Parametros:
            case AgenteParametros:
            case ParametrosImpl:
                obj = new IntermediarioParametros();
                break;
            default:
                return null;

        }
        mapaIntermediario.put(c, obj);

        return obj;
    }
}
