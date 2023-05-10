public class SeismicData {
    private float time;
    private float richterMagnitude;

    public SeismicData(float time, float richterMagnitude){
        this.time = time;
        this.richterMagnitude = richterMagnitude;
    }

    public float getTime(){
        return this.time;
    }

    public float getMagnitude(){
        return this.richterMagnitude;
    }
}
