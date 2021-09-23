package trie;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import static java.util.concurrent.TimeUnit.MICROSECONDS;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import trie.Util.Sequence;
import static trie.Util.generateRandomString;

public class TrieMapBenchMark {


    public static void main(String[] args) throws RunnerException {

        Options options = new OptionsBuilder().include(TrieMapBenchMark.class.getSimpleName())
                                              .forks(1)
                                              .threads(1)
//                                              .warmupForks(1)
                                              .warmupIterations(2)
                                              .measurementIterations(3)
                                              .measurementTime(TimeValue.seconds(4))
                                              .mode(Mode.AverageTime)
                                              .timeUnit(MICROSECONDS)
                                              .build();
        new Runner(options).run();
    }


//    @Benchmark
//    public void insert1000Trie(BenchmarkState1000Insert state, Blackhole blackhole) {
//
//        for(Sequence token: state.tokens) {
//
//            state.trieMap.put(token, token);
//        }
//        blackhole.consume(state.trieMap);
//    }
//
//    @Benchmark
//    public void insert1000Map(BenchmarkState1000Insert state, Blackhole blackhole) {
//
//        for(Sequence token: state.tokens) {
//
//            state.hashMap.put(token, token);
//        }
//        blackhole.consume(state.hashMap);
//    }

    @Benchmark
    public void read1000Trie(BenchmarkState1000Read state, Blackhole blackhole) {

        for(Sequence token: state.tokens) {

            blackhole.consume(state.trieMap.get(token));
        }
    }

    @Benchmark
    public void read1000Map(BenchmarkState1000Read state, Blackhole blackhole) {

        for(Sequence token: state.tokens) {

            blackhole.consume(state.hashMap.get(token));
        }
    }

//    @Benchmark
//    public void insert100000Trie(BenchmarkState100000Insert state, Blackhole blackhole) {
//
//        for(Sequence token: state.tokens) {
//
//            state.trieMap.put(token, token);
//        }
//        blackhole.consume(state.trieMap);
//    }
//
//    @Benchmark
//    public void insert100000Map(BenchmarkState100000Insert state, Blackhole blackhole) {
//
//        for(Sequence token: state.tokens) {
//
//            state.hashMap.put(token, token);
//        }
//        blackhole.consume(state.hashMap);
//    }

    @Benchmark
    public void read100000Trie(BenchmarkState100000Read state, Blackhole blackhole) {

        for(Sequence token: state.tokens) {

            blackhole.consume(state.trieMap.get(token));
        }
    }

    @Benchmark
    public void read100000Map(BenchmarkState100000Read state, Blackhole blackhole) {

        for(Sequence token: state.tokens) {

            blackhole.consume(state.hashMap.get(token));
        }
    }

    @State(Scope.Benchmark)
    public static class BenchmarkState1000Insert {

        TrieMap<Sequence, Sequence> trieMap = new TrieMap<>();
        Map<Sequence, Sequence> hashMap = new ConcurrentHashMap<>();
        Set<Sequence> tokens = new HashSet<>();
        @Setup
        public void setup() {

            trieMap = new TrieMap<>();
            hashMap = new ConcurrentHashMap<>();
            tokens = new HashSet<>();
            for(int i = 0 ; i < 1000; i++) {
                tokens.add(new Sequence(generateRandomString()));
            }
        }
    }


    @State(Scope.Benchmark)
    public static class BenchmarkState100000Insert {

        TrieMap<Sequence, Sequence> trieMap = new TrieMap<>();
        Map<Sequence, Sequence> hashMap = new ConcurrentHashMap<>();
        Set<Sequence> tokens = new HashSet<>();
        @Setup
        public void setup() {

            trieMap = new TrieMap<>();
            hashMap = new ConcurrentHashMap<>();
            tokens = new HashSet<>();
            for(int i = 0 ; i < 100000; i++) {
                tokens.add(new Sequence(generateRandomString()));
            }
        }
    }

    @State(Scope.Benchmark)
    public static class BenchmarkState1000Read {

        TrieMap<Sequence, Sequence> trieMap = new TrieMap<>();
        Map<Sequence, Sequence> hashMap = new ConcurrentHashMap<>();
        Set<Sequence> tokens = new HashSet<>();

        @Setup
        public void setup() {

            trieMap = new TrieMap<>();
            hashMap = new ConcurrentHashMap<>();
            tokens = new HashSet<>();
            for(int i = 0 ; i < 1000; i++) {

                Sequence token = new Sequence(generateRandomString());
                tokens.add(token);
                trieMap.put(token, token);
                hashMap.put(token, token);
            }
        }
    }

    @State(Scope.Benchmark)
    public static class BenchmarkState100000Read {

        TrieMap<Sequence, Sequence> trieMap = new TrieMap<>();
        Map<Sequence, Sequence> hashMap = new ConcurrentHashMap<>();
        Set<Sequence> tokens = new HashSet<>();

