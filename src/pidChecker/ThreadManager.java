package pidChecker;

import java.io.IOException;

public class ThreadManager {

	public void registerAllThread() {
		runPidManagerThread();
		runCommandsThread();
	}

	public void runCommandsThread() {
		Thread commandsThread = new Thread(() -> {
			while (true) {
				RunPidChecker.COMMANDS_MANAGER.parseCommands();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		commandsThread.setName("commandsThread");
		commandsThread.start();
	}
	
	public void runPidManagerThread() {
		Thread pidmanager = new Thread(() -> {
			while (true) {
				try {
					RunPidChecker.PID_MANAGER.updatePids();
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		pidmanager.setName("pidmanager");
		pidmanager.start();

	}
	
}
