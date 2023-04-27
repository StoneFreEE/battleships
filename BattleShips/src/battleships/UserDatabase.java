
package battleships;

import java.util.Iterator;
import java.util.TreeSet;

// Manages database of users
public class UserDatabase {
    private TreeSet<User> users;
    
    public UserDatabase() {
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
                    System.out.println("You didn't beat your highscore, better luck next time!");
                }
            }
        }
        
    }
    
}
