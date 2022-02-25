package tw.org.iii.cma.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.RankBy;

@RestController
@RequestMapping("/foodmap")
public class GeoController {
	@GetMapping(path = "/map/{querys}")
	public ResponseEntity<?> getDetail(@Value("${gmaps.api.key}") String apiKey, 
										@PathVariable String query) {
		GeoApiContext geoApiContext = new GeoApiContext.Builder()
				.apiKey(apiKey)
				.build();
		
		//textSearchQuery
		PlacesSearchResponse placesSearchResponse = null;
		try {
			placesSearchResponse = PlacesApi.textSearchQuery(geoApiContext, query).await();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Geocoding for placeId,latitude,longitude
		GeocodingResult[] geocodingResults = null;
		try {
			geocodingResults = GeocodingApi.newRequest(geoApiContext)
					.place(placesSearchResponse.results[0].placeId)
					.await();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final String placeId = geocodingResults[0].placeId;
		final double latitude = geocodingResults[0].geometry.location.lat;
		final double longitude = geocodingResults[0].geometry.location.lng;
		
		//PlaceDetails
//		PlaceDetails placeDetails = null;
//		try {
//			placeDetails = PlacesApi.placeDetails(geoApiContext, placeId)
//					.fields(
//							PlaceDetailsRequest.FieldMask.PLACE_ID,
//							PlaceDetailsRequest.FieldMask.NAME,
//							PlaceDetailsRequest.FieldMask.TYPES)
//					.await();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return ResponseEntity.ok(placeDetails);
		
		//PlacesSearch
		LatLng location = new LatLng(latitude, longitude);
		PlacesSearchResponse nearbySearch = null;
		try {
			nearbySearch = PlacesApi.nearbySearchQuery(geoApiContext, location)
					.radius(500)
					.rankby(RankBy.PROMINENCE)
					.openNow(true)
					.type(PlaceType.RESTAURANT)
					.type(PlaceType.MEAL_TAKEAWAY)
					.type(PlaceType.MEAL_DELIVERY)
					.type(PlaceType.BAKERY)
					.await();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(nearbySearch);
		
		// placeAutocomplete不吃中文??
//		AutocompletePrediction[] predictions = null;
//		try {
//			SessionToken sessionToken= new SessionToken();
//			String encodeQuery = URLEncoder.encode(query, "UTF-8");
//			predictions = PlacesApi.placeAutocomplete(geoApiContext, encodeQuery, sessionToken)
//					.radius(500)
//					.types(PlaceAutocompleteType.GEOCODE)
//					.await();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return ResponseEntity.ok(predictions);
	}

}
