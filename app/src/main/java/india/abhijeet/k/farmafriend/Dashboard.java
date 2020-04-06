package india.abhijeet.k.farmafriend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);



        firebaseAuth= FirebaseAuth.getInstance();

    }


    public void goUser(View v)
    {


        startActivity(new Intent(this,FarmarDash.class));


    }


    public void goAdmin(View v)
    {


        startActivity(new Intent(this,AdminDash.class));


    }




    public void logout(View v){

        firebaseAuth.signOut();

        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

}
