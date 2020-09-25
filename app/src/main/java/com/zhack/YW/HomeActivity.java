package com.zhack.YW;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.Parse;
import com.zhack.YW.fragments.ChatActivity;
import com.zhack.YW.fragments.Feedfragment;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class HomeActivity extends AppCompatActivity {

    public static final String TAG = "FeedActivity";

    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("EfggqM19wCuCy4XeOOLXiT7PqMCpqVNVpx7hzKNK") // should correspond to Application Id env variable
                .clientKey("wN6ZJERctLLWzvI34bSrLpNcEXDyJFptDSLQhyko")
                .server("https://parseapi.back4app.com").build());

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_chat:
                        fragment = new ChatActivity();
                        break;
                    case R.id.action_feed:
                        fragment = new Feedfragment();
                        break;
//                    case R.id.action_profile:
                    default:
                        fragment = new Feedfragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });


//        ImageButton ibPost = (ImageButton) findViewById(R.id.ibPost);
//        ibPost.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                ComposeFragment compose = new ComposeFragment();
//                fragmentTransaction.replace(R.id.flContainer, compose);
//                fragmentTransaction.commit();
//            }
//        }


        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_feed);

    }
}
