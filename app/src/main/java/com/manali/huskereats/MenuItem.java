package com.manali.huskereats;

public class MenuItem {
    private int id;
    private String itemName;
    private int image;

    public MenuItem(int id, String itemName, int image) {
        this.id = id;
        this.itemName = itemName;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public int getImage() {
        return image;
    }
}
