package com.epicodus.discussionforum.ui;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.epicodus.discussionforum.Constants;
import com.epicodus.discussionforum.R;
import com.epicodus.discussionforum.adapters.FirebasePigeonholeViewHolder;
import com.epicodus.discussionforum.models.Pigeonhole;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

import static junit.framework.Assert.assertTrue;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mPigeonholeReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.birds) ImageView mBackground;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPigeonholeReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PIGEONHOLES);
        setUpFirebaseAdapter();


        mBackground.setBackgroundResource(R.drawable.background_animated);
        AnimationDrawable frameAnimation = (AnimationDrawable) mBackground.getBackground();

        frameAnimation.start();


    }

    private void setUpFirebaseAdapter() {
        Log.d(TAG, "setUpFirebaseAdapter: XXXXXXXXXX");
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Pigeonhole, FirebasePigeonholeViewHolder>
                (Pigeonhole.class, R.layout.pigeonhole_list_item, FirebasePigeonholeViewHolder.class,
                        mPigeonholeReference) {

            
            
            @Override
            protected void populateViewHolder(FirebasePigeonholeViewHolder viewHolder,
                                              Pigeonhole model, int position) {
                viewHolder.bindPigeonhole(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
