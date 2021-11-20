import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author cesar
 */
public class BaseDatos {
    Connection conexion;
    Statement transaccion;
    ResultSet cursor;
    
    public BaseDatos() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tap_u4_practica1?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
            transaccion = conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean insertar(Productos p) {
        String insertar = "INSERT INTO PRODUCTOS VALUES (NULL,'" + p.autoNum + "','" + p.descripcion + "','" + p.precio + "'," + p.existencia + ")";

        try {
            transaccion.execute(insertar);
        } catch (SQLException ex) {
            return false;
        }

        return true;
    }
    
    public ArrayList<String[]> consultarTodos() {
        ArrayList<String[]> resultado = new ArrayList<String[]>();

        try {
            cursor = transaccion.executeQuery("SELECT * FROM PRODUCTOS");
            if (cursor.next()) {
                do {
                    String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)};
                    resultado.add(renglon);
                } while (cursor.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }
    
    public Productos consultarID(int id){
        Productos producto = new Productos();
        
        try {
            cursor = transaccion.executeQuery("SELECT * FROM PRODUCTOS WHERE IDPRODUCTOS ="+id);
            if (cursor.next()) {
                producto.autoNum = Integer.parseInt(cursor.getString(2));
                producto.descripcion = cursor.getString(3);
                producto.precio = Float.parseFloat(cursor.getString(4));
                producto.existencia = Integer.parseInt(cursor.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return producto;
    }
    
    public boolean eliminar(int id){
        
        try {
            transaccion.executeQuery("DELETE FROM PRODUCTOS WHERE IDPRODUCTOS = "+id);
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
        
    }
    
    public boolean actualizar(Productos p){
        
        String actualizar = "UPDATE PRODUCTOS SET AUTONUM = " + p.autoNum + ", DESCRIPCION = '" + p.descripcion + "', PRECIO = " + p.precio + ", EXISTENCIA = " + p.existencia + " WHERE IDPRODUCTOS = " + p.idProductos;
        
        try {
            transaccion.executeQuery(actualizar);
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
    }
    
}
