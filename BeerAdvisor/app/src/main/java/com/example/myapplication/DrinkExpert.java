package com.example.myapplication;
import java.util.ArrayList;
import java.util.List;

public class DrinkExpert {
    List<String>getBrands(String color) {
        List<String> brands = new ArrayList<>();
        if (color.equals("Clear")) {
            brands.add("Sprite");
            brands.add("7Up");
            brands.add("Canada Dry");
            brands.add("Water");
            brands.add("Propel");
        }
        else if (color.equals("Dark Cola")) {
            brands.add("Pepsi");
            brands.add("Coke");
            brands.add("RC");
            brands.add("Root Bear");
        }
        else if (color.equals("Punch")) {
            brands.add("Fruit Punch");
            brands.add("Grape");
            brands.add("Pineapple");
            brands.add("Cherry");
        }
        else if (color.equals("Diet")) {
            brands.add("Diet Pepsi");
            brands.add("Diet Coke");
            brands.add("Diet Mt Dew");
            brands.add("Diet Root Beer");
        }
        else if (color.equals("Zero Sugar")) {
            brands.add("Pepsi Zero");
            brands.add("Coke Zero");
        }
        else if (color.equals("Energy Drinks")) {
            brands.add("Red Bull");
            brands.add("Bang");
            brands.add("Monster");
            brands.add("Reign");
        }
        else if (color.equals("Tea")) {
            brands.add("Arizona");
            brands.add("Lipton");
            brands.add("Green Tea");
            brands.add("Herbal Tea");
        }
        else if (color.equals("Coffee")) {
            brands.add("Latte");
            brands.add("Capuccino");
            brands.add("Ice Coffee");
            brands.add("Espresso");
        }
        else {
            brands.add("Please select a drink type");
            brands.add("I can not give recommendations with out knowing what you want");
        }
        return brands;
    }
}
