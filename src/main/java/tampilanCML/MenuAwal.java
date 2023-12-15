package tampilanCML;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import models.Admin;
import models.Kontraktor;
import models.Pembeli;
import models.Properti;
import utils.KontraktorDAO;
import utils.PembeliDAO;
import utils.PropertiDAO;

public class MenuAwal {

    private static Kontraktor k1 = new Kontraktor();
    private static Pembeli p1 = new Pembeli();
    private static KontraktorDAO kk1 = new KontraktorDAO();
    private static PembeliDAO pp1 = new PembeliDAO();
    private static Scanner scanner = new Scanner(System.in);
    private static MenuKontraktor mk = new MenuKontraktor(k1);
    private static MenuPembeli mp = new MenuPembeli(p1);
    private static MenuAdmin madm = new MenuAdmin();
    private static Admin adm = new Admin();
    private static PropertiDAO g1 = new PropertiDAO();
    private static Properti pro1 = new Properti();

    public MenuAwal() {
        this.kk1 = new KontraktorDAO();
        this.pp1 = new PembeliDAO();
        this.mk = new MenuKontraktor(new Kontraktor());
        this.mp = new MenuPembeli(new Pembeli());    
        this.madm = new MenuAdmin();
    }

    public void tampilkanMenuUtama() throws SQLException {
        int choice;
        do {
            System.out.println("Menu Utama:");
            System.out.println("1. Informasi Properti");
            System.out.println("2. Sign Up");
            System.out.println("3. Sign In");
            System.out.println("0. Keluar");
            System.out.print("Pilihan Anda: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Anda memilih Informasi Properti.");
                    List<Properti> lP = g1.getSemuaProperti();
                    for(Properti i : lP){
                        pro1.displayProperti(i);
                    }
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    signIn();
                    break;
                case 0:
                    System.out.println("Terima kasih! Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
                    break;
            }
        } while (choice != 0);
    }

    private static void signUp() {
        System.out.println("Anda memilih Sign Up.");
        System.out.println("1. Pembeli");
        System.out.println("2. Kontraktor");
        System.out.println("0. Kembali ke Menu Utama");
        int signUpChoice = scanner.nextInt();
        switch (signUpChoice) {
            case 1:
                signUpAsPembeli();
                break;
            case 2:
                signUpAsKontraktor();
                break;
            case 0:
                System.out.println("Kembali ke Menu Utama.");
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
                break;
        }
    }

    private static void signUpAsPembeli() {
        System.out.println("Anda memilih Sign Up sebagai Pembeli.");
        System.out.println("Masukkan data Pembeli:");
        System.out.print("Nama: ");
        scanner.nextLine();
        String nama = scanner.nextLine();

        System.out.print("Nomor HP: ");
        String noHP = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        Timestamp tanggalDaftar = new Timestamp(System.currentTimeMillis());
        int jumlahTerbeli = 0;
        String tipePengguna = "Pembeli";

        p1 = new Pembeli(nama, noHP, email, password, tanggalDaftar, tipePengguna, jumlahTerbeli);
        pp1.tambahPembeli(p1);
    }

    private static void signUpAsKontraktor() {
        System.out.println("Anda memilih Sign Up sebagai Kontraktor.");
        System.out.println("Masukkan data Kontraktor:");
        System.out.print("Nama: ");
        scanner.nextLine();
        String knama = scanner.nextLine();

        System.out.print("Nomor HP: ");
        String knoHP = scanner.nextLine();

        System.out.print("Email: ");
        String kemail = scanner.nextLine();

        System.out.print("Password: ");
        String kpassword = scanner.nextLine();

        System.out.print("Link: ");
        String klink = scanner.nextLine();

        Timestamp ktanggalDaftar = new Timestamp(System.currentTimeMillis());
        String ktipePengguna = "Kontraktor";

        k1 = new Kontraktor(knama, knoHP, kemail, kpassword, ktanggalDaftar, klink, ktipePengguna);
        kk1.tambahKontraktor(k1);
    }

    private static void signIn() throws SQLException {

        System.out.println("Anda memilih Sign In.");
        System.out.println("Masukkan email: ");
        String email = scanner.next();

        System.out.println("Masukkan password: ");
        String password = scanner.next();

        if (adm.isValidAdmin(email, password)) {
            // Admin sign in berhasil
            System.out.println("Anda berhasil sign in sebagai Admin.");
            // Panggil MenuAdmin atau proses yang sesuai
            madm.tampilkanMenu();
        } else if (pp1.isValidPembeli(email, password)) {
            // Pembeli sign in berhasil
            System.out.println("Anda berhasil sign in sebagai idUser:" + pp1.getIdUserPembeli(email, password));
            // Panggil MenuPembeli atau proses yang sesuai
            mp.tampilkanMenuPembeli(pp1.getIdUserPembeli(email, password));
        } else if (kk1.isValidKontraktor(email, password)) {
            // Kontraktor sign in berhasil
            System.out.println("Anda berhasil sign in sebagai idUser:" + kk1.getIdUserKontraktor(email, password));
            // Panggil MenuKontraktor atau proses yang sesuai
            mk.tampilkanMenuKontraktor(kk1.getIdUserKontraktor(email, password));
        } else {
            // Sign in gagal
            System.out.println("Sign in gagal. Email atau password salah.");
        }
    }
}
