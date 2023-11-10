package use_case.signup;

//This is the SignupOutputBoundary interface. It declares a method called prepareSuccessView, which takes a SignupOutputData
//object as a parameter, and returns nothing. It also declares a method called prepareFailView, which takes a String as a
//parameter, and returns nothing.
public interface SignupOutputBoundary {
    void prepareSuccessView(SignupOutputData user);

    void prepareFailView(String error);
}