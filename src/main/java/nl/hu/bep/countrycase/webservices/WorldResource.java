package nl.hu.bep.countrycase.webservices;

import nl.hu.bep.countrycase.model.World;
import nl.hu.bep.countrycase.model.Country;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.StringReader;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/countries")
public class WorldResource {
    private World world = World.getWorld();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCountries() {
        return Response.ok(world.getAllCountries()).build();
    }

    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountryByCode(@PathParam("code") String code) {
        Country country = world.getCountryByCode(code);
        if (country == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(buildCountryObject(country)).build();
    }

    @GET
    @Path("/largestsurfaces")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLargestSurfaces() {
        return Response.ok(world.get10LargestSurfaces()).build();
    }

    @GET
    @Path("/largestpopulations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLargestPopulations() {
        return Response.ok(world.get10LargestPopulations()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCountry(String jsonBody) {
        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonBody))) {
            JsonObject jsonObject = jsonReader.readObject();

            String code = jsonObject.getString("code");
            String iso3 = jsonObject.getString("iso3");
            String name = jsonObject.getString("name");
            String capital = jsonObject.getString("capital");
            String continent = jsonObject.getString("continent");
            String region = jsonObject.getString("region");
            double surface = Double.parseDouble(jsonObject.getString("surface"));
            int population = Integer.parseInt(jsonObject.getString("population"));
            double latitude = Double.parseDouble(jsonObject.getString("latitude"));
            double longitude = Double.parseDouble(jsonObject.getString("longitude"));
            String government = jsonObject.getString("government");

            boolean added = world.addCountry(code, iso3, name, capital, continent, region, surface, population, government, latitude, longitude);

            JsonObjectBuilder responseBuilder = Json.createObjectBuilder();
            if (added) {
                responseBuilder.add("message", "Country added successfully.");
                return Response.ok(responseBuilder.build().toString()).build();
            } else {
                responseBuilder.add("message", "Country with the same code already exists.");
                return Response.status(Response.Status.CONFLICT).entity(responseBuilder.build().toString()).build();
            }
        } catch (NumberFormatException e) {
            JsonObjectBuilder responseBuilder = Json.createObjectBuilder();
            responseBuilder.add("message", "Invalid number format: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(responseBuilder.build().toString()).build();
        } catch (Exception e) {
            JsonObjectBuilder responseBuilder = Json.createObjectBuilder();
            responseBuilder.add("message", "Failed to add country: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(responseBuilder.build().toString()).build();
        }
    }

    private JsonArrayBuilder buildCountryArray(List<Country> countries) {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Country country : countries) {
            jab.add(buildCountryObject(country));
        }
        return jab;
    }

    private JsonObjectBuilder buildCountryObject(Country country) {
        return Json.createObjectBuilder()
                .add("code", country.getCode())
                .add("iso3", country.getIso3())
                .add("name", country.getName())
                .add("continent", country.getContinent())
                .add("capital", country.getCapital())
                .add("region", country.getRegion())
                .add("surface", country.getSurface())
                .add("population", country.getPopulation())
                .add("government", country.getGovernment())
                .add("lat", country.getLatitude())
                .add("lng", country.getLongitude());
    }
}
