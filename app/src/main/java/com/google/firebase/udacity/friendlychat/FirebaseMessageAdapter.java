package com.google.firebase.udacity.friendlychat;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.Query;

/**
 * Created by arbalan on 4/3/17.
 */

public class FirebaseMessageAdapter extends FirebaseListAdapter<FriendlyMessage> {

    public FirebaseMessageAdapter(Activity activity, Class<FriendlyMessage> modelClass, int modelLayout, Query ref) {
        super(activity, modelClass, modelLayout, ref);
    }

    @Override
    protected void populateView(View v, FriendlyMessage model, int position) {

        ImageView photoImageView = (ImageView) v.findViewById(R.id.photoImageView);
        TextView messageTextView = (TextView) v.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) v.findViewById(R.id.nameTextView);

        FriendlyMessage message = getItem(position);

        boolean isPhoto = message.getPhotoUrl() != null;
        if (isPhoto) {
            messageTextView.setVisibility(View.GONE);
            photoImageView.setVisibility(View.VISIBLE);
            Glide.with(photoImageView.getContext())
                .load(message.getPhotoUrl())
                .into(photoImageView);
        } else {
            messageTextView.setVisibility(View.VISIBLE);
            photoImageView.setVisibility(View.GONE);
            messageTextView.setText(message.getText());
        }
        authorTextView.setText(message.getName());
    }
}
