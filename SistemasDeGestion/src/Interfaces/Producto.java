/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;

/**
 *
 * @author diego
 */
public interface Producto {

    public int getCodigoProducto();

    public void setCodigoProducto(int CodigoProducto);

    public String getDescripcionProducto();

    public void setDescripcionProducto(String DescripcionProdcuto);

    public String getNombreProducto();

    public void setNombreProducto(String NombreProducto);

    public double getPrecioCompra();

    public void setPrecioCompra(double preciocompra);

    public double getPrecioVenta();

    public void setPrecioVenta(double precioventa);

    public int getbaja();

    public void setbaja(int baja);

    public Stock getStock();

    public void setStock(Stock stock);

    public List<Proveedor> getProveedors();

    public void setProveedors(List<Proveedor> proveedor);

    public void setProveedor(Proveedor proveedor);
    
    public List<Demanda> getDemandas();

    public void setDemandas(List<Demanda> demanda);

    public void setDemanda(Demanda demanda);

}
