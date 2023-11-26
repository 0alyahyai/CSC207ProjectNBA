package entity;

class CommonPlayer implements Player {

    private final String playerName;

    private final int playerID;

    CommonPlayer(String playerName, int playerID){
        this.playerName = playerName;
        this.playerID = playerID;
    }
    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public int getPlayerID() {
        return playerID;
    }

    public String toString() {
        return playerName;
    }
}
