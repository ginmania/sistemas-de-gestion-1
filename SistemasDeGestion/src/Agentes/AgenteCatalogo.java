/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import Implementaciones.CatalogoImpl;
import Interfaces.Catalogo;
import Interfaces.Producto;
import Interfaces.Proveedor;
import Persistencia.ObjetoPersistente;

/**
 *
 * @author duende
 */
public class AgenteCatalogo implements Catalogo, ObjetoPersistente{
    private String OIDCatalogo;
    private String OIDProd;
    private String OIDProv;
    
    private CatalogoImpl impl;

    public String getFecha() {
        return impl.getFecha();
    }

    public void setFecha(String fecha) {
        impl.setFecha(fecha);
    }

    public double getPrecioCompra() {
        return impl.getPrecioCompra();
    }

    public void setPrecioCompra(double precioCompra) {
        impl.setPrecioCompra(precioCompra);
    }

    public int getDemora() {
        return impl.getDemora();
    }

    public void setDemora(int demora) {
        impl.setDemora(demora);
    }

    public Proveedor getProveedor() {
        return impl.getProveedor();
    }

    public void setProveedor(Proveedor proveedor) {
        AgenteProveedor aP = (AgenteProveedor) proveedor;
        impl.setProveedor(proveedor);
        this.OIDProd = aP.getoid();
    }

    public Producto getProducto() {
        return impl.getProducto();
    }

    public void setProducto(Producto producto) {
        AgenteProducto ap = (AgenteProducto) producto;
        impl.setProducto(producto);
        this.OIDProd = ap.getoid();
    }

    public void setoid(String oid) {
        this.OIDCatalogo = oid;
    }

    public String getoid() {
        return this.OIDCatalogo;
    }

    public void setImpl(CatalogoImpl catalogoImpl) {
        this.impl = catalogoImpl;
    }

    public String getOIDProveedor() {
        return this.OIDProv;
    }
    
    public String getOIDProducto(){
        return this.OIDProd;
    }

    public void setOIDProducto(String valor) {
        this.OIDProd = valor;
    }

    public void setOIDProveedor(String valor) {
        this.OIDProv = valor;
    }

    public int getbaja() {
        return impl.getbaja();
    }

    public void setbaja(int baja) {
        impl.setbaja(baja);
    }
    
}
