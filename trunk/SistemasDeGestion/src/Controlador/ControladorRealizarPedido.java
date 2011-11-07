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
    
    public ControladorRealizarPedido(ControladorPrincipal p) {
        ppal = p;
        pantalla = new PantallaRealizarPedido();
        experto = (ExpertoRealizarPedido) FabricaExperto.getInstancia().FabricarExperto("ExpertoRealizarPedido");
        pantalla.setVisible(true);
        p.add(pantalla);
    }

    void iniciar() {
        pantalla.getJbRealizarPedido().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
               pantalla.getJtPedidos().setModel( experto.iniciar());
            }
        });
    }
    
    
    
}
