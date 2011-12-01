/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Agentes.AgenteDetalleVenta;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
/**
 *
 * @author duende
 */
public class ExpertoVentas implements Experto{
    //private Cliente cliente; //solamente un cliente
    //private ArrayList<Producto> productos;
    private Fachada fachada;
    private int NroFactura;
    private Venta vta;
    private AgenteVenta Avta;
    private List<DetalleVenta> detalles;
    private ArrayList<Producto> produs;
    private DetalleVenta detalle;
    private AgenteDetalleVenta Adetalle;
    private DTO_DetalleVenta dtoDetalle;
    private double Total;    
    private Hashtable detVta;

    public ExpertoVentas() {
        fachada = Fachada.getInstancia();
        this.NroFactura = (int) Math.random();//nro aleatorio de factura
        this.vta = (Venta) FabricaEntidad.getInstancia().FabricarEntidad(Venta.class); 
        Avta = (AgenteVenta) vta;
        vta.setNumero(this.NroFactura()+1);
        this.detalles = new ArrayList<DetalleVenta>();  
        this.produs = new ArrayList<Producto>();
        this.detVta = new Hashtable();
    }
    
    public DTO_DetalleVenta nuevoDetalle(Producto prod, int Cant){        
        dtoDetalle = new DTO_DetalleVenta();
        detalle = (DetalleVenta) FabricaEntidad.getInstancia().FabricarEntidad(DetalleVenta.class);
        Adetalle = (AgenteDetalleVenta) detalle;

        detalle.setProducto(prod);
        dtoDetalle.setProd(prod);
        dtoDetalle.setCodProducto(prod.getCodigoProducto());
        dtoDetalle.setNombreProducto(prod.getNombreProducto());

        detalle.setCantidad(Cant);
        dtoDetalle.setCantidad(Cant);

        detalle.setVenta(this.vta);
        Adetalle.setOIDVenta(this.Avta.getoid());
        dtoDetalle.setNroVenta(this.vta.getNumero());
        dtoDetalle.setVta(this.vta);

        double p = prod.getPrecioVenta();
        detalle.setPrecioUnitario(p);
        dtoDetalle.setPrecioU(p);

        double pv = p * Cant;
        detalle.setPrecio(pv);
        dtoDetalle.setPrecio(pv);

        Total = Total + pv;     
        vta.setTotal(Total);
        vta.setDetalleVenta(detalle);
        detalles.add(detalle);  
        produs.add(prod);
        System.out.println("agregamos el siguiente detalle");
        System.out.println(detalle.getCantidad()+"\n "+detalle.getPrecioUnitario()+"\n "+detalle.getProducto());
        return dtoDetalle;
        
    }

    public double CalcularTotal() {
        return Total;
    }
    
    public int NroFactura(){
        int aux1, aux, nro=0;
        ArrayList<Venta> vtas = fachada.buscar_todo(Venta.class);
        for(int i=1;i<vtas.size();i++){
            aux = vtas.get(i-1).getNumero();
            aux1 = vtas.get(i).getNumero();
            if(aux > aux1) nro = aux;
            else nro = aux1;
        }
        return nro +1;
    }
    
    public boolean GuardarVenta(int nroFactura,Date fecha,Cliente cli){
        boolean res = false;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        ExpertoPoliticaSQ expPSQ = (ExpertoPoliticaSQ) FabricaExperto.getInstancia().FabricarExperto("ExpertoPoliticaSQ");
        ExpertoPoliticaSR expPSR = (ExpertoPoliticaSR) FabricaExperto.getInstancia().FabricarExperto("ExpertoPoliticaSR");
         formato.applyPattern("yyyy-MM-dd");
        String fch = formato.format(fecha);
        vta.setFechaventa(fch);        
        vta.setCliente(cli);
        vta.setTotal(Total);
        
        res= fachada.guardar((ObjetoPersistente) vta);
        if(res){
            actualizarStock(detalles);
            /*AUTOMATIZACION*/
            //verifico si el producto alcanzo el ss y si es asi realizo el pedido
            expPSQ.automatizado(produs);
            expPSR.automatizado(produs);
            //deberia imprimir por impresora la factura
            System.out.println("\n Se guardo la factura nro"+String.valueOf(nroFactura)); 
            System.out.println("\n Cliente"+cli.getNombre()+"-"+cli.getApellido()+" -CUIT "+cli.getCUIT());
            detalles.clear();
            produs.clear();
        }
        return res;
    }
    
    /*Este método busca las ventas que fueron hechas en un rango de fechas*/
    public List<DTO_Venta> buscarVentas (String fechaDesde,String fechaHasta){
        Criterio c1 = new Criterio();
        Criterio c2 = new Criterio();
        if (!fechaDesde.equals("")) {
            c1 = fachada.crearCriterio("fechaventa", ">=", fechaDesde);
        }
        if (!fechaHasta.equalsIgnoreCase("")) {
            c2 = fachada.crearCriterio("fechaventa", "<=", fechaHasta);
        }
        Criterio c3 = fachada.crearCriterioCompuesto(c1, "AND", c2);
        
        ArrayList<Venta> venta = fachada.buscar(Venta.class,c3 ); 
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
            AgenteProducto p = (AgenteProducto)detalles.get(i).getProducto();
            es.DisminuirStock(cantN,p);
        }
        
    }
}
