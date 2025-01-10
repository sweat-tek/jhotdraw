package org.jhotdraw.action.edit;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.api.app.Application;
import org.jhotdraw.api.app.View;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ThenVerifyOutcome extends Stage<ThenVerifyOutcome> {
        @ProvidedScenarioState
        Action mockRealAction;

        public ThenVerifyOutcome the_real_action_should_be_invoked() {
            verify(mockRealAction).actionPerformed(any(ActionEvent.class));
            return self();
        }

        public ThenVerifyOutcome the_real_action_should_not_be_invoked() {
            verify(mockRealAction, never()).actionPerformed(any(ActionEvent.class));
            return self();
        }
    }



