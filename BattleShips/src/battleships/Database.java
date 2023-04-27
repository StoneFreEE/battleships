/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author oliver
 */
public class Database {
    private TreeSet<User> users;
    
    public Database() {
        this.users = new TreeSet<>();
    }

    /**
     * @return the users
     */
    public TreeSet<User> getUsers() {
        return users;
    }
    
    public User checkUnique(String username) {
        for (User user : users) {
            if (user.getName().toLowerCase().equals(username.toLowerCase()))
            {
                return user;
            }
        }
        
        return null;
    }
    
    public void updateUser(User o) {
        Iterator it = users.iterator();
        while (it.hasNext()) {
            User currentUser = (User)it.next();
            if (currentUser.equals(o)) {
                if (o.getScore() > currentUser.getScore()) {
                    System.out.println("You got a highscore!!");
                    users.remove(currentUser); 
                    users.add(o);
                    break;
                }
                else {
                    System.out.println("You didn't beat you're highscore, better luck next time!");
                }
            }
        }
        
    }
    
}
