/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pantalla;

import Interfaces.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Matias
 */
public class ModeloTablaProducto extends AbstractTableModel {

    private List<Producto> productos;
    private List<String> columnas;

    public ModeloTablaProducto() {
        productos = new ArrayList<Producto>();
        columnas = new ArrayList<String>();
        columnas.add("Codigo");
        columnas.add("Nombre");
        columnas.add("Descripcion");
        columnas.add("PrecioCompra");
        columnas.add("PrecioVenta");
        columnas.add("Cantidad Minima");
        columnas.add("Cantidad");
    }

    @Override
    public String getColumnName(int i) {
        return columnas.get(i);
    }

    @Override
    public int getRowCount() {
        return productos.size();

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
        Producto p = productos.get(i);
        switch (i1) {
            case 0:
                return p.getCodigoProducto();
            case 1:
                return p.getNombreProducto();
            case 2:
                return p.getDescripcionProducto();
            case 3:
                return p.getPrecioCompra();
            case 4:
                return p.getPrecioVenta();
            case 5:
                return p.getStock().getCantidadMinima();
            case 6:
                return p.getStock().getCantidad();
            default:
                return "Error";

        }
    }

    public void addAll(List<Producto> prod) {
        for (Producto producto : prod) {
            productos.add(producto);
        }
        super.fireTableDataChanged();
    }

    public void setProducto(List<Producto> productos) {
        this.productos.clear();
        addAll(productos);
    }

    public Producto getProducto(int fila) {
        return productos.get(fila);
    }
}
