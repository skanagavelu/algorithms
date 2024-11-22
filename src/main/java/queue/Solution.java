package queue;

import java.util.*;
import java.util.stream.Collectors;

class Solution {

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

  public static String rankTeams(String[] votes) {

    Objects.requireNonNull(votes);
    int totalRankColumns = votes[0].length();

    Map<Character, int[]> teamVoteCount = getTeamVoteCount(votes, totalRankColumns);

    return teamVoteCount.entrySet().stream()
        .sorted(Solution.teamComparator(totalRankColumns))
        .map(e -> String.valueOf(e.getKey()))
        .collect(Collectors.joining());
  }

  private static Map<Character, int[]> getTeamVoteCount(String[] votes, int totalRankColumns) {
    Map<Character, int[]> teamVoteCount = new HashMap<>();
    for (int i = 0; i < totalRankColumns; i++) {
      for (String vote : votes) {
        Character team = vote.charAt(i);
        teamVoteCount.computeIfAbsent(team, key -> new int[totalRankColumns]);
        teamVoteCount.get(team)[i]++;
      }
    }
    return teamVoteCount;
  }

  private static Comparator<Map.Entry<Character, int[]>> teamComparator(int totalRankColumns) {
    return (e1, e2) -> {
      for (int i = 0; i < totalRankColumns; i++) {
        if (e1.getValue()[i] == e2.getValue()[i]) {
          continue;
        }
        return (Integer.compare(e2.getValue()[i], e1.getValue()[i]));
      }
      return e1.getKey().compareTo(e2.getKey());
    };
  }
}
