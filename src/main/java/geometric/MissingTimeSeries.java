package geometric;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

public class MissingTimeSeries {

    public static File input = new File(
            "/Users/sugumar/Documents/code/algorithams/src/main/resources/MissingSeriesData.txt");

    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static void main(String[] args) throws Exception {

        missingSeries(input);
    }

    static void missingSeries1(File file) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        Result result = new Result();
        br.lines()
          .map(line -> parse(line))
          .collect(Collectors.groupingBy(Data::getApiID));

    }

    static void missingSeries(File file) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        Result result = new Result();
        br.lines()
          .forEach(line -> {
              if (result.previousData == null) {
                  result.previousData = parse(line);
                  return;
              }

              Data current = parse(line);
              if (!result.previousData.apiID.equals(current.apiID)) {

                  result.previousData = current;
                  return;
              }

              result.currentData = current;
              if (result.previousData.date.getTime() + 60 * 1000 < result.currentData.date.getTime()) {
                  notifyMissingSeries(result);
              }
              result.previousData = current;
          });
    }

    private static class Result {

        Data previousData;
        Data currentData;
    }

    private static Date dateFormat(String input) throws Exception{
       return df.parse(input);
    }

    static Data parse(String csvInput)  {

        String[] inputCols = csvInput.split(" ");
        assert (inputCols.length == 4);
        try {
            return new Data(dateFormat(inputCols[0] + " " + inputCols[1]), inputCols[2],
                            Integer.parseInt(inputCols[3]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static void notifyMissingSeries(Result result) {

        long from = result.previousData.date.getTime();
        long to = result.currentData.date.getTime();
        if (from < to) {
            System.out.println(result.previousData.apiID + "," + new Date(from) + "," + new Date(to));
        }
    }

    private static class Data {

        Date date;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) { this.date = date; }

        public String getApiID() {return apiID;}

        public void setApiID(String apiID) {this.apiID = apiID;}

        public int getNumberOfReq() {return numberOfReq;}

        public void setNumberOfReq(int numberOfReq) {this.numberOfReq = numberOfReq;}

        String apiID;
        int numberOfReq;
        public Data(Date date, String apiID, int numberOfReq) {

            this.date = date;
            this.apiID = apiID;
            this.numberOfReq = numberOfReq;
        }
    }
}