package theater;

import java.awt.Font;

import javax.swing.UIManager;

import theater.view.MainWindow;

public class Main {

	public static void main(String[] args) {

		// code that will be run when app closes
		// it saves state to files
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				GlobalState.getInstance().saveAll();
			}
		});

		//add default fonts
		UIManager.put("Button.font", new Font("arial", Font.PLAIN, 24));
		UIManager.put("CheckBox.font", new Font("arial", Font.PLAIN, 24));
		UIManager.put("ComboBox.font", new Font("arial", Font.PLAIN, 24));
		UIManager.put("Label.font", new Font("arial", Font.PLAIN, 24));
		UIManager.put("OptionPane.font", new Font("arial", Font.PLAIN, 24));
		UIManager.put("ScrollPane.font", new Font("arial", Font.PLAIN, 24));
		UIManager.put("Viewport.font", new Font("arial", Font.PLAIN, 24));
		UIManager.put("Table.font", new Font("arial", Font.PLAIN, 18));
		UIManager.put("TableHeader.font", new Font("arial", Font.PLAIN, 24));
		UIManager.put("TextField.font", new Font("arial", Font.PLAIN, 24));
		UIManager.put("PasswordField.font", new Font("arial", Font.PLAIN, 24));
		UIManager.put("TextArea.font", new Font("arial", Font.PLAIN, 24));

		MainWindow.getInstance().setVisible(true);

	}

}
