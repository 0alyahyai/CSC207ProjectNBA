package entity;

public interface User {

    String getUserID();

    String getUserName();

    String getUserPassword();

    // returns null if user does not have a team yet
    Team getUserTeam();

    void setTeam(Team userTeam);

    //Added the following methods just to that Main runs., revisit this later and remove if necessary.

    //Method commented out because it is not used because of getUserName() method.

//    String getName();

    //Method deemed unnecessary.
//    String getCreationTime();
}
