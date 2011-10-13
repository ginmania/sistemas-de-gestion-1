/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author natalia
 */
public class InformeABC {
    private String codigoDeProducto;
    private double demandaAnual;
    private double demandaAV; // demanda anual valorizada DAV
    private double costoUnitario;
    private double demandaAVA; // demanda anual valorizada acumulada
    private double porcentajeAcumulado; // CAP
    
     public void setCodigoDeProducto(String codigoDeProducto){
        this.codigoDeProducto = codigoDeProducto;
    }

    public void setDemandaAnual(double demandaAnual){
        this.demandaAnual = demandaAnual;
    }

    public void setDemandaAV(){
        this.demandaAV = this.costoUnitario * this.getDemandaAnual();

    }

    public void setDemandaAV(double demandaAV){
        this.demandaAV = demandaAV;
    }

    public void setCostoUnitario(double costoUnitario){
        this.costoUnitario = costoUnitario;
    }

    public void setDemandaAVA(double demandaAVA){
        this.demandaAVA = demandaAVA;
    }

    public void setPorcentajeAcumulado(double porcentajeAcumulado){
        this.porcentajeAcumulado = porcentajeAcumulado;
    }

    public String getCodigoDeProducto(){
        return this.codigoDeProducto;
    }

    public double getDemandaAnual(){
       return this.demandaAnual;
    }

    public double getDemandaAV(){
       return this.demandaAV;
    }

    public double getCostoUnitario(){
        return this.costoUnitario;
    }

    public double getDemandaAVA(){
        return this.demandaAVA;
    }

    public double getPorcentajeAcumulado(){
        return this.porcentajeAcumulado;
    }
}
