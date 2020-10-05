package edu.cs3500.spreadsheets.controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.view.WorksheetView;

/**
 * Controller for the Worksheet.
 */
public class SpreadsheetController implements Controller, MouseListener, ActionListener {

  private Worksheet model;
  private WorksheetView view;

  /**
   * Takes in user input and delegates the commands appropriate to the given model and view.
   * @param model the model that is being manipulated
   * @param view the view that displays the model
   */
  public SpreadsheetController(Worksheet model, WorksheetView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void goController() {
    this.view.setCellSelectionListener(this);
    this.view.setInputListener(this);
  }

  /**
   * Updates worksheet based on given MouseEvent.
   * @param e the MouseEvent.
   */
  public void mouseClicked(MouseEvent e) {
    Point p = e.getPoint();
    view.selectCell(p);
    view.refresh();
  }

  /**
   * Updates worksheet based on given ActionEvent.
   * @param e the ActionEvent.
   */
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    Rectangle r = view.getSelected();
    Coord c = new Coord((r.x / view.CELL_WIDTH) + 1, (r.y / view.CELL_HEIGHT) + 1);
    if (command.equals("accept")) {
      String s = view.getInputBoxText();
      model.updateCell(c, s);
    } else if (command.equals("decline")) {
      view.setInputField();
    } else if (command.equals("addRow")) {
      view.addRow();
    } else if (command.equals("addColumn")) {
      view.addCol();
    }
    view.refresh();
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // do nothing.
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // do nothing.
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // do nothing.
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // do nothing.
  }
}
