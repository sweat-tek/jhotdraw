package org.jhotdraw.action.edit;

import org.jhotdraw.action.AbstractViewAction;
import org.jhotdraw.api.app.Application;
import org.jhotdraw.api.app.View;
import org.jhotdraw.util.ResourceBundleUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

/**
 * An abstract base class for undo and redo actions that centralizes common logic.
 * <p>
 * This class uses an {@code actionID} to differentiate between various actions,
 * configures resource bundles, manages property change listeners, and delegates
 * action invocation to the corresponding real action from the view.
 * </p>
 */
public abstract class AbstractUndoRedoAction extends AbstractViewAction {
    protected final String actionID;
    protected final ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.action.Labels");
    private final PropertyChangeListener actionPropertyListener = evt -> {
        String name = evt.getPropertyName();
        if (Objects.equals(name, AbstractAction.NAME)) {
            putValue(AbstractAction.NAME, evt.getNewValue());
        } else if ("enabled".equals(name)) {
            updateEnabledState();
        }
    };

    protected AbstractUndoRedoAction(Application app, View view, String id) {
        super(app, view);
        this.actionID = id;
        labels.configureAction(this, id);
    }

    private Action getRealAction() {
        return (getActiveView() == null) ? null : getActiveView().getActionMap().get(actionID);
    }

    protected void updateEnabledState() {
        boolean isEnabled = false;
        Action realAction = getRealAction();
        if (realAction != null && realAction != this) {
            isEnabled = realAction.isEnabled();
        }
        setEnabled(isEnabled);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Action realAction = getRealAction();
        if (realAction != null && realAction != this) {
            realAction.actionPerformed(e);
        }
    }

    /**
     * Installs listeners on the view object.
     */
    @Override
    protected void installViewListeners(View p) {
        super.installViewListeners(p);
        Action actionInView = p.getActionMap().get(actionID);
        if (actionInView != null && actionInView != this) {
            actionInView.addPropertyChangeListener(actionPropertyListener);
        }
    }


    /**
     * Installs listeners on the view object.
     */
    @Override
    protected void uninstallViewListeners(View p) {
        super.uninstallViewListeners(p);
        Action actionInView = p.getActionMap().get(actionID);
        if (actionInView != null && actionInView != this) {
            actionInView.removePropertyChangeListener(actionPropertyListener);
        }
    }


    @Override
    protected void updateView(View oldValue, View newValue) {
        super.updateView(oldValue, newValue);
        if (newValue == null || newValue.getActionMap().get(actionID) == null) {
            return;
        }
        Action actionInView = newValue.getActionMap().get(actionID);
        if (actionInView != this) {
            putValue(AbstractAction.NAME, actionInView.getValue(AbstractAction.NAME));
            updateEnabledState();
        }
    }


}

