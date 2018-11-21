package com.risk.model;

import javax.swing.table.AbstractTableModel;

/**
 * Table model to display the results of the tournament
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 *
 */
public class ResultsTableModel extends AbstractTableModel{
	
	@SuppressWarnings("javadoc")
	private static final long serialVersionUID = -2946310373387127208L;
	@SuppressWarnings("javadoc")
	private String[] columnNames;
	@SuppressWarnings("javadoc")
	private String[][] data;
	
	/**
	 * Constructor
	 * 
	 * @param columnNames
	 * @param data
	 */
	public ResultsTableModel(String[] columnNames, String[][] data) {
		this.columnNames = columnNames;
		this.data = data;
	}
	

	public int getColumnCount() {
      return columnNames.length;
    }

    public int getRowCount() {
      return data.length;
    }

    public String getColumnName(int col) {
      return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
      return data[row][col];
    }

}
