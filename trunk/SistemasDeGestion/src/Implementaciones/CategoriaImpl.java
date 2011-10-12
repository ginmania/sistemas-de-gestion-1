/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.Categoria;

/**
 *
 * @author diego
 */
public class CategoriaImpl implements Categoria {
    private int codigo;
    private String nombre;

    public CategoriaImpl() {
    }

    public int getCodigo() {
       return codigo;
    }

    public void setCodigo(int codigo) {
      this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre= Nombre;
    }
    
}
