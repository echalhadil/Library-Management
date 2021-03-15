/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FindBook;

import book.Book;
import book.BookDAO;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import librarymanagement.addbook.FXMLDocumentController;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class FindBookController implements Initializable {

    @FXML
    private TextField givenID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void findBook(ActionEvent event) throws ClassNotFoundException, IOException {
        if(!givenID.getText().trim().isEmpty())
        { 
            BookDAO b = new BookDAO();
            String id = givenID.getText();
           
            Book book;
            
                book = b.getBook(id);
                book.affiche();
                if(book.getAuthor() != null){
                    FXMLDocumentController c =  new FXMLDocumentController();
                    loadWindow("/librarymanagement/addbook/FXMLDocument.fxml","edit");
                   
                    Parent root =new FXMLLoader(getClass().getResource("/librarymanagement/addbook/FXMLDocument.fxml")).load();
 
                    TextField title = (TextField) root.lookup("#title");
                    TextField bid = (TextField) root.lookup("#id");

                    title.setText(book.getTitle());
                   bid.setText(book.getId());
             /*this.author.setText(book.getAuthor());
             this.price.setText( Double.toString(book.getPrice()));
              */           
                        Stage stage = new Stage(StageStyle.DECORATED);
                        stage.setTitle("edit");
                        stage.setScene(new Scene(root));
                        stage.show();
                    
                }
               
            
        }
        
    }
    
    
    void loadWindow(String location,String title) throws IOException{
        
        Parent root =new FXMLLoader(getClass().getResource(location)).load();

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();

    }

  
    
}
