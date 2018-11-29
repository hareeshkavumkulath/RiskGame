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
	 * @param columnNames String Array Table Column Names
	 * @param data String two dimensional array for data
	 */
	public ResultsTableModel(String[] columnNames, String[][] data) {
		this.columnNames = columnNames;
		this.data = data;
	}
	
	/**
	 * Getter to get the table column
	 * 
	 * @return int number of column
	 */
	public int getColumnCount() {
      return columnNames.length;
    }

	/**
	 * Getter to get the table row
	 * 
	 * @return int number of row
	 */
    public int getRowCount() {
      return data.length;
    }

    /**
     * Getter to get the column name
     * 
     * @return String column name
     */
    public String getColumnName(int col) {
      return columnNames[col];
    }

    /**
     * Getter to get the value in the table
     * 
     * @return Object value
     */
    public Object getValueAt(int row, int col) {
      return data[row][col];
    }

}
