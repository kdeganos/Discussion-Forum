package com.epicodus.discussionforum.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.epicodus.discussionforum.Constants;
import com.epicodus.discussionforum.R;
import com.epicodus.discussionforum.models.Pigeonhole;
import com.epicodus.discussionforum.ui.CommentActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Guest on 7/11/16.
 */
public class FirebasePigeonholeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = FirebasePigeonholeViewHolder.class.getSimpleName();

    View mView;
    Context mContext;

    public FirebasePigeonholeViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindPigeonhole(Pigeonhole pigeonhole) {
        TextView nameTextView = (TextView) mView.findViewById(R.id.pigeonholeNameTextView);
        LinearLayout cats = (LinearLayout) mView.findViewById(R.id.cats);

        Random rnd = new Random();

        int opacity = rnd.nextInt(256);

        while (opacity < 200) {
            opacity += 50;
        }

        cats.setBackgroundColor(Color.argb(opacity, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        nameTextView.setText(pigeonhole.getName());

    }


    @Override
    public void onClick(View view) {
        final ArrayList<Pigeonhole> pigeonholes = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PIGEONHOLES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    pigeonholes.add(snapshot.getValue(Pigeonhole.class));
                }
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, CommentActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("pigeonholes", Parcels.wrap(pigeonholes));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
