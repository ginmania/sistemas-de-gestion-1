/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Agentes.AgentePoliticaStock;
import Agentes.AgenteProducto;
import Agentes.AgenteProveedor;
import Controlador.DTO_Venta;
import Interfaces.Catalogo;
import Interfaces.DayOfYear;
import Interfaces.Demanda;
import Interfaces.PoliticaStock;
import Interfaces.Producto;
import Interfaces.Proveedor;
import Persistencia.Criterio;
import Persistencia.Fachada;
import Persistencia.FachadaInterna;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Vector;

/**
 *
 * @author duende
 */
public class ExpertoPoliticaSQ implements Experto{
  
     private ArrayList<Object> respBD;
    private Fachada obFP;
    private int qOptimo;
    private Proveedor prov;
    private Demanda hDemanda;
    private Catalogo catalogo;
   // private EntidadPedidoPendiente pedido;
    private PoliticaStock[] politica;

    public void iniciar(DTO_Venta venta){
        obFP = Fachada.getInstancia();
        
        Vector prod = new Vector();
        Vector cant = new Vector();

        politica = new PoliticaStock[2];
        respBD = obFP.buscar_todo(PoliticaStock.class);
        for(int i = 0; i < politica.length; i++){
            politica[i] = (PoliticaStock)respBD.get(i); 
            }// busca las politicas de stock
        
        String id = new String();
        for(int i = 0; i < politica.length; i++){
           if(politica[i].getDescripcion().equals("Politica S,Q"))
               id = ((AgentePoliticaStock)politica[i]).getoid();
       }//setea la politica sq en una variable id
        prod = venta.getProductosVector(); //vector con los productos vendidos
        //cant = venta.getCantidadesVector(); // vector con las cantidades vendidas de cada producto

        prov = (Proveedor) FabricaEntidad.getInstancia().FabricarEntidad(Proveedor.class);
        catalogo = (Catalogo) FabricaEntidad.getInstancia().FabricarEntidad(Catalogo.class);
        Producto producto = (Producto) FabricaEntidad.getInstancia().FabricarEntidad(Producto.class);
        int cantidad = 0;
        
        for(int i = 0; i < prod.size(); i++){
            producto = (Producto)prod.get(i);
            cantidad = (Integer)cant.get(i);
            AgenteProducto ap = (AgenteProducto) prod.get(i);
            if(ap.getoid().equals(id)) 
              if(verificarPtoDePedido(producto))
                  cantidad = calcularLoteOptimo((Producto)prod.get(i),prov);
        }
       // obFP.confirmarTransaccion();
    }
    
    public void iniciar(){
        obFP = Fachada.getInstancia();
    }

    private boolean verificarPtoDePedido(Producto producto){
        Criterio c1 = obFP.crearCriterio("OIDProducto", "=", ((AgenteProducto)producto).getoid());
        boolean pedir = false;
        catalogo = (Catalogo)obFP.buscar(Catalogo.class, c1);
        hDemanda = (Demanda)obFP.buscar(Demanda.class,c1);
        int cantMinima = producto.getStock().getCantidadMinima();
        int stockPendiente = producto.getStock().getCantidad() + producto.getStock().getStockPendiente();
        if(cantMinima > stockPendiente){ // prod.getStockPend debe introducirse en el intermediario producto
            pedir = true;
        }
        return pedir;
    }

    public int calcularLoteOptimo(Producto producto, Proveedor prove){
        obFP = Fachada.getInstancia();
        boolean pedir = false;
        AgenteProducto agprod = (AgenteProducto) producto;
        AgenteProveedor agprov = (AgenteProveedor) prove;
        GregorianCalendar fechaActual = new GregorianCalendar();
        DayOfYear objDia;
        int tiemR = 1;     //Valor por defecto...
        int perActual = 1; //Valor por defecto...
        float diaTemp = 0; //Valor por defecto...
        float demanda = (float) 0.0; 
        float MSE = (float) 0.0;
        tiemR = prove.getTiempoR();
        perActual =prove.getPeriodoActual();
        objDia = new DayOfYear();
        int diaDelAnio = objDia.getDiaDelAnio();
        int diasTotalAnio = (objDia.esBisiesto()) ? 366 : 365;
            if (tiemR == 0) {
                diaTemp = 0;
            } else {
                diaTemp = diaDelAnio / tiemR;
            }
            int periodo = (int) diaTemp;
            //..................................................................
            if (diaTemp - periodo != 0) {
                if (diaTemp < (int) (diasTotalAnio / tiemR)) {
                    ++periodo;
                }
            }
        ArrayList<Catalogo> cat = new ArrayList<Catalogo>();
        ArrayList<Demanda> dem = new ArrayList<Demanda>();
        Criterio c1 = obFP.crearCriterio("OIDProducto", "=", agprod.getoid());
        Criterio c2 = obFP.crearCriterio("OIDProveedor", "=", agprov.getoid());
        Criterio c3 = obFP.crearCriterioCompuesto(c1,"AND", c2);
        Criterio c4 = obFP.crearCriterio("periodo", "=", String.valueOf(periodo));
        Criterio c5 = obFP.crearCriterio("anio","=",String.valueOf(fechaActual.get(GregorianCalendar.YEAR)-1));
        //deberia devolver un solo catalogo
        cat = obFP.buscar(Catalogo.class, c3);
        Criterio c6 = obFP.crearCriterioCompuesto(c1, "AND", c4);
        Criterio c7 = obFP.crearCriterioCompuesto(c6, "AND", c5);
        dem = obFP.buscar(Demanda.class, c7);
        Demanda D = (Demanda) FabricaEntidad.getInstancia().FabricarEntidad(Demanda.class);
        if(!dem.isEmpty()) 
            D = dem.get(0);
        int optimo = 0;
        Catalogo K = (Catalogo) FabricaEntidad.getInstancia().FabricarEntidad(Catalogo.class);
        if(!cat.isEmpty())
            K = cat.get(0);  
        //evaluo si llego al punto de pedido
        int cantMinima = producto.getStock().getCantidadMinima();
        int stockPendiente = producto.getStock().getCantidad() + producto.getStock().getStockPendiente();
        if(cantMinima > stockPendiente){
            optimo = (int)Math.sqrt(2 * prove.getCostoEmision() * D.getDemandareal() * 52 / (0.2 * K.getPrecioCompra()));
            System.out.println("Lote optimo " + optimo);
        }
        
        return optimo;
    }
    
}
