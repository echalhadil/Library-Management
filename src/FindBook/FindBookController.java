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
import java.util.Objects;
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
    private void findBook(ActionEvent event) throws ClassNotFoundException, IOException {
        if(!givenID.getText().trim().isEmpty())
        {             
            BookDAO b = new BookDAO();
            int id = Integer.parseInt(givenID.getText());
            Book book = b.getBook(id);
            this.message.setText("No Results.");        
            if(!Objects.isNull(book)){
                loadWindow("/librarymanagement/editbook/EditBook.fxml","edit",book);
                this.message.setText("");
            }
        }
        else 
            
            this.message.setText("this field is required.");   
        
    }
    
    
    void loadWindow(String location,String tit,Book book) throws IOException{
        Parent root =new FXMLLoader(getClass().getResource(location)).load();
        initializeData(book,root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle(tit);
        stage.setScene(new Scene(root));
        stage.show();

    }
    
    void initializeData(Book book,Parent root){
        TextField id = (TextField) root.lookup("#id");
        TextField title = (TextField) root.lookup("#title");
        TextField codebare = (TextField) root.lookup("#codebare");                    
        TextField author = (TextField) root.lookup("#author");
        TextField price = (TextField) root.lookup("#price");

        
        id.setText( Integer.toString(book.getId())); 
        title.setText(book.getTitle());
        codebare.setText(book.getCodebare());
        author.setText(book.getAuthor());
        price.setText( Double.toString(book.getPrice()));         
        
    }

  
    
}
