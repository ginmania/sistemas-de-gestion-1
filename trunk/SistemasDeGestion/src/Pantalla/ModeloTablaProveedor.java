/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pantalla;

import Interfaces.Proveedor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author duende
 */
public class ModeloTablaProveedor extends AbstractTableModel{
     private List<Proveedor> proveedor;
    private List<String> columnas;
    
    public ModeloTablaProveedor() {
        proveedor = new ArrayList<Proveedor>();
        columnas = new ArrayList<String>();
        columnas.add("Codigo_Proveedor");
        columnas.add("Nombre");
        columnas.add("Direccion");
        columnas.add("CUIT");
        columnas.add("Telefono");        
        columnas.add("email");
    }
    
    @Override
    public String getColumnName(int i) {
        return columnas.get(i);
    }
    
    @Override
    public int getRowCount() {
        return proveedor.size();

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
        Proveedor c = proveedor.get(i);
        switch (i1) {
            case 0:
                return c.getCodigo_proveedor();
            case 1:
                return c.getNombre();
            case 2:
                return c.getDireccion();
            case 3:
                return c.getCUIT();
            case 4:
                return c.getTelefono();
            case 5:
                return c.getEmail();            
            default:
                return "Error";

        }
    }

    public void addAll(List<Proveedor> provs) {
        for (Proveedor P : provs) {
            proveedor.add(P);
        }
        super.fireTableDataChanged();
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedor.clear();
        addAll(proveedores);
    }
    public Proveedor getProveedor(int fila){
       return proveedor.get(fila);
    }
    
}
