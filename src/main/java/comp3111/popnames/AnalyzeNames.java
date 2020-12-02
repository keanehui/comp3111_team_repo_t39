package comp3111.popnames;

import java.util.ArrayList;
import java.util.LinkedHashMap;

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
	 
	 
	 public static LinkedHashMap<String, RankProperties> getTrend(int startYear, int endYear, String gender, int topN) {
		 if (startYear == 0 || endYear == 0) {
			 return null;
		 }

		 var result = new LinkedHashMap<String, RankProperties>();
	 	 for (int i = startYear, j = 1; j <= topN; j++) {
 			var currentProp = new RankProperties(); 
 			var currentName = getName(i, j, gender);
 			currentProp.name = currentName;
 			currentProp.highestRank = j;
 			currentProp.highestRankYear = i;
 			currentProp.lowestRank = j;
 			currentProp.lowestRankYear = i;
 			currentProp.grossTrend = "FLAT";
 			result.put(currentName, currentProp);
 		 }

	 	 for (int i = startYear + 1; i <= endYear; i++) {
	 		 var deletingName = new ArrayList<String>();
	 		 for (String key : result.keySet()) {
	 			 int currentRank;
	 			 if (topN >= (currentRank = getRank(i, key, gender))) {		// maintained, then update
	 				 if (currentRank == result.get(key).highestRank && currentRank == result.get(key).lowestRank)
	 					 continue;
	 				 if (result.get(key).highestRank < currentRank) {		
	 					if (result.get(key).lowestRank < currentRank){
	 						result.get(key).lowestRank = currentRank;
	 						result.get(key).lowestRankYear = i;
	 					}
	 				 } else if (result.get(key).highestRank > currentRank) {
	 					result.get(key).highestRank = currentRank;
						result.get(key).highestRankYear = i;
	 				 }
	 				 if (result.get(key).lowestRankYear < result.get(key).highestRankYear) 
	 					result.get(key).grossTrend = "UP";
	 				 else 
	 					result.get(key).grossTrend = "DOWN";
	 			 } else {									// remove from map
	 				deletingName.add(key);
	 			 }
	 		 }
	 		 for (var key : deletingName)
	 			 result.remove(key);
	 	 }
	 	 return result;
	 }
	 
	 public static float calculateCompatiblityScore(String iName, String iGender, int iYOB, String iNameMate, String iGenderMate, String iPreference) {
		 int oRank, oRankMate;
		 
		 try {
		 oRank = getRank(iYOB, iName, iGender);
		 } catch (Exception e) {
			 oRank = 1;
		 }
		 if (oRank == -1)
			 oRank = 1;

		 int oYOB;
		 if (iPreference == "Younger")
			 oYOB = iYOB + 1;
		 else 
			 oYOB = iYOB - 1;
		 
		 try {
			 oRankMate = getRank(oYOB, iNameMate, iGenderMate);
		 } catch (Exception e) {
			 oRankMate = 1;
		 }
		 if (oRankMate == -1)
			 oRankMate = 1;

//		 System.out.println(Math.abs(oRank));
//		 System.out.println(Math.abs(oRankMate));
//		 System.out.println(Math.abs(oRank - oRankMate));
		 if (oRank > oRankMate)
			 return ((1 - Math.abs(oRank - oRankMate) / (float)oRank) * 100);
		 return ((1 - Math.abs(oRank - oRankMate) / (float)oRankMate) * 100);
	 }
 
}