/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Agentes.AgenteDemanda;
import Interfaces.Demanda;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class IntermediarioDemanda extends IntermediarioRelacional {

    public IntermediarioDemanda() {
        tabla = "Demanda";
        oid = "OIDDemanda";
    }

    @Override
    public String generarSQLOID(String oid) {
        return "SELECT * FROM " + tabla + " WHERE OIDDemanda= " + oid;

    }
//        private void busquedaoidquenoexisten(AgenteDemanda demanda) {
//        String consulta = "SELECT * FROM Cliente_Proveedor WHERE  OIDDemanda =" + demanda.getoid();
//
//        List<Registro> registros = ejecutarSQLB(consulta);
//        for (Registro registro : registros) {
//            cliente.addOIDProveedor(registro.getCampo("OIDProveedor").getValor());
//
//        }
//    }

    @Override
    public Registro convertirObjetoRegistro(ObjetoPersistente objeto) {
        Registro rs = new Registro();
        Agentes.AgenteDemanda demanda = (AgenteDemanda) objeto;
        rs.addCampo(new Campo("OIDDemanda", "'" + demanda.getoid() + "'"));
        rs.addCampo(new Campo("OIDProducto", "'" + ((AgenteDemanda) demanda.getProducto()).getoid() + "'"));
        rs.addCampo(new Campo("fechainicio", "'" + demanda.getFechainicio() + "'"));
        rs.addCampo(new Campo("fechafin", "'" + demanda.getFechafin() + "'"));
        rs.addCampo(new Campo("demandareal", String.valueOf(demanda.getDemandareal())));
        rs.addCampo(new Campo("demandaactualizada", String.valueOf(demanda.getDemandaactualizada())));
        rs.addCampo(new Campo("demandapronosticada", String.valueOf(demanda.getDemandapronosticada())));
        rs.addCampo(new Campo("factorestacional", String.valueOf(demanda.getFactorestacional())));
        rs.addCampo(new Campo("gananciaperiodo", String.valueOf(demanda.getGananciaperiodo())));
        rs.addCampo(new Campo("anio", "'" + demanda.getAnio() + "'"));
        rs.addCampo(new Campo("periodo", String.valueOf(demanda.getPeriodo())));
        rs.addCampo(new Campo("tendencia", String.valueOf(demanda.getTendencia())));
        rs.addCampo(new Campo("errorpromedio", String.valueOf(demanda.getErrorpromedio())));
        rs.addCampo(new Campo("mse", String.valueOf(demanda.getMse())));
        rs.addCampo(new Campo("senalrastreo", String.valueOf(demanda.getSenalrastreo())));
        rs.addCampo(new Campo("cantidad", String.valueOf(demanda.getCantidad())));
        return rs;
    }

    @Override
    public ArrayList convertirRegistroObjeto(List<Registro> rs) {
        ArrayList<Demanda> demanda = new ArrayList<Demanda>();
        Agentes.AgenteDemanda temp = null;
        try {
            for (Registro registro : rs) {
                temp = new Agentes.AgenteDemanda();
                temp.setImpl(new Implementaciones.DemandaImpl());
                temp.setoid(registro.getCampo(oid).getValor());
                temp.setOIDProducto(registro.getCampo("OIDProducto").getValor());
                temp.setFechainicio(registro.getCampo("fechainicio").getValor());
                temp.setFechafin(registro.getCampo("fechafin").getValor());
                temp.setDemandareal(Double.parseDouble(registro.getCampo("demandareal").getValor()));
                temp.setDemandaactualizada(Double.parseDouble(registro.getCampo("demandaactualizada").getValor()));
                temp.setDemandapronosticada(Double.parseDouble(registro.getCampo("demandapronosticada").getValor()));
                temp.setFactorestacional(Double.parseDouble(registro.getCampo("factorestacional").getValor()));
                temp.setGananciaperiodo(Double.parseDouble(registro.getCampo("gananciaperiodo").getValor()));
                temp.setAnio(registro.getCampo("anio").getValor());
                temp.setPeriodo(Integer.parseInt(registro.getCampo("periodo").getValor()));
                temp.setTendencia(Double.parseDouble(registro.getCampo("tendencia").getValor()));
                temp.setErrorpromedio(Double.parseDouble(registro.getCampo("errorpromedio").getValor()));
                temp.setMse(Double.parseDouble(registro.getCampo("mse").getValor()));
                temp.setSenalrastreo(Double.parseDouble(registro.getCampo("senalrastreo").getValor()));
                temp.setCantidad(Integer.parseInt(registro.getCampo("cantidad").getValor()));
                demanda.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Exploto el IntermediarioDemanda");
            ex.printStackTrace();
        }
        return demanda;
    }

    @Override
    public ArrayList materializar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
