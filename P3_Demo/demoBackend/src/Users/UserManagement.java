package Users;

import Execptions.Status;

import java.io.*;
import java.util.*;

public class UserManagement {

   

    private static void addUser(User user) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(user.toString());
            writer.newLine();
        }
    }

    public static List<User> getUsers() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Files\\users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(User.fromString(line));
            }
        }
        return users;
    }

    public static User getUserByEmail(String email) throws IOException {
        List<User> users = getUsers();
        System.out.println("Searching for email: " + email);

        for (User user : users) {
            System.out.println("Checking user: " + user.getEmail());
            if (user.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Match found! Returning password.");
                return user;
            }
        }

        System.out.println("Email not found: " + email);
        return null;
    }

    public static  Status createCustomerAccount(String firstName, String lastName, String email, String password1,
            String password2, int userType) {
        try {
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password1.isEmpty()
                    || password2.isEmpty()) {
                throw new Exception("Not all fields are complete.");
            } else if (!email.contains("@") && !email.contains(".")) {
                throw new Exception("The email is not valid.");
            } else if (!password1.equals(password2)) {
                throw new Exception("Password mismatching!");
            } else {
                User user = new User(firstName, lastName, email, password1, 1);
                addUser(user);
                return new Status();
            }
        } catch (Exception e) {
            return new Status(e.getMessage());
        }
    }

    public boolean logIn(String email, String password) throws IOException {
        try {
            User currentUser = getUserByEmail(email);
            if (currentUser == null)
                throw new NullPointerException();
            if (email.isEmpty())
                throw new EmptyString();
            else {
                if (email.contains("@") && email.contains(".")) {
                    if (currentUser.getPassword().equals(password))
                        System.out.println("Oh hi " + currentUser.getName() + "!");
                    else
                        throw new PasswordMismatching();
                } else
                    throw new NotValidEmail();
            }
        } catch (EmptyString e) {
            System.out.println("The email is empty!");
            
            return false;
        } catch (NotValidEmail e) {
            System.out.println("The email is Invalid!");
            
            return false;
        } catch (NullPointerException e) {
            System.out.println("There is no user with that email!");
           
            return false;
        } catch (PasswordMismatching e) {
           
            System.out.println("Password isn't correct");
            return false;
        }
        
        return true;
    }
}
