package bo.edu.ucb.betebackend.domain.utils;

import bo.edu.ucb.betebackend.domain.Match;
import bo.edu.ucb.betebackend.domain.Team;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LeagueUtils {
    static public class GroupMatch {
        private List<Match> matches;
        private Team team;

        public GroupMatch() {
        }

        public List<Match> getMatches() {
            return matches;
        }

        public void setMatches(List<Match> matches) {
            this.matches = matches;
        }

        public Team getTeam() {
            return team;
        }

        public void setTeam(Team team) {
            this.team = team;
        }
    }

    public GroupMatch raffleTeams(List<Team> teams) {
        Collections.shuffle(teams, new Random());
        int count = 1;
        return getGroupMatch(teams, count);
    }

    private GroupMatch getGroupMatch(List<Team> teams, int count) {
        List<Match> matches = new ArrayList<>();
        Match match = new Match();
        GroupMatch groupMatch = new GroupMatch();
        extracted(teams, count, matches, match);
        if (teams.size() % 2 != 0) groupMatch.setTeam(teams.get(teams.size() - 1));
        groupMatch.setMatches(matches);
        return groupMatch;
    }

    private void extracted(List<Team> teams, int count, List<Match> matches, Match match) {
        for (Team team : teams) {
            if (count == 1) {
                match.setTeam1(team);
                count++;
            } else if (count == 2) {
                match.setTeam2(team);
                count = 1;
                matches.add(match);
                match = new Match();
            }
        }
    }
}