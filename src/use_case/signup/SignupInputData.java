package use_case.signup;

//This is the SignupInputData class. It is a simple data class, which has three private final variables, username,
//password, and repeatPassword. It has a constructor which takes three parameters, and assigns them to the variables.
//It has getter methods, one for each variable.
public class SignupInputData {

    //Variable Declarations:
    final private String username;
    final private String password;
    final private String repeatPassword;

    //Constructor:
    public SignupInputData(String username, String password, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    //Getter Methods:
    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}
