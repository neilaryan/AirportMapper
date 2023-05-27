import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphLoader {
  private graph<String, Double> airportGraph;
  public GraphLoader(String filepathToDot) throws FileNotFoundException {
    airportGraph = new graph<>();
    new Scanner(System.in);
  }
  /**This method allows for a user to input a DOT file in txt format to be read and converted into a simple graph.
  *  This graph is intended to then be used as a mapper of landmarks, such as airports. 
  */
  public graph<String, Double> loadGraph(String filepathToDot) throws FileNotFoundException {
    Scanner scnr = new Scanner(new File(filepathToDot));
    new ArrayList<>();
    while (scnr.hasNextLine()) {
      String line = scnr.nextLine();
      Pattern pattern = Pattern.compile("\"(.*?)\"");
      Matcher matcher = pattern.matcher(line);
      String vertex = null;
      if (matcher.find()) {
        vertex = matcher.group();
        airportGraph.insertVertex(vertex);
      }
      if (matcher.find()) {
        String vertex2 = matcher.group();
        airportGraph.insertVertex(vertex2);
        airportGraph.insertEdge(vertex, vertex2,
            Double.valueOf(line.substring(line.lastIndexOf(" ")+1, line.lastIndexOf("]"))));
        airportGraph.insertEdge(vertex2, vertex,
            Double.valueOf(line.substring(line.lastIndexOf(" ")+1, line.lastIndexOf("]"))));
      }
    }
    return airportGraph;
  }
  public static void main(String[] args) throws FileNotFoundException {
    //arbitrary paths
    GraphLoader newLoader = new GraphLoader("/workspaces/codespaces-blank/GraphLoader.java/graph.dot");
    newLoader.loadGraph("/workspaces/codespaces-blank/GraphLoader.java/graph.dot");
  }
}
