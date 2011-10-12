
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pantalla;

import Controlador.DTO_DetalleVenta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author duende
 */
public class ModeloTablaDetalleVenta extends AbstractTableModel{
    private List<DTO_DetalleVenta> detalle;    
    private List<String> columnas;

    public ModeloTablaDetalleVenta() {
        detalle = new ArrayList<DTO_DetalleVenta>();
        columnas = new ArrayList<String>();
        columnas.add("CodigoProducto"); 
        columnas.add("Producto");        
        columnas.add("Cantidad");
        columnas.add("Precio Unitario");
        columnas.add("Precio Total");       
    }

    @Override
    public String getColumnName(int i) {
        return columnas.get(i);
    }

    @Override
    public int getRowCount() {
        return detalle.size();
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public int getColumnCount() {
        return columnas.size();
    }

    public Object getValueAt(int i, int i1) {
        DTO_DetalleVenta d = detalle.get(i);
        switch (i1) {
            case 0:
                return d.getCodProducto();
            case 1:
                return d.getNombreProducto();
            case 2:
                return d.getCantidad();                
            case 3:
                return d.getPrecioU();
            case 4:
                return d.getPrecio(); 
            case 5:
                return d.getNroVenta();
            case 6:
                return d.getVta();
            case 7:
                return d.getProd();                
            default:
                return "Error";

        }
    }

   /* public void add(Producto prod, int cant ){
            DetalleVentaImpl detVta = new DetalleVentaImpl(prod,cant);         
            detalle.add(detVta);        
    }*/
    
    public void add(DTO_DetalleVenta detVta){                    
        boolean add = detalle.add(detVta);
        if(add == true)
           this.fireTableDataChanged();
        else
            System.out.println("Error al llenar "+this);
    } 

    public int size() {
        return detalle.size();
    }

    public boolean isEmpty() {
        return detalle.isEmpty();
    }      
}
