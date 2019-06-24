
public final class RunnersData {
	String RunnersName;
	double RunnersSpeed;
	double RestPercentage;

	public RunnersData(String RunnersName, double RunnersSpeed, double RestPercentage) {
		this.RunnersName = RunnersName;
		this.RunnersSpeed = RunnersSpeed;
		this.RestPercentage = RestPercentage;
	}

	public RunnersData() {
	}

	public String getName() {
		return RunnersName;
	}

	public void setName(String RunnersName) {
		this.RunnersName = RunnersName;
	}

	public double getSpeed() {
		return RunnersSpeed;
	}

	public void setSpeed(double RunnersSpeed) {
		this.RunnersSpeed = RunnersSpeed;
	}

	public double getRestPercentage() {
		return RestPercentage;
	}

	public void setRestPercentage(double RestPercentage) {
		this.RestPercentage = RestPercentage;
	}
}