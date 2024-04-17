package com.smithcastlepictures.smithcastlefilmmedia.mediaplayerstates;

public enum MediaplayerStates {
        MPS_IDLE(0), MPS_INITIALIZED(1),
        MPS_PREPARING(2), MPS_PREPARED(3),
        MPS_STARTED(4), MPS_STOPPED(5),
        MPS_PAUSED(6);

        private int state;

        private MediaplayerStates(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }
}
