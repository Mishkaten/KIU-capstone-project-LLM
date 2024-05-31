//package GPT.St1.HW9;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Stream;
//
//public class CSVReading {
//    public static Stream<Object> processInputFile(String filePath) {
//        List<Object> data = new ArrayList<>().reversed();
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                String trackID = values[0];
//                double latitude = Double.parseDouble(values[1]);
//                double longitude = Double.parseDouble(values[2]);
//                Geo location = new Geo(latitude, longitude);
//                data.add(new PenguinData(trackID, location));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return data.stream();
//    }
//}
