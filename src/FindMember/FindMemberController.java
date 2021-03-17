/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FindMember;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import member.Member;
import member.MemberDAO;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class FindMemberController implements Initializable {

    @FXML
    private TextField givenID;
    @FXML
    private Label message;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void findMember(ActionEvent event) throws IOException, ClassNotFoundException {
        if(!givenID.getText().trim().isEmpty())
        {             
            MemberDAO m = new MemberDAO();
            int id = Integer.parseInt(givenID.getText());
            Member member = m.getMember(id);
            this.message.setText("No Results.");        
            if(!Objects.isNull(member)){
                loadWindow("/EditMember/EditMember.fxml","edit",member);
                this.message.setText("");
            }
        }
        else 
            
            this.message.setText("this field is required.");   
        
    }
    
    
    void loadWindow(String location,String tit,Member member) throws IOException{
        Parent root =new FXMLLoader(getClass().getResource(location)).load();
        initializeData(member,root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle(tit);
        stage.setScene(new Scene(root));
        stage.show();

    }
    
    void initializeData(Member member,Parent root){
        TextField id = (TextField) root.lookup("#id");
        TextField fname = (TextField) root.lookup("#fname");
        TextField lname = (TextField) root.lookup("#lname");                    
        TextField cin = (TextField) root.lookup("#cin");
        
        
        id.setText( Integer.toString(member.getId())); 
        fname.setText(member.getFname());
        lname.setText(member.getLname());
        cin.setText(member.getCin());
        
    }
    
}
