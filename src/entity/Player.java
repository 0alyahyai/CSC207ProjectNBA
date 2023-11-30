package entity;

public interface Player {

    String getPlayerName();

    int getPlayerID();

    String toString();

    // Implementing classes must override this
    boolean equals(Object o);


}
