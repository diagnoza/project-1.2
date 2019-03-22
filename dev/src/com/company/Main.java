package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Universe universe = new Universe();

        // TODO: pass a file here...
        universe.loadData();

        universe.run();


        new GUI(universe);
    }
}
