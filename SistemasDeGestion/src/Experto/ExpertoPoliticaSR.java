/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Agentes.AgenteCatalogo;
import Agentes.AgenteDetallePedido;
import Agentes.AgentePedido;
import Agentes.AgentePoliticaStock;
import Agentes.AgenteProducto;
import Agentes.AgenteProveedor;
import Interfaces.Catalogo;
import Interfaces.DayOfYear;
import Interfaces.Demanda;
import Interfaces.DetallePedido;
import Interfaces.Pedido;
import Interfaces.PoliticaStock;
import Interfaces.Producto;
import Interfaces.Proveedor;
import Persistencia.Criterio;
import Persistencia.Fachada;
import Persistencia.FachadaInterna;
import Persistencia.ObjetoPersistente;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;

/**
 *
 * @author duende
 */
public class ExpertoPoliticaSR implements Experto {

    private DayOfYear objDia;
    private Fachada objFP;
    private ArrayList<Proveedor> objProv;
    private Producto[] objProd;
    private PoliticaStock[] objPolStock;
    private float[][] tablaK = new float[9][2];
    private ArrayList<Demanda> objHD;
    private Catalogo objCat;
    //..............................
    private Pedido objPPend;
    private ArrayList<DetallePedido> objDPPend;
    private Object[][] matrix;
    private String[][] matrixII;
    private String fechaSistema;
    private String NroPedido;

    public ExpertoPoliticaSR() {
    }

    public void iniciar(GregorianCalendar fechaS) {
        objFP = Fachada.getInstancia();
        GregorianCalendar fechaActual = new GregorianCalendar();
        //Busco todos los proveedores...........................................
        objProv = objFP.buscar_todo(Proveedor.class);
       /* fechaSistema = String.valueOf(fechaActual.get(GregorianCalendar.DAY_OF_MONTH))
                .concat("-")
                .concat(String.valueOf(fechaActual.get(GregorianCalendar.MONTH) + 1))
                .concat("-")
                .concat(String.valueOf(fechaActual.get(GregorianCalendar.YEAR)));*/
        fechaSistema = String.valueOf(fechaS.get(GregorianCalendar.DAY_OF_MONTH))
                .concat("-")
                .concat(String.valueOf(fechaS.get(GregorianCalendar.MONTH) + 1))
                .concat("-")
                .concat(String.valueOf(fechaS.get(GregorianCalendar.YEAR)));

        //verificarPolitica();
        //......................................................................o
    }

