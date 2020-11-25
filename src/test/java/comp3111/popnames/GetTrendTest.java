package comp3111.popnames;

import static org.junit.Assert.*;

import org.junit.Test;

public class GetTrendTest {
	
	@Test 
    public void testGetTrend() {
    	AnalyzeNames a = new AnalyzeNames();
    	var testResult = a.getTrend(2018, 2019, "F");
    	String[][] actualResult = {{"Seher", "17674", "6926", "10748"}, {"Anifer", "3919", "15651", "11732"}};
    	for (int i = 0; i < 2; i++) {
    		for (int j = 0; j < 3; j++) {
    			if (!testResult[i][j].equals(actualResult[i][j])) {
    				assertTrue(false);
    			}
    		}
    	}
    	assertTrue(true);
    }
	
	@Test 
    public void testGetTrendInvaildYear() {
    	AnalyzeNames a = new AnalyzeNames();
    	var testResult = a.getTrend(0, 0, "F");
    	assertTrue(testResult == null);
    }
}
