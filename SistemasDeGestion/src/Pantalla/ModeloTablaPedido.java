/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pantalla;

import Interfaces.DetallePedido;
import Interfaces.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author duende
 */
public class ModeloTablaPedido extends AbstractTableModel{
    private String NroPedido;
    private String OIDPedido;
    private String OIDProducto;
    private List <Pedido> pedidos;
    private String[][] detalles;
    private List<String> columnas;

    public ModeloTablaPedido() {
        //
        pedidos = new ArrayList<Pedido>();        
        columnas = new ArrayList<String>();
        columnas.add("NroPedido");
        columnas.add("Proveedor");
        columnas.add("Producto");
        columnas.add("Cantidad");
    }
    
    @Override
    public String getColumnName(int i) {
        return columnas.get(i);
    }

    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getValueAt(int i, int i1) {
        Pedido c = pedidos.get(i);
        switch (i1) {
            case 0:
                return c.getNroPedido();
            case 1:
                return c.getProveedor();
            case 2:
                return c.getDetallePedido();
            case 3:
                return c.getPendiente();            
            default:
                return "Error";

        }
    }
    
    public void addAll(List<Pedido> pd) {
        for (Pedido pedido : pd) {
            pedidos.add(pedido);
        }
        super.fireTableDataChanged();
    }
    
    public void addDetalle (List<DetallePedido> detalles){
        int fila = detalles.size();
        this.detalles = new String[fila][2];
        for(int i=0;i<detalles.size();i++){
            this.detalles[i][0]= detalles.get(i).getProducto().getDescripcionProducto();
            this.detalles[i][1]= String.valueOf(detalles.get(i).getCantidad());
        }
    }
}
