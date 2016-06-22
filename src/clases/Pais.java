package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author manu_
 */
public class Pais extends Conexion{
    
    public ResultSet comboPais(JComboBox paises, int continente){
        paises.removeAllItems();
        paises.addItem("ND");
        String sql;
        if(continente==0)
        {
            return null;
        }
        else
        {
            sql="SELECT * FROM paises WHERE id_conti="+continente;
        }
        try {
            ResultSet rs = this.consultar(sql);
            while(rs.next())
            {
                paises.addItem(rs.getString("nombre_pais"));
            }
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ResultSet obtenerID(Object pais, JTextField caja){
        String sql="SELECT * FROM paises WHERE nombre_pais = '"+pais+"'";
        try {
            ResultSet rs = this.consultar(sql);
            while(rs.next())
            {
                caja.setText(rs.getString("id_pais"));
            }
        } catch (Exception ex) {
            Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
