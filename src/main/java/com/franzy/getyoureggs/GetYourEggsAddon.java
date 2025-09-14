package com.franzy.getyoureggs;

import btw.AddonHandler;
import btw.BTWAddon;

public class GetYourEggsAddon extends BTWAddon {
    private static GetYourEggsAddon instance;

    public GetYourEggsAddon() {
        super();
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
    }
}