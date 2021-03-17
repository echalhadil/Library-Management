/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package member;

import bookList.FXMLbookListController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author hp
 */
public class MemberDAO {
    
    
    
    public int insertMember(Member member) throws ClassNotFoundException {
		
		String INSERT_MEMBER_SQL = " INSERT INTO members(fname, lname, cin) VALUES (?,?,?);";
		int result = 0;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try( 
				Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "");
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MEMBER_SQL)
			){
                               
				preparedStatement.setString(1,member.getFname());
				preparedStatement.setString(2,member.getLname());
				preparedStatement.setString(3,member.getCin());
				
				System.out.println(preparedStatement);
				
				result = preparedStatement.executeUpdate();
					
				}
		catch(SQLException e ) {
			e.printStackTrace();
			
		}

		return result;
		
	}

        
    
        public void getMembers( ObservableList<Member> list) {
       
              try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "");
                String SELECT_ALL_BOOKS = "SELECT * FROM members";
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS);
                ResultSet rs = preparedStatement.executeQuery();
                
                

                while(rs.next()){
                    int id = rs.getInt("id");
                    String fname = rs.getString("fname");
                    String lname = rs.getString("lname");
                    String cin = rs.getString("cin");
                   

                    Member m = new Member();
                    m.setId(id);
                    m.setFname(fname);
                    m.setLname(lname);
                    m.setCin(cin);
                            
                    list.add(m);
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(FXMLbookListController.class.getName()).log(Level.SEVERE, null, ex);
            }

		
	}
                      
        public Member getMember(int givenID ) throws ClassNotFoundException{
            String SELECT_STUDENT_SQL = " SELECT * FROM members WHERE id = '"+givenID+"'";
            Member m = new Member();
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try( 
                    Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "");
                    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_SQL);
		)
                {
                    ResultSet rs = preparedStatement.executeQuery(SELECT_STUDENT_SQL);
                    
                    while(rs.next()){   
                        int id = rs.getInt("id");
                        String fname = rs.getString("fname");
                        String lname = rs.getString("lname");
                        String cin = rs.getString("cin");


                        
                        m.setId(id);
                        m.setFname(fname);
                        m.setLname(lname);
                        m.setCin(cin);
                    }
                    return m;

                }
		catch(SQLException e ) {
			e.printStackTrace();
			
		}
        return m;
		
        } 
        
        public void updateMember(String oldMemberId,Member newMember) throws SQLException{
            
            int id = Integer.parseInt(oldMemberId);
            System.out.print("this is the id :: "+ id);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "");
            String UPDATE_SELECTED_BOOK = "UPDATE members SET fname=?,lname=?,cin=? WHERE id= ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SELECTED_BOOK);
            preparedStatement.setString(1, newMember.getFname());
            preparedStatement.setString(2, newMember.getLname());
            preparedStatement.setString(3, newMember.getCin());
            preparedStatement.setInt(4, id);
            
            System.out.println(preparedStatement);
            int rs = preparedStatement.executeUpdate();
            
            
            
        }
        
        public int deleteMember(String givenId) throws SQLException{
            
            int id = Integer.parseInt(givenId);            
            System.out.print("this is the id :: "+ id);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "");
            String UPDATE_SELECTED_BOOK = "DELETE FROM members WHERE id=? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SELECTED_BOOK);
            preparedStatement.setInt(1, id);
            
            System.out.println(preparedStatement);
            return preparedStatement.executeUpdate();
        }
        
        
         public int countMembers() throws SQLException{
            
           int i = 0;
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "");
            String UPDATE_SELECTED_BOOK = "SELECT * FROM members ;";
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SELECTED_BOOK);
            
            System.out.println(preparedStatement);
             ResultSet rs = preparedStatement.executeQuery();
             
                     while(rs.next()){
                 i++;
             }
             return i;
                
        }
}
