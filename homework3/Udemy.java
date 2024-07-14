package homework3;

import homework3.account.Account;
import homework3.course.Category;
import homework3.course.Course;
import homework3.course.duration.CourseDuration;
import homework3.exception.AccountNotFoundException;
import homework3.exception.CourseNotFoundException;

public class Udemy implements LearningPlatform {
    private Account[] accounts;
    private Course[] courses;
    
    public Udemy(Account[] accounts, Course[] courses){
        this.accounts = accounts;
        this.courses = courses;
    }

    @Override
    public Course findByName(String name) throws CourseNotFoundException {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Invalid course name!");
        }

        for(Course course : courses){
            if(course.getName().equals(name)){
                return course;
            }
        }

        throw new CourseNotFoundException();
    }

    @Override
    public Course[] findByKeyword(String keyword) {
        Course[] match = new Course[courses.length];
        int count = 0;

        if(keyword == null || keyword.isBlank() || keyword.matches("[a-zA-Z]+")){
            throw new IllegalArgumentException("Invalid keyword!");
        }
        
        for(Course course : courses){
            String description = course.getDescription();
            String name = course.getName();

            if(description.contains(keyword) || name.contains(keyword)){
                match[count] = course;
                count++;
            }
        }

        return match;
    }

    @Override
    public Course[] getAllCoursesByCategory(Category category) {
        Course[] match = new Course[courses.length];
        int count = 0;

        if(category == null){
            throw new IllegalArgumentException("Invalid category!");
        }
        
        for(Course course : courses){
            if(category == course.getCategory()){
                match[count] = course;
                count++;
            }
        }

        return match;
    }

    @Override
    public Account getAccount(String name) throws AccountNotFoundException {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Invalid name!");
        }

        for(Account account : accounts){
            if(account.getUsername().equals(name)){
                return account;
            }
        }

        throw new AccountNotFoundException();
    }

    @Override
    public Course getLongestCourse() {
        if(courses.length == 0) return null;

        CourseDuration max = new CourseDuration(0, 0);
        int index = 0;

        for(int i = 0; i < courses.length; i++){
            CourseDuration courseTime = courses[i].getTotalTime();
            if((max.hours() < courseTime.hours()) || (max.hours() == courseTime.hours() && max.minutes() < courseTime.minutes())){
                max = courseTime;
                index = i;
            }
        }

        return courses[index];
    }

    @Override
    public Course getCheapestByCategory(Category category) {
        if(category == null){
            throw new IllegalArgumentException("Invalid category!");
        }

        if (courses.length == 0) {
            return null;
        }

        Course cheapestCourse = null;
        for (Course course : courses) {
            if (course.getCategory() == category && (cheapestCourse == null || course.getPrice() < cheapestCourse.getPrice())) {
                cheapestCourse = course;
            }
        }

        return cheapestCourse;
    }
}
