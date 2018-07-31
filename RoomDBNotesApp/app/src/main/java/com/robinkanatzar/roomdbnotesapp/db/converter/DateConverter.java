package com.robinkanatzar.roomdbnotesapp.db.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {

        @TypeConverter
        public static Date longToDate(Long value) {
            if(value != null) {
                return new Date(value);
            }
            return null;
        }

        @TypeConverter
        public static Long dateToLong(Date value) {
            if(value != null) {
                return value.getTime();
            }
            return null;
        }
}