 public void verificarPolitica() {
         //......................................................................
        //Busco todas las polÃ­ticas para identicar el UUID de S,R...............
        String uuidPSR = "";
        ArrayList<PoliticaStock> objAUXII = objFP.buscar_todo(PoliticaStock.class);
        ArrayList<Producto> prod = new ArrayList<Producto>();
        Hashtable demora = new Hashtable();
        GregorianCalendar fechaActual = new GregorianCalendar();

        for (int j = 0; j < objAUXII.size(); j++) {
            objPolStock[j] = objAUXII.get(j);
            //Obtengo el UUID de la polÃ­tica SR.................................
            if (objPolStock[j].getDescripcion().equals("Politica S,R")) {
                uuidPSR = String.valueOf(((AgentePoliticaStock) objPolStock[j]).getoid());
            }
            //..................................................................
        }
        int tiemR = 1;     //Valor por defecto...
        int perActual = 1; //Valor por defecto...
        float diaTemp = 0; //Valor por defecto...
        //Calculo el periodo para cada proveedor................................
        for (int i = 0; i < objProv.size(); i++) {
            tiemR = objProv.get(i).getTiempoR();
            perActual = objProv.get(i).getPeriodoActual();
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
            //..................................................................
            if (periodo != perActual) {
                System.out.println("");
                System.out.println("  Ejecutando Politica(S,R) para el Proveedor : " + objProv.get(i).getNombre());
                String datos = new String();
                datos = uuidPSR;
                //Busco en el catalogo los productos del proveedor
                Criterio c1 = objFP.crearCriterio("OIDProveedor", "=", ((AgenteProveedor) objProv.get(i)).getoid());
                //Criterio c2 = objFP.crearCriterio("politica", "=", uuidPSR);
                ArrayList<Catalogo> cat = objFP.buscar(Catalogo.class, c1);
                //traigo todos los productos del catalogo             
                for (int h = 0; h < cat.size(); h++) {
                    Producto aux = (Producto) FabricaEntidad.getInstancia().FabricarEntidad(Producto.class);
                    aux = (Producto) FachadaInterna.getInstancia().buscarOID(Producto.class, ((AgenteCatalogo) cat.get(h)).getOIDProducto());
                    AgenteProducto Agaux = (AgenteProducto) aux;
                    if (Agaux.getOIDPolitica().equals("1")) {
                        prod.add(aux);
                        demora.put(aux.getDescripcionProducto(), cat.get(h).getDemora());
                    }
                }
                //armo el pedido si hay productos para armar el pedido

                if(!prod.isEmpty()){
                Pedido objPP = (Pedido) FabricaEntidad.getInstancia().FabricarEntidad(Pedido.class);  
                //intento armar un numero de pedido único
                NroPedido =String.valueOf(fechaActual.get(GregorianCalendar.DAY_OF_MONTH))
                    .concat(String.valueOf(fechaActual.get(GregorianCalendar.MONTH)+1))
                    .concat(String.valueOf(fechaActual.get(GregorianCalendar.YEAR)).substring(2,4))
                    .concat(String.valueOf(fechaActual.get(GregorianCalendar.HOUR)))
                    .concat(String.valueOf(fechaActual.get(GregorianCalendar.MINUTE)));
                objPP.setNroPedido(NroPedido);
                ((AgentePedido)objPP).setOIDProveedor(((AgenteProveedor)objProv.get(i)).getoid());
                objPP.setFechaEmision(fechaSistema);
                //calcular el tamaño de la orden por producto
                for(int z=0;z<prod.size();z++){
                   DetallePedido objDP = (DetallePedido) FabricaEntidad.getInstancia().FabricarEntidad(DetallePedido.class);
                   Producto aux = prod.get(z);
                   System.out.println("");
                   System.out.println("  Inicio Calculo para el producto: "+aux.getDescripcionProducto());
                   int R = objProv.get(i).getTiempoR();
                   float nivelServicio = aux.getNivelServicio();
                   float StockDisp = aux.getStock().getCantidad() + aux.getStock().getStockPendiente();
                   
                   //Prediccion de la demanda
                   c1 = objFP.crearCriterio("OIDProducto", "=",((AgenteProducto)aux).getoid());
                   Criterio c2 = objFP.crearCriterio("periodo", "=", String.valueOf(periodo));
                   Criterio c3 = objFP.crearCriterio("anio","=",String.valueOf(fechaActual.get(GregorianCalendar.YEAR)-1));
                   Criterio co = objFP.crearCriterioCompuesto(c1, "=", c2);
                   Criterio cco = objFP.crearCriterioCompuesto(co, "=", c3);
                   System.out.println("Criterio: "+cco.getAtributo()+cco.getOperador()+cco.getValor().toString());
                   objHD = objFP.buscar(Demanda.class, cco);
                   float demanda = (float) 0.0; 
                   float MSE = (float) 0.0;
                   for(int d=0; d < objHD.size();d++){
                       demanda = (float) (demanda + objHD.get(d).getDemandapronosticada());
                       MSE = (float) objHD.get(d).getMse();
                   }
                   //busco la demora en el hashtable para este producto
                    int te = Integer.parseInt(demora.get(aux.getDescripcionProducto()).toString());
                   //Registro el pedido pendiente
                    int loteS = getLoteS(nivelServicio,demanda,R,te,MSE, (int) StockDisp,aux);
                    ((AgenteDetallePedido)objDP).setOIDPedido(((AgentePedido)objPP).getoid());
                    ((AgenteDetallePedido)objDP).setOIDProducto(((AgenteProducto)aux).getoid());
                    ((AgenteDetallePedido)objDP).setCantidad(loteS); 
                    objPP.setDetallePedido(objDP);                   
                    }
                    //guardar un pedido por proveedor - la cabecera deberia guardar el detalle
                    objPP.setPendiente(1);
                    objFP.guardar((ObjetoPersistente) objPP);

                }
            }
        }
    }

