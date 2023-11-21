package use_case.signup;

import entity.User;

//This is the SignupUserDataAccessInterface interface. It declares a method called existsByName, which takes a String
//as a parameter, and returns a boolean. It also declares a method called save, which takes a User object as a parameter,
//and returns nothing. It depends on the User class (entity).
public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);
}
