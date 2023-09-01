package future;


import com.google.common.util.concurrent.SettableFuture;

import java.util.concurrent.Future;

public class ImplementingThing {

    public Baz doSomethingAndBlock(Foo fooArg, Bar barArg) {
        try {
            return doSomething(fooArg, barArg).get();
        } catch (Exception e) {
            throw new RuntimeException("Oh dear");
        }
    }


    public Future<Baz> doSomething(Foo fooArg, Bar barArg) {
        final SettableFuture<Baz> future = SettableFuture.create();
        doSomethingAsync(fooArg, barArg, new BazComputationSink() {
            @Override
            public void onBazResult(Baz result) {
                future.set(result);
            }
        });
        return future;
    }


// Everything below here is just mock stuff to make the example work,
// so you can copy it into your IDE and see it run.

    public static class Baz {
    }

    public static class Foo {
    }

    public static class Bar {
    }

    public static interface BazComputationSink {
        public void onBazResult(Baz result);
    }

    public void doSomethingAsync(Foo fooArg, Bar barArg, final BazComputationSink sink) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Baz baz = new Baz();
                sink.onBazResult(baz);
            }
        }).start();
    }


    public static void main(String[] args) {
        System.err.println("Starting Main");
        System.err.println((new ImplementingThing()).doSomethingAndBlock(null, null));
        System.err.println("Ending Main");
    }
}