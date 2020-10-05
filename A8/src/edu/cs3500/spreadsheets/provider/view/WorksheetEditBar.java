package edu.cs3500.spreadsheets.provider.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 * A toolbar for editing a worksheet.
 */
public class WorksheetEditBar extends JToolBar {
  private JTextField coordDisplay;
  private JButton confirmButton;
  private JButton rejectButton;
  private JTextField editField;

  /**
   * A public constructor for the WorksheetEditBar class.
   * @param bg background color of the toolbar
   */
  public WorksheetEditBar(Color bg) {
    super();
    setBackground(bg);
    setFloatable(false);

    coordDisplay = new JTextField();
    coordDisplay.setBackground(new Color(71, 71, 71));
    coordDisplay.setForeground(Color.WHITE);
    coordDisplay.setMaximumSize(new Dimension(50, coordDisplay.getPreferredSize().height));
    coordDisplay.setColumns(7);
    coordDisplay.setEditable(false);
    add(coordDisplay);

    addSeparator();

    confirmButton = new JButton("✓");
    confirmButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,
            (int) Math.floor(confirmButton.getFont().getSize() * 1.4)));
    confirmButton.setBackground(bg);
    confirmButton.setForeground(Color.WHITE);
    confirmButton.setOpaque(true);
    confirmButton.setBorder(BorderFactory.createEmptyBorder(1, 7, 2, 7));
    confirmButton.setAlignmentY(JLabel.CENTER_ALIGNMENT);
    confirmButton.setRolloverEnabled(true);
    add(confirmButton);

    rejectButton = new JButton("\uD800\uDD02");
    rejectButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,
            (int) Math.floor(rejectButton.getFont().getSize() * 1.4)));
    rejectButton.setBackground(bg);
    rejectButton.setForeground(Color.WHITE);
    rejectButton.setOpaque(true);
    rejectButton.setBorder(BorderFactory.createEmptyBorder(1, 7, 2, 7));
    rejectButton.setAlignmentY(JLabel.CENTER_ALIGNMENT);
    rejectButton.setRolloverEnabled(true);
    add(rejectButton);

    editField = new JTextField();
    editField.setBackground(new Color(71, 71, 71));
    editField.setForeground(Color.WHITE);
    add(editField);
  }

  /**
   * Gets the coord display in the edit bar.
   * @return the coord display
   */
  public JTextField getCoordDisplay() {
    return coordDisplay;
  }

  /**
   * Gets the confirm cell button in the edit bar.
   * @return the confirm cell button
   */
  public JButton getConfirmButton() {
    return confirmButton;
  }

  /**
   * Gets the reject cell button in the edit bar.
   * @return the reject cell button
   */
  public JButton getRejectButton() {
    return rejectButton;
  }

  /**
   * Gets the edit field in the edit bar.
   * @return the edit field
   */
  public JTextField getEditField() {
    return editField;
  }
}
