package com.kcm.cloneapp.home;

import java.util.List;

import com.kcm.cloneapp.abs.BasePresenter;
import com.kcm.cloneapp.abs.BaseView;
import com.kcm.cloneapp.home.models.AppInfo;

/**
 * @author Lody
 * @version 1.0
 */
/*package*/ class ListAppContract {
    interface ListAppView extends BaseView<ListAppPresenter> {

        void startLoading();

        void loadFinish(List<AppInfo> infoList);
    }

    interface ListAppPresenter extends BasePresenter {

    }
}
