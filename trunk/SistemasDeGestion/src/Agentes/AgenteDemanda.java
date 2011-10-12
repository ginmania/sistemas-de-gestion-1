/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import Implementaciones.DemandaImpl;
import Interfaces.Demanda;
import Interfaces.Producto;
import Persistencia.FachadaInterna;
import Persistencia.ObjetoPersistente;

/**
 *
 * @author diego
 */
public class AgenteDemanda implements Demanda, ObjetoPersistente {

    private DemandaImpl impl;
    private String OIDDemanda;
    private String OIDProducto;

    public String getOIDProducto() {
        return OIDProducto;
    }

    public void setOIDProducto(String OIDProducto) {
        this.OIDProducto = OIDProducto;
    }

    public AgenteDemanda() {
    }

    public AgenteDemanda(DemandaImpl impl) {
        this.impl = impl;
    }
    

    public DemandaImpl getImpl() {
        return impl;
    }

    public void setImpl(DemandaImpl impl) {
        this.impl = impl;
    }

    public String getAnio() {
        return impl.getAnio();
    }

    public void setAnio(String anio) {
        this.impl.setAnio(anio);
    }

    public int getCantidad() {
        return impl.getCantidad();
    }

    public void setCantidad(int cantidad) {
        this.impl.setCantidad(cantidad);
    }

    public double getDemandaactualizada() {
        return impl.getDemandaactualizada();
    }

    public void setDemandaactualizada(double demandaactualizada) {
        this.impl.setDemandaactualizada(demandaactualizada);
    }

    public double getDemandapronosticada() {
        return impl.getDemandapronosticada();
    }

    public void setDemandapronosticada(double demandapronosticada) {
        this.impl.setDemandapronosticada(demandapronosticada);
    }

    public double getDemandareal() {
        return impl.getDemandareal();
    }

    public void setDemandareal(double demandareal) {
        this.impl.setDemandareal(demandareal);
    }

    public double getErrorpromedio() {
        return impl.getErrorpromedio();
    }

    public void setErrorpromedio(double errorpromedio) {
        this.impl.setErrorpromedio(errorpromedio);
    }

    public double getFactorestacional() {
        return impl.getFactorestacional();
    }

    public void setFactorestacional(double factorestacional) {
        this.impl.setFactorestacional(factorestacional);
    }

    public String getFechafin() {
        return impl.getFechafin();
    }

    public void setFechafin(String fechafin) {
        this.impl.setFechafin(fechafin);
    }

    public String getFechainicio() {
        return impl.getFechafin();
    }

    public void setFechainicio(String fechainicio) {
        this.impl.setFechainicio(fechainicio);
    }

    public double getGananciaperiodo() {
        return impl.getGananciaperiodo();
    }

    public void setGananciaperiodo(double gananciaperiodo) {
        this.impl.setGananciaperiodo(gananciaperiodo);
    }

    public double getMse() {
        return impl.getMse();
    }

    public void setMse(double mse) {
        this.impl.setMse(mse);
    }

    public int getPeriodo() {
        return impl.getPeriodo();
    }

    public void setPeriodo(int periodo) {
        this.impl.setPeriodo(periodo);
    }

    public double getSenalrastreo() {
        return impl.getSenalrastreo();
    }

    public void setSenalrastreo(double senalrastreo) {
        this.impl.setSenalrastreo(senalrastreo);
    }

    public double getTendencia() {
        return impl.getTendencia();
    }

    public void setTendencia(double tendencia) {
        this.impl.setTendencia(tendencia);
    }

    public void setoid(String oid) {
        this.OIDDemanda = oid;
    }

    public String getoid() {
        return OIDDemanda;
    }

    public Producto getProducto() {
        if ((impl).getProducto() == null) {
            setProducto((Producto) FachadaInterna.getInstancia().buscarOID(Producto.class, OIDProducto));
        }
        return impl.getProducto();
    }

    public void setProducto(Producto producto) {
        this.impl.setProducto(producto);
    }
}
