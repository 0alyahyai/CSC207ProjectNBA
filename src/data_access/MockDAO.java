package data_access;

import entity.*;
import use_case.login.LoginUserDataAccessInterface;
import use_case.make_team.MakeTeamDAI;
import use_case.menu.MenuUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.*;

public class MockDAO implements
        SignupUserDataAccessInterface, LoginUserDataAccessInterface,
        MenuUserDataAccessInterface,
        MakeTeamDAI {

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private User activeUser;

    private UserFactory userFactory;

    public MockDAO() {
        this.userFactory = new CommonUserFactory();

    }

    @Override
    public void save(User user) {
        accounts.put(user.getUserName(), user);
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }



    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }


    /**
     * Return the active user.
     * @return the active user
     */
    public User getActiveUser() {
        return activeUser;
    }

    /**
     * Set the active user.
     * @param user the user to set as active
     */
    @Override
    public void setActiveUser(User user) {
        activeUser = user;
    }

    @Override
    public boolean saveTeam(User user, Team team) {
        user.setTeam(team); // user must already be in accounts
        return true;
    }

    @Override
    public Team getTeamOfUser(String userName) {
        return accounts.get(userName).getUserTeam();
    }

}
