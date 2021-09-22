package trie;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

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

public class BitSetCardinalityBenchMark {

    public static void main(String[] args) throws RunnerException {

        Options options = new OptionsBuilder().include(BitSetCardinalityBenchMark.class.getSimpleName())
                                              .forks(1)
                                              .threads(1)
//                                              .warmupForks(1)
                                              .warmupIterations(2)
                                              .measurementIterations(3)
                                              .measurementTime(TimeValue.seconds(4))
                                              .mode(Mode.AverageTime)
                                              .timeUnit(NANOSECONDS)
                                              .build();
        new Runner(options).run();
    }


    static int[] masks32 = {
                       0x1,         0x3,          0x7,         0xF,
                       0x1F,        0x3F,         0x7F,        0xFF,
                       0x1FF,       0x3FF,        0x7FF,       0xFFF,
                       0x1FFF,      0x3FFF,       0x7FFF,      0xFFFF,
                       0x1FFFF,     0x3FFFF,      0x7FFFF,     0xFFFFF,
                       0x1FFFFF,    0x3FFFFF,     0x7FFFFF,    0xFFFFFF,
                       0x1FFFFFF,   0x3FFFFFF,    0x7FFFFFF,   0xFFFFFFF,
                       0x1FFFFFFF,  0x3FFFFFFF,   0x7FFFFFFF,  0xFFFFFFFF
                   };

    @Benchmark
    public void forBitCount(NumberState numberState, Blackhole blackhole) {

//        int s = 0;
//        int n = numberState.num;
//        if ((n & 1) == 1) {
//            s += 1;
//        }
//        for (int i = 1; i < 32; i++) {
//            n >>>= 1;
//            if ((n & 1) == 1) {
//                s += 1;
//            }
//        }

        int n = 0b01100101;
        int upto = 7;
        blackhole.consume(Integer.bitCount(n & upto));
    }

    @Benchmark
    public void IntegerBitCount(NumberState numberState, Blackhole blackhole) {

        int n = numberState.num;
        blackhole.consume(Integer.bitCount(n));
    }

    @State(Scope.Benchmark)
    public static class NumberState {

        int num;

        @Setup
        public void setup() {

            num = 9999;
        }
    }

    /*
    valuating 10:

    Benchmark                                   Mode  Cnt   Score   Error  Units
    BitSetCardinalityBenchMark.IntegerBitCount  avgt    3   2.091 ± 0.993  ns/op
    BitSetCardinalityBenchMark.forBitCount      avgt    3  13.871 ± 3.972  ns/op

    valuating 9999:
    Benchmark                                   Mode  Cnt   Score    Error  Units
    BitSetCardinalityBenchMark.IntegerBitCount  avgt    3   2.029 ±  1.176  ns/op
    BitSetCardinalityBenchMark.forBitCount      avgt    3  20.051 ± 24.417  ns/op

     */
}
