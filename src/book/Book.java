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
    
   public boolean isNull(){
   
    SimpleStringProperty title,id,author;
    SimpleDoubleProperty price;
    if(this.title.get()==null && this.author.get()==null && this.id.get()==0 && this.codebare.get() == null &&this.price.get()==0.0)
        return true;
    return false;
       
   }
    
    public void affiche(){

        System.out.println(id +" "+codebare+" "+title +" "+author +" "+price);
    }
    
    
    
       
    public boolean isEquial(Book b){
        if(this.title.get() == b.title.get() && this.id.get() == b.id.get() && this.author.get() == b.author.get() && this.price.get() == b.price.get())
            return true;
        return false;
    }
}


