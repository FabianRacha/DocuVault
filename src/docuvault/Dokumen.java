package docuvault;

import java.time.LocalDate;

public class Dokumen {

    private int id;
    private String namaDokumen;
    private String nomorDokumen;
    private LocalDate tanggal;
    private String kategori;
    private String deskripsi;
    private String filePdf;

    public Dokumen(int id, String namaDokumen, String nomorDokumen,
                   LocalDate tanggal, String kategori, String deskripsi, String filePdf) {
        this.id = id;
        setNamaDokumen(namaDokumen);
        setNomorDokumen(nomorDokumen);
        setTanggal(tanggal);
        setKategori(kategori);
        setDeskripsi(deskripsi);
        setFilePdf(filePdf);
    }

    public int getId()               { return id; }
    public String getNamaDokumen()   { return namaDokumen; }
    public String getNomorDokumen()  { return nomorDokumen; }
    public LocalDate getTanggal()    { return tanggal; }
    public String getKategori()      { return kategori; }
    public String getDeskripsi()     { return deskripsi; }
    public String getFilePdf()       { return filePdf; }

    public void setNamaDokumen(String namaDokumen) {
        if (namaDokumen == null || namaDokumen.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama dokumen tidak boleh kosong!");
        }
        this.namaDokumen = namaDokumen.trim();
    }

    public void setNomorDokumen(String nomorDokumen) {
        if (nomorDokumen == null || nomorDokumen.trim().isEmpty()) {
            throw new IllegalArgumentException("Nomor dokumen tidak boleh kosong!");
        }
        this.nomorDokumen = nomorDokumen.trim();
    }

    public void setTanggal(LocalDate tanggal) {
        if (tanggal == null) {
            throw new IllegalArgumentException("Tanggal tidak boleh kosong!");
        }
        this.tanggal = tanggal;
    }

    public void setKategori(String kategori) {
        if (kategori == null || kategori.trim().isEmpty()) {
            throw new IllegalArgumentException("Kategori tidak boleh kosong!");
        }
        this.kategori = kategori.trim();
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = (deskripsi == null) ? "" : deskripsi.trim();
    }

    public void setFilePdf(String filePdf) {
        if (filePdf == null || !filePdf.trim().toLowerCase().endsWith(".pdf")) {
            throw new IllegalArgumentException("Format file tidak sesuai, hanya .pdf yang diizinkan!");
        }
        this.filePdf = filePdf.trim();
    }

    @Override
    public String toString() {
        return String.format("[%d] %s | No: %s | Tgl: %s | Kategori: %s | File: %s%n     Deskripsi: %s",
                id, namaDokumen, nomorDokumen, tanggal, kategori, filePdf, deskripsi);
    }
}
