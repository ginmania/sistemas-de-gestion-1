/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Agentes.AgenteProducto;
import Controlador.ControladorPrincipal;
import Interfaces.Catalogo;
import Interfaces.DayOfYear;
import Interfaces.Pedido;
import Interfaces.Producto;
import Interfaces.Proveedor;
import Interfaces.Stock;
import Pantalla.PantallaPrincipal;
import Persistencia.Criterio;
import Persistencia.Fachada;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author duende
 */
public class ExpertoReloj implements Experto{
    private Fachada fac;
    private GregorianCalendar fechaSistema;
    private DayOfYear dia;
    private PantallaPrincipal ppal;
    private ExpertoMetodos expMetodos;
    private ExpertoVentas expVentas;
    private ExpertoGestionStock expStock;
    private ExpertoPoliticaSQ expSQ;
    private ExpertoPoliticaSR expSR;
    private ExpertoRealizarPedido expPed;
    
    private ArrayList<Producto> prods;
    private ArrayList<Catalogo> catgs;
    private ArrayList<Pedido> peds;
    private int diaActual;

    public ExpertoReloj() {
        fac = Fachada.getInstancia();
        //ppal = ControladorPrincipal.class.ge;
        fechaSistema = new GregorianCalendar();
        dia = new DayOfYear();
        diaActual = dia.getDiaDelAnio();
        
        
    }
    public void iniciar(PantallaPrincipal pant){
        ppal = pant;
        buscarPedidosPendientes();
        buscarProductosPuntoPedido();
        //calculoDemanda();
    }
    /*Este experto carga el informe inicial o bandeja de entrada de pedidos pendientes*/
    /*carga los avisos de productos por debajo del stock*/
    /*Manda a calcular demandas y genera nuevos pedidos*/

    private void buscarPedidosPendientes() {
        DefaultTableModel md = new DefaultTableModel();
        Criterio cp = fac.crearCriterio("pend", "=", 1);
        peds = fac.buscar(Pedido.class, cp);
        md.addColumn("NroPedido");        
        md.addColumn("FechaEmision");   
        md.addColumn("CodigoProveedor");   
        md.addColumn("Proveedor");        
        
        for(int i=0; i<peds.size();i++){
            //lleno la tabla del detalle en pantalla
            Object[] newRow = new Object[4];
            newRow[0] = peds.get(i).getNroPedido();
            newRow[1] = peds.get(i).getFechaEmision();
            newRow[2] = peds.get(i).getProveedor().getCodigo_proveedor();
            newRow[3] = peds.get(i).getProveedor().getNombre();            
            md.addRow(newRow);
        }
        ppal.getBandejaEntrada().setModel(md);
    }

    private boolean buscarProductosPuntoPedido() {
        DefaultTableModel tprod = new DefaultTableModel();
        Criterio p1 = fac.crearCriterio("baja", "=",0);
        ArrayList<Producto> pp = new ArrayList();
        List<Proveedor> pr;        
        ArrayList<Proveedor> praux = new ArrayList();
        Hashtable prodaux = new Hashtable();
        tprod.addColumn("CodProducto"); 
        tprod.addColumn("cantidad");   
        tprod.addColumn("cantidad minima");  
        tprod.addColumn("clasificacion");  
        
        //buscamos los productos por debajo del mínimo y mostramos
        Proveedor prov = (Proveedor) FabricaEntidad.getInstancia().FabricarEntidad(Proveedor.class);
        prods = fac.buscar(Producto.class, p1);
        //separo los productos que alcanzaron el mínimo
        for(int i=0; i< prods.size();i++){
            Stock s = prods.get(i).getStock();
            int ss = s.getCantidadMinima();
            int actual = s.getCantidad();
            if(actual <= ss){
                pp.add(prods.get(i));
                Object[] newRow = new Object[4];
                newRow[0] = prods.get(i).getCodigoProducto();
                newRow[1] = prods.get(i).getStock().getCantidad();
                newRow[2] = prods.get(i).getStock().getCantidadMinima();
                newRow[3] = prods.get(i).getClasifABC();
                tprod.addRow(newRow);
            }
        }
        
        if(diaActual != 28) return false;
        if(pp.size()== 0) return false;
        //si tenemos productos con stock bajo y es fin de mes        
        for(int j=0; j<pp.size();j++){
                Producto pto = pp.get(j);
                pr= pp.get(j).getProveedors();                
                prov = SeleccionarProveedor(pr);
                //los elegidos
                praux.add(prov);
                ArrayList<Producto> aux = new ArrayList();
                if(prodaux.containsKey(prov)) { 
                    aux = (ArrayList<Producto>) prodaux.get(prov);
                    aux.add(pto);
                    prodaux.remove(prov);
                    prodaux.put(prov, aux);
                }else{
                    aux.add(pto);
                    prodaux.put(prov, aux);
                }
        }
        //armamos por proveedor el pedido
        expPed = (ExpertoRealizarPedido) FabricaExperto.getInstancia().FabricarExperto("ExpertoRealizarPedido");
        expSR = (ExpertoPoliticaSR) FabricaExperto.getInstancia().FabricarExperto("ExpertoPoliticaSR");
        expSQ = (ExpertoPoliticaSQ) FabricaExperto.getInstancia().FabricarExperto("ExpertoPoliticaSQ");
        
        for(int s=0; s<praux.size();s++){
            ArrayList<Producto> aux = new ArrayList();
            Proveedor P =praux.get(s);
            aux = (ArrayList<Producto>) prodaux.get(P);
            Hashtable cantidad = new Hashtable();
            for(int l=0; l<aux.size();l++){
                int lote = 0;
                Producto aux2 = aux.get(l);
                AgenteProducto ap = (AgenteProducto) aux2;
                if(ap.getOIDPolitica().equals("1")){
                    lote = expSR.calcularLote(aux2,P);
                }else{
                //si es la politica S,Q llamo al otro experto
                    lote = expSQ.calcularLoteOptimo(aux2,P); 
                }
                cantidad.put(aux2, lote);
            }
            expPed.CrearPedidoPendiente(fechaSistema.getGregorianChange(), prov, prods, cantidad);
        }
        
        return true;
    }

    private void calculoDemanda() {
        expMetodos = (ExpertoMetodos) FabricaExperto.getInstancia().FabricarExperto("ExpertoMetodos") ;
        
        Criterio pp = fac.crearCriterio("baja", "=", 0);
        prods = fac.buscar(Producto.class, pp);
        for(int i=0;i<prods.size();i++){
            Producto sel = (Producto) FabricaEntidad.getInstancia().FabricarEntidad(Producto.class);
            sel = prods.get(i);
            expMetodos.calcularestacionalidad(i, i, sel.getNombreProducto(), diaActual, diaActual, diaActual);
        }
    }

    private Proveedor SeleccionarProveedor(List<Proveedor> pr) {
        //seleccionamos de acuerdo al menor tiempo de demora del proveedor
        int tr1,tr2;
        Proveedor elegido = (Proveedor) FabricaEntidad.getInstancia().FabricarEntidad(Proveedor.class);
        for(int i=1; i< pr.size();i++){
            tr1= pr.get(i-1).getTiempoR();
            tr2= pr.get(i).getTiempoR();
            if(tr2 < tr1)
                elegido = pr.get(i);
            else
                elegido = pr.get(i-1);
        }
        return elegido;
    }
    
    
}
