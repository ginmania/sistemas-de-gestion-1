/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Experto.ExpertoRealizarPedido;
import Experto.FabricaExperto;
import Pantalla.PantallaRealizarPedido;
import java.awt.event.ActionEvent;

/**
 *
 * @author duende
 */
public class ControladorRealizarPedido {
    private ControladorPrincipal ppal;
    private PantallaRealizarPedido pantalla;
    private ExpertoRealizarPedido experto;
    private ControladorRecibirPedido ctrlRecibir;
    
    public ControladorRealizarPedido(ControladorPrincipal p) {
        ppal = p;
        pantalla = new PantallaRealizarPedido();
        experto = (ExpertoRealizarPedido) FabricaExperto.getInstancia().FabricarExperto("ExpertoRealizarPedido");
        pantalla.setVisible(true);
        p.add(pantalla);
        
        pantalla.getJbRecibirPedido().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pantalla.setVisible(false);
                ppal.remove(pantalla);
               //para recibir los pedidos.
                ctrlRecibir = new ControladorRecibirPedido(ppal);
            }
        });
    }

    void iniciar() {
        pantalla.getJbRealizarPedido().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
               pantalla.getJtPedidos().setModel( experto.iniciar());
            }
        });        
    }
    
    
    
}
