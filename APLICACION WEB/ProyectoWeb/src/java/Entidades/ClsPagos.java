/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author tomas
 */
public class ClsPagos extends ClsDesglose{
    
    //Metodos
    public ClsPagos(int vgn_id, int vgn_idDetallePLanilla, String vgc_Concepto, double vgn_Porcentaje, double vgn_Monto) {
        super(vgn_id, vgn_idDetallePLanilla, vgc_Concepto, vgn_Porcentaje, vgn_Monto);
    }

    public ClsPagos() {
        super();
    }
    
}
