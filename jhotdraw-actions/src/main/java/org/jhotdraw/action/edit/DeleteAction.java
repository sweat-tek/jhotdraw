/*
 * @(#)DeleteAction.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.action.edit;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import javax.swing.text.*;
import org.jhotdraw.api.gui.EditableComponent;
import org.jhotdraw.beans.WeakPropertyChangeListener;
import org.jhotdraw.util.*;

/**
 * Deletes the region at (or after) the caret position.
 * <p>
 * This action acts on the last {@link org.jhotdraw.gui.EditableComponent} /
 * {@code JTextComponent} which had the focus when the {@code ActionEvent}
 * was generated.
 * <p>
 * This action is called when the user selects the Delete item in the Edit
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
 * @author Werner Randelshofer
 * @version $Id$
 */
public class DeleteAction extends TextAction {

    private static final long serialVersionUID = 1L;
    /**
     * The ID for this action.
     */
    public static final String ID = "edit.delete";
    /**
     * The target of the action or null if the action acts on the currently
     * focused component.
     */
    private JComponent target;
    /**
     * This variable keeps a strong reference on the property change listener.
     */
    private PropertyChangeListener propertyHandler;

    /**
     * Creates a new instance which acts on the currently focused component.
     */
    public DeleteAction() {
        this(null, ID);
    }

    /**
     * Creates a new instance which acts on the specified component.
     *
     * @param target The target of the action. Specify null for the currently
     *               focused component.
     */
    public DeleteAction(JComponent target) {
        this(target, ID);
    }

    /**
     * Creates a new instance which acts on the specified component.
     *
     * @param target The target of the action. Specify null for the currently
     *               focused component.
     */
    protected DeleteAction(JComponent target, String id) {
        super(id);
        this.target = target;
        if (target != null) {
            // Register with a weak reference on the JComponent.
            propertyHandler = new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if ("enabled".equals(evt.getPropertyName())) {
                        setEnabled((Boolean) evt.getNewValue());
                    }
                }
            };
            target.addPropertyChangeListener(new WeakPropertyChangeListener(propertyHandler));
        }
        ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.action.Labels");
        labels.configureAction(this, ID);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JComponent c = target;
        if (c == null && (KeyboardFocusManager.getCurrentKeyboardFocusManager().
                getPermanentFocusOwner() instanceof JComponent)) {
            c = (JComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().
                    getPermanentFocusOwner();
        }
        if (c != null && c.isEnabled()) {
            if (c instanceof EditableComponent) {
                ((EditableComponent) c).delete();
            } else {
                deleteNextChar(evt);
            }
        }
    }


    /**
     * This method was copied from
     * DefaultEditorKit.DeleteNextCharAction.actionPerformed(ActionEvent).
     */
    public void deleteNextChar(ActionEvent e) {
        JTextComponent textComponent = getTextComponent(e);
        if (textComponent == null || !textComponent.isEditable()) {
            // Emit beep if the component is null or not editable
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        try {
            javax.swing.text.Document doc = textComponent.getDocument();
            Caret caret = textComponent.getCaret();

            // If there is a selection, delete the selected text
            if (hasSelection(caret)) {
                deleteSelectedText(doc, caret);
            }
            // If no selection, check if we can delete the next character
            else if (canDeleteNextCharacter(caret, doc)) {
                deleteNextCharacter(doc, caret.getDot());
            }
            // Emit beep if no deletion occurred
            else {
                Toolkit.getDefaultToolkit().beep();
            }
        } catch (BadLocationException bl) {
            // Ignoring exception due to invalid document position
        }
    }

    // Helper methods remain the same
    private boolean hasSelection(Caret caret) {
        return caret.getDot() != caret.getMark(); // True if there is a selection
    }

    private void deleteSelectedText(javax.swing.text.Document doc, Caret caret) throws BadLocationException {
        int dot = caret.getDot();
        int mark = caret.getMark();
        doc.remove(Math.min(dot, mark), Math.abs(dot - mark)); // Remove selected text
    }

    private boolean canDeleteNextCharacter(Caret caret, javax.swing.text.Document doc) {
        return caret.getDot() < doc.getLength(); // True if caret is not at the end
    }

    private void deleteNextCharacter(javax.swing.text.Document doc, int dot) throws BadLocationException {
        doc.remove(dot, 1); // Remove one character at the caret position
    }
}
