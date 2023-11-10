package interface_adapter.Menu;

import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInputData;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class MenuController {

    //The following code declares a final variable userSignupUseCaseInteractor of type SignupInputBoundary. This is the use
    //Case interactor for the signup use case, and it implements the SignupInputBoundary interface.
    final MenuInputBoundary MenuUseCaseInteractor;

    //The following code declares a constructor for the SignupController class. It takes a SignupInputBoundary object as
    //a parameter, and assigns it to the userSignupUseCaseInteractor variable, which is declared above, but not initialized.
    //The input is the use case interactor for the signup use case, it will provide a concrete implementation of the
    //SignupInputBoundary interface, and can be interchanged with another implementation of the SignupInputBoundary.
    public MenuController(MenuInputBoundary MenuUseCaseInteractor) {
        this.MenuUseCaseInteractor = MenuUseCaseInteractor;
    }

    //The following code declares a method called execute. It takes parameters username, password1, and password2
    //all of type String. It creates a SignupInputData object, and passes the three parameters to the
    //constructor. It then calls the execute method of the userSignupUseCaseInteractor object, passing the signupInputData
    //object as a parameter.
    public void execute(String buttonName) {
        MenuInputData MenuInputData = new MenuInputData(buttonName);

        MenuUseCaseInteractor.execute(MenuInputData);
    }
}
