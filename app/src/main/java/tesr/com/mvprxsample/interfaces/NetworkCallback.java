package tesr.com.mvprxsample.interfaces;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;
import tesr.com.mvprxsample.model.User;

/**
 * Created by livinlawrence on 03/03/18.
 */

public interface NetworkCallback {

    @GET
    Observable<User> getUserData(@QueryMap Map<String, String> keyMap);
}
