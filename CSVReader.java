package CSVUtil;

import java.util.Scanner;
import java.util.Map;
import java.util.Arrays;

import java.io.FileNotFoundException;

public class CSVReader extends CSVEdit {
    public Map<String, String[]> read() {
        return read(TOKEN_SEPARATOR, SKIP_FIRST_LINE);
    }

    public Map<String, String[]> read(String separator, boolean skipFirstLine) {
        Map<String, String[]> table = new java.util.Hashtable<String, String[]>();
        if (workbook != null) {
            try {
                Scanner inputStream = new Scanner(workbook);
                if (skipFirstLine)
                    inputStream.nextLine();
                while (inputStream.hasNext()) {
                    String[] tuple = parseLine(inputStream.nextLine(), separator);
                    table.put(tuple[0], Arrays.copyOfRange(tuple, 1, tuple.length));
                }
                inputStream.close();
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
        return table;
    }

    private String[] parseLine(String line, String separator) {
        String regex = separator + "(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"; //don't split on separator into text in quotes
        String[] matches = line.split(regex);
        
        for (int i = 0; i < matches.length; ++i) {
            matches[i] = matches[i].strip().replaceAll("^\"+|\"+$", ""); //removes those quotes from both sides of the text
        }

        return matches;
    }
}
