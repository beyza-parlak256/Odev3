/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package odev3;

import java.util.ArrayList;
import static spark.Spark.get;
import static spark.Spark.post;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static <Logger> void main(String[] args) {
        System.out.println(new App().getGreeting());

        Logger logger = (Logger) LogManager.getLogger(App.class);
        ((org.apache.logging.log4j.Logger) logger).error("hello world");

        var port = Integer.parseInt(System.getenv("PORT"));
        port(port);
        ((org.apache.logging.log4j.Logger) logger).error("Current port number:" + port);

        get("/", (req, res) -> "Hello, world!");

        get("/compute",
                (rq, rs) -> {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("result", "not computed yet!");
                    return new ModelAndView(map, "compute.mustache");
                },
                new MustacheTemplateEngine());

        post("/compute", (req, res) -> {
            String input1 = req.queryParams("input1");
            java.util.Scanner sc1 = new java.util.Scanner(input1);
            sc1.useDelimiter("[;\r\n]+");
            java.util.ArrayList<Integer> inpuList = new java.util.ArrayList<>();
            while (sc1.hasNext()) {
                int value = Integer.parseInt(sc1.next().replaceAll("\\s", ""));
                inpuList.add(value);
            }
            sc1.close();

            System.out.println(inpuList);

            String input2 = req.queryParams("input2").replaceAll("\\s", "");
            int input2AsInt = Integer.parseInt(input2);

            boolean result = App.search(inpuList, input2AsInt);
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            map.put("result", result);
            return new ModelAndView(map, "compute.mustache");

            
        },
                new MustacheTemplateEngine());
    }

    private static void port(int port) {
    }

    public static boolean search(ArrayList<Integer> array, int e) {
        System.out.println("inside search");
        if (array == null)
            return false;

        for (int elt : array) {
            if (elt == e)
                return true;
        }
        return false;
    }
}
