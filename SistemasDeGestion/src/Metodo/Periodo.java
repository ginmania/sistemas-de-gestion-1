/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author diego
 */
public class Periodo {

    private int periodos;
    private int anio;
    private int mes;
    private int dia;
    private String resultado;

    public Periodo() {
    }

    /*CREAMOS EL GREGORIAN CALENDAR DE LAS DOS FECHAS*/

    /* COMPROBAMOS SI ESTAMOS EN EL MISMO ANYO */
    /*EJEMPLO RESTAR 2 FECHAS EN JAVA*/
    public String restaFechas(Calendar date1, Calendar date2) {
        if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) {
            return String.valueOf(date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR));
        } else {
            /* SI ESTAMOS EN DISTINTO ANYO COMPROBAMOS QUE EL ANYO DEL DATEINI NO SEA BISIESTO
             * SI ES BISIESTO SON 366 DIAS EL ANYO
             * SINO SON 365
             */
            int diasAnyo = ((GregorianCalendar) date1).isLeapYear(date1.get(Calendar.YEAR)) ? 366 : 365;

            /* CALCULAMOS EL RANGO DE ANYOS */
            int rangoAnyos = date2.get(Calendar.YEAR) - date1.get(Calendar.YEAR);

            /* CALCULAMOS EL RANGO DE DIAS QUE HAY */
            int rango = (rangoAnyos * diasAnyo) + (date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR));

            return String.valueOf(rango);
        }
    }

    public int getPeriodos(Date fechainicio, Date fechafin) throws Exception, ParseException {
        Calendar greg = Calendar.getInstance();
        Calendar greg1 = Calendar.getInstance();
        greg.setTime(fechainicio);
        greg1.setTime(fechafin);
        resultado = restaFechas(greg, greg1);
        periodos = (int) java.lang.Math.rint(Integer.parseInt(resultado) / 28);
        return periodos;
    }

    public GregorianCalendar convertDateString(String dateString) throws Exception, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        java.util.Date date = sdf.parse(dateString);
        Calendar calender = new GregorianCalendar();
        calender.setTime(date);
        return (GregorianCalendar) calender;
    }
}