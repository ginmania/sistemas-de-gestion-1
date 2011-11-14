/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Agentes.AgenteCatalogo;
import Interfaces.Catalogo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duende
 */
class IntermediarioCatalogo extends IntermediarioRelacional {

    public IntermediarioCatalogo() {
        tabla = "Catalogo";
        oid = "OIDCatalogo";
    }

    @Override
    public Registro convertirObjetoRegistro(ObjetoPersistente objeto) {
        Registro rs = new Registro();
        AgenteCatalogo pp = (AgenteCatalogo) objeto;
        rs.addCampo(new Campo("OIDCatalogo", "'" + pp.getoid() + "'"));
        rs.addCampo(new Campo("OIDProducto", "'" + pp.getOIDProducto() + "'"));
        rs.addCampo(new Campo("OIDProveedor", "'" + pp.getOIDProveedor() + "'"));
        rs.addCampo(new Campo("Fecha", "'" + pp.getFecha() + "'"));
        rs.addCampo(new Campo("Demora", "'" + String.valueOf(pp.getDemora()) + "'"));
        rs.addCampo(new Campo("PrecioCompra", "'" + String.valueOf(pp.getPrecioCompra()) + "'"));
        rs.addCampo(new Campo("baja", "'" + String.valueOf(pp.getbaja() + "'")));
        return rs;
    }

    @Override
    public ArrayList convertirRegistroObjeto(List<Registro> rs) {
        ArrayList<Catalogo> prodprovs = new ArrayList<Catalogo>();
        Agentes.AgenteCatalogo temp = null;
        try {
            for (Registro registro : rs) {
                temp = new Agentes.AgenteCatalogo();
                temp.setImpl(new Implementaciones.CatalogoImpl());
                temp.setoid(registro.getCampo(oid).getValor());
                temp.setOIDProducto(registro.getCampo("OIDProducto").getValor());
                temp.setOIDProveedor(registro.getCampo("OIDProveedor").getValor());
                temp.setFecha(registro.getCampo("Fecha").getValor());
                temp.setDemora((Integer)registro.getCampo("Demora").getValor(Integer.class));
                temp.setPrecioCompra((Double)registro.getCampo("PrecioCompra").getValor(Double.class));
                temp.setbaja((Integer)registro.getCampo("baja").getValor(Integer.class));
                prodprovs.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Exploto el Intermediariocatalogo");
            ex.printStackTrace();
        }

        return prodprovs;
    }

    
    
}
