package controllers;

import fs.FileFinder;
import fs.Files;
import play.Play;
import play.mvc.Controller;

import java.io.File;
import java.util.regex.Pattern;

public class Application extends Controller {

    private static String filesRootPath = Play.configuration.getProperty("files.dir");
    private static File pathToFiles = new File(Play.applicationPath, filesRootPath);

    public static void index() {
        Files files = new FileFinder(pathToFiles, Pattern.compile(".*\\.rtf$")).find();
        renderArgs.put("filesRootPath", filesRootPath);
        render(files);
    }

}