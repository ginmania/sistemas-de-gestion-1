/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;

/**
 *
 * @author duende
 */
public interface Proveedor {

    public String getDireccion();

    public void setDireccion(String Direccion);

    public String getCUIT();

    public void setCUIT(String CUIT);

    public String getNombre();

    public void setNombre(String Nombre);

    public String getTelefono();

    public void setTelefono(String Telefono);

    public int getCodigo_proveedor();

    public void setCodigo_proveedor(int codigo_proveedor);

    public String getEmail();

    public void setEmail(String email);

    public int getbaja();

    public void setbaja(int baja);
    
}
