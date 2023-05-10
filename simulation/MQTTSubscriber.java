import java.util.ArrayList;

abstract class MQTTSubscriber extends MQTTClient{
    private ArrayList<String> topics = new ArrayList<>();

    public MQTTSubscriber(String ID, MQTTBroker broker){
        super(ID,broker, false);
    }

    public boolean isTopicPresent(String topic){
        for(String t : topics){
            if(t.equals(topic)){
                return true;
            }
        }
        return false;
    }

    public void subscribe(String topic){
        if(isConnected()){
            System.out.println("new subscription to " + topic);
            topics.add(topic);
        }
    }

    abstract void setData(SeismicData data);

    public void update(){
        ArrayList<SeismicData> data = new ArrayList<>();
        if(isConnected()){
            for(String t : topics){
                System.out.println("Updating about topic " + t);
                data = this.broker.onUpdate(t);
                for(SeismicData s: data){
                    if(s != null){
                        System.out.println("Setting new data");
                        System.out.println(s.getTime());
                        setData(s);
                    }
                }
            }
        }
    }
}