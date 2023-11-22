package entity;

public interface User {

    String getUserID();

    String getUserName();

    String getUserPassword();

    String getCreationTIme();

    Team getUserTeam();

    void setTeam(Team userTeam);

    //Added the following methods just to that Main runs., revisit this later and remove if necessary.
    String getName();

    String getCreationTime();
}
