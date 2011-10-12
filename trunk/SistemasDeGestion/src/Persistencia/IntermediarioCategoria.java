package Persistencia;

import Agentes.AgenteCategoria;
import Agentes.AgenteCliente;
import Interfaces.Categoria;
import Interfaces.Cliente;
import java.util.ArrayList;
import java.util.List;

public class IntermediarioCategoria extends IntermediarioRelacional {

    public IntermediarioCategoria() {
        tabla = "Categoria";
        oid = "OIDCategoria";
    }
    @Override
        public String generarSQLOID(String oid) {
        return "SELECT * FROM " + tabla + " WHERE OIDCategoria = " + oid;

    }
        private void busquedaoidquenoexisten(AgenteCategoria categoria) {
        String consulta = "SELECT * FROM Cliente_Proveedor WHERE  OIDCategoria =" + categoria.getoid();
//oid relacionado
//        List<Registro> registros = ejecutarSQLB(consulta);
//        for (Registro registro : registros) {
//            cliente.addOIDProveedor(registro.getCampo("OIDProveedor").getValor());
//
//        }
    }

    @Override
    public Registro convertirObjetoRegistro(ObjetoPersistente objeto) {
        Registro rs = new Registro();
        Agentes.AgenteCategoria categoria = (AgenteCategoria) objeto;
        rs.addCampo(new Campo("OIDCategoria", "'" + categoria.getoid() + "'"));
        rs.addCampo(new Campo("nombre", "'" + categoria.getNombre() + "'"));
        rs.addCampo(new Campo("codigo", String.valueOf(categoria.getCodigo())));
        return rs;
    }

    @Override
    public ArrayList convertirRegistroObjeto(List<Registro> rs) {
        ArrayList<Categoria> categoria = new ArrayList<Categoria>();
        Agentes.AgenteCategoria temp = null;
        try {
            for (Registro registro : rs) {
                temp = new Agentes.AgenteCategoria();
                temp.setImpl(new Implementaciones.CategoriaImpl());
                temp.setoid(registro.getCampo(oid).getValor());
                temp.setNombre(registro.getCampo("nombre").getValor());
                temp.setCodigo(Integer.parseInt(registro.getCampo("codigo").getValor()));
                categoria.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Exploto el IntermediarioCategoria");
        }
        return categoria;
    }

    @Override
    public ArrayList materializar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
