
package bookList;

import book.Book;
import book.BookDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLbookListController implements Initializable {

    ObservableList<Book> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Book> booksTable;
    @FXML
    private TableColumn<Book, String> bookTitle;
    @FXML
    private TableColumn<Book, Integer> bookId;
    @FXML
    private TableColumn<Book, String> bookAuthor;
    @FXML
    private TableColumn<Book, Double> bookPrice;
    @FXML
    private TableColumn<Book, String> bookCode;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BookDAO b = new BookDAO();
        b.getBooks(list); 
        initCol();
        booksTable.setItems(list);
    } 
    
    private void initCol(){
        bookId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        bookTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        bookPrice.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
        bookCode.setCellValueFactory(new PropertyValueFactory<Book, String>("codebare"));        
    }

    


}
