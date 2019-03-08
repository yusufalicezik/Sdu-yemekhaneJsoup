package com.yusufalicezik.jsoup_project;

public class Yemek {
    private String tarih,gun,yemekListesi;
    //yemek listesi daha sonra br ler ile bölünecek.


    public Yemek(String tarih, String gun, String yemekListesi) {
        this.tarih = tarih;
        this.gun = gun;
        this.yemekListesi = yemekListesi;
    }

    public Yemek() {
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getGun() {
        return gun;
    }

    public void setGun(String gun) {
        this.gun = gun;
    }

    public String getYemekListesi() {
        return yemekListesi;
    }

    public void setYemekListesi(String yemekListesi) {
        this.yemekListesi = yemekListesi;
    }
}
