package com.example.demo;

import java.io.Serializable;

public class Flag implements Serializable {
    String name;
    String en_name;
    String population;
    String capital;
    String currency;
    String language;
    String area;
    Integer img;

    //建構式
    Flag(int image, String en_name, String name, String population, String capital, String currency, String language, String area){
        this.img = image;
        this.en_name = en_name;
        this.name = name;
        this.population = population;
        this.capital = capital;
        this.currency = currency;
        this.language = language;
        this.area = area;
    }
    Flag(int image, String name){
        this.img = image;
        this.name = name;
    }


    public Integer getImg() {
        return img;
    }

    public String getEn_name() { return en_name; }

    public String getName(){
        return  name;
    }

    public String getPopulation() {
        return population;
    }

    public String getCapital() {
        return capital;
    }

    public String getCurrency() {
        return currency;
    }

    public String getLanguage() {
        return language;
    }

    public String getArea() {
        return area;
    }
}
