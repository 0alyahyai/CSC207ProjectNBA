package entity;

class CommonUser implements User {

    private final int userID;

    private final String userName;

    private final String userPassword;

    private Team userTeam;

    CommonUser(int userID, String userName, String userPassword){
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userTeam = null;
    }


    @Override
    public int getUserID() {
        return userID;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getUserPassword() {
        return userPassword;
    }


    @Override
    // returns null if user does not have a team yet
    public Team getUserTeam() {
        return userTeam;
    }

    public void setTeam(Team userTeam){
        this.userTeam = userTeam;
    }
}
