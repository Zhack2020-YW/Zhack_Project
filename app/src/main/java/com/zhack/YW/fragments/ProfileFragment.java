package com.zhack.YW.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zhack.YW.ComposeAdapter;
import com.zhack.YW.Post;
import com.zhack.YW.PostsAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.zhack.YW.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Feedfragment {

//    @Override
//    protected void queryPosts() {
//        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
//        query.include(Post.KEY_USER);
//        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
//        query.setLimit(20);
//        query.addDescendingOrder(Post.KEY_CREATED_KEY);
//        query.findInBackground(new FindCallback<Post>() {
//            @Override
//            public void done(List<Post> posts, ParseException e) {
//                if (e != null) {
//                    Log.e(TAG, "Issue with getting posts", e);
//                    return;
//                }
//                for (Post post : posts) {
//                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
//                }
//                allPosts.addAll(posts);
//                adapter.notifyDataSetChanged();
//            }
//        });
//    }

    TextView tvUsername;
    TextView tvEmail;
    TextView tvName;
    RecyclerView rvPosts;
    RecyclerView rvCompose;
    ImageView ivProfile;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvUsername = view.findViewById(R.id.tvUserName);
        tvName = view.findViewById(R.id.tvName);
        rvPosts = view.findViewById(R.id.rvPosts);
        rvCompose = view.findViewById(R.id.rvCompose);
        ivProfile = view.findViewById(R.id.ivProfile);

        tvUsername.setText(ParseUser.getCurrentUser().getUsername());
        tvName.setText(ParseUser.getCurrentUser().getString("neighbour"));


        allPosts = new ArrayList<>();
        adapter2 = new ComposeAdapter(getContext());
        adapter = new PostsAdapter(getContext(), allPosts);
        rvCompose.setAdapter(adapter2);
        rvCompose.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCompose.getLayoutManager().scrollToPosition(0);

//        queryPosts();

        Glide.with(this).load(getProfileUrl(ParseUser.getCurrentUser().getInt("number"))).override(270,270).circleCrop().into(ivProfile);
    }
    @Override
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private static String getProfileUrl(final int userNumber) {
        Log.d("Number: ", ""+userNumber);
        return "https://robohash.org/honey?set=set" + userNumber;
    }
}
