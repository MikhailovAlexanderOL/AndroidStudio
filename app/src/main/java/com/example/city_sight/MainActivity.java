package com.example.city_sight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button nextActivity;

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextActivity = findViewById(R.id.nextActivity);
        nextActivity.setOnClickListener(this);

        final EditText editText1 = findViewById(R.id.nameInput);
        editText1.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER)){
                user.setName(editText1.getText().toString());
                return true;
            }
            return false;
        });

        final EditText editText2 = findViewById(R.id.surnameInput);
        editText2.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER)){
                user.setSurname(editText2.getText().toString());
                return true;
            }
            return false;
        });

    }

    @Override
    public void onClick(View v) {
        if (user.getName() == null){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Поле 'Имя' является обезательным!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Intent intent = new Intent(this, sightList.class);
            intent.putExtra("name", user.getName());
            intent.putExtra("surname", user.getSurname());
            startActivity(intent);
        }
    }
}