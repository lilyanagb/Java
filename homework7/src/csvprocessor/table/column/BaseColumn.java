package homework7.src.csvprocessor.table.column;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BaseColumn implements Column {
    private Set<String> values;

    public BaseColumn() {
        this(new HashSet<>());
    }

    public BaseColumn(Set<String> values) {
        this.values = values;
    }

    @Override
    public void addData(String data) {
        if(data == null || data.isBlank()){
            throw new IllegalArgumentException("Data cannot be null or blank.");
        }

        values.add(data);
    }

    @Override
    public Collection<String> getData() {
        return Set.copyOf(values);
    }
    
}
