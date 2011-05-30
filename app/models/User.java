package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class User extends Model {
    public String username;
    public String fullname;
    public String password;

    public User (String fullname, String username, String password) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }

    public static User connect(String username, String password) {
        return find("byUsernameAndPassword",username, password).first();
    }
}
