/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodo;

/**
 *
 * @author diego
 */
public class Calculo {

    private double alfa;
    private double beta;
    private double constantesuavizamiento;
    private int periodo;
    private double promedioinicial;
    private int promedioactual;
    private double demandareal;
    private double prediccionfn;
    private double prediccionfn_1;
    private double errorEn;
    private double errorPromedio;
    private double error2;
    private double MSEn;
    private double destandar;
    private double destandarerror;
    private double senal;

    public Calculo() {
    }

    public void DatosIniciales(double promedioinicial, double demandareal, double prediccionfn,
            double prediccionfn_1, double errorEn, double errorPromedio, double error2,
            double MSEn, double destandar, double alfa, double cs, int periodo, double destandarerror,
            double senal) {
        this.promedioinicial = promedioinicial;
        this.demandareal = demandareal;
        this.prediccionfn = prediccionfn;
        this.prediccionfn_1 = prediccionfn_1;
        this.errorEn = errorEn;
        this.errorPromedio = errorPromedio;
        this.error2 = error2;
        this.alfa = alfa;
        this.constantesuavizamiento = cs;
        this.periodo = periodo;
        this.MSEn = MSEn;
        this.destandar = destandar;
        this.destandarerror = destandarerror;
        this.senal = senal;
        beta = 1 - alfa;


    }

    public int actualizacion() {
        promedioactual = (int) redondear((alfa * demandareal) + (beta * promedioinicial), 2);

        return promedioactual;
    }

    public double getPrediccionfn() {
        prediccionfn = promedioactual;
        return prediccionfn;
    }

    public double getPrediccionfn_1() {
        return prediccionfn_1 = promedioinicial; //falta revisar
    }

    public double geterrorEn() {
        errorEn = prediccionfn_1 - demandareal;
        return errorEn;
    }

    public double geterror2() {
        error2 = errorEn * errorEn;
        return error2;
    }

    public double getErrorPromedio() {
        errorPromedio = redondear(((alfa * errorEn) + (beta * errorPromedio)), 2);
        return errorPromedio;
    }

    public double getMSEen() {
        MSEn = redondear(((constantesuavizamiento * Math.pow(errorEn, 2)) + (beta * MSEn)), 2);
        return MSEn;
    }

    public double getDesviacionEstandar() {
        destandar = redondear(Math.sqrt(MSEn), 2);
        return destandar;
    }

    public double getDesviacionEstandarError() {
        destandarerror = redondear(((Math.sqrt(alfa) * (Math.sqrt(MSEn))) / (2 - alfa)), 2);
        return destandarerror;
    }

    public double getsenal() {
        senal = redondear((errorPromedio / destandarerror), 2);
        return senal;
    }

    public static double redondear(double num, int ndecimal) {
        double aux0 = Math.pow(10, ndecimal);
        double aux = num * aux0;
        int tmp = (int) aux;

        return (double) (tmp / aux0);
    }
}
