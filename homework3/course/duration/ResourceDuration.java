package homework3.course.duration;

public record ResourceDuration(int minutes) {
    public ResourceDuration{
        if(minutes < 0 || minutes > 60){
            throw new IllegalArgumentException("Wrong minutes range!");
        }
    }
}
