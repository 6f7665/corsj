import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

// Class för att hantera förfrågningar
class HttpRequestHandler {
	public static String getPage(String getrequest) {
		String output = "";
		Path path = Paths.get(getrequest);
		try {
			List<String> content = Files.readAllLines(path);
			for (String line : content) {
				output = String.join("\n", output, line);
				//System.out.println(line);
			}
		}
		catch (IOException e) {
			System.out.printf("error: %s", e);
		}
		return output;
	}
	public void putEvent(String[] data) {
		System.out.println("Skickar data till databasen");
	}
}
