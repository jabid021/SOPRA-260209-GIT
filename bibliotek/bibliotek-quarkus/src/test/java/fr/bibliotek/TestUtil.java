package fr.bibliotek;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
    private TestUtil() { }

    public static String toJson(Object value) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(value);
        }

        catch (Exception ex) {
            return "";
        }
    }
}
