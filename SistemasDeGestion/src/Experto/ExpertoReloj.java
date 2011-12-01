/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Agentes.AgenteCatalogo;
import Agentes.AgenteProducto;
import Agentes.AgenteProveedor;
import Controlador.ControladorPrincipal;
import Interfaces.Catalogo;
import Interfaces.DayOfYear;
import Interfaces.DetallePedido;
import Interfaces.Parametros;
import Interfaces.Pedido;
import Interfaces.Producto;
import Interfaces.Proveedor;
import Interfaces.Stock;
import Pantalla.PantallaPrincipal;
import Persistencia.Criterio;
import Persistencia.Fachada;
import Persistencia.FachadaInterna;
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
    private ControladorPrincipal ppal;
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
    public void iniciar(ControladorPrincipal pant){
        ppal = pant;
        fechaSistema = pant.fechaSistema;
        //calculoDemanda();
        buscarProductosPuntoPedido(); 
        buscarPedidosPendientes();
               
    }
    /*Este experto carga el informe inicial o bandeja de entrada de pedidos pendientes*/
    /*carga los avisos de productos por debajo del stock*/
    /*Manda a calcular demandas y genera nuevos pedidos*/

    private void buscarPedidosPendientes() {
        DefaultTableModel md = new DefaultTableModel();
        ppal.getPantallaPrincipal().getBandejaEntrada().removeAll();
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
        ppal.getPantallaPrincipal().getBandejaEntrada().setModel(md);
    }

    private boolean buscarProductosPuntoPedido() {
        ExpertoPoliticaSQ expPSQ = (ExpertoPoliticaSQ) FabricaExperto.getInstancia().FabricarExperto("ExpertoPoliticaSQ");
        ExpertoPoliticaSR expPSR = (ExpertoPoliticaSR) FabricaExperto.getInstancia().FabricarExperto("ExpertoPoliticaSR");
        DefaultTableModel tprod = new DefaultTableModel();
        ppal.getPantallaPrincipal().getBandejaProductos().removeAll();
        Criterio p1 = fac.crearCriterio("baja", "=",0);
        ArrayList<Producto> pp = new ArrayList();
        List<Proveedor> pr;        
        ArrayList<Proveedor> praux = new ArrayList();
        Hashtable prodaux = new Hashtable();
        tprod.addColumn("CodProducto"); 
        tprod.addColumn("Producto"); 
        tprod.addColumn("cantidad");   
        tprod.addColumn("cantidad minima");  
        tprod.addColumn("clasificacion");  
        
        //buscamos los productos por debajo del mínimo y mostramos
        Proveedor prov = (Proveedor) FabricaEntidad.getInstancia().FabricarEntidad(Proveedor.class);
        prods = fac.buscar(Producto.class, p1);
        //separo los productos que alcanzaron el mínimo
        for(int i=0;i<prods.size();i++){
            Criterio cp = fac.crearCriterio("baja", "=", 0);
            Criterio pP = fac.crearCriterio("OIDProducto", "=", ((AgenteProducto)prods.get(i)).getoid());
            Criterio cx = fac.crearCriterioCompuesto(cp,"AND", cp);
            ArrayList <DetallePedido> Dpen = fac.buscar(DetallePedido.class, cp);
            if(!Dpen.isEmpty()) //existen pedidos pendientes para el producto
                prods.remove(i);
        }
        //if(diaActual != 28) return false;
        
        expPSQ.automatizado(prods);
        expPSR.automatizado(prods);
        for(int i=0; i< prods.size();i++){
            Stock s = prods.get(i).getStock();
            int ss = s.getCantidadMinima();
            int actual = s.getCantidad();
            if(actual < ss){
                pp.add(prods.get(i));
                Object[] newRow = new Object[5];
                newRow[0] = prods.get(i).getCodigoProducto();
                newRow[1] = prods.get(i).getNombreProducto();
                newRow[2] = prods.get(i).getStock().getCantidad();
                newRow[3] = prods.get(i).getStock().getCantidadMinima();
                newRow[4] = prods.get(i).getClasifABC();
                tprod.addRow(newRow);
            }
        }
        ppal.getPantallaPrincipal().getBandejaProductos().setModel(tprod);
        
        
        //si tenemos productos con stock bajo y es fin de mes        
        /*for(int j=0; j<pp.size();j++){
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
            //producto sobre el que trabajamos
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
        */
        return true;
    }

    private void calculoDemanda() {
        expMetodos = (ExpertoMetodos) FabricaExperto.getInstancia().FabricarExperto("ExpertoMetodos") ;
        Criterio Pr = fac.crearCriterio("baja", "=", 0);
        ArrayList<Proveedor> APR = fac.buscar(Proveedor.class, Pr);
        
        for(int j = 0; j<APR.size();j++){
            AgenteProveedor agPr= (AgenteProveedor) APR.get(j);
            DayOfYear objDia = new DayOfYear();
            int diaDelAnio = objDia.getDiaDelAnio();
            int diasTotalAnio = (objDia.esBisiesto()) ? 366 : 365;
            int tiemR = 1;     //Valor por defecto...
            int perActual = 1; //Valor por defecto...
            float diaTemp = 0; //Valor por defecto...
            String periodoinicial = "01012010";
            String periodofinal = "31122010";
            tiemR = APR.get(j).getTiempoR();
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
            Criterio cc = fac.crearCriterio("OIDProveedor","=", agPr.getoid());
            ArrayList<Catalogo> cat = fac.buscar(Catalogo.class, cc);
          for(int c=0; c < cat.size();c++){ 
            AgenteCatalogo agC = (AgenteCatalogo) cat.get(c);
            Producto prod = (Producto) FabricaEntidad.getInstancia().FabricarEntidad(Producto.class);
            prod = (Producto) FachadaInterna.getInstancia().buscarOID(Producto.class,agC.getOIDProducto());
            ArrayList<Parametros> pars = Fachada.getInstancia().buscar_todo(Parametros.class);
            Parametros par = (Parametros) FabricaEntidad.getInstancia().FabricarEntidad(Parametros.class);
            /*Deberia calcular demanda*/
            /*calcularestacionalidad(double alfa, int valorperiodo, String productoSeleccionado, int valorperiodoinicial, int valorperiodofinal, int periodosapredecir) {*/
            //periodo inicial = 01-01-2010
            //periodo final = 28 del mes que sea
            //periodos a predecir = 1 (mes siguiente)
            //buscar en parametro
            //expMetodos.calcularestacionalidad(par.getAlfa(), periodo,prod.getNombreProducto(),'', periodo, periodo);
          }
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
