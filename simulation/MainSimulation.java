public class MainSimulation {
    private static MQTTBroker brokerEtnaNord = new MQTTBroker("BrokerProvenzana");
    private static Station stationEtnaNord = new Station("stationProvenzana", brokerEtnaNord);

    private static Sensor sensor1 = new Sensor("sensor1", brokerEtnaNord);
    private static Sensor sensor2 = new Sensor("sensor2", brokerEtnaNord);
    private static Sensor sensor3 = new Sensor("sensor3", brokerEtnaNord);
    private static Sensor sensor4 = new Sensor("sensor4", brokerEtnaNord);

    private static String topic1 = "latoNord";
    private static String topic2 = "latoSud";
    
    public static void main(String[] args){
        sensor1.connect();
        sensor2.connect();
        sensor3.connect();
        sensor4.connect();
        stationEtnaNord.connect();
        System.out.println();

        sensor1.setTopicMessage(topic1);
        sensor2.setTopicMessage(topic1);
        sensor3.setTopicMessage(topic1);
        sensor4.setTopicMessage(topic2);
        stationEtnaNord.subscribe(topic1);
        stationEtnaNord.subscribe(topic2);
        System.out.println();

        sensor1.setNewData(new SeismicData(10.40f, 2.8f),topic1);
        System.out.println();
        sensor2.setNewData(new SeismicData(12.22f, 1.5f),topic1);
        System.out.println();
        sensor1.setNewData(new SeismicData(15.40f, 4.2f),topic1);
        System.out.println();
        sensor3.setNewData(new SeismicData(18.30f, 5.2f),topic1);
        System.out.println();
        sensor4.setNewData(new SeismicData(12.50f, 3.5f),topic2);
        System.out.println();

        stationEtnaNord.printData();
    }
    
}
