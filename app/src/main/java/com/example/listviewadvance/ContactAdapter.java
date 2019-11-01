package com.example.listviewadvance;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import org.w3c.dom.Text;

import java.util.List;

public class ContactAdapter extends BaseAdapter {

    private Context mContext;
    private List<ContactModel> listContact;
    private IOnChildItemClick iOnChildItemClick;


    public ContactAdapter(Context mContext, List<ContactModel> listContact) {
        this.mContext = mContext;
        this.listContact = listContact;
    }

    public void registerChildItemClick(IOnChildItemClick iOnChildItemClick) {
        this.iOnChildItemClick = iOnChildItemClick;
    }

    public void unRegisterChildItemClick() {
        this.iOnChildItemClick = null;
    }

    @Override
    public int getCount() {
        return listContact.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if(rowView == null) {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            rowView = inflater.inflate(R.layout.item_contact, null);
            ViewHolder holder = new ViewHolder();
            holder.tvName = (TextView) rowView.findViewById(R.id.tvName);
            holder.ivAvatar = (TextView) rowView.findViewById(R.id.ivAvatar);
            holder.btCall = (TextView) rowView.findViewById(R.id.btCall);
            holder.btEdit = (TextView) rowView.findViewById(R.id.btEdit);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.tvName.setText(listContact.get(position).getName());
        holder.tvPhone.setText(listContact.get(position).getPhone());
        holder.ivAvatar.setImageResource(listContact.get(position).getImage());

        holder.btCall.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onCall(position);
            }
        });

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnChildItemClick.onItemChildClick(position);
            }
        });

        return rowView;
    }

    private void onCall(int position) {
        ContactModel contact = listContact.get(position);
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+contact.getPhone()));
        if(ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        mContext.startActivity(intent);
    }


    static class ViewHolder{
        TextView tvName;
        TextView tvPhone;
        ImageView ivAvatar;
        Button btCall;
        Button btEdit;
    }
}
