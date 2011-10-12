/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import Implementaciones.CategoriaImpl;
import Interfaces.Categoria;
import Persistencia.ObjetoPersistente;

/**
 *
 * @author diego
 */
public class AgenteCategoria implements Categoria, ObjetoPersistente {

    private CategoriaImpl impl;
    private String OIDCategoria;

    public AgenteCategoria() {
    }

    public CategoriaImpl getImpl() {
        return impl;
    }

    public void setImpl(CategoriaImpl impl) {
        this.impl = impl;
    }

    public int getCodigo() {
        return impl.getCodigo();
    }

    public void setCodigo(int codigo) {
      impl.setCodigo(codigo);
    }

    public String getNombre() {
       return impl.getNombre();
    }

    public void setNombre(String Nombre) {
        impl.setNombre(Nombre);
    }

    public void setoid(String oid) {
       this.OIDCategoria = oid;
    }

    public String getoid() {
      return OIDCategoria;
    }
}
