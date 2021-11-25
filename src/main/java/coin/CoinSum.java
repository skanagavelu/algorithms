package coin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CoinSum {

    private static final List<List<Integer>> permutation = new ArrayList<>();
    private static final Set<Map<Integer, Integer>> combinations = new HashSet<>();

    public static void main(String[] args) {

        int[] coins = {25, 10, 5, 1};
        int sum = 40;

//        coinSumPermutation(coins, sum, new ArrayList<>());
//        for (List<Integer> pathSum : permutaion) {
//            for (Integer coin : pathSum) {
//                System.out.print(coin + " ");
//            }
//            System.out.println();
//        }

        coinSumCombination(coins, sum, new HashMap<>());
        for (Map<Integer, Integer> pathSum : combinations) {
            for (Entry<Integer, Integer> coin : pathSum.entrySet()) {
                for (int i = 0; i < coin.getValue(); i++) {
                    System.out.print(coin.getKey() + " ");
                }
            }
            System.out.println();
        }
    }




    private static void coinSumPermutation(int[] coins, int sum, List<Integer> pathSum) {

        for (int coin : coins) {

            if (coin == sum) {
                pathSum.add(coin);
                permutation.add(new ArrayList<>(pathSum));
                pathSum.remove(pathSum.size() - 1);
                continue;
            }

            if (coin > sum) {
                continue;
            }
            pathSum.add(coin);
            coinSumPermutation(coins, sum - coin, pathSum);
            pathSum.remove(pathSum.size() - 1);
        }
    }


    private static void coinSumCombination(int[] coins, int sum, Map<Integer, Integer> pathSum) {

        for (int coin : coins) {

            if (coin == sum) {
                pathSum.compute(coin, (k, v) -> v == null ? 1 : v + 1);
                combinations.add(new HashMap<>(pathSum));
                if (pathSum.compute(coin, (k, v) ->  v - 1) == 0) {
                    pathSum.remove(coin);
                }
                continue;
            }

            if (coin > sum) {
                continue;
            }

            pathSum.compute(coin, (k, v) -> v == null ? 1 : v + 1);
            coinSumCombination(coins, sum - coin, pathSum);
//            pathSum.compute(coin, (k, v) -> v > 1 ? v - 1 : pathSum.remove(k)); //Concurrent Modification Exception
//            pathSum.values().removeIf(v -> v == 0);
            if (pathSum.compute(coin, (k, v) ->  v - 1) == 0) {
                pathSum.remove(coin);
            }
        }
    }
}
