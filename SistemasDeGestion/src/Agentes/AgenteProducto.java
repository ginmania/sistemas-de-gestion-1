/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import Implementaciones.ProductoImpl;
import Interfaces.Demanda;
import Interfaces.Producto;
import Interfaces.Proveedor;
import Interfaces.Stock;
import Persistencia.FachadaInterna;
import Persistencia.ObjetoPersistente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author malayo
 */
public class AgenteProducto implements Producto, ObjetoPersistente {

    private ProductoImpl impl;
    private String OIDProducto, OIDStock;
    private List<String> proveedorrelacionados;
    private List<String> demandarelacionadas;

    public AgenteProducto() {
    }

    public AgenteProducto(ProductoImpl impl) {
        this.impl = impl;
    }

    public AgenteProducto(AgenteProducto agente) {
        this.impl = agente.impl;
        this.OIDProducto = agente.OIDProducto;
        this.OIDStock = agente.OIDStock;
        this.proveedorrelacionados = agente.proveedorrelacionados;
        this.demandarelacionadas = agente.demandarelacionadas;
    }

    
    public List<String> getDemandarelacionadas() {
        return demandarelacionadas;
    }

    public void setDemandarelacionadas(List<String> demandarelacionadas) {
        this.demandarelacionadas = demandarelacionadas;
    }

    public List<String> getProveedorrelacionados() {
        return proveedorrelacionados;
    }

    public void setProveedorrelacionados(List<String> proveedorrelacionados) {
        this.proveedorrelacionados = proveedorrelacionados;
    }

    public void addOIDProveedor(String OIDProveedor) {
        if (proveedorrelacionados == null) {
            proveedorrelacionados = new ArrayList<String>();
        }
        proveedorrelacionados.add(OIDProveedor);
    }

    public void setProveedors(List<Proveedor> proveedor) {
        impl.setProveedors(proveedor);

    }

    public void setProveedor(Proveedor proveedor) {
        impl.setProveedor(proveedor);
    }

    public List<Proveedor> getProveedors() {
        if (impl.getProveedors() == null) {
            for (String OIDProveedor : proveedorrelacionados) {
                setProveedor((Proveedor) FachadaInterna.getInstancia().buscarOID(Proveedor.class, OIDProveedor));
            }
        }
        return impl.getProveedors();
    }

    public String getOIDStock() {
        return OIDStock;
    }

    public void setOIDStock(String OIDS) {
        this.OIDStock = OIDS;
    }

    public ProductoImpl getImpl() {
        return impl;
    }

    public void setImpl(ProductoImpl impl) {
        this.impl = impl;
    }

    public int getCodigoProducto() {
        return impl.getCodigoProducto();
    }

    public void setCodigoProducto(int CodigoProducto) {
        this.impl.setCodigoProducto(CodigoProducto);
    }

    public String getDescripcionProducto() {
        return impl.getDescripcionProducto();
    }

    public void setDescripcionProducto(String DescripcionProdcuto) {
        this.impl.setDescripcionProducto(DescripcionProdcuto);
    }

    public String getNombreProducto() {
        return impl.getNombreProducto();
    }

    public void setNombreProducto(String NombreProducto) {
        this.impl.setNombreProducto(NombreProducto);
    }

    public void setoid(String oid) {
        this.OIDProducto = oid;
    }

    public String getoid() {
        return OIDProducto;
    }

    public int getbaja() {
        return impl.getbaja();
    }

    public void setbaja(int baja) {
        this.impl.setbaja(baja);
    }

    public double getPrecioCompra() {
        return impl.getPrecioCompra();
    }

    public void setPrecioCompra(double preciocompra) {
        this.impl.setPrecioCompra(preciocompra);
    }

    public double getPrecioVenta() {
        return impl.getPrecioVenta();
    }

    public void setPrecioVenta(double precioventa) {
        this.impl.setPrecioVenta(precioventa);
    }

    public Stock getStock() {
        if ((impl).getStock() == null) {
            setStock((Stock) FachadaInterna.getInstancia().buscarOID(Stock.class, OIDStock));
        }
        return impl.getStock();

    }

    public void setStock(Stock stock) {
        this.impl.setStock(stock);
    }

    public List<Demanda> getDemandas() {
        if (impl.getDemandas() == null) {
            for (String OIDDemanda : demandarelacionadas) {
                setDemanda((Demanda) FachadaInterna.getInstancia().buscarOID(Demanda.class, OIDDemanda));
            }
        }
        return impl.getDemandas();
    }

    public void setDemandas(List<Demanda> demanda) {
        impl.setDemandas(demanda);
    }

    public void setDemanda(Demanda demanda) {
        impl.setDemanda(demanda);
    }

    public void addOIDDemanda(String OIDDemanda) {
        if (demandarelacionadas == null) {
            demandarelacionadas = new ArrayList<String>();
        }
        proveedorrelacionados.add(OIDDemanda);
    }
}
