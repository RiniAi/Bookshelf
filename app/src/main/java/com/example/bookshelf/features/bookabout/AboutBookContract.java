package com.example.bookshelf.features.bookabout;

import android.os.Bundle;

public interface AboutBookContract {
    interface View {
        void setBookView(String title,
                         String authors,
                         float rating,
                         String publishedDate,
                         String publisher,
                         String pageCount,
                         String lang,
                         String description);

        void setImage(String image);

        void setBrokenImage();
    }

    interface Presenter {
        void onStartWitchData(Bundle bundle);
    }
}
