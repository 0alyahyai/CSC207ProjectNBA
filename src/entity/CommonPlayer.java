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

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        Player player = (CommonPlayer) o;
        // field comparison
        boolean idMatch = (playerID == player.getPlayerID());
        boolean nameMatch = playerName.equals(player.getPlayerName());

        return idMatch && nameMatch;
    }
}
