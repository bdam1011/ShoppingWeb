package tw.org.iii.cma.rest;

import java.io.IOException;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MapsTests {

	public static void main(String[] args) throws IOException {

		//
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyCh11t64J8khPfFTJghgerzQpmtWZaEv4k").build();
		GeocodingResult[] results = null;
		try {
			results = GeocodingApi.geocode(context, "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
		} catch (Exception e) {
			e.toString();
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(results[0].addressComponents));

		// Invoke .shutdown() after your application is done making requests
		context.shutdown();

		System.out.println("--------------------------------------------");
		
		//Find Place examples
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		Request request = new Request.Builder().url(
				"https://maps.googleapis.com/maps/api/place/findplacefromtext/json?"
				+ "input=Museum%20of%20Contemporary%20Art%20Australia&inputtype=textquery&fields=formatted_address%2Cname%2Crating%2Copening_hours%2Cgeometry&key=YOUR_API_KEY")
				.method("GET", null).build();
		Response response = client.newCall(request).execute();
		String result = response.body().string();
		System.out.println(result);

		// Place Details example
//		OkHttpClient client = new OkHttpClient().newBuilder().build();
//		Request request = new Request.Builder().url(
//				"https://maps.googleapis.com/maps/api/place/findplacefromtext/json?"
//				+ "input=Museum%20of%20Contemporary%20Art%20Australia&inputtype=textquery&fields=formatted_address%2Cname%2Crating%2Copening_hours%2Cgeometry&key=AIzaSyCh11t64J8khPfFTJghgerzQpmtWZaEv4k")
//				.method("GET", null).build();
//		Response response = client.newCall(request).execute();
//		String result = response.body().string();
//		System.out.println(result);

	}

}
