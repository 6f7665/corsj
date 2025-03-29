import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class HttpServer {
	public static void main(String[] args) {
		RequestHandler hander = new RequestHandler();
		System.out.println(hander.getPage("dumps/dump-req-page"));
	}
}

