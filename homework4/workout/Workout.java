package homework4.workout;

import java.util.ArrayList;
import java.util.List;

public record Workout(SequencedCollection<Exercise> exercises) {
    public List<Exercise> getExercises(){
        return new ArrayList<>(exercises);
    }

    public Workout addExercise(Exercise exercise){
        List<Exercise> updateExercises = new ArrayList<>(exercises);
        updateExercises.add(exercise);
        return new Workout(SequencedCollection.from(updateExercises));
    }
}