package pidChecker;

public class Process {

	private String name;

	private long startDate;
	
	private int pidId;

	public Process(String name, int pidId) {
		this.name = name;
		this.pidId = pidId;
		this.startDate = System.currentTimeMillis();
	}

	public String getName() {
		return name;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the pidId
	 */
	public int getPidId() {
		return pidId;
	}

	/**
	 * @param pidId the pidId to set
	 */
	public void setPidId(int pidId) {
		this.pidId = pidId;
	}
}
