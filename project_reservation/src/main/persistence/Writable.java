package persistence;

import org.json.JSONObject;


// note: the design of Json Reader and Writer structure is referred to
// JsonSerialzation Demo.
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
