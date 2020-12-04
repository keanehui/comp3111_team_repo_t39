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
	 
	 /**
     * Identify the names that have maintained a high level of popularity within Top N over a given period.
     *
     * @param int istartYear the start year of the period
     * @param int iendYear the end year of the period
     * @param String igender the gender of interest
     * @param int topN the level of popularity
     * @return A LinkedHashMap<String, RankProperties> that stores name as the key and RankProperties as value
     */
	 public static LinkedHashMap<String, RankProperties> getTrend(int istartYear, int iendYear, String igender, int topN) {
		 var result = new LinkedHashMap<String, RankProperties>();
	 	 for (int i = istartYear, j = 1; j <= topN; j++) {
 			var currentName = getName(i, j, igender);
 			result.put(currentName, new RankProperties(j, i, j, i, "FLAT"));
 		 }

	 	 for (int i = istartYear + 1; i <= iendYear; i++) {
	 		 var deletingName = new ArrayList<String>();
	 		 for (String key : result.keySet()) {
	 			 int currentRank;
	 			 if (topN >= (currentRank = getRank(i, key, igender))) {		// maintained, then update
	 				 if (currentRank == result.get(key).highestRank && currentRank == result.get(key).lowestRank) {
	 					result.get(key).highestRank = currentRank;
	 					result.get(key).highestRankYear = i;
	 					result.get(key).lowestRank = currentRank;
	 					result.get(key).lowestRankYear = i;
	 				 }
	 				 if (result.get(key).highestRank < currentRank) {		
	 					if (result.get(key).lowestRank <= currentRank){
	 						result.get(key).lowestRank = currentRank;
	 						result.get(key).lowestRankYear = i;
	 					}
	 				 } else if (result.get(key).highestRank >= currentRank) {
	 					result.get(key).highestRank = currentRank;
						result.get(key).highestRankYear = i;
	 				 }
	 				 if (result.get(key).lowestRankYear < result.get(key).highestRankYear) 
	 					result.get(key).grossTrend = "UP";
	 				 else if (result.get(key).lowestRankYear > result.get(key).highestRankYear) 
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
	 
	 /**
     * Calculate Scores for Compatible Pairs
     * The algorithm is described as follows.
     * Compute oRank which equals to the iGender ranking of iName in iYOB (equals to 1, if iName is not ranked in iYOB)
	 * Compute oYOB which equals to (iYOB+1) if (iPreference is Younger), or (iYOB-1) if (iPreference is Older)
	 * Compute oRankMate which equals to the iGenderMate ranking of iNameMate in oYOB (equals to 1, if iNameMate is not ranked in oYOB)
	 * If oRank is larger than oRankMate then
	 * Compute oScore which equals to (1 - abs(oRank – oRankMate) / oRank) * 100%
	 * If oRankMate is larger than oRank then
	 * Compute oScore which equals to (1 - abs(oRank – oRankMate) / oRankMate) * 100%
	 * In this way, the algorithm will provide a score of compatibility in range of 0%-100% (0%: Not Compatible; 100%: Perfect Match)
     *
     * @param String iName Name of the user
     * @param String iGender Gender of the user
     * @param int iYOB Year of Birth of the user
     * @param String iNameMate Name of the person to be matched
     * @param String iGenderMate Gender of the person to be matched
     * @param String iPreference user's preference on either have a younger or older soulmate
     * @return A float number that storing the calculated compatibility score
     */
	 public static float calculateCompatiblityScore(String iName, String iGender, int iYOB, String iNameMate, String iGenderMate, String iPreference) {
		 int oRank = getRank(iYOB, iName, iGender);
		 oRank = oRank == -1 ? 1 : oRank;
		 
		 int oYOB = iPreference.equals("Younger") ? iYOB + 1 : iYOB - 1;

		 int oRankMate = getRank(oYOB, iNameMate, iGenderMate);
		 oRankMate = oRankMate == -1 ? 1 : oRankMate;

		 System.out.println(oRankMate);
		 System.out.println(oRank);
		 System.out.println(oYOB);
		 
		 if (oRank > oRankMate)
			 return ((1 - Math.abs(oRank - oRankMate) / (float)oRank) * 100);
		 return ((1 - Math.abs(oRank - oRankMate) / (float)oRankMate) * 100);
	 }
 
}

/**
 * A helper class to store the rank properties for implementing getTrend functionality
 */
class RankProperties {
	public String name;
	public int lowestRank;
	public int lowestRankYear;
	public int highestRank;
	public int highestRankYear;
	public String grossTrend;
	
	RankProperties(int lowestRank, int lowestRankYear, int highestRank, int highestRankYear, String grossTrend){
		this.lowestRank = lowestRank;
		this.lowestRankYear = lowestRankYear;
		this.highestRank = highestRank;
		this.highestRankYear = highestRankYear;
		this.grossTrend = grossTrend;
	}
	
	@Override
	public boolean equals(Object o) {
		RankProperties another = (RankProperties) o;
		return lowestRank == another.lowestRank && lowestRankYear == another.lowestRankYear
				&& highestRank == another.highestRank && highestRankYear == another.highestRankYear
				&& grossTrend.equals(another.grossTrend);
	}
}
