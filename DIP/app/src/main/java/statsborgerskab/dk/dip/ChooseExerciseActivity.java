package statsborgerskab.dk.dip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ChooseExerciseActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exercise);
        Button c1=(Button)findViewById(R.id.cat1BT);
        Button c2=(Button)findViewById(R.id.cat2BT);
        Button c3=(Button)findViewById(R.id.cat3BT);
        Button c4=(Button)findViewById(R.id.cat4BT);
        Button c5=(Button)findViewById(R.id.cat5BT);
        Button c6=(Button)findViewById(R.id.cat6BT);
        Button c7=(Button)findViewById(R.id.cat7BT);
        Button c8=(Button)findViewById(R.id.cat8BT);
        Button c9=(Button)findViewById(R.id.cat9BT);
        Button c10=(Button)findViewById(R.id.cat10BT);
        Button c11=(Button)findViewById(R.id.cat11BT);
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);
        c7.setOnClickListener(this);
        c8.setOnClickListener(this);
        c9.setOnClickListener(this);
        c10.setOnClickListener(this);
        c11.setOnClickListener(this);




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
            case R.id.MenuTestPage:
                intent=new Intent(this,ChooseTestActivity.class);
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


    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cat1BT:
                startTest(Database.CAT_1_FILE);
                break;
            case R.id.cat2BT:
                startTest(Database.CAT_2_FILE);
                break;
            case R.id.cat3BT:
                startTest(Database.CAT_3_FILE);
                break;
            case R.id.cat4BT:
                startTest(Database.CAT_4_FILE);
                break;
            case R.id.cat5BT:
                startTest(Database.CAT_5_FILE);
                break;
            case R.id.cat6BT:
                startTest(Database.CAT_6_FILE);
                break;
            case R.id.cat7BT:
                startTest(Database.CAT_7_FILE);
                break;
            case R.id.cat8BT:
                startTest(Database.CAT_8_FILE);
                break;
            case R.id.cat9BT:
                startTest(Database.CAT_9_FILE);
                break;
            case R.id.cat10BT:
                startTest(Database.CAT_10_FILE);
                break;
            case R.id.cat11BT:
                startTest(Database.CAT_11_FILE);
                break;
        }

    }
    public void startTest(String testFile){
        Intent intent=new Intent(this,Test.class);
        intent.putExtra(Database.TEST_KEY,testFile);
        startActivity(intent);
        finish();
    }
}
