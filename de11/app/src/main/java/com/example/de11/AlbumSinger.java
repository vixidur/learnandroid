package com.example.de11;

public class AlbumSinger {
    private String mySong;
    private String nameSinger;

    public AlbumSinger(String mySong, String nameSinger) {
        this.mySong = mySong;
        this.nameSinger = nameSinger;
    }

    public String getMySong() {
        return mySong;
    }

    public void setMySong(String mySong) {
        this.mySong = mySong;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    @Override
    public String toString() {
        // VD: Someone like you - Adele
        return mySong + " - " + nameSinger;
    }
}
