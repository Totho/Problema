/*
Este proyecto fue desarrollado por Adalberto Naranjo,
cualquier copia, distribucion, reproduccion total o parcial 
esta prohibida sin consentimiento del autor.
Según el artículo 6 de la Ley 23 de 1982 (Ley de Derechos de Autor),
en el artículo 7 de la norma andina sobre derechos de autor, la
Decisión 351 de 1993 y Copyright Act (1968) USA.
 */
package problema;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OraclePreparedStatement;
/**
 *
 * @author AdalbertoNaranjo
 */
public class FXMLDocumentController implements Initializable {
    @FXML    private TextField tfCod;
    @FXML    private TextField tfNom;
    @FXML    private ChoiceBox tipoID;
    @FXML    private TextField numID;
    @FXML    private ChoiceBox natu;
    @FXML    private CheckBox mulARP;
    @FXML    private CheckBox fsp  ;
    @FXML    private CheckBox fusion;
    @FXML    private DatePicker fecha;
    @FXML    ObservableList<String> tipoI;
    @FXML    ObservableList<String> natuList;
    Connection conn=null;
    OraclePreparedStatement pst = null;
    OracleResultSet rs = null;
    
    public FXMLDocumentController() {
        this.tipoI = FXCollections.observableArrayList("NI","CC","PA","RC");
        this.natuList = FXCollections.observableArrayList("PR","PU","MI");
    
    }
            
    @FXML  private void accion(ActionEvent event) {
        if(fusion.isSelected()){
            fecha.setDisable(false);
            }
        else{
        fecha.setDisable(true);
        }
    }    
    @FXML  private void handleButtonAction(ActionEvent evento) throws SQLException {
        
            String Cod =tfCod.getText();
            String Nom =tfNom.getText();
            String Tid =tipoID.getValue().toString();
            String Nid =numID.getText();
            String Natur=natu.getValue().toString();
            int fsp1=0;
            if(fsp.isSelected()){
                fsp1+=1;
            }
            int marp=0;
            if(mulARP.isSelected()){
                marp=1;
            }

            int fus=0;
            if(fusion.isSelected()){
                fus=1;
            } 
            LocalDate dia = fecha.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
            String text = dia.format(formatter);

            if(!(tfNom.getText().trim().isEmpty())){
                if(!(tfCod.getText().trim().isEmpty())){
                    if(!(numID.getText().trim().isEmpty())){

                        System.out.println("codigo  "+Cod+"\nNombre  "+Nom+"\nTipoID  "+Tid+"\nNumero ID  "+Nid+"\nNaturaleza  "+Natur+"\nFSP  "+fsp1+"\nmultiple ARP  "+marp+"\nFusion  "+fus+"\nFecha "+text);            
                        }
                    }    
                }   
            conn=JavaConnectDB.ConnecrDB();
            try{
                pst = (OraclePreparedStatement) conn.prepareStatement("insert into ADMINISTRADORAS  values (id_sequence.nextval,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, Cod);
                pst.setString(2,Nom);
                pst.setString(3,Tid);
                pst.setString(4,Nid);
                pst.setString(5,Natur);
                pst.setInt(6,marp);
                pst.setInt(7,fsp1);
                pst.setInt(8,fus);
                pst.setString(9,text);
            
                int colunas= pst.executeUpdate();
                if(colunas > 0) {
                    JOptionPane.showMessageDialog(null, "DATOS INGRESADOS EXITOSAMENTE");
                }
                
                else{
                    JOptionPane.showMessageDialog(null, "ALGO ANDA MAL EN EL REGISTRO DE LOS DATOS REINICIE LA APP", "notupdated", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            conn.close();
        }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        natu.setItems(natuList);
        tipoID.setItems(tipoI);
        
        UnaryOperator<TextFormatter.Change> filter;
        filter = new UnaryOperator<TextFormatter.Change>()
               {
                  @Override
                  public TextFormatter.Change apply(TextFormatter.Change change)
                  {
                    String text = change.getText();
                    for (int i = 0; i < text.length(); i++)
                        if (!Character.isAlphabetic(text.charAt(i))) {
                            if (!Character.isDigit(text.charAt(i))) {
                                return null;
                            }
                        }
                    return change;
                    }
                };
    
        UnaryOperator<TextFormatter.Change> filter1;
        filter1 = new UnaryOperator<TextFormatter.Change>()
               {
                  @Override
                  public TextFormatter.Change apply(TextFormatter.Change change)
                  {
                    String text = change.getText();
                    for (int i = 0; i < text.length(); i++)
                        if (!Character.isAlphabetic(text.charAt(i))) {
                                return null;
                            }

                    return change;
                    }
                };    
        UnaryOperator<TextFormatter.Change> filter2;
        filter2 = new UnaryOperator<TextFormatter.Change>()
               {
                  @Override
                  public TextFormatter.Change apply(TextFormatter.Change change)
                  {
                    String text = change.getText();
                    for (int i = 0; i < text.length(); i++)
                            if (!Character.isDigit(text.charAt(i))) {
                                return null;
                            }
                    return change;
                    }
                };
    tfCod.setTextFormatter(new TextFormatter<>(filter));
    tfNom.setTextFormatter(new TextFormatter<>(filter1));
    numID.setTextFormatter(new TextFormatter<>(filter2));
    fecha.setDisable(true);
    }
}
