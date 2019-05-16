package com.maruani.games.mylittleheroesgi.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.maruani.games.mylittleheroesgi.R;
import com.maruani.games.mylittleheroesgi.data.model.Weapon;
import com.maruani.games.mylittleheroesgi.data.service.NetworkProvider;
import java.util.List;

public class ChooseWeaponActivity extends AppCompatActivity {

  @BindView(R.id.activity_choose_weapon_rcv) RecyclerView weaponChoiceRcv;
  private WeaponAdapter weaponAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choose_weapon);
    ButterKnife.bind(this);

    initRecyclerView();
    loadData();
  }

  private void initRecyclerView() {
    weaponChoiceRcv.setLayoutManager(new LinearLayoutManager(this));
    weaponAdapter = new WeaponAdapter();
    weaponChoiceRcv.setAdapter(weaponAdapter);

    weaponAdapter.setItemClickListener(weapon -> {
      Toast.makeText(this, weapon.getName(), Toast.LENGTH_SHORT).show();
    });
  }

  private void loadData() {
    NetworkProvider.getInstance().getWeapons(new NetworkProvider.Listener<List<Weapon>>() {
      @Override public void onSuccess(List<Weapon> data) {
        weaponAdapter.setWeaponList(data);
      }

      @Override public void onError(Throwable t) {

      }
    });
  }
}
