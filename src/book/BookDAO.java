package book;


import bookList.FXMLbookListController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

public class BookDAO {
	
	public int insertBook(Book book) throws ClassNotFoundException {
		
		String INSERT_STUDENTS_SQL = " INSERT INTO books VALUES (?,?,?,?);";
		int result = 0;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try( 
				Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "");
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL)
			){
                               
				preparedStatement.setString(1,book.getTitle());
				preparedStatement.setString(2,book.getId());
				preparedStatement.setString(3,book.getAuthor());
				preparedStatement.setDouble(4,book.getPrice());
				
				System.out.println(preparedStatement);
				
				result = preparedStatement.executeUpdate();
					
				}
		catch(SQLException e ) {
			e.printStackTrace();
			
		}
			
		
		
		return result;
		
	}

        
        public void getBooks( ObservableList<Book> list) {
       
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

		
	}
         
       
        public Book getBook(String givenID ) throws ClassNotFoundException{
            Book b = new Book();
            String SELECT_STUDENT_SQL = " SELECT * FROM books WHERE id = '"+givenID+"'";
             
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try( 
                    Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "");
                    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_SQL);
		)
                {
                    ResultSet rs = preparedStatement.executeQuery(SELECT_STUDENT_SQL);
                    
                    while(rs.next()){
                        String title = rs.getString("title");
                        String id = rs.getString("id");
                        String author = rs.getString("author");
                        double price = rs.getDouble("price");


                        b.setTitle(title);
                        b.setId(id);
                        b.setAuthor(author);
                        b.setPrice(price);

                    }
                    return b;

                }
		catch(SQLException e ) {
			e.printStackTrace();
			
		}
        return b;
		
        } 
            
          
        
        
        public void updateBook(Book book,Book newbook){
            
        }
        
        public void deleteBook(){
            
        }
   
}
