package edu.cs3500.spreadsheets.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.cs3500.spreadsheets.model.CellAdapter;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetAdapter;
import edu.cs3500.spreadsheets.provider.view.Coord;
import edu.cs3500.spreadsheets.provider.view.Features;
import edu.cs3500.spreadsheets.provider.view.WorksheetView;

/**
 * An adapter of Provider's Controller interface to
 * make Provider's Controller work with our Controller.
 */
public class ControllerAdapter implements Features, Controller, ActionListener,
        CellEditorListener, DocumentListener, KeyListener {

  private edu.cs3500.spreadsheets.model.Worksheet ourWorksheet;
  private WorksheetAdapter theirWorksheet;
  private edu.cs3500.spreadsheets.provider.view.WorksheetView theirView;

  /**
   * Creates an instance of Controller adapter using our Model's worksheet and their
   * model's view.
   * @param ourWorksheet our model's worksheet
   * @param theirView provider's view
   */
  public ControllerAdapter(Worksheet ourWorksheet, WorksheetView theirView) {
    this.ourWorksheet = ourWorksheet;
    this.theirWorksheet = new WorksheetAdapter(ourWorksheet);
    this.theirView = theirView;

  }

  @Override
  public void confirmCell() {
    Coord c = theirView.getFirstSelected();
    theirWorksheet.setCell(c, new CellAdapter(ourWorksheet, theirView.getEditText(), c));
  }

  @Override
  public void rejectCell() {
    String s = theirWorksheet.getCellAt(theirView.getFirstSelected()).getRawContents();
    theirView.setEditText(s);
  }

  @Override
  public void newFile() {
    // not implemented
  }

  @Override
  public void save() {
    // not implemented
  }

  @Override
  public void open() {
    // not implemented
  }

  @Override
  public void deleteCells() {
    // not implemented
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    if (command.equals("Confirm")) {
      this.confirmCell();
    } else if (command.equals("Reject Button")) {
      this.rejectCell();
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // not implemented
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // not implemented
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // not implemented
  }

  @Override
  public void editingStopped(ChangeEvent e) {
    // not implemented
  }

  @Override
  public void editingCanceled(ChangeEvent e) {
    // not implemented
  }

  @Override
  public void insertUpdate(DocumentEvent e) {
    // not implemented
  }

  @Override
  public void removeUpdate(DocumentEvent e) {
    // not implemented
  }

  @Override
  public void changedUpdate(DocumentEvent e) {
    // not implemented
  }

  @Override
  public void goController() {
    theirView.setListeners(this, this,
            this, this);
  }
}
