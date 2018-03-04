package tesr.com.mvprxsample.network;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tesr.com.mvprxsample.constants.WebConstants;
import tesr.com.mvprxsample.interfaces.NetworkCallback;
import tesr.com.mvprxsample.model.User;

/**
 * Created by livinlawrence on 03/03/18.
 */

public class NetworkManager {

    private NetworkCallback networkCallback;

    private static final int DEFAULT_TIMEOUT = 15;
    private volatile static NetworkManager INSTANCE;



    private NetworkManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        //Intreceptor can be added to the Okhttpclient to handle retry
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(WebConstants.END_POINT)
                .build();

        networkCallback = retrofit.create(NetworkCallback.class);
    }

    /**
     *
     * @returns instance of the NetworkManager
     */
    public static NetworkManager getInstance() {
        if (INSTANCE == null) {
            synchronized (NetworkManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NetworkManager();
                }
            }
        }
        return INSTANCE;
    }



    public Subscription getUserData(Map<String, String> querymap, Subscriber<User> subscriber) {
        return networkCallback.getUserData(querymap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
