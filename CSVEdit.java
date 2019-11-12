package CSVUtil;

import java.io.File;
import java.io.IOException;

abstract class CSVEdit {
    public static final String TOKEN_SEPARATOR = ",";
    public static final boolean SKIP_FIRST_LINE = true;
    
    protected File workbook = null;

    public boolean open(String url) {
        try {
            workbook = new File(url);
            if (!workbook.exists()) {
                try {
                    workbook.createNewFile();
                } catch (IOException e) {
                    return false;
                }
            }
        } catch (NullPointerException e) {
            return false;
        } 
        return workbook.isFile();
    }

    public static String CSVformat(String str) {
        return str.contains("\"") ? str.replace("\"", "\"\"") : str;
    }
}
