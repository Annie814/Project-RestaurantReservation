package persistence;

import exception.AddCustomerInvalidException;
import model.Customer;
import model.DayReservation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

// Represents a reader that reads reservations from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads dayreservation from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DayReservation read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDayreservation(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses eayreservation from JSON object and returns it
    private DayReservation parseDayreservation(JSONObject jsonObject) {
        String thedate = jsonObject.getString("date");
        LocalDate date = LocalDate.parse(thedate);
        DayReservation res = new DayReservation(date);
        addCustomer(res, jsonObject);
        return res;
    }

    // MODIFIES: res
    // EFFECTS: parses customers from JSON object and adds them to dayreservation
    private void addCustomer(DayReservation res, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("customers");
        for (Object json : jsonArray) {
            JSONObject nextcus = (JSONObject) json;
            addEachCus(res, nextcus);
        }
    }


    // MODIFIES: res
    // EFFECTS: parses customer from JSON object and adds it to the customer list in dayreservation
    private void addEachCus(DayReservation res, JSONObject jsonObject) {
        String name = jsonObject.getString("customername");
        String phonenum = jsonObject.getString("phonenum");
        Double time = jsonObject.getDouble("bookedtime");
        Integer size = jsonObject.getInt("partysize");

        Customer customer = new Customer(name, phonenum, time, size);
        try {
            res.addCustomertoDay(customer);
        } catch (AddCustomerInvalidException datae) {
            System.out.println("jason ERROR!");
        }
    }
}
