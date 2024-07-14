package homework4.member;

public record Address(double longitude, double latitude){
    public double getDistanceTo(Address other){
        return Math.sqrt(
                Math.pow(other.longitude - longitude, 2) + 
                Math.pow(other.latitude - latitude, 2)
                );
    }
}
