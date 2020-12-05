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
		s2 = "The start year of the period should be blank.";
		if (s1.equals(s2))
			counter++;
		input.setText("2000");
		sleep(1000);
		
		input = (TextField)s.lookup("#textfieldTrendEndYear");
		input.setText("");
		clickOn("#ButtonDoTrend");
		s1 = t.getText();
		s2 = "The end year of the period should be blank.";
		if (s1.equals(s2))
			counter++;
		input.setText("2001");
		sleep(1000);
		
		input = (TextField)s.lookup("#textfieldTrendTopN");
		input.setText("");
		clickOn("#ButtonDoTrend");
		s1 = t.getText();
		s2 = "The TopN parameter should be blank.";
		if (s1.equals(s2))
			counter++;
		sleep(1000);
		
		assertTrue(counter == 3);
	}
	
	@Test
	public void testReport3ANonIntegralInputValidation() {	
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
		sleep(1000);
		
		assertTrue(counter == 3);
	}
		
}
