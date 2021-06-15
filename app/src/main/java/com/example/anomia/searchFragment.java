package com.example.anomia;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anomia.Model.Community;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import Adapter.CommunityAdapter;

public class searchFragment extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CommunityAdapter communityAdapter;
    private List<Community> mCommunityList;

    EditText search_bar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root;
        View view = inflater.inflate(R.layout.activity_community, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_community);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        search_bar = view.findViewById(R.id.search_bar);

        mCommunityList = new ArrayList<>();
        communityAdapter = new CommunityAdapter(getBaseContext(), mCommunityList);

        recyclerView.setAdapter(communityAdapter);

        readCommunity();
        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                search_community(charSequence.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        
        return  view;
    }

    private void search_community(String s){
        Query query = FirebaseFirestore.getInstance().collection("community").orderBy("name").startAt(s).endAt(s+"\uf0ff");

        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                mCommunityList.clear();
                for(DocumentSnapshot snapshots:value.getDocuments()){
                    Community community = (Community) snapshots.get(String.valueOf(Community.class));
                    mCommunityList.add(community);
                }
                communityAdapter.notifyDataSetChanged();
            }
        });
    }

    private void readCommunity() {
        CollectionReference reference = FirebaseFirestore.getInstance().collection("community");
        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (search_bar.getText().toString().equals("")) {
                    mCommunityList.clear();
                    for (QueryDocumentSnapshot value : task.getResult()) {
                        Community community = (Community) value.get(String.valueOf(Community.class));
                        mCommunityList.add(community);
                    }
                    communityAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    /*addSnapshotListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (search_bar.getText().toString().equals("")){
                    mCommunityList.clear();
                    for (DataSnapshot value: snapshot.getChildren()){
                        Community community = value.getValue(Community.class);
                        mCommunityList.add(community);
                    }
                    communityAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/
}
