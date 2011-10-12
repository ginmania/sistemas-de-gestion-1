/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pantalla;

import Interfaces.Cliente;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author diego
 */
public class ModeloTablaCliente extends AbstractTableModel {

    private List<Cliente> clientes;
    private List<String> columnas;

    public ModeloTablaCliente() {
        clientes = new ArrayList<Cliente>();
        columnas = new ArrayList<String>();
        columnas.add("Nombre");
        columnas.add("Apellido");
        columnas.add("DNI");
        columnas.add("CUIT");
        columnas.add("Direccion");
        columnas.add("Tel. Fijo");
        columnas.add("Celular");
        columnas.add("email");
    }

    @Override
    public String getColumnName(int i) {
        return columnas.get(i);
    }

    @Override
    public int getRowCount() {
        return clientes.size();

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
        Cliente c = clientes.get(i);
        switch (i1) {
            case 0:
                return c.getNombre();
            case 1:
                return c.getApellido();
            case 2:
                return c.getdni();
            case 3:
                return c.getCUIT();
            case 4:
                return c.getDireccion();
            case 5:
                return c.getTelefono_Fijo();
            case 6:
                return c.getCelular();
            case 7:
                return c.getemail();
            default:
                return "Error";

        }
    }

    public void addAll(List<Cliente> cli) {
        for (Cliente cliente : cli) {
            clientes.add(cliente);
        }
        super.fireTableDataChanged();
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes.clear();
        addAll(clientes);
    }
    public Cliente getCliente(int fila){
       return clientes.get(fila);
    }
}
