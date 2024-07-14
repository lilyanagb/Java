package homework3.course.duration;

import homework3.course.Resource;

public record CourseDuration(int hours, int minutes) {
    public CourseDuration{
        if(hours < 0 || hours > 24){
            throw new IllegalArgumentException("Wrong hours range!");
        }
        if(minutes < 0 || minutes > 60){
            throw new IllegalArgumentException("Wrong minutes range!");
        }
    }

    public static CourseDuration of(Resource[] content){
        int totalMinutes = 0;
        for(Resource resourse : content){
            totalMinutes += resourse.getDuration().minutes();
        }

        int hours = totalMinutes / 60;
        int remainingMinutes = totalMinutes % 60;

        return new CourseDuration(hours, remainingMinutes);
    }
}
