/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EditMember;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import member.Member;
import member.MemberDAO;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class EditMemberController implements Initializable {

   
    @FXML
    private TextField id;
    @FXML
    private TextField fname;
    @FXML
    private Label fnameError;
    @FXML
    private TextField lname;
    @FXML
    private Label lnameError;
    @FXML
    private TextField cin;
    @FXML
    private Label cinError;
    @FXML
    private Label message;
    @FXML
    private AnchorPane editmemberPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void editMember(ActionEvent event) throws ClassNotFoundException, SQLException {
        String id =this.id.getText(); 
        if(this.fname.getText().trim().isEmpty())
            this.fnameError.setText("first name is required");
        else
             this.fnameError.setText("");
        
        if(this.lname.getText().trim().isEmpty())
            this.lnameError.setText("last name is required");
        else
             this.lnameError.setText("");
        
        if(this.cin.getText().trim().isEmpty())
            this.cinError.setText("CIN is required");
        else     
            this.cinError.setText("");
        
        
        
        if(!this.fname.getText().trim().isEmpty() && !this.lname.getText().trim().isEmpty() && !this.cin.getText().trim().isEmpty())
        {   
             this.fnameError.setText("");
             this.lnameError.setText("");
             this.cinError.setText("");
             
             Member member = new Member();
             member.setFname(this.fname.getText());
             member.setLname(this.lname.getText());
              member.setCin(this.cin.getText());
          
             MemberDAO m = new MemberDAO();
             m.updateMember(id, member);
                   
             this.message.setText("Edited !");
             

             
        }
    }
  
 

    @FXML
    private void deleteMember(ActionEvent event) throws SQLException {
        
        
         String givenId = this.id.getText();
        MemberDAO m = new MemberDAO();
       
            Alert alertConfirmation = new Alert(AlertType.CONFIRMATION);
            alertConfirmation.setTitle("Delete Confirmation");
            alertConfirmation.setHeaderText(" Confirm !");
            alertConfirmation.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alertConfirmation.showAndWait();
            if (result.get() == ButtonType.OK){
                if(m.deleteMember(givenId)!=0){
                    Alert alertSuccess = new Alert(AlertType.INFORMATION);
                    alertSuccess.setTitle("Information Dialog");
                    alertSuccess.setHeaderText(null);
                    alertSuccess.setContentText("your members info deleted Successfuly!");
                    alertSuccess.showAndWait();
                    Stage editstage = (Stage) editmemberPane.getScene().getWindow();
                    editstage.close();
                }
                
            }
             
       

        
        
        
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        
        
        Stage stage = (Stage) editmemberPane.getScene().getWindow();
        stage.close();
        
        
        

    }
    
   
    
}
