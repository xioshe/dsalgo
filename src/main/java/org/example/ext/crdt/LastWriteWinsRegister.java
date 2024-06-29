package org.example.ext.crdt;

/**
 * LWW 最后写入优先寄存器，一种简单的 CRDT 实现。
 * <p/>
 * 每个节点维护一个时间戳（版本号），版本号大的覆盖版本号小的
 */
public class LastWriteWinsRegister<T> implements CRDT<T, LastWriteWinsRegister.State<T>> {

    /**
     * 节点 id
     */
    private final int id;

    /**
     * 节点状态
     */
    private State<T> state;

    public LastWriteWinsRegister(int id, T value) {
        this.id = id;
        this.state = new State<>(id, 1, value);
    }

    public LastWriteWinsRegister(int id, State<T> state) {
        this.id = id;
        this.state = state;
    }

    @Override
    public T getValue() {
        return state.value;
    }

    public void setValue(T value) {
        // 修改 value 时版本号自增
        this.state = new State<>(this.id, this.state.timestamp + 1, value);
    }

    @Override
    public State<T> getState() {
        return state;
    }

    @Override
    public void merge(State<T> otherState) {
        // 版本大小代表修改时间的先后，后修改的才能修改前面的
        if (state.timestamp > otherState.timestamp) {
            System.out.printf("%d: discard remote state, current state is %s, remote state is %s%n", id, state, otherState);
            return;
        }
        // 修改时间想同，比较 id，id 大的代表后创建，不能用小 id 覆盖大 id
        if (state.timestamp == otherState.timestamp
            && state.peer > otherState.peer) {
            System.out.printf("%d: discard remote state, current state is %s, remote state is %s%n", id, state, otherState);
            return;
        }
        System.out.printf("%d: accept remote state, current state is %s, remote state is %s%n", id, state, otherState);
        this.state = otherState;
    }

    // 版本号代表了修改的先后顺序
    // peer 代表当前状态属于谁
    public record State<T>(int peer, long timestamp, T value) {

        @Override
        public String toString() {
            return "[" + peer + ", " + timestamp + "]";
        }
    }
}
