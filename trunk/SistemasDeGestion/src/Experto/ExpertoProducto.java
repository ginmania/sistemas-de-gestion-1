/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Agentes.AgenteCatalogo;
import Agentes.AgenteProducto;
import Agentes.AgenteProductoProveedor;
import Agentes.AgenteProveedor;
import Interfaces.Producto;
import Persistencia.Criterio;
import Persistencia.Fachada;
import java.util.ArrayList;
import Excepciones.NoProductoExcepcion;
import Interfaces.Catalogo;
import Interfaces.DayOfYear;
import Interfaces.Demanda;
import Interfaces.DetallePedido;
import Interfaces.DetalleVenta;
import Interfaces.Pedido;
import Interfaces.ProductoProveedor;
import Interfaces.Proveedor;
import Interfaces.Stock;
import Persistencia.FachadaInterna;
import Persistencia.ObjetoPersistente;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author diego
 */
public class ExpertoProducto implements Experto {

    private List<Producto> vectorDTOProducto = new ArrayList();
    private float[][] tablaK = new float[9][2];

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
                System.out.println("Cantidad Minima: " + producto.getStock().getCantidadMinima() + "\n");
                System.out.println("Cantidad: " + producto.getStock().getCantidad() + "\n");
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
        stock.setCantidadMinima(cantidadminima);
        stock.setCantidad(cantidad);
        resultado = Fachada.getInstancia().guardar((ObjetoPersistente) stock);
        producto.setStock(stock);
        resultado = Fachada.getInstancia().guardar((ObjetoPersistente) producto);

        return resultado;
    }
    
     public boolean insertarProducto(int codigo, String nombre, String descripcion, double precioCompra,
            double precioVenta, int baja, int cantidadminima, int cantidad, char ABC, Proveedor prov) throws NoProductoExcepcion {
        boolean resultado = false;      
        double nivel = 0.0;
        GregorianCalendar fechaActual = new GregorianCalendar();
        String fechaSistema = String.valueOf(fechaActual.get(GregorianCalendar.DAY_OF_MONTH))
                       .concat("-")
                       .concat(String.valueOf(fechaActual.get(GregorianCalendar.MONTH) + 1))
                       .concat("-")
                       .concat(String.valueOf(fechaActual.get(GregorianCalendar.YEAR)));
        Producto producto = (Producto) FabricaEntidad.getInstancia().FabricarEntidad(Producto.class);
        Catalogo asocia = (Catalogo) FabricaEntidad.getInstancia().FabricarEntidad(Catalogo.class);
        Stock stock = (Stock) FabricaEntidad.getInstancia().FabricarEntidad(Stock.class);
        AgenteProducto ap = (AgenteProducto) producto;
        AgenteProveedor aP = (AgenteProveedor) prov;
        AgenteCatalogo APP= (AgenteCatalogo) asocia;
        //producto
        nivel = this.CalcularNivelServicio(producto, prov);
        producto.setCodigoProducto(codigo);
        producto.setNombreProducto(nombre);
        producto.setDescripcionProducto(descripcion);
        producto.setPrecioCompra(precioCompra);
        producto.setPrecioVenta(precioVenta);
        producto.setClasifABC(ABC);
        producto.setNivelServicio((float) nivel);
        producto.setbaja(baja);       
        //stock
        stock.setStockPendiente(0);
        cantidadminima = (int) this.CalcularStockSeguridad(producto, prov);
        stock.setCantidadMinima(cantidadminima);
        stock.setCantidad(cantidad);
        //catalogo
        APP.setOIDProducto(aP.getoid());
        APP.setOIDProducto(ap.getoid());
        asocia.setProducto(producto);
        asocia.setProveedor(prov);
        asocia.setPrecioCompra(precioCompra);
        asocia.setFecha(fechaSistema);
        asocia.setDemora(2);
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
    
    public ArrayList<Producto> buscarEnCatalogo(Proveedor p){
        ArrayList<Producto> productos = new ArrayList<Producto>();
        ArrayList<Catalogo> catalogo;
        AgenteProveedor ap = (AgenteProveedor) p;
        Criterio c1 = Fachada.getInstancia().crearCriterio("OIDProveedor", "=", ap.getoid());
        Criterio c2 = Fachada.getInstancia().crearCriterio("baja", "=", 0);
        Criterio c3 = Fachada.getInstancia().crearCriterioCompuesto(c1, "and", c2);
        catalogo = Fachada.getInstancia().buscar(Catalogo.class, c3);
        for(int i=0; i<catalogo.size();i++){
            Producto aux;
            AgenteCatalogo ac= (AgenteCatalogo) catalogo.get(i);
            aux = (Producto) FachadaInterna.getInstancia().buscarOID(Producto.class,ac.getOIDProducto());
            productos.add(aux);
        }
        return productos;
    }
    
    public Double CalcularNivelServicio(Producto p, Proveedor P){
        // 1 - (costo anual de almacenamiento * cantidad pedido)/(Demanda * costo emisión pedido)
        Double res; 
        int agotamiento, cantPedida = 0;
        double costoemi,costoalmac = 0;
        Fachada fac = Fachada.getInstancia();
        AgenteProducto ap = (AgenteProducto) p;
        AgenteProveedor AP = (AgenteProveedor) P;
        ArrayList<Demanda> demanda;
        ArrayList<Proveedor> proveedors;
        ArrayList<Pedido> pedidos;
         Criterio d = fac.crearCriterio("OIDProducto", "=", ap.getoid());
         demanda = fac.buscar(Demanda.class, d);
         float dem = (float) 0.0;  
         float MSE = (float) 0.0;
         for(int i=0; i < demanda.size();i++){
              dem = (float) (dem + demanda.get(i).getDemandapronosticada());              
           }
         costoemi = P.getCostoEmision();
         if (dem != 0.0){ //el producto no es nuevo
             Criterio ped = fac.crearCriterio("OIDProveedor", "=", AP.getoid());
             Criterio p2 = fac.crearCriterio("pend", "=", 1);
             Criterio c = fac.crearCriterioCompuesto(ped, "AND", p2);
             //buscamos el pedido pendiente
             pedidos = fac.buscar(Pedido.class, c);
             for(int j=0; j < pedidos.size();j++){
                 List<DetallePedido> dets = pedidos.get(j).getDetallePedido();
                 for(int k=0;k < dets.size();k++){
                     if(dets.get(k).getProducto().getCodigoProducto() == p.getCodigoProducto())
                         cantPedida = dets.get(k).getCantidad();
                 }             
             }
          }
         
         res = ((costoalmac * cantPedida)/(dem * costoemi));
         res = 1 - res;
         if(Double.isNaN(res)) res =0.0;
        return res;
    }
    
    public double CalcularNivelServicio(Producto p){
        double res; 
        int cantVendida=0, cantPedida = 0;
        
        Fachada fac = Fachada.getInstancia();
        AgenteProducto ap = (AgenteProducto) p;
        ArrayList<Demanda> demanda;
        ArrayList<DetalleVenta> ventas;
        ArrayList<Pedido> pedidos;
         Criterio d = fac.crearCriterio("OIDProducto", "=", ap.getoid());
         demanda = fac.buscar(Demanda.class, d);
         float dem = (float) 0.0;  
         float vtas = (float) 0.0;
         for(int i=0; i < demanda.size();i++){
              dem = (float) (dem + demanda.get(i).getDemandapronosticada());
          }
         ventas = fac.buscar(DetalleVenta.class, d);
         for(int j=0; j < ventas.size();j++){
             vtas = vtas + ventas.get(j).getCantidad();
         }
         
         if(vtas==0 || dem==0) res = 0.50;
         else res = (vtas/dem);
         res = Math.round(res);
        return res;
    }
    
    public double CalcularStockSeguridad(Producto p, Proveedor P){
        double ss =0;
        Fachada fac = Fachada.getInstancia();
        AgenteProducto ap = (AgenteProducto) p;
        AgenteProveedor AP = (AgenteProveedor) P;
        ArrayList<Demanda> demanda;
        ArrayList<Proveedor> proveedors;
        //buscamos la demanda para ese producto
        Criterio d = fac.crearCriterio("OIDProducto", "=", ap.getoid());
         demanda = fac.buscar(Demanda.class, d);
         double dem = 0;  
         for(int i=0; i < demanda.size();i++){
              dem = dem + demanda.get(i).getDemandapronosticada();              
           }
         //nuestro plazo máximo de entrega son 15 días pasado el tiempo de reposicion
         int PME = AP.getTiempoR() + 15;
         int pe = AP.getTiempoR();
         ss = (PME - pe)*dem;
         
        return ss;
    }
    
    public double CalcularPuntoPedido(Producto p, Proveedor P){
        return 0;
    }
}