 public Object[][] getProductosPrev(int posProv) {
        ArrayList<Pedido> PedPen;
        String prov;
        prov = String.valueOf((((AgenteProveedor) objProv.get(posProv)).getoid()));
        //Busco todos los productos............................................
        Criterio c = objFP.crearCriterio("OIDProveedor", "=", prov);
        ArrayList<Catalogo> cats = objFP.buscar(Catalogo.class, c);
        ArrayList<Producto> productos = new ArrayList<Producto>();
        for (int f = 0; f < cats.size(); f++) {
            AgenteCatalogo ac = (AgenteCatalogo) cats.get(f);
            Producto aux = (Producto) FachadaInterna.getInstancia().buscarOID(Producto.class, ac.getOIDProducto());
            boolean add = productos.add(aux);
        }
        //......................................................................
        matrix = new Object[productos.size()][5];
        matrixII = new String[productos.size()][5];
        //para cada producto
        for (int i = 0; i < productos.size(); i++) {
            //objProd[i] = (Producto)objAUX.get(i);
            //..................................................................
            matrix[i][0] = productos.get(i).getCodigoProducto();
            matrix[i][1] = productos.get(i).getDescripcionProducto();
            matrix[i][2] = productos.get(i).getPrecioCompra();
            //cantidad en stock
            int stockActual = productos.get(i).getStock().getCantidad();
            int stockPendiente= productos.get(i).getStock().getStockPendiente();
            matrix[i][3] = stockActual + stockPendiente ;
            //..................................................................            
            Criterio c2 = objFP.crearCriterio("OIDProveedor", "=", prov);
            Criterio c4 = objFP.crearCriterio("pend", "=", "1");
            Criterio c5 = objFP.crearCriterioCompuesto(c2, "and", c4);
            PedPen = objFP.buscar(Pedido.class, c5);
            //Criterio c1 = objFP.crearCriterio("OIDProducto", "=", ((AgenteProducto)productos.get(i)).getoid());
            
            for(int pp=0; pp<PedPen.size();pp++){
            //traigo los detalles de ese pedido
            objDPPend = (ArrayList<DetallePedido>) PedPen.get(pp).getDetallePedido();
            matrix[i][4] = String.valueOf(0);
            for(int d=0; d < objDPPend.size();d++){
                if(objDPPend.get(d).getProducto().getCodigoProducto() == productos.get(i).getCodigoProducto())
                    matrix[i][4] = objDPPend.get(d).getCantidad();
                
            }
            matrixII[i][0] = prov; //UUID Proveedor...
            matrixII[i][1] = ((AgenteProducto)productos.get(i)).getoid(); //UUID Producto...
            matrixII[i][2] = String.valueOf(productos.get(i).getCodigoProducto());
            matrixII[i][3] = ((AgentePedido)PedPen.get(pp)).getoid(); //UUID Pedido Pendiente... 
            matrixII[i][4] = String.valueOf(0);
            }
        }
        return matrix;
    }

