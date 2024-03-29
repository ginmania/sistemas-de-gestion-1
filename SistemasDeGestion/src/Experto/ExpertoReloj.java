/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Agentes.AgenteCatalogo;
import Agentes.AgenteProducto;
import Agentes.AgenteProveedor;
import Controlador.ControladorPrincipal;
import Excepciones.NoProductoExcepcion;
import Interfaces.Catalogo;
import Interfaces.DayOfYear;
import Interfaces.DetallePedido;
import Interfaces.Parametros;
import Interfaces.Pedido;
import Interfaces.Producto;
import Interfaces.Proveedor;
import Interfaces.Stock;
import Metodo.Periodo;
import Pantalla.PantallaPrincipal;
import Persistencia.ConvertirFechas;
import Persistencia.Criterio;
import Persistencia.Fachada;
import Persistencia.FachadaInterna;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
        Date dia = new Date();
        try {
            calculoDemanda();
        } catch (NoProductoExcepcion ex) {
            Logger.getLogger(ExpertoReloj.class.getName()).log(Level.SEVERE, null, ex);
        }
        buscarProductosPuntoPedido(dia.getDay()); 
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

    private boolean buscarProductosPuntoPedido(int diaActual) {
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
        if(diaActual != 28) return false;
        
        expPSQ.automatizado(prods);
        expPSR.automatizado(prods);
        for(int i=0; i< prods.size();i++){
            Stock s = prods.get(i).getStock();
            int ss = s.getCantidadMinima();
            int actual = s.getCantidad();
                pp.add(prods.get(i));
                Object[] newRow = new Object[5];
                newRow[0] = prods.get(i).getCodigoProducto();
                newRow[1] = prods.get(i).getNombreProducto();
                newRow[2] = prods.get(i).getStock().getCantidad();
                newRow[3] = prods.get(i).getStock().getCantidadMinima();
                newRow[4] = prods.get(i).getClasifABC();
                tprod.addRow(newRow);
        }
        ppal.getPantallaPrincipal().getBandejaProductos().setModel(tprod);
        
        return true;
    }

    private void calculoDemanda() throws NoProductoExcepcion {
        expMetodos = (ExpertoMetodos) FabricaExperto.getInstancia().FabricarExperto("ExpertoMetodos") ;
        Criterio Pr = fac.crearCriterio("baja", "=", 0);
        ArrayList<Proveedor> APR = fac.buscar(Proveedor.class, Pr);
        int diferenciadeperiodos, valorperiodoinicial, valorperiodofinal, periodosapredecir;
        Date perinicial,perfinal;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        expMetodos.buscarProducto();
        for(int j = 0; j<APR.size();j++){
            AgenteProveedor agPr= (AgenteProveedor) APR.get(j);
            Periodo periodo = new Periodo();
            String periodoinicial = "01012010";
            String periodofinal = "31122010";
            Criterio cc = fac.crearCriterio("OIDProveedor","=", agPr.getoid());
            ArrayList<Catalogo> cat = fac.buscar(Catalogo.class, cc);
          for(int c=0; c < cat.size();c++){ 
                try {
                    AgenteCatalogo agC = (AgenteCatalogo) cat.get(c);
                    Producto prod = (Producto) FabricaEntidad.getInstancia().FabricarEntidad(Producto.class);
                    prod = (Producto) FachadaInterna.getInstancia().buscarOID(Producto.class,agC.getOIDProducto());
                    ArrayList<Parametros> pars = Fachada.getInstancia().buscar_todo(Parametros.class);
                    Parametros par = (Parametros) FabricaEntidad.getInstancia().FabricarEntidad(Parametros.class);
                    
                    //double alfa = 0.1;
                    Date fechainicio = formato.parse("2010-01-01");
                    Date fechafin = formato.parse("2010-05-31");
                    
                    valorperiodoinicial = periodo.getPeriodos(fechainicio, fechafin)+1;
                    diferenciadeperiodos = periodo.getPeriodos(fechainicio, fechafin);
                    perinicial = ConvertirFechas.stringAFecha(periodoinicial);
                    perfinal = ConvertirFechas.stringAFecha(periodofinal);
                    valorperiodofinal = valorperiodoinicial + diferenciadeperiodos;
                    
                    expMetodos.calcularestacionalidad(pars.get(0).getAlfa(),diferenciadeperiodos,prod.getNombreProducto(),valorperiodoinicial, valorperiodofinal,3);
                    
                } catch (Exception ex) {
                    Logger.getLogger(ExpertoReloj.class.getName()).log(Level.SEVERE, null, ex);
                    Logger.getLogger(ExpertoReloj.class.getName()).log(Level.SEVERE, null, ex);
                }
          }
          expMetodos.guardar();
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

    public void simulacion() {
        try {
            calculoDemanda();
        } catch (NoProductoExcepcion ex) {
            Logger.getLogger(ExpertoReloj.class.getName()).log(Level.SEVERE, null, ex);
        }
        buscarProductosPuntoPedido(28); 
        buscarPedidosPendientes();
        PantallaPrincipal pant = ppal.getPantallaPrincipal();
        JOptionPane.showMessageDialog(pant, "Simulación terminada!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}
