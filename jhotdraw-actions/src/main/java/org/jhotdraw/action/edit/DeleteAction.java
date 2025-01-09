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
        JComponent component = target;
        if (component == null && (KeyboardFocusManager.getCurrentKeyboardFocusManager().
                getPermanentFocusOwner() instanceof JComponent)) {
            component = (JComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().
                    getPermanentFocusOwner();
        }
        if (component != null && component.isEnabled()) {
            if (component instanceof EditableComponent) {
                ((EditableComponent) component).delete();
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

        if (shouldBeepInsteadOfDelete(textComponent)) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }

        try {
            deleteTextOrNextCharacter(textComponent);
        } catch (BadLocationException ignored) {
            // Exception ignored as it's unlikely to occur in practice
        }
    }
// Refactored helper methods
    private boolean shouldBeepInsteadOfDelete(JTextComponent textComponent) {
        return textComponent == null || !textComponent.isEditable();
    }

    private void deleteTextOrNextCharacter(JTextComponent textComponent) throws BadLocationException {
        Document doc = textComponent.getDocument();
        Caret caret = textComponent.getCaret();

        if (hasSelection(caret)) {
            deleteSelectedText(doc, caret);
        } else if (canDeleteNextCharacter(caret, doc)) {
            deleteNextCharacter(doc, caret.getDot());
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    private boolean hasSelection(Caret caret) {
        return caret.getDot() != caret.getMark();
    }

    private void deleteSelectedText(Document doc, Caret caret) throws BadLocationException {
        int dot = caret.getDot();
        int mark = caret.getMark();
        doc.remove(Math.min(dot, mark), Math.abs(dot - mark));
    }

    private boolean canDeleteNextCharacter(Caret caret, Document doc) {
        return caret.getDot() < doc.getLength();
    }

    private void deleteNextCharacter(Document doc, int dot) throws BadLocationException {
        doc.remove(dot, 1);
    }}
