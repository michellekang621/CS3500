package edu.cs3500.spreadsheets.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;

/**
 * Represents a graphical view of a Worksheet.
 */
public class SpreadsheetView extends JFrame {

  private static int CELL_HEIGHT = 20;
  private static int CELL_WIDTH = 60;
  private static int ROW_LABEL_WIDTH_INITIAL = 20;

  /**
   * Constructs an instance of a graphical view of the given worksheet.
   * @param ws    the given worksheet to construct the view from
   */
  public SpreadsheetView(Worksheet ws) {
    super();

    ArrayList<Coord> allCoords;
    allCoords = ws.nonEmptyCoords();

    int maxRow = 0;
    int maxCol = 0;
    for (Coord c : allCoords) {
      if (c.row > maxRow) {
        maxRow = c.row;
      }
      if (c.col > maxCol) {
        maxCol = c.col;
      }
    }

    this.setTitle("Spreadsheet");
    this.setSize(maxCol * CELL_WIDTH, maxRow * CELL_HEIGHT);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // sets the layout for a spreadsheet
    this.setLayout(new BorderLayout());
    SpreadsheetPanel spreadsheetPanel = new SpreadsheetPanel(ws);
    ColumnPanel columnPanel = new ColumnPanel();
    RowPanel rowPanel = new RowPanel();

    int width = maxCol * CELL_WIDTH;
    int height = maxRow * CELL_HEIGHT;
    spreadsheetPanel.setPreferredSize(new Dimension(width, height));
    columnPanel.setPreferredSize(new Dimension(width, CELL_HEIGHT));
    rowPanel.setPreferredSize(new Dimension(ROW_LABEL_WIDTH_INITIAL, height));

    JScrollPane scrollPane = new JScrollPane(spreadsheetPanel);
    JScrollBar horizontal = scrollPane.getHorizontalScrollBar();
    JScrollBar vertical = scrollPane.getVerticalScrollBar();
    vertical.setValue(vertical.getMaximum());
    horizontal.setValue(horizontal.getMaximum());
    scrollPane.setColumnHeaderView(columnPanel);
    scrollPane.setRowHeaderView(rowPanel);

    this.add(scrollPane, BorderLayout.CENTER);

    this.pack(); //all contents are at or above preferred sizes

  }

  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(500, 500);
  }
}