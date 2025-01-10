package org.jhotdraw.action.edit;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import static org.mockito.Mockito.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ThenUndoActionIsPerformed extends Stage<ThenUndoActionIsPerformed> {
        @ProvidedScenarioState
        Action mockRealAction;

        public ThenUndoActionIsPerformed the_real_action_should_be_invoked() {
            verify(mockRealAction).actionPerformed(any(ActionEvent.class));
            return self();
        }

        public ThenUndoActionIsPerformed the_real_action_should_not_be_invoked() {
            verify(mockRealAction, never()).actionPerformed(any(ActionEvent.class));
            return self();
        }
    }



