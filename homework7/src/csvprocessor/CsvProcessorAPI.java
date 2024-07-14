package homework7.src.csvprocessor;

import java.io.Reader;
import java.io.Writer;

import homework7.src.csvprocessor.exceptions.CsvDataNotCorrectException;
import homework7.src.csvprocessor.table.printer.ColumnAlignment;

public interface CsvProcessorAPI {

    /**
     * Reads a CSV data from Reader
     * @param reader the Reader from which the CSV will be read
     * @param delimiter the delimeter used to split the CSV (such as ,.- and so on)
     * @throws CsvDataNotCorrectException if the CSV data is in wrong format
     */
    void readCsv(Reader reader, String delimiter) throws CsvDataNotCorrectException;

    /**
     * Writes the content of the table to the provided Writer
     * @param writer - the Writer to which the table will be written
     */
    void writeTable(Writer writer, ColumnAlignment... alignments);

}