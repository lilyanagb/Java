package homework7.src.csvprocessor.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import homework7.src.csvprocessor.exceptions.CsvDataNotCorrectException;
import homework7.src.csvprocessor.table.column.BaseColumn;
import homework7.src.csvprocessor.table.column.Column;

public class BaseTable implements Table {
    private List<Column> columns;
    
    public BaseTable(){
        this.columns = new ArrayList<>();
    }

    @Override
    public void addData(String[] data) throws CsvDataNotCorrectException {
        if(data == null){
            throw new IllegalArgumentException("Data cannot be null");
        }

        if(columns.isEmpty()){
            for(String header : data){
                BaseColumn column = new BaseColumn();
                column.addData(header);
                columns.add(column);
            }
        } else {
            if(data.length != columns.size()){
                throw new CsvDataNotCorrectException();
            }

            for(int i = 0; i < data.length; i++){
                columns.get(i).addData(data[i]);
            }
        }
    }

    @Override
    public Collection<String> getColumnNames() {
        List<String> columnNames = new ArrayList<>();

        for(Column column : columns){
            Collection<String> columnData = column.getData();
            
            if(!columnData.isEmpty()){
                columnNames.add(columnData.iterator().next()); //Връща първия елемент от колекцията
            }
        }

        return columnNames;
    }

    @Override
    public Collection<String> getColumnData(String column) {
        for(Column col : columns){
            Collection<String> columnData = col.getData();

            if(!columnData.isEmpty() && columnData.iterator().next().equals(column)){
                return columnData;
            }
        }

        throw new IllegalArgumentException("Column not found");
    }

    @Override
    public int getRowsCount() {
        if(columns.isEmpty()){
            return 0;
        }

        return columns.get(0).getData().size();
    }
    
}
