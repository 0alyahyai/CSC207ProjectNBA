package use_case.signup.interface_adapter;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

//This is the SignupController Class. It depends on the Input Data, and Input Boundary Interface.
public class SignupController {

    //The following code declares a final variable userSignupUseCaseInteractor of type SignupInputBoundary. This is the use
    //Case interactor for the signup use case, and it implements the SignupInputBoundary interface.
    final SignupInputBoundary userSignupUseCaseInteractor;

    //The following code declares a constructor for the SignupController class. It takes a SignupInputBoundary object as
    //a parameter, and assigns it to the userSignupUseCaseInteractor variable, which is declared above, but not initialized.
    //The input is the use case interactor for the signup use case, it will provide a concrete implementation of the
    //SignupInputBoundary interface, and can be interchanged with another implementation of the SignupInputBoundary.
    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    //The following code declares a method called execute. It takes parameters username, password1, and password2
    //all of type String. It creates a SignupInputData object, and passes the three parameters to the
    //constructor. It then calls the execute method of the userSignupUseCaseInteractor object, passing the signupInputData
    //object as a parameter.
    public void execute(String username, String password1, String password2) {
        SignupInputData signupInputData = new SignupInputData(
                username, password1, password2);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
