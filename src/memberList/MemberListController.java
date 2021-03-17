/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memberList;

import book.Book;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import member.Member;
import member.MemberDAO;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MemberListController implements Initializable {

    ObservableList<Member> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Member, Integer> memberid;
    @FXML
    private TableColumn<Member, String> momberFname;
    @FXML
    private TableColumn<Member, String> momberLname;
    @FXML
    private TableColumn<Member, String> momberCIN;
    @FXML
    private TableView<Member> membersTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MemberDAO m = new MemberDAO();
        m.getMembers(list); 
        initCol();
        membersTable.setItems(list);
    }    
    
      private void initCol(){
        memberid.setCellValueFactory(new PropertyValueFactory<Member, Integer>("id"));
        momberFname.setCellValueFactory(new PropertyValueFactory<Member, String>("fname"));
        momberLname.setCellValueFactory(new PropertyValueFactory<Member, String>("lname"));
        momberCIN.setCellValueFactory(new PropertyValueFactory<Member, String>("cin"));
        
    }

    
}
