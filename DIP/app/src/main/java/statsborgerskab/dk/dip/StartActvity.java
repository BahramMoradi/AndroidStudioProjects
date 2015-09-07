package statsborgerskab.dk.dip;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class StartActvity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button exerciseBT=(Button)findViewById(R.id.exerciseBT);
        Button testBT=(Button)findViewById(R.id.testBT);
        exerciseBT.setOnClickListener(this);
        testBT.setOnClickListener(this);
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
            case R.id.MenuTestPage:
                intent=new Intent(this,ChooseTestActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.exerciseBT:
                Intent exc=new Intent(this,ChooseExerciseActivity.class);
                startActivity(exc);
                finish();
                break;
            case R.id.testBT:
                Intent test=new Intent(this,ChooseTestActivity.class);
                startActivity(test);
                finish();
                break;
        }
    }
}
