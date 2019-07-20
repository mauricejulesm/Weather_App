package com.jules.maurice.s1719024_weatherapp.models;

import com.jules.maurice.s1719024_weatherapp.R;

public enum NavBarItem {
    MAURICE_PHOTOS(R.id.maurice_photos),
    TWITTER(R.id.twitter),
    FACEBOOK(R.id.facebook),
    SETTINGS(R.id.user_settings);

    private int itemId;

    NavBarItem(int itemId) {
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public static NavBarItem fromViewId(int viewId) {
        for (NavBarItem navBarItem : NavBarItem.values()) {
            if (navBarItem.getItemId() == viewId) {
                return navBarItem;
            }
        }
        throw new IllegalStateException("Couldn't the view u requested");

    }
}