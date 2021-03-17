/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package member;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author hp
 */
public class Member {
   
    private SimpleIntegerProperty id;
    private  SimpleStringProperty fname,lname,cin;

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public void setFname(String fname) {
        this.fname = new SimpleStringProperty(fname);
    }

    public void setLname(String lname) {
        this.lname = new SimpleStringProperty(lname);
    }

    public void setCin(String cin) {
        this.cin =new SimpleStringProperty(cin);
    }

    public int getId() {
        return id.get();
    }

    public String getFname() {
        return fname.get();
    }

    public String getLname() {
        return lname.get();
    }

    public String getCin() {
        return cin.get();
    }
  
}