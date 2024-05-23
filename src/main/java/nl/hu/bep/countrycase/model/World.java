package nl.hu.bep.countrycase.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class World {
	private List<Country> allCountries = new ArrayList<>();

	private static World myWorld = new World();
	public static World getWorld() {
		return myWorld;
	}

	public static void setWorld(World world) {
		myWorld = world;
	}

	private World() {
		allCountries.add(new Country("CN", "CHN", "China", "Beijing", "Asia", "Eastern Asia", 9572900.00, 1277558000, "People'sRepublic", 39.55, 116.23));
		allCountries.add(new Country("IN", "IND", "India", "New Delhi", "Asia", "Southern and Central Asia", 3287263.00, 1013662000, "Federal Republic", 28.368, 77.125));
		allCountries.add(new Country("US", "USA", "United States", "Washington DC", "North America", "North America", 9363520.00, 278357000, "Federal Republic", 38.53, -77.01));
		allCountries.add(new Country("ID", "IDN", "Indonesia", "Jakarta", "Asia", "Southeast Asia", 1904569.00, 212107000, "Republic", -6.105, 106.497));
		allCountries.add(new Country("BR", "BRA", "Brazil", "Brasilia", "South America", "South America", 8547403.00, 170115000, "Federal Republic", -15.47, -47.52));
		allCountries.add(new Country("PK", "PAK", "Pakistan", "Islamabad", "Asia", "Southern and Central Asia", 796095.00, 156483000, "Republic", 33.40, 73.10));
		allCountries.add(new Country("RU", "RUS", "Russian Federation", "Moscow", "Europe", "Eastern Europe", 17075400.00, 146934000, "Federal Republic", 55.45, 37.37));
		allCountries.add(new Country("BD", "BGD", "Bangladesh", "Dhaka", "Asia", "Southern and Central Asia", 143998.00, 129155000, "Republic", 23.42, 90.21));
		allCountries.add(new Country("JP", "JPN", "Japan", "Tokyo", "Asia", "Eastern Asia", 377829.00, 126714000, "Constitutional Monarchy", 35.41, 139.46));
		allCountries.add(new Country("NG", "NGA", "Nigeria", "Abuja", "Africa", "Western Africa", 923768.00, 111506000, "Federal Republic", 9.4, 7.29));
		allCountries.add(new Country("AQ", "ATA", "Antarctica", "Antarctica", "Antarctica", "Antarctica", 13120000.00, 0, "Co-administrated", -65.9692891,-53.0795886));
		allCountries.add(new Country("NL", "NLD", "The Netherlands", "Amsterdam", "Europe", "West-Europe", 41543, 17100475, "Constitutional Monarchy, Unitary parliamentary", 52.22, 4.53));
		allCountries.add(new Country("CA", "CAN", "Canada", "Ottawa", "North America", "North America", 9970610.00, 31147000, "Constitutional Monarchy, Federation", 45.24, -75.40));
		allCountries.add(new Country("AU", "AUS", "Australia", "Canberra", "Oceania", "Australia and New Zealand", 7741220.00, 18886000, "Constitutional Monarchy, Federation", -35.1848, 149.747));
		allCountries.add(new Country("AR", "ARG", "Argentina", "Buenos Aires", "South America", "South America", 2780400.00, 37032000, "Federal Republic", -34.36, -58.23));
		allCountries.add(new Country("KZ", "KAZ", "Kazakstan", "Astana", "Asia", "Southern and Central Asia", 2724900.00, 16223000, "Republic", 51.10, 71.26));
	}

	public List<Country> getAllCountries() {
		return allCountries;
	}

	/**
	 * Gets 10 largest populations.
	 * Sorts the list of countries by population, then returns the first 10.
	 * Uses the {@link Collections#sort(List, Comparator)} method with a custom Comparator
	 * @return the 10 largest populations
	 */
	public List<Country> get10LargestPopulations() {
		Collections.sort(allCountries, new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				return c2.getPopulation() - c1.getPopulation();
			}
		});
		return allCountries.subList(0, 10);
	}

	/**
	 * Gets 10 largest surfaces.
	 * Sorts the list of countries by surface, then returns the first 10.
	 * Uses the {@link List#sort(Comparator) } method with a lambda expression
	 * @return the 10 largest surfaces
	 */
	public List<Country> get10LargestSurfaces() {
		allCountries.sort((c1, c2) -> (int) (c2.getSurface() - c1.getSurface()));
		return allCountries.subList(0, 10);
	}

	/**
	 * Gets country by code.
	 * TODO: Maak deze methode af: return het Country-object voor
	 * de gevraagde code, of return null als het een niet bekende code is!
	 * @param code the code
	 * @return the country by code or null
	 */
	public Country getCountryByCode(String code) {
		for (Country country : allCountries) {
			if (country.getCode().equalsIgnoreCase(code)) {
				return country;
			}
		}
		return null;
	}

	/**
	 * Adds a country to the list if a country with the same code doesn't exist.
	 * @param code the country code
	 * @param iso3 the iso3 code
	 * @param name the name of the country
	 * @param capital the capital city
	 * @param continent the continent
	 * @param region the region
	 * @param surface the surface area
	 * @param population the population
	 * @param government the government type
	 * @param latitude the latitude
	 * @param longitude the longitude
	 * @return true if the country was added, false if a country with the same code already exists
	 */
	public boolean addCountry(String code, String iso3, String name, String capital, String continent, String region, double surface, int population, String government, double latitude, double longitude) {
		if (getCountryByCode(code) != null) {
			return false; // Country with the same code already exists
		}
		Country newCountry = new Country(code, iso3, name, capital, continent, region, surface, population, government, latitude, longitude);
		allCountries.add(newCountry);
		return true;
	}

}
