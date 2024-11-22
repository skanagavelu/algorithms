package misc;

import java.util.HashMap;
import java.util.Map;

/**
 * Input: votes = ["ABC","ACB","ABC","ACB","ACB"] Output: "ACB" Explanation: Team A was ranked first
 * place by 5 voters. No other team was voted as first place, so team A is the first team. Team B
 * was ranked second by 2 voters and ranked third by 3 voters. Team C was ranked second by 3 voters
 * and ranked third by 2 voters. As most of the voters ranked C second, team C is the second team,
 * and team B is the third. Example 2:
 *
 * <p>Input: votes = ["WXYZ","XYZW"] Output: "XWYZ" Explanation: X is the winner due to the
 * tie-breaking rule. X has the same votes as W for the first position, but X has one vote in the
 * second position, while W does not have any votes in the second position. Example 3:
 *
 * <p>Input: votes = ["ZMNAGUEDSJYLBOPHRQICWFXTVK"] Output: "ZMNAGUEDSJYLBOPHRQICWFXTVK"
 * Explanation: Only one voter, so their votes are used for the ranking.
 *
 * <p>Constraints:
 *
 * <p>1 <= votes.length <= 1000 1 <= votes[i].length <= 26 votes[i].length == votes[j].length for 0
 * <= i, j < votes.length. votes[i][j] is an English uppercase letter. All characters of votes[i]
 * are unique. All the characters that occur in votes[0] also occur in votes[j] where 1 <= j <
 * votes.length.
 */
class RankTeamByVotes {
  public static String rankTeams(String[] votes) {
    Map<Character, int[]> teamVoteCount = new HashMap<>();

    // Initialize the team vote counts
    for (String vote : votes) {
      for (int rankPtr = 0; rankPtr < vote.length(); rankPtr++) {
        char team = vote.charAt(rankPtr);
        teamVoteCount.computeIfAbsent(team, k -> new int[votes[0].length()]);
        teamVoteCount.get(team)[rankPtr]++;
      }
    }

    // Sort the teams based on their vote counts
    return teamVoteCount.entrySet().stream()
        .sorted(
            (e1, e2) -> {
              for (int i = 0; i < votes[0].length(); i++) {
                int count1 = e1.getValue()[i];
                int count2 = e2.getValue()[i];

                if (count1 != count2) {
                  return Integer.compare(count2, count1); // Sort in descending order
                }
              }
              return Character.compare(
                  e1.getKey(), e2.getKey()); // If all vote counts are equal, sort alphabetically
            })
        .map(e -> String.valueOf(e.getKey()))
        .reduce("", (a, b) -> a + b);
    //        .map(Map.Entry::getKey)
    //        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
    //        .toString();
  }

  public static void main(String[] args) {
    //    String[] votes = {"AECB", "AEBC"};
    String[] votes = {
      "FVSHJIEMNGYPTQOURLWCZKAX",
      "AITFQORCEHPVJMXGKSLNZWUY",
      "OTERVXFZUMHNIYSCQAWGPKJL",
      "VMSERIJYLZNWCPQTOKFUHAXG",
      "VNHOZWKQCEFYPSGLAMXJIUTR",
      "ANPHQIJMXCWOSKTYGULFVERZ",
      "RFYUXJEWCKQOMGATHZVILNSP",
      "SCPYUMQJTVEXKRNLIOWGHAFZ",
      "VIKTSJCEYQGLOMPZWAHFXURN",
      "SVJICLXKHQZTFWNPYRGMEUAO",
      "JRCTHYKIGSXPOZLUQAVNEWFM",
      "NGMSWJITREHFZVQCUKXYAPOL",
      "WUXJOQKGNSYLHEZAFIPMRCVT",
      "PKYQIOLXFCRGHZNAMJVUTWES",
      "FERSGNMJVZXWAYLIKCPUQHTO",
      "HPLRIUQMTSGYJVAXWNOCZEKF",
      "JUVWPTEGCOFYSKXNRMHQALIZ",
      "MWPIAZCNSLEYRTHFKQXUOVGJ",
      "EZXLUNFVCMORSIWKTYHJAQPG",
      "HRQNLTKJFIEGMCSXAZPYOVUW",
      "LOHXVYGWRIJMCPSQENUAKTZF",
      "XKUTWPRGHOAQFLVYMJSNEIZC",
      "WTCRQMVKPHOSLGAXZUEFYNJI"
    };

    System.out.println(rankTeams(votes));
  }
}
