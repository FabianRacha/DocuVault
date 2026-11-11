package docuvault;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner in = new Scanner(System.in);
    private static final DokumenManager manager = new DokumenManager();

    public static void main(String[] args) {
        int pilih;
        do {
            System.out.println("\n===== DOCUVAULT =====");
            System.out.println("1. Tambah Dokumen");
            System.out.println("2. Lihat Daftar Dokumen");
            System.out.println("3. Edit Dokumen");
            System.out.println("4. Hapus Dokumen");
            System.out.println("5. Baca Dokumen (PDF Viewer)");
            System.out.println("6. Cari Dokumen");
            System.out.println("7. Filter Berdasarkan Kategori");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilih = bacaInt();

            try {
                switch (pilih) {
                    case 1: tambah(); break;
                    case 2: tampil(manager.lihatSemua()); break;
                    case 3: edit(); break;
                    case 4: hapus(); break;
                    case 5: baca(); break;
                    case 6: cari(); break;
                    case 7: filter(); break;
                    case 0: System.out.println("Terima kasih."); break;
                    default: System.out.println("Menu tidak valid!");
                }
            } catch (IllegalArgumentException e) {
                // pesan validasi dari setter
                System.out.println("GAGAL: " + e.getMessage());
            }
        } while (pilih != 0);
    }

    private static void tambah() {
        System.out.println("--- Tambah Dokumen ---");
        String nama = input("Nama dokumen   : ");
        String nomor = input("Nomor dokumen  : ");
        LocalDate tgl = bacaTanggal();
        String kategori = input("Kategori (Legal/Pendidikan/Keuangan/Identitas): ");
        String desk = input("Deskripsi      : ");
        String file = input("Path file PDF  : ");
        Dokumen d = manager.tambah(nama, nomor, tgl, kategori, desk, file);
        System.out.println("Berhasil ditambahkan dengan ID " + d.getId());
    }

    private static void tampil(List<Dokumen> list) {
        if (list.isEmpty()) {
            System.out.println("(Tidak ada data)");
            return;
        }
        for (Dokumen d : list) System.out.println(d);
    }

    private static void edit() {
        System.out.print("ID dokumen yang diedit: ");
        int id = bacaInt();
        Dokumen lama = manager.cariById(id);
        if (lama == null) {
            System.out.println("Dokumen tidak ditemukan.");
            return;
        }
        System.out.println("Data lama:\n" + lama);
        String nama = input("Nama baru       : ");
        String nomor = input("Nomor baru      : ");
        LocalDate tgl = bacaTanggal();
        String kategori = input("Kategori baru   : ");
        String desk = input("Deskripsi baru   : ");
        String file = input("Path PDF baru    : ");
        manager.edit(id, nama, nomor, tgl, kategori, desk, file);
        System.out.println("Berhasil diperbarui.");
    }

    private static void hapus() {
        System.out.print("ID dokumen yang dihapus: ");
        int id = bacaInt();
        String yakin = input("Yakin hapus? (y/n): ");
        if (yakin.equalsIgnoreCase("y")) {
            System.out.println(manager.hapus(id) ? "Berhasil dihapus." : "Dokumen tidak ditemukan.");
        } else {
            System.out.println("Dibatalkan.");
        }
    }

    private static void baca() {
        System.out.print("ID dokumen yang dibaca: ");
        System.out.println(manager.baca(bacaInt()));
    }

    private static void cari() {
        tampil(manager.cari(input("Kata kunci (nama/nomor): ")));
    }

    private static void filter() {
        tampil(manager.filterKategori(input("Kategori: ")));
    }

    // ===== Helper input =====
    private static String input(String label) {
        System.out.print(label);
        return in.nextLine();
    }

    private static int bacaInt() {
        try {
            return Integer.parseInt(in.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static LocalDate bacaTanggal() {
        while (true) {
            try {
                return LocalDate.parse(input("Tanggal (yyyy-MM-dd): ").trim());
            } catch (DateTimeParseException e) {
                System.out.println("Format tanggal salah, contoh: 2026-10-19");
            }
        }
    }
}
