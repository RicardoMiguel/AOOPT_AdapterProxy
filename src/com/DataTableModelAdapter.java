package com;

import javax.swing.table.AbstractTableModel;

/**
 * Created by Ricardo on 15/04/2016.
 */
public class DataTableModelAdapter extends AbstractTableModel {

    private final static int INDEX = 0;

    private final static int COLUMN = 1;

    private final static int COLUMNS = 2;

    private Data data;

    public DataTableModelAdapter(Data data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return columnIndex == INDEX ? rowIndex + 1 : data.get(rowIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != INDEX;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.set(rowIndex, Integer.valueOf((String)aValue));
        fireTableStructureChanged();
    }
}
