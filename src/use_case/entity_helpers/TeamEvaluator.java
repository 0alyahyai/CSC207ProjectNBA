package use_case.entity_helpers;

import entity.Team;

/**
 * Class offering a single method 'evaluateTeam(Team t)' which returns a float representing the "score" of the team.
 * The calculation of this "score" depends on the 'playerEvaluator' attribute used to evaluate the teams players
 * (as well as potentially additional logic specific to each implementation of this abstract class).
 */
public abstract class TeamEvaluator {

    protected PlayerEvaluator playerEvaluator;

    public TeamEvaluator(PlayerEvaluator playerEvaluator) {
        this.playerEvaluator = playerEvaluator;
    }

    /**
     * Evaluates the passed Team using the 'playerEvaluator' attribute (to evaluate the Team's players).
     * @param t the team to be evaluated
     * @return the calculated "score" of Team t
     */
    public abstract float evaluateTeam(Team t);
}
