package com.haw.hawcontextmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn01;
    private Button btn02;
    private Button btn03;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn01 = (Button) this.findViewById(R.id.button01);
        btn02 = (Button) this.findViewById(R.id.button02);
        btn03 = (Button) this.findViewById(R.id.button03);
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button01: {
                Intent intent = new Intent(this, MenuDemo1.class);
                this.startActivity(intent);
                break;
            }
            case R.id.button02: {
                Intent intent = new Intent(this, NineOldDemo.class);
                this.startActivity(intent);
                break;
            }
            case R.id.button03: {
                Intent intent = new Intent(this, MenuDemo2.class);
                this.startActivity(intent);
                break;
            }
            default: {

            }
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
