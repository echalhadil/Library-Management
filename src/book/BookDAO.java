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
		
		String INSERT_STUDENTS_SQL = " INSERT INTO books(title, codebare, author, price) VALUES (?,?,?,?);";
		int result = 0;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try( 
				Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "");
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL)
			){
                               
				preparedStatement.setString(1,book.getTitle());
				preparedStatement.setString(2,book.getCodebare());
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
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String codebare = rs.getString("codebare");
                    String author = rs.getString("author");
                    double price = rs.getDouble("price");


                    Book b = new Book();
                    b.setTitle(title);
                    b.setId(id);
                    b.setCodebare(codebare);
                    b.setAuthor(author);
                    b.setPrice(price);
                    list.add(b);
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(FXMLbookListController.class.getName()).log(Level.SEVERE, null, ex);
            }

		
	}
         
       
        public Book getBook(int givenID ) throws ClassNotFoundException{
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
                        int id = rs.getInt("id");
                        String title = rs.getString("title");
                        String codebare = rs.getString("codebare");
                        String author = rs.getString("author");
                        double price = rs.getDouble("price");

                        b.setId(id);
                        b.setTitle(title);
                        b.setCodebare(codebare);
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
            
          
        
        
        public void updateBook(String oldBookId,Book newBook) throws SQLException{
            
            int id = Integer.parseInt(oldBookId);
            System.out.print("this is the id :: "+ id);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "");
            String UPDATE_SELECTED_BOOK = "UPDATE books SET title=?,codebare=?,author=?,price=? WHERE id=? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SELECTED_BOOK);
            preparedStatement.setString(1, newBook.getTitle());
            preparedStatement.setString(2, newBook.getCodebare());
            preparedStatement.setString(3, newBook.getAuthor());
            preparedStatement.setDouble(4, newBook.getPrice());
            preparedStatement.setInt(5, id);
            
            System.out.println(preparedStatement);
            int rs = preparedStatement.executeUpdate();
            
            
            
        }
        
        public int deleteBook(String givenId) throws SQLException{
            
            int id = Integer.parseInt(givenId);            
            System.out.print("this is the id :: "+ id);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "");
            String UPDATE_SELECTED_BOOK = "DELETE FROM books WHERE id=? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SELECTED_BOOK);
            preparedStatement.setInt(1, id);
            
            System.out.println(preparedStatement);
            return preparedStatement.executeUpdate();
        }
   
}
