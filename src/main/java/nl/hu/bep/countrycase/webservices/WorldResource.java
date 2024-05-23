package nl.hu.bep.countrycase.webservices;

import nl.hu.bep.countrycase.model.World;
import nl.hu.bep.countrycase.model.Country;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
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
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Country country : world.getAllCountries()) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("code", country.getCode())
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
            jab.add(job);
        }
        return Response.ok(jab.build().toString()).build();
    }

    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountryByCode(@PathParam("code") String code) {
        Country country = world.getCountryByCode(code);
        if (country == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("code", country.getCode())
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
        return Response.ok(job.build().toString()).build();
    }

    @GET
    @Path("/largestsurfaces")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLargestSurfaces() {
        List<Country> countries = world.get10LargestSurfaces();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Country country : countries) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("code", country.getCode())
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
            jab.add(job);
        }
        return Response.ok(jab.build().toString()).build();
    }

    @GET
    @Path("/largestpopulations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLargestPopulations() {
        List<Country> countries = world.get10LargestPopulations();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Country country : countries) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("code", country.getCode())
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
            jab.add(job);
        }
        return Response.ok(jab.build().toString()).build();
    }
}
