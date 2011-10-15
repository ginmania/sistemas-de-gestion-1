/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experto;

import Excepciones.NoProductoExcepcion;
import Interfaces.Demanda;
import Interfaces.Producto;
import Metodo.Estacionalidad;
import Metodo.Simple;
import Metodo.Tendencia;
import Persistencia.Criterio;
import Persistencia.Fachada;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author diego
 */
public class ExpertoMetodos implements Experto {

    private static class OrdenarDemandas implements Comparator<Demanda> {

        public int compare(Demanda t, Demanda t1) {
            return new Integer(t.getPeriodo()).compareTo(t1.getPeriodo());
        }
    }
    private int cantpersimple = 0;
    private int cantpertendencia = 0;
    private int cantperestacionalidad = 0;
    private List<Producto> vectorDTOProducto = new ArrayList();
    private double[] resultadosimple;
    private double[] demandarealsimple;
    private double[] resultadotendencia;
    private double[] demandarealtendencia;
    private double[] resultadoestacionalidad;
    private double[] demandarealestacionalidad;
    private double[][] demandahistorica;
    private int[] periodosimple;
    private int[] periodotendencia;
    private int[] periodoestacionalidad;
    private double a = 0.1;
    private double b = 0.1;
    private double g = 0.1;

    public ExpertoMetodos() {
    }

    public List<Producto> buscarProducto() throws NoProductoExcepcion {
        vectorDTOProducto.clear();
        List<Producto> productos = null;
        Criterio c1 = new Criterio();
        Criterio c2 = new Criterio();
        Criterio c3 = new Criterio();
        //Criterio para buscar todos los productos vigentes
        c1 = Fachada.getInstancia().crearCriterio("1", "=", 1);
        c2 = Fachada.getInstancia().crearCriterio("baja", "=", 0);
        c3 = Fachada.getInstancia().crearCriterioCompuesto(c1, "AND", c2);
        productos = Fachada.getInstancia().buscar(Producto.class, c3);
        if (productos.isEmpty()) {
            throw new NoProductoExcepcion();
        }
        for (Producto producto : productos) {

            vectorDTOProducto.add(producto);
        }
        return vectorDTOProducto;
    }

    public String[][] calcularsimple(double alfa, int valorperiodo, String productoSeleccionado, int valorperiodoinicial, int valorperiodofinal, int periodosapredecir) {
        a = alfa;
        //  System.out.println("Cantidad de Periodos a predecir:   " + periodosapredecir);
        //System.out.println("SUMA :  " + (periodosapredecir + valorperiodofinal - valorperiodoinicial));
        System.out.println("Valor Inicial:  " + valorperiodoinicial + "  Valor Final:  " + valorperiodofinal);
        String seleccion = productoSeleccionado;
        demandarealsimple = new double[1 + valorperiodofinal - valorperiodoinicial];
        periodosimple = new int[periodosapredecir + (valorperiodofinal - valorperiodoinicial)];
        cantpersimple = valorperiodofinal - valorperiodoinicial;
        String valorsimple[][] = new String[cantpersimple + 1][2];
        for (Producto producto : vectorDTOProducto) {
            List<Demanda> listaDemanda = producto.getDemandas();
            Collections.sort(listaDemanda, new OrdenarDemandas());
            if (producto.getNombreProducto().equalsIgnoreCase(seleccion)) {
                for (int k = valorperiodoinicial; k < valorperiodofinal + 1; k++) {
                    demandarealsimple[k - valorperiodoinicial] = listaDemanda.get(k - 1).getDemandareal();
                    periodosimple[k - valorperiodoinicial] = listaDemanda.get(k - 1).getPeriodo();
                    valorsimple[k - valorperiodoinicial][1] = "Demanda real:  " + demandarealsimple[k - valorperiodoinicial] + "   En el periodo: " + periodosimple[k - valorperiodoinicial];
                }
            }
        }
        Simple metodosimple = new Simple();
        resultadosimple = metodosimple.calcularSimple(demandarealsimple, a, periodosapredecir);
        for (int i = 1; i < periodosapredecir + 1; i++) {
            resultadosimple[i] = redondear(resultadosimple[i], 2);
            valorsimple[i - 1][0] = "Demanda Pronosticada: " + String.valueOf(resultadosimple[i] + "  En el periodo:  " + (i + valorperiodofinal));
        }
        return valorsimple;
    }

