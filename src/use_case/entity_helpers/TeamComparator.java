package use_case.entity_helpers;

import entity.Team;

/**
 * A class that aids in the comparison between two teams. This is used both in the leaderboard and the compare-team
 * use-cases of the app.
 * DESIGN PATTERN:
 *      A 2-dimensional Strategy design-pattern is applied here as follows: We might have different strategies to
 *      "evaluate" a player, hence the 'PlayerEvaluator' interface. The "context" abstract class 'TeamEvaluator'
 *      has a 'PlayerEvaluator' as an instance variable, because it will use it to get the "scores" of players,
 *      thus decoupling our algorithm of determining the "value" of a player from the more general class that evaluates
 *      teams.
 *      In turn, we may have various different strategies for "evaluating" a Team, hence the 'TeamEvaluator' abstract
 *      class. The "context" abstract class 'TeamComparator' has a 'TeamEvaluator' as an instance variable, because it
 *      will use it to get the "scores" of teams (passed as arguments to methods), thus decoupling our algorithm of
 *      determining the "value" of a team from the more general class whose purpose is to compare two+ teams.
 *      A major design decision we had to make was to make 'TeamEvaluator' an abstract class instead of an interface.
 *      Consultation with the Professor helped us understand that both are valid. However, we finally decided to
 *      make 'TeamEvaluator' and abstract class because this way we could add a 'PlayerEvaluator' as an instance
 *      variable (instead of having to pass it as an argument in a class, which would require the TeamComparator class
 *      to know about it).
 */
public abstract class TeamComparator {
    /**
     * This attribute showcases the implementation of the strategy design pattern.
     * We can make different team-evaluation algorithms through different implementations of TeamEvaluator,
     * and this class will evaluate teams based on the TeamEvaluator used to instantiate it (i.e. passed to it as an
     * attribute).
     */
    protected final TeamEvaluator teamEvaluator;

    protected TeamComparator(TeamEvaluator teamEvaluator) {
        this.teamEvaluator = teamEvaluator;
    }

    /**
     * Compares two teams given the 'teamEvaluator' field and returns the winning team.
     * @param t1 - a team
     * @param t2 - another team
     * @return winning team
     */
    public abstract Team compareTeams(Team t1, Team t2);

    /**
     * Returns the "score" of the passed team based the 'teamEvaluator' attribute.
     * @param t - the team to be evaluated
     * @return evaluation of winning team
     */
    public float getTeamScore(Team t) {
        return teamEvaluator.evaluateTeam(t);
    }
}
