/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.DTO_Venta;
import Interfaces.DetalleVenta;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author duende
 */
class ModeloTablaVenta extends AbstractTableModel{
    private List<DTO_Venta> venta;    
    private List<String> columnas;
    private Hashtable detalles;
    

    public ModeloTablaVenta() {
        venta = new ArrayList<DTO_Venta>();
        detalles = new Hashtable();
        columnas = new ArrayList<String>();
        columnas.add("NroVenta"); 
        columnas.add("Fecha");        
        columnas.add("Cliente");
        columnas.add("Total");
        //columnas.add("Precio Total");       
    }

    @Override
    public String getColumnName(int i) {
        return columnas.get(i);
    }

    @Override
    public int getRowCount() {
        return venta.size();
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
        DTO_Venta d = venta.get(i);
        switch (i1) {
            case 0:
                return d.getNroVenta();
            case 1:
                return d.getFecha();
            case 2:
                return d.getNombreCliente();                
            case 3:
                return d.getTotal();
            /*case 4:
                return d.getPrecio(); 
            case 5:
                return d.getNroVenta();
            case 6:
                return d.getVta();
            case 7:
                return d.getProd();   */             
            default:
                return "Error";

        }
    }   
    
    public void add(DTO_Venta Vta){                    
        boolean add = venta.add(Vta);
        
        for(int i=0;i<Vta.getDetalle().size();i++)
            detalles.put(Vta.getNroVenta(),Vta.getDetalle().get(i));
        
        if(add == true)
           this.fireTableDataChanged();
        else
            System.out.println("Error al llenar "+this);
    } 
    
    public List<DetalleVenta> getDetalles(int nroVenta){
        return (List<DetalleVenta>) this.detalles.get(nroVenta);
    }

    public int size() {
        return venta.size();
    }

    public boolean isEmpty() {
        return venta.isEmpty();
    }

    
    
}
