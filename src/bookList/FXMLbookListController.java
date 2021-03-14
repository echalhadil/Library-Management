/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookList;

import book.Book;
import book.BookDAO;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author hp
 */
public class FXMLbookListController implements Initializable {

    ObservableList<Book> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Book> booksTable;
    @FXML
    private TableColumn<Book, String> bookTitle;
    @FXML
    private TableColumn<Book, String> bookId;
    @FXML
    private TableColumn<Book, String> bookAuthor;
    @FXML
    private TableColumn<Book, Double> bookPrice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
             
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "");
            String SELECT_ALL_BOOKS = "SELECT * FROM books";
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                String title = rs.getString("title");
                String id = rs.getString("id");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                

                Book b = new Book();
                b.setTitle(title);
                b.setId(id);
                b.setAuthor(author);
                b.setPrice(price);
                list.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLbookListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initCol();
        booksTable.setItems(list);
    } 
    
    private void initCol(){
        bookTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        bookId.setCellValueFactory(new PropertyValueFactory<Book, String>("id"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        bookPrice.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
            
    }

    


}
