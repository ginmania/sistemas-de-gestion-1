package Pantalla;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.MenuElement;
import javax.swing.UIManager;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author diego
 */
public class FormatearGUI {

    protected static Font letra;
    protected static Color colorForeground;
    protected static Dimension tamanioCampo; //Tamaño del campo

    public static void formatear(JInternalFrame formulario) {
        try {
            formulario.setMaximum(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        formulario.setResizable(false);
        formulario.setClosable(true);

        formulario.setFont(letra);
        // TODO ver lo del fondo
        //formulario.setBackground(colorBackground);
        formulario.setForeground(colorForeground);
        formulario.setBorder(BorderFactory.createEmptyBorder());
        Component[] componentes = formulario.getContentPane().getComponents();
        for (int i = 0; i < componentes.length; i++) {
            formatear(componentes[i]);
        }
    }
 private static void establecerFuente(final Component componente) {
        if (componente instanceof JTabbedPane) {
            componente.setFont(Font.decode("SansSerif 14"));
        } else {
            componente.setFont(letra);
        }
    }
    private static void formatear(Component componente) {
        establecerFuente(componente);
        componente.setForeground(colorForeground);
        // TODO ver lo del fondo
        // componente.setBackground(colorBackground);
        if (componente instanceof JTextField) {
            ((JTextField) componente).setDisabledTextColor(Color.BLACK);
        } else if (componente instanceof JComboBox) {
            //TODO encontrar un modo elegante de hacer esto
            UIManager.put("ComboBox.disabledForeground", Color.BLACK);
        }
        if (componente instanceof JTextField) {
            componente.setPreferredSize(tamanioCampo);
        } else if (componente instanceof JTextArea || componente instanceof JList) {
            componente.setPreferredSize(new Dimension(componente.getPreferredSize().width, tamanioCampo.height));
        }
        // TODO ver que pasa con botones chicos
        /*else if(componente instanceof JButton){
        componente.setPreferredSize(new Dimension(tamanioCampo.width*3/4, tamanioCampo.height*2));
        }*/
        if (componente instanceof JComponent && !(componente instanceof JMenu)) {
            Component[] elementos = ((JComponent) componente).getComponents();
            if (elementos != null) {
                for (int i = 0; i < elementos.length; i++) {
                    formatear(elementos[i]);
                }
            }
        } else if (componente instanceof JMenu) {
            MenuElement[] elementos = ((JMenu) componente).getSubElements();
            if (elementos != null) {
                for (int i = 0; i < elementos.length; i++) {
                    formatear((JComponent) elementos[i]);
                }
            }
        }
    }
}
