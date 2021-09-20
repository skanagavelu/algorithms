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

public class AccessBenchMark {

    public static void main(String[] args) throws RunnerException {

        Options options = new OptionsBuilder().include(AccessBenchMark.class.getSimpleName())
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


    @Benchmark
    public void arrayRead(AccessState state, Blackhole blackhole) {

        blackhole.consume(state.array[14]);
    }

    @Benchmark
    public void LinkRead(AccessState state, Blackhole blackhole) {

        LinkedNode start = state.node;
        for(int j = 0 ; j < 14; j++)
        {
            start = start.getNext();
        }
        blackhole.consume(start);
    }

    @State(Scope.Benchmark)
    public static class AccessState {

        Object[] array = new Object[16];
        LinkedNode node = new LinkedNode() {};
        LinkedNode next = node;

        @Setup
        public void setup() {

            for(int j = 0 ; j < 16; j++)
            {
                LinkedNode newNext = new LinkedNode() {};

                next.setNext(newNext);
                next = newNext;
            }
        }
    }

    /*
    Accessing 10th element when size 16:
    Benchmark                  Mode  Cnt  Score   Error  Units
    AccessBenchMark.LinkRead   avgt    3  4.947 ± 0.403  ns/op
    AccessBenchMark.arrayRead  avgt    3  2.708 ± 1.264  ns/op


    Accessing 14th element when size 16:
    Benchmark                  Mode  Cnt  Score   Error  Units
    AccessBenchMark.LinkRead   avgt    3  6.875 ± 0.725  ns/op
    AccessBenchMark.arrayRead  avgt    3  2.672 ± 0.203  ns/op
     */
}
