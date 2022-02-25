package tw.org.iii.cma.rest;

import java.net.URI;

import org.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class ProductsRestApiTests {
	private static final String REST_API_URL = "http://localhost:8080/rest/api";
	private RestTemplate template = new RestTemplate();

//	@Test
	public void testSelects() {
		URI uri = URI.create(REST_API_URL + "/products");
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		try {
			ResponseEntity<String> response = template.exchange(request, String.class);
			if (HttpStatus.OK.equals(response.getStatusCode())) {
				if (response.hasBody()) {
					String body = response.getBody();
					System.out.println(body);
				} else {
					System.out.println("no body data");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void testSelect() {
		URI uri = URI.create(REST_API_URL + "/products/50");
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		try {
			ResponseEntity<String> response = template.exchange(request, String.class);
			if (HttpStatus.OK.equals(response.getStatusCode())) {
				if (response.hasBody()) {
					String body = response.getBody();
					System.out.println(body);
				} else {
					System.out.println("no body data");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void testInsert() {
		JSONObject insert = new JSONObject();
		insert.put("id", 200);
		insert.put("name", "demo");
		insert.put("price", 13.57);
		insert.put("make", "2021-12-26");
		insert.put("expire", 24);

		URI uri = URI.create(REST_API_URL + "/products");
		RequestEntity<String> request = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).body(insert.toString());
		try {
			ResponseEntity<String> response = template.exchange(request, String.class);
			if (HttpStatus.CREATED.equals(response.getStatusCode())) {
				URI location = response.getHeaders().getLocation();
				System.out.println("location=" + location);

				if (response.hasBody()) {
					String body = response.getBody();
					System.out.println(body);
				} else {
					System.out.println("no body data");
				}
			} else if (HttpStatus.NO_CONTENT.equals(response.getStatusCode())) {
				System.out.println("no content");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void testUpdate() {
		JSONObject update = new JSONObject();
		update.put("id", 200);
		update.put("name", "not a demo");
		update.put("price", 24.68);
		update.put("make", "2021-01-01");
		update.put("expire", 35);

		URI uri = URI.create(REST_API_URL + "/products/200");
		RequestEntity<String> request = RequestEntity.put(uri).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).body(update.toString());
		try {
			ResponseEntity<String> response = template.exchange(request, String.class);
			if (HttpStatus.OK.equals(response.getStatusCode())) {
				if (response.hasBody()) {
					String body = response.getBody();
					System.out.println(body);
				} else {
					System.out.println("no body data");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void testDelete() {
		URI uri = URI.create(REST_API_URL + "/products/200");
		RequestEntity<Void> request = RequestEntity.delete(uri).build();
		try {
			ResponseEntity<String> response = template.exchange(request, String.class);
			if (HttpStatus.NO_CONTENT.equals(response.getStatusCode())) {
				System.out.println("delete success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
