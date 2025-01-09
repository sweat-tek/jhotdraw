package org.jhotdraw.action.edit;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import org.jhotdraw.datatransfer.ClipboardUtil;
import org.jhotdraw.util.ResourceBundleUtil;

/**
 * Cuts the selected region and places its contents into the system clipboard.
 * <p>
 * This action acts on the last {@link org.jhotdraw.gui.EditableComponent} /
 * {@code JTextComponent} which had the focus when the {@code ActionEvent}
 * was generated.
 * <p>
 * This action is called when the user selects the Cut item in the Edit
 * menu. The menu item is automatically created by the application.
 * <p>
 * If you want this behavior in your application, you have to create an action
 * with this ID and put it in your {@code ApplicationModel} in method
 * {@link org.jhotdraw.app.ApplicationModel#initApplication}.
 * </p>
 */
public class CutAction extends AbstractSelectionAction {

    private static final long serialVersionUID = 1L;
    public static final String ID = "edit.cut";

    /**
     * Creates a new instance which acts on the currently focused component.
     */
    public CutAction() {
        this(null);
    }

    /**
     * Creates a new instance which acts on the specified component.
     *
     * @param target The target of the action. Specify null for the currently
     * focused component.
     */
    public CutAction(JComponent target) {
        super(target);
        initializeAction();
    }

    private void initializeAction() {
        ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.action.Labels");
        labels.configureAction(this, ID);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JComponent component = getActiveComponent();
        if (isComponentEligibleForCut(component)) {
            performCutToClipboard(component);
        }
    }

    /**
     * Retrieves the target component, defaulting to the currently focused component if target is null.
     *
     * @return the active JComponent or null if none is focused.
     */
    private JComponent getActiveComponent() {
        if (target != null) {
            return target;
        }
        Component focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getPermanentFocusOwner();
        return (focusOwner instanceof JComponent) ? (JComponent) focusOwner : null;
    }

    /**
     * Checks if the component is eligible for a cut operation.
     *
     * @param component the component to check
     * @return true if component is not null and is enabled
     */
    private boolean isComponentEligibleForCut(JComponent component) {
        return component != null && component.isEnabled();
    }

    /**
     * Cuts the contents of the component to the system clipboard.
     *
     * @param component the component whose contents are to be cut
     */
    private void performCutToClipboard(JComponent component) {
        component.getTransferHandler().exportToClipboard(
                component,
                ClipboardUtil.getClipboard(),
                TransferHandler.MOVE
        );
    }
}

