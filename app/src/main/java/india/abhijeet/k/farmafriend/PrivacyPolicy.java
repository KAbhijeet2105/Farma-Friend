package india.abhijeet.k.farmafriend;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PrivacyPolicy extends AppCompatActivity {

    TextView pripolicy;
    TextView terms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);




        pripolicy=findViewById(R.id.txt_pripolicy_go_online);
        terms=findViewById(R.id.txt_termsCondi_go_online);


        pripolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://farmafriendprivacypolicy2019.blogspot.com/2019/03/privacy-policy-farma-friend.html"));
                    startActivity(intent);



                }catch (Exception eiss)
                {



                }
            }
        });


        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://farmafriendprivacypolicy2019.blogspot.com/2019/03/farma-friend-terms-conditions.html"));
                    startActivity(intent);



                }catch (Exception eiss)
                {



                }



            }
        });







    }
}
