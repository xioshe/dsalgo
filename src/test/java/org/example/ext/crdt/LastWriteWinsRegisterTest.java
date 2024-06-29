package org.example.ext.crdt;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

class LastWriteWinsRegisterTest {

    @Test
    void test() throws InterruptedException {
        EventBus eventBus = new EventBus();

        var cdrt1 = new LastWriteWinsRegister<>(1, "");
        var cdrt2 = new LastWriteWinsRegister<>(2, "");

        var w1 = new Worker(cdrt1, eventBus);
        eventBus.register(w1);
        var t1 = new Thread(w1);

        var w2 = new Worker(cdrt2, eventBus);
        eventBus.register(w2);
        var t2 = new Thread(w2);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    static class Worker implements Runnable, Listener {

        LastWriteWinsRegister<String> cdrt;
        EventBus eventBus;

        public Worker(LastWriteWinsRegister<String> cdrt, EventBus eventBus) {
            this.cdrt = cdrt;
            this.eventBus = eventBus;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                var change = ThreadLocalRandom.current().nextInt(10);
                cdrt.setValue(cdrt.getValue() + change);
                // 用 event bus 模拟网络通信
                eventBus.post(this, cdrt.getState());

                // 线程随机睡眠 1到3 秒
                try {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 4));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void onEvent(Object stateEvent) {
            cdrt.merge((LastWriteWinsRegister.State<String>) stateEvent);
        }
    }

    interface Listener {

        void onEvent(Object stateEvent);
    }

    static class EventBus {
        Set<Listener> listeners = new HashSet<>();

        synchronized void register(Listener listener) {
            listeners.add(listener);
        }

        synchronized void unregister(Listener listener) {
            listeners.remove(listener);
        }

        synchronized void post(Listener poster, Object event) {
            for (Listener listener : listeners) {
                if (listener != poster) {
                    listener.onEvent(event);
                }
            }
        }
    }

}