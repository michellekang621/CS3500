//package edu.cs3500.spreadsheets.view;
//
//import java.awt.*;
//
//import javax.swing.*;
//
//import edu.cs3500.spreadsheets.model.Worksheet;
//
//public class ScrollPanel extends javax.swing.JPanel {
//
//  JPanel sp;
//  JScrollBar horizontal, vertical;
//
//  // for GridBagLayout
//  JPanel pane;
//
//  public ScrollPanel(Worksheet w) {
//    sp = new SpreadsheetPanel(w);
//
//    JScrollPane scroll = new JScrollPane(sp);
//
//    horizontal = scroll.getHorizontalScrollBar();
//    vertical = scroll.getVerticalScrollBar();
//
//    pane = new JPanel(new GridBagLayout());
//    GridBagConstraints forSpreadsheet = new GridBagConstraints();
//    forSpreadsheet.fill = GridBagConstraints.BOTH;
//    forSpreadsheet.insets = new Insets(0, 0,10, 10);
//    pane.add(sp, forSpreadsheet);
//
//    GridBagConstraints forVertical = new GridBagConstraints();
//    forVertical.fill = GridBagConstraints.BOTH;
//    forVertical.anchor = GridBagConstraints.PAGE_END;
//    pane.add(vertical, forVertical);
//
//    GridBagConstraints forHorizontal = new GridBagConstraints();
//    forHorizontal.fill = GridBagConstraints.BOTH;
//    forHorizontal.anchor = GridBagConstraints.LINE_END;
//    pane.add(vertical, forHorizontal);
//
//
//  }
//}
