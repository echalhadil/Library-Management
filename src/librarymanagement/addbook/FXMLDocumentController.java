/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagement.addbook;

import EditMember.EditMemberController;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author hp
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private TextField codebare;
    @FXML
    private TextField price;
    @FXML
    private Label titleError;
    @FXML
    private Label idError;
    @FXML
    private Label authorError;
    @FXML
    private Label priceError;
    
    
        @FXML
    private Label message;
    @FXML
    private AnchorPane addbookPane;
    
    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
  
    }
    
    @FXML
    private void saveBook(ActionEvent event) throws ClassNotFoundException  {
            
        if(this.title.getText().trim().isEmpty())
            this.titleError.setText("name is required");
        else
             this.titleError.setText("");
        
        if(this.codebare.getText().trim().isEmpty())
            this.idError.setText("id is required");
        else     
            this.idError.setText("");
        
        if(this.author.getText().trim().isEmpty())
            this.authorError.setText("author is required");
        else
            this.authorError.setText("");
        
        if(this.price.getText().trim().isEmpty())
            this.priceError.setText("price is required");
        else
            this.priceError.setText("");
        
        if(!this.title.getText().trim().isEmpty() && !this.codebare.getText().trim().isEmpty() && !this.author.getText().trim().isEmpty() && !this.price.getText().trim().isEmpty())
        {   
             this.titleError.setText("");
             this.idError.setText("");
             this.authorError.setText("");
             this.priceError.setText("");
             
             Book book = new Book();
             book.setTitle( this.title.getText() );
             book.setCodebare(this.codebare.getText());
             book.setAuthor(this.author.getText());
             book.setPrice(Double.parseDouble(this.price.getText()));
             BookDAO b = new BookDAO();
             b.insertBook(book);
                   
             this.message.setText("saved !");
             
             this.title.setText("");
             this.codebare.setText("");
             this.author.setText("");
             this.price.setText("");
             
             book.affiche();
        }
    }
    

    
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
          
          Stage stage = (Stage) addbookPane.getScene().getWindow();
        stage.close();

            
        }

        
    }
    
    
    public void initializeData(Book book){
        this.title.setText(book.getTitle());
        this.codebare.setText(book.getCodebare());
        this.author.setText(book.getAuthor());
        this.price.setText( Double.toString(book.getPrice()));
             
    }
    
}
