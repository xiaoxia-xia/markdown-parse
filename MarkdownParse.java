// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse{
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        int lastParanIndex = markdown.lastIndexOf(")");
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            String newLink = markdown.substring(openParen + 1, closeParen);
            if ("".equals(newLink)){
                newLink = "the paren leaves blank";
            }
            toReturn.add(newLink);
            currentIndex = closeParen + 1;
            System.out.println(closeParen);
            if(closeParen == lastParanIndex)
            {
                break;
            }
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        try
        {
            ArrayList<String> links = getLinks(contents);
            System.out.println(links);
        }
        catch(Exception e)
        {
            System.out.println("can not detect link in the file");
        }
    }
}