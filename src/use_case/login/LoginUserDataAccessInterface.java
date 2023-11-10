package use_case.login;

import entity.User;

//This is the LoginUserDataAccessInterface interface. It declares a method called existsByName, which takes a String
//as a parameter, and returns a boolean. It also declares a method called save, which takes a User object as a parameter,
//and returns nothing. It also declares a method called get, which takes a String as a parameter, and returns a User.
//It depends on the User class (entity).
public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);

    User get(String username);
}
