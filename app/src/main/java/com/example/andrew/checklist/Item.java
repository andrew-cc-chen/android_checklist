package com.example.andrew.checklist;

/**
 * Created by Andrew on 5/19/2017.
 */

public class Item {
    private String name;
    private boolean checked = false;

    public Item() {
        name = "untitled";
    }

    public Item(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getName() {
        return this.name;
    }

    public boolean isChecked() {
        return this.checked;
    }
}
