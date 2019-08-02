package pidChecker;

import java.io.IOException;
import java.util.Scanner;

public class CommandsManager {

	private Scanner inputReader = new Scanner(System.in);

	public void parseCommands() {
		boolean match = false;

		try {
			String s = inputReader.next();
			System.out.println(s);

			if (s.equalsIgnoreCase("-l")) {
				listAllProcesses();
				match = true;
			} else if (s.equalsIgnoreCase("-ka")) {
				killAll();
				match = true;
			}

			if (match) {
				System.out.println("Executed command " + s);
			}
		} catch (Exception e) {
			System.out.println("Couldn't parse " + e);
		}
	}

	private void listAllProcesses() {
		RunPidChecker.PID_MANAGER.processNameWithPid.forEach((key, value) -> {
			System.out.println("pid: " + key + " name: " + value.getName() + " start: " + value.getStartDate()
					+ " end (> 600.000): " + (System.currentTimeMillis() - value.getStartDate()));
		});
	}

	private void killAll() {
		RunPidChecker.PID_MANAGER.processNameWithPid.forEach((key, value) -> {
			try {
				RunPidChecker.PID_MANAGER.killProcessByPid(key);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}
