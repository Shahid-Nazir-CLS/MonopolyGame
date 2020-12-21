import java.util.HashMap;
import java.util.Map;

class Properties{

	Map<String, Map<String, String>> locMap = new HashMap<String, Map<String, String>>();

	
	public Properties(){

		// create properties one by one

		// ist
		locMap.put("0, 0", new HashMap<String, String>());
		locMap.get("0, 0").put("name", "First Card");
		locMap.get("0, 0").put("cost", "0");
		locMap.get("0, 0").put("rent", "0");
		locMap.get("0, 0").put("reward", "200");
		locMap.get("0, 0").put("owner", "0");

		// 2nd
		locMap.put("-1, 0", new HashMap<String, String>());
		locMap.get("-1, 0").put("name", "Mediterranean Avenue");
		locMap.get("-1, 0").put("cost", "60");
		locMap.get("-1, 0").put("rent", "2");
		locMap.get("-1, 0").put("reward", "0");
		locMap.get("-1, 0").put("owner", "0");

		// 3rd
		locMap.put("-2, 0", new HashMap<String, String>());
		locMap.get("-2, 0").put("name", "Community Chest");
		locMap.get("-2, 0").put("cost", "0");
		locMap.get("-2, 0").put("rent", "0");
		locMap.get("-2, 0").put("reward", "100");
		locMap.get("-2, 0").put("owner", "0");

		// 4th
		locMap.put("-3, 0", new HashMap<String, String>());
		locMap.get("-3, 0").put("name", "Baltic Avenue");
		locMap.get("-3, 0").put("cost", "60");
		locMap.get("-3, 0").put("rent", "4");
		locMap.get("-3, 0").put("reward", "0");
		locMap.get("-3, 0").put("owner", "0");

		// 5th
		locMap.put("-4, 0", new HashMap<String, String>());
		locMap.get("-4, 0").put("name", "Income Tax");
		locMap.get("-4, 0").put("cost", "0");
		locMap.get("-4, 0").put("rent", "200");
		locMap.get("-4, 0").put("reward", "0");
		locMap.get("-4, 0").put("owner", "0");

		// 6th
		locMap.put("-5, 0", new HashMap<String, String>());
		locMap.get("-5, 0").put("name", "Reading Rail Road");
		locMap.get("-5, 0").put("cost", "200");
		locMap.get("-5, 0").put("rent", "16");
		locMap.get("-5, 0").put("reward", "0");
		locMap.get("-5, 0").put("owner", "0");

		// 7th
		locMap.put("-6, 0", new HashMap<String, String>());
		locMap.get("-6, 0").put("name", "Oriental Avenue");
		locMap.get("-6, 0").put("cost", "100");
		locMap.get("-6, 0").put("rent", "6");
		locMap.get("-6, 0").put("reward", "0");
		locMap.get("-6, 0").put("owner", "0");

		// 8th
		locMap.put("-7, 0", new HashMap<String, String>());
		locMap.get("-7, 0").put("name", "Chance");
		locMap.get("-7, 0").put("cost", "0");
		locMap.get("-7, 0").put("rent", "0");
		locMap.get("-7, 0").put("reward", "100");
		locMap.get("-7, 0").put("owner", "0");

		// 9th
		locMap.put("-8, 0", new HashMap<String, String>());
		locMap.get("-8, 0").put("name", "Vermont Avenue");
		locMap.get("-8, 0").put("cost", "100");
		locMap.get("-8, 0").put("rent", "6");
		locMap.get("-8, 0").put("reward", "0");
		locMap.get("-8, 0").put("owner", "0");

		// 10th
		locMap.put("-9, 0", new HashMap<String, String>());
		locMap.get("-9, 0").put("name", "Connecticut Avenue");
		locMap.get("-9, 0").put("cost", "120");
		locMap.get("-9, 0").put("rent", "8");
		locMap.get("-9, 0").put("reward", "0");
		locMap.get("-9, 0").put("owner", "0");

		// 11th
		locMap.put("-10, 0", new HashMap<String, String>());
		locMap.get("-10, 0").put("name", "Jail");
		locMap.get("-10, 0").put("cost", "0");
		locMap.get("-10, 0").put("rent", "0");
		locMap.get("-10, 0").put("reward", "0");
		locMap.get("-10, 0").put("owner", "0");

		// 12th
		locMap.put("-10, -1", new HashMap<String, String>());
		locMap.get("-10, -1").put("name", "St. Charles Place");
		locMap.get("-10, -1").put("cost", "140");
		locMap.get("-10, -1").put("rent", "10");
		locMap.get("-10, -1").put("reward", "0");
		locMap.get("-10, -1").put("owner", "0");

		// 13th
		locMap.put("-10, -2", new HashMap<String, String>());
		locMap.get("-10, -2").put("name", "Electric Company");
		locMap.get("-10, -2").put("cost", "150");
		locMap.get("-10, -2").put("rent", "11");
		locMap.get("-10, -2").put("reward", "0");
		locMap.get("-10, -2").put("owner", "0");

		// 14th
		locMap.put("-10, -3", new HashMap<String, String>());
		locMap.get("-10, -3").put("name", "States Avenue");
		locMap.get("-10, -3").put("cost", "140");
		locMap.get("-10, -3").put("rent", "10");
		locMap.get("-10, -3").put("reward", "0");
		locMap.get("-10, -3").put("owner", "0");

		// 15th
		locMap.put("-10, -4", new HashMap<String, String>());
		locMap.get("-10, -4").put("name", "Virginia Avenue");
		locMap.get("-10, -4").put("cost", "160");
		locMap.get("-10, -4").put("rent", "12");
		locMap.get("-10, -4").put("reward", "0");
		locMap.get("-10, -4").put("owner", "0");

		// 16th
		locMap.put("-10, -5", new HashMap<String, String>());
		locMap.get("-10, -5").put("name", "Pennsylvania RailRoad");
		locMap.get("-10, -5").put("cost", "200");
		locMap.get("-10, -5").put("rent", "16");
		locMap.get("-10, -5").put("reward", "0");
		locMap.get("-10, -5").put("owner", "0");

		// 17th
		locMap.put("-10, -6", new HashMap<String, String>());
		locMap.get("-10, -6").put("name", "St. James Place");
		locMap.get("-10, -6").put("cost", "180");
		locMap.get("-10, -6").put("rent", "14");
		locMap.get("-10, -6").put("reward", "0");
		locMap.get("-10, -6").put("owner", "0");

		// 18th
		locMap.put("-10, -7", new HashMap<String, String>());
		locMap.get("-10, -7").put("name", "Community Chest");
		locMap.get("-10, -7").put("cost", "0");
		locMap.get("-10, -7").put("rent", "0");
		locMap.get("-10, -7").put("reward", "100");
		locMap.get("-10, -7").put("owner", "0");

		// 19th
		locMap.put("-10, -8", new HashMap<String, String>());
		locMap.get("-10, -8").put("name", "Tennessee Avenue");
		locMap.get("-10, -8").put("cost", "180");
		locMap.get("-10, -8").put("rent", "14");
		locMap.get("-10, -8").put("reward", "0");
		locMap.get("-10, -8").put("owner", "0");

		// 20th
		locMap.put("-10, -9", new HashMap<String, String>());
		locMap.get("-10, -9").put("name", "New York Avenue");
		locMap.get("-10, -9").put("cost", "200");
		locMap.get("-10, -9").put("rent", "16");
		locMap.get("-10, -9").put("reward", "0");
		locMap.get("-10, -9").put("owner", "0");

		// 21th
		locMap.put("-10, -10", new HashMap<String, String>());
		locMap.get("-10, -10").put("name", "Free Parking");
		locMap.get("-10, -10").put("cost", "0");
		locMap.get("-10, -10").put("rent", "0");
		locMap.get("-10, -10").put("reward", "0");
		locMap.get("-10, -10").put("owner", "0");

		// 22th
		locMap.put("-9, -10", new HashMap<String, String>());
		locMap.get("-9, -10").put("name", "Kentucky Avenue");
		locMap.get("-9, -10").put("cost", "220");
		locMap.get("-9, -10").put("rent", "18");
		locMap.get("-9, -10").put("reward", "0");
		locMap.get("-9, -10").put("owner", "0");

		// 23th
		locMap.put("-8, -10", new HashMap<String, String>());
		locMap.get("-8, -10").put("name", "Chance");
		locMap.get("-8, -10").put("cost", "0");
		locMap.get("-8, -10").put("rent", "0");
		locMap.get("-8, -10").put("reward", "100");
		locMap.get("-8, -10").put("owner", "0");

		// 24th
		locMap.put("-7, -10", new HashMap<String, String>());
		locMap.get("-7, -10").put("name", "Indiana Avenue");
		locMap.get("-7, -10").put("cost", "220");
		locMap.get("-7, -10").put("rent", "18");
		locMap.get("-7, -10").put("reward", "0");
		locMap.get("-7, -10").put("owner", "0");

		// 25th
		locMap.put("-6, -10", new HashMap<String, String>());
		locMap.get("-6, -10").put("name", "Illinois Avenue");
		locMap.get("-6, -10").put("cost", "240");
		locMap.get("-6, -10").put("rent", "20");
		locMap.get("-6, -10").put("reward", "0");
		locMap.get("-6, -10").put("owner", "0");

		// 26th
		locMap.put("-5, -10", new HashMap<String, String>());
		locMap.get("-5, -10").put("name", "B & O RailRoad");
		locMap.get("-5, -10").put("cost", "200");
		locMap.get("-5, -10").put("rent", "16");
		locMap.get("-5, -10").put("reward", "0");
		locMap.get("-5, -10").put("owner", "0");

		// 27th
		locMap.put("-4, -10", new HashMap<String, String>());
		locMap.get("-4, -10").put("name", "Atlantic Avenue");
		locMap.get("-4, -10").put("cost", "260");
		locMap.get("-4, -10").put("rent", "22");
		locMap.get("-4, -10").put("reward", "0");
		locMap.get("-4, -10").put("owner", "0");

		// 28th
		locMap.put("-3, -10", new HashMap<String, String>());
		locMap.get("-3, -10").put("name", "Ventnor Avenue");
		locMap.get("-3, -10").put("cost", "260");
		locMap.get("-3, -10").put("rent", "22");
		locMap.get("-3, -10").put("reward", "0");
		locMap.get("-3, -10").put("owner", "0");

		// 29th
		locMap.put("-2, -10", new HashMap<String, String>());
		locMap.get("-2, -10").put("name", "Water Works");
		locMap.get("-2, -10").put("cost", "150");
		locMap.get("-2, -10").put("rent", "11");
		locMap.get("-2, -10").put("reward", "0");
		locMap.get("-2, -10").put("owner", "0");

		// 30th
		locMap.put("-1, -10", new HashMap<String, String>());
		locMap.get("-1, -10").put("name", "Marvin Gardens");
		locMap.get("-1, -10").put("cost", "280");
		locMap.get("-1, -10").put("rent", "24");
		locMap.get("-1, -10").put("reward", "0");
		locMap.get("-1, -10").put("owner", "0");

		// 31th
		locMap.put("0, -10", new HashMap<String, String>());
		locMap.get("0, -10").put("name", "GoJail");
		locMap.get("0, -10").put("cost", "0");
		locMap.get("0, -10").put("rent", "0");
		locMap.get("0, -10").put("reward", "0");
		locMap.get("0, -10").put("owner", "0");

		// 32th
		locMap.put("0, -9", new HashMap<String, String>());
		locMap.get("0, -9").put("name", "Pacific Avenue");
		locMap.get("0, -9").put("cost", "300");
		locMap.get("0, -9").put("rent", "26");
		locMap.get("0, -9").put("reward", "0");
		locMap.get("0, -9").put("owner", "0");

		// 33th
		locMap.put("0, -8", new HashMap<String, String>());
		locMap.get("0, -8").put("name", "North Carolina Avenue");
		locMap.get("0, -8").put("cost", "300");
		locMap.get("0, -8").put("rent", "26");
		locMap.get("0, -8").put("reward", "0");
		locMap.get("0, -8").put("owner", "0");

		// 34th
		locMap.put("0, -7", new HashMap<String, String>());
		locMap.get("0, -7").put("name", "Community Chest");
		locMap.get("0, -7").put("cost", "0");
		locMap.get("0, -7").put("rent", "0");
		locMap.get("0, -7").put("reward", "100");
		locMap.get("0, -7").put("owner", "0");

		// 35th
		locMap.put("0, -6", new HashMap<String, String>());
		locMap.get("0, -6").put("name", "Pennsylvania Avenue");
		locMap.get("0, -6").put("cost", "320");
		locMap.get("0, -6").put("rent", "28");
		locMap.get("0, -6").put("reward", "0");
		locMap.get("0, -6").put("owner", "0");

		// 36th
		locMap.put("0, -5", new HashMap<String, String>());
		locMap.get("0, -5").put("name", "Short Line");
		locMap.get("0, -5").put("cost", "200");
		locMap.get("0, -5").put("rent", "16");
		locMap.get("0, -5").put("reward", "0");
		locMap.get("0, -5").put("owner", "0");

		// 37th
		locMap.put("0, -4", new HashMap<String, String>());
		locMap.get("0, -4").put("name", "Chance");
		locMap.get("0, -4").put("cost", "0");
		locMap.get("0, -4").put("rent", "0");
		locMap.get("0, -4").put("reward", "100");
		locMap.get("0, -4").put("owner", "0");

		// 38th
		locMap.put("0, -3", new HashMap<String, String>());
		locMap.get("0, -3").put("name", "Park Place");
		locMap.get("0, -3").put("cost", "350");
		locMap.get("0, -3").put("rent", "31");
		locMap.get("0, -3").put("reward", "0");
		locMap.get("0, -3").put("owner", "0");

		// 39th
		locMap.put("0, -2", new HashMap<String, String>());
		locMap.get("0, -2").put("name", "Luxury Tax");
		locMap.get("0, -2").put("cost", "0");
		locMap.get("0, -2").put("rent", "100");
		locMap.get("0, -2").put("reward", "0");
		locMap.get("0, -2").put("owner", "0");

		// 40th
		locMap.put("0, -1", new HashMap<String, String>());
		locMap.get("0, -1").put("name", "Boardwalk");
		locMap.get("0, -1").put("cost", "400");
		locMap.get("0, -1").put("rent", "36");
		locMap.get("0, -1").put("reward", "0");
		locMap.get("0, -1").put("owner", "0");


	}
	

	public String getPropertyName(String locXAndLocY){	
    	return locMap.get(locXAndLocY).get("name");
	}

	public String getPropertyCost(String locXAndLocY){
		return locMap.get(locXAndLocY).get("cost");
	}

	public String getPropertyRent(String locXAndLocY){
		return locMap.get(locXAndLocY).get("rent");
	}

	public String getPropertyReward(String locXAndLocY){
		return locMap.get(locXAndLocY).get("reward");
	}

	public String getPropertyOwner(String locXAndLocY){
		return locMap.get(locXAndLocY).get("owner");
	}

	public void setPropertyOwner(String locXAndLocY, String playerNo){
		locMap.get(locXAndLocY).put("owner", playerNo);
	}

}