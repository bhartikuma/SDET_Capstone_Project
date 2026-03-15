package utils;

import java.util.ArrayList;
import java.util.List;

public class DataProviderUtil {

    public static Object[][] getAllData() throws Exception {

        Object[][] excel = ExcelReader.getExcelData();
        Object[][] csv = CSVReaderUtil.getCSVData();
        Object[][] db = DatabaseUtil.getDBData();
        

        List<Object[]> allData = new ArrayList<>();

        for(Object[] row : excel)
            allData.add(row);

        for(Object[] row : csv)
            allData.add(row);

        for(Object[] row : db)
            allData.add(row);

        return allData.toArray(new Object[0][0]);
    }
}
