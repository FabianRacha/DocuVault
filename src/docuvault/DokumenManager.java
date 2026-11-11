package docuvault;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DokumenManager {

    private final ArrayList<Dokumen> daftar = new ArrayList<>();
    private int idBerikutnya = 1;

    public Dokumen tambah(String nama, String nomor, LocalDate tanggal,
                          String kategori, String deskripsi, String filePdf) {
        Dokumen d = new Dokumen(idBerikutnya++, nama, nomor, tanggal, kategori, deskripsi, filePdf);
        daftar.add(d);
        return d;
    }

    public List<Dokumen> lihatSemua() {
        return new ArrayList<>(daftar);
    }

    public Dokumen cariById(int id) {
        for (Dokumen d : daftar) {
            if (d.getId() == id) return d;
        }
        return null;
    }

    public boolean edit(int id, String nama, String nomor, LocalDate tanggal,
                        String kategori, String deskripsi, String filePdf) {
        Dokumen d = cariById(id);
        if (d == null) return false;
        d.setNamaDokumen(nama);
        d.setNomorDokumen(nomor);
        d.setTanggal(tanggal);
        d.setKategori(kategori);
        d.setDeskripsi(deskripsi);
        d.setFilePdf(filePdf);
        return true;
    }

    public boolean hapus(int id) {
        Dokumen d = cariById(id);
        return d != null && daftar.remove(d);
    }

    public String baca(int id) {
        Dokumen d = cariById(id);
        if (d == null) return "Dokumen tidak ditemukan.";
        File f = new File(d.getFilePdf());
        if (!f.exists()) return "File PDF tidak ditemukan di: " + d.getFilePdf();
        try {
            java.awt.Desktop.getDesktop().open(f);
            return "Membuka " + d.getFilePdf() + " ...";
        } catch (Exception e) {
            return "Gagal membuka PDF: " + e.getMessage();
        }
    }

    public List<Dokumen> cari(String kataKunci) {
        List<Dokumen> hasil = new ArrayList<>();
        String k = kataKunci.toLowerCase();
        for (Dokumen d : daftar) {
            if (d.getNamaDokumen().toLowerCase().contains(k)
                    || d.getNomorDokumen().toLowerCase().contains(k)) {
                hasil.add(d);
            }
        }
        return hasil;
    }

    public List<Dokumen> filterKategori(String kategori) {
        List<Dokumen> hasil = new ArrayList<>();
        for (Dokumen d : daftar) {
            if (d.getKategori().equalsIgnoreCase(kategori)) {
                hasil.add(d);
            }
        }
        return hasil;
    }
}
