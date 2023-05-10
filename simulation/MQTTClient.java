abstract class MQTTClient {
    private String clientID;
    protected MQTTBroker broker;
    private boolean publisher;
    private boolean connectionState;
    
    public MQTTClient(String ID, MQTTBroker broker, boolean publisher){
        this.clientID = ID;
        this.broker = broker;
        this.connectionState = false;
        this.publisher = publisher;
    }

    public String getID(){
        return this.clientID;
    }

    public boolean isPublisher(){
        return this.publisher;
    }

    public boolean isConnected(){
        return connectionState;
    }

    public void setConnectionState(boolean state){
        String c = state == true ? "connected" : "not connected";
        System.out.print("Device " + c);
        System.out.println(" to broker " + this.broker.getID());
        this.connectionState = state;
    }

    public void connect(){
        broker.onConnection(this);
    }

    /*
     *  subscriber methods not implemented yet
     */
    abstract void update();
    abstract boolean isTopicPresent(String topic);

    /*
     *  publisher methods not implemented yet
     */
    abstract SeismicData getData();
}
