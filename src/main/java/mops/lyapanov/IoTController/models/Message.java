package mops.lyapanov.IoTController.models;

public class Message {
    private Device device;
    private int message;

    public Message(Device device, int message) {
        this.device = device;
        this.message = message;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public int getMessage() {
        return message;
    }
    public void setMessage(int message) {
        this.message = message;
    }

}
