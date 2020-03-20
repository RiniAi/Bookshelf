package com.example.bookshelf.features.bookabout;

import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.base.BaseView;

public interface AboutBookContract {
    interface View extends BaseView {
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

    interface Presenter extends BasePresenter {
    }
}
