package com.zhack.YW.Parse;

import com.zhack.YW.ChatApplication;
import com.zhack.YW.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends ChatApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

//        // Use for troubleshooting -- remove this line for production
//        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
//
//        // Use for monitoring Parse OkHttp traffic
//        // Can be Level.BASIC, Level.HEADERS, or Level.BODY
//        // See http://square.github.io/okhttp/3.x/logging-interceptor/ to see the options.
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        builder.networkInterceptors().add(httpLoggingInterceptor);

        // set applicationId, and server server based on the values in the Heroku settings.
        // clientKey is not needed unless explicitly configured
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("EfggqM19wCuCy4XeOOLXiT7PqMCpqVNVpx7hzKNK") // should correspond to APP_ID env variable
                .clientKey("wN6ZJERctLLWzvI34bSrLpNcEXDyJFptDSLQhyko")  // set explicitly unless clientKey is explicitly configured on Parse server
//                .clientBuilder(builder)
                .server("https://parseapi.back4app.com").build());
    }
}
