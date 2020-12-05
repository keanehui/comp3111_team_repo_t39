package comp3111.popnames;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.*;
import edu.duke.*;

public class AnalyzeNames {

	public static CSVParser getFileParser(int year) {
     FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
     return fr.getCSVParser(false);
	}
 
	
	public static String getSummary(int year) {
		String oReport = "";	
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int totalNames = 0;
		int uniqueBoys = 0;
		int uniqueGirls = 0;
		
		oReport = String.format("Summary (Year of %d):\n", year);
		for (CSVRecord rec : getFileParser(year)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			totalNames += 1;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				uniqueBoys++;
			}
			else {
				totalGirls += numBorn;
				uniqueGirls++;
			}
		}
		
		oReport += String.format("Total Births = %,d\n", totalBirths);
		oReport += String.format("***Baby Girls = %,d\n", totalGirls);
		oReport += String.format("***Baby Boys = %,d\n", totalBoys);
		
		oReport += String.format("Total Number of Unique Names = %,d\n", totalNames);
		oReport += String.format("***Unique Names (baby girls) = %,d\n", uniqueGirls);
		oReport += String.format("***Unique Names (baby boys) = %,d\n", uniqueBoys);
		
		return oReport;
	}
	
	 /**
	  *
	  * @param year The year we want to find the rank of the name from
	  * @param name The name we want to find the rank of
	  * @param gender The gender of the name we want to find the rank of
	  * @return rank If the name is found in the given year
	  */
	 public static int getRank(int year, String name, String gender) {
	     boolean found = false;
	     int oRank = 0;
	 	int rank = 1;
	     for (CSVRecord rec : getFileParser(year)) {
	         // Increment rank if gender matches param
	         if (rec.get(1).equals(gender)) {
	             // Return rank if name matches param
	             if (rec.get(0).equals(name)) {
	             	found = true;
	             	oRank = rank;
	             	break;
	             }
	             rank++;
	         }
	     }
	     if (found)
	     	return oRank;
	     else
	     	return -1;
	 }
	 
	 /**
	  * 
	  * @param year The year we want to find the name based on the given rank from. 
	  * @param rank The rank we use to find the name in the given year.
	  * @param gender The gender of the name we want to find based on the given rank.
	  * @return Name If the rank with gender is valid in the year.
	  */
	 public static String getName(int year, int rank, String gender) {
	 	boolean found = false;
	     String oName = "";
	     int currentRank = 0;
	     
	     // For every name entry in the CSV file
	     for (CSVRecord rec : getFileParser(year)) {
	         // Get its rank if gender matches param
	         if (rec.get(1).equals(gender)) {
	             // Get the name whose rank matches param
	         	currentRank++;
	            if (currentRank == rank) {
	             	found = true;
	             	oName = rec.get(0);
	                break;
	            }
	         }
	     }     
	     if (found)
	     	return oName;
	     else
	     	return "information on the name at the specified rank is not available";
	 }
	 
	 // task 1
	 /**
	  * 
	  * @param hashmap HashMap where each entry contains Name and its frequency 
	  * @return name The name with the maximum frequency in the HashMap
	  */
	 public static String getMaxFromHashMap(HashMap<String, Integer> hashmap) { 
		 String result = "";
		 Integer currentMaxFreq = -1;
		 for (Map.Entry<String, Integer> entry : hashmap.entrySet()) {
			 if (entry.getValue() > currentMaxFreq) {
				 currentMaxFreq = entry.getValue();
				 result = entry.getKey();
			 }
		 }
		 return result;
	 }
	 
	 /**
	  * 
	  * @param topN Number of result. 
	  * @param fromYear The starting year we are interested in. 
	  * @param toYear The ending year we are interested in to find the top names. 
	  * @param gender The gender of the names we are interested in. 
	  * @return HashMap with entries containing top names and their frequencies
	  */
	 public static HashMap<String, Integer> getTopNNameWithInYears(int topN, int fromYear, int toYear, String gender) {
		 HashMap<String, Integer> result = new HashMap<String, Integer>();
		 HashMap<String, Integer> name_freq = new HashMap<String, Integer>();
		 for (int year = fromYear; year <= toYear; ++year) { // put all valid entries in map
			 for (CSVRecord rec : getFileParser(year)) {
		         if (rec.get(1).equals(gender)) {
		        	 name_freq.put(rec.get(0), Integer.parseInt(rec.get(2)));
		         }
		     }
		 }
		 String currentName = "";
		 for (int i = 0; i < topN; ++i) { // get max name, add to result, remove it, do 5 times
			 currentName = getMaxFromHashMap(name_freq);
			 result.put(currentName, name_freq.get(currentName));
			 name_freq.remove(currentName);
		 }
		 
		 return result;
	 }
	 
 
}