package com.solvegam.adaptertest;

/**
 * Created by Stas on 29.10.2015.
 */
public class Item {
    private String name;
    private int age;

    public Item (String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
