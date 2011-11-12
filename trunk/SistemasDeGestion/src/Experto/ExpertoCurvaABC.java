/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Agentes.AgenteProducto;
import Interfaces.Demanda;
import Interfaces.InformeABC;
import Interfaces.Producto;
import Pantalla.ventanaGrafica;
import Persistencia.Criterio;
import Persistencia.Fachada;
import Persistencia.ObjetoPersistente;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author natalia
 */
public class ExpertoCurvaABC implements Experto{
    private InformeABC arregloABC[];
    private Demanda temp;// objeto temporal que va a contener la demanda real
    private ArrayList<Object> respBD;
    private Producto prod[];
    private Fachada obFP;
    
      public void iniciar(){
        System.out.println("  Experto Realizar Informe ABC...");
        obFP = Fachada.getInstancia();
       
        this.historial();
        this.calcularDemandaAV(arregloABC);
        this.ordenarArreglo(arregloABC);
        this.calcularDemandaAVA(arregloABC);
        this.calcularCAP(arregloABC);
        this.prepararValores(arregloABC, prod);
        this.guardarProducto(prod);
        this.graficar();
        this.mostrarEnPantallaInformeABC(arregloABC, prod);
        
    }
private void historial(){
       
        respBD = obFP.buscar_todo(Producto.class);
        prod = new Producto[respBD.size()];
        ArrayList<Demanda> temp;

        String atrib = "OIDProducto";
        String oper = "=";

        for (int i = 0; i < prod.length; i++){
            prod[i] = (Producto)respBD.get(i);
        }

        arregloABC = new InformeABC[prod.length];
        for(int i = 0; i < arregloABC.length; i++){
            AgenteProducto ap = (AgenteProducto) prod[i];
            String valor = "" + String.valueOf(ap.getoid()) + "";
            Criterio c1 = new Criterio();
            c1.setAtributo(atrib);
            c1.setOperador(oper);
            c1.setValor(valor);
            temp = obFP.buscar(Demanda.class, c1);
            arregloABC[i] = new InformeABC();
            arregloABC[i].setCodigoDeProducto(String.valueOf(prod[i].getCodigoProducto()));
            arregloABC[i].setCostoUnitario(prod[i].getPrecioVenta());
            arregloABC[i].setDemandaAnual(temp.get(i).getDemandareal());
        }
    }

    private void calcularDemandaAV(InformeABC[] arregloABC){
        for (int i = 0; i < arregloABC.length; i++){
            arregloABC[i].setDemandaAV();
        }
    } 
    
    private void ordenarArreglo(InformeABC[] arregloABC){
        InformeABC auxiliar = new InformeABC();
        boolean bandera = true;
        while (bandera == true){
            bandera = false;            
            for (int i = 0; i < arregloABC.length - 1; i++){
                if (arregloABC[i].getDemandaAV() < arregloABC[i + 1].getDemandaAV()){
                    //bandera = true;
                    auxiliar.setCodigoDeProducto(arregloABC[i].getCodigoDeProducto());
                    auxiliar.setCostoUnitario(arregloABC[i].getCostoUnitario());
                    auxiliar.setDemandaAnual(arregloABC[i].getDemandaAnual());
                    auxiliar.setDemandaAV(arregloABC[i].getDemandaAV());
                    arregloABC[i].setCodigoDeProducto(arregloABC[i + 1].getCodigoDeProducto());
                    arregloABC[i].setCostoUnitario(arregloABC[i + 1].getCostoUnitario());
                    arregloABC[i].setDemandaAnual(arregloABC[i + 1].getDemandaAnual());
                    arregloABC[i].setDemandaAV(arregloABC[i + 1].getDemandaAV());
                    arregloABC[i + 1].setCodigoDeProducto(auxiliar.getCodigoDeProducto());
                    arregloABC[i + 1].setCostoUnitario(auxiliar.getCostoUnitario());
                    arregloABC[i + 1].setDemandaAnual(auxiliar.getDemandaAnual());
                    arregloABC[i + 1].setDemandaAV(auxiliar.getDemandaAV());
                }
            
            }
        }
       
    }

    private void calcularDemandaAVA(InformeABC arregloABC[]){
        double dava = 0;
        for (int i = 0; i < arregloABC.length; i++){
            dava = arregloABC[i].getDemandaAV() + dava;
            System.out.println("DAVA :  " + dava);
            arregloABC[i].setDemandaAVA(dava);
        }
    }

    private void calcularCAP(InformeABC arregloABC[]){
        double dava = arregloABC[arregloABC.length - 1].getDemandaAVA();
        for (int i = 0; i < arregloABC.length; i++){
            arregloABC[i].setPorcentajeAcumulado(10);//Revisar esto (arregloABC[i].getDemandaAVA() * 100 / dava);
        }

    }

    private void prepararValores(InformeABC arregloABC[], Producto prod[]){
        // el 35% de los elementos que estan en la parte superior del arreglo o sea los más importantes
        int a = 35 * arregloABC.length / 100; 
        // el 25% de los elementos que estan en la parte b de la curva
        int b = 25 * arregloABC.length / 100; 

        for (int i = 0; i < a; i++){
            for( int j = 0; j < prod.length; j++){
                if(arregloABC[i].getCodigoDeProducto().equals(prod[j].getCodigoProducto()))
                    prod[j].setClasifABC('a');
            }
        }

        for (int i = a; i < a + b; i++){
            for( int j = 0; j < prod.length; j++){
                if(arregloABC[i].getCodigoDeProducto().equals(prod[j].getCodigoProducto()))
                    prod[j].setClasifABC('b');
            }
        }

        for (int i = a + b ; i < arregloABC.length; i++){
            for( int j = 0; j < prod.length; j++){
                if(arregloABC[i].getCodigoDeProducto().equals(prod[j].getCodigoProducto()))
                    prod[j].setClasifABC('c');
            }
        }


        /*for (int i = 0; i == a; i++){
            clasifABC[i].setCodProducto(arregloABC[i].getCodigoDeProducto());
            clasifABC[i].setClasifABC('a');
        }

        for (int i = a + 1; i == b; i++){
            clasifABC[i].setCodProducto(arregloABC[i].getCodigoDeProducto());
            clasifABC[i].setClasifABC('b');
        }

        for (int i = a + b + 1; i < arregloABC.length; i++){
            clasifABC[i].setCodProducto(arregloABC[i].getCodigoDeProducto());
            clasifABC[i].setClasifABC('c');
        }*/

    }

    private void guardarProducto(Producto prod[]){
        for (int i = 0; i < prod.length; i++){
             obFP.guardar((ObjetoPersistente) prod[i]);
       }
    }

    private void graficar(){
        double[][] datos = new double[2][arregloABC.length];
        double itempercent = 0;
        for(int i = 0; i < datos[0].length; i++){
            datos[0][i] = arregloABC[i].getPorcentajeAcumulado();
            System.out.println(datos[0][i]);
            datos[1][i] = (i+1) * 100 / arregloABC.length;
            System.out.println(datos[1][i]);
        }
        ventanaGrafica ventana = new ventanaGrafica(datos);
    }
      
    private void mostrarEnPantallaInformeABC(InformeABC arregloABC[], Producto prod[]){
        String sFichero = "informeABC.txt";
        File fichero = new File(sFichero);
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
            String s = new String();
            s = "OID Producto  Demanda Anual   Demanda Anual Valorizada    Costo Unitario  DAV Acumulada   Porcentaje Acumulado    Valor ABC";
            bw.write(s);
            for (int i = 0; i < arregloABC.length; i++){
                bw.newLine();
                for (int j = 0; j < arregloABC.length; j++){
                    String codigo1 = arregloABC[i].getCodigoDeProducto();
                    String codigo2 = String.valueOf(prod[j].getCodigoProducto());
                    if(codigo1.equals(codigo2) )
                    s = "  " + arregloABC[i].getCodigoDeProducto() + "            " 
                            + arregloABC[i].getDemandaAnual() + "                    " 
                            + arregloABC[i].getDemandaAV() + "                    " 
                            + arregloABC[i].getCostoUnitario() + "           " 
                            + arregloABC[i].getDemandaAVA() + "                " 
                            + String.valueOf(arregloABC[i].getPorcentajeAcumulado()) 
                            + "%            " + String.valueOf(prod[j].getClasifABC());
                }
                bw.write(s);
            }
        bw.close();

        }catch (IOException ioe){
        }
        //obFP.confirmarTransaccion();
    }
}