        @Setup
        public void setup() {

            trieMap = new TrieMap<>();
            hashMap = new ConcurrentHashMap<>();
            tokens = new HashSet<>();
            for(int i = 0 ; i < 100000; i++) {

                Sequence token = new Sequence(generateRandomString());
                tokens.add(token);
                trieMap.put(token, token);
                hashMap.put(token, token);
            }
        }
    }


    /*
    Benchmark                         Mode  Cnt    Score    Error  Units
TrieMap_BenchMark.insert1000Map   avgt    4   26.934 ±  1.217  us/op
TrieMap_BenchMark.insert1000Trie  avgt    4  116.261 ±  9.792  us/op
TrieMap_BenchMark.read1000Map     avgt    4   13.290 ±  0.546  us/op
TrieMap_BenchMark.read1000Trie    avgt    4  108.571 ± 19.874  us/op



Benchmark                         Mode  Cnt   Score    Error  Units
TrieMap_BenchMark.insert1000Map   avgt    3  29.206 ± 19.927  us/op
TrieMap_BenchMark.insert1000Trie  avgt    3  80.717 ± 61.880  us/op
TrieMap_BenchMark.read1000Map     avgt    3  13.727 ± 26.626  us/op
TrieMap_BenchMark.read1000Trie    avgt    3  80.117 ± 73.892  us/op


Benchmark                         Mode  Cnt   Score    Error  Units
TrieMap_BenchMark.insert1000Map   avgt    3  27.725 ± 10.461  us/op
TrieMap_BenchMark.insert1000Trie  avgt    3  55.023 ± 15.663  us/op
TrieMap_BenchMark.read1000Map     avgt    3  12.176 ±  2.329  us/op
TrieMap_BenchMark.read1000Trie    avgt    3  50.235 ±  2.560  us/op


Benchmark                         Mode  Cnt   Score    Error  Units
TrieMap_BenchMark.insert1000Map   avgt    3  26.874 ± 19.428  us/op
TrieMap_BenchMark.insert1000Trie  avgt    3  38.822 ±  0.456  us/op
TrieMap_BenchMark.read1000Map     avgt    3  11.819 ±  3.568  us/op
TrieMap_BenchMark.read1000Trie    avgt    3  39.704 ±  1.945  us/op


After Sequence insert and read:
Benchmark                          Mode  Cnt      Score       Error  Units
TrieMapBenchMark.insert100000Map   avgt    3   5886.864 ±  5289.910  us/op
TrieMapBenchMark.insert100000Trie  avgt    3  17322.974 ± 34330.425  us/op
TrieMapBenchMark.insert1000Map     avgt    3     31.516 ±     2.770  us/op
TrieMapBenchMark.insert1000Trie    avgt    3     65.555 ±    43.379  us/op
TrieMapBenchMark.read100000Map     avgt    3   9568.874 ±  1057.155  us/op
TrieMapBenchMark.read100000Trie    avgt    3  14497.299 ±  5685.119  us/op
TrieMapBenchMark.read1000Map       avgt    3     20.530 ±     1.155  us/op
TrieMapBenchMark.read1000Trie      avgt    3     43.303 ±    15.968  us/op


Benchmark                          Mode  Cnt      Score       Error  Units
TrieMapBenchMark.insert100000Trie  avgt    3   9975.766 ±   363.426  us/op
TrieMapBenchMark.insert1000Trie    avgt    3     39.757 ±     6.025  us/op
TrieMapBenchMark.read100000Trie    avgt    3  13636.436 ± 47449.180  us/op
TrieMapBenchMark.read1000Trie      avgt    3     33.613 ±    12.318  us/op


Benchmark                          Mode  Cnt      Score      Error  Units
TrieMapBenchMark.insert100000Trie  avgt    3  10558.998 ± 5641.578  us/op
TrieMapBenchMark.insert1000Trie    avgt    3     54.318 ±    9.660  us/op
TrieMapBenchMark.read100000Trie    avgt    3  12335.826 ± 1134.474  us/op
TrieMapBenchMark.read1000Trie      avgt    3     36.596 ±    1.025  us/op


Benchmark                          Mode  Cnt      Score      Error  Units
TrieMapBenchMark.insert100000Map   avgt    3   6308.408 ±  484.931  us/op
TrieMapBenchMark.insert100000Trie  avgt    3  10698.976 ± 1085.481  us/op
TrieMapBenchMark.insert1000Map     avgt    3     32.420 ±    1.191  us/op
TrieMapBenchMark.insert1000Trie    avgt    3     52.714 ±   37.215  us/op
TrieMapBenchMark.read100000Map     avgt    3   7720.364 ±  471.905  us/op
TrieMapBenchMark.read100000Trie    avgt    3  12483.032 ±  310.445  us/op
TrieMapBenchMark.read1000Map       avgt    3     24.361 ±    3.241  us/op
TrieMapBenchMark.read1000Trie      avgt    3     34.499 ±    0.903  us/op
     */
}
