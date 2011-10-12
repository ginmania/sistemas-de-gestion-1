/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author diego
 */
public interface Demanda {

    public String getAnio();

    public void setAnio(String anio);

    public int getCantidad();

    public void setCantidad(int cantidad);

    public double getDemandaactualizada();

    public void setDemandaactualizada(double demandaactualizada);

    public double getDemandapronosticada();

    public void setDemandapronosticada(double demandapronosticada);

    public double getDemandareal();

    public void setDemandareal(double demandareal);

    public double getErrorpromedio();

    public void setErrorpromedio(double errorpromedio);

    public double getFactorestacional();

    public void setFactorestacional(double factorestacional);

    public String getFechafin();

    public void setFechafin(String fechafin);

    public String getFechainicio();

    public void setFechainicio(String fechainicio);

    public double getGananciaperiodo();

    public void setGananciaperiodo(double gananciaperiodo);

    public double getMse();

    public void setMse(double mse);

    public int getPeriodo();

    public void setPeriodo(int periodo);

    public double getSenalrastreo();

    public void setSenalrastreo(double senalrastreo);

    public double getTendencia();

    public void setTendencia(double tendencia);

    public Producto getProducto();

    public void setProducto(Producto producto);
}
