package com.example.dialoger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.dialoger.MinDialog;

public class MainActivity extends AppCompatActivity implements MinDialog.MittInterface {
    @Override
    public void onYesClick() {
        finish(); // Avslutter aktiviteten når "Ja"-knappen i dialogen klikkes.
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Lagrer tilstand i SharedPreferences når aktiviteten går i bakgrunnen.
        SharedPreferences sharedPreferences =
                getSharedPreferences("MinePreferanser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Henter teksten fra EditText og en global verdi, og lagrer dem i SharedPreferences.
        EditText et = findViewById(R.id.tekst);
        final Global global = (Global) getApplicationContext();
        final String globaltekst = global.getminvar();
        et.setText(globaltekst);
        editor.putString("brukernavn", "JohnDoe");
        editor.putInt("alder", 30);
        editor.putBoolean("innlogget", true);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Henter data fra SharedPreferences når aktiviteten kommer tilbake i fokus.
        SharedPreferences sharedPreferences = getSharedPreferences("MinePreferanser", Context.MODE_PRIVATE);

        // Henter verdier fra SharedPreferences.
        String username = sharedPreferences.getString("brukernavn", "");
        int age = sharedPreferences.getInt("alder", 0);
        boolean isLoggedIn = sharedPreferences.getBoolean("innlogget", false);

        // Gjør noe med de hentede verdiene
        if (!username.isEmpty()) {
            // Utfør handling basert på brukernavnet
        }

        // ... Andre handlinger basert på de hentede verdiene
    }

    @Override
    public void onNoClick() {
        return; // Utfører ingen handling når "Nei"-knappen i dialogen klikkes.
    }

    public void visDialog(View v) {
        // Viser en egendefinert dialog når denne metoden kalles.
        MinDialog dialog = new MinDialog();
        dialog.show(getSupportFragmentManager(), "Tittel");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Setter innholdet til aktiviteten fra layouten "activity_main.xml".

        // Henter en referanse til en EditText og legger til en klikklytter for en knapp.
        EditText et = findViewById(R.id.tekst);
        Button dialogknapp = findViewById(R.id.dialog);
        dialogknapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visDialog(view);
            }
        });

        // Henter en referanse til en annen knapp og legger til en klikklytter for å starte en annen aktivitet.
        Button preferanseknapp = findViewById(R.id.preferanser);
        Intent intent = new Intent(this, SettingsActivity.class);
        preferanseknapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);

        // Lagrer innholdet i EditText i "outstate" for senere gjenoppretting.
        EditText textView = findViewById(R.id.tekst);
        outstate.putString("antall", textView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Gjenoppretter innholdet i EditText fra "savedInstanceState" for å gjenopprette tidligere data.
        EditText tw = findViewById(R.id.tekst);
        tw.setText(savedInstanceState.getString("antall"));
    }
}
