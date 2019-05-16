package com.maruani.games.mylittleheroesgi.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.bumptech.glide.Glide;
import com.maruani.games.mylittleheroesgi.R;
import com.maruani.games.mylittleheroesgi.data.model.Weapon;
import com.maruani.games.mylittleheroesgi.data.service.NetworkProvider;
import java.util.List;

public class ChooseWeaponActivity extends AppCompatActivity {

  @BindView(R.id.activity_choose_weapon_rcv) RecyclerView weaponChoiceRcv;
  private WeaponAdapter adapter;
  private TextView Text;
  private ImageView imageView;
  private Intent intente;
  private String currentUrlPicture;
  private String currentText;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    intente = getIntent();
    setContentView(R.layout.activity_choose_weapon);
    ButterKnife.bind(this);
    Text = findViewById(R.id.textViewWeapon);
    imageView = findViewById(R.id.imageViewWeapon);
    initRecyclerView();
    loadData();
    if (intente == null){
      Log.i("DEBUG","Intent est null donc ça plante");
    }
    Log.i("pseudo Weapon Activity",intente.getStringExtra("pseudo"));
    Log.i("gender weapon Activity",intente.getStringExtra("gender"));
    Log.i("birthdate weapon",intente.getStringExtra("birthsdate"));
  }

  private void initRecyclerView() {
    weaponChoiceRcv.setLayoutManager(new LinearLayoutManager(this));
    adapter = new WeaponAdapter();
    weaponChoiceRcv.setAdapter(adapter);

    adapter.setItemClickListener(weapon -> {
      Toast.makeText(this, weapon.getName(), Toast.LENGTH_SHORT).show();
      Log.i("Test du nom",weapon.getName());
      Text.setText(weapon.getName());
      Text.setVisibility(View.GONE);
      imageView.setVisibility(View.VISIBLE);
      Glide.with(this).load(weapon.getPictureUrl()).into(imageView);
      this.currentUrlPicture = weapon.getPictureUrl();
      this.currentText = weapon.getName();
    });
  }
  private void loadData() {
    NetworkProvider.getInstance().getWeapons(new NetworkProvider.Listener<List<Weapon>>() {
      @Override public void onSuccess(List<Weapon> data) {
        adapter.setWeaponList(data);
      }

      @Override public void onError(Throwable t) {

      }
    });
  }


  @OnClick(R.id.buttonNext) void toTheDetailViewButton(){
    Intent detailWeapon = new Intent(this, InfoPlayerActivity.class);
    detailWeapon.putExtra("pseudo",intente.getStringExtra("pseudo").toString());
    detailWeapon.putExtra("gender",intente.getStringExtra("gender").toString());
    detailWeapon.putExtra("birthsdate",intente.getStringExtra("birthsdate").toString());
    detailWeapon.putExtra("urlPicture",currentUrlPicture.toString());
    detailWeapon.putExtra("nameWeapon",this.currentText.toString());
    this.startActivity(detailWeapon);
  }
}
