
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class RunnersTextFile implements DataSource {

	public ArrayList<RunnersData> getRunnersData() {
		ArrayList<RunnersData> RunnersList = new ArrayList<>();
		RunnersData runner = null;
		String fileStr = "Resources/FinalTextData.txt";
		Path filePath = Paths.get(fileStr);
		if (Files.exists(filePath)) {
			File runnersFile = filePath.toFile();
			try (BufferedReader in = new BufferedReader(new FileReader(runnersFile))) {
				String line;
				while ((line = in.readLine()) != null) {
					runner = new RunnersData();
					String[] strLine = line.split(" ");
					runner.setName(strLine[0]);
					runner.setSpeed(Double.parseDouble(strLine[1]));
					runner.setRestPercentage(Double.parseDouble(strLine[2]));
					RunnersList.add(runner);
				}
			} catch (IOException e) {
				System.err.println(e);
			}
		}
		return RunnersList;
	}
}