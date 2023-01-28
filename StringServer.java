import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    String s = "";

    public String handleRequest(URI url) {
        if (!url.getPath().equals("/add-message")) {
            return "Invalid command: try /add-message";
        } 

        if (!url.getQuery().startsWith("s=")) {
            return "Invalid query: try ?s={STRING_TO_ADD}";
        }

        s += "\n" + url.getQuery().substring(2);
        return s.substring(1);
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
