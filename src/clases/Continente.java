package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author manu_
 */
public class Continente extends Conexion{
    
    public ResultSet comboContinente(JComboBox continentes){
        String sql="SELECT * FROM continente";
        ResultSet rs = this.consultar(sql);
        
        try {
            while(rs.next())
            {
                continentes.addItem(rs.getString("nom_conti"));
            }
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Continente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
