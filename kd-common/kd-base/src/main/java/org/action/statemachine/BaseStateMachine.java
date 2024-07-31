package org.action.statemachine;

import java.util.HashMap;
import java.util.Map;
import static org.action.enums.BizErrorCode.STATE_MACHINE_TRANSITION_FAILED;
import org.action.exception.BizException;

/**
 * @author gzw
 * @description：
 * @since：2024/7/31 00:28
 */
@SuppressWarnings("unused")
public class BaseStateMachine<STATE,EVENT> implements StateMachine<STATE,EVENT> {

  private final Map<String,STATE> stateTransitions = new HashMap<>();

    protected void putTransition(STATE origin, EVENT event, STATE target) {
        stateTransitions.put(origin+"_"+event, target);
    }

    @Override
    public STATE takeTransition(STATE state, EVENT event) {
        STATE target = stateTransitions.get(state + "_" + event);
        if (target == null) {
            throw new BizException("state = " + state + " , event = " + event, STATE_MACHINE_TRANSITION_FAILED);
        }
        return target;
    }
}
