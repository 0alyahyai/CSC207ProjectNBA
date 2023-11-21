package use_case.signup;

//This is the SignupInputBoundary interface. It declares a method called execute, which takes a SignupInputData object
//as a parameter, and returns nothing (This is how interfaces are declared in Java, and how they represent methods).
public interface SignupInputBoundary {
    void execute(SignupInputData signupInputData);
}
