package org.action.statemachine;

/**
 * @author gzw
 * @description：
 * @since：2024/7/31 00:26
 */
public interface StateMachine<STATE, EVENT> {


    /**
     * 状态机转移
     * @param state orgion
     * @param event event
     * @return
     */

    public STATE takeTransition(STATE state, EVENT event);

}
