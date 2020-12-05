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
		//clickOn("#tabTaskZero");
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
		//clickOn("#tabTaskZero");
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
		
}
