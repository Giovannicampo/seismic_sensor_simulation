import java.util.ArrayList;

public class MQTTBroker {
    private String brokerID;
    private ArrayList<MQTTClient> devices = new ArrayList<>();

    public MQTTBroker(String ID){
        this.brokerID = ID;
    }

    public void attach(MQTTClient sub){
        devices.add(sub);
    }

    public void detach(String ID){
        devices.stream().filter(c -> c.getID().equals(ID)).forEach(c -> devices.remove(c));
    }

    public String getID(){
        return this.brokerID;
    }

    /*
     * connection methods
    */
    public void onConnection(MQTTClient device){
        attach(device);
        sendConnack(device.getID());
    }

    public void sendConnack(String clientID){
        System.out.println("Request of connection by " + clientID);
        devices.stream().filter(c -> c.getID().equals(clientID)).forEach(c -> c.setConnectionState(true));
    }

    /*
     *  publish methods
    */
    public void onPublishing(String topic){
        devices.stream().filter(c -> c.isPublisher() == false).filter(c -> c.isTopicPresent(topic)).forEach(c -> c.update());
    }

    /*
     *  update methods
    */
    public ArrayList<SeismicData> onUpdate(String topic){
        ArrayList<SeismicData> data = new ArrayList<>();
        for(MQTTClient c : devices){
            if(c.isPublisher()){
                if(c.isTopicPresent(topic)){
                    data.add(c.getData());
                }
            }
        }
        return data;
    }

}
