package org.jhotdraw.action.edit;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.jhotdraw.action.edit.AbstractUndoRedoAction;
import org.junit.jupiter.api.BeforeEach;
import org.jhotdraw.api.app.Application;
import org.jhotdraw.api.app.View;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

class AbstractUndoRedoActionTest {
    private Application mockApp;
    private View mockView;
    private AbstractUndoRedoAction action;
    private ActionEvent mockEvent;
    private Action mockRealAction;

    @BeforeEach
    void setUp() {
        mockApp = mock(Application.class);
        mockView = mock(View.class);
        mockEvent = mock(ActionEvent.class);
        mockRealAction = mock(Action.class);
        when(mockView.getActionMap()).thenReturn(new ActionMap());
        action = new AbstractUndoRedoAction(mockApp, mockView, "undo") {};
    }

    @Test
    void actionPerformed_delegatesToRealAction() {
        mockView.getActionMap().put("undo", mockRealAction);
        action.updateView(null, mockView);
        action.actionPerformed(mockEvent);
        verify(mockRealAction).actionPerformed(mockEvent);
    }

    @Test
    void actionPerformed_noRealAction_doesNothing() {
        action.updateView(null, mockView);
        action.actionPerformed(mockEvent);
        verify(mockRealAction, never()).actionPerformed(mockEvent);
    }

    @Test
    void updateEnabledState_realActionEnabled_setsEnabledTrue() {
        when(mockRealAction.isEnabled()).thenReturn(true);
        when(mockApp.isEnabled()).thenReturn(true);
        when(mockView.isEnabled()).thenReturn(true);
        mockView.getActionMap().put("undo", mockRealAction);
        action.updateView(null, mockView);
        action.updateEnabledState();
        assertTrue(action.isEnabled());
    }
    @Test
    void updateEnabledState_noRealAction_setsEnabledFalse() {
        action.updateView(null, mockView);
        action.updateEnabledState();
        assertFalse(action.isEnabled());
    }

    @Test
    void installViewListeners_addsPropertyChangeListener() {
        mockView.getActionMap().put("undo", mockRealAction);
        action.installViewListeners(mockView);
        verify(mockRealAction).addPropertyChangeListener(any(PropertyChangeListener.class));
    }
    @Test
    void uninstallViewListeners_removesPropertyChangeListener() {
        mockView.getActionMap().put("undo", mockRealAction);
        action.uninstallViewListeners(mockView);
        verify(mockRealAction).removePropertyChangeListener(any(PropertyChangeListener.class));
    }
}