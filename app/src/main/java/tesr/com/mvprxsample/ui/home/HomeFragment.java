package tesr.com.mvprxsample.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tesr.com.mvprxsample.R;
import tesr.com.mvprxsample.model.User;
import tesr.com.mvprxsample.ui.home.presenter.HomeContract;
import tesr.com.mvprxsample.ui.home.presenter.HomePresenter;

/**
 * Created by livinlawrence on 03/03/18.
 */

public class HomeFragment extends Fragment implements HomeContract.View{
    public final static String TAG=HomeFragment.class.getSimpleName();

    Unbinder mUnbinder;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private HomePresenter mHomePresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recycler_view, container, false);
        mHomePresenter = new HomePresenter(this);
        mHomePresenter.callGetUSerAPI(buildQuery());
        mUnbinder = ButterKnife.bind(this, rootView);
    }

    @Override
    public void updateUI(User user) {

    }

    @Override
    public void onFailure(Error error) {

    }

    @Override
    public void onDestroy() {
        mUnbinder.unbind();
        mHomePresenter.unSubscribe();
        super.onDestroy();
    }
}
