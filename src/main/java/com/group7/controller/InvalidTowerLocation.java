package com.group7.controller;

public class InvalidTowerLocation extends Exception {

    public InvalidTowerLocation(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public InvalidTowerLocation(String errorMessage) {
        super(errorMessage);
    }

}
