/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import Implementaciones.ProductoProveedorImpl;
import Interfaces.Producto;
import Interfaces.ProductoProveedor;
import Interfaces.Proveedor;
import Persistencia.ObjetoPersistente;

/**
 *
 * @author duende
 */
public class AgenteProductoProveedor implements ProductoProveedor, ObjetoPersistente{
    String OIDProducto;
    String OIDProveedor;
    String OID;
    ProductoProveedor impl;

    public AgenteProductoProveedor(ProductoProveedor impl) {
        this.impl = impl;
    }

    public AgenteProductoProveedor() {
        
    }
    
    public void setoid(String oid) {
        this.OID = oid;
    }

    public String getoid() {
        return this.OID;
    }

    public String getOIDProducto() {
        return OIDProducto;
    }

    public void setOIDProducto(String OIDProducto) {
        this.OIDProducto = OIDProducto;
    }

    public String getOIDProveedor() {
        return OIDProveedor;
    }

    public void setOIDProveedor(String OIDProveedor) {
        this.OIDProveedor = OIDProveedor;
    }

    public int getbaja() {
        return impl.getbaja();
    }

    public void setbaja(int baja) {
        impl.setbaja(baja);
    }

    public void setProducto(Producto p) {
        AgenteProducto ap = (AgenteProducto) p;
        this.setOIDProducto(ap.getoid());
        this.impl.setProducto(p);
    }

    public Producto getProducto() {
        return this.impl.getProducto();
    }

    public void setProveedor(Proveedor pr) {
        AgenteProveedor aP = (AgenteProveedor) pr;
        this.setOIDProveedor(aP.getoid());
        this.impl.setProveedor(pr);
    }

    public Proveedor getProveedor() {
        return this.getProveedor();
    }

    public void setImpl(ProductoProveedorImpl productoProveedorImpl) {
        impl = productoProveedorImpl;
    }
    
    
    
}
