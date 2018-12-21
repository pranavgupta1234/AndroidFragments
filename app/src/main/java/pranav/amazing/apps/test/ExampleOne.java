package pranav.amazing.apps.test;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import pranav.amazing.apps.test.Fragment.FragmentDetail;
import pranav.amazing.apps.test.Fragment.FragmentMenu;
import pranav.amazing.apps.test.R;

public class ExampleOne extends AppCompatActivity implements FragmentMenu.onItemSelectedListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example1);

        if(savedInstanceState == null){
            FragmentMenu fragMenu = new FragmentMenu();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.frame1, fragMenu);
            ft.commit();
        }

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

            FragmentDetail fragDetail = new FragmentDetail();

            Bundle bdl = new Bundle();
            bdl.putInt("position", 0);
            fragDetail.setArguments(bdl);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.frame2, fragDetail);
            ft.commit();
        }
    }

    @Override
    public void onItemSelected(int pos) {
        Toast.makeText(ExampleOne.this, "called by fragment from pos " + String.valueOf(pos), Toast.LENGTH_SHORT).show();

        FragmentDetail fragDetail = new FragmentDetail();

        Bundle bdl = new Bundle();
        bdl.putInt("position", pos);
        fragDetail.setArguments(bdl);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame2, fragDetail).commit();
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.frame1, fragDetail).addToBackStack(null).commit();
        }

    }
}
