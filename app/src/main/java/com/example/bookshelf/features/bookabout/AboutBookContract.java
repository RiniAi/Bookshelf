package com.example.bookshelf.features.bookabout;

import android.os.Bundle;

import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.base.BaseView;

public interface AboutBookContract {
    interface View extends BaseView {
        void setTitle(String title);

        void setAuthors(String authors);

        void setImage(String image);

        void setBrokenImage();

        void setRating(float rating);

        void setPublishedDate(String publishedDate);

        void setPublisher(String publisher);

        void setPageCount(String pageCount);

        void setLanguage(String lang);

        void setDescription(String description);
    }

    interface Presenter extends BasePresenter {
        void getBook(Bundle bundle);
    }
}
