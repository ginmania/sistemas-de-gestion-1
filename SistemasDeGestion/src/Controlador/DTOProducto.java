/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author Matias
 */
public class DTOProducto {

    private String codigoProd;
    private String nombreProd;
    private String descripcion;
    private double precioCompra;
    private double precioVenta;
    
    
    public double getPrecioCompra() {
        return precioCompra;
    }
    
    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }
    
    
    public double getPrecioVenta() {
        return precioVenta;
    }
    
    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    


    /**
     * @return the codigoProd
     */
    public String getCodigoProd() {
        return codigoProd;
    }

    /**
     * @param codigoProd the codigoProd to set
     */
    public void setCodigoProd(String codigoProd) {
        this.codigoProd = codigoProd;
    }

    /**
     * @return the nombreProd
     */
    public String getNombreProd() {
        return nombreProd;
    }

    /**
     * @param nombreProd the nombreProd to set
     */
    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   
}
