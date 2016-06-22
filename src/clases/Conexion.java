package clases;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author manu_
 */
public class Conexion 
{
    private static Connection cone;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost:3306/probusqueda";

    public Connection conectar() 
    {
        cone = null; 
        try
        {
            Class.forName(driver);
            cone = DriverManager.getConnection(url, user, password);
            if (cone!=null) 
            {
                System.out.println("Conexion establecida");
            }
        } 
        catch(ClassNotFoundException | SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Conexion inestable, verifique el servidor, pueda que este apagado");
            System.exit(0);
        }
        return cone;
    }
    
    public void desconectar()
    {
        try 
        {
            cone.close();
            System.out.println("Conexion terminada");
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "No se pudo desconectar");
        }
        
    }
    
    public boolean ejecutar(String sql, String mensaje){
        this.conectar();
        try 
        {
            PreparedStatement res = cone.prepareStatement(sql);
            res.executeUpdate();
            JOptionPane.showMessageDialog(null, mensaje);
        } 
        catch (SQLException ex)
        {
            return false;
        }
        this.desconectar();
        return true;
    }  
    
    public ResultSet consultar(String sql)
    {
        this.conectar();
        try 
        {
            Statement st = cone.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "No se pudo ejecutar la consulta");
        }
        this.desconectar();
        return null;
    }  
}
