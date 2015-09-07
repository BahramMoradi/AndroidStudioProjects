package statsborgerskab.dk.dip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChooseTestActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv=null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_test);
        tv=(TextView)findViewById(R.id.vtTV);
        Button b1=(Button)findViewById(R.id.test11juni2014BT);
        Button b2 =(Button)findViewById(R.id.test2dec2014BT);
        Button b3 =(Button)findViewById(R.id.test2juni2015BT);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int option=item.getItemId();
        Intent intent=null;
        switch(option){
            case R.id.MenuStartPage:
                intent=new Intent(this,StartActvity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.MenuExercisePage:
                intent=new Intent(this,ChooseExerciseActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.MenuResultPage:
                intent=new Intent(this,ShowResultActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()) {
            case R.id.test2juni2015BT:
                intent = new Intent(this, Test.class);
                intent.putExtra(Database.TEST_KEY, "2juni2015");
                startActivity(intent);
                finish();
                break;
            case R.id.test2dec2014BT:
                intent = new Intent(this, Test.class);
                intent.putExtra(Database.TEST_KEY, "2dec2014");
                startActivity(intent);
                finish();
                break;
            case R.id.test11juni2014BT:
                intent = new Intent(this, Test.class);
                intent.putExtra(Database.TEST_KEY, "11juni2014");
                startActivity(intent);
                finish();
                break;


        }
    }
}
