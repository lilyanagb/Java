package homework7.src.csvprocessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import homework7.src.csvprocessor.exceptions.CsvDataNotCorrectException;
import homework7.src.csvprocessor.table.BaseTable;
import homework7.src.csvprocessor.table.Table;
import homework7.src.csvprocessor.table.printer.ColumnAlignment;
import homework7.src.csvprocessor.table.printer.MarkdownTablePrinter;

public class CsvProcessor implements CsvProcessorAPI {
    private Table table;

    public CsvProcessor() {
        this(new BaseTable());
    }
        
    public CsvProcessor(Table table) {
        this.table = table;
    }

    @Override
    public void readCsv(Reader reader, String delimiter) throws CsvDataNotCorrectException {
        try(BufferedReader bufferedReader = new BufferedReader(reader)){
            String line;

            while((line = bufferedReader.readLine()) != null){
                String[] data = line.split(delimiter);
                table.addData(data);
            }
        } catch (IOException e){
            throw new CsvDataNotCorrectException();
        }
    }

    @Override
    public void writeTable(Writer writer, ColumnAlignment... alignments) {
        MarkdownTablePrinter markdownTablePrinter = new MarkdownTablePrinter();

        try {
            for(String row : markdownTablePrinter.printTable(table, alignments)){
                writer.write(row + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
