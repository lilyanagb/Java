package homework7.src.csvprocessor.table.printer;

import java.util.Collection;

import homework7.src.csvprocessor.table.Table;

public interface TablePrinter {

  /**
   * Returns unmodifiable collection of strings, each one representing a formatted single row that needs to be printed
   * @param table - the table to be printed
   * @param alignments - the applied alignments for columns; if the number of given alignments is 
   * smaller than the number of columns, the remaining columns have NOALIGNMENT. If it's more, ignore the extra ones.
   */
  Collection<String> printTable(Table table, ColumnAlignment... alignments);

}
