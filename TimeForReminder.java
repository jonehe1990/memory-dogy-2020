package memory;

import java.text.ParseException;
import java.util.TimerTask;

public class TimeForReminder extends TimerTask {

	@Override
	public void run() {
		try {
			Tools.reminder();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
