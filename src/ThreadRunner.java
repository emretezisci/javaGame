
import java.util.Random;

public class ThreadRunner extends Thread {
	int distance;
	String RunnersName;
	double RunnersSpeed;
	double RestPercentage;
	ThreadCondition check = null;

	public ThreadRunner(String RunnersName, double RunnersSpeed, double RestPercentage, ThreadCondition check) {
		this.RunnersName = RunnersName;
		this.RunnersSpeed = RunnersSpeed;
		this.RestPercentage = RestPercentage;
		this.check = check;
	}

	public int getDistance() {
		return distance;
	}

	@Override
	public void run() {
		// check for conditions of less than final count and if there is no winner yet
		while (distance < RunnersConstants.FINAL_COUNT && !check.getFlag()) {

			int rand = new Random().nextInt(100); // random number generator

			if (rand > RestPercentage) { // randomize thread run
				distance += RunnersSpeed;
				System.out.println(this.getName() + " : " + distance);
				Thread.yield();
			} else {
				try {
					Thread.sleep(RunnersConstants.DEFAULT_SLEEP_TIME); // if thread is not active, sleep
				} catch (InterruptedException e) {
					check.setFlag();
					break;
				}
			}
		}

		MarathonRaceRunnerApp.finished(this, check); // declare the results
	}
}