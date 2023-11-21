package use_case.login;

//This is the LoginInputBoundary interface. It declares a method called execute, which takes a LoginInputData object
//as a parameter, and returns nothing (This is how interfaces are declared in Java, and how they represent methods).
public interface LoginInputBoundary {
    void execute(LoginInputData loginInputData);
}
