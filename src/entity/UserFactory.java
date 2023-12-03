package entity;

//This is the UserFactory interface. It declares a method called create, which takes a String, a String, and a LocalDateTime
//as parameters, and returns a User. It depends on the User class (entity).
public interface UserFactory {
    /** Requires: password is valid. */
    User create(String userID, String userName, String userPassword);

    User createMockUser();
}
