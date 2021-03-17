/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addMember;

import EditMember.EditMemberController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class AddMemberController implements Initializable {

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
    private AnchorPane addMemberPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void cancel(ActionEvent event) {
       
        Parent root = null;
        try {
            root = new FXMLLoader(getClass().getResource("/main/Main.fxml")).load();
            Stage main = new Stage(StageStyle.DECORATED);
            main.setTitle("HOME");
            main.setScene(new Scene(root));
            main.show();
        } catch (IOException ex) {
            Logger.getLogger(EditMemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
          
            Stage stage = (Stage) addMemberPane.getScene().getWindow();
            stage.close();

            
        }
    }

    @FXML
    private void saveMember(ActionEvent event) throws ClassNotFoundException {
            
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
             m.insertMember(member);
                   
             this.message.setText("saved !");
             
             this.fname.setText("");
             this.lname.setText("");
             this.cin.setText("");
             
        }
    }
    
}
