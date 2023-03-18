package main.java.Testing.Experiments;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlID;

@Entity
@Table(name = "User")
public class User {
    
    @ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
        private int id;
    @Column(name = "FName")
        private String FName;
    @Column(name = "LName")
        private String LName;
    @Column(name = "Email")
        private String Email;

    public int getID() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FName;
    }

    public void setFirstName(String FirstName) {
        this.FName = FirstName;
    }

    public String getLastName() {
        return LName;
    }

    public void setLastName(String LastName) {
        this.LName = LastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmailId(String UserEmail) {
        this.Email = UserEmail;
    }
    
}
