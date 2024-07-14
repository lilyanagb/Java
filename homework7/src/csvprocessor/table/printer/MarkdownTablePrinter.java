package homework7.src.csvprocessor.table.printer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import homework7.src.csvprocessor.table.Table;

public class MarkdownTablePrinter implements TablePrinter {

    @Override
    public Collection<String> printTable(Table table, ColumnAlignment... alignments) {
        List<String> formattedRows = new ArrayList<>();
        
        // Печат на заглавните и подзаглавните редове
        formattedRows.add("| " + String.join(" | ", table.getColumnNames()) + " |");
        formattedRows.add("| " + createAlignmentRow(table.getColumnNames().size(), alignments) + " |");
    
        //Печат на данните
        for(int i = 0; i < table.getRowsCount(); i++){
            List<String> rowData = new ArrayList<>();

            for(String colName : table.getColumnNames()){
                rowData.add(table.getColumnData(colName).toArray()[i].toString());
            }
            formattedRows.add("| " + String.join(" | ", rowData) + " |");
        }

        return formattedRows;
   }

    private String createAlignmentRow(int columnCount, ColumnAlignment[] alignments) {
        List<String> alignmentSymbols = new ArrayList<>();
        for (int i = 0; i < columnCount; i++) {
            if (i < alignments.length) {
                alignmentSymbols.add(alignments[i].toString());
            } else {
                alignmentSymbols.add(ColumnAlignment.NOALIGNMENT.toString());
            }
        }
        return String.join(" | ", alignmentSymbols);
    }    
}
