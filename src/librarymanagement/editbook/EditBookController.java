/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagement.editbook;

import book.Book;
import book.BookDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 *
 * @author hp
 */
public class EditBookController implements Initializable {
    
    @FXML
    private TextField title;
    @FXML
    private TextField author;
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
    
    public Book oldBook  = new Book();
    
        @FXML
    private Label message;
    @FXML
    private AnchorPane editbookPane;
    @FXML
    private TextField codebare;
    @FXML
    private TextField id;
    
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

          //   this.oldBook.setTitle(this.title.getText());             
        //    this.oldBook.setId(this.id.getText());
            // this.oldBook.setAuthor(this.author.getText());
            // this.oldBook.setPrice(0.0);
    
    
    
    }
    
    
    
    
    
    @FXML
    private void editBook(ActionEvent event) throws ClassNotFoundException  {
            
        if(this.title.getText().trim().isEmpty())
            this.titleError.setText("name is required");
        else
             this.titleError.setText("");
        
        if(this.codebare.getText().trim().isEmpty())
            this.idError.setText("code bare is required");
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

            Book newBook = new Book();
            newBook.setTitle( this.title.getText() );
            newBook.setCodebare(this.codebare.getText());
            newBook.setAuthor(this.author.getText());
            newBook.setPrice(Double.parseDouble(this.price.getText()));

            
                BookDAO b = new BookDAO();
                try {
                    b.updateBook(this.id.getText(), newBook);
                  
                } catch (SQLException ex) {
                    Logger.getLogger(EditBookController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                  this.oldBook.affiche();
                
                
                
                this.message.setText("edited !");
                newBook.affiche();
             
            
             
            
        
             
        }
    }
    

    
 

    @FXML
    private void cancel(ActionEvent event) {
        
        Stage stage = (Stage) editbookPane.getScene().getWindow();
        stage.close();
        
    }
    
    
    public void initializeData(Book book){
          
             book.setTitle(this.title.getText());             
             book.setCodebare(this.codebare.getText());
             book.setAuthor(this.author.getText());
             book.setPrice(0.0);

             
    }

    @FXML
    private void deletebook(ActionEvent event) {
        String givenId = this.id.getText();
        BookDAO b = new BookDAO();
        try {
            
            Alert alertConfirmation = new Alert(AlertType.CONFIRMATION);
            alertConfirmation.setTitle("Delete Confirmation");
            alertConfirmation.setHeaderText(" Confirm !");
            alertConfirmation.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alertConfirmation.showAndWait();
            if (result.get() == ButtonType.OK){
                if(b.deleteBook(givenId)!=0){
                    Alert alertSuccess = new Alert(AlertType.INFORMATION);
                    alertSuccess.setTitle("Information Dialog");
                    alertSuccess.setHeaderText(null);
                    alertSuccess.setContentText("your book deleted Successfuly!");
                    alertSuccess.showAndWait();
                    Stage stage = (Stage) editbookPane.getScene().getWindow();
                    stage.close();
                }
                

            }
            
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EditBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    

  
    
}
