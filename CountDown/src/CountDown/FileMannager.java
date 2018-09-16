package CountDown;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Stack;

public class FileMannager {
    public static Deadline loadCountdowns(){
        String coundDownName=null;

        int[] holder= new int[6];
        String userHomeFolder = System.getProperty("user.home")+ "\\Desktop";
        File countDownSaves = new File(userHomeFolder, "Countdowns.txt");
        if(countDownSaves.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(countDownSaves));
                String line = null;
                try {
                    while ((line = in.readLine()) != null) {

                        coundDownName= line;
                        //System.out.println(coundDownName);
                        String countDownDate = in.readLine();
                        System.out.println(countDownDate);
                        //int year,month,date,hrs,min,sec;

                        for (int i=0;i<6; i++) {
                            int getnextpos;
                            String inputSub=null;
                            if (i<5) {
                                 getnextpos = countDownDate.indexOf(",");
                                 inputSub= countDownDate.substring(0, getnextpos);
                                countDownDate=countDownDate.substring(getnextpos+1);
                                 //System.out.println(inputSub);
                                //System.out.println(countDownDate);

                            }
                            else{
                                //System.out.println(countDownDate);
                                inputSub=countDownDate;

                            }
                            holder[i]= Integer.parseInt(inputSub);


                            //System.out.println(holder[i]);
                        }





                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        /*
        System.out.println(holder[0]);
        System.out.println(holder[1]);
        System.out.println(holder[2]);
*/
        return new Deadline(coundDownName, new GregorianCalendar(holder[0], holder[1] - 1, holder[2], holder[3], holder[4], holder[5]));
    }


    public static Stack<String> listFilesForFolder(final File folder) {

        Stack<String> results= new Stack<String>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                String str=fileEntry.getName();
                results.push(str);
            }
        }
        return results;

    }


}
