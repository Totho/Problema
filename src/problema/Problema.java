/*
Este proyecto fue desarrollado por Adalberto Naranjo,
cualquier copia, distribucion, reproduccion total o parcial 
esta prohibida sin consentimiento del autor.
Según el artículo 6 de la Ley 23 de 1982 (Ley de Derechos de Autor),
en el artículo 7 de la norma andina sobre derechos de autor, la
Decisión 351 de 1993 y Copyright Act (1968) USA.
 */
package problema;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Adalberto Naranjo
 */
public class Problema extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
