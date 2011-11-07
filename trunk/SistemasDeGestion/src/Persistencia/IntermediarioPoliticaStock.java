/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Agentes.AgentePoliticaStock;
import Interfaces.PoliticaStock;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duende
 */
public class IntermediarioPoliticaStock extends IntermediarioRelacional{
    
    public IntermediarioPoliticaStock() {
        tabla = "Politica";
        oid = "OIDPolitica";
    }
    
    @Override
    public String generarSQLOID(String oid) {
        return "SELECT * FROM " + tabla + " WHERE OIDPolitica= " + oid;
    }

    @Override
    public Registro convertirObjetoRegistro(ObjetoPersistente objeto) {
        Registro rs = new Registro();
        AgentePoliticaStock politica = (AgentePoliticaStock) objeto;
        rs.addCampo(new Campo("OIDPolitica", "'" + politica.getoid() + "'"));
        rs.addCampo(new Campo("descripcion", "'" + politica.getDescripcion() + "'"));
        return rs;
    }

    @Override
    public ArrayList convertirRegistroObjeto(List<Registro> rs) {
        ArrayList<PoliticaStock> politica = new ArrayList<PoliticaStock>();
        Agentes.AgentePoliticaStock temp = null;
        try {
            for (Registro registro : rs) {
                temp = new Agentes.AgentePoliticaStock();
                temp.setImpl(new Implementaciones.PoliticaStockImpl());
                temp.setoid(registro.getCampo(oid).getValor());
                temp.setDescripcion(registro.getCampo("descripcion").getValor());
                }
        } catch (Exception ex) {
            System.out.println("Exploto el Intermediario Politica");
            ex.printStackTrace();
        }
        return politica;
    }
    
}
