package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Restaurant {
	
	public static String parseString(String str) {
		return str.replace("\"", "\\\"").replace("/", " or ");
	}

	public static String jsonArrayToString(JSONArray array) {
		StringBuilder sb = new StringBuilder();
		try {
			for (int i = 0; i < array.length(); i++) {
				String obj = (String) array.get(i);
				sb.append(obj);
				if (i != array.length() - 1) {
					sb.append(",");
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static JSONArray stringToJSONArray(String str) {
		String[] strArray = str.split(",");
		JSONArray ret = null;
		try {
			ret = new JSONArray(strArray);
		} catch (JSONException e) {
			System.out.println("Error: stringToJSONArray "+str);
			e.printStackTrace();
		}
		return ret;
	}
	
	private String businessId;
	private String name;
	private String categories;
	private String city;
	private String state;
	private String fullAddress;
	private double stars;
	private double latitude;
	private double longitude;
	private String imageUrl;
	private String url;

	public Restaurant(JSONObject object) {
		try {
			if (object != null) {
				this.businessId = object.getString("id");
				JSONArray jsonArray = (JSONArray) object.get("categories");
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONArray subArray = jsonArray.getJSONArray(i);
					for (int j = 0; j < subArray.length(); j++) {
						list.add(parseString(subArray.getString(j)));
					}
				}
				this.categories = String.join(",", list);
				this.name = object.getString("name");
				this.imageUrl = object.getString("image_url");
				this.stars = object.getDouble("rating");
				JSONObject location = (JSONObject) object.get("location");
				JSONObject coordinate = (JSONObject) location.get("coordinate");
				this.latitude = coordinate.getDouble("latitude");
				this.longitude = coordinate.getDouble("longitude");
				this.city = location.getString("city");
				this.state = location.getString("state_code");
				this.fullAddress = jsonArrayToString((JSONArray) location
						.get("display_address"));
				this.url = object.getString("url");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Restaurant(String businessId, String name, String categories,
			String city, String state, double stars, String fullAddress,
			double latitude, double longitude, String imageUrl, String url) {
		this.businessId = businessId;
		this.categories = categories;
		this.name = name;
		this.city = city;
		this.state = state;
		this.stars = stars;
		this.fullAddress = fullAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.imageUrl = imageUrl;
		this.url = url;
	}
	
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("business_id", businessId);
			obj.put("name", name);
			obj.put("stars", stars);
			obj.put("latitude", latitude);
			obj.put("longitude", longitude);
			obj.put("full_address", fullAddress);
			obj.put("city", city);
			obj.put("state", state);
			obj.put("categories", stringToJSONArray(categories));
			obj.put("image_url", imageUrl);
			obj.put("url", url);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public String getBusinessId() {
		return this.businessId;
	}

	public String getName() {
		return this.name;
	}

	public String getCategories() {
		return this.categories;
	}

	public String getCity() {
		return this.city;
	}

	public String getState() {
		return this.state;
	}

	public String getFullAddress() {
		return this.fullAddress;
	}

	public double getStars() {
		return this.stars;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public String getUrl() {
		return this.url;
	}
}
