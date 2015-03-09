package com.uninorte.userpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    TextView name, age, gender;
    MainActivity Main = this;
    SharedPreferences mSharedPreferences;
    String skeyname = "prefname", skeyage = "prefage", skeygender = "prefgender";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (TextView) findViewById(R.id.name);
        age = (TextView) findViewById(R.id.age);
        gender = (TextView) findViewById(R.id.gender);
        if(this.getIntent().getExtras()!=null) {
            Bundle bundle = this.getIntent().getExtras();
            name.setText(bundle.getString("prefname"));
            age.setText(bundle.getString("prefage")+" years old");
            gender.setText(bundle.getString("prefgender"));
        }else{
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String textname = mSharedPreferences.getString(skeyname,"User name");
            String textage = mSharedPreferences.getString(skeyage,"User age");
            String textgender = mSharedPreferences.getString(skeygender,"User gender");
            name.setText(textname);
            age.setText(textage);
            gender.setText(textgender);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,SettingsActivity.class);
            finish();
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Preferences(){
        name = (TextView) findViewById(R.id.name);
        age = (TextView) findViewById(R.id.age);
        gender = (TextView) findViewById(R.id.gender);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Main.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(skeygender,gender.getText().toString());
        editor.commit();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        this.Preferences();
    }
}
