package book;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;



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

        
        public void getBooks( ) throws ClassNotFoundException  {
		
            String SELECT_ALL_BOOKS = " SELECT * FROM books";
            Class.forName("com.mysql.cj.jdbc.Driver");
            try(
                    Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "");
                    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS)
                )
            {

                                
                ResultSet rs = preparedStatement.executeQuery();
                
                System.out.print(rs.toString());
                System.out.println(preparedStatement);

                    while(rs.next()){
                       //Retrieve by column name
                        Book book  = new Book();
                        book.setTitle(rs.getString("title"));
                        book.setId(rs.getString("id"));
                        book.setAuthor(rs.getString("author"));
                        book.setPrice(rs.getDouble("price"));
                        
                        book.affiche();

                         
                     

                    }
                    rs.close();
            }

            catch(SQLException e ) {
                    e.printStackTrace();

            }


                
			
		
		
		
	}

   
}
