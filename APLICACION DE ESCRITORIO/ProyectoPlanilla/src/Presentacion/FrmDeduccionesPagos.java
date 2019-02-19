/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Entidades.ClsDeduccionesPagos;
import Entidades.ClsRetorno;
import Logica.ClsLogicaDeduccionesPagos;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thomas Chevez
 */
public class FrmDeduccionesPagos extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmDeduccionesPagos
     */
    public FrmDeduccionesPagos() {
        initComponents();
        this.closable = true;
        txtidDeduccionPago.setVisible(false);
        Limpiar();
        CargarLista();
    }

    private void Limpiar() {
        txtDetalleEspecifiico.setText("");
        txtDetalleGeneral.setText("");
        txtMonto.setText("");
        txtTipoDeduccion.setSelectedIndex(0);
        txtidDeduccionPago.setText("-1");
        cmb_esDeduccion.setSelectedIndex(0);
    }

    private ClsDeduccionesPagos LeerDatos() {
        //Variables
        ClsDeduccionesPagos vlo_DeduccionesPagos = new ClsDeduccionesPagos();

        //Inicio
        vlo_DeduccionesPagos.setVgc_DeduccionDetallada(txtDetalleEspecifiico.getText());
        vlo_DeduccionesPagos.setVgc_DeduccionGeneral(txtDetalleGeneral.getText());
        vlo_DeduccionesPagos.setVgn_Monto(Double.parseDouble(txtMonto.getText()));
        vlo_DeduccionesPagos.setVgn_idDeduccionPago(Integer.parseInt(txtidDeduccionPago.getText()));

        //Se verifica que tipo de transaccion se va hacer.
        if (cmb_esDeduccion.getSelectedIndex() == 1) {
            vlo_DeduccionesPagos.setVgc_EsDeduccion(true);
        } else if (cmb_esDeduccion.getSelectedIndex() == 2) {
            vlo_DeduccionesPagos.setVgc_EsDeduccion(false);
        }

        //Se verifica que tipo de monto se va utilizar si estatico o porcentaje
        if (txtTipoDeduccion.getSelectedIndex() == 1) {
            vlo_DeduccionesPagos.setVgc_tipo("POR");
        } else if (txtTipoDeduccion.getSelectedIndex() == 2) {
            vlo_DeduccionesPagos.setVgc_tipo("DEC");
        }

        return vlo_DeduccionesPagos;
    }

    private void CargarLista() {
        //Variables
        ClsLogicaDeduccionesPagos vlo_LogicaDeduccionesPagos = new ClsLogicaDeduccionesPagos();
        ResultSet vlo_RS;
        DefaultTableModel Modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        //Inicio
        jTable1.setModel(Modelo);
        Modelo.addColumn("");
        Modelo.addColumn("Concepto");
        Modelo.addColumn("Deducción");
        Modelo.addColumn("Pago");
        Modelo.addColumn("Tipo");
        Modelo.addColumn("Monto");

        try {
            vlo_RS = vlo_LogicaDeduccionesPagos.ListaPagosDeducciones();
            Object[] fila = new Object[6];
            while (vlo_RS.next()) {
                fila[0] = vlo_RS.getObject(1);
                fila[1] = vlo_RS.getObject(2);
                if (vlo_RS.getBoolean(4)) {
                    fila[2] = "Si";
                    fila[3] = "No";
                } else {
                    fila[2] = "No";
                    fila[3] = "Si";
                }
                if (vlo_RS.getString(5).equals("POR")) {
                    fila[4] = "%";
                } else {
                    fila[4] = "₡";
                }
                fila[5] = vlo_RS.getObject(6);

                Modelo.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la lista de elementos. " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDetalleGeneral = new javax.swing.JTextField();
        txtDetalleEspecifiico = new javax.swing.JTextField();
        cmb_esDeduccion = new javax.swing.JComboBox<>();
        txtTipoDeduccion = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnLimpiiar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        txtidDeduccionPago = new javax.swing.JTextField();

        jButton1.setText("jButton1");

        setClosable(true);
        setTitle("Configuración");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Deducciones y Pagos"));

        jLabel1.setText("Concepto:");

        jLabel2.setText("Información Adicional:");

        jLabel3.setText("Tipo de concepto:");

        txtDetalleGeneral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDetalleGeneralKeyTyped(evt);
            }
        });

        txtDetalleEspecifiico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDetalleEspecifiicoKeyTyped(evt);
            }
        });

        cmb_esDeduccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Deducción", "Pago" }));

        txtTipoDeduccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Porcentaje", "Constante" }));

        jLabel4.setText("Tipo de Monto:");

        jLabel5.setText("Monto:");

        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnLimpiiar.setText("Limpiar");
        btnLimpiiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiiarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDetalleGeneral, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDetalleEspecifiico)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_esDeduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtTipoDeduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtMonto)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDetalleGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDetalleEspecifiico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_esDeduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipoDeduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnLimpiiar)
                    .addComponent(btnEliminar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Deducciones y Pagos"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(txtidDeduccionPago, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(txtidDeduccionPago, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDetalleGeneralKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDetalleGeneralKeyTyped
        if (txtDetalleGeneral.getText().length() > 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDetalleGeneralKeyTyped

    private void txtDetalleEspecifiicoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDetalleEspecifiicoKeyTyped
        if (txtDetalleEspecifiico.getText().length() > 500) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDetalleEspecifiicoKeyTyped

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        char e = evt.getKeyChar();
        if (!((e >= '0' && e <= '9') || e == '.')) {
            evt.consume();
        }
        //boolean correcto = true;

        //correcto = esDecimal(txtMonto.getText());
//        if (correcto == true) {
//            evt.consume();
//        }
    }//GEN-LAST:event_txtMontoKeyTyped

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //Varaibles
        ClsRetorno vlo_Retorno = new ClsRetorno();
        ClsDeduccionesPagos vlo_DeduccionesPagos = new ClsDeduccionesPagos();
        ClsLogicaDeduccionesPagos vlo_LogicaDeduccionesPagos = new ClsLogicaDeduccionesPagos();

        //Inicio
        if (txtDetalleEspecifiico.getText().equals("") || txtDetalleGeneral.getText().equals("") || txtMonto.getText().equals("") || txtTipoDeduccion.getSelectedIndex() == 0 || cmb_esDeduccion.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Todos los campos son requeridos.");
        } else {
            try {
                vlo_DeduccionesPagos = LeerDatos();
                vlo_Retorno = vlo_LogicaDeduccionesPagos.GuardarDeduccionesPagos(vlo_DeduccionesPagos);
                JOptionPane.showMessageDialog(this, vlo_Retorno.getVgc_Mensaje());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            Limpiar();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        //Variables
        int vln_id;
        ClsLogicaDeduccionesPagos vlo_LogicaDeduccionesPagos = new ClsLogicaDeduccionesPagos();
        ClsDeduccionesPagos vlo_DeduccionesPagos = new ClsDeduccionesPagos();

        //Inicio
        if (evt.getClickCount() == 2) {
            vln_id = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            try {
                vlo_DeduccionesPagos = vlo_LogicaDeduccionesPagos.ObteDeduccionesPagos(vln_id);
                txtDetalleGeneral.setText(vlo_DeduccionesPagos.getVgc_DeduccionGeneral());
                txtDetalleEspecifiico.setText(vlo_DeduccionesPagos.getVgc_DeduccionDetallada());
                txtMonto.setText(Double.toString(vlo_DeduccionesPagos.getVgn_Monto()));
                txtidDeduccionPago.setText(Integer.toString(vlo_DeduccionesPagos.getVgn_idDeduccionPago()));
                if (vlo_DeduccionesPagos.isVgc_EsDeduccion()) {
                    txtTipoDeduccion.setSelectedIndex(1);
                } else {
                    txtTipoDeduccion.setSelectedIndex(2);
                }

                if (vlo_DeduccionesPagos.getVgc_tipo().equals("POR")) {
                    cmb_esDeduccion.setSelectedIndex(1);
                } else {
                    cmb_esDeduccion.setSelectedIndex(2);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al tratar de cargar el valor seleccionado. " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnLimpiiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnLimpiiarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //Variables
        ClsLogicaDeduccionesPagos vlo_LogicaDeduccionesPagos = new ClsLogicaDeduccionesPagos();
        
        //Inicio
        try {
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

//Devuelve true si la cadena que llega es un numero decimal, false en caso contrario
//    public boolean esDecimal(String cad) {
//        try {
//            Double.parseDouble(cad);
//            return true;
//        } catch (NumberFormatException nfe) {
//            return false;
//        }
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cmb_esDeduccion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtDetalleEspecifiico;
    private javax.swing.JTextField txtDetalleGeneral;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JComboBox<String> txtTipoDeduccion;
    private javax.swing.JTextField txtidDeduccionPago;
    // End of variables declaration//GEN-END:variables
}
