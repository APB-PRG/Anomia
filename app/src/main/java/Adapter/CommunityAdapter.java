package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.anomia.Model.Community;
import com.example.anomia.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder>{

    private Context mContext;
    private List<Community> mCommunity;

    private DatabaseReference firebaseCommunity;

    public CommunityAdapter(Context mContext, List<Community> mCommunity) {
        this.mContext = mContext;
        this.mCommunity = mCommunity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.community_item, parent, false);
        return new CommunityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        firebaseCommunity = FirebaseDatabase.getInstance().getReference();

        final Community community = mCommunity.get(position);

        holder.btn_follow.setVisibility(View.VISIBLE);

        holder.name_community.setText(community.getName());
        holder.nbr_follower.setText(community.getNbr_follower());


        //TODO creer fabrication communaute, ensuite video "INSTAGRAM App with Firebase - Part 4" 13 minutes 30 secondes
        if (community.getId().equals(firebaseCommunity.child("community").child(community.getId()))){
            holder.btn_follow.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mCommunity.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name_community;
        public TextView nbr_follower;
        public CircleImageView image_community;
        public Button btn_follow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name_community = itemView.findViewById(R.id.comunity_name);
            nbr_follower = itemView.findViewById(R.id.nbr_follower);
            image_community = itemView.findViewById(R.id.image_community);
            btn_follow = itemView.findViewById(R.id.btn_following_community);
        }
    }

    private void isFollowing(String id_community, Button button){
       // DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                //.child()
    }

}
