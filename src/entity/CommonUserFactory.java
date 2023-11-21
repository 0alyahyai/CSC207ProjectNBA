package entity;

import java.time.LocalDateTime;

public class CommonUserFactory implements UserFactory {
    /**
     * Requires: password is valid.
     * @param userID
     * @param userName
     * @return
     */

    @Override
    public User create(int userID, String userName, String userPassword) {
        return new CommonUser(userID, userName, userPassword);
    }
}
