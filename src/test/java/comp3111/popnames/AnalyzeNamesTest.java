package comp3111.popnames;

import org.junit.Test;
import static org.junit.Assert.*;


import java.util.HashMap;

import java.util.LinkedHashMap;


public class AnalyzeNamesTest {
	
    @Test 
    public void testGetRankNotFound() {
    	AnalyzeNames a = new AnalyzeNames();
    	int i = a.getRank(2019, "XXX", "M");
		assertEquals(i, -1);
    }
    
    @Test 
    public void testGetRankMale() {
    	AnalyzeNames a = new AnalyzeNames();
    	int i = a.getRank(2019, "David", "M");
    	assertTrue(i==27);
    }
    
    @Test 
    public void testGetRankFemale() {
    	AnalyzeNames a = new AnalyzeNames();
    	int i = a.getRank(2019, "Desire", "F");
    	assertTrue(i==2192);
    }
    	
    @Test 
    public void testGetNameMale() {
    	AnalyzeNames a = new AnalyzeNames();
    	String name = a.getName(2019, 27, "M");
    	assertTrue(name.equals("David"));
    }
    
    @Test 
    public void testGetNameFemale() {
    	AnalyzeNames a = new AnalyzeNames();
    	String name = a.getName(2019, 2192, "F");
    	assertTrue(name.equals("Desire"));
    }
    

    @Test
    public void testGetTopNNameWithInYears1() { 
    	AnalyzeNames a = new AnalyzeNames();
    	HashMap<String, Integer> name_freq = a.getTopNNameWithInYears(1, 1880, 1880, "M");
    	assertTrue(name_freq.size() == 1);
    }
    
    @Test
    public void testGetTopNNameWithInYears2() { 
    	AnalyzeNames a = new AnalyzeNames();
    	HashMap<String, Integer> name_freq = a.getTopNNameWithInYears(20, 1880, 1880, "M");
    	assertTrue(name_freq.size() == 20);
    }
    
    @Test
    public void testGetTopNNameWithInYears3() { 
    	AnalyzeNames a = new AnalyzeNames();
    	HashMap<String, Integer> name_freq = a.getTopNNameWithInYears(10, 1880, 2019, "F");
    	assertTrue(name_freq.size() == 10);
    }
    
    @Test 
    public void testGetTrendValid() {
    	AnalyzeNames a = new AnalyzeNames();
    	var testResult = a.getTrend(2010, 2019, "M", 10);
    	LinkedHashMap<String, RankProperties> actualResult = new LinkedHashMap<>();
    	actualResult.put("William", new RankProperties(5, 2015, 3, 2018, "UP"));
    	actualResult.put("Noah", new RankProperties(7, 2010, 1, 2016, "UP"));
    	for (String key : testResult.keySet()) {
    		if (!testResult.get(key).equals(actualResult.get(key)))
    			assertTrue(false);
    	}
    	assertTrue(true);
    }
    
    @Test 
    public void testGetTrendwithInvalidTopN() {
    	AnalyzeNames a = new AnalyzeNames();
    	try {
    		a.getTrend(1880, 2019, "M", 99999999);
    	} catch (Exception e) {
    		assertTrue(e.getMessage().equals("The TopN parameter is too large."));
    	}
    }
	
	@Test 
    public void testCalculateCompatiblityScoreGeneralCase() {
		AnalyzeNames a = new AnalyzeNames();
    	var testResult = a.calculateCompatiblityScore("Elai", "M", 2018, "Kazi", "F", "Younger");
    	double actualResult = 66.34;
    	if (String.format("%.2f", testResult).equals(String.format("%.2f", actualResult)))
			assertTrue(true);
    	else
    		assertTrue(false);
    }
	
	@Test 
    public void testCalculateCompatiblityScoreBoundaryCase() {
		AnalyzeNames a = new AnalyzeNames();
    	var testResult = a.calculateCompatiblityScore("Abc", "M", 2018, "Def", "F", "Younger");
    	double actualResult = 100.00;
    	if (String.format("%.2f", testResult).equals(String.format("%.2f", actualResult)))
			assertTrue(true);
    	else
    		assertTrue(false);
    }


}
