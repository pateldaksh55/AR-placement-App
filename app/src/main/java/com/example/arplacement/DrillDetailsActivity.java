package com.example.arplacement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrillDetailsActivity extends AppCompatActivity {
    TextView tvTitle, tvDesc, tvTips;
    ImageView imgDrill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drill_details);

        tvTitle = findViewById(R.id.tvTitle);
        tvDesc = findViewById(R.id.tvDesc);
        tvTips = findViewById(R.id.tvTips);
        imgDrill = findViewById(R.id.imgDrill);

        String drillName = getIntent().getStringExtra("Drill_Name");

        tvTitle.setText(drillName);

        switch (drillName) {
            case "Drill 1":
                imgDrill.setImageResource(R.drawable.img);
                tvDesc.setText("Drill 1 focuses on spatial awareness. The user will learn how to position themselves accurately in a designated AR area.");
                tvTips.setText("Tips:\n- Use a well-lit area\n- Keep your phone steady\n- Tap gently on the detected surface");
                break;

            case "Drill 2":
                imgDrill.setImageResource(R.drawable.img_1);
                tvDesc.setText("Drill 2 improves object placement skills. The goal is to place virtual objects precisely on a flat surface.");
                tvTips.setText("Tips:\n- Ensure surface is flat\n- Avoid reflective floors\n- Try different angles for detection");
                break;

            case "Drill 3":
                imgDrill.setImageResource(R.drawable.img_2);
                tvDesc.setText("Drill 3 is designed for orientation practice. This drill teaches users how to rotate and view AR elements from different perspectives.");
                tvTips.setText("Tips:\n- Walk around the object\n- Observe shadows\n- Use zoom and rotate features if enabled");
                break;

            default:
                imgDrill.setImageResource(R.drawable.img);
                tvDesc.setText("Default drill description. Please select a valid drill.");
                tvTips.setText("Tips: Make sure you selected a valid drill.");
        }
    }
}
