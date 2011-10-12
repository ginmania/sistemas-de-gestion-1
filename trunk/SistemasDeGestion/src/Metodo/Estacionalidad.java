/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodo;

/**
 *
 * @author diego
 */
public class Estacionalidad {

    private double beta;
    private double alfa;
    private double[] Xj; // almacena todas las actualizaciones y predicciones
    private double[] Sj; // almacena todoas las tendencias
    private double[] prom;
    private double[] factor;
    private double[] pron;

    public Estacionalidad() {
    }

    private double actualizacion(int i, double demReal) {
        return (alfa * (demReal / Sj[i - 1]) + (1 - alfa) * prom[i - 1]);
    }

    private double prediccion(int ultimo, int periodo) {
        return prom[ultimo] * factor[periodo];
    }

    private void inicializacion(double[][] dem_hist) {
        Xj = new double[dem_hist[0].length];
        Sj = new double[dem_hist[0].length];
        for (int i = 0; i < dem_hist[0].length; i++) {
            double[] fila = obtenerFila(dem_hist, i);
            Xj[i] = promedio(fila);
        }
        double Xj_prom = promedio(Xj);
        for (int k = 0; k < dem_hist[0].length; k++) {
            Sj[k] = Xj[k] / Xj_prom;
        }
        prom[0] = Xj_prom;
        factor[0] = Sj[dem_hist.length - 1];
    }

    private double promedio(double[] v) {
        double suma = 0;
        for (int i = 0; i < v.length; i++) {
            suma += v[i];
        }
        return suma / v.length;
    }

    private double[] obtenerFila(double[][] matriz, int fila) {
        double[] v = new double[matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            v[i] = matriz[i][fila];
        }
        return v;
    }

    /** Recibe 
         *  dem_hist [][] ---> demanda real historica [anio][periodo]
         *  demReal[] ---> demanda real a actualizar [periodo]
         *  a ---> alfa
         *  b ---> beta
         *  cantPeriodos ----> periodos del anio de demandas
         *  cantPred ---> los periodos que tiene que predecir
         *
         *  Devuelve el pronostico para los periodos del proximo anio
         **/
    public double[] calcularEstacionalidad(double[][] dem_hist, double[] demReal, double a, double b) {
        prom = new double[demReal.length];
        factor = new double[demReal.length];
        pron = new double[demReal.length];
        alfa = a;
        beta = b;
        inicializacion(dem_hist);
        for (int i = 1; i < demReal.length; i++) {
            prom[i] = actualizacion(i, demReal[i - 1]);
//return (alfa*(demReal[i-1]/Sj[i-1]) + (1-alfa)*prom[i-1]);
            factor[i] = beta * (demReal[i - 1] - prom[i]) + (1 - beta) * Sj[i - 1];
        }
        for (int j = 0; j < demReal.length; j++) {
            pron[j] = prediccion(demReal.length - 1, j);
        }
        return pron;
    }

    public String[] mostrar(double[] v) {
        String valor[] = new String[v.length];
        for (int i = 0; i < v.length; i++) {
            valor[i] = String.valueOf(v[i]);
        }
        return valor;
    }
}
