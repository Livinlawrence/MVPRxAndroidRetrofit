package tesr.com.mvprxsample.ui.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tesr.com.mvprxsample.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.home_container, new HomeFragment(), HomeFragment.TAG).commit();
)
    }
}
