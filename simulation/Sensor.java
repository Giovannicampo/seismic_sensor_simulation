public class Sensor extends MQTTPublisher{
    private SeismicData data;

    public Sensor(String ID, MQTTBroker broker){
        super(ID, broker);
    }

    public void setNewData(SeismicData data, String topic){
        this.data = data;
        publish(topic);
    }

    @Override
    public SeismicData getData() {
        return this.data;
    }
}
