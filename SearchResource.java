
package com.skyscanner;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@Path("/search")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

    private final ObjectMapper mapper = new ObjectMapper();
    private final List<Item> cars;
    private final List<Item> hotels;

    public SearchResource() throws Exception {
        this.cars = loadList("/data/cars.json");
        this.hotels = loadList("/data/hotels.json");
    }

    private List<Item> loadList(String resourcePath) throws Exception {
        try (InputStream in = getClass().getResourceAsStream(resourcePath)) {
            if (in == null) return new ArrayList<>();
            String s = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            return mapper.readValue(s, new TypeReference<List<Item>>() {});
        }
    }

    public static class SearchRequest {
        public String city;
    }

    public static class Item {
        public String id;
        public String type;
        public String name;
        public String city;
        public double price;
    }

    @POST
    public Response search(SearchRequest req) {
        if (req == null || req.city == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("{"error":"missing city"}")
                .build();
        }
        String q = req.city.trim().toLowerCase();
        List<Item> results = new ArrayList<>();
        results.addAll(cars.stream().filter(i -> i.city != null && i.city.toLowerCase().equals(q)).collect(Collectors.toList()));
        results.addAll(hotels.stream().filter(i -> i.city != null && i.city.toLowerCase().equals(q)).collect(Collectors.toList()));
        return Response.ok(results).build();
    }
}
