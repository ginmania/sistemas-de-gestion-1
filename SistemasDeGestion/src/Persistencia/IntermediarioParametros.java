/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Agentes.AgenteParametros;
import Interfaces.Parametros;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class IntermediarioParametros extends IntermediarioRelacional {

    public IntermediarioParametros() {
        tabla = "Parametros";
        oid = "idParametros";
    }

    @Override
    public String generarSQLOID(String oid) {
        return "SELECT * FROM " + tabla + " WHERE idParametros= " + oid;
    }

    @Override
    public Registro convertirObjetoRegistro(ObjetoPersistente objeto) {
        Registro rs = new Registro();
        AgenteParametros parametros = (AgenteParametros) objeto;
        rs.addCampo(new Campo("idParametros", "'" + parametros.getoid() + "'"));
        rs.addCampo(new Campo("Alfa", "'" + String.valueOf(parametros.getAlfa()) + "'"));
        rs.addCampo(new Campo("Beta", "'" + String.valueOf(parametros.getBeta()) + "'"));
        rs.addCampo(new Campo("Gama", "'" + String.valueOf(parametros.getGama()) + "'"));
        return rs;
    }

    public ArrayList convertirRegistroObjeto(List<Registro> rs) {
        ArrayList<Parametros> parametros = new ArrayList<Parametros>();
        Agentes.AgenteParametros temp = null;
        try {
            for (Registro registro : rs) {
                temp = new Agentes.AgenteParametros();
                temp.setImpl(new Implementaciones.ParametrosImpl());
                temp.setoid(registro.getCampo(oid).getValor());
                temp.setAlfa(Double.parseDouble(registro.getCampo("Alfa").getValor()));
                temp.setBeta(Double.parseDouble(registro.getCampo("Beta").getValor()));
                temp.setGama(Double.parseDouble(registro.getCampo("Gama").getValor()));
                parametros.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Exploto el Intermediario PArametros");
        }
        return parametros;
    }
}
