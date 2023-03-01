import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class WeatherApp {
    // Copy your API-KEY here
    public final static String apiKey = "7e8289ce96cd4b62906185025232702";
    // TODO: Write main function
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String city = input.nextLine();
        String finalData = getWeatherData(city);
       double result1 = getTemperature(finalData);
       int result2 = getHumidity(finalData);
       double result3 = getWindSpeed(finalData);
       String result4 = getWindDirection(finalData);
       System.out.println(result1);
       System.out.println(result2);
       System.out.println(result3);
        System.out.println(result4);




    }

    /**
     * Retrieves weather data for the specified city.
     *
     * @param city the name of the city for which weather data should be retrieved
     * @return a string representation of the weather data, or null if an error occurred
     */
    public static String getWeatherData(String city) {
        try {
            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Write getTemperature function returns celsius temperature of city by given json string
    public static double getTemperature(String weatherJson){

        double answer = 0.0 ;
        JSONObject jobj= new JSONObject(weatherJson);
        answer = jobj.getJSONObject("current").getDouble("temp_c");
        return  answer;
    }

    // TODO: Write getHumidity function returns humidity percentage of city by given json string
    public static int getHumidity(String weatherJson){

        int answer = 0 ;
        JSONObject jobj= new JSONObject(weatherJson);
        answer = jobj.getJSONObject("current").getInt("humidity");
        return  answer;
    }

    public static double getWindSpeed(String weatherJson){

        double answer = 0.0 ;
        JSONObject jobj= new JSONObject(weatherJson);
        answer = jobj.getJSONObject("current").getDouble("wind_kph");
        return  answer;
    }

    public static String getWindDirection(String weatherJson){

        String answer ;
        JSONObject jobj= new JSONObject(weatherJson);
        answer = jobj.getJSONObject("current").getString("wind_dir");
        return  answer;
    }


}

