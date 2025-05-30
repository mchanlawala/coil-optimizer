/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dmtech.coiloptimizer.planning.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
    public static String string2Date2String(String date,String inputDateFormat,String outputDateFormat) {
        String returnDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat(inputDateFormat);
        SimpleDateFormat sdf1 = new SimpleDateFormat(outputDateFormat);
        Date deliveryDate = new Date();
        try {
            if (date != null) {
                deliveryDate = sdf.parse(date);
                returnDate = sdf1.format(deliveryDate);
            }

        } catch (ParseException ex) {
            return null;
        }

        return returnDate;
    }
}
