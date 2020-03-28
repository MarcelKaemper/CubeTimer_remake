package dev.marcelkaemper.cubeTimer;


import javax.sound.sampled.Port;

public class CubeTimer {

    private static GUI gui;

    public static void main(String[] args) {
        gui = new GUI();
    }

    public static GUI getGui() {
        return gui;
    }
}