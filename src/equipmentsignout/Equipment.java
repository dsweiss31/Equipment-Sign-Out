/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipmentsignout;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author DSW029
 */
public class Equipment 
{
    String name, type, date, purpose, usage, otime, itime, inout, teachApp;
    int sMin, sHour, eMin, eHour;
    boolean isSignedOut, approved;
    ArrayList<Equipment> equipment = new ArrayList<Equipment>();
    Charset charset = Charset.forName("US-ASCII");
    File fileName = new File("L:\\TechEd-Engineering\\Equipment Sign Out\\EquipmentSignOut\\dataFile.csv");
    String path = "L:\\TechEd-Engineering\\Equipment Sign Out\\EquipmentSignOut\\dataFile.csv";
    //File fileName = new File("HHS0772-FS1\\Studentshd\\DSW029\\dataFile.csv");
    
    public Equipment()
    {
        fileName.setWritable(true);
        fileName.setExecutable(true);
        fileName.setReadable(true);
    }
    
    public Equipment(ArrayList<Equipment> ray)
    {
        equipment = ray;
    }
    
    public Equipment(String ty)
    {
        type = ty;
    }
    
    public Equipment(String n, String ty, String d, String p, String io)
    {
        name = n;
        type = ty;
        date = d;
        purpose = p;
        inout = io;
        if(inout.toUpperCase().equals("OUT"))
        {
            isSignedOut = true;
        }
        if(inout.toUpperCase().equals("IN"))
        {
            isSignedOut = false;
        }
    }
    
    public Equipment(String n, String d, String ty, String p, String use, String time, String app, String io)
    {
        name = n;
        type = ty;
        date = d;
        purpose = p;
        usage = use;
        inout = io;
        otime = time;
        teachApp = app;
    }
    
    public Equipment(String n, String d, String ty, String p, String use, String time, String app, String io, String in)
    {
        name = n;
        type = ty;
        date = d;
        purpose = p;
        usage = use;
        inout = io;
        otime = time;
        teachApp = app;
        itime = in;
    }
    
    public Equipment(String n, String d,String ty, String p, String use, String tim, String app)
    {
        name = n;
        type = ty;
        otime = tim;
        date = d;
        purpose = p;
        usage = use;
        teachApp = app;
        
        try   //sets the inout variable by checking the data file
        {
           Scanner data = new Scanner(fileName);
           while(data.hasNextLine())
           {
                String line = data.nextLine();
                String[] arr = line.split(",");
                if(arr.length == 9)
                {
                    if(name.equals(arr[0])&&type.equals(arr[2])&&(arr[7].toUpperCase().equals("OUT")))
                    {
                        inout = "out";
                    }
                    else if(name.equals(arr[0])&&type.equals(arr[2])&&(arr[7].toUpperCase().equals("IN")))
                    {
                        inout = "in";
                    }
                }
           }
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage()+"\n");
        }
    }
    
    //gives the string statement when successfully signing out an item
    public String getUse()
    {
        String s = type+" was successfully signed out by "+name+"\nTime Out: "+otime+" on "+date;
        return s;
    }
    
    //reads the data file to see if the equipment is available to be signed out
    public boolean isAvailable(Equipment equip)
    {
        boolean tf = true;
        try
        {
            Scanner data = new Scanner(fileName);
            while(data.hasNextLine())
            {
                String line = data.nextLine();
                String[] arr = line.split(","); //takes a line and puts the elements separated by , into an array
                System.out.println(equip.getType());
                if(arr.length == 9)
                {
                    if(arr[2].toUpperCase().equals(equip.getType().toUpperCase())&&(arr[7].toUpperCase().equals("OUT")))
                    {
                        tf = false;
                    }
                }
                else if(arr.length == 0)
                {
                    tf = true;        
                }
                else
                {
                    tf = false;
                }
            }
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("Unable to open file '" + fileName + "'");
            System.out.println(System.getProperty("user.dir"));
            System.out.println(new File("dataStorage.txt").getAbsolutePath());
            tf = false;
        }
        catch (NullPointerException e)
        {
            System.out.print("NullPointerException");
            tf = false;
        }
        return tf;
    }
    
    //signs in the item by rewriting the data file, with the desired line changing from "out" to "in"
    public void signIn(String name, String type)
    {
        for(int i = 0; i < equipment.size(); i++)
        {
            if(equipment.get(i).getName().toUpperCase().equals(name.toUpperCase())&&equipment.get(i).getType().toUpperCase().equals(type.toUpperCase()))
            {
                equipment.get(i).setSignIn();    
                if(Integer.parseInt(time().substring(0,2))<=11)
                {
                    itime = time().substring(0,2) + ":";
                    itime += time().substring(3);
                    itime += " AM";
                    equipment.get(i).setItime(itime);
                }
                if(Integer.parseInt(time().substring(0,2))>12)
                {
                    itime = ""+(-12+Integer.parseInt(time().substring(0,2))) + ":";
                    itime += time().substring(3);
                    itime += " PM";
                    equipment.get(i).setItime(itime);
                }
                if(Integer.parseInt(time().substring(0,2))==0)
                {
                    itime = "12:";
                    itime += time().substring(3) ;
                    itime += " AM";
                    equipment.get(i).setItime(itime);
                }
                if(Integer.parseInt(time().substring(0,2))==12)
                {
                    itime = "12:";
                    itime += time().substring(3);
                    itime += " PM";
                    equipment.get(i).setItime(itime);
                } 
            }
        }
        try
        {
            FileWriter writer = new FileWriter(fileName);
            for(int i = 0; i < equipment.size(); i++)
            {
                if(i == equipment.size()-1)
                {
                    writer.write(equipment.get(i).toString());
                    writer.flush();
                }
                else
                {
                    writer.write(equipment.get(i).toString()+"\n");
                    writer.flush();
                }
            }
        }
        catch (IOException e)
        {
            System.out.print(e.getMessage()+"\n");
        }
    }
    
    //changes the string at a given line and spot in the data file
    public void changeData(String s, int line, int spot)
    {
        readEquip();
        try
        {
            FileWriter writer = new FileWriter(fileName);
            for(int i = 0; i < line; i++)
            {
                writer.write(equipment.get(i).toString()+"\n");
                writer.flush();
            }
            for(int i = line; i < equipment.size(); i++)
            {
                if(i == line && spot == 0)
                {
                    writer.write(s+","+equipment.get(i).getDate()+","+equipment.get(i).getType()+","+equipment.get(i).getPurpose()+","+equipment.get(i).getUsage()+","+equipment.get(i).getOtime()+","+equipment.get(i).getTeachApp()+","+equipment.get(i).getInout()+","+equipment.get(i).getItime()+"\n");
                    writer.flush();
                }
                else if(i == line && spot == 1)
                {
                    writer.write(equipment.get(i).getName()+","+s+","+equipment.get(i).getType()+","+equipment.get(i).getPurpose()+","+equipment.get(i).getUsage()+","+equipment.get(i).getOtime()+","+equipment.get(i).getTeachApp()+","+equipment.get(i).getInout()+","+equipment.get(i).getItime()+"\n");
                    writer.flush();
                }
                else if(i == line && spot == 2)
                {
                    writer.write(equipment.get(i).getName()+","+equipment.get(i).getDate()+","+s+","+equipment.get(i).getPurpose()+","+equipment.get(i).getUsage()+","+equipment.get(i).getOtime()+","+equipment.get(i).getTeachApp()+","+equipment.get(i).getInout()+","+equipment.get(i).getItime()+"\n");
                    writer.flush();
                }
                else if(i == line && spot == 3)
                {
                    writer.write(equipment.get(i).getName()+","+equipment.get(i).getDate()+","+equipment.get(i).getType()+","+s+","+equipment.get(i).getUsage()+","+equipment.get(i).getOtime()+","+equipment.get(i).getTeachApp()+","+equipment.get(i).getInout()+","+equipment.get(i).getItime()+"\n");
                    writer.flush();
                }
                else if(i == line && spot == 4)
                {
                    writer.write(equipment.get(i).getName()+","+equipment.get(i).getDate()+","+equipment.get(i).getType()+","+equipment.get(i).getPurpose()+","+s+","+equipment.get(i).getOtime()+","+equipment.get(i).getTeachApp()+","+equipment.get(i).getInout()+","+equipment.get(i).getItime()+"\n");
                    writer.flush();
                }
                else if(i == line && spot == 5)
                {
                    writer.write(equipment.get(i).getName()+","+equipment.get(i).getDate()+","+equipment.get(i).getType()+","+equipment.get(i).getPurpose()+","+equipment.get(i).getUsage()+","+s+","+equipment.get(i).getTeachApp()+","+equipment.get(i).getInout()+","+equipment.get(i).getItime()+"\n");
                    writer.flush();
                }
                else if(i == line && spot == 6)
                {
                    writer.write(equipment.get(i).getName()+","+equipment.get(i).getDate()+","+equipment.get(i).getType()+","+equipment.get(i).getPurpose()+","+equipment.get(i).getUsage()+","+equipment.get(i).getOtime()+","+s+","+equipment.get(i).getInout()+","+equipment.get(i).getItime()+"\n");
                    writer.flush();
                }
                else if(i == line && spot == 7)
                {
                    writer.write(equipment.get(i).getName()+","+equipment.get(i).getDate()+","+equipment.get(i).getType()+","+equipment.get(i).getPurpose()+","+equipment.get(i).getUsage()+","+equipment.get(i).getOtime()+","+equipment.get(i).getTeachApp()+","+s+","+equipment.get(i).getItime()+"\n");
                    writer.flush();
                }
                else if(i == line && spot == 8)
                {
                    writer.write(equipment.get(i).getName()+","+equipment.get(i).getDate()+","+equipment.get(i).getType()+","+equipment.get(i).getPurpose()+","+equipment.get(i).getUsage()+","+equipment.get(i).getOtime()+","+equipment.get(i).getTeachApp()+","+equipment.get(i).getInout()+","+s+"\n");
                    writer.flush();
                }
                else if(i == (equipment.size()-1))
                {
                    writer.write(equipment.get(i).toString());
                    writer.flush();
                }
                else
                {
                    writer.write(equipment.get(i).toString()+"\n");
                    writer.flush();
                }    
            }
                    
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage()+"\n");
        }
    }
    
    //reads and rewrites the data, adding the new line at the end
    public void addData(String n, String d,String ty, String p, String use, String tim, String app)
    {
        readEquip();
        try
        {
            FileWriter writer = new FileWriter(fileName);
            for(int i = 0; i < equipment.size(); i++)
            {
                if(i == equipment.size()-1)
                {
                    writer.write(equipment.get(i).toString());
                    writer.flush();
                }
                else
                {
                    writer.write(equipment.get(i).toString()+"\n");
                    writer.flush();
                }
            }
            if(isAvailable(new Equipment(n, d, ty, p, use, tim, app))&&!equipment.isEmpty())
            {
                writer.write("\n"+n+","+d+","+ty+","+p+","+use+","+tim+","+app+",out,N/A");
                writer.flush();
            }
            else if(isAvailable(new Equipment(n, d, ty, p, use, tim, app))&&equipment.isEmpty())
            {
                writer.write(n+","+d+","+ty+","+p+","+use+","+tim+","+app+",out,N/A");
                writer.flush();
            }
        }
        catch (IOException e)
        {
            System.out.print(e.getMessage()+"\n");
        }
    }
    
    //rewrites the data and adds a new line at the end, with the io and in variables
    public void addDatas(String n, String d,String ty, String p, String use, String tim, String app, String io, String in)
    {
        readEquip();
        try
        {
            FileWriter writer = new FileWriter(fileName);
            for(int i = 0; i < equipment.size(); i++)
            {
                if(i == equipment.size()-1)
                {
                    writer.write(equipment.get(i).toString());
                    writer.flush();
                }
                else
                {
                    writer.write(equipment.get(i).toString()+"\n");
                    writer.flush();
                }
            }
            if(equipment.isEmpty())
            {
                writer.write(n+","+d+","+ty+","+p+","+use+","+tim+","+app+","+io+","+in);
                writer.flush();
            }
            else
            {
                writer.write("\n"+n+","+d+","+ty+","+p+","+use+","+tim+","+app+","+io+","+in);
                writer.flush();
            }
        }
        catch (IOException e)
        {
            System.out.print(e.getMessage()+"\n");
        }
    }
    
    //makes sure the string is an integer
    public static boolean isInteger(String s)
    {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
    
    public int day()
    {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.DATE);
    }
    
    public int month()
    {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.MONTH);
    }
    
    public String date()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    
    public String time()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime now = LocalTime.now();
        return dtf.format(now);
    }
    
    public String getType()
    {
        return type;
    }
    
    //reads the data by adding objects to the arraylist
    public void readEquip()
    {        
        try 
        {
            Scanner data = new Scanner(fileName);
            
            while(data.hasNextLine())
            {
                String line = data.nextLine();
                
                if(line == null)
                {
                    return;
                }
                
                String[] equip = line.split(",");
                
                if(equip.length == 9)
                {
                    Equipment equips = new Equipment(equip[0], equip[1], equip[2], equip[3], equip[4], equip[5], equip[6], equip[7], equip[8]);
                    equipment.add(equips);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.print("bad");
        }
    }
    
    public void setSignIn()
    {
        isSignedOut = false;
        inout = "in";
    }
    
    public void setSignOut()
    {
        isSignedOut = true;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getoTime()
    {
        return otime;
    }
    
    public String getiTime()
    {
        return itime;
    }
    
    public String getDate()
    {
        return date;
    }
    
    public String getPurpose()
    {
        return purpose;
    }
    
    public String getUsage()
    {
        return usage;
    }
    
    public ArrayList getEquip()
    {
        return equipment;
    }
    
    public boolean getSign()
    {
        return isSignedOut;
    }
    
    public void setName(String n)
    {
        name = n;
    }
    
    public String getInout()
    {
        return inout;
    }
    
    public String getOtime()
    {
        return otime;
    }
    
    public String getTeachApp()
    {
        return teachApp;
    }
    
    public String getItime()
    {
        return itime;
    }
    
    public void setItime(String in)
    {
        itime = in;
    }
    
    public String toString()
    {
        return getName()+","+getDate()+","+getType()+","+getPurpose()+","+getUsage()+","+getoTime()+","+getTeachApp()+","+getInout()+","+getItime();
    }
    
    public boolean canRead()
    {
        return fileName.canRead();
    }
    
    public boolean canWrite()
    {
        return fileName.canWrite();
    }
    
    public boolean canExecute()
    {
        return fileName.canExecute();
    }
}
