/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Agentes.AgenteProducto;
import Agentes.AgenteVenta;
import Controlador.DTO_DetalleVenta;
import Controlador.DTO_Venta;
import Interfaces.Cliente;
import Interfaces.DetalleVenta;
import Interfaces.Producto;
import Interfaces.Venta;
import Persistencia.Criterio;
import Persistencia.Fachada;
import Persistencia.FachadaInterna;
import Persistencia.ObjetoPersistente;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
/**
 *
 * @author duende
 */
public class ExpertoVentas implements Experto{
    //private Cliente cliente; //solamente un cliente
    //private ArrayList<Producto> productos;
    private int NroFactura;
    private Venta vta;
    private List<DetalleVenta> detalles;
    private DetalleVenta detalle;
    private DTO_DetalleVenta dtoDetalle;
    private double Total;    
    private Hashtable detVta;

    public ExpertoVentas() {
        this.NroFactura = (int) Math.random();//nro aleatorio de factura
        this.vta = (Venta) FabricaEntidad.getInstancia().FabricarEntidad(Venta.class); 
        this.detalles = new ArrayList<DetalleVenta>();  
        this.detVta = new Hashtable();
    }
    
    public DTO_DetalleVenta nuevoDetalle(Producto prod, int Cant){        
        dtoDetalle = new DTO_DetalleVenta();
        detalle = (DetalleVenta) FabricaEntidad.getInstancia().FabricarEntidad(DetalleVenta.class);

        detalle.setProducto(prod);
        dtoDetalle.setProd(prod);
        dtoDetalle.setCodProducto(prod.getCodigoProducto());
        dtoDetalle.setNombreProducto(prod.getNombreProducto());

        detalle.setCantidad(Cant);
        dtoDetalle.setCantidad(Cant);

        detalle.setVenta(this.vta);
        dtoDetalle.setNroVenta(this.vta.getNumero());
        dtoDetalle.setVta(this.vta);

        double p = prod.getPrecioVenta();
        detalle.setPrecioUnitario(p);
        dtoDetalle.setPrecioU(p);

        double pv = p * Cant;
        detalle.setPrecio(pv);
        dtoDetalle.setPrecio(pv);

        Total = Total + pv;            
        detalles.add(detalle);  
        System.out.println("agregamos el siguiente detalle");
        System.out.println(detalle);
        return dtoDetalle;
        
    }

    public double CalcularTotal() {
        return Total;
    }
    
    public int NroFactura(){
        return this.NroFactura + (int) Math.random();
    }
    
    public boolean GuardarVenta(int nroFactura,String fecha,Cliente cli){
        boolean res = false;
        vta.setFechaventa(fecha);        
        vta.setCliente(cli);
        vta.setTotal(Total);
        vta.setNumero(nroFactura);        
        vta.setDetalleVentas(detalles);               
        actualizarStock(detalles);
        res= Fachada.getInstancia().guardar((ObjetoPersistente) vta);
        
        NroFactura = vta.getNumero();        
        //deberia imprimir por impresora la factura
        System.out.println("\n Se guardo la factura nro"+String.valueOf(nroFactura)); 
        System.out.println("\n Cliente"+cli.getNombre()+"-"+cli.getApellido()+" -CUIT "+cli.getCUIT());       
        
        return res;
    }
    
    /*Este método busca las ventas que fueron hechas en un rango de fechas*/
    public List<DTO_Venta> buscarVentas (String fechaDesde,String fechaHasta){
        Criterio c1 = new Criterio();
        Criterio c2 = new Criterio();
        /*if (!fechaDesde.equals("")) {
            c1 = Fachada.getInstancia().crearCriterio("fechaventa", ">", fechaDesde);
        }
        if (!fechaHasta.equalsIgnoreCase("")) {
            c2 = Fachada.getInstancia().crearCriterio("fechaventa", "<", fechaHasta);
        }*/
        Criterio c3 = Fachada.getInstancia().crearCriterio("1", "=", "1");
        
        ArrayList<Venta> venta = Fachada.getInstancia().buscar(Venta.class,c3 ); 
        List<DTO_Venta> vres = new ArrayList<DTO_Venta>();
        
        for(int i=0; i<venta.size();i++){
            DTO_Venta dtoVenta = new DTO_Venta();
            dtoVenta.setNroVenta(venta.get(i).getNumero());
            dtoVenta.setFecha(venta.get(i).getFechaventa());
            Cliente cli = (Cliente) FachadaInterna.getInstancia().buscarOID(Cliente.class, ((AgenteVenta) venta.get(i)).getOIDCliente());
            dtoVenta.setNombreCliente(cli.getNombre()+"-"+cli.getApellido());
            dtoVenta.setDetalle(venta.get(i).getDetalleVenta());
            dtoVenta.setTotal(venta.get(i).getTotal());
            detVta.put(venta.get(i).getNumero(),venta.get(i).getDetalleVenta() );
            vres.add(dtoVenta);
        }  
        
        return vres;
    }
    
    /*Este método me trae todo el detalle de una venta - carga tardía*/
    public ArrayList<DTO_DetalleVenta> buscarDetallesVenta(int nroVta){
        ArrayList<DTO_DetalleVenta> dtoDV = new ArrayList<DTO_DetalleVenta>();
        List<DetalleVenta> dv = (List<DetalleVenta>) detVta.get(nroVta);
        /*Criterio cri = new Criterio();
        cri.setAtributo("OIDVenta");
        cri.setOperador("=");
        cri.setValor(v.getoid());
        ArrayList<DetalleVenta> DV = Fachada.getInstancia().buscar(DetalleVenta.class,cri);*/
        
        for(int i=0; i< dv.size();i++){
            DTO_DetalleVenta dtoTemp = new DTO_DetalleVenta();
            Producto p= dv.get(i).getProducto();
            dtoTemp.setCodProducto(p.getCodigoProducto());
            dtoTemp.setNombreProducto(p.getNombreProducto());
            dtoTemp.setPrecioU(p.getPrecioVenta());
            dtoTemp.setCantidad(dv.get(i).getCantidad());
            dtoTemp.setPrecio(dv.get(i).getPrecio());
            dtoDV.add(dtoTemp);
        }
        
        return dtoDV;    
    }

    private void actualizarStock(List<DetalleVenta> detalles) {
        ExpertoGestionStock es = (ExpertoGestionStock) FabricaExperto.getInstancia().FabricarExperto("ExpertoGestionStock");
        for(int i=0;i<detalles.size();i++){
            int cantN = detalles.get(i).getCantidad();            
            es.ActualizarStock(cantN,(AgenteProducto)detalles.get(i).getProducto());
        }
    }
}
