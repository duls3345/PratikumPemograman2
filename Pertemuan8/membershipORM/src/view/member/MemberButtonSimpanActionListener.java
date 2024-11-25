package view.member;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import model.JenisMember;
import model.Member;
import dao.MemberDao;

public class MemberButtonSimpanActionListener implements ActionListener {
    private MemberFrame memberFrame;
    private MemberDao memberDao;
    private Member editingMember; // Menyimpan referensi jika sedang mengedit data

    public MemberButtonSimpanActionListener(MemberFrame memberFrame, MemberDao memberDao) {
        this.memberFrame = memberFrame;
        this.memberDao = memberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.memberFrame.getNama();
        if (nama.isEmpty()) {
            this.memberFrame.showAlert("Nama tidak boleh kosong");
            return; // Hentikan proses jika nama kosong
        }

        JenisMember jenisMember = this.memberFrame.getJenisMember();
        if (jenisMember == null) {
            this.memberFrame.showAlert("Jenis Member harus dipilih");
            return; // Hentikan proses jika jenis member belum dipilih
        }

        if (editingMember == null) { // Menambah data baru
            Member newMember = new Member();
            newMember.setId(UUID.randomUUID().toString()); // ID acak menggunakan UUID
            newMember.setNama(nama);
            newMember.setJenisMember(jenisMember);

            this.memberDao.insert(newMember); // Simpan ke database
            this.memberFrame.addMember(newMember); // Tambahkan ke tabel UI
        } else { // Mengedit data yang ada
            editingMember.setNama(nama);
            editingMember.setJenisMember(jenisMember);

            this.memberDao.update(editingMember); // Update di database
            this.memberFrame.updateMember(editingMember); // Perbarui di tabel UI
            editingMember = null; // Reset mode editing
        }

        this.memberFrame.clearForm(); // Bersihkan form setelah simpan
    }

    // Setter untuk mengatur data yang sedang diedit
    public void setEditingMember(Member member) {
        this.editingMember = member;
    }
}
