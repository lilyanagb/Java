package homework3.course;

import homework3.course.duration.ResourceDuration;

public class Resource implements Completable {
    private String name;
    private ResourceDuration duration; 
    private boolean completed;

    public Resource(String name, ResourceDuration duration){
        this.name = name;
        this.duration = duration;
        this.completed = false;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public int getCompletionPercentage() {
        return completed ? 100 : 0;
    }

     /**
     * Returns the resource name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the total duration of the resource.
     */
    public ResourceDuration getDuration() {
        return duration;
    }

    /**
     * Marks the resource as completed.
     */
    public void complete() {
        this.completed = true;
    }
}
