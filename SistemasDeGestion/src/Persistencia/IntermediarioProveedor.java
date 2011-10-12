package Persistencia;

import Agentes.AgenteProveedor;
import Interfaces.Proveedor;
import java.util.ArrayList;
import java.util.List;

public class IntermediarioProveedor extends IntermediarioRelacional {

    public IntermediarioProveedor() {
        tabla = "Proveedor";
        oid = "OIDProveedor";
    }

    @Override
    public String generarSQLOID(String oid) {
        return "SELECT * FROM " + tabla + " WHERE OIDProveedor = " + oid;
    }

    /*private void busquedaoidquenoexisten(AgenteProveedor ad) {
    String consulta = "SELECT * FROM Cliente_Proveedor WHERE  OIDProveedor =" + ad.getoid();
    
    List<Registro> registros = ejecutarSQLB(consulta);
    for (Registro registro : registros) {
    ad.addOIDCliente(registro.getCampo("OIDCliente").getValor());
    }
    }*/
    @Override
    public Registro convertirObjetoRegistro(ObjetoPersistente objeto) {
        Registro rs = new Registro();
        AgenteProveedor proveedor = (AgenteProveedor) objeto;
        rs.addCampo(new Campo("OIDProveedor", "'" + proveedor.getoid() + "'"));
        rs.addCampo(new Campo("Nombre", "'" + proveedor.getNombre() + "'"));
        rs.addCampo(new Campo("Direccion", "'" + proveedor.getDireccion() + "'"));
        rs.addCampo(new Campo("Telefono", "'" + proveedor.getTelefono() + "'"));
        rs.addCampo(new Campo("CUIT", "'" + proveedor.getCUIT() + "'"));
        //rs.addCampo(new Campo("Codigo_Proveedor", String.valueOf(proveedor.getCodigo_proveedor())));
        rs.addCampo(new Campo("email", "'" + proveedor.getEmail() + "'"));        
        rs.addCampo(new Campo("baja", "'" + String.valueOf(proveedor.getbaja()) + "'"));
        return rs;

    }

    @Override
    public ArrayList convertirRegistroObjeto(List<Registro> rs) {
        ArrayList<Proveedor> proveedor = new ArrayList<Proveedor>();
        Agentes.AgenteProveedor temp = null;
        try {
            for (Registro registro : rs) {
                temp = new Agentes.AgenteProveedor();
                temp.setImpl(new Implementaciones.ProveedorImpl());
                temp.setoid(registro.getCampo("OIDProveedor").getValor());
                temp.setNombre(registro.getCampo("Nombre").getValor());
                temp.setDireccion(registro.getCampo("Direccion").getValor());
                temp.setTelefono(registro.getCampo("Telefono").getValor());
                temp.setCUIT(registro.getCampo("CUIT").getValor());
                temp.setCodigo_proveedor(Integer.parseInt(registro.getCampo("Codigo_Proveedor").getValor()));
                temp.setEmail(registro.getCampo("email").getValor());
                temp.setbaja(Integer.parseInt(registro.getCampo("baja").getValor()));
                proveedor.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Exploto el IntermediarioProveedor");
            ex.printStackTrace();
        }
        return proveedor;

    }
}
