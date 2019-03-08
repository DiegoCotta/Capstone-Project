package com.example.beerlovers.model;


public class NetworkState {
    public enum Status {
        RUNNING,
        SUCCESS,
        FAILED
    }

    public Status status;
    public String message;

    public NetworkState(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public NetworkState(Status status) {
        this.status = status;
    }

    public static final NetworkState LOADED = new NetworkState(Status.SUCCESS);
    public static final NetworkState LOADING = new NetworkState(Status.RUNNING);

    public NetworkState error(String message) {
        return new NetworkState(Status.FAILED, message);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        NetworkState state = (NetworkState) obj;
        return state.status.equals(this.status);
    }
}
