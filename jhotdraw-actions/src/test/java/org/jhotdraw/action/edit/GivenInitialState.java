package org.jhotdraw.action.edit;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.api.app.Application;
import org.jhotdraw.api.app.View;
import org.mockito.Mockito;
import org.mockito.Mockito.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.mockito.Mockito.mock;

public class GivenInitialState extends Stage<GivenInitialState> {
    @ProvidedScenarioState
    Application mockApp;

    @ProvidedScenarioState
    View mockView;

    @ProvidedScenarioState
    AbstractUndoRedoAction action;

    @ProvidedScenarioState
    ActionEvent mockEvent;

    @ProvidedScenarioState
    Action mockRealAction;


    public GivenInitialState an_application_is_running() {
        mockApp = mock(Application.class);
        mockView = mock(View.class);
        mockEvent = mock(ActionEvent.class);
        mockRealAction = mock(Action.class);
        Mockito.when(mockView.getActionMap()).thenReturn(new ActionMap());
        action = new AbstractUndoRedoAction(mockApp, mockView, "undo") {
        };
        return self();
    }

    public GivenInitialState a_real_action_is_set() {
        mockView.getActionMap().put("undo", mockRealAction);
        action.updateView(null, mockView);
        return self();
    }
}
