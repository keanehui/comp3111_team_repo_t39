/**
 * Building on the sample skeleton for 'ui.fxml' COntroller Class generated by SceneBuilder 
 */
package comp3111.popnames;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
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
    private ToggleGroup T111;

    @FXML
    private Tab tabApp1;

    @FXML
    private Tab tabApp2;

    @FXML
    private Tab tabApp3;

    @FXML
    private TextArea textAreaConsole;
    
    /**
     * Task 2 private data
     */
    @FXML
    private TextField textfieldNamereport2;
    
    @FXML
    private TextField textfieldfirstyear_report2;
    
    @FXML
    private TextField textfieldlastyear_report2;

    @FXML
    private Button report2;
    
    /**
     *  Task 5 private data
     */
    @FXML
    private ToggleGroup T51;
    
    @FXML
    private ToggleGroup T52;
    
    @FXML
    private ToggleGroup T53;
    
    @FXML
    private TextField textfieldname_app2;
    
    @FXML
    private TextField textfieldyear_app2;

    @FXML
    private Button report5;
    
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
     *  Task 2
     * 
     */
    @FXML
    void reporting2() {
    	String oReport = "";
    	String iName = textfieldNamereport2.getText();
    	int first_iyear = Integer.parseInt(textfieldfirstyear_report2.getText());
    	int last_iyear = Integer.parseInt(textfieldlastyear_report2.getText());
    	String iGender = ((RadioButton) T11.getSelectedToggle()).getText().contentEquals("Male") ? "M" : "F";
    	int count_times = 0;
    	
    	if (first_iyear<1880 || last_iyear>2019 ||first_iyear>last_iyear) {
    		oReport = String.format("The period is invalid. Please enter again.");
    	}
    	
    	else {
        	oReport = String.format("|Year\t\t|Rank\t\t|Count\t\t|Percentage\n");
        	oReport += String.format("_________________________________________________\n");
        	for(int this_year=last_iyear; this_year>=first_iyear;this_year--)
        	{
        		int count = AnalyzeNames.getcount(this_year, iName, iGender);
        		double temp_count = count;
        		double babynumber = AnalyzeNames.getbabynumber(this_year);
        		double percentage = (temp_count/babynumber)*100;
        		oReport += String.format("%d\t\t%d\t\t\t%d\t\t\t%f\n",this_year,AnalyzeNames.getRank(this_year, iName, iGender),count,percentage);
        		if(count == -1) {count = 0;}
        		count_times += count;
        	}
        	if (count_times == 0) {
        		oReport = String.format("No people used the name in the period.");
        	}
    	}    	
    	textAreaConsole.setText(oReport);
    }
    
    /**
     * Task 5 
     */
    @FXML
    void reporting5() {
    	String oReport = "";
    	String iName = textfieldname_app2.getText();
    	String iGender = ((RadioButton) T51.getSelectedToggle()).getText().contentEquals("Male") ? "M" : "F";
    	int iYOB = Integer.parseInt(textfieldyear_app2.getText());
    	String iGender_soulmate = ((RadioButton) T52.getSelectedToggle()).getText().contentEquals("Male") ? "M" : "F";
    	String iPreference = ((RadioButton) T53.getSelectedToggle()).getText().contentEquals("Older") ? "O" : "Y";
    	String ouput_name = AnalyzeNames.NK_T5algorithm(iName, iGender, iYOB, iGender_soulmate, iPreference);
    	oReport = String.format("The recommended name is %s.", ouput_name);
    	textAreaConsole.setText(oReport);
    	
    }
    
    
}

