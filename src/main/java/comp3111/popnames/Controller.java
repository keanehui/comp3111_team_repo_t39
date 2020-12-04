/**
 * Building on the sample skeleton for 'ui.fxml' COntroller Class generated by SceneBuilder 
 */
package comp3111.popnames;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
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
    private ToggleGroup T1;

    @FXML
    private Tab tabReport2;

    @FXML
    private ToggleGroup T11;

    @FXML
    private Tab tabReport3;
    
    @FXML
    private TextField textfieldTrendStartYear;
    
    @FXML
    private TextField textfieldTrendEndYear;

    @FXML
    private ToggleGroup ToggleGroupTrendGender;
    
    @FXML
    private TextField textfieldTrendTopN;

    @FXML
    private Tab tabApp1;

    @FXML
    private Tab tabApp2;

    @FXML
    private Tab tabApp3;
    
    @FXML
    private TextField textfieldCompatibleUserName;
    
    @FXML
    private ToggleGroup ToggleGroupCompatibleUserGender;
    
    @FXML
    private TextField textfieldCompatibleUserYOB;
    
    @FXML
    private TextField textfieldCompatibleMatchName;
    
    @FXML
    private ToggleGroup ToggleGroupCompatibleMatchGender;
    
    @FXML
    private ToggleGroup ToggleGroupCompatiblePreference;
    

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
    
    /*
     *  Task 3A
     *  To be triggered by the "Report" button on the Report3 Tab
     *  
     */
    @FXML
    void doTrend() {
    	// Validation
    	if (textfieldTrendStartYear.getText().trim().isEmpty()) {
    		textAreaConsole.setText("The start year of the period cannot be blank.");	
    		return;
    	}
    	if (textfieldTrendEndYear.getText().trim().isEmpty()) {
    		textAreaConsole.setText("The end year of the period cannot be blank.");	
    		return;
    	}
    	if (textfieldTrendTopN.getText().trim().isEmpty()) {
    		textAreaConsole.setText("The TopN parameter cannot be blank.");	
    		return;
    	}
    	try {
            Integer.parseInt(textfieldTrendStartYear.getText());
        } catch (Exception e) {
        	textAreaConsole.setText("The start year of the period should contain only integral numbers.");	
    		return;
        }
    	try {
            Integer.parseInt(textfieldTrendEndYear.getText());
        } catch (Exception e) {
        	textAreaConsole.setText("The end year of the period should contain only integral numbers.");	
    		return;
        }
    	try {
            Integer.parseInt(textfieldTrendTopN.getText());
        } catch (Exception e) {
        	textAreaConsole.setText("The TopN parameter should contain only integral numbers.");	
    		return;
        }
    	if (textfieldTrendStartYear.getText().trim().charAt(0) == '0') {
    		textAreaConsole.setText("The start year should not start with zero(s).");	
    		return;
    	}
    	if (textfieldTrendEndYear.getText().trim().charAt(0) == '0') {
    		textAreaConsole.setText("The end year should not start with zero(s).");	
    		return;
    	}
    	if (textfieldTrendTopN.getText().trim().charAt(0) == '0') {
    		textAreaConsole.setText("The TopN parameter should not start with zero(s).");	
    		return;
    	}
    	int istartYear = Integer.parseInt(textfieldTrendStartYear.getText());
    	int iendYear = Integer.parseInt(textfieldTrendEndYear.getText());
    	String igender = ((RadioButton) ToggleGroupTrendGender.getSelectedToggle()).getText().contentEquals("Male") ? "M" : "F";
    	int topN = Integer.parseInt(textfieldTrendTopN.getText());
    	
    	if (istartYear < 1880 || istartYear > 2019 || iendYear < 1880 || iendYear > 2019) {
    		textAreaConsole.setText("The period should be in between 1880 to 2019 inclusively.");	
    		return;
    	}
    	if (topN <= 0) {
    		textAreaConsole.setText("The TopN parameter should larger than 0.");	
    		return;
    	}
    	
    	// Call the calculation method
    	var oReport = AnalyzeNames.getTrend(istartYear, iendYear, igender, topN);
    	
    	// Print out the output
    	String[] header = {"Name", "Lowest Rank", "Highest Rank", "Gross Trend"};
    	String output = "";
    	String format = "|%s\t\t|%s\t\t\t|%s\t\t\t|%s\t\t\n";
    	output += String.format(format, (Object[]) header);
    	
    	String formatRecord = "|%s\t\t|rank: %s, year: %s\t\t|rank: %s, year: %s\t\t|%s\t\t\n";
    	for (String key : oReport.keySet()) {
    		var currentValue = oReport.get(key);
    		String[] record = {key, Integer.toString(currentValue.lowestRank), Integer.toString(currentValue.lowestRankYear), 
    				Integer.toString(currentValue.highestRank), Integer.toString(currentValue.highestRankYear), currentValue.grossTrend};
    		output += String.format(formatRecord, (Object[]) record);
    	}
    	textAreaConsole.setText(output);
    }
    
    /**
     *  Task 6
     *  To be triggered by the "Show Compatibility Score" button on the Application 3 Tab
     *  
     */
    @FXML
    void doShowCompatibilityScore() {
    	// Validation
    	if (textfieldCompatibleUserYOB.getText().trim().isEmpty()) {
    		textAreaConsole.setText("The year of birth cannot be blank.");	
    		return;
    	}
    	try {
            Integer.parseInt(textfieldCompatibleUserYOB.getText());
        } catch (Exception e) {
        	textAreaConsole.setText("The year of birth should contain only integral numbers.");	
    		return;
        }
    	if (textfieldCompatibleUserYOB.getText().trim().charAt(0) == '0') {
    		textAreaConsole.setText("The year of birth should not start with zero(s).");	
    		return;
    	}
    	String iName =  textfieldCompatibleUserName.getText();
    	String igender = ((RadioButton) ToggleGroupCompatibleUserGender.getSelectedToggle()).getText().contentEquals("Male") ? "M" : "F";
    	int iYOB = Integer.parseInt(textfieldCompatibleUserYOB.getText());
    	
    	String iNameMate = textfieldCompatibleMatchName.getText();
    	String iGenderMate = ((RadioButton) ToggleGroupCompatibleMatchGender.getSelectedToggle()).getText().contentEquals("Male") ? "M" : "F";
    	String iPreference = ((RadioButton) ToggleGroupCompatiblePreference.getSelectedToggle()).getText();
    	
    	if (iYOB < 1880 || iYOB > 2019) {
    		textAreaConsole.setText("Your year of birth should be in between 1880 to 2019 inclusively.");	
    		return;
    	}
    	if (iYOB == 1880 && iPreference.equals("Older")) {
    		textAreaConsole.setText("You could not have a soulmate younger than you, please change your preference.");	
    		return;
    	}
    	if (iYOB == 2019 && iPreference.equals("Younger")) {
    		textAreaConsole.setText("You could not have a soulmate older than you, please change your preference.");	
    		return;
    	}
    	
    	// Call the calculation method
    	var oApp = AnalyzeNames.calculateCompatiblityScore(iName, igender, iYOB, iNameMate, iGenderMate, iPreference);
    	
    	// Print out the output
    	String output = "";
    	output += "(0%: Not Compatible; 100%: Perfect Match)\n\n";
    	output += "Compatiblity Score = " + String.format("%.2f", oApp) + "%";
    	textAreaConsole.setText(output);
    }

}

