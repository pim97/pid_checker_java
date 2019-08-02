package pidChecker;

import java.io.IOException;

public class RunPidChecker {

	public static final PidManager PID_MANAGER = new PidManager();

	public static final ThreadManager THREAD_MANAGER = new ThreadManager();
	
	public static final CommandsManager COMMANDS_MANAGER = new CommandsManager();

	public static void main(String[] args) throws IOException, InterruptedException {
		THREAD_MANAGER.registerAllThread();
	}

}
