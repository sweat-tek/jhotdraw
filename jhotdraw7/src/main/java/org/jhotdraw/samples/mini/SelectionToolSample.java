/*
 * @(#)SelectionToolSample.java  1.0 January 11, 2007
 *
 * Copyright (c) 1996-2006 by the original authors of JHotDraw
 * and all its contributors ("JHotDraw.org")
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * JHotDraw.org ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * JHotDraw.org.
 */

package org.jhotdraw.samples.mini;

import java.awt.geom.*; 
import javax.swing.*; 
import org.jhotdraw.draw.*; 
import org.jhotdraw.geom.*; 

/**
 * SelectionToolSample demonstrates how the <code>SelectionTool</code> works.
 * <p>
 * Internally, the <code>SelectionTool</code> uses three smaller tools 
 * (named as 'Tracker') to fulfill its task. If the user presses the mouse 
 * button over an empty area of a drawing, the <code>SelectAreaTracker</code>
 * comes into action. If the user presses the mouse button over a figure, the
 * <code>DragTracker</code> comes into action. If the user presses the mouse
 * button over a handle, the <code>HandleTracker</code> comes into action. 
 * <p>
 * You need to edit the source code as marked below. 
 * <p>
 * With this program you can:
 * <ol>
 * <li>See how the <code>SelectionTool</code> interacts with a 
 * <code>LineFigure</code>.</li>
 * <li>See how the <code>SelectAreaTracker</code> interacts with a 
 * <code>LineFigure</code>.</li>
 * <li>See how the <code>DragTracker</code> interacts with a 
 * <code>LineFigure</code>.</li>
 * <li>See how the <code>HandleTracker</code> interacts with a 
 * <code>LineFigure</code>.</li>
 * </ol>
 * 
 * 
 * @author Pondus
 * @version 1.0 January 11, 2007 Created.
 */
public class SelectionToolSample {

        /**
     * Creates a new instance of SelectionToolSample
     */
        public SelectionToolSample() {
            LineFigure lf = new LineFigure();
            lf.setBounds(new Point2D.Double(40,40), new Point2D.Double(200,
40));

            // Add all figures to a drawing 
            Drawing drawing = new DefaultDrawing(); 

            drawing.add(lf);

            // Show the drawing 
            JFrame f = new JFrame("UltraMini"); 
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
            f.setSize(600,300); 

            DrawingView view = new DefaultDrawingView(); 
            view.setDrawing(drawing);

            f.getContentPane().add(view.getComponent()); 
            // set up the drawing editor
            DrawingEditor editor = new DefaultDrawingEditor();
            editor.add(view);
            
            
            // Activate the following line to see the SelectionTool in full
            // action.
            editor.setTool(new SelectionTool());

            // Activate the following line to only see the SelectAreaTracker in 
            // action.
            //editor.setTool(new SelectAreaTracker());
            
            // Activate the following line to only see the DragTracker in 
            // action.
            //editor.setTool(new DragTracker(lf));
            
            // Activate the following lines to only see the HandleTracker in 
            // action.
            //view.selectAll();
            //editor.setTool(new HandleTracker(view.findHandle(view.drawingToView(lf.getStartPoint()))));
            
            f.show(); 
        }
      public static void main(String[] args) { 
            SwingUtilities.invokeLater(new Runnable() { 
                public void run() { 
                    new SelectionToolSample(); 
            } 
        }); 
      }    
}