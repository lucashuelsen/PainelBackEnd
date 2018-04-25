package com.example.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {

    public static String parseData(Calendar calendar, String formato) {
        DateFormat dateFormat = new SimpleDateFormat(formato);
        return dateFormat.format(calendar.getTime());
    }

    public static String getDayOfWeek(Calendar calendar)
    {
        switch(calendar.get(Calendar.DAY_OF_WEEK))
        {
            case Calendar.MONDAY:
            {
                return "SEGUNDA-FEIRA";
            }
            case Calendar.TUESDAY:
            {
                return "TERÇA-FEIRA";
            }
            case Calendar.WEDNESDAY:
            {
                return "QUARTA-FEIRA";
            }
            case Calendar.THURSDAY:
            {
                return "QUINTA-FEIRA";
            }
            case Calendar.FRIDAY:
            {
                return "SEXTA-FEIRA";
            }
            case Calendar.SATURDAY:
            {
                return "SÁBADO";
            }
            case Calendar.SUNDAY:
            {
                return "DOMINGO";
            }
        }
        
        return null;
    }
    
    public static boolean isWeekend(Calendar calendar){
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }
}
