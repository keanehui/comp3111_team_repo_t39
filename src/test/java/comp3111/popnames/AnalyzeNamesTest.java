package comp3111.popnames;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;

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
    

}
