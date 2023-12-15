/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author usama
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Donasi;
import static utils.BaseDAO.conn;

public class DonasiDAO extends BaseDAO {

    public void tambahDonasi(Donasi donasi) {
        String query = "INSERT INTO revisi.donasi (nominalDonasi, waktuDonasi, idUser) VALUES (?, ?, ?)";
        try (PreparedStatement pS = conn.prepareStatement(query)) {
            pS.setInt(1, donasi.getNominalDonasi());
            pS.setTimestamp(2, donasi.getWaktuDonasi());
            pS.setString(3, donasi.getIdUser());

            int rowsAffected = pS.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data berhasil dimasukkan ke tabel donasi.");
            } else {
                System.out.println("Gagal memasukkan data ke tabel donasi.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal memasukkan data ke tabel donasi: " + e.getMessage());
        }
    }

    public int totalDonasiByIdUser(String idUser) {
        int totalDonasi = 0;
        String query = "SELECT SUM(nominalDonasi) AS total FROM revisi.donasi WHERE idUser = ?";
        try (PreparedStatement pS = conn.prepareStatement(query)) {
            pS.setString(1, idUser);

            ResultSet resultSet = pS.executeQuery();
            if (resultSet.next()) {
                totalDonasi = resultSet.getInt("total");
                System.out.println("Total donasi untuk idUser " + idUser + ": " + totalDonasi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal mengambil total donasi: " + e.getMessage());
        }
        return totalDonasi;
    }

    public int banyakDonasiByIdUser(String idUser) {
        int banyakDonasi = 0;
        String query = "SELECT COUNT(*) AS jumlah_donasi FROM revisi.donasi WHERE idUser = ?";
        try (PreparedStatement pS = conn.prepareStatement(query)) {
            pS.setString(1, idUser);

            ResultSet resultSet = pS.executeQuery();
            if (resultSet.next()) {
                banyakDonasi = resultSet.getInt("jumlah_donasi");
                System.out.println("Banyaknya donasi untuk idUser " + idUser + ": " + banyakDonasi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal mengambil banyaknya donasi: " + e.getMessage());
        }
        return banyakDonasi;
    }

}
