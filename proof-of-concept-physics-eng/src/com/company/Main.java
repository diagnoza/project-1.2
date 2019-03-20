package com.company;

public class Main {

    public static void main(String[] args) {
        Universe universe = new Universe();

        // TODO: pass a file here...
        universe.loadData();

        universe.run();
    }
}
