package india.abhijeet.k.farmafriend;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class AdminDash extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash);


       firebaseAuth= FirebaseAuth.getInstance();

    }

    public void goUserlist(View view)
    {

        Intent intent =new Intent(this,Admin_UserList_FormsReq.class);

        startActivity(intent);


    }



    public void logOutAdmin(View view)
    {


        firebaseAuth.signOut();

        startActivity(new Intent(this,LoginActivity.class));

    }




}
