package com.sajal.tasks;

public class Foods {
    private String name;
    private String description;
    private int imageresource;

    public static final Foods[] foods = {
            new Foods("Cookies","A cookie is a baked or cooked food that is typically small, flat and sweet. It usually contains flour, sugar and some type of oil or fat. It may include other ingredients such as raisins, oats, chocolate chips, nuts, etc. ", R.drawable.cookies),
            new Foods("Sandwiches","A sandwich is a food typically consisting of vegetables, sliced cheese or meat, placed on or between slices of bread, or more generally any dish wherein two or more pieces of bread serve as a container or wrapper for another food type.", R.drawable.sandwich),
            new Foods("Pancakes","A pancake is a flat cake, often thin and round, prepared from a starch-based batter that may contain eggs, milk and butter", R.drawable.pancakes)
    };

    private Foods(String name, String description, int imageresource)
    {
        this.name=name;
        this.description=description;
        this.imageresource=imageresource;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public int getImageresource()
    {
        return imageresource;
    }

    public String toString()
    {
        return this.name;
    }
}
