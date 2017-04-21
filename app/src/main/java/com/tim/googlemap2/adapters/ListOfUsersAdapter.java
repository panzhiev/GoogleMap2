package com.tim.googlemap2.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tim.googlemap2.R;
import com.tim.googlemap2.model.User;

import java.util.List;

/**
 * Created by Panzhiev on 21.04.2017.
 */

public class ListOfUsersAdapter extends ArrayAdapter<User> {
    private Activity mContext;
    List<User> mUsers;

    public ListOfUsersAdapter(Activity context, List<User> users) {
        super(context, R.layout.item_user_list, users);
        this.mContext = context;
        this.mUsers = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.item_user_list, null, true);

        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmail);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);

        User user = mUsers.get(position);
        textViewName.setText(user.getName());
        textViewEmail.setText(user.getEmail());

        return listViewItem;
    }
}
