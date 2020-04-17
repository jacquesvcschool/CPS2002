package files;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;

public class Helper {
    private static FileReader fileReader;
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;

    /**
     * Reads file fromresources
     * @param filename filename to read
     * @return String of file contents
     * @throws IOException
     * @throws URISyntaxException
     */
    public static String readResourcesFileAsString(String filename) throws IOException, URISyntaxException {
        //get absolute path
        URL url = Helper.class.getClassLoader().getResource(filename);
        File file = Paths.get(url.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();

        //read file
        Helper.fileReader = new FileReader(absolutePath);
        Helper.bufferedReader = new BufferedReader(Helper.fileReader);
        StringBuilder content = new StringBuilder();
        String line;
        //add contents line by line
        while((line = Helper.bufferedReader.readLine()) != null)
        {
            content.append(line);
        }

        return content.toString();
    }


    /**
     * Method to write file
     * @param directory directory
     * @param filename filename
     * @param content content
     * @throws IOException
     */
    public static void writeFile(String directory, String filename, String content) throws IOException {

        File dir = new File(directory);

        //if directory doe snot exist, create it
        if(!dir.exists())
            dir.mkdir();

        filename = directory + "/" + filename;
        Helper.bufferedWriter = new BufferedWriter(new FileWriter(filename));
        Helper.bufferedWriter.write(content);
        Helper.bufferedWriter.close();
    }

    /**
     * Method to copy directory
     * @param sourceFilename source
     * @param destinationFilename destination
     * @throws IOException
     */
    public static void copyDirectory(String sourceFilename, String destinationFilename) throws IOException {
        File src = new File(sourceFilename);
        File dest = new File(destinationFilename);

        FileUtils.copyDirectory(src, dest);
    }

    /**
     * Method to copy file
     * @param sourceFilename source
     * @param destinationFilename destination
     * @throws IOException
     */
    public static void copyFile(String sourceFilename, String destinationFilename) throws IOException {
        File src = new File(sourceFilename);
        File dest = new File(destinationFilename);

        FileUtils.copyFile(src, dest);
    }

    /**
     * Method to delete directory
     * @param source source
     * @throws IOException
     */
    public static void deleteDirectory(String source) throws IOException {
        File src = new File(source);

        FileUtils.deleteDirectory(new File(source));
    }

}