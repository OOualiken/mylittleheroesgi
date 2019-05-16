package com.maruani.games.mylittleheroesgi.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.maruani.games.mylittleheroesgi.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreatePlayerActivity extends AppCompatActivity {

  EditText pseudoEdt;
  @BindView(R.id.activity_create_player_birthdate_edt) EditText birthdateEdt;
  @BindView(R.id.activity_create_player_gender_gr) RadioGroup genderGr;

  DatePickerDialog datePickerDialog;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_player);
    ButterKnife.bind(this);

    pseudoEdt = findViewById(R.id.activity_create_player_pseudo_edt);
    initDatePicker();

    genderGr.setOnCheckedChangeListener((group, checkedId) -> {
      if (checkedId == R.id.activity_create_player_radio_button_male) {

      } else {

      }
    });
  }

  private void initDatePicker() {
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
      String format = "dd/MM/yy";
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.FRANCE);

      calendar.set(Calendar.YEAR, year1);
      calendar.set(Calendar.MONTH, month1);
      calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

      birthdateEdt.setText(simpleDateFormat.format(calendar.getTime()));
    }, year, month, day);
  }

  @OnClick(R.id.activity_create_player_birthdate_edt) void onBirthdateClick() {
    datePickerDialog.show();
  }

  @OnClick(R.id.activity_create_player_next_btn) void onNextButtonClick() {
    //validate data
    //if OK navigate
    //if KO show error
    Intent intent = new Intent(this, ChooseWeaponActivity.class);
    intent.putExtra("pseudo",pseudoEdt.getText().toString());
    //On veut get le bouton sélectionné et on veut récupérer son texte pour le mettre dans l'extra.
    RadioButton radioButton = findViewById(genderGr.getCheckedRadioButtonId());
    Log.i("radioButton", (String) radioButton.getText());
    intent.putExtra("gender",radioButton.getText().toString());
    // On veut récupérer la date sous forme de String et la mettre dans l'extra pour pouvoir être récupérer ensuite.
    Log.i("gender", (String) radioButton.getText());
    intent.putExtra("birthsdate",birthdateEdt.getText().toString());

    startActivity(intent);
  }
}
//On check si toutes les données ont été entrés. Sinon, on affiche une pop Up d'erreur
//Dans un premier temps, fait le sans la gestion d'erreur.