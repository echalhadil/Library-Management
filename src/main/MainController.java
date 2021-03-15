/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void openAddBook(ActionEvent event) throws IOException {    
       loadWindow("/librarymanagement/addbook/FXMLDocument.fxml","add Book"); 
    }
    
    
    void loadWindow(String location,String title) throws IOException{
        
        Parent root =new FXMLLoader(getClass().getResource(location)).load();

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    private void openAllBooks(ActionEvent event) throws IOException {
        loadWindow("/bookList/FXMLbookList.fxml","all Books");
    }
}
