package com.example.bookshelf.database;

import androidx.room.TypeConverter;

public class BookStatusConverter {
    @TypeConverter
    public static String fromStatusToString(Book.BookStatus bookStatus) {
        switch (bookStatus) {
            case IN_THE_PROCESS_OF_READING:
                return "In the process of reading";
            case PLAN_READING:
                return "Plan to read";
            case FINISH_READING:
                return "Finish reading";
            case QUIT_READING:
                return "Quit reading";
            default:
                return "In the process of reading";
        }
    }

    @TypeConverter
    public static Book.BookStatus fromStringToStatus(String status) {
        switch (status) {
            case "In the process of reading":
                return Book.BookStatus.IN_THE_PROCESS_OF_READING;
            case "Plan to read":
                return Book.BookStatus.PLAN_READING;
            case "Finish reading":
                return Book.BookStatus.FINISH_READING;
            case "Quit reading":
                return Book.BookStatus.QUIT_READING;
            default:
                return Book.BookStatus.IN_THE_PROCESS_OF_READING;
        }
    }
}
