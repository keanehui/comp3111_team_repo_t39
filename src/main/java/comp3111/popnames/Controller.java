
/**
 * Building on the sample skeleton for 'ui.fxml' COntroller Class generated by SceneBuilder 
 */
package comp3111.popnames;

import java.util.HashMap;
import java.util.LinkedHashMap;

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
	 *  Task 3A
	 *  To be triggered by the "Report" button on the Report3 Tab
	 *  
	 */
	@FXML
	void doTrend() {
		// Validation
		if (textfieldTrendStartYear.getText().trim().isEmpty()) {
			textAreaConsole.setText("The start year of the period should not be blank.");	
			return;
		}
		if (textfieldTrendEndYear.getText().trim().isEmpty()) {
			textAreaConsole.setText("The end year of the period should not be blank.");	
			return;
		}
		if (textfieldTrendTopN.getText().trim().isEmpty()) {
			textAreaConsole.setText("The TopN parameter should not be blank.");	
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
		if (istartYear >= iendYear){
			textAreaConsole.setText("The start year must smaller than the end year.");	
			return;
		}
		if (topN <= 0) {
			textAreaConsole.setText("The TopN parameter should be larger than 0.");	
			return;
		}

		// Call the calculation method
		LinkedHashMap<String, RankProperties> oReport;
		try {
			oReport = AnalyzeNames.getTrend(istartYear, iendYear, igender, topN);
		} catch (Exception e) {
			textAreaConsole.setText(e.getMessage());	
			return;
		}

		if (oReport.isEmpty()) {
			textAreaConsole.setText("No record is found.");	
			return;
		}

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
			textAreaConsole.setText("The year of birth should not be blank.");	
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
			textAreaConsole.setText("You could not have a soulmate older than you, please change your preference.");	
			return;
		}
		if (iYOB == 2019 && iPreference.equals("Younger")) {
			textAreaConsole.setText("You could not have a soulmate younger than you, please change your preference.");	
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


	/**
	 *  Task 2
	 * 
	 */
	@FXML
	void reporting2() {
		String oReport = "";
		String iName = textfieldNamereport2.getText();
//		int first_iyear = Integer.parseInt(textfieldfirstyear_report2.getText());
//		int last_iyear = Integer.parseInt(textfieldlastyear_report2.getText());
		String iGender = ((RadioButton) T11.getSelectedToggle()).getText().contentEquals("Male") ? "M" : "F";
		int count_times = 0;
		int first_iyear;
		int last_iyear;
		try {
			first_iyear = Integer.parseInt(textfieldfirstyear_report2.getText());
		} catch(Exception e) {
			textAreaConsole.setText("Input year must be an integer. \n");
			return;
		}
		try {
			last_iyear = Integer.parseInt(textfieldlastyear_report2.getText());
		} catch(Exception e) {
			textAreaConsole.setText("Input year must be an integer. \n");
			return;
		}

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
		int iYOB;
		try {
			iYOB = Integer.parseInt(textfieldyear_app2.getText());
		} catch(Exception e) {
			textAreaConsole.setText("Input year must be an integer. \n");
			return;
		}
		String iGender_soulmate = ((RadioButton) T52.getSelectedToggle()).getText().contentEquals("Male") ? "M" : "F";
		String iPreference = ((RadioButton) T53.getSelectedToggle()).getText().contentEquals("Older") ? "O" : "Y";
		String ouput_name = AnalyzeNames.NK_T5algorithm(iName, iGender, iYOB, iGender_soulmate, iPreference);
		oReport = String.format("The recommended name is %s.", ouput_name);
		textAreaConsole.setText(oReport);

	}

}

