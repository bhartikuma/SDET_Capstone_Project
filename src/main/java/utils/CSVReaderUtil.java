package utils;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderUtil {

    public static Object[][] getCSVData() throws Exception {

        CSVReader reader =
        new CSVReader(new FileReader("src/test/resources/testdata.csv"));

        List<Object[]> dataList = new ArrayList<>();

        String[] line;

        reader.readNext(); 

        while((line = reader.readNext()) != null) {

            String name = line[0];
            String city = line[1];
            String card = line[2];

            System.out.println("DATA SOURCE : CSV");
            System.out.println("Name: "+name+" City: "+city+" Card: "+card);

            dataList.add(new Object[]{name,city,card});
        }

        reader.close();

        return dataList.toArray(new Object[0][0]);
    }
}