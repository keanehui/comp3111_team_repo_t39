/**
 * Building on the sample skeleton for 'ui.fxml' COntroller Class generated by SceneBuilder 
 */
package comp3111.popnames;

import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller {

    @FXML
    private Tab tabTaskZero;

    @FXML
    private TextField textfieldNameF;

    @FXML
    private TextField textfieldYear;

    @FXML
    private Button buttonRankM;

    @FXML
    private TextField textfieldNameM;

    @FXML
    private Button buttonRankF;

    @FXML
    private Button buttonTopM;

    @FXML
    private Button buttonTopF;

    @FXML
    private Button buttonSummary;
    
    @FXML
    private Tab tabReport1;
    
    @FXML
    private TextField textfieldTopN;
    
    @FXML
    private TextField textfieldFromYear;
    
    @FXML
    private TextField textfieldToYear;

    @FXML
    private ToggleGroup T1;

    @FXML
    private Tab tabReport2;

    @FXML
    private ToggleGroup T11;

    @FXML
    private Tab tabReport3;

    @FXML
    private ToggleGroup T111;

    @FXML
    private Tab tabApp1;
    
    @FXML
    private TextField textfieldDadName;
    
    @FXML
    private TextField textfieldDadYOB;
    
    @FXML
    private TextField textfieldMumName;
    
    @FXML
    private TextField textfieldMumYOB;
    
    @FXML
    private TextField textfieldVintageYear;

    @FXML
    private Button buttonGetChildName;
    
    @FXML
    private Tab tabApp2;

    @FXML
    private Tab tabApp3;

    @FXML
    private TextArea textAreaConsole;
    

    /**
     *  Task Zero
     *  To be triggered by the "Summary" button on the Task Zero Tab 
     *  
     */
    @FXML
    void doSummary() {
    	int year = Integer.parseInt(textfieldYear.getText());
    	String oReport = AnalyzeNames.getSummary(year);
    	textAreaConsole.setText(oReport);
    }

  
    /**
     *  Task Zero
     *  To be triggered by the "Rank (female)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doRankF() {
    	String oReport = "";
    	String iNameF = textfieldNameF.getText();
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	int oRank = AnalyzeNames.getRank(iYear, iNameF, "F");
    	if (oRank == -1)
    		oReport = String.format("The name %s (female) has not been ranked in the year %d.\n", iNameF, iYear);
    	else
    		oReport = String.format("Rank of %s (female) in year %d is #%d.\n", iNameF, iYear, oRank);
    	textAreaConsole.setText(oReport);
    }

  
    /**
     *  Task Zero
     *  To be triggered by the "Rank (male)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doRankM() {
    	String oReport = "";
    	String iNameM = textfieldNameM.getText();
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	int oRank = AnalyzeNames.getRank(iYear, iNameM, "M");
    	if (oRank == -1)
    		oReport = String.format("The name %s (male) has not been ranked in the year %d.\n", iNameM, iYear);
    	else
    		oReport = String.format("Rank of %s (male) in year %d is #%d.\n", iNameM, iYear, oRank);
    	textAreaConsole.setText(oReport);
    }


    /**
     *  Task Zero
     *  To be triggered by the "Top 5 (female)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doTopF() {
    	String oReport = "";
    	final int topN = 5;
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	oReport = String.format("Top %d most popular names (female) in the year %d:\n", topN, iYear);
    	for (int i=1; i<=topN; i++)
    		oReport += String.format("#%d: %s\n", i, AnalyzeNames.getName(iYear, i, "F"));
    	textAreaConsole.setText(oReport);
    }


    /**
     *  Task Zero
     *  To be triggered by the "Top 5 (male)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doTopM() {
    	String oReport = "";
    	final int topN = 5;
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	oReport = String.format("Top %d most popular names (male) in the year %d:\n", topN, iYear);
    	for (int i=1; i<=topN; i++)
    		oReport += String.format("#%d: %s\n", i, AnalyzeNames.getName(iYear, i, "M"));
    	textAreaConsole.setText(oReport);
    }
    
    /**
     * Task One
     * To be triggered by the "REPORT" button on the Reporting1 Tab
     * 
     */
    @FXML
    void doTopNWithGender() {
    	int topN = 0;
    	try {
    		topN = Integer.parseInt(textfieldTopN.getText());
    	} catch(Exception e) {
    		textAreaConsole.setText("topN must be an integer. \n");
    		return;
    	}
    	int fromYear = 0;
    	try {
    		fromYear = Integer.parseInt(textfieldFromYear.getText());
    	} catch(Exception e) {
    		textAreaConsole.setText("Year range must be integers. \n");
    		return;
    	}
    	int toYear = 0;
    	try {
    		toYear = Integer.parseInt(textfieldToYear.getText());
    	} catch(Exception e) {
    		textAreaConsole.setText("Year range must be integers. \n");
    		return;
    	}
    	String gender = (T1.getSelectedToggle().toString().indexOf("Male") > -1) ? "M" : "F";
    	if (topN < 1) { 
    		textAreaConsole.setText("Top N value must be greater or equal to 1. \n");
    		return;
    	}
    	if (fromYear < 1880 || toYear > 2019 || fromYear > toYear) { 
    		textAreaConsole.setText("Year range is invalid. The valid year period ranges from 1880 to 2019. \n");
    		return;
    	}
    	
    	String oReport = String.format("Top %d most popular names(%s) from %d to %s:\n\n", topN, (gender.equals("M")?"Male":"Female"), fromYear, toYear);
    	
    	HashMap<String, Integer> name_freq = AnalyzeNames.getTopNNameWithInYears(topN, fromYear, toYear, gender);
    	for (int i = 1; i <= topN; ++i) { 
    		String name = AnalyzeNames.getMaxFromHashMap(name_freq);
    		Integer freq = name_freq.get(name);
    		name_freq.remove(name);
    		oReport += String.format("Top %d\nName: %s\nFrequency: %d\n\n", i, name, freq);
    	}
    	textAreaConsole.setText(oReport);
    }
    
    /**
     * Application 1
     * To be triggered by the "REPORT" button on the Reporting1 Tab
     * 
     */
     @FXML
     void doGetChildName() {
    	 String dadName = textfieldDadName.getText();
    	 String mumName = textfieldMumName.getText();
    	 Integer dadYOB = 0;
    	 Integer mumYOB = 0;
    	 Integer vYear = Integer.MIN_VALUE;
    	 try {
     		dadYOB = Integer.parseInt(textfieldDadYOB.getText());
     	 } catch(Exception e) {
     		textAreaConsole.setText("Year of birth must be an integer. \n");
     		return;
     	 }
    	 try {
      		mumYOB = Integer.parseInt(textfieldMumYOB.getText());
      	 } catch(Exception e) {
      		textAreaConsole.setText("Year of birth must be an integer. \n");
      		return;
      	 }
    	 try {
       		vYear = Integer.parseInt(textfieldVintageYear.getText());
       	 } catch(Exception e) {
       		if (textfieldVintageYear.getText().equals("") == false) { // if input v year is not empty and is invalid
       			textAreaConsole.setText("Vintage year must be an integer. \n");
       			return;
       		}
       	 }
    	 if (dadYOB < 1880 || dadYOB > 2019 || mumYOB < 1880 || mumYOB > 2019) {
    		 textAreaConsole.setText("Year of birth must be within period from 1880 to 2019. \n");
    		 return;
    	 }
    	 if (vYear.equals(Integer.MIN_VALUE) == false && (vYear < 1880 || vYear > 2019)) {
    		 textAreaConsole.setText("Vintage year must be within period from 1880 to 2019. \n");
    		 return;
    	 }
    	 if (vYear.equals(Integer.MIN_VALUE)) { // vYear not chosen, set to 2019
    		 vYear = 2019;
    	 }
    	 
    	 Integer dadRank = AnalyzeNames.getRank(dadYOB, dadName, "M");
    	 dadRank = dadRank.equals(-1) ? 1 : dadRank;
    	 Integer mumRank = AnalyzeNames.getRank(mumYOB, mumName, "F");
    	 mumRank = mumRank.equals(-1) ? 1 : mumRank;
    	 String boyName = AnalyzeNames.getName(vYear, dadRank, "M");
    	 String girlName = AnalyzeNames.getName(vYear, mumRank, "F");
    	 
    	 textAreaConsole.setText(String.format("Boy's Name: %s\nGirl's Name: %s", boyName, girlName)); 
     }
}

