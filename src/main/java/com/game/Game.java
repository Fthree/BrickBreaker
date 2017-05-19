package com.game;

import com.engine.Engine;
import com.game.World.IWorld;
import com.game.World.WorldA;

public class Game implements Runnable {
    Engine engine;

    IWorld world;

    public void run() {
        world = new WorldA();
        world.create();

        engine = new Engine();
        engine.init();

        while(engine.isRunning()) {
            engine.preProcess();
            engine.update();
            engine.render();
        }
    }

    public static void main(String[] args) {
        new Game().run();
    }
}