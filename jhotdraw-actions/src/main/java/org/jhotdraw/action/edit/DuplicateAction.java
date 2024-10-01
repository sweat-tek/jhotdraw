/*
 * @(#)DuplicateAction.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.action.edit;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.jhotdraw.api.gui.EditableComponent;
import org.jhotdraw.util.*;


/**
 * Duplicates the selected region.
 * <p>
 * This action acts on the last {@link org.jhotdraw.gui.EditableComponent} /
 * {@code JTextComponent} which had the focus when the {@code ActionEvent}
 * was generated.
 * <p>
 * This action is called when the user selects the Duplicate item in the Edit
 * menu. The menu item is automatically created by the application.
 * <p>
 * If you want this behavior in your application, you have to create an action
 * with this ID and put it in your {@code ApplicationModel} in method
 * {@link org.jhotdraw.app.ApplicationModel#initApplication}.
 *
 * <hr>
 * <b>Design Patterns</b>
 *
 * <p>
 * <em>Framework</em><br>
 * The interfaces and classes listed below work together:
 * <br>
 * Contract: {@link org.jhotdraw.gui.EditableComponent}, {@code JTextComponent}.<br>
 * Client: {@link org.jhotdraw.action.edit.AbstractSelectionAction},
 * {@link org.jhotdraw.action.edit.DeleteAction},
 * {@link org.jhotdraw.action.edit.DuplicateAction},
 * {@link org.jhotdraw.action.edit.SelectAllAction},
 * {@link org.jhotdraw.action.edit.ClearSelectionAction}.
 * <hr>
 *
 * @author Werner Randelshofer.
 * @version $Id$
 */
public class DuplicateAction extends AbstractSelectionAction {

    private static final long serialVersionUID = 1L;
    public static final String ID = "edit.duplicate";

    /**
     * Creates a new instance which acts on the currently focused component.
     */
    public DuplicateAction() {
        this(null);
    }

    /**
     * Creates a new instance which acts on the specified component.
     *
     * @param target The target of the action. Specify null for the currently
     *               focused component.
     */
    public DuplicateAction(JComponent target) {
        super(target);
        ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.action.Labels");
        labels.configureAction(this, ID);
    }


    @Override
    public void actionPerformed(ActionEvent evt) {
        // Change Variable name
        JComponent targetComponent = getTargetComponent();

        // Return early if there is no valid or enabled component
        if (targetComponent == null || !targetComponent.isEnabled()) {
            return;
        }

        // Perform duplication if the component is editable
        if (targetComponent instanceof EditableComponent) {
            ((EditableComponent) targetComponent).duplicate();
        } else {
            targetComponent.getToolkit().beep();
        }
    }
    private JComponent getTargetComponent() {
        // Check if 'target' is available
        if (target != null) {
            return target;
        }

        // Get the component that is currently in focus
        Component focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getPermanentFocusOwner();
        if (focusOwner instanceof JComponent) {
            return (JComponent) focusOwner;
        }

        // Returnér null, if no valid component is found
        return null;
    }
}
