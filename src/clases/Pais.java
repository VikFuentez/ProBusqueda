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
public class Pais extends Conexion{
    
    public ResultSet comboPais(JComboBox paises, int continente){
        paises.removeAllItems();
        paises.addItem("ND");
        String sql="";
        if(continente==0)
        {
            return null;
        }
        if(continente==1)
        {
            sql="SELECT * FROM paises WHERE id_pais between 1 and 35";
        }
        else if(continente==2)
        {
            sql="SELECT * FROM paises WHERE id_pais between 36 and 63";
        }
        else if(continente==3)
        {
            sql="SELECT * FROM paises WHERE id_pais between 64 and 110";
        }
        else if(continente==4)
        {
            sql="SELECT * FROM paises WHERE id_pais between 111 and 176";
        }
        else if(continente==5)
        {
            sql="SELECT * FROM paises WHERE id_pais between 177 and 202";
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
    
    public ResultSet obtenerID(Object pais){
        String sql="SELECT * FROM paises WHERE nombre_pais = '"+pais+"'";
        try {
            ResultSet rs = this.consultar(sql);
            return rs;
        } catch (Exception ex) {
            Logger.getLogger(Pais.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
