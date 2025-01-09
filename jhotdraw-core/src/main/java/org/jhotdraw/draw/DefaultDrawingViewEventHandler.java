package org.jhotdraw.draw;

import org.jhotdraw.draw.event.*;

import java.awt.event.FocusEvent;

import static org.jhotdraw.draw.AttributeKeys.CANVAS_HEIGHT;
import static org.jhotdraw.draw.AttributeKeys.CANVAS_WIDTH;

public class DefaultDrawingViewEventHandler implements IDrawingViewEventHandler  {
    private DefaultDrawingView view;

    protected DefaultDrawingViewEventHandler(DefaultDrawingView drawingView) {
        this.view = drawingView;
    }

    @Override
    public void figureAdded(CompositeFigureEvent evt) {
        if (view.getDrawing().getChildCount() == 1 && view.getEmptyDrawingMessage() != null) {
            view.repaint();
        } else {
            view.repaintDrawingArea(evt.getInvalidatedArea());
        }
        view.invalidateDimension();
    }

    @Override
    public void figureRemoved(CompositeFigureEvent evt) {
        if (view.getDrawing().getChildCount() == 0 && view.getEmptyDrawingMessage() != null) {
            view.repaint();
        } else {
            view.repaintDrawingArea(evt.getInvalidatedArea());
        }
        view.removeFromSelection(evt.getChildFigure());
        view.invalidateDimension();
    }

    @Override
    public void areaInvalidated(FigureEvent evt) {
        view.repaintDrawingArea(evt.getInvalidatedArea());
        view.invalidateDimension();
    }

    @Override
    public void areaInvalidated(HandleEvent evt) {
        view.repaint(evt.getInvalidatedArea());
        view.invalidateDimension();
    }

    @Override
    public void handleRequestSecondaryHandles(HandleEvent e) {
        view.handleSecondaryHandlesRequest(e);
    }

    @Override
    public void focusGained(FocusEvent e) {
        view.setFocusGained();
    }

    @Override
    public void focusLost(FocusEvent e) {
        //   repaintHandles();
    }

    @Override
    public void handleRequestRemove(HandleEvent e) {
        view.selectionHandles.remove(e.getHandle());
        e.getHandle().dispose();
        view.invalidateHandles();
        view.repaint(e.getInvalidatedArea());
    }

    @Override
    public void attributeChanged(FigureEvent e) {
        if (e.getSource() == view.getDrawing()) {
            AttributeKey<?> a = e.getAttribute();
            if (a.equals(CANVAS_HEIGHT) || a.equals(CANVAS_WIDTH)) {
                view.validateViewTranslation();
                view.repaint(); // must repaint everything
            }
            if (e.getInvalidatedArea() != null) {
                view.repaintDrawingArea(e.getInvalidatedArea());
            } else {
                view.repaintDrawingArea(view.viewToDrawing(view.getCanvasViewBounds()));
            }
        } else {
            if (e.getInvalidatedArea() != null) {
                view.repaintDrawingArea(e.getInvalidatedArea());
            }
        }
    }

    @Override
    public void figureHandlesChanged(FigureEvent e) {
    }

    @Override
    public void figureChanged(FigureEvent e) {
        view.repaintDrawingArea(e.getInvalidatedArea());
    }

    @Override
    public void figureAdded(FigureEvent e) {
    }

    @Override
    public void figureRemoved(FigureEvent e) {
    }

    @Override
    public void figureRequestRemove(FigureEvent e) {
    }
}
