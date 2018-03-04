package tesr.com.mvprxsample.ui.home.presenter;

import java.util.Map;

import tesr.com.mvprxsample.model.User;

/**
 * Created by livinlawrence on 03/03/18.
 */

public class HomeContract {

    interface View{
        void updateUI(User user);
        void onFailure(Error error);
    }

    interface Presenter{
        void unSubscribe();
        void callGetUSerAPI(Map<String,String> map);
    }
}
