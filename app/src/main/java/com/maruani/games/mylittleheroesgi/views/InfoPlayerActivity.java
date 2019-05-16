package com.maruani.games.mylittleheroesgi.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.maruani.games.mylittleheroesgi.R;



public class InfoPlayerActivity extends AppCompatActivity {

    private TextView pseudo;
    private TextView gender;
    private TextView birthsdate;
    private ImageView imageWeapon;
    private TextView textViewWeapon;
    private Intent intente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_of_player);
            intente = getIntent();
            pseudo = (TextView) findViewById(R.id.pseudoDetailLabel);
             birthsdate = (TextView) findViewById(R.id.birthDetail);
              imageWeapon = (ImageView) findViewById(R.id.imageWeaponDetail);
             gender = (TextView) findViewById(R.id.gender);



             textViewWeapon = (TextView) findViewById(R.id.valueWeaponDetail);
             pseudo.setText(intente.getStringExtra("pseudo"));
             gender.setText(intente.getStringExtra("gender"));
             birthsdate.setText(intente.getStringExtra("birthsdate"));
             textViewWeapon.setText(intente.getStringExtra("nameWeapon"));
             Glide.with(this).load(intente.getStringExtra("urlPicture")).into(imageWeapon);


    }
}
