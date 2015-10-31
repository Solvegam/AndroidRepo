package com.solvegam.adaptertest;

/**
 * Created by Stas on 29.10.2015.
 */
public class Item {
    private String name;
    private String age;

    public Item (String name, String age)
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
