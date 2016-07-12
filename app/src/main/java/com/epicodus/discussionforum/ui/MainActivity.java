package com.epicodus.discussionforum.ui;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mPigeonholeReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private AnimationDrawable frameAnimation;
    private boolean newCatFormShowing =  false;
    public static final String TAG = MainActivity.class.getSimpleName();
    private EditText myEditText;


    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.birds) ImageView mBackground;
    @Bind(R.id.newCategory) Button mNewCatButton;
    @Bind(R.id.newCategoryArea) LinearLayout mLayout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPigeonholeReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PIGEONHOLES);
        setUpFirebaseAdapter();


        mBackground.setBackgroundResource(R.drawable.background_animated);
        frameAnimation = (AnimationDrawable) mBackground.getBackground();
        mNewCatButton.setOnClickListener(this);

        myEditText = new EditText(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        frameAnimation.start();
        super.onWindowFocusChanged(hasFocus);
    }

    private void setUpFirebaseAdapter() {

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

    @Override
    public void onClick(View view) {
        if (view == mNewCatButton){
            if (!newCatFormShowing){
                 // Pass it an Activity or Context
                myEditText.setLayoutParams(new ViewGroup.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT)); // Pass two args; must be LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, or an integer pixel value.
                mLayout.addView(myEditText);
                myEditText.setTextColor(Color.parseColor("#ffffff"));
                myEditText.getBackground().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_ATOP);
                myEditText.setShadowLayer(9.2f, -1, 1, Color.parseColor("#000000"));
                mNewCatButton.setText("Submit");
                newCatFormShowing =  true;
            } else {
                Pigeonhole newPigeonhole = new Pigeonhole(myEditText.getText().toString());


                DatabaseReference pigeonholeRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PIGEONHOLES);
                pigeonholeRef.push().setValue(newPigeonhole);

                newCatFormShowing =  false;
                myEditText.setText("");
                mLayout.removeAllViews();
                mNewCatButton.setText("Add a Category");
            }
        }

    }
}
