/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tampilanCML;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;
import models.Donasi;
import models.Kontraktor;
import utils.DonasiDAO;
import utils.KontraktorDAO;

/**
 *
 * @author usama
 */
public class MenuKontraktor {

    private Scanner scanner;
    private Kontraktor k1;
    private KontraktorDAO kk1;
    private Donasi d1;
    private DonasiDAO dd1;

    public MenuKontraktor(Kontraktor kontraktor) {
        k1 = new Kontraktor();
        kk1 = new KontraktorDAO();
        d1 = new Donasi();
        dd1 = new DonasiDAO();
        scanner = new Scanner(System.in);
    }

    public void tampilkanMenuKontraktor(String idUser) throws SQLException {
        int pilihan;
        do {
            k1 = kk1.infoAkun(idUser);

            if (k1 != null) {
                k1.displayInfo(k1);
                System.out.println("Menu Kontraktor:");
                System.out.println("1. Update Informasi Kontraktor");
                System.out.println("2. Hapus Pengguna Kontraktor");
                System.out.println("3. Tambah Donasi");
                System.out.println("4. Informasi Donasi");
                System.out.println("5. Logout");
                System.out.println("0. Keluar");
                System.out.print("Pilihan Anda: ");
                pilihan = scanner.nextInt();

                switch (pilihan) {
                    case 1:
                        updateInformasiKontraktor(idUser);
                        break;
                    case 2:
                        hapusPenggunaKontraktor(idUser);
                        break;
                    case 3:
                        tambahDonasi(idUser);
                        break;
                    case 4:
                        totalDonasi(idUser);
                        break;
                    case 5:
                        System.out.println("Anda berhasil logout.");
                        break;
                    case 0:
                        System.out.println("Keluar dari Menu Kontraktor.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
                        break;
                }
            } else {
                System.out.println("Akun tidak ditemukan.");
                pilihan = 0; // Keluar dari loop jika akun tidak ditemukan}
            }
        } while (pilihan != 0);
    }

    private void updateInformasiKontraktor(String idUser) throws SQLException  {
        scanner.nextLine(); // Membersihkan buffer
        System.out.println("Update Informasi Kontraktor:");

        // Meminta pengguna untuk memasukkan informasi baru
        System.out.print("Nama (kosongkan jika tidak ingin mengubah): ");
        String nama = scanner.nextLine().trim();
        System.out.print("Nomor HP (kosongkan jika tidak ingin mengubah): ");
        String noHP = scanner.nextLine().trim();
        System.out.print("Email (kosongkan jika tidak ingin mengubah): ");
        String email = scanner.nextLine().trim();
        System.out.print("Password (kosongkan jika tidak ingin mengubah): ");
        String password = scanner.nextLine().trim();
        System.out.print("Link (kosongkan jika tidak ingin mengubah): ");
        String link = scanner.nextLine().trim();

        k1 = kk1.infoAkun(idUser);
        //pp1.updatePembeli(p1);
        boolean adaPerubahan = false; // Variabel untuk menandai apakah ada perubahan

        // Memeriksa setiap informasi, jika tidak kosong, maka lakukan pembaruan
        if (!nama.isEmpty()) {
            k1.setNama(nama);
            adaPerubahan = true;
        }
        if (!noHP.isEmpty()) {
            k1.setNoHP(noHP);
            adaPerubahan = true;
        }
        if (!email.isEmpty()) {
            k1.setEmail(email);
            adaPerubahan = true;
        }
        if (!password.isEmpty()) {
            k1.setPassword(password);
            adaPerubahan = true;
        }
        if (!link.isEmpty()) {
            k1.setLink(link);
            adaPerubahan = true;
        }

        // Jika ada perubahan, lakukan pembaruan pada database
        if (adaPerubahan) {
            System.out.println(k1.getEmail());
            System.out.println(k1.getIdUser());
            System.out.println(k1.getNama());
            System.out.println(k1.getNoHP());
            System.out.println(k1.getPassword());
            kk1.updateKontraktor(k1);
            System.out.println("Informasi pembeli berhasil diperbarui.");
        } else {
            System.out.println("Tidak ada perubahan yang dimasukkan. Informasi tidak diperbarui.");
        }
    }

    private void hapusPenggunaKontraktor(String idUser) {
        System.out.println("Apakah Anda yakin ingin menghapus akun Anda?");
        System.out.println("1. Iya");
        System.out.println("0. Tidak");
        System.out.print("Pilihan Anda: ");
        int pilihan = scanner.nextInt();

        switch (pilihan) {
            case 1:
                kk1.hapusKontraktor(idUser);
                System.out.println("Akun telah dihapus.");
                break;
            case 0:
                System.out.println("Penghapusan akun dibatalkan.");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }
    }

    private void tambahDonasi(String idUser) {
        // Logika untuk menambah donasi
        // Implementasi kode untuk menambah donasi di sini
        System.out.print("Masukkan Nominal: ");
        int nominal = scanner.nextInt();
        Timestamp waktuDonasi = new Timestamp(System.currentTimeMillis());
        d1 = new Donasi(nominal,waktuDonasi,idUser);
        dd1.tambahDonasi(d1);
    }

    private void totalDonasi(String idUser) {
        System.out.println("Informasi Donasi: ");
        dd1.totalDonasiByIdUser(idUser);
        dd1.banyakDonasiByIdUser(idUser);
    }
}
