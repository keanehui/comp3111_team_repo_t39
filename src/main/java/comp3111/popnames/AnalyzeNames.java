package comp3111.popnames;

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
	 
	 public static String[][] getTrend(int startYear, int endYear, String gender) {
		 if (startYear == 0 || endYear == 0) {
			 return null;
		 }
	 	 String[][] result = new String [4][4];
	 	 int rise = 0;
	 	 int fall = 0;
	     int currentRank = 0;
	     int otherRank = 0;
	     boolean init = false;
	     
	     // For every name entry in the CSV file
	     for (CSVRecord rec : getFileParser(startYear)) {
	         // Get its rank if gender matches param
	         if (rec.get(1).equals(gender)) {
	        	 String currentName = rec.get(0);
	             // Get the name whose rank matches param
	         	currentRank++;
	         	if (!init) {
		    		 if ((otherRank = getRank(endYear, currentName, gender)) != -1) {
		    			 rise = currentRank - otherRank;	// suppose it's positive
		    			 fall = currentRank - otherRank;	// suppose it's negative
		    			 result[0][0] = currentName;
		    			 result[0][1] = Integer.toString(currentRank);
		    			 result[0][2] = Integer.toString(otherRank);
		    			 result[0][3] = Integer.toString(Math.abs(rise));
		    			 result[1][0] = currentName;
		    			 result[1][1] = Integer.toString(currentRank);
		    			 result[2][2] = Integer.toString(otherRank);
		    			 result[3][3] = Integer.toString(Math.abs(fall));
		    			 init = !init;
		    		 }
		    	} else if ((otherRank = getRank(endYear, currentName, gender)) != -1) {
		    		if (currentRank - otherRank > rise) {	// there's a name have higher rise
		    			rise = currentRank - otherRank;
		    			result[0][0] = currentName;
		    			result[0][1] = Integer.toString(currentRank);
		    			result[0][2] = Integer.toString(otherRank);
		    			result[0][3] = Integer.toString(Math.abs(rise));
		    		}
		    		if (currentRank - otherRank < fall) {	// there's a name have higher fall
		    			fall = currentRank - otherRank;
		    			result[1][0] = currentName;
		    			result[1][1] = Integer.toString(currentRank);
		    			result[1][2] = Integer.toString(otherRank);
		    			result[1][3] = Integer.toString(Math.abs(fall));
		    		}
		    	}
	         }
	     } 
//	     System.out.println(result[0]);
	     return result;	// did not handle not found result (little possibility)
	 }
 
}