package entity;

/**
 * Interface (whose implementations are) used to evaluate a Player with the offered 'evaluatePlayer(Player)' method.
 * This interface is part of a Strategy design pattern; this is the strategy and the context is the 'TeamEvaluator'
 * abstract class.
 */
public interface PlayerEvaluator {

    /**
     * Evaluates the passed player.
     * @param player the player to be evaluated
     * @return a float corresponding to the "evaluation" of the player
     */
    float evaluatePlayer(Player player, Stats statsBoundary);
}
