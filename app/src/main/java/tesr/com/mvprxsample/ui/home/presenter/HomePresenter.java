package tesr.com.mvprxsample.ui.home.presenter;

import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import tesr.com.mvprxsample.constants.WebConstants;
import tesr.com.mvprxsample.model.User;
import tesr.com.mvprxsample.network.NetworkManager;

/**
 * Created by livinlawrence on 03/03/18.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;
    private NetworkManager mNetworkManager;
    private CompositeSubscription mComposesubs;

    public HomePresenter(HomeContract.View view) {
        mView = view;
        mNetworkManager= NetworkManager.getInstance();
        mComposesubs=new CompositeSubscription();
    }

    @Override
    public void unSubscribe() {
        mComposesubs.clear();
    }



    @Override
    public void callGetUSerAPI(Map<String,String>map) {
        Subscription subscription=mNetworkManager.getUserData(map, new Subscriber<User>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onFailure(null);
            }

            @Override
            public void onNext(User user) {
                mView.updateUI(user);
            }
        });
        mComposesubs.add(subscription);

    }


}
