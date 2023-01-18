import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    ArrayList<String> arr = new ArrayList<>();

    public String handleRequest(URI url) {
        String query = url.getQuery();
        if (query == null || !query.startsWith("s=")) return "Invalid Query!";
        query = query.substring(2);
        if (url.getPath().equals("/add")) {
            if (arr.contains(query)) return "String already in list";
            arr.add(query);
            return String.format("Added %s to list", query);
        } else if (url.getPath().equals("/search")) {
            StringBuilder ret = new StringBuilder("Superstrings in list: ");
            for (String s : arr) {
                if (s.contains(query)) ret.append(s + ", ");
            }
            return ret.length() == 22 ? ret.toString() + "None" : ret.toString().substring(0, ret.length()-2);
        } else {
            return "Unknown Command! Try /add or /search instead";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
