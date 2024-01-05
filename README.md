# PropertiAPK
 Tugas_Besar_Kelompok_5_PBO

My SQL script untuk membuat suatu tabel di MySQl
1. Buat terlebih dahulu skema "revisi"
2. Script pertama:
CREATE DATABASE IF NOT EXISTS revisi;
USE revisi;

CREATE TABLE IF NOT EXISTS Pengguna (
    idUser INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(255) NOT NULL,
    noHP VARCHAR(20),
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    tanggalDaftar TIMESTAMP,
    tipePengguna VARCHAR(20) NOT NULL,
    waktuDonasi TIMESTAMP,
    link VARCHAR(255),
    jumlahTerbeli INT,
    favorit INT
);

CREATE TABLE IF NOT EXISTS Properti (
    propertiId INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(255) NOT NULL,
    deskripsi TEXT,
    lokasi VARCHAR(255),
    harga DOUBLE,
    panjangRumah DOUBLE,
    lebarRumah DOUBLE,
    luasRumah DOUBLE,
    jumlahDiFavoritkan DOUBLE,
    idUser INT,
    ketersediaan VARCHAR(20),
    waktuDitambahkan TIMESTAMP,
    FOREIGN KEY (idUser) REFERENCES Pengguna(idUser)
);

3. Script kedua:
CREATE TABLE IF NOT EXISTS Donasi (
    idDonasi INT AUTO_INCREMENT PRIMARY KEY,
    nominalDonasi INT,
    waktuDonasi TIMESTAMP,
    idUser INT,
    FOREIGN KEY (idUser) REFERENCES Pengguna(idUser)
);

4. Script ketiga:
ALTER TABLE Properti DROP FOREIGN KEY propertiId;
ALTER TABLE Properti ADD CONSTRAINT propertiId FOREIGN KEY (idUser) REFERENCES Pengguna(idUser) ON DELETE CASCADE;
