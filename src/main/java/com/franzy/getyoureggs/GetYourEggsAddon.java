package com.franzy.getyoureggs;

import api.AddonHandler;
import api.BTWAddon;

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