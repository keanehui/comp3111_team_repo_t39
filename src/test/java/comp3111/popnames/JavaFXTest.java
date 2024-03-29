package comp3111.popnames;

import static org.junit.Assert.*;
import org.junit.Test;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class JavaFXTest extends ApplicationTest {

	private Scene s;
	private TextArea t;
	private TextField tf;
	
	@Override
	public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/ui.fxml"));
   		VBox root = (VBox) loader.load();
   		Scene scene =  new Scene(root);
   		stage.setScene(scene);
   		stage.setTitle("Popular Names");
   		stage.show();
   		s = scene;
		t = (TextArea)s.lookup("#textAreaConsole");
	}

    
	@Test
	public void testButtonRankTrue() {	
		clickOn("#tabTaskZero");
		clickOn("#buttonRankM");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonRankM");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	
	@Test
	public void testButtonRankFalse() {	
		clickOn("#tabTaskZero");
		clickOn("#buttonRankM");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonRankF");
		//sleep(1000);
		String s2 = t.getText();
		assertFalse(s1.equals(s2));
	}
	
	
	@Test
	public void testTextAreaConsole() {	
		t.setText("David");
		String s = t.getText();
		assertTrue(s.equals("David"));
	}
	
	@Test
	public void testReport3ABlankInputValidation() {	
		clickOn("#tabReport3A");
		TextField input;
		String s1, s2;
		int counter = 0;
		
		input = (TextField)s.lookup("#textfieldTrendStartYear");
		input.setText("");
		clickOn("#ButtonDoTrend");
		s1 = t.getText();
		s2 = "The start year of the period should not be blank.";
		if (s1.equals(s2))
			counter++;
		input.setText("2000");
//		sleep(1000);
		
		input = (TextField)s.lookup("#textfieldTrendEndYear");
		input.setText("");
		clickOn("#ButtonDoTrend");
		s1 = t.getText();
		s2 = "The end year of the period should not be blank.";
		if (s1.equals(s2))
			counter++;
		input.setText("2001");
//		sleep(1000);
		
		input = (TextField)s.lookup("#textfieldTrendTopN");
		input.setText("");
		clickOn("#ButtonDoTrend");
		s1 = t.getText();
		s2 = "The TopN parameter should not be blank.";
		if (s1.equals(s2))
			counter++;
		input.setText("10");
//		sleep(1000);
		
		assertTrue(counter == 3);
	}
	
	@Test
	public void testReport3ANonIntegralInputValidation() {	
		sleep(1000);
		clickOn("#tabReport3A");
		TextField input;
		String s1, s2;
		int counter = 0;
		
		input = (TextField)s.lookup("#textfieldTrendStartYear");
		input.setText("aaa");
		clickOn("#ButtonDoTrend");
		s1 = t.getText();
		s2 = "The start year of the period should contain only integral numbers.";
		if (s1.equals(s2))
			counter++;
		input.setText("2000");
		sleep(1000);
		
		input = (TextField)s.lookup("#textfieldTrendEndYear");
		input.setText("aaa");
		clickOn("#ButtonDoTrend");
		s1 = t.getText();
		s2 = "The end year of the period should contain only integral numbers.";
		if (s1.equals(s2))
			counter++;
		input.setText("2001");
		sleep(1000);
		
		input = (TextField)s.lookup("#textfieldTrendTopN");
		input.setText("aaa");
		clickOn("#ButtonDoTrend");
		s1 = t.getText();
		s2 = "The TopN parameter should contain only integral numbers.";
		if (s1.equals(s2))
			counter++;
		input.setText("10");
		sleep(1000);
		
		assertTrue(counter == 3);
	}
	
	@Test
	public void testReport3AZeroNumberInputValidation() {	
		clickOn("#tabReport3A");
		TextField input;
		String s1, s2;
		int counter = 0;
		// topn, period
		input = (TextField)s.lookup("#textfieldTrendStartYear");
		input.setText("02000");
		clickOn("#ButtonDoTrend");
		s1 = t.getText();
		s2 = "The start year should not start with zero(s).";
		if (s1.equals(s2))
			counter++;
		input.setText("2000");
//		sleep(1000);
		
		input = (TextField)s.lookup("#textfieldTrendEndYear");
		input.setText("02001");
		clickOn("#ButtonDoTrend");
		s1 = t.getText();
		s2 = "The end year should not start with zero(s).";
		if (s1.equals(s2))
			counter++;
		input.setText("2001");
//		sleep(1000);
		
		input = (TextField)s.lookup("#textfieldTrendTopN");
		input.setText("010");
		clickOn("#ButtonDoTrend");
		s1 = t.getText();
		s2 = "The TopN parameter should not start with zero(s).";
		if (s1.equals(s2))
			counter++;
		input.setText("10");
//		sleep(1000);
		
		assertTrue(counter == 3);
	}
	
	@Test
	public void testReport3AInvalidRangeInputValidation() {	
		clickOn("#tabReport3A");
		TextField input;
		String s1, s2;
		int counter = 0;

		input = (TextField)s.lookup("#textfieldTrendStartYear");
		input.setText("1879");
		clickOn("#ButtonDoTrend");
		s1 = t.getText();
		s2 = "The period should be in between 1880 to 2019 inclusively.";
		if (s1.equals(s2))
			counter++;
		input.setText("2000");
//		sleep(1000);
		
		input = (TextField)s.lookup("#textfieldTrendEndYear");
		input.setText("2020");
		clickOn("#ButtonDoTrend");
		s1 = t.getText();
		s2 = "The period should be in between 1880 to 2019 inclusively.";
		if (s1.equals(s2))
			counter++;
		input.setText("2001");
//		sleep(1000);
		
		input = (TextField)s.lookup("#textfieldTrendStartYear");
		input.setText("2002");
		clickOn("#ButtonDoTrend");
		s1 = t.getText();
		s2 = "The start year must smaller than the end year.";
		if (s1.equals(s2))
			counter++;
		input.setText("2000");
//		sleep(1000);
		
		input = (TextField)s.lookup("#textfieldTrendTopN");
		input.setText("-1");
		clickOn("#ButtonDoTrend");
		s1 = t.getText();
		s2 = "The TopN parameter should be larger than 0.";
		if (s1.equals(s2))
			counter++;
		input.setText("10");
//		sleep(1000);
		
		assertTrue(counter == 4);
	}
	
	@Test
	public void testApp6InputValidation() {	
		clickOn("#tabApp3");
		TextField input;
		String s1, s2;
		int counter = 0;
		
		input = (TextField)s.lookup("#textfieldCompatibleUserYOB");
		input.setText("");
		clickOn("#ButtonCalculateCompatibility");
		s1 = t.getText();
		s2 = "The year of birth should not be blank.";
		if (s1.equals(s2))
			counter++;
		input.setText("2000");
//		sleep(1000);
		
		input = (TextField)s.lookup("#textfieldCompatibleUserYOB");
		input.setText("aaa");
		clickOn("#ButtonCalculateCompatibility");
		s1 = t.getText();
		s2 = "The year of birth should contain only integral numbers.";
		if (s1.equals(s2))
			counter++;
		input.setText("2000");
//		sleep(1000);
		
		input = (TextField)s.lookup("#textfieldCompatibleUserYOB");
		input.setText("02000");
		clickOn("#ButtonCalculateCompatibility");
		s1 = t.getText();
		s2 = "The year of birth should not start with zero(s).";
		if (s1.equals(s2))
			counter++;
		input.setText("2000");
//		sleep(1000);
		
		input = (TextField)s.lookup("#textfieldCompatibleUserYOB");
		input.setText("2020");
		clickOn("#ButtonCalculateCompatibility");
		s1 = t.getText();
		s2 = "Your year of birth should be in between 1880 to 2019 inclusively.";
		if (s1.equals(s2))
			counter++;
		input.setText("2000");
//		sleep(1000);
		
		assertTrue(counter == 4);
  }

	public void testReport1_1() {	
		clickOn("#tabReport1");
		clickOn("#buttonReport1");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonReport1");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testReport1_2() {	
		tf = (TextField)s.lookup("#textfieldTopN");
		clickOn("#tabReport1");
		tf.setText("-1");
		clickOn("#buttonReport1");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonReport1");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testReport1_3() {	
		tf = (TextField)s.lookup("#textfieldFromYear");
		clickOn("#tabReport1");
		tf.setText("1879");
		clickOn("#buttonReport1");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonReport1");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testReport1_4() {	
		tf = (TextField)s.lookup("#textfieldFromYear");
		clickOn("#tabReport1");
		tf.setText("1900");
		clickOn("#buttonReport1");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonReport1");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testReport1_5() {	
		tf = (TextField)s.lookup("#textfieldToYear");
		clickOn("#tabReport1");
		tf.setText("2020");
		clickOn("#buttonReport1");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonReport1");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testReport1_6() {	
		tf = (TextField)s.lookup("#textfieldToYear");
		clickOn("#tabReport1");
		tf.setText("2019");
		clickOn("#buttonReport1");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonReport1");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testReport1_7() {	
		clickOn("#tabReport1");
		tf = (TextField)s.lookup("#textfieldFromYear");
		tf.setText("2018");
		tf = (TextField)s.lookup("#textfieldToYear");
		tf.setText("2017");
		clickOn("#buttonReport1");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonReport1");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testReport1_8() {	
		clickOn("#tabReport1");
		tf = (TextField)s.lookup("#textfieldTopN");
		tf.setText("abc");
		clickOn("#buttonReport1");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonReport1");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testApp1_1() {	
		clickOn("#tabApp1");
		tf = (TextField)s.lookup("#textfieldDadYOB");
		tf.setText("abc");
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testApp1_2() {	
		clickOn("#tabApp1");
		tf = (TextField)s.lookup("#textfieldMumYOB");
		tf.setText("abc");
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testApp1_3() {	
		clickOn("#tabApp1");
		tf = (TextField)s.lookup("#textfieldVintageYear");
		tf.setText("abc");
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testApp1_4() {	
		clickOn("#tabApp1");
		tf = (TextField)s.lookup("#textfieldDadYOB");
		tf.setText("1879");
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testApp1_5() {	
		clickOn("#tabApp1");
		tf = (TextField)s.lookup("#textfieldDadYOB");
		tf.setText("2020");
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testApp1_6() {	
		clickOn("#tabApp1");
		tf = (TextField)s.lookup("#textfieldMumYOB");
		tf.setText("1879");
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testApp1_7() {	
		clickOn("#tabApp1");
		tf = (TextField)s.lookup("#textfieldMumYOB");
		tf.setText("2020");
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testApp1_8() {	
		clickOn("#tabApp1");
		tf = (TextField)s.lookup("#textfieldVintageYear");
		tf.setText("");
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testApp1_9() {	
		clickOn("#tabApp1");
		tf = (TextField)s.lookup("#textfieldVintageYear");
		tf.setText("2000");
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonGetChildName");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	// test 2
	@Test
	public void testReport2_1() {	
		tf = (TextField)s.lookup("#textfieldNamereport2");
		clickOn("#tabReport2");
		tf.setText("-1");
		clickOn("#report2");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#report2");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	@Test
	public void testReport2_2() {	
		tf = (TextField)s.lookup("#textfieldNamereport2");
		clickOn("#tabReport2");
		tf.setText("Mary");
		clickOn("#report2");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#report2");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	@Test
	public void testReport2_3() {	
		tf = (TextField)s.lookup("#textfieldNamereport2");
		clickOn("#tabReport2");
		tf.setText("");
		clickOn("#report2");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#report2");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	@Test
	public void testReport2_4() {	
		tf = (TextField)s.lookup("#textfieldlastyear_report2");
		clickOn("#tabReport2");
		tf.setText("1.1");
		clickOn("#report2");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#report2");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	@Test
	public void testReport2_5() {	
		tf = (TextField)s.lookup("#textfieldlastyear_report2");
		clickOn("#tabReport2");
		tf.setText("100");
		clickOn("#report2");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#report2");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	// test 5
	@Test
	public void testapp2_1() {	
		tf = (TextField)s.lookup("#textfieldyear_app2");
		clickOn("#tabApp2");
		tf.setText("1.1");
		clickOn("#report5");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#report5");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	@Test
	public void testapp2_2() {	
		tf = (TextField)s.lookup("#textfieldyear_app2");
		clickOn("#tabApp2");
		tf.setText("1946");
		clickOn("#report5");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#report5");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	
	
	
	
	
}
