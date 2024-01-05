 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tampilanCML;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import models.Kontraktor;
import models.Pembeli;
import models.Properti;
import utils.KontraktorDAO;
import utils.PembeliDAO;
import utils.PropertiDAO;

/**
 *
 * @author usama
 */
public class MenuAdmin {

    private Scanner scanner;
    private PropertiDAO pp1;
    private PembeliDAO pb1;
    private Pembeli p1;
    private Kontraktor k1;
    private KontraktorDAO kk1;
    private Properti g1 = new Properti();
    //private PropertiDAO g2 = new PropertiDAO();

    public MenuAdmin() {
        scanner = new Scanner(System.in);
        pp1 = new PropertiDAO();
        pb1 = new PembeliDAO();
        kk1 = new KontraktorDAO();
        k1 = new Kontraktor();
        p1 = new Pembeli();
    }

    public void tampilkanMenu() {
        int pilihan;
        do {
            System.out.println("Menu Admin:");
            System.out.println("1. Tambah Properti");
            System.out.println("2. Update Properti");
            System.out.println("3. Hapus Properti");
            System.out.println("4. Semua Informasi Properti");
            System.out.println("5. Tambah Akun Pembeli");
            System.out.println("6. Update Akun Pembeli");
            System.out.println("7. Hapus Akun Pembeli");
            System.out.println("8. Semua Informasi Akun Pembeli");
            System.out.println("9. Tambah Akun Kontraktor");
            System.out.println("10. Update Akun Kontraktor");
            System.out.println("11. Hapus Akun Kontraktor");
            System.out.println("12. Semua Informasi Akun Kontraktor");
            System.out.println("0. Keluar");
            System.out.print("Pilihan Anda: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    // Tambah Properti
                    tambahProperti();
                    break;
                case 2:
                    // Update Properti
                    updateProperti();
                    break;
                case 3:
                    // Hapus Properti
                    hapusProperti();
                    break;
                case 4:
                    // Semua Informasi Properti
                    semuaInformasiProperti();
                    break;
                case 5:
                    // Tambah Akun Pembeli
                    tambahAkunPembeli();
                    break;
                case 6:
                    // Update Akun Pembeli
                    updateAkunPembeli();
                    break;
                case 7:
                    // Hapus Akun Pembeli
                    hapusAkunPembeli();
                    break;
                case 8:
                    // Semua Informasi Akun Pembeli
                    semuaInformasiAkunPembeli();
                    break;
                case 9:
                    // Tambah Akun Kontraktor
                    tambahAkunKontraktor();
                    break;
                case 10:
                    // Update Akun Kontraktor
                    updateAkunKontraktor();
                    break;
                case 11:
                    // Hapus Akun Kontraktor
                    hapusAkunKontraktor();
                    break;
                case 12:
                    // Semua Informasi Akun Kontraktor
                    semuaInformasiAkunKontraktor();
                    break;
                case 0:
                    // Keluar dari Menu Admin
                    System.out.println("Terima kasih! Keluar dari Menu Admin.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
                    break;
            }
        } while (pilihan != 0);
    }

    // Implementasikan method-method untuk setiap pilihan menu di sini
    // contoh:
    private void tambahProperti() {
        // Logika untuk menambah properti
        System.out.println("Masukkan detail properti:");

        // Membersihkan newline di buffer setelah nextInt() atau nextDouble()
        scanner.nextLine();

        System.out.print("Nama: ");
        String nama = scanner.nextLine();

        System.out.print("Deskripsi: ");
        String deskripsi = scanner.nextLine();

        System.out.print("Lokasi: ");
        String lokasi = scanner.nextLine();

        System.out.print("Harga: ");
        double harga = scanner.nextDouble();

        System.out.print("Panjang Rumah: ");
        double panjangRumah = scanner.nextDouble();

        System.out.print("Lebar Rumah: ");
        double lebarRumah = scanner.nextDouble();

        double luasRumah = lebarRumah * panjangRumah;

        // Membersihkan newline di buffer setelah nextDouble()
        scanner.nextLine();

        //System.out.print("Ketersediaan: ");
        String ketersediaan = "Tersedia";

        // Mendapatkan waktu saat ini untuk properti yang ditambahkan
        Timestamp tanggalDaftar = new Timestamp(System.currentTimeMillis());

        double jumlahDiFavoritkan = 0; // Sesuaikan dengan kebutuhan Anda

        Properti p1 = new Properti(nama, deskripsi, lokasi, harga, panjangRumah, lebarRumah, luasRumah, jumlahDiFavoritkan, ketersediaan, tanggalDaftar);
        pp1.tambahProperti(p1);
    }

    private void updateProperti() {
        List<Properti> l1 = pp1.getSemuaProperti();
        for (Properti i : l1) {
            g1.displayProperti(i);
        }
        System.out.println("Pilih PropertiId yang ingin di Update:");
        int propertiIdToUpdate = scanner.nextInt();
        boolean diupdate = false;
        for (Properti properti : l1) {
            if (propertiIdToUpdate == properti.getPropertiId()) {
                scanner.nextLine(); // Membersihkan buffer
                System.out.println("Update Informasi Properti:");

                System.out.print("Nama (kosongkan jika tidak ingin mengubah): ");
                String nama = scanner.nextLine().trim();
                System.out.print("Deskripsi (kosongkan jika tidak ingin mengubah): ");
                String deskripsi = scanner.nextLine().trim();
                System.out.print("Lokasi (kosongkan jika tidak ingin mengubah): ");
                String lokasi = scanner.nextLine().trim();
                System.out.print("Harga (kosongkan jika tidak ingin mengubah): ");
                String harga = scanner.nextLine().trim();
                System.out.print("Panjang Rumah (kosongkan jika tidak ingin mengubah): ");
                String panjangRumah = scanner.nextLine().trim();
                System.out.print("Lebar Rumah (kosongkan jika tidak ingin mengubah): ");
                String lebarRumah = scanner.nextLine().trim();
                System.out.print("Id User (kosongkan jika tidak ingin mengubah): ");
                String idUser = scanner.nextLine().trim();
                System.out.print("Ketersediaan (kosongkan jika tidak ingin mengubah): ");
                String ketersediaan = scanner.nextLine().trim();

                // Lakukan pengecekan jika properti yang ingin diubah ditemukan
                if (nama.isEmpty() && deskripsi.isEmpty() && lokasi.isEmpty() && harga.isEmpty()
                        && panjangRumah.isEmpty() && lebarRumah.isEmpty() && idUser.isEmpty() && ketersediaan.isEmpty()) {
                    System.out.println("Tidak ada perubahan yang dimasukkan. Informasi tidak diperbarui.");
                } else {
                    // Set nilai properti hanya jika input tidak kosong
                    if (!nama.isEmpty()) {
                        properti.setNama(nama);
                    }
                    if (!deskripsi.isEmpty()) {
                        properti.setDeskripsi(deskripsi);
                    }
                    if (!lokasi.isEmpty()) {
                        properti.setLokasi(lokasi);
                    }
                    if (!harga.isEmpty()) {
                        properti.setHarga(Double.parseDouble(harga));
                    }
                    if (!panjangRumah.isEmpty()) {
                        properti.setPanjangRumah(Double.parseDouble(panjangRumah));
                    }
                    if (!lebarRumah.isEmpty()) {
                        properti.setLebarRumah(Double.parseDouble(lebarRumah));
                    }
                    if (!idUser.isEmpty()) {
                        properti.setIdUser(idUser);
                    }
                    if (!ketersediaan.isEmpty()) {
                        properti.setKetersediaan(ketersediaan);
                    }

                    // Lakukan pembaruan properti pada database
                    pp1.updateProperti(properti);
                    System.out.println("Informasi properti berhasil diperbarui.");
                }
                diupdate = true;
                break;
            }
        }
        if (!diupdate) {
            System.out.println("Properti Tidak Ditemukan");
        } else {
            System.out.println("Berhasil meng-Update Properti");
        }
    }

    private void hapusProperti() {
        List<Properti> l1 = pp1.getSemuaProperti();
        for (Properti i : l1) {
            g1.displayProperti(i);
        }
        System.out.println("Masukkan ID Properti yang akan dihapus:");
        int propertiIdToDelete = scanner.nextInt();
        boolean dihapus = false;

        for (Properti properti : l1) {
            if (propertiIdToDelete == properti.getPropertiId()) {
                pp1.hapusProperti(propertiIdToDelete); // Panggil metode hapusProperti dari PropertiDAO dengan ID yang sesuai
                System.out.println("Properti berhasil dihapus.");
                dihapus = true;
                break;
            }
        }

        if (!dihapus) {
            System.out.println("Properti tidak ditemukan.");
        }
    }

    private void semuaInformasiProperti() {
        // Logika untuk menampilkan semua informasi properti
        List<Properti> lP = pp1.getSemuaProperti();
                    for(Properti i : lP){
                        g1.displayProperti(i);
                    }
    }

    private void tambahAkunPembeli()  {
        scanner.nextLine(); // Membersihkan buffer

        System.out.println("Tambah Akun Pembeli:");
        System.out.print("Nama: ");
        String nama = scanner.nextLine().trim();
        System.out.print("Nomor HP: ");
        String noHP = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        Timestamp tanggalDaftar = new Timestamp(System.currentTimeMillis());
        int jumlahTerbeli = 0;
        String tipePengguna = "Pembeli";
        // Buat instance Pembeli baru dan panggil metode untuk menambah akun pembeli
        Pembeli p1 = new Pembeli(nama, noHP, email, password, tanggalDaftar, tipePengguna, jumlahTerbeli);
        pb1.tambahPembeli(p1);
    }

    private void updateAkunPembeli() {
        // Logika untuk mengupdate akun pembeli
        
    }

    private void hapusAkunPembeli() {
        // Logika untuk menghapus akun pembeli
    }

    private void semuaInformasiAkunPembeli() {
        // Logika untuk menampilkan semua informasi akun pembeli
    }

    private void tambahAkunKontraktor() {
        scanner.nextLine(); // Membersihkan buffer

        System.out.println("Tambah Akun Kontraktor:");
        System.out.print("Nama: ");
        String nama = scanner.nextLine().trim();
        System.out.print("Nomor HP: ");
        String noHP = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        System.out.print("Link: ");
        String link = scanner.nextLine().trim();

        Timestamp tanggalDaftar = new Timestamp(System.currentTimeMillis());
        String tipePengguna = "Kontraktor";
        // Buat instance Kontraktor baru dan panggil metode untuk menambah akun kontraktor
        Kontraktor k1 = new Kontraktor(nama, noHP, email, password, tanggalDaftar, link, tipePengguna);
        kk1.tambahKontraktor(k1);
    }

    private void updateAkunKontraktor() {
        // Logika untuk mengupdate akun kontraktor
    }

    private void hapusAkunKontraktor() {
        // Logika untuk menghapus akun kontraktor
    }

    private void semuaInformasiAkunKontraktor() {
        // Logika untuk menampilkan semua informasi akun kontraktor
    }

}
