package fr.neocraft.main.ServeurManager;

import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

public class DailyManager {

	public static int getIdDayByName()
	{ 

		String e = getCurrentDay();
		if(e == "lundi" || e == "monday")
		{
			return EnumDay.B1.getIdDay();
		} else if(e == "mardi" || e == "tuesday")
		{
			return EnumDay.B2.getIdDay();
		}else if(e == "mercredi" || e == "wednesday")
		{
			return EnumDay.B3.getIdDay();
		}else if(e == "jeudi" || e == "thursday")
		{
			return EnumDay.B4.getIdDay();
		}else if(e == "vendredi" || e == "friday")
		{
			return EnumDay.B5.getIdDay();
		}else if(e == "samedi" || e == "saturday")
		{
			return EnumDay.B6.getIdDay();
		}else if(e == "dimanche" || e == "sunday")
		{
			return EnumDay.B7.getIdDay();
		} else {
			System.err.println(e);
			return -1;
			
		}
	}
	public static String getCurrentDay(){

	    String daysArray[] = {"sunday","monday","tuesday", "wednesday","thursday","friday", "saturday"};

	    Calendar calendar = Calendar.getInstance();
	    int day = calendar.get(Calendar.DAY_OF_WEEK);
		if(day == 0)
		{
			day = 6;
		}
		else
		{
			day--;
		}
	    return daysArray[day];

	}
	public static int getNextIdDayByName()
	{ 
		int id = getIdDayByName();
		if(id == 7)
		{
			 id = 1;
			 
		}else {
			id++;
		}
		return id;
	}
	
	public static enum EnumDay {
		B1(1),
		B2(2),
		B3(3),
		B4(4),
		B5(5),
		B6(6),
		B7(7);

	 
	    private int id;
	 
	    EnumDay(int envUrl) {
	        this.id = envUrl;
	    }
	 
	    public int getIdDay() {
	        return this.id;
	    }
	    
	}

	

}
