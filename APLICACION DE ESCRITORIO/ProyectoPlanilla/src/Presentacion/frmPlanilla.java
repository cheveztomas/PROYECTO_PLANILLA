/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Entidades.ClsPlanilla;
import Entidades.ClsRetorno;
import Logica.ClsLogicaPlanilla;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thomas Chevez
 */
public class frmPlanilla extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmPlanilla
     */
    public frmPlanilla() {
        initComponents();
        this.closable = true;
        txt_idPlanillas.setVisible(false);
        //jTable1.setVisible(false);
        //jTable1.setTableHeader(null);
        DefaultTableModel Modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TableColumnModelListener CModel;

        //Inicio
        jTable1.setModel(Modelo);
        Modelo.addColumn("");
        Modelo.addColumn("Nombre");
        Modelo.addColumn("Salario Neto");
        Modelo.addColumn("Salario Bruto");
    }

    private void CargarListaDetallesPlanillas() {
        //Variables
        ClsLogicaPlanilla vlo_LogicaPlanilla = new ClsLogicaPlanilla();
        ResultSet vlo_RS;
        DefaultTableModel Modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TableColumnModelListener CModel;

        //Inicio
        jTable1.setModel(Modelo);
        Modelo.addColumn("");
        Modelo.addColumn("Nombre");
        Modelo.addColumn("Salario Neto");
        Modelo.addColumn("Salario Bruto");

        Object[] fila = new Object[4];
        
       //jTable1.settabl;
        try {
            vlo_RS = vlo_LogicaPlanilla.ListaDetallesPLanilla(Integer.parseInt(txt_idPlanillas.getText()));
            while (vlo_RS.next()) {
                for (int i = 0; i < 4; i++) {
                    fila[i] = vlo_RS.getObject(i + 1);
                }
                Modelo.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar los empleados de la planilla.");
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

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        txt_idPlanillas = new javax.swing.JTextField();

        setTitle("Generar Planilla");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Generar Planilla"));

        jButton1.setText("Generar Planila");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        jScrollPane1.setViewportView(jTable1);

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
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txt_idPlanillas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(txt_idPlanillas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Variables
        ClsLogicaPlanilla vlo_LogicaPlanilla = new ClsLogicaPlanilla();
        ClsRetorno vlo_Retorno = new ClsRetorno();
        ClsPlanilla vlo_Planilla;

        //Inicio
        if (jDateChooser2.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione la fecha para la planilla.");
        } else {
            try {
                vlo_Planilla = new ClsPlanilla();
                vlo_Planilla.setVgn_idPlanilla(-1);
                String fechaString = new SimpleDateFormat("yyyyMMdd").format(jDateChooser2.getDate());
                SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
                Date parsed = (Date) formato.parse(fechaString);
                java.sql.Date sql = new java.sql.Date(parsed.getTime());
                vlo_Planilla.setVgf_Fecha(sql);

                vlo_Retorno = vlo_LogicaPlanilla.GenerarPlanilla(vlo_Planilla);
                JOptionPane.showMessageDialog(this, vlo_Retorno.getVgc_Mensaje());
                txt_idPlanillas.setText(Integer.toString(vlo_Retorno.getVgc_ID()));
                jTable1.setVisible(true);
                //jTable1.setTableHeader(null);
                CargarListaDetallesPlanillas();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_idPlanillas;
    // End of variables declaration//GEN-END:variables
}
