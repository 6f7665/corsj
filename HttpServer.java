import java.nio.*;
import java.net.*;

// Class för att hantera förfrågningar
class HttpRequestHandlerClass {
	public static String getPage(String getrequest) {
		Path path = FileSystems.getDefault().getPath("html", getrequest);
		BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		String output = "";
		output.concat(reader.readLine());
		return output;
	}
	public void putEvent(String[] data) {
		System.out.println("Skickar data till databasen");
	}
}

public class HttpServer {
	public static void main(String[] args) {
		int port = 8080;

		// Initiera class för hantering av förfrågningar
		HttpRequestHandlerClass httphandler = new HttpRequestHandlerClass();

		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Servern lyssnar på port " + port);

			while (true) {
				try {
					// Vänta på en inkommande förfrågan
					Socket clientSocket = serverSocket.accept();
					System.out.println("Anslutning från: " + clientSocket.getInetAddress());

					// Läs HTTP-förfrågningen från klienten
					BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					StringBuilder request = new StringBuilder();
					String line;
					while ((line = input.readLine()) != null && !line.isEmpty()) {
						request.append(line).append("\n");
					}

					// Skriv ut hela HTTP-förfrågan
					System.out.println("Mottagen förfrågan:\n" + request.toString());

					// Skicka ett enkelt svar till klienten
					PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
					output.println("HTTP/1.1 200 OK");
					output.println("Content-Type: text/plain");
					output.println();
					output.println("Förfrågan mottagen!");

					// Stäng anslutningen
					clientSocket.close();
				} catch (IOException e) {
					System.out.println("Fel vid hantering av förfrågan: " + e.getMessage());
				}
			}
		} catch (IOException e) {
			System.out.println("Servern kunde inte starta: " + e.getMessage());
		}
	}
}

