package com.contactserver.contactutils;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 16/9/20.
 */
public class CommonUtils {
    /**
     * 输出格式
     */
    public static String formatNotes( Socket client, String notes ) {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        String currentTime = sdf.format( new Date( ) );
        return client + "(" + currentTime + ")\r\n" + notes + "\r\n";
    }

    /**
     * Mills转date
     */
    public static String formatTime( long millis ) {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Date date = new Date( millis );
        String dateTime = sdf.format( date );
        return dateTime;
    }
}
