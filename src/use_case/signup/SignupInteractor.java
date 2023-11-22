package use_case.signup;

import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;
import java.util.UUID;

//This is the SignupInteractor Class. It depends on the SignupUserDataAccessInterface, and implements
//the SignupInputBoundary interface. It also depends on the SignupOutputBoundary interface, as well as the
//SignupOutputData class. Also it depends on the User class (entity), and the input data class SignupInputData.
public class SignupInteractor implements SignupInputBoundary {

    //The following code declares a final variable userDataAccessObject of type SignupUserDataAccessInterface. This is
    //the data access object for the signup use case, and it implements the SignupUserDataAccessInterface interface.
    //The following code declares a final variable userPresenter of type SignupOutputBoundary. This is the presenter for
    //the signup use case, and it implements the SignupOutputBoundary interface.
    //The following code declares a final variable userFactory of type UserFactory. This is the factory for
    //the user entity, and it implements the UserFactory interface.
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    //The following code declares a constructor for the SignupInteractor class. It takes a SignupUserDataAccessInterface
    //object, a SignupOutputBoundary object, and a UserFactory object as parameters, and assigns them to the userDataAccessObject,
    //userPresenter, and userFactory variables, which are declared above, but not initialized. The input is the data access object for
    //the signup use case, the presenter for the signup use case, and the factory for the user entity, they will provide concrete
    //implementations of the SignupUserDataAccessInterface, SignupOutputBoundary, and UserFactory interfaces, and can be interchanged
    //with other implementations of the SignupUserDataAccessInterface, SignupOutputBoundary, and UserFactory.
    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    //The following code declares a method called execute. It takes a SignupInputData object as a parameter, and returns
    //nothing It checks to see whether the user exists in the database using the userDataAccessObject method.
    // If the user does exist, it calls the prepareFailView method of the userPresenter object, passing a message as a parameter.
    // If the user does not exist, it checks to see if the password and repeat password match. If they don't match, it calls the
    // prepareFailView method of the userPresenter object, passing a message as a parameter. If they do match, it creates a new
    // user using the userFactory, and saves it to the database using the userDataAccessObject. It then creates a SignupOutputData
    // object, passing the username, the current time, and false as parameters. It then calls the prepareSuccessView method of the
    // userPresenter object, passing the signupOutputData object as a parameter.
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (signupInputData.getUsername().equals("") || signupInputData.getPassword().equals("")) {
            userPresenter.prepareFailView("Username or password cannot be empty.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            //For now I have just set userID to 1 when generating a new user.
            //ToDo: Create userid generating function (done)
            String userid = UUID.randomUUID().toString();

            User user = userFactory.create(userid, signupInputData.getUsername(), signupInputData.getPassword());
            userDataAccessObject.save(user);

            //I have replaced CreationTime with standard string "string" for now.
            SignupOutputData signupOutputData = new SignupOutputData(user.getUserName(), "string", false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}