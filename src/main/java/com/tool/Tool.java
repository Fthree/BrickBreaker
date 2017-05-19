package com.tool;

import com.engine.Engine;

public class Tool implements Runnable {
    Engine engine;

    public void run() {
        engine = new Engine();
        engine.init();

        while(engine.isRunning()) {
            engine.preProcess();
            engine.update();
            engine.render();
        }
    }

    public static void main(String[] args) {
        new Tool().run();
    }
}