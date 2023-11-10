package use_case.login;

import entity.User;

//This is the LoginInteractor Class. It depends on the LoginUserDataAccessInterface, and implements
//the LoginInputBoundary interface. It also depends on the LoginOutputBoundary interface, as well as the
//LoginOutputData class. Also it depends on the User class (entity), and the input data class LoginInputData.
public class LoginInteractor implements LoginInputBoundary {

    //The following code declares a final variable userDataAccessObject of type LoginUserDataAccessInterface. This is
    //the data access object for the login use case, and it implements the LoginUserDataAccessInterface interface.
    //The following code declares a final variable loginPresenter of type LoginOutputBoundary. This is the presenter for
    //the login use case, and it implements the LoginOutputBoundary interface.
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    //The following code declares a constructor for the LoginInteractor class. It takes a LoginUserDataAccessInterface
    //object, and a LoginOutputBoundary object as parameters, and assigns them to the userDataAccessObject, and
    //loginPresenter variables, which are declared above, but not initialized. The input is the data access object for
    //the login use case, and the presenter for the login use case, they will provide concrete implementations of the
    //LoginUserDataAccessInterface, and LoginOutputBoundary interfaces, and can be interchanged with other
    //implementations of the LoginUserDataAccessInterface, and LoginOutputBoundary.
    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    //The following code declares a method called execute. It takes a LoginInputData object as a parameter, and returns
    //nothing It first gets the username, then the password. It then checks if the user exists in the database using the
    //userDataAccessObject. If the user does not exist, it calls the prepareFailView method of the loginPresenter object,
    //passing a message as a parameter. If the user does exist, it gets the password from the database (userDataAccessObject)
    //and compares it to the password entered by the user. If the passwords don't match, it calls the prepareFailView method
    //of the loginPresenter object, passing a message as a parameter. If the passwords do match, it gets the user from the
    //database (userDataAccessObject), and creates a LoginOutputData object, passing the username, and false as parameters.
    //It then calls the prepareSuccessView method of the loginPresenter object, passing the loginOutputData object as a
    //parameter.

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            String pwd = userDataAccessObject.get(username).getUserPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {

                User user = userDataAccessObject.get(loginInputData.getUsername());

                LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}