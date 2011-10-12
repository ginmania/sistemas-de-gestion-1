/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Experto;

import Interfaces.Proveedor;
import Persistencia.Criterio;
import Persistencia.Fachada;
import Persistencia.ObjetoPersistente;
import java.util.ArrayList;


/**
 *
 * @author duende
 */
public class ExpertoProveedor implements Experto{
    private static ArrayList<Proveedor> VecProveedores = new ArrayList<Proveedor>();

    public static ArrayList<Proveedor> buscarProveedor(String atributo,String busca, String op) {
        ArrayList<Proveedor> resultado = new ArrayList<Proveedor>();
        int i = 0;
        VecProveedores.clear();
        Criterio c2 = Fachada.getInstancia().crearCriterio(atributo,op,busca);
        System.out.print(c2);//muestro que busco
        resultado = Fachada.getInstancia().buscar(Proveedor.class, c2);  
        while (resultado.size()!= i){
            VecProveedores.add(resultado.get(i));
            i++;
        }
        return VecProveedores;        
    }
    
    public static Proveedor buscarUnProveedor(String atributo,String busca, String op) {
        Proveedor res = null;
        ArrayList <Proveedor> resultado;
        int i = 0;
        VecProveedores.clear();
        Criterio c2 = Fachada.getInstancia().crearCriterio(atributo,op,busca);
        System.out.print(c2);//muestro que busco
        resultado = Fachada.getInstancia().buscar(Proveedor.class, c2); 
        if(resultado.size()==1)
            res = resultado.get(0);
        else
            System.out.print("Existen 2 proveedores con el mismo código");
        return res;       
    }
    
    public static ArrayList<Proveedor> ListarProveedor() {
        ArrayList<Proveedor> resultado = new ArrayList<Proveedor>();
        VecProveedores.clear();
        int i = 0;    
        //solo me traigo los proveedores activos
        Criterio c2 = Fachada.getInstancia().crearCriterio("baja","=",0);
        resultado = Fachada.getInstancia().buscar(Proveedor.class, c2);
        while (resultado.size()!= i){
            VecProveedores.add(resultado.get(i));
            i++;
        }
        return VecProveedores;        
    }

    public boolean InsertarProveedor(String Nombre, String CUIT, String Direccion, String Telefono, String email){
        Proveedor nuevo = (Proveedor) FabricaEntidad.getInstancia().FabricarEntidad(Proveedor.class);
        boolean listo = false;
        nuevo.setNombre(Nombre);
        nuevo.setCUIT(CUIT);
        nuevo.setDireccion(Direccion);
        nuevo.setTelefono(Telefono);
        nuevo.setEmail(email);
        nuevo.setbaja(0);
        listo = Fachada.getInstancia().guardar((ObjetoPersistente)nuevo);
        return listo = true;
    };

    public void EliminarProveedor(Proveedor prov) {
        prov.setbaja(1);
        boolean eliminado = Fachada.getInstancia().guardar((ObjetoPersistente)prov);
    }
    
    public boolean ModificarProveedor(Proveedor modifica){            
        boolean guardar = Fachada.getInstancia().guardar((ObjetoPersistente)modifica);        
        return guardar;        
    }

}
