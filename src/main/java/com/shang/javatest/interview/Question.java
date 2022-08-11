package com.shang.javatest.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author baj
 * @creat 2022-06-04 15:32
 */
public class Question {
    public static void main(String[] args) {
        int max = 0;
        Map<String,Integer> map = new HashMap<String, Integer>();
        List<String> strings = new ArrayList<String>();
        String[] str = "Donald Trump has carried the crucial battleground states of Florida, Texas Ohio and Iowa but Joe Biden has won Arizona The contests in Michigan and Wisconsin are close as postal votes are counted Because of the many postal ballots cast in this election some states’ results may not be known for days or even weeks More than 100 million Americans voted early or by post suggesting a record turnout Democrats’ hopes gaining control of the Senate are fading after Republicans held on to closely fought seats in South Carolina and Iowa Control of the Senate may come down to a Georgia special election that will be decided in a run-off in January".toLowerCase().split(" |, |\\.");
        for(String s : str){
            if(map.containsKey(s)){
                map.put(s,map.get(s)+1);
            }else{
                map.put(s,1);
                strings.add(s);
            }
        }
        for(String s :str){
            if(max < map.get(s))
                max = map.get((s));
        }
        for(String s : strings){
            if(max == map.get(s)){
                System.out.println(s+"出现了"+max+"次");
            }
        }
    }

}
