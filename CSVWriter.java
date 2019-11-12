package CSVUtil;

import java.util.List;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter extends CSVEdit {
    public static final boolean APPEND_TEXT = false;

    public void write(List<String[]> data) {
        write(TOKEN_SEPARATOR, SKIP_FIRST_LINE, APPEND_TEXT, data);
    }

    public void write(String separator, boolean skipFirstLine, boolean append, List<String[]> data) {
        if (workbook != null) {    
            try {
                FileWriter outputStream = new FileWriter(workbook, append);
                if (skipFirstLine)
                    outputStream.append("\n");
                for (String[] row : data)
                    printLine(outputStream, String.join(separator, row));
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void printLine(FileWriter outputStream, String line) throws IOException {
        outputStream.append(CSVformat(line));
        outputStream.append("\n");
    }
}
