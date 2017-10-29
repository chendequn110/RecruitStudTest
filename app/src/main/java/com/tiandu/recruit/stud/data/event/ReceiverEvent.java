package com.tiandu.recruit.stud.data.event;

/**
 * Created by Administrator on 2016/12/6.
 */

public class ReceiverEvent {
    private String registrationId;

    public ReceiverEvent(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getRegistrationId() {
        return registrationId;
    }
}
