/*
Este proyecto fue desarrollado por Adalberto Naranjo,
cualquier copia, distribucion, reproduccion total o parcial 
esta prohibida sin consentimiento del autor.
Según el artículo 6 de la Ley 23 de 1982 (Ley de Derechos de Autor),
en el artículo 7 de la norma andina sobre derechos de autor, la
Decisión 351 de 1993 y Copyright Act (1968) USA.
 */
package problema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Adalberto Naranjo
 */
    public class JavaConnectDB{

        /**
         *
         * @return
         */
        public static  Connection ConnecrDB(){
        
            try{
            
                Class.forName("oracle.jdbc.OracleDriver");
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "admin","123");
                return con;
            }
            catch(ClassNotFoundException | SQLException e  ){
                JOptionPane.showMessageDialog(null, e);
            
            }
            return null;
        }
    }