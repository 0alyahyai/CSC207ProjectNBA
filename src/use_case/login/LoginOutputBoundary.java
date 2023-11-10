package use_case.login;

//This is the LoginOutputBoundary interface. It declares a method called prepareSuccessView, which takes a LoginOutputData
//object as a parameter, and returns nothing. It also declares a method called prepareFailView, which takes a String as a
//parameter, and returns nothing.
public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData user);

    void prepareFailView(String error);
}