/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrummaster;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author nmacisaac
 */
public class HistoryFileParser {
    
    Map<Integer, Candidate> parseFileForHistory(String fileName) throws IOException, NumberFormatException {
        Map<Integer, Candidate> history = new HashMap<>();
        
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(fileName);
        props.load(fis);
        Enumeration<?> propertyNames = props.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String key = (String) propertyNames.nextElement();
            if (!history.containsKey(Integer.parseInt(key))) {
                String candidateFirstName = props.getProperty(key);
                Candidate fileCandidate = null;
                for (Candidate candidate : Candidate.values()) {
                    if (candidateFirstName.equalsIgnoreCase(candidate.getFirstName())) {
                        fileCandidate = candidate;
                    }
                }
                if (fileCandidate != null) {
                    history.put(Integer.parseInt(key), fileCandidate);
                    if (Constants.DEBUG) {
                        System.out.println("Iteration: " + key + " Candidate: " + fileCandidate.getName() + " --- map: " + history.get(Integer.parseInt(key)).getName());
                    }
                }                
            }
        }
        if (history.isEmpty()) {
            throw new IOException("No past iterations found");
        }
        return history;
    }
    
}
