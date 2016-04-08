package com.gaincigarretprice.idiot.sun.view.interfaces;

/**
 * Created by ladmusician on 4/6/16.
 */
public interface MusicFocusable {
    /** Signals that audio focus was gained. */
    void onGainedAudioFocus();
    /**
     * Signals that audio focus was lost.
     *
     * @param canDuck If true, audio can continue in "ducked" mode (low volume). Otherwise, all
     * audio must stop.
     */
    void onLostAudioFocus(boolean canDuck);
}
