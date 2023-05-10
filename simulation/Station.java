import java.util.ArrayList;

public class Station extends MQTTSubscriber{
    ArrayList<SeismicData> totalData = new ArrayList<>();
    public Station(String ID, MQTTBroker broker){
        super(ID, broker);
    }

    public SeismicData getData(){return null;}

    @Override
    public void setData(SeismicData data) {
        if(!totalData.contains(data)){
            totalData.add(data);
        }
    }

    public void printData(){
        for(SeismicData d : totalData){
            System.out.println("Data collected in date: " + d.getTime());
            System.out.println("Richter scale, magnitude: "+ d.getMagnitude());
    
            if(d.getMagnitude() > 5.0){
                System.out.println("Likely large damage for inhabitants");
            }
            else if(d.getMagnitude() > 3.5){
                System.out.println("Possible damage for inhabitants");
            }
        }
    }
}
