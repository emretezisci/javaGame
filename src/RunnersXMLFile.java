
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

import javax.xml.stream.*;

public class RunnersXMLFile implements DataSource {

	public ArrayList<RunnersData> getRunnersData() {
		ArrayList<RunnersData> runners = new ArrayList<>();
		String fileStr = "Resources/FinalXMLData.xml";
		Path filePath = Paths.get(fileStr);
		RunnersData runner = null;

		if (Files.exists(filePath)) {
			XMLInputFactory input = XMLInputFactory.newFactory(); // create XMLInputFactory object

			try {
				FileReader fileReader = new FileReader(filePath.toFile()); // create a XMLStreamReader object

				XMLStreamReader reader = input.createXMLStreamReader(fileReader);

				while (reader.hasNext()) {
					int eventType = reader.getEventType();
					switch (eventType) {

					case XMLStreamConstants.START_ELEMENT:
						String eleName = reader.getLocalName();
						if (eleName.equals("Runner")) { // look for Runner
							runner = new RunnersData();
							runner.setName(reader.getAttributeValue(0));
						}
						if (eleName.equals("RunnersMoveIncrement")) { // look for RunnersMoveIncrement
							runner.setSpeed(Double.parseDouble(reader.getElementText()));
						}
						if (eleName.equals("RestPercentage")) { // look for RestPercentage
							runner.setRestPercentage(Double.parseDouble(reader.getElementText()));
						}
						break;

					case XMLStreamConstants.END_ELEMENT:
						eleName = reader.getLocalName();
						if (eleName.equals("Runner")) { // look for latest Runner
							runners.add(runner);
						}
						break;
					default:
						break;
					}

					reader.next();
				}
			} catch (IOException | XMLStreamException e) {
				System.err.println(e);
			}
		}
		return runners;
	}
}