package use_case.login;

//This is the LoginInputData class. It is a simple data class, which has two private final variables, username
//and password. It has a constructor which takes two parameters, and assigns them to the variables.
//It has getter methods, one for each variable.
public class LoginInputData {

    //Variable Declarations:
    final private String username;
    final private String password;

    //Constructor:
    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Getter Methods:
    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

}
