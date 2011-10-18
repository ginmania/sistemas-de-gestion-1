package Persistencia;

import Agentes.AgenteProducto;
import Agentes.AgenteStock;
import Interfaces.Demanda;
import Interfaces.Producto;
import Interfaces.ProductoProveedor;
import Interfaces.Proveedor;
import java.util.ArrayList;
import java.util.List;

public class IntermediarioProducto extends IntermediarioRelacional {

    public IntermediarioProducto() {
        tabla = "Producto";
        oid = "OIDProducto";
    }

    private void busquedaoidquenoexisten(AgenteProducto aproducto) {
        String consulta = "SELECT * FROM ProductoProveedor WHERE  OIDProducto =" + aproducto.getoid();

        List<Registro> registros = ejecutarSQLB(consulta);
        for (Registro registro : registros) {
            aproducto.addOIDProveedor(registro.getCampo("OIDProveedor").getValor());

        }
    }

    @Override
    public String generarSQLOID(String oid) {
        return "SELECT * FROM " + tabla + " WHERE OIDProducto= " + oid;

    }

    @Override
    public Registro convertirObjetoRegistro(ObjetoPersistente objeto) {
        Registro rs = new Registro();
        AgenteProducto producto = (AgenteProducto) objeto;
        rs.addCampo(new Campo("OIDProducto", "'" + producto.getoid() + "'"));
        rs.addCampo(new Campo("codigo", String.valueOf(producto.getCodigoProducto())));
        rs.addCampo(new Campo("nombre", "'" + producto.getNombreProducto() + "'"));
        rs.addCampo(new Campo("descripcion", "'" + producto.getDescripcionProducto() + "'"));
        rs.addCampo(new Campo("preciocompra", String.valueOf(producto.getPrecioCompra())));
        rs.addCampo(new Campo("precioventa", String.valueOf(producto.getPrecioVenta())));
        rs.addCampo(new Campo("OIDStock", "'" + ((AgenteStock) producto.getStock()).getoid() + "'"));
        rs.addCampo(new Campo("clasifABC","'"+producto.getClasifABC()+"'"));
        rs.addCampo(new Campo("baja", "'" + String.valueOf(producto.getbaja() + "'")));
        return rs;

    }

    @Override
    public ArrayList convertirRegistroObjeto(List<Registro> rs) {
        ArrayList<Producto> producto = new ArrayList<Producto>();
        Agentes.AgenteProducto temp = null;
        try {
            for (Registro registro : rs) {
                temp = new Agentes.AgenteProducto();
                temp.setImpl(new Implementaciones.ProductoImpl());
                temp.setoid(registro.getCampo(oid).getValor());
                temp.setCodigoProducto(Integer.parseInt(registro.getCampo("codigo").getValor()));
                temp.setNombreProducto(registro.getCampo("nombre").getValor());
                temp.setDescripcionProducto(registro.getCampo("descripcion").getValor());
                temp.setPrecioCompra((Double) (registro.getCampo("preciocompra").getValor(Double.class)));
                temp.setPrecioVenta((Double) (registro.getCampo("precioventa").getValor(Double.class)));
                temp.setOIDStock(registro.getCampo("OIDStock").getValor());
                String valor = registro.getCampo("clasifABC").getValor();  
                temp.setClasifABC(valor.charAt(0));
                temp.setbaja(Integer.parseInt(registro.getCampo("baja").getValor()));
                temp.setDemandas(Fachada.getInstancia().buscar(Demanda.class, FabricaCriterio.getInstancia().crearCriterio("OIDProducto", "=", temp.getoid())));
                busquedaoidquenoexisten(temp);
//                temp.setProveedors(Fachada.getInstancia().buscar(Proveedor.class, FabricaCriterio.getInstancia().crearCriterio("OIDProducto", "=", temp.getoid())));
                producto.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Exploto el Intermediario Producto");
            ex.printStackTrace();
        }

        return producto;
    }
//    @Override
//    public boolean guardar(ObjetoPersistente objeto) {
//        try {
//            return FachadaInterna.getInstancia().guardar((ObjetoPersistente) ((Producto) objeto).getStock());
//        } catch (IndexOutOfBoundsException ex) {
//            return true;
//        }
    //}
}
