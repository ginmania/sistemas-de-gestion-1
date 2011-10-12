/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.Cliente;
import Interfaces.DetalleVenta;
import Interfaces.Venta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class VentaImpl implements Venta{
    
    private String fechaventa;
    private int numero;
    private double total;
    private List<DetalleVenta> detalleventas;
    private Cliente cliente;

    public VentaImpl() {
    }
    

    public String getFechaventa() {
        return fechaventa;
    }

    public void setFechaventa(String fechaventa) {
        this.fechaventa = fechaventa;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
       this.numero = numero;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
       this.total = total;
    }

    public List<DetalleVenta> getDetalleVenta() {
        return detalleventas;
    }

    public void setDetalleVentas(List<DetalleVenta> detalleventa) {
         if (detalleventas == null) {
            detalleventas = new ArrayList<DetalleVenta>();
        }
        detalleventas.addAll(detalleventa);
    }

    public void setDetalleVenta(DetalleVenta detalleventa) {
      if (detalleventas == null) {
            detalleventas = new ArrayList<DetalleVenta>();
        }
        detalleventas.add(detalleventa);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
