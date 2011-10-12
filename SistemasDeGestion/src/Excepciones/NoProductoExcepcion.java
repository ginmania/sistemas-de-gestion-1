/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author Matias
 */
public class NoProductoExcepcion extends Exception {
    
    public NoProductoExcepcion(){
        System.out.println("No hay productos");
    }  
}
