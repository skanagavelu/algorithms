//package trie;
//
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Random;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//import static java.util.concurrent.TimeUnit.MICROSECONDS;
//
//import org.openjdk.jmh.annotations.Benchmark;
//import org.openjdk.jmh.annotations.Mode;
//import org.openjdk.jmh.annotations.Scope;
//import org.openjdk.jmh.annotations.Setup;
//import org.openjdk.jmh.annotations.State;
//import org.openjdk.jmh.infra.Blackhole;
//import org.openjdk.jmh.runner.Runner;
//import org.openjdk.jmh.runner.RunnerException;
//import org.openjdk.jmh.runner.options.Options;
//import org.openjdk.jmh.runner.options.OptionsBuilder;
//import org.openjdk.jmh.runner.options.TimeValue;
//import static trie.Util.generateRandomString;
//
//public class TrieMap_BenchMark {
//
////     TrieMap<String, String> trieMap = new TrieMap<>();
////     Map<String, String> hashMap = new ConcurrentHashMap<>();
//
//    public static void main(String[] args) throws RunnerException {
//        TrieMap_BenchMark instance = new TrieMap_BenchMark();
//        Options options = new OptionsBuilder().include(TrieMap_BenchMark.class.getSimpleName())
//                                              .forks(1)
//                                              .threads(1)
////                                              .warmupForks(1)
//                                              .warmupIterations(2)
//                                              .measurementIterations(3)
//                                              .measurementTime(TimeValue.seconds(4))
//                                              .mode(Mode.AverageTime)
//                                              .timeUnit(MICROSECONDS)
//                                              .build();
//        new Runner(options).run();
//
//
//
//
////        Set<String> tokens = new HashSet<>();
////
////
////            instance.trieMap = new TrieMap<>();
////            tokens = new HashSet<>();
////            for(int i = 0 ; i < 20000; i++) {
////
////                String token = generateRandomString();
////                tokens.add(token);
////                instance.trieMap.put(token, token);
////                instance.hashMap.put(token, token);
////            }
//
//
//
////        for(String token: tokens) {
////            long currentTime = System.nanoTime();
////            instance.trieMap.get(token);
////            System.out.println(System.nanoTime() - currentTime + "  "+token);
////        }
////        for(String token: tokens) {
////            long currentTime = System.nanoTime();
////            trieMap.get(token); // ntaefhi   | 1212  sgjfh |  1512  uwrjdyxlp  1442  bje  | 549  hpjwslbty | 547
////            // ppxkhx | 965  bgb | 929  cibqzaitb | 996  enjdqxv
////
////
////            System.out.println(System.nanoTime() - currentTime + "  "+token);
////        }
//    }
//
//
//    @Benchmark
//    public void insert1000Trie(BenchmarkState1000Insert state, Blackhole blackhole) {
//
//        for(String token: state.tokens) {
//
//            state.trieMap.put(token, token);
//        }
//        blackhole.consume(state.trieMap);
//    }
//
//    @Benchmark
//    public void insert1000Map(BenchmarkState1000Insert state, Blackhole blackhole) {
//
//        for(String token: state.tokens) {
//
//            state.hashMap.put(token, token);
//        }
//        blackhole.consume(state.hashMap);
//    }
//
//    @Benchmark
//    public void read1000Trie(BenchmarkState1000Read state, Blackhole blackhole) {
//
//        for(String token: state.tokens) {
//
//            blackhole.consume(state.trieMap.get(token));
//        }
//    }
//
//    @Benchmark
//    public void read1000Map(BenchmarkState1000Read state, Blackhole blackhole) {
//
//        for(String token: state.tokens) {
//
//            blackhole.consume(state.hashMap.get(token));
//        }
//    }
//
////    @Benchmark
////    public void insert100000Trie(BenchmarkState100000Insert state, Blackhole blackhole) {
////
////        for(String token: state.tokens) {
////
////            state.trieMap.put(token, token);
////        }
////        blackhole.consume(state.trieMap);
////    }
//
////    @Benchmark
////    public void insert100000Map(BenchmarkState100000Insert state, Blackhole blackhole) {
////
////        for(String token: state.tokens) {
////
////            state.hashMap.put(token, token);
////        }
////        blackhole.consume(state.hashMap);
////    }
//
////    @Benchmark
////    public void read100000Trie(BenchmarkState100000Read state, Blackhole blackhole) {
////
////        for(String token: state.tokens) {
////
////            blackhole.consume(state.trieMap.get(token));
////        }
////    }
//
////    @Benchmark
////    public void read100000Map(BenchmarkState100000Read state, Blackhole blackhole) {
////
////        for(String token: state.tokens) {
////
////            blackhole.consume(state.hashMap.get(token));
////        }
////    }
//
//    @State(Scope.Benchmark)
//    public static class BenchmarkState1000Insert {
//
//        TrieMap<String, String> trieMap = new TrieMap<>();
//        Map<String, String> hashMap = new ConcurrentHashMap<>();
//        Set<String> tokens = new HashSet<>();
//        @Setup
//        public void setup() {
//
//            trieMap = new TrieMap<>();
//            hashMap = new ConcurrentHashMap<>();
//            tokens = new HashSet<>();
//            for(int i = 0 ; i < 1000; i++) {
//                tokens.add(generateRandomString());
//            }
//        }
//    }
//
//
//    @State(Scope.Benchmark)
//    public static class BenchmarkState100000Insert {
//
//        TrieMap<String, String> trieMap = new TrieMap<>();
//        Map<String, String> hashMap = new ConcurrentHashMap<>();
//        Set<String> tokens = new HashSet<>();
//        @Setup
//        public void setup() {
//
//            trieMap = new TrieMap<>();
//            hashMap = new ConcurrentHashMap<>();
//            tokens = new HashSet<>();
//            for(int i = 0 ; i < 100000; i++) {
//                tokens.add(generateRandomString());
//            }
//        }
//    }
//
//    @State(Scope.Benchmark)
//    public static class BenchmarkState1000Read {
//
//        TrieMap<String, String> trieMap = new TrieMap<>();
//        Map<String, String> hashMap = new ConcurrentHashMap<>();
//        Set<String> tokens = new HashSet<>();
//
//        @Setup
//        public void setup() {
//
//            trieMap = new TrieMap<>();
//            hashMap = new ConcurrentHashMap<>();
//            tokens = new HashSet<>();
//            for(int i = 0 ; i < 1000; i++) {
//
//                String token = generateRandomString();
//                tokens.add(token);
//                trieMap.put(token, token);
//                hashMap.put(token, token);
//            }
//        }
//    }
//
//    @State(Scope.Benchmark)
//    public static class BenchmarkState100000Read {
//
//        TrieMap<String, String> trieMap = new TrieMap<>();
//        Map<String, String> hashMap = new ConcurrentHashMap<>();
//        Set<String> tokens = new HashSet<>();
//
//        @Setup
//        public void setup() {
//
//            trieMap = new TrieMap<>();
//            hashMap = new ConcurrentHashMap<>();
//            tokens = new HashSet<>();
//            for(int i = 0 ; i < 100000; i++) {
//
//                String token = generateRandomString();
//                tokens.add(token);
//                trieMap.put(token, token);
//                hashMap.put(token, token);
//            }
//        }
//    }
//
//
////    public static String generateRandomString() {
////
////        int leftLimit = 97; // letter 'a'
////        int rightLimit = 122; // letter 'z'
////        Random random = new Random();
////        int targetStringLength = random.nextInt((10 - 1) + 1) + 1;
////        StringBuilder buffer = new StringBuilder(targetStringLength);
////        for (int i = 0; i < targetStringLength; i++) {
////            int randomLimitedInt = leftLimit + (int)
////                    (random.nextFloat() * (rightLimit - leftLimit + 1));
////            buffer.append((char) randomLimitedInt);
////        }
////        String generatedString = buffer.toString();
////        return generatedString;
////    }
//
//
//
//
//    /*
//    Benchmark                         Mode  Cnt    Score    Error  Units
//TrieMap_BenchMark.insert1000Map   avgt    4   26.934 ±  1.217  us/op
//TrieMap_BenchMark.insert1000Trie  avgt    4  116.261 ±  9.792  us/op
//TrieMap_BenchMark.read1000Map     avgt    4   13.290 ±  0.546  us/op
//TrieMap_BenchMark.read1000Trie    avgt    4  108.571 ± 19.874  us/op
//
//
//
//Benchmark                         Mode  Cnt   Score    Error  Units
//TrieMap_BenchMark.insert1000Map   avgt    3  29.206 ± 19.927  us/op
//TrieMap_BenchMark.insert1000Trie  avgt    3  80.717 ± 61.880  us/op
//TrieMap_BenchMark.read1000Map     avgt    3  13.727 ± 26.626  us/op
//TrieMap_BenchMark.read1000Trie    avgt    3  80.117 ± 73.892  us/op
//
//
//
//Benchmark                         Mode  Cnt   Score    Error  Units
//TrieMap_BenchMark.insert1000Map   avgt    3  27.725 ± 10.461  us/op
//TrieMap_BenchMark.insert1000Trie  avgt    3  55.023 ± 15.663  us/op
//TrieMap_BenchMark.read1000Map     avgt    3  12.176 ±  2.329  us/op
//TrieMap_BenchMark.read1000Trie    avgt    3  50.235 ±  2.560  us/op
//
//
//Benchmark                         Mode  Cnt   Score    Error  Units
//TrieMap_BenchMark.insert1000Map   avgt    3  26.874 ± 19.428  us/op
//TrieMap_BenchMark.insert1000Trie  avgt    3  38.822 ±  0.456  us/op
//TrieMap_BenchMark.read1000Map     avgt    3  11.819 ±  3.568  us/op
//TrieMap_BenchMark.read1000Trie    avgt    3  39.704 ±  1.945  us/op
//
//     */
//}
