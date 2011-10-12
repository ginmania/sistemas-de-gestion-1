/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodo;

/**
 *
 * @author diego
 */
public class Simple {

    private double alfa;
    private double[] pred;

    public Simple() {
    }

    public double actualizacion(int i, double demReal) {
        return (alfa * demReal) + (1 - alfa) * pred[i - 1];
    }

    public double prediccion(int ultimo) {
        return pred[ultimo];
    }

    public void inicializacion(double inicial) {
        pred[0] = inicial;
    }

    /** Recibe 
         *  dem ---> demanda real
         *  alfa
         *  cantPred ---> los periodos que tiene que predecir
         *
         *  Devuelve las actualizaciones para cada demanda (dem.length) y las predicciones en el vector
         **/
    public double[] calcularSimple(double[] dem, double alfaI, int cantPred) {
        int longitud = dem.length + cantPred;
        this.pred = new double[longitud];
        this.alfa = alfaI;

        inicializacion(dem[0]);
        for (int i = 1; i < dem.length; i++) {
            pred[i] = actualizacion(i, dem[i]);
        }
        for (int j = 0; j < cantPred; j++) {
            int indice = dem.length + j;
            pred[indice] = prediccion(dem.length - 1);
        }
        return pred;
    }
}
