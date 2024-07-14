package homework3.course;

import java.util.Arrays;

import homework3.course.duration.CourseDuration;
import homework3.exception.ResourceNotFoundException;

public class Course implements Completable, Purchasable {
    private String name;
    private String description;
    private double price;
    private Resource[] content;
    private Category category;
    private boolean purchased;

    public Course(String name, String description, double price, Resource[] content, Category category){
        this.name = name;
        this.description = description;
        this.content = content;
        this.price = price;
        this.category = category;
        this.purchased = false;
    }

    @Override
    public void purchase() {
        this.purchased = true;
    }

    @Override
    public boolean isPurchased() {
        return purchased;
    }

    @Override
    public boolean isCompleted() {
        int count = 0;

        for(Resource resource : content){
            if(!resource.isCompleted()) count++;
        }

        if(count > 0) return false;
        return true;
    }

    @Override
    public int getCompletionPercentage() {
        int count = 0;

        for(Resource resource : content){
            if(!resource.isCompleted()) count++;
        }

        return (count * 100) / content.length;
    }

    /**
     * Returns the name of the course.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the course.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the price of the course.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the category of the course.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Returns the content of the course.
     */
    public Resource[] getContent() {
        return content;
    }

    /**
     * Returns the total duration of the course.
     */
    public CourseDuration getTotalTime() {
        return CourseDuration.of(content);
    }

    /**
     * Completes a resource from the course.
     * 
     * @param resourceToComplete the resource which will be completed.
     * @return 
     * @throws IllegalArgumentException if resourceToComplete is null.
     * @throws ResourceNotFoundException if the resource could not be found in the course.
     */
    public void completeResource(Resource resourceToComplete) throws ResourceNotFoundException {
        if(resourceToComplete == null){
            throw new IllegalArgumentException("Invalid course!");
        }

        if(!Arrays.asList(content).contains(resourceToComplete)){
            throw new ResourceNotFoundException();
        }

        resourceToComplete.complete();
    }
}
