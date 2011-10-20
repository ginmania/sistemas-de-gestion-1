/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Agentes.AgenteProductoProveedor;
import Interfaces.ProductoProveedor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duende
 */
class IntermediarioProductoProveedor extends IntermediarioRelacional {

    public IntermediarioProductoProveedor() {
        tabla = "ProductoProveedor";
        oid = "OIDProductoProveedor";
    }

    @Override
    public Registro convertirObjetoRegistro(ObjetoPersistente objeto) {
        Registro rs = new Registro();
        AgenteProductoProveedor pp = (AgenteProductoProveedor) objeto;
        rs.addCampo(new Campo("OIDProductoProveedor", "'" + pp.getoid() + "'"));
        rs.addCampo(new Campo("OIDProducto", "'" + pp.getOIDProducto() + "'"));
        rs.addCampo(new Campo("OIDProveedor", "'" + pp.getOIDProveedor() + "'"));
        rs.addCampo(new Campo("baja", "'" + String.valueOf(pp.getbaja()) + "'"));
        return rs;
    }

    @Override
    public ArrayList convertirRegistroObjeto(List<Registro> rs) {
        ArrayList<ProductoProveedor> prodprovs = new ArrayList<ProductoProveedor>();
        Agentes.AgenteProductoProveedor temp = null;
        try {
            for (Registro registro : rs) {
                temp = new Agentes.AgenteProductoProveedor();
                temp.setImpl(new Implementaciones.ProductoProveedorImpl());
                temp.setoid(registro.getCampo(oid).getValor());
                temp.setOIDProducto(registro.getCampo("OIDProducto").getValor());
                temp.setOIDProveedor(registro.getCampo("OIDProveedor").getValor());
                prodprovs.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Exploto el Intermediario ProductoProveedor");
            ex.printStackTrace();
        }

        return prodprovs;
    }

    
    
}
