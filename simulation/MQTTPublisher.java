import java.util.ArrayList;

abstract class MQTTPublisher extends MQTTClient{
    private ArrayList<String> topics = new ArrayList<>();

    public MQTTPublisher(String ID, MQTTBroker broker){
        super(ID, broker, true);
    }

    public void setTopicMessage(String topic){
            System.out.print("new topic is " + topic);
            System.out.println(" for device " + getID());
            this.topics.add(topic);
    }

    /*
     *  inherited methods for subscribers
     */
    public void update(){}


    public boolean isTopicPresent(String topic){
        return this.topics.contains(topic);
    }

    public void publish(String topic){
        if(isConnected()){
            if(topics.contains(topic)){
                System.out.println("Publishing new data about topic: " + topic);
                this.broker.onPublishing(topic);
            }
        }
    }

    abstract SeismicData getData();
}
