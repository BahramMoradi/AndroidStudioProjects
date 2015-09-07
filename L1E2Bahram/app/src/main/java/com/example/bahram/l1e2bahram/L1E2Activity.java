package com.example.bahram.l1e2bahram;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;


public class L1E2Activity extends Activity implements OnClickListener{
    Button bt=null;
    LinearLayout lo=null;
    int counter=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.layout);
        lo=new LinearLayout(this);
        lo.setOrientation(LinearLayout.VERTICAL);
        LayoutParams linearlayoutParam = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);


        bt=new Button(this);
        bt.setText("Push Me");
        bt.setHeight(100);
        bt.setWidth(100);
        bt.setOnClickListener(this);
        lo.addView(bt);

        this.setContentView(lo, linearlayoutParam);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.l1_e2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        System.out.println("You have pushed me");
        counter++;
        if(counter==1){
            bt.setText("You have pused me "+counter+ " time" );}
        else{
            bt.setText("You have pused me "+counter+ " times" );
        }

    }
}