    public void setPedidoManual(String[][] tabla) {
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < matrixII.length; j++) {
                if (tabla[i][0].equals(matrixII[j][2])) {
                    if (Integer.parseInt(tabla[i][1]) == Integer.parseInt(String.valueOf(matrixII[j][4]))) {
                        //Paso a entregado el pedido
                        Pedido objPPaux = (Pedido) FachadaInterna.getInstancia().buscarOID(Pedido.class, matrixII[j][3]);
                        objPPaux.setFechaEntrega(String.valueOf(GregorianCalendar.DATE));
                        objPPaux.setPendiente(0);
                        objFP.guardar((ObjetoPersistente) objPPaux);
                        //Agrego al stock del producto
                        Producto objPd = (Producto) FachadaInterna.getInstancia().buscarOID(Producto.class, matrixII[j][1]);
                        int auxSP = objPd.getStock().getStockPendiente();
                        auxSP = auxSP + Integer.parseInt(matrixII[j][4]);
                        objPd.getStock().setStockPendiente(auxSP);
                        objFP.guardar((ObjetoPersistente) objPd.getStock());
                    } else {
                        //Actualizo el Lote del Pedido Pendiente...
                        DetallePedido objDPPaux = (DetallePedido) FachadaInterna.getInstancia().buscarOID(DetallePedido.class, matrixII[j][3]);
                        objDPPaux.setCantidad((Integer.parseInt(String.valueOf(matrixII[j][4])) - Integer.parseInt(tabla[i][1])));
                        objFP.guardar((ObjetoPersistente) objDPPaux);
                        Pedido objPPaux = (Pedido) FachadaInterna.getInstancia().buscarOID(Pedido.class, matrixII[j][3]);
                        objPPaux.setFechaEntrega(String.valueOf(GregorianCalendar.DATE));
                        objPPaux.setPendiente(1);
                        objFP.guardar((ObjetoPersistente) objPPaux);
                        //......................................................
                        Producto objPd = (Producto) FachadaInterna.getInstancia().buscarOID(Producto.class, matrixII[j][1]);
                        int auxSP = objPd.getStock().getStockPendiente();
                        auxSP = auxSP + Integer.parseInt(tabla[i][1]);
                        objPd.getStock().setStockPendiente(auxSP);
                        objFP.guardar((ObjetoPersistente) objPd);

                    }
                }
            }
        }
    }

    private int getLoteS(float nivelServ, float demanda, int R, int te, float desvEstandar, int stockDisp,Producto p) {
        System.out.println("Nivel Servicio:"+ nivelServ);
        int k = (int) getK(nivelServ);
        System.out.println("K = " + k + "\t Demanda Pronosticada: " + demanda + "\t R: " + R + "\t te: " + te + "\t Oe: " + desvEstandar + "\t Stock Disponible: " + stockDisp);

        int Ue = (int) (demanda * (R + te) / 52);
        System.out.println("  Ue(R+te): " + Ue + " unidades");

        float Oe = desvEstandar * (float) (R + te) / 52;
        System.out.println(" Oe(R+te): " + Oe + " unidades");

        int S = (int) (Ue + (k * Oe));
        System.out.println("  S: " + S + " unidades");
        p.getStock().setCantidadMinima(S);
        int total = 0;
        if(S > 0)
            total = S - stockDisp;
        
        System.out.println("  S - Stock Disponible: " + total + " unidades");

        return total;
    }

     /*
     //PREDICCION DE LA DEMANDA DURANTE UN TIEMPO R (ue).......
     * Demanda (Demandan Pronostica) // Tabla HistorialDemanda.  $$$$$
     * R (tiempoR) // Tabla proveedor.                           $$$$$
     * te = (Demora) //Tabla catalogo.                           $$$$$

     Nivel de Servicio // Tabla Productos. Con esto obtengo K.   $$$$$

     //DesviaciÃ³n estandar (oe).................................
     * oe = Raiz(MSE) // Tabla HistorialDemanda.                  $$$$$
     * R =  (tiempoR) // Tabla proveedor.                         $$$$$
     * te = (Demora) //Tabla catalogo.                            $$$$$

     Stock Disponible = Cant_Stock + StockPend //Tabla Productos. $$$$$
     */
    private float getK(float nivelServ){
       Hashtable factor = new Hashtable();
        factor.put("0.0", 0.0);
        factor.put("0.25", 0.0);
        factor.put("0.3", 0.0);
        factor.put("0.5", 0.0);
        factor.put("0.6", 0.25);
        factor.put("0.7", 0.52);
        factor.put("0.8", 0.84);
        factor.put("0.9", 1.28);
        factor.put("0.95",1.64);
        factor.put("0.975", 1.96);
        factor.put("0.999", 3.08);
        factor.put("0.9998",3.0);
        factor.put("1.0",3.0);
        if(Double.isNaN(nivelServ))
            nivelServ =0;
        float rtaK = 0;
        String key = String.valueOf(nivelServ);
        System.out.println("nivel de servicio convertido a string:"+key);
        double k = (Double) factor.get(key);
        if(k!=0.0)
            rtaK = (float) k;
        else
            rtaK = (float) 0.0;
        return rtaK;
    }

 public int calcularLote(Producto p, Proveedor P) {               
        GregorianCalendar fechaActual = new GregorianCalendar();
        objFP = Fachada.getInstancia();
        int tiemR = 1;     //Valor por defecto...
        int perActual = 1; //Valor por defecto...
        float diaTemp = 0; //Valor por defecto...
        float demanda = (float) 0.0; 
        float MSE = (float) 0.0;
        tiemR = P.getTiempoR();
        perActual = P.getPeriodoActual();
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
        //busco la demanda pronosticada del producto
        //busco el tiempo de reposición del proveedor
        //busco el tiempo de demora en el catálogo
        //calculo el stock disponible
        /*Producto aux = prod.get(z);*/
          System.out.println("Producto: "+p.getNombreProducto());
          System.out.println("Proveedor: "+P.getNombre());
          int R = P.getTiempoR();
          double nivelServicio = p.getNivelServicio();
          float StockDisp = p.getStock().getCantidad() + p.getStock().getStockPendiente();
       //busco la demanda para el producto - año - periodo
          Criterio c1 = objFP.crearCriterio("OIDProducto", "=",((AgenteProducto)p).getoid());
          Criterio c2 = objFP.crearCriterio("periodo", "=", String.valueOf(periodo));
          Criterio c3 = objFP.crearCriterio("anio","=",String.valueOf(fechaActual.get(GregorianCalendar.YEAR)-1));
          Criterio co = objFP.crearCriterioCompuesto(c1, "AND", c2);
          Criterio cco = objFP.crearCriterioCompuesto(co, "AND", c3);
          System.out.println("Criterio: "+cco.getAtributo()+cco.getOperador()+cco.getValor().toString());
          objHD = objFP.buscar(Demanda.class, cco);
          
          for(int d=0; d < objHD.size();d++){
              demanda = (float) (demanda + objHD.get(d).getDemandapronosticada());
              MSE = (float) objHD.get(d).getMse();
                   }
          Criterio k1 = objFP.crearCriterio("OIDProducto", "=", ((AgenteProducto)p).getoid());
          Criterio k2 = objFP.crearCriterio("OIDProveedor","=",((AgenteProveedor)P).getoid());
          Criterio k3 = objFP.crearCriterioCompuesto(k1,"AND",k2);
          ArrayList<Catalogo> cat = objFP.buscar(Catalogo.class,k3);          
           //busco la demora en el hashtable para este producto
          int te = 0;
          if(cat.size()!=0){
              int i = cat.size()-1;
              te = cat.get(i).getDemora();
          }           
           //Registro el pedido pendiente
            ExpertoProducto expProd = (ExpertoProducto) FabricaExperto.getInstancia().FabricarExperto("ExpertoProducto");
            if(nivelServicio == 0){
                //nivelServicio =  expProd.CalcularNivelServicio(p, P);
                nivelServicio =  expProd.CalcularNivelServicio(p);
                p.setNivelServicio((float)nivelServicio);
                System.out.println("Nivel Servicio segun experto producto: "+nivelServicio);
            }
            
            System.out.println("Nivel Servicio segun experto producto: "+nivelServicio);
            System.out.println("Desviacion Estándar: "+MSE);
            System.out.println("Stock Disponible: "+StockDisp);
            System.out.println("Demanda Pronosticada: "+demanda);
            System.out.println("te: "+te);
            
            int loteS = (int) getLoteS((float)nivelServicio,demanda,R,te,MSE, (int) StockDisp,p);
            objFP.guardar((ObjetoPersistente)p);
          return loteS;
    }
 
    public void automatizado(ArrayList<Producto> prods){
        boolean pp = false;
        Date fechaSistema = new Date();
        ArrayList<Producto> prods2 = new ArrayList();
        Hashtable lotes = new Hashtable();
        ArrayList<Proveedor> provs = new ArrayList();
        ArrayList<Catalogo> cats = new ArrayList();
        Fachada fach = Fachada.getInstancia();
        
        for(int i=0;i<prods.size();i++){
            //verifico que el producto sea de la politica
          if(prods.get(i).getPolitica().equals("1")){
                AgenteProducto ap = (AgenteProducto) prods.get(i);
                Criterio c1 = fach.crearCriterio("OIDProducto", "=", ap.getoid());
                //busco el catalogo correcto
                cats = fach.buscar(Catalogo.class, c1);
                for(int c=1;c<cats.size();c++){
                    AgenteCatalogo ac = (AgenteCatalogo) cats.get(c);
                    //busco en el catalogo los proveedores correctos
                    Proveedor prov = (Proveedor) FabricaEntidad.getInstancia().FabricarEntidad(Proveedor.class);
                    prov = (Proveedor)FachadaInterna.getInstancia().buscarOID(Proveedor.class, ac.getOIDProveedor());
                   //si no existe el proveedor lo agrego
                    if(!provs.contains(prov))
                        provs.add(prov);
                    int lote = calcularLote(prods.get(i),prov);
                    prods2.add(prods.get(i));
                    lotes.put(prods.get(i).getCodigoProducto(), lote);
                }
            }
        }
        ExpertoRealizarPedido expPedido = (ExpertoRealizarPedido) FabricaExperto.getInstancia().FabricarExperto("ExpertoRealizarPedido");
        for(int p=0; p< provs.size();p++){
            //armo el pedido si se alcanzo el tiempo de reposición
            if(lotes.isEmpty()) break;
            if(prods2.isEmpty())break;
            if(verificarR(provs.get(p)))
            expPedido.CrearPedidoPendiente(fechaSistema, provs.get(p), prods2, lotes);
        }
    }
    
    public boolean verificarR(Proveedor P){
        GregorianCalendar fechaActual = new GregorianCalendar();
        int tiemR = 1;     //Valor por defecto...
        int perActual = 1; //Valor por defecto...
        float diaTemp = 0; //Valor por defecto...
        float demanda = (float) 0.0; 
        float MSE = (float) 0.0;
        tiemR = P.getTiempoR();
        perActual = P.getPeriodoActual();
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
            if (diaTemp - periodo == 0) {
                return true;
            }
            else
                return false;
    }
}