    public String[][] calculartendencia(double alfa, int valorperiodo, String productoSeleccionado, int valorperiodoinicial, int valorperiodofinal, int periodosapredecir) {
        a = alfa;
        b = alfa / 2;
        String seleccion = productoSeleccionado;
        demandarealtendencia = new double[1 + valorperiodofinal - valorperiodoinicial];
        periodotendencia = new int[periodosapredecir + (valorperiodofinal - valorperiodoinicial)];
        cantpertendencia = valorperiodofinal - valorperiodoinicial;
        String valortendencia[][] = new String[cantpertendencia + 1][2];
        for (Producto producto : vectorDTOProducto) {
            List<Demanda> listaDemanda = producto.getDemandas();
            Collections.sort(listaDemanda, new OrdenarDemandas());
            if (producto.getNombreProducto().equalsIgnoreCase(seleccion)) {
                for (int k = valorperiodoinicial; k < valorperiodofinal + 1; k++) {
                    demandarealtendencia[k - valorperiodoinicial] = listaDemanda.get(k - 1).getDemandareal();
                    periodotendencia[k - valorperiodoinicial] = listaDemanda.get(k - 1).getPeriodo();
                    valortendencia[k - valorperiodoinicial][1] = "Demanda real:  " + demandarealtendencia[k - valorperiodoinicial] + "   En el periodo: " + periodotendencia[k - valorperiodoinicial];
                }
            }
        }
        Tendencia metodotendencia = new Tendencia();
        resultadotendencia = metodotendencia.calcularTendencia(demandarealtendencia, a, b, cantpertendencia, periodosapredecir);
        for (int i = 1; i < periodosapredecir + 1; i++) {
            resultadotendencia[i] = redondear(resultadotendencia[i], 2);
            valortendencia[i - 1][0] = "Demanda Pronosticada: " + String.valueOf(resultadotendencia[i] + "  En el periodo:  " + (i + valorperiodofinal));
        }
        return valortendencia;
    }

    public String[][] calcularestacionalidad(double alfa, int valorperiodo, String productoSeleccionado, int valorperiodoinicial, int valorperiodofinal, int periodosapredecir) {
        a = alfa;
        g = alfa * 2;
        System.out.println("Alfa:  " + a + "  gama: " + g);
        System.out.println("Cantidad de Periodos a predecir:   " + periodosapredecir);
        System.out.println("SUMA :  " + (periodosapredecir + valorperiodofinal - valorperiodoinicial));
        System.out.println("Valor Inicial:  " + valorperiodoinicial + "  Valor Final:  " + valorperiodofinal);
        String seleccion = productoSeleccionado;
        demandarealestacionalidad = new double[1 + valorperiodofinal - valorperiodoinicial];
        demandahistorica = new double[1][13];
        periodoestacionalidad = new int[periodosapredecir + (valorperiodofinal - valorperiodoinicial)];
        cantperestacionalidad = valorperiodofinal - valorperiodoinicial;
        String valorestacionalidad[][] = new String[cantperestacionalidad + 1][2];
        for (Producto producto : vectorDTOProducto) {
            List<Demanda> listaDemanda = producto.getDemandas();
            Collections.sort(listaDemanda, new OrdenarDemandas());
            if (producto.getNombreProducto().equalsIgnoreCase(seleccion)) {
                for (int k = valorperiodoinicial; k < valorperiodofinal + 1; k++) {
                    demandarealestacionalidad[k - valorperiodoinicial] = listaDemanda.get(k - 1).getDemandareal();
                    periodoestacionalidad[k - valorperiodoinicial] = listaDemanda.get(k - 1).getPeriodo();
                    valorestacionalidad[k - valorperiodoinicial][1] = "Demanda real:  " + demandarealestacionalidad[k - valorperiodoinicial] + "   En el periodo: " + periodoestacionalidad[k - valorperiodoinicial];
                    System.out.println("Demanda real:  " + demandarealestacionalidad[k - valorperiodoinicial] + "   En el periodo: " + periodoestacionalidad[k - valorperiodoinicial]);
                }
            }
        }
        for (Producto producto : vectorDTOProducto) {
            List<Demanda> listaDemanda = producto.getDemandas();
            Collections.sort(listaDemanda, new OrdenarDemandas());
            if (producto.getNombreProducto().equalsIgnoreCase(seleccion)) {
                for (int k = 0; k < 12; k++) {
                    demandahistorica[0][k] = listaDemanda.get(k).getDemandapronosticada();
                    System.out.println("HISTORICA:  " + demandahistorica[0][k]);
                }
            }
        }

        Estacionalidad metodoestacionalidad = new Estacionalidad();
        resultadoestacionalidad = metodoestacionalidad.calcularEstacionalidad(demandahistorica, demandarealestacionalidad, a, g);
        for (int i = 1; i < periodosapredecir + 1; i++) {
            System.out.println(" Resultado estacionalidad: " + resultadoestacionalidad[i]);
            resultadoestacionalidad[i] = redondear(resultadoestacionalidad[i], 2);
            valorestacionalidad[i - 1][0] = "Demanda Pronosticada: " + String.valueOf(resultadoestacionalidad[i] + "  En el periodo:  " + (i + valorperiodofinal));
            System.out.println("Demanda Pronosticada: " + String.valueOf(resultadoestacionalidad[i] + "  En el periodo:  " + (i + valorperiodofinal)));
        }
        return valorestacionalidad;
    }

    public static double redondear(double num, int ndecimal) {
        double aux0 = Math.pow(10, ndecimal);
        double aux = num * aux0;
        int tmp = (int) aux;

        return (double) (tmp / aux0);
    }

    public boolean confirmar() {
        return true;
    }
}
