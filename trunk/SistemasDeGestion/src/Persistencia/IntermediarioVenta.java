/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Agentes.AgenteCliente;
import Agentes.AgenteDetalleVenta;
import Agentes.AgenteProducto;
import Agentes.AgenteVenta;
import Interfaces.Cliente;
import Interfaces.DetalleVenta;
import Interfaces.Venta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class IntermediarioVenta extends IntermediarioRelacional {

    public IntermediarioVenta() {
        tabla = "Venta";
        oid = "OIDVenta";
    }

    @Override
    public String generarSQLOID(String oid) {
        return "SELECT * FROM " + tabla + " WHERE OIDVenta= " + oid;

    }

    @Override
    public Registro convertirObjetoRegistro(ObjetoPersistente objeto) {
        Registro rs = new Registro();
        AgenteVenta venta = (AgenteVenta) objeto;
        rs.addCampo(new Campo("OIDVenta", "'" + venta.getoid() + "'"));
        rs.addCampo(new Campo("OIDCliente", "'" + venta.getOIDCliente() + "'"));
        rs.addCampo(new Campo("fechaventa", "'" + venta.getFechaventa() + "'"));
        rs.addCampo(new Campo("numero", String.valueOf(venta.getNumero())));
        rs.addCampo(new Campo("total", String.valueOf(venta.getTotal())));
        return rs;

    }

    @Override
    public ArrayList convertirRegistroObjeto(List<Registro> rs) {
        ArrayList<Venta> venta = new ArrayList<Venta>();
        Agentes.AgenteVenta temp = null;
        try {

            for (Registro registro : rs) {
                temp = new Agentes.AgenteVenta();
                temp.setImpl(new Implementaciones.VentaImpl());
                temp.setoid(registro.getCampo("OIDVenta").getValor());
                temp.setOIDCliente(registro.getCampo("OIDCliente").getValor());
                temp.setFechaventa(registro.getCampo("fechaventa").getValor());
                temp.setNumero(Integer.parseInt(registro.getCampo("numero").getValor()));
                temp.setTotal(Double.parseDouble(registro.getCampo("total").getValor()));
                temp.setDetalleVentas(Fachada.getInstancia().buscar(DetalleVenta.class, FabricaCriterio.getInstancia().crearCriterio("OIDVenta", "=", temp.getoid())));
                //temp.setDetalleVentas(temp.getoid());
                venta.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Exploto el Intermediario de Ventas");
            ex.printStackTrace();
        }
        return venta;
    }

    @Override
    public boolean guardar(ObjetoPersistente objeto) {//objeto = venta        
        //asigno el OID relacionado del cliente
        AgenteCliente cliente = (AgenteCliente) ((Venta) objeto).getCliente();
        ((AgenteVenta) objeto).setOIDCliente(cliente.getoid());   
        //guardo la venta
        boolean guardado = super.guardar(objeto);        
        if (guardado) {
            List<DetalleVenta> detalleventas = ((Venta) objeto).getDetalleVenta();
            //para guardar el detalle necesito el OIDVenta y el OIDProducto
            for (int i=0; i<detalleventas.size();i++) {
                //asigno el OID relacionado de la venta
                String vta = objeto.getoid();
                AgenteDetalleVenta detalle=(AgenteDetalleVenta) detalleventas.get(i);
                detalle.setOIDVenta(vta);
                //asigno el OID relacionado del producto                
                AgenteProducto prod = (AgenteProducto)detalle.getProducto();
                detalle.setOIDProducto(prod.getoid());
                detalle.setCantidad(detalleventas.get(i).getCantidad());
                detalle.setPrecioUnitario(detalleventas.get(i).getPrecioUnitario());
                detalle.setPrecio(detalleventas.get(i).getPrecio());
                //guardo un detalle
                boolean res = FachadaInterna.getInstancia().guardar((ObjetoPersistente) detalle);
                if (res == false) {
                    guardado = false;                    
                }
            }
        }        
        return guardado;
    }
}