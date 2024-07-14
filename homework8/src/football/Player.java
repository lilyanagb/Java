package homework8.src.football;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record Player(String name, String fullName, LocalDate birthDate, 
            int age, double heightCm, double weightKg, 
            List<Position> positions, String nationality, 
            int overallRating, int potential, long valueEuro, 
            long wageEuro, Foot preferredFoot) {

    private static final String PLAYER_ATTRIBUTE_DELIMITER = ";";

    public static Player of(String line){
        final String tokens[] = line.split(PLAYER_ATTRIBUTE_DELIMITER);
        LocalDate date = LocalDate.parse(tokens[2]);
        List<Position> positions = parsePositions(tokens[6]);
        Foot preferred = Foot.valueOf(tokens[12]);

        return new Player(tokens[0], tokens[1], date, Integer.parseInt(tokens[3]),
                Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]), positions, 
                tokens[7], Integer.parseInt(tokens[8]), Integer.parseInt(tokens[9]), 
                Long.parseLong(tokens[10]), Long.parseLong(tokens[11]), preferred);
    } 

    private static List<Position> parsePositions(String positionsString) {
        String[] positionNames = positionsString.split(",");
        
        return Arrays.stream(positionNames)
                .map(String::trim)
                .map(Position::valueOf)
                .collect(Collectors.toList());
    }
}
