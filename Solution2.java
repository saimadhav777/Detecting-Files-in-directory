

import java.util.*;
import java.io.File;
import java.lang.StringBuilder;
public class Solution2 {
    public static void main(String[] args) {
        //Root directory path where v1 will be created
        String rootdirectorypath = "C:/Users/gsaim/Downloads";

        //version input
        String version = "v1";

        //searching for version_directory
        File version_directory = new File(rootdirectorypath + "/" + version);
        if(!version_directory.exists()) {
            System.out.println("Directory does not exist");
        }   

        String Output = output_generator(version_directory,version);
        System.out.println(Output);
            
    }

    public static String output_generator(File version_directory, String version){

        //Hashmap for storing key(market) - value(files)
        Map<String,String[]> Hashmap_market = new HashMap<>();
    
         
        //creating array of folders(market_directory) inside the version_directory.
        File[] market_directories = version_directory.listFiles(File::isDirectory);



        //For-each loop to iterate over each market directory.
        for(File market: market_directories){
            String market_name = market.getName();
            
            
            File reportFile = new File(market,"\\report_"+version+".txt");    
        
            File logFile = new File(market,"\\log_"+version+".txt");

            //Array of log and report files
            String[] files = new String[2];
            files[0] = (reportFile.exists()) ?  reportFile.getName(): "";
            files[1] = (logFile.exists()) ?  logFile.getName(): "";

            Hashmap_market.put(market_name, files);
        }

        //Stringbuiler is used to print the output in json format here.
        StringBuilder Output = new StringBuilder("{\n");
        for (Map.Entry<String, String[]> entry : Hashmap_market.entrySet()) {
            Output.append("\t\"" + entry.getKey() + "\": [\"" + entry.getValue()[0] + "\", \"" + entry.getValue()[1] + "\"],\n");
        }
        Output.deleteCharAt(Output.length() - 2); // Remove trailing comma
        Output.append("\n}");

        return Output.toString();
    }

}


