package tampilanCML;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Pembeli;
import models.Properti;
import utils.PembeliDAO;
import utils.PropertiDAO;

public class MenuPembeli {

    private Scanner scanner;
    private Pembeli p1;
    private PembeliDAO pp1;
    private PropertiDAO g1;
    private Properti pro1;
    private List<Properti> tampung;

    public MenuPembeli(Pembeli pembeli) {
        p1 = new Pembeli();
        pp1 = new PembeliDAO();
        g1 = new PropertiDAO();
        scanner = new Scanner(System.in);
        tampung = new ArrayList<>();
        pro1 = new Properti();
    }

    public void tampilkanMenuPembeli(String idUser) throws SQLException {
//        if (idUser == null) {
//            System.out.println("Akun tidak ditemukan.");
//            return; // Keluar dari metode jika idUser null
//        }
        int pilihan;
        do {
            p1 = pp1.infoAkun(idUser);
            if (p1.getIdUser() != null) {
                tampung = g1.getSemuaProperti();
                System.out.println("Menu Pembeli:");
                System.out.println("1. Tampilkan Seluruh Properti Tersedia");
                System.out.println("2. Filter Lokasi/Ketersediaan Properti");
                System.out.println("3. Properti Ter-Favorit");
                System.out.println("4. Beli Properti");
                System.out.println("5. Informasi Akun");
                System.out.println("0. Logout");
                System.out.print("Pilihan Anda: ");
                pilihan = scanner.nextInt();

                switch (pilihan) {
                    case 1:
                        informasiProperti();
                        break;
                    case 2:
                        filterLokasiProperti();
                        break;
                    case 3:
                        propertiTerFavorit();
                        break;
                    case 4:
                        beliProperti(idUser);
                        break;
                    case 5:
                        tampilkanMenuInformasiPembeli(idUser);
                        break;
                    case 0:
                        System.out.println("Anda berhasil logout.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
                        break;
                }
            } else {
                System.out.println("Akun tidak ditemukan.");
                pilihan = 0; // Keluar dari loop jika akun tidak ditemukan
            }
        } while (pilihan != 0);
    }

    private void informasiProperti() {
        // Implementasi kode untuk menampilkan informasi properti di sini
        for (Properti i : tampung) {
            i.displayProperti(i);
        }
    }

    private void filterLokasiProperti() {
        // Implementasi kode untuk filter lokasi properti di sini
        String search = scanner.next();
        List<Properti> t1 = g1.searchByLoc(search);
        System.out.println(t1.size());
        for (Properti i : t1) {
            i.displayProperti(i);
        }
    }

    private void propertiTerFavorit() {
        // Implementasi kode untuk menampilkan properti ter-favorit di sini
    }

    private void beliProperti(String idUser) {
        // Implementasi kode untuk melakukan pembelian properti di sini
        int PropertiId = scanner.nextInt();
        List<Properti> t1 = g1.searchByLoc("Tersedia");
        //pro1 = 
        boolean dibeli = false;
        for (Properti i : t1) {
            if (PropertiId == i.getPropertiId()) {
                g1.purchase(pro1, PropertiId, idUser);
                dibeli = true;
                break;
            }
        }
        if (!dibeli) {
            System.out.println("Tidak Tersedia");
        } else {
            System.out.println("Berhasil Terbeli");
        }

    }

    private void tampilkanMenuInformasiPembeli(String idUser) throws SQLException {
        int pilihan;
        do {
            p1 = pp1.infoAkun(idUser);
            if (p1.getIdUser() != null) {
                p1.displayInfo(p1);
                System.out.println("Menu Informasi Pembeli:");
                System.out.println("1. Update Informasi Pembeli");
                System.out.println("2. Hapus Pembeli");
                System.out.println("3. List Properti Saya");
                System.out.println("4. List Properti Favorit Saya");
                System.out.println("0. Kembali ke Menu Pembeli");
                System.out.print("Pilihan Anda: ");
                pilihan = scanner.nextInt();

                switch (pilihan) {
                    case 1:
                        updateInformasiPembeli(idUser);
                        break;
                    case 2:
                        hapusPembeli(idUser);
                        break;
                    case 3:
                        listPropertiDimiliki(idUser);
                        break;
                    case 4:
                        listFavorit(idUser);
                        break;
                    case 0:
                        System.out.println("Kembali ke Menu Pembeli.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
                        break;
                }
            } else {
                System.out.println("Akun tidak ditemukan.");
                pilihan = 0; // Keluar dari loop jika akun tidak ditemukan
            }
        } while (pilihan != 0);
    }

    private void updateInformasiPembeli(String idUser) throws SQLException {
        scanner.nextLine(); // Membersihkan buffer
        System.out.println("Update Informasi Pembeli:");

        // Meminta pengguna untuk memasukkan informasi baru
        System.out.print("Nama (kosongkan jika tidak ingin mengubah): ");
        String nama = scanner.nextLine().trim();
        System.out.print("Nomor HP (kosongkan jika tidak ingin mengubah): ");
        String noHP = scanner.nextLine().trim();
        System.out.print("Email (kosongkan jika tidak ingin mengubah): ");
        String email = scanner.nextLine().trim();
        System.out.print("Password (kosongkan jika tidak ingin mengubah): ");
        String password = scanner.nextLine().trim();

        p1 = pp1.infoAkun(idUser);
        //pp1.updatePembeli(p1);
        boolean adaPerubahan = false; // Variabel untuk menandai apakah ada perubahan

        // Memeriksa setiap informasi, jika tidak kosong, maka lakukan pembaruan
        if (!nama.isEmpty()) {
            p1.setNama(nama);
            adaPerubahan = true;
        }
        if (!noHP.isEmpty()) {
            p1.setNoHP(noHP);
            adaPerubahan = true;
        }
        if (!email.isEmpty()) {
            p1.setEmail(email);
            adaPerubahan = true;
        }
        if (!password.isEmpty()) {
            p1.setPassword(password);
            adaPerubahan = true;
        }

        // Jika ada perubahan, lakukan pembaruan pada database
        if (adaPerubahan) {
//            System.out.println(p1.getEmail());
//            System.out.println(p1.getIdUser());
//            System.out.println(p1.getNama());
//            System.out.println(p1.getNoHP());
//            System.out.println(p1.getPassword());
            pp1.updatePembeli(p1);
            System.out.println("Informasi pembeli berhasil diperbarui.");
        } else {
            System.out.println("Tidak ada perubahan yang dimasukkan. Informasi tidak diperbarui.");
        }
    }

    private void hapusPembeli(String idUser) {
        System.out.println("Apakah Anda yakin ingin menghapus akun Anda?");
        System.out.println("1. Iya");
        System.out.println("0. Tidak");
        System.out.print("Pilihan Anda: ");
        int pilihan = scanner.nextInt();

        switch (pilihan) {
            case 1:
                p1 = pp1.hapusPembeli(idUser);
                System.out.println("Akun telah dihapus.");
                //kembaliKeMenuAwal(); // Memanggil fungsi untuk kembali ke MenuAwal
                break;
            case 0:
                System.out.println("Penghapusan akun dibatalkan.");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }
    }

    private void listPropertiDimiliki(String idUser) {
        // Implementasi kode untuk menampilkan daftar properti yang dimiliki di sini
        List<Properti> t2 = g1.userPemilik(idUser);
        for (Properti i : t2) {
            i.displayProperti(i);
        }
    }

    private void listFavorit(String idUser) {
        // Implementasi kode untuk menampilkan daftar properti yang dimiliki di sini

    }
}
