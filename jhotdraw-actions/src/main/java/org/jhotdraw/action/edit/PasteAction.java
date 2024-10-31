/*
 * @(#)PasteAction.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.action.edit;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import javax.swing.*;
import org.jhotdraw.datatransfer.ClipboardUtil;
import org.jhotdraw.util.*;

/**
 * Pastes the contents of the system clipboard at the caret position.
 * <p>
 * This action acts on the last {@link org.jhotdraw.gui.EditableComponent} /
 * {@code JTextComponent} which had the focus when the {@code ActionEvent}
 * was generated.
 * <p>
 * This action is called when the user selects the Paste item in the Edit
 * menu. The menu item is automatically created by the application.
 * <p>
 * If you want this behavior in your application, you have to create an action
 * with this ID and put it in your {@code ApplicationModel} in method
 * .
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class PasteAction extends AbstractSelectionAction {

    private static final long serialVersionUID = 1L;
    public static final String ID = "edit.paste";

    /**
     * Creates a new instance which acts on the currently focused component.
     */
    public PasteAction() {
        this(null);
    }

    /**
     * Creates a new instance which acts on the specified component.
     *
     * @param target The target of the action. Specify null for the currently
     * focused component.
     */
    public PasteAction(JComponent target) {
        super(target);
        ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.action.Labels");
        labels.configureAction(this, ID);
    }



    @Override
    protected void updateEnabled() {
        if (target != null) {
            setEnabled(target.isEnabled());
        }
    }@Override
    public void actionPerformed(ActionEvent evt) {
        JComponent focusedComponent = getTargetComponent();

        if (isComponentEligibleForTransfer(focusedComponent)) {
            Transferable clipboardContent = ClipboardUtil.getClipboard().getContents(focusedComponent);
            if (clipboardContent != null) {
                importDataToComponent(focusedComponent, clipboardContent);
            }
        }
    }

    private JComponent getTargetComponent() {
        if (target != null) {
            return target;
        }
        KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        if (focusManager.getPermanentFocusOwner() instanceof JComponent) {
            return (JComponent) focusManager.getPermanentFocusOwner();
        }

        return null;
    }
    private boolean isComponentEligibleForTransfer(JComponent component) {
        return component != null && component.isEnabled() && component.getTransferHandler() != null;
    }

    private void importDataToComponent(JComponent component, Transferable transferable) {
        component.getTransferHandler().importData(component, transferable);
    }

}
