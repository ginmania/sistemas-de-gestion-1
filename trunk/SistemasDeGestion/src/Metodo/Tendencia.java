/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class Tendencia {

    private double beta;
    private double alfa;
    private double[] pred;  // almacena todas las actualizaciones y predicciones
    private double[] Tn; // almacena todoas las tendencias

    /** Creates a new instance of Tendencia */
    public Tendencia() {
    }

    public double actualizacion(int i, double demReal) {
        double act = (alfa * demReal) + (1 - alfa) * (pred[i - 1] + Tn[i - 1]);
        return act;
    }

    public double prediccion(int ultimo, int h) {
        return pred[ultimo] + (h * Tn[ultimo]);
    }

    public void inicializacion(double inicial) {
        Tn[0] = 0;
        pred[0] = inicial;
    }

    /** Recibe 
         *  dem ---> demanda real
         *  a ---> alfa
         *  b ---> beta
         *  cantPeriodos ----> periodos del a�o de demandas
         *  cantPred ---> los periodos que tiene que predecir
         *
         *  Devuelve las actualizaciones para cada demanda (dem.length) y las predicciones en el vector
         **/
    public double[] calcularTendencia(double[] dem, double a, double b, int cantPeriodos, int cantPred) {
        alfa = a;
        beta = b;

        int longitud = dem.length + cantPred;
        pred = new double[longitud];
        Tn = new double[longitud];

        inicializacion(dem[0]);

        for (int i = 1; i < dem.length; i++) {
            pred[i] = actualizacion(i, dem[i]);
            Tn[i] = beta * (pred[i] - pred[i - 1]) + (1 - beta) * Tn[i - 1];
        }
        for (int j = 0; j < cantPred; j++) {
            int indice = dem.length + j;
            Tn[indice] = Tn[dem.length - 1];
            pred[indice] = prediccion(dem.length - 1, j + 1);
        }
        return pred;
    }

    public String [] mostrar(double[] v) {
      String  valor [] = new String[v.length]; 
        for (int i = 0; i < v.length; i++) {
            valor[i] = String.valueOf(v[i]);
            
        }

        return valor;
    }

}
