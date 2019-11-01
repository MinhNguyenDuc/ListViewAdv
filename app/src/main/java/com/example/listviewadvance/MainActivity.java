package com.example.listviewadvance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IOnChildItemClick{

    private List<ContactModel> listContact = new ArrayList<>();
    private ListView lvContact ;
    private ContactAdapter mAdapter;
    private ImageView ivUser;
    private TextView tvName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();


        mAdapter =new ContactAdapter(this, listContact);
        mAdapter.registerChildItemClick(this);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactModel model = listContact.get(i);
                Toast.makeText(MainActivity.this, model.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        lvContact = (ListView)findViewById(R.id.lvContact);
        ivUser = (ImageView) findViewById(R.id.ivUser);
        tvName = (TextView)findViewById((R.id.tvName));
    }

    private void initData() {
        listContact.add((new ContactModel("Minh", "123456789", R.drawable.ic_launcher_foreground)));
        listContact.add((new ContactModel("Minh", "123456789", R.drawable.ic_launcher_foreground)));
        listContact.add((new ContactModel("Minh", "123456789", R.drawable.ic_launcher_foreground)));
        listContact.add((new ContactModel("Minh", "123456789", R.drawable.ic_launcher_foreground)));
        listContact.add((new ContactModel("Minh", "123456789", R.drawable.ic_launcher_foreground)));
        listContact.add((new ContactModel("Minh", "123456789", R.drawable.ic_launcher_foreground)));
        listContact.add((new ContactModel("Minh", "123456789", R.drawable.ic_launcher_foreground)));
    }

    @Override
    public void onItemChildClick(int position) {
        ContactModel contactModel = listContact.get(position);
        ivUser.setImageResource(contactModel.getImage());
        tvName.setText(contactModel.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.unRegisterChildItemClick();
    }
}
