package org.example.ext.crdt;

/**
 * 无冲突复制类型
 *
 * @param <T> 值的类型
 * @param <S> State 类型
 */
public interface CRDT<T, S> {

    /**
     * 值，节点之间需要同步的数据
     */
    T getValue();

    /**
     * 节点之间维护同步时使用的状态，序列化后在节点之间传递
     */
    S getState();

    /**
     * 状态合并方法。需要满足交换律、结合律、幂等性
     *
     * @param otherState 待合并状态
     */
    void merge(S otherState);

}
