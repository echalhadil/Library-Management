/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MainController implements Initializable {

    @FXML
    private Label date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
 
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {        
        LocalTime currentTime = LocalTime.now();
        this.date.setText(dateFormat.format(date)+" "+currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
    }),
         new KeyFrame(Duration.seconds(1))
    );
    clock.setCycleCount(Animation.INDEFINITE);
    clock.play();

        
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

    @FXML
    private void findBook(ActionEvent event) {
        
        try { 
            loadWindow("/FindBook/FXMLFindBook.fxml","find Book");
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
