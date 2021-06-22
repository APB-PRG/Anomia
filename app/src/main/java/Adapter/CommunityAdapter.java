package Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.anomia.Model.Community;
import com.example.anomia.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder>{
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String id_Community = "id";

    private Context mContext;
    private List<Community> mCommunity;
    private FirebaseAuth mAuth;

    //private firebaseCommunity = FirebaseFirestore.getInstance().collection("community").get();
    private CollectionReference firebaseCommunity = FirebaseFirestore.getInstance().collection("community");

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

        final Community community = mCommunity.get(position);

        holder.btn_follow.setVisibility(View.VISIBLE);

        holder.name_community.setText(community.getName());
        holder.nbr_follower.setText(community.getNbr_follower());
        isFollowing(mAuth.getUid(), holder.btn_follow);


        //TODO creer fabrication communaute, ensuite video "INSTAGRAM com.example.anomia.App with Firebase - Part 4" 13 minutes 30 secondes
        if (community.getId().equals(firebaseCommunity.document().getId())){
            holder.btn_follow.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences =  mContext.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(id_Community, firebaseCommunity.getId());
                editor.apply();
            }
        });

        holder.btn_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.btn_follow.getText().toString().equals("follow")){
                    FirebaseFirestore.getInstance().collection("follow").document(mAuth.getUid()).collection(firebaseCommunity.document().getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCommunity.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name_community;
        public TextView nbr_follower;
        public Button btn_follow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name_community = itemView.findViewById(R.id.comunity_name);
            nbr_follower = itemView.findViewById(R.id.nbr_follower);
            btn_follow = itemView.findViewById(R.id.btn_following_community);
        }
    }

    private void isFollowing(String id_community, Button button){
        mAuth = FirebaseAuth.getInstance();
        Query reference_follow = FirebaseFirestore.getInstance().collection("follow").whereEqualTo("id", mAuth.getUid()).whereEqualTo("following", firebaseCommunity.document().getId());
        reference_follow.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.equals(id_community)){
                    button.setText("following");
                }else{
                    button.setText("follow");
                }
            }
        });
    }

}
