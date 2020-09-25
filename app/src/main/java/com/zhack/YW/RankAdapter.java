//package com.zhack.YW;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.parse.ParseUser;
//
//import java.util.List;
//import java.util.zip.Inflater;
//
//public class RankAdapter extends RecyclerView.Adapter {
//
//    private static final String TAG = "PostsAdapter";
//
//    private Context context;
//    private List<Post> ranks;
//    private Inflater layoutInflater;
//
//    public RankAdapter(Context context, List<Post> ranks) {
//        this.context = context;
//        this.ranks = ranks;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        int viewType = 1; //Default is 1
//        return viewType;
//    }
//
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view;
//
//        view = layoutInflater.inflate(R.layout.item_rank, parent, false);
//        return new ViewHolderTwo(view);
//    }
//
////        switch (viewType) {
////            case 1: {
////                view = layoutInflater.inflate(R.layout.item_rank, parent, false);
////                return new ViewHolderTwo(view);
////            }
////            default:
////                throw new IllegalArgumentException("Invalid view type");
////        }
//
//
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        RankAdapter.ViewHolderTwo viewHolderTwo = (RankAdapter.ViewHolderTwo) holder;
////        viewHolderTwo.tvUsername.setText("[" + ParseUser.getCurrentUser().getUsername() + "]");
////        viewHolderTwo.tvRank.setText((Integer) ParseUser.getCurrentUser().get("point"));
//
////        switch(position) {
////            case 0: {
////            }
////            break;
////            case 1: {
////                // change view for second position
////            }
////            break;
////            case 2: {
////                // change view for third position
////            }
////            break;
////            default: {
////                // change view for other positions
////            }
////        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return ranks.size();
//    }
//
//    class ViewHolderTwo extends RecyclerView.ViewHolder {
//        TextView tvUsername;
//        ImageView ivImage;
//        TextView tvRank;
//
//        public ViewHolderTwo(@NonNull View itemView) {
//            super(itemView);
//            tvUsername = itemView.findViewById(R.id.tvName);
//            ivImage = itemView.findViewById(R.id.ivImage);
//            tvRank = itemView.findViewById(R.id.tvNumbers);
//
//        }
//    }
//}
