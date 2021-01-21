package net.isspgj.knowledge.share;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import net.isspgj.knowledge.R;
import net.isspgj.util.PShareUtil;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PShareUtil().shareText(ShareActivity.this,"https://www.fly2cn.net");
            }
        });

    }

}