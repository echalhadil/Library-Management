package book;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
    private  SimpleStringProperty title,codebare,author;
    private SimpleIntegerProperty id;
    private  SimpleDoubleProperty price;
    

    
    public String getTitle(){
        return this.title.get();
    }
    

    public int getId(){
        return this.id.get();
    }
    
    public String getAuthor(){
        return this.author.get();
    }
    
     public String getCodebare(){
        return this.codebare.get();
    }
    
    public double getPrice(){
        return this.price.get();
    }
     
    
    public void setTitle(String title){
        this.title = new SimpleStringProperty (title);
    }
    public void setId(int id){
        this.id = new SimpleIntegerProperty (id);
    }
    public void setAuthor(String author){
        this.author = new SimpleStringProperty (author);
    }
      public void setCodebare(String codebare){
        this.codebare = new SimpleStringProperty (codebare);
    }
    public void setPrice(double price){
        this.price = new SimpleDoubleProperty (price);
    }
    
    
    public void affiche(){

        System.out.println(id +" "+codebare+" "+title +" "+author +" "+price);
    }
    
  
}


