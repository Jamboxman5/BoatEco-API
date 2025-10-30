package me.jamboxman5.boateco.model;

import me.jamboxman5.boateco.dao.EntryDAO;

public class Entry {

    String plate;
    int month;
    int day;
    int year;
    int miles;
    double gallons;
    double cost;

    public String getPlate() { return plate; }

    public void setPlate(String plate) {  this.plate = plate; }

    public int getMonth() { return month; }

    public void setMonth(int month) { this.month = month; }

    public int getDay() { return day; }

    public void setDay(int day) { this.day = day; }

    public int getYear() { return year; }

    public void setYear(int year) { this.year = year; }

    public int getMiles() { return miles;  }

    public void setMiles(int miles) { this.miles = miles;  }

    public double getGallons() { return gallons; }

    public void setGallons(double gallons) { this.gallons = gallons; }

    public double getCost() { return cost; }

    public void setCost(double cost) { this.cost = cost; }


    public void updateEntry(EntryDAO entryDAO) {
        entryDAO.delete(month, day, year, plate);
        entryDAO.add(month, day, year, miles, gallons, cost, plate);
    }
}


