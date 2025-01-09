package org.jhotdraw.action.edit;

import org.jhotdraw.datatransfer.ClipboardUtil;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;

import java.awt.event.ActionEvent;
@ExtendWith(MockitoExtension.class)
class CopyActionTest {
    private CopyAction copyAction;
    @Mock
    private JComponent targetComponent;
    @Mock
    private TransferHandler mockTransferHandler;
    @Mock
    private KeyboardFocusManager mockFocusManager;

    @BeforeEach
    void setUP() {
        copyAction = new CopyAction(targetComponent);
        lenient().when(mockFocusManager.getPermanentFocusOwner()).thenReturn(targetComponent);
        KeyboardFocusManager.setCurrentKeyboardFocusManager(mockFocusManager);

        lenient().when(targetComponent.getTransferHandler()).thenReturn(mockTransferHandler);

    }

    @Test
    void TestAction() {
        assertNotNull(targetComponent);
    }

    @Test
    void ActionPerformedTest() {
        ActionEvent event = new ActionEvent(targetComponent, ActionEvent.ACTION_PERFORMED, "copy");
        // Perform copyAction
        copyAction.actionPerformed(event);

        verify(mockTransferHandler).exportToClipboard(
                eq(targetComponent),
                eq(ClipboardUtil.getClipboard()),
                eq(TransferHandler.COPY)
        );

        verify(mockTransferHandler, times(1)).exportToClipboard(
                eq(targetComponent),
                eq(ClipboardUtil.getClipboard()),
                eq(TransferHandler.COPY)
        );

    }
}
