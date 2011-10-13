/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.Demanda;
import Interfaces.Producto;
import Interfaces.Proveedor;
import Interfaces.Stock;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author malayo
 */
public class ProductoImpl implements Producto {

    private int codigo;
    private String nombre;
    private String descripcion;
    private double preciocompra;
    private double precioventa;
    private int baja;
    public Stock stock;
    public List<Proveedor> proveedores;
    public List<Demanda> demandas;
    private char clasifABC;

    public ProductoImpl() {
    }

    public ProductoImpl(ProductoImpl producto) {
        this.codigo = producto.getCodigoProducto();
        this.nombre = producto.getNombreProducto();
        this.descripcion = producto.getDescripcionProducto();
        this.preciocompra = producto.getPrecioCompra();
        this.precioventa = producto.getPrecioVenta();
        this.baja = producto.getbaja();
        this.stock = producto.getStock();
        this.proveedores = producto.getProveedors();
        this.demandas = producto.getDemandas();
    }
    

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getCodigoProducto() {
        return codigo;
    }

    public void setCodigoProducto(int CodigoProducto) {
        this.codigo = CodigoProducto;
    }

    public String getDescripcionProducto() {
        return descripcion;
    }

    public void setDescripcionProducto(String DescripcionProdcuto) {
        this.descripcion = DescripcionProdcuto;
    }

    public String getNombreProducto() {
        return nombre;
    }

    public void setNombreProducto(String NombreProducto) {
        this.nombre = NombreProducto;
    }

    public int getbaja() {
        return baja;
    }

    public void setbaja(int baja) {
        this.baja = baja;
    }

    public double getPrecioCompra() {
        return preciocompra;
    }

    public void setPrecioCompra(double preciocompra) {
        this.preciocompra = preciocompra;
    }

    public double getPrecioVenta() {
        return precioventa;
    }

    public void setPrecioVenta(double precioventa) {
        this.precioventa = precioventa;
    }

    public List<Proveedor> getProveedors() {
        return proveedores;
    }

    public void setProveedors(List<Proveedor> proveedor) {
        if (proveedores == null) {
            proveedores = new ArrayList<Proveedor>();
        }
        proveedores.addAll(proveedor);
    }
    

    public void setProveedor(Proveedor proveedor) {
        if (proveedores == null) {
            proveedores = new ArrayList<Proveedor>();
        }
        proveedores.add(proveedor);
    }

    public List<Demanda> getDemandas() {
       return demandas;
    }

    public void setDemandas(List<Demanda> demanda) {
        if(demandas == null) {
            demandas = new ArrayList<Demanda>();
        }
        demandas.addAll(demanda);
    }

    public void setDemanda(Demanda demanda) {
        if(demandas == null) {
            demandas = new ArrayList<Demanda>();
        }
        demandas.add(demanda);
    }
    
    public char getClasifABC(){
        return this.clasifABC;
    }
    
    public void setClasifABC(char c) {
        this.clasifABC = c;
    }
}
