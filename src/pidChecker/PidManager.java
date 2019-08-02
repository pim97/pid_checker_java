package pidChecker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class PidManager {

	public HashMap<Integer, Process> processNameWithPid = new HashMap<Integer, Process>();

	public ArrayList<String> listenToNames = new ArrayList<String>(
			Arrays.asList("chrome.exe", "chromedriver.exe", "RC2InstanceChrome.exe"));

	private boolean isCorrectNameToListenTo(String name) {
		for (String listenTo : listenToNames) {
			if (listenTo.contains(name)) {
				return true;
			}
		}
		return false;
	}

	public void killProcessByPid(int pid) throws IOException {
		String cmd = "taskkill /F /PID " + pid;
		Runtime.getRuntime().exec(cmd);
	}

	public void updatePids() throws IOException, InterruptedException {
		java.lang.Process process = new ProcessBuilder("tasklist.exe", "/fo", "csv", "/nh").start();
		Scanner sc = new Scanner(process.getInputStream());
		ArrayList<Integer> currentIds = new ArrayList<Integer>();
		if (sc.hasNextLine())
			sc.nextLine();
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] parts = line.split(",");
			String unq = parts[0].substring(1).replaceFirst(".$", "");

			if (isCorrectNameToListenTo(unq)) {
				String pid = parts[1].substring(1).replaceFirst(".$", "");
				int pidToIng = Integer.parseInt(pid);

				// Adding new processes when don't exist
				if (processNameWithPid.get(pidToIng) == null) {
					processNameWithPid.put(pidToIng, new Process(unq, pidToIng));
				}
				currentIds.add(pidToIng);
			}
		}

		sc.close();
		process.waitFor();

		ArrayList<Integer> idsToRemove = new ArrayList<Integer>();
		// Removing process when don't exist
		for (Entry<Integer, Process> entry : processNameWithPid.entrySet()) {
			Integer key = entry.getKey();
			Process value = entry.getValue();

			if (isCorrectNameToListenTo(value.getName())) {
				if (!currentIds.contains(key)) {
					idsToRemove.add(key);
				}

				if (System.currentTimeMillis() - value.getStartDate() > 1000 * 60 * 10) { // 10min
					idsToRemove.add(key);
				}
			}

		}
		idsToRemove.stream().forEach(key -> {
			processNameWithPid.remove(key);
			try {
				killProcessByPid(key);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

}
