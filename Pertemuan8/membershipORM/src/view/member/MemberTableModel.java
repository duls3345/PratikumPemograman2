package view.member;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Member;

class MemberTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nama", "Jenis Member"};
    private List<Member> data;

    public MemberTableModel(List<Member> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Member rowItem = data.get(row);
        switch (col) {
            case 0:
                return rowItem.getNama(); // Kolom pertama: Nama
            case 1:
                return rowItem.getJenisMember().getNama(); // Kolom kedua: Jenis Member
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false; // Tabel tidak dapat diedit langsung
    }

    // Menambahkan data baru ke tabel
    public void add(Member value) {
        data.add(value); // Tambahkan data ke list
        fireTableRowsInserted(data.size() - 1, data.size() - 1); // Notify UI ada baris baru
    }

    // Memperbarui data di tabel
    @Override
    public void setValueAt(Object aValue, int row, int col) {
        Member rowItem = data.get(row);
        switch (col) {
            case 0: // Kolom pertama: Nama
                rowItem.setNama((String) aValue);
                break;
            case 1: // Kolom kedua: Jenis Member
                // Anda bisa menambahkan logika untuk memperbarui Jenis Member berdasarkan nama
                break;
        }
        fireTableCellUpdated(row, col); // Notify UI ada perubahan data
    }
}
