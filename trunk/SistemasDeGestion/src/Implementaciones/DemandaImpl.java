/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.Demanda;
import Interfaces.Producto;

/**
 *
 * @author diego
 */
public class DemandaImpl implements Demanda {

    private String fechainicio;
    private String fechafin;
    private double demandareal;
    private double demandaactualizada;
    private double demandapronosticada;
    private double factorestacional;
    private double gananciaperiodo;
    private String anio;
    private int periodo;
    private double tendencia;
    private double errorpromedio;
    private double mse;
    private double senalrastreo;
    private int cantidad;
    public Producto producto;

    public DemandaImpl() {
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getDemandaactualizada() {
        return demandaactualizada;
    }

    public void setDemandaactualizada(double demandaactualizada) {
        this.demandaactualizada = demandaactualizada;
    }

    public double getDemandapronosticada() {
        return demandapronosticada;
    }

    public void setDemandapronosticada(double demandapronosticada) {
        this.demandapronosticada = demandapronosticada;
    }

    public double getDemandareal() {
        return demandareal;
    }

    public void setDemandareal(double demandareal) {
        this.demandareal = demandareal;
    }

    public double getErrorpromedio() {
        return errorpromedio;
    }

    public void setErrorpromedio(double errorpromedio) {
        this.errorpromedio = errorpromedio;
    }

    public double getFactorestacional() {
        return factorestacional;
    }

    public void setFactorestacional(double factorestacional) {
        this.factorestacional = factorestacional;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public double getGananciaperiodo() {
        return gananciaperiodo;
    }

    public void setGananciaperiodo(double gananciaperiodo) {
        this.gananciaperiodo = gananciaperiodo;
    }

    public double getMse() {
        return mse;
    }

    public void setMse(double mse) {
        this.mse = mse;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public double getSenalrastreo() {
        return senalrastreo;
    }

    public void setSenalrastreo(double senalrastreo) {
        this.senalrastreo = senalrastreo;
    }

    public double getTendencia() {
        return tendencia;
    }

    public void setTendencia(double tendencia) {
        this.tendencia = tendencia;
    }

    public Producto getProducto() {
       return producto;
    }

    public void setProducto(Producto producto) {
      this.producto = producto;
    }


}
