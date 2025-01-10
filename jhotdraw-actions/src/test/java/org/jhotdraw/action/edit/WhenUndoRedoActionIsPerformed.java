package org.jhotdraw.action.edit;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import java.awt.event.ActionEvent;

public class WhenUndoRedoActionIsPerformed extends Stage<WhenUndoRedoActionIsPerformed> {
        @ProvidedScenarioState
        AbstractUndoRedoAction action;

        @ProvidedScenarioState
        ActionEvent mockEvent;

        public WhenUndoRedoActionIsPerformed the_undo_action_is_performed() {
            action.actionPerformed(mockEvent);
            return self();
        }
    }
