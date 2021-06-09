package theater;

import theater.view.MainWindow;

public class Main {

	public static void main(String[] args) {
		 MainWindow.getInstance().setVisible(true);
		 
		// code that will be run when app closes
		 //it saves state to files
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					GlobalState.getInstance().saveAll();
				}
			});
	}

}
