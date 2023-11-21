package use_case.clear_users;

public interface ClearUserDataAccessInterface {
    String[] clearUsers();
    //Removes all users from the database, returning a list of users removed.
}
