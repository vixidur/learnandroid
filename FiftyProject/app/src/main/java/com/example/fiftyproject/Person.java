package com.example.fiftyproject;

public class Person {
    private String nameSong;
    private String nameSinger;

    public Person(String nameSong, String nameSinger) {
        this.nameSong = nameSong;
        this.nameSinger = nameSinger;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    @Override
    public String toString() {
        return nameSong + " - " + nameSinger;
    }
}
