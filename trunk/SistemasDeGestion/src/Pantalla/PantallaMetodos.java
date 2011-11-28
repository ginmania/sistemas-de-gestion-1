/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaMetodos.java
 *
 * Created on 22-sep-2011, 18:39:29
 */

package Pantalla;

import com.toedter.calendar.JDateChooser;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author malayo
 */
public class PantallaMetodos extends javax.swing.JDialog {

    /** Creates new form PantallaMetodos */
    
    public PantallaMetodos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoDeMetodos = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        BotonCancelarMetodos = new javax.swing.JButton();
        botonSimple = new javax.swing.JRadioButton();
        botonTendencia = new javax.swing.JRadioButton();
        botonEstacionalidad = new javax.swing.JRadioButton();
        botonCalcularMetodos = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        comboProductos = new javax.swing.JComboBox();
        txFechadesde = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "####/##/##", '_');
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txFechahasta = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "####/##/##", '_');
        jLabel8 = new javax.swing.JLabel();
        txPeriodo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaResultado = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaResultado2 = new javax.swing.JTextArea();
        botonDE = new javax.swing.JCheckBox();
        botonSR = new javax.swing.JCheckBox();
        botonConsultar = new javax.swing.JButton();
        botonGuardar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Metodos de Suavizamiento");
        setAlwaysOnTop(true);

        jLabel1.setText("Metodos:");

        BotonCancelarMetodos.setText("Salir");

        grupoDeMetodos.add(botonSimple);
        botonSimple.setText("Simple");

        grupoDeMetodos.add(botonTendencia);
        botonTendencia.setText("Tendencia");

        grupoDeMetodos.add(botonEstacionalidad);
        botonEstacionalidad.setText("Estacionalidad");

        botonCalcularMetodos.setText("Calcular Problema");

        jLabel5.setText("Productos:");

        jLabel6.setText("Fecha Desde");

        jLabel7.setText("Fecha Hasta");

        jLabel8.setText("Periodos a Predecir");

        txPeriodo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txPeriodoFocusGained(evt);
            }
        });

        areaResultado.setColumns(20);
        areaResultado.setRows(5);
        jScrollPane1.setViewportView(areaResultado);

        areaResultado2.setColumns(20);
        areaResultado2.setRows(5);
        jScrollPane2.setViewportView(areaResultado2);

        botonDE.setText("Desviación Estandar");

        botonSR.setText("Señal de rastreo");

        botonConsultar.setText("Consultar Demanda");

        botonGuardar.setText("Guardar Demanda");

        jLabel2.setText("Resultado del metodo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(comboProductos, 0, 188, Short.MAX_VALUE)
                                    .addGap(133, 133, 133))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(botonEstacionalidad)
                                        .addComponent(botonSimple)
                                        .addComponent(botonTendencia))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txFechadesde, 0, 0, Short.MAX_VALUE)
                                    .addComponent(txFechahasta, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                                .addComponent(txPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(21, 21, 21))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botonCalcularMetodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botonConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botonGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BotonCancelarMetodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botonDE)
                                .addGap(50, 50, 50)
                                .addComponent(botonSR)))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botonConsultar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonCalcularMetodos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonGuardar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txFechadesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(txFechahasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotonCancelarMetodos))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(botonSimple)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonTendencia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonEstacionalidad))
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonDE)
                    .addComponent(botonSR)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txPeriodoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txPeriodoFocusGained
    // TODO add your handling code here:
}//GEN-LAST:event_txPeriodoFocusGained

    /**
    * @param args the command line arguments
    */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonCancelarMetodos;
    private javax.swing.JTextArea areaResultado;
    private javax.swing.JTextArea areaResultado2;
    private javax.swing.JButton botonCalcularMetodos;
    private javax.swing.JButton botonConsultar;
    private javax.swing.JCheckBox botonDE;
    private javax.swing.JRadioButton botonEstacionalidad;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JCheckBox botonSR;
    private javax.swing.JRadioButton botonSimple;
    private javax.swing.JRadioButton botonTendencia;
    private javax.swing.JComboBox comboProductos;
    private javax.swing.ButtonGroup grupoDeMetodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser txFechadesde;
    private com.toedter.calendar.JDateChooser txFechahasta;
    private javax.swing.JTextField txPeriodo;
    // End of variables declaration//GEN-END:variables

    public JButton getBotonConsultar() {
        return botonConsultar;
    }

    public void setBotonConsultar(JButton botonConsultar) {
        this.botonConsultar = botonConsultar;
    }

    public JButton getBotonGuardar() {
        return botonGuardar;
    }

    public void setBotonGuardar(JButton botonGuardar) {
        this.botonGuardar = botonGuardar;
    }

    public JTextArea getAreaResultado2() {
        return areaResultado2;
    }

    public void setAreaResultado2(JTextArea areaResultado2) {
        this.areaResultado2 = areaResultado2;
    }

    public JCheckBox getBotonDE() {
        return botonDE;
    }

    public void setBotonDE(JCheckBox botonDE) {
        this.botonDE = botonDE;
    }

    public JCheckBox getBotonSR() {
        return botonSR;
    }

    public void setBotonSR(JCheckBox botonSR) {
        this.botonSR = botonSR;
    }

    public JTextArea getAreaResultado() {
        return areaResultado;
    }

    public void setAreaResultado(JTextArea areaResultado) {
        this.areaResultado = areaResultado;
    }

    public JDateChooser getTxFechadesde() {
        return txFechadesde;
    }

    public void setTxFechadesde(JDateChooser txFechadesde) {
        this.txFechadesde = txFechadesde;
    }

    public JDateChooser getTxFechahasta() {
        return txFechahasta;
    }

    public void setTxFechahasta(JDateChooser txFechahasta) {
        this.txFechahasta = txFechahasta;
    }

    public JTextField getTxPeriodo() {
        return txPeriodo;
    }

    public void setTxPeriodo(JTextField txPeriodo) {
        this.txPeriodo = txPeriodo;
    }

    public JComboBox getComboProductos() {
        return comboProductos;
    }

    public void setComboProductos(JComboBox comboProductos) {
        this.comboProductos = comboProductos;
    }

    public JButton getBotonCancelarMetodos() {
        return BotonCancelarMetodos;
    }

    public void setBotonCancelarMetodos(JButton BotonCancelarMetodos) {
        this.BotonCancelarMetodos = BotonCancelarMetodos;
    }

    public JButton getBotonCalcularMetodos() {
        return botonCalcularMetodos;
    }

    public void setBotonCalcularMetodos(JButton botonCalcularMetodos) {
        this.botonCalcularMetodos = botonCalcularMetodos;
    }

    public JRadioButton getBotonEstacionalidad() {
        return botonEstacionalidad;
    }

    public void setBotonEstacionalidad(JRadioButton botonEstacionalidad) {
        this.botonEstacionalidad = botonEstacionalidad;
    }

    public JRadioButton getBotonSimple() {
        return botonSimple;
    }

    public void setBotonSimple(JRadioButton botonSimple) {
        this.botonSimple = botonSimple;
    }

    public JRadioButton getBotonTendencia() {
        return botonTendencia;
    }

    public void setBotonTendencia(JRadioButton botonTendencia) {
        this.botonTendencia = botonTendencia;
    }

    public ButtonGroup getGrupoDeMetodos() {
        return grupoDeMetodos;
    }

    public void setGrupoDeMetodos(ButtonGroup grupoDeMetodos) {
        this.grupoDeMetodos = grupoDeMetodos;
    }

}