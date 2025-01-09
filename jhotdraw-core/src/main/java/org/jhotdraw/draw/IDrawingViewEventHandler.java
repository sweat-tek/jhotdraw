package org.jhotdraw.draw;

import org.jhotdraw.draw.event.CompositeFigureListener;
import org.jhotdraw.draw.event.FigureListener;
import org.jhotdraw.draw.event.HandleEvent;
import org.jhotdraw.draw.event.HandleListener;

import java.awt.event.FocusListener;

public interface IDrawingViewEventHandler extends FigureListener, CompositeFigureListener, HandleListener, FocusListener {
    void handleRequestSecondaryHandles(HandleEvent e);

    void handleRequestRemove(HandleEvent e);
}
