package com.example.payoneertesttask.data;

public class Applicable {

    public String label;
    private Links links;

    public Applicable(String label, Links links) {
        this.label = label;
        this.links = links;
    }

    public String getLabel() {
        return label;
    }

    public Links getLinks() {
        return links;
    }
}
