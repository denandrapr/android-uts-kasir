package com.example.kaseerr.Model;

public class BarangModel {

    String kode;
    String nama;
    String qty;
    int harga_beli;
    int harga_jual;
    int diskon;

    public BarangModel(String kode, String nama, String qty, int harga_beli, int harga_jual, int diskon) {
        this.kode = kode;
        this.nama = nama;
        this.qty = qty;
        this.harga_beli = harga_beli;
        this.harga_jual = harga_jual;
        this.diskon = diskon;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public String getQty() {
        return qty;
    }

    public int getHarga_beli() {
        return harga_beli;
    }

    public int getHarga_jual() {
        return harga_jual;
    }

    public int getDiskon() {
        return diskon;
    }
}
