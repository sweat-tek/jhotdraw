package org.jhotdraw.action.edit;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

public class AbstractUndoRedoActionBDDTest extends ScenarioTest<GivenInitialState, WhenUndoRedoActionIsPerformed, ThenUndoActionIsPerformed> {

    @Test
    public void undo_action_should_delegate_to_real_action() {
        given().an_application_is_running().and().a_real_action_is_set();
        when().the_undo_action_is_performed();
        then().the_real_action_should_be_invoked();
    }

    @Test
    public void undo_action_should_do_nothing_if_no_real_action() {
        given().an_application_is_running();
        when().the_undo_action_is_performed();
        then().the_real_action_should_not_be_invoked();
    }
}
