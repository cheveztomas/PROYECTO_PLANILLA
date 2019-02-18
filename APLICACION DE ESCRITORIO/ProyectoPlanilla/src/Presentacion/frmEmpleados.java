/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Entidades.ClsEmpleados;
import Logica.ClsLogicaEmpleado;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thomas Chevez
 */
public class frmEmpleados extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmEmpleados
     */
    public frmEmpleados() {
        initComponents();
        txt_idCliente.setVisible(false);
        this.closable = true;
        Limpiar();
        //Se oculta la primer columna la cual tiene el id.
        CargarListaEmpledos("");
        //tbl_Empleados.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        //tbl_Empleados.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
    }
    
    private void Limpiar() {
        txtBuscar.setText("");
        txtCedula.setText("");
        txtCorreo.setText("");
        txtNombre.setText("");
        txtNumeroCuenta.setText("");
        txtPrimerApellido.setText("");
        txtTelefono.setText("");
        txt_idCliente.setText("-1");
        //cldFecha.setEnabled(false);
    }
    
    private ClsEmpleados LeerDatos() throws ParseException {
        //variables
        ClsEmpleados vlo_Empleados = new ClsEmpleados();

        //inicio
        vlo_Empleados.setVgc_cedula(txtCedula.getText());
        vlo_Empleados.setVgc_correo(txtCorreo.getText());
        vlo_Empleados.setVgc_nombre(txtNombre.getText());
        vlo_Empleados.setVgc_numeroCuenta(txtNumeroCuenta.getText());
        vlo_Empleados.setVgc_primerApellido(txtPrimerApellido.getText());
        vlo_Empleados.setVgc_segundoApellido(txtSegundoApellidio.getText());
        vlo_Empleados.setVgc_telefono(txtTelefono.getText());
        vlo_Empleados.setVgn_idEmpleado(Integer.parseInt(txt_idCliente.getText()));
        String fechaString = new SimpleDateFormat("yyyyMMdd").format(cldFecha.getDate());
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        Date parsed = (Date) formato.parse(fechaString);
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        vlo_Empleados.setVgf_fechaContratacion(sql);
        
        return vlo_Empleados;
    }
    
    private void CargarListaEmpledos(String pvc_ValorFiltrado) {
        //Variables
        //Se declara una varible tipo tabla por defecto.
        DefaultTableModel Modelo;

        //Se sobre escribe el metodo que deja editar la tabla.
        Modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        ClsLogicaEmpleado vlo_LogicaEmpleado = new ClsLogicaEmpleado();
        ResultSet vlo_RS;
        Object[] fila = new Object[5];

        //Inicio
        //A la tabla se le envia el nuevo modelo con el metodo sobre escrito.
        tbl_Empleados.setModel(Modelo);

        //Se agregan las columnas a la tabla.
        Modelo.addColumn("ID");
        Modelo.addColumn("Cédula");
        Modelo.addColumn("Nombre");
        Modelo.addColumn("Teléfono");
        Modelo.addColumn("Correo");
        
        try {
            //Se invoca el metodo que retorna la lista de empelados.
            vlo_RS = vlo_LogicaEmpleado.ListaEmpleados(pvc_ValorFiltrado);

            //Se recorre la lista de empleados.
            while (vlo_RS.next()) {
                //Se asigna a cada colimna de la fila el valor correspondiente del el result set.
                for (int i = 0; i < 5; i++) {
                    fila[i] = vlo_RS.getObject(i + 1);
                }
                //Se agrega la fila a la tabla.
                Modelo.addRow(fila);
                tbl_Empleados.getColumnModel().getColumn(0).setMaxWidth(0);
                //tbl_Empleados.getColumnModel().getColumn(0).setPreferredWidth(0);
                tbl_Empleados.getColumnModel().getColumn(0).setMinWidth(0);
                //tbl_Empleados.getColumnModel().getColumn(0).setResizable(false);
                //tbl_Empleados.doLayout();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la lista de empleados. (" + e.getMessage() + ").");
        }
    }
    
    private ClsEmpleados ObtenerEmpleado(int pvn_idEmpleado) throws Exception {
        //Variables
        ClsEmpleados vlo_Empleado = new ClsEmpleados();
        ClsLogicaEmpleado vlo_LogicaEmpleado = new ClsLogicaEmpleado();

        //Inicio
        try {
            vlo_Empleado = vlo_LogicaEmpleado.ObtenerEmpleado(pvn_idEmpleado);
        } catch (Exception e) {
            throw e;
        }
        return vlo_Empleado;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        txt_idCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtPrimerApellido = new javax.swing.JTextField();
        txtSegundoApellidio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtNumeroCuenta = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cldFecha = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Empleados = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setTitle("Empleados");

        jPanel1.setBackground(javax.swing.UIManager.getDefaults().getColor("info"));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Información Personal"));

        jLabel1.setText("Cédula");

        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        txt_idCliente.setText("jTextField1");

        jLabel2.setText("Nombre");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtPrimerApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrimerApellidoKeyTyped(evt);
            }
        });

        txtSegundoApellidio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSegundoApellidioKeyTyped(evt);
            }
        });

        jLabel3.setText("Primer Apellido");

        jLabel4.setText("Segundo Apellido");

        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });

        jLabel5.setText("Correo");

        jLabel6.setText("Teléfono");

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        txtNumeroCuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroCuentaKeyTyped(evt);
            }
        });

        jLabel7.setText("Número de cuenta");

        jLabel8.setText("Fecha de ingreso:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jButton2.setText("Eliminar");

        btnLimpiar.setText("Limpiar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6)
                            .addComponent(txtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(txtTelefono))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrimerApellido)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(0, 104, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtSegundoApellidio, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtCorreo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNumeroCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(cldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrimerApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSegundoApellidio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNumeroCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(jButton2)
                    .addComponent(btnLimpiar))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista empleados"));

        tbl_Empleados.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_Empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_EmpleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Empleados);

        jLabel9.setText("Buscar:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSalir.setText("Salir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        if (txtCedula.getText().length() > 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //Variables
        ClsLogicaEmpleado vlo_LogicaEmpledo = new ClsLogicaEmpleado();
        String vlc_Mensaje = "";

        //inicio
        //Se verifica que los cuadros de texto no se encuentren vacios.
        if (txtCedula.getText().equals("") || txtCorreo.getText().equals("") || txtNombre.getText().equals("") || txtNumeroCuenta.getText().equals("") || txtPrimerApellido.getText().equals("") || txtSegundoApellidio.getText().equals("") || txtTelefono.getText().equals("") || cldFecha.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Error algún valor requerido no esta seleccionado.");
        } else {
            try {
                vlc_Mensaje = vlo_LogicaEmpledo.GuardarEmpleado(LeerDatos());
                JOptionPane.showMessageDialog(this, vlc_Mensaje);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error. " + e.getMessage());
            }
            Limpiar();
            CargarListaEmpledos("");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        if (txtNombre.getText().length() > 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtPrimerApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrimerApellidoKeyTyped
        if (txtPrimerApellido.getText().length() > 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrimerApellidoKeyTyped

    private void txtSegundoApellidioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegundoApellidioKeyTyped
        if (txtSegundoApellidio.getText().length() > 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSegundoApellidioKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        if (txtTelefono.getText().length() > 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
        if (txtCorreo.getText().length() > 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCorreoKeyTyped

    private void txtNumeroCuentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroCuentaKeyTyped
        if (txtNumeroCuenta.getText().length() > 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNumeroCuentaKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        CargarListaEmpledos(txtBuscar.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tbl_EmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_EmpleadosMouseClicked
        //Variables
        ClsEmpleados vlo_Empleado;

        //Inicio
        if (evt.getClickCount() == 2) {
            try {
                vlo_Empleado = new ClsEmpleados();
                vlo_Empleado = ObtenerEmpleado(Integer.parseInt(tbl_Empleados.getValueAt(tbl_Empleados.getSelectedRow(), 0).toString()));
                txtCedula.setText(vlo_Empleado.getVgc_cedula());
                txtCorreo.setText(vlo_Empleado.getVgc_correo());
                txtNombre.setText(vlo_Empleado.getVgc_nombre());
                txtNumeroCuenta.setText(vlo_Empleado.getVgc_numeroCuenta());
                txtPrimerApellido.setText(vlo_Empleado.getVgc_primerApellido());
                txtSegundoApellidio.setText(vlo_Empleado.getVgc_segundoApellido());
                txtTelefono.setText(vlo_Empleado.getVgc_telefono());
                txt_idCliente.setText(Integer.toString(vlo_Empleado.getVgn_idEmpleado()));
                cldFecha.setDateFormatString(vlo_Empleado.getVgf_fechaContratacion().toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar al empleado. (" + e.getMessage() + ")");
            }
        }
    }//GEN-LAST:event_tbl_EmpleadosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private com.toedter.calendar.JDateChooser cldFecha;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbl_Empleados;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumeroCuenta;
    private javax.swing.JTextField txtPrimerApellido;
    private javax.swing.JTextField txtSegundoApellidio;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txt_idCliente;
    // End of variables declaration//GEN-END:variables
}
