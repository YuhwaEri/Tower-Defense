package com.group7.controller;

import com.group7.model.TowerDefenseModel;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * 
 * Takes care of timings and running code that
 * needs to happen repeatedly.
 * 
 * No need to instantiate; prefer not to because would lead
 * to multiple timelines.
 * 
 */

public abstract class Ticker {
    private static final int STARTING_TICK_RATE = 30;
    private static TowerDefenseModel model;
    private static Timeline t1;


    /**
     * Starts an instance of a javafx Timeline object that runs an event handler
     * STARTING_TICK_RATE times per second.
     * 
     * @param model Model to be updated by event handler.
     */
    public static void start(TowerDefenseModel model) {

        Ticker.model = model;
        
        // t1 = new Timeline(new KeyFrame(Duration.seconds(1.0 / STARTING_TICK_RATE), new AnimationHandler()));
        
        // t1.setCycleCount(-1);
        // t1.play();
        

    }
    
}