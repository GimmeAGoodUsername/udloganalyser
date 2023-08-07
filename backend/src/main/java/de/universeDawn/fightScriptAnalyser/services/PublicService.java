package de.universeDawn.fightscriptanalyser.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@ApplicationScope
@Service
public class PublicService {

    private Date lastOxi = new Date();


    public long getCurrentOxiDate(){
        long diffInMillies = Math.abs(lastOxi.getTime() - new Date().getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public void setLastOxi(){
        lastOxi = new Date();
    }
}
