package ru.netology.entity;

public class Location {

    private final String city;

    private final Country country;

    private final String street;

    private final int building;

    public Location(String city, Country country, String street, int building) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.building = building;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public int getBuilding() {
        return building;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Location object = (Location) obj;
        if (this.getCity() == null) {
            if (object.getCity() != null) {
                return false;
            }
        } else {
            if (object.getCity() == null) {
                return false;
            } else {
                if (!this.getCity().equals(object.getCity())) {
                    return false;
                }
            }
        }
        if (this.getStreet() == null) {
            if (object.getStreet() != null) {
                return false;
            }
        } else {
            if (object.getStreet() == null) {
                return false;
            } else {
                if (!this.getStreet().equals(object.getStreet())) {
                    return false;
                }
            }
        }
        if (this.getCountry() == null) {
            if (object.getCountry() != null) {
                return false;
            }
        } else {
            if (object.getCountry() == null) {
                return false;
            } else {
                if (this.getCountry() != object.getCountry()) {
                    return false;
                }
            }
        }
        return (this.getBuilding() != object.getBuilding()) ? false : true;
    }
}

