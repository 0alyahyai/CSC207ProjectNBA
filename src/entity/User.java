package entity;

public interface User {

    int getUserID();

    String getUserName();

    String getUserPassword();

    Team getUserTeam();

    void setTeam(Team userTeam);
}
