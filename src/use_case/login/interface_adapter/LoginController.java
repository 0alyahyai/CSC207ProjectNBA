package use_case.login.interface_adapter;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

//This is the login controller Class. It depends on the Input Data, and Input Boundary Interface.
public class LoginController {
    //The following code declares a final variable loginUseCaseInteractor of type LoginInputBoundary. This is the use
    //Case interactor for the login use case, and it implements the LoginInputBoundary interface.

    final LoginInputBoundary loginUseCaseInteractor;

    //The following code declares a constructor for the LoginController class. It takes a LoginInputBoundary object as
    //a parameter, and assigns it to the loginUseCaseInteractor variable, which is declared above, but not initialized.
    //The input is the use case interactor for the login use case, it will provide a concrete implementation of the
    //LoginInputBoundary interface, and can be interchanged with another implementation of the LoginInputBoundary.
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    //The following code declares a method called execute. It takes parameters username and password
    //both of type String. It creates a LoginInputData object, and passes the two parameters to the
    //constructor. It then calls the execute method of the loginUseCaseInteractor object, passing the loginInputData
    //object as a parameter.
    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
}
