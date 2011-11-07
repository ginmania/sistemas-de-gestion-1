/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.GregorianCalendar;
import java.util.Calendar;

/**
 *
 * @author duende
 */
public class DayOfYear {
    private int dia;
    private int mes;
    private int anio;

    public DayOfYear(){
        fechaDelSistema();
    }

    private void fechaDelSistema(){
        String fechaSistema;
        GregorianCalendar fechaActual = new GregorianCalendar();
        fechaSistema = String.valueOf(fechaActual.get(GregorianCalendar.DAY_OF_MONTH)).concat("/")
                    .concat(String.valueOf(fechaActual.get(GregorianCalendar.MONTH)+1)).concat("/")
                    .concat(String.valueOf(fechaActual.get(GregorianCalendar.YEAR)).substring(2,4));//AÃ±o...
        System.out.println("  Fecha del Sistema: "+fechaSistema);

        dia = fechaActual.get(GregorianCalendar.DAY_OF_MONTH);
        mes = fechaActual.get(GregorianCalendar.MONTH);
        anio = fechaActual.get(GregorianCalendar.YEAR);
    }

    public int getDiaDelAnio(){
        Calendar ca1 = Calendar.getInstance();
        /*
        set(int year, int month, int date)
        Enero=0, Febrero=1, Marzo=2...
        */

        //ca1.set(2010,11,1);
        ca1.set(anio,mes,dia);

        /*
        2 Feb 2009, Day of Year, Jan 31 + 2 Feb = 33 day of year
        */

        int DAY_OF_YEAR = ca1.get(Calendar.DAY_OF_YEAR);

        // More Calendar Date option can check
        /*
        int DAY_OF_MONTH=ca1.get(Calendar.DAY_OF_MONTH);
        int DAY_OF_WEEK=ca1.get(Calendar.DAY_OF_WEEK);
        int WEEK_OF_MONTH=ca1.get(Calendar.WEEK_OF_MONTH);
        int WEEK_OF_YEAR=ca1.get(Calendar.WEEK_OF_YEAR);
         */
        return DAY_OF_YEAR;
    }
    public boolean esBisiesto(){
        boolean rta = false;
        GregorianCalendar cal = new GregorianCalendar();
        if (cal.isLeapYear(anio))
            rta = true;
        return rta;
    }

    public int getAño(){
        GregorianCalendar anio = new GregorianCalendar();
        return anio.get(GregorianCalendar.YEAR);
    }
}