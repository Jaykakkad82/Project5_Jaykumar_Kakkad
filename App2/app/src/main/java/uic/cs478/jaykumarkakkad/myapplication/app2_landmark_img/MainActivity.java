package uic.cs478.jaykumarkakkad.myapplication.app2_landmark_img;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout aContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aContainer = findViewById(R.id.lin_layout);

        // Array of drawable resource IDs for the images
        int[] imageIds = {
                R.drawable.buck_fountain,
                R.drawable.field_musem,
                R.drawable.mill_park,
                R.drawable.navy_pier,
                R.drawable.union_station,
                R.drawable.wr_building
        };

        // Get device screen width
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;

        // Add images to the linear layout
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageIds[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE); // Scale the image to fit the screen
            imageView.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setPadding(5,5,10,5);
            aContainer.addView(imageView);
        }


    }
}