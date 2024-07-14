package homework4.member;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import homework4.workout.Exercise;
import homework4.workout.Workout;

public class Member implements GymMember {
    private Address address;
    private String name;
    private int age;
    private String personalIdNumber;
    private Gender gender;
    private Map<DayOfWeek, Workout> program;

    public Member(Address address, String name, int age, String personalIdNumber, Gender gender) {
        this.address = address;
        this.name = name;
        this.age = age;
        this.personalIdNumber = personalIdNumber;
        this.gender = gender;
        this.program = new HashMap<>();
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getPersonalIdNumber() {
        return personalIdNumber;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public Map<DayOfWeek, Workout> getTrainingProgram() {
        return Collections.unmodifiableMap(program);
    }

    @Override
    public void setWorkout(DayOfWeek day, Workout workout) {
        if(day == null || workout == null){
            throw new IllegalArgumentException("Wrong args passed!");
        }

        program.put(day, workout);
    }

    @Override
    public Collection<DayOfWeek> getDaysFinishingWith(String exerciseName) {
        if(exerciseName == null){
            throw new IllegalArgumentException("Wrong exercise name passed!");
        }

        List<DayOfWeek> days = new ArrayList<>();

        for(Map.Entry<DayOfWeek, Workout> entry : program.entrySet()) {
            DayOfWeek day = entry.getKey();
            Workout workout = entry.getValue();
            
            if(workout != null){
                List<Exercise> exercises = workout.getExercises();
                Exercise last = exercises.get(exercises.size() - 1);
                if(exerciseName.equals(last.name())){
                    days.add(day);
                }
            }
        }

        return days;
    }

    //не трябва да хвърлям така exceptions
    @Override
    public void addExercise(DayOfWeek day, Exercise exercise) throws DayOffException {
       if(day == null || exercise == null) {
            throw new IllegalArgumentException("Wrong args passed!");
       }

       Workout workout = program.get(day);
       if(workout == null){
            throw new DayOffException();
       }

       workout.addExercise(exercise);
    }

    @Override
    public void addExercises(DayOfWeek day, List<Exercise> exercises) throws DayOffException {
        if (day == null || exercises == null || exercises.isEmpty()) {
            throw new IllegalArgumentException("Day and exercises cannot be null or empty.");
        }

        Workout workout = program.get(day);

        if(workout == null) {
            throw new DayOffException();
        }
    
        for(Exercise ex : exercises){
            workout.addExercise(ex);
        }
    }
    
}
