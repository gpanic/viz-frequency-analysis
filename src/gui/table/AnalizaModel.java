package gui.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import fa.Frekvenca;

/**
 * @author Gregor Panič
 *
 */
public class AnalizaModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	private String[] names={"Črka","Frekvenca"};
	private ArrayList<Frekvenca> tableData=new ArrayList<Frekvenca>();
	
	@Override
	public String getColumnName(int column) {
		return names[column];
	}

	@Override
	public int getColumnCount() {
		return names.length;
	}

	@Override
	public int getRowCount() {
		return tableData.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		if(col==1) {
			return tableData.get(row).getCrka();
		} else {
			return tableData.get(row).getStevilo();
		}
	}
	
	public void setTableData(ArrayList<Frekvenca> data) {
		tableData=data;
	}

}
