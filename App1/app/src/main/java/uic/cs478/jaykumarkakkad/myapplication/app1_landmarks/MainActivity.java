package uic.cs478.jaykumarkakkad.myapplication.app1_landmarks;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar; // this is need to set toolbar as action bar
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

// Step by step comments

public class MainActivity extends AppCompatActivity {

    // define two arrays, fragment Manager,
    // frament variables, layout variables, viewModel variable
    public static String[] mLandmarkArray;
    public static String[] mWebsiteArray;

    FragmentManager mFragmentManager;

    private webViewFragment mwebViewfragment = null;
    private landListFragment mlandlistfragment = null;

    private FrameLayout mLandListFrameLayout, mWebViewFrameLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "MainActivity";

    private LandListViewModel mModel ;

    // ==================== All initialization in Oncreate method ===============
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "Executing MainActivity onCreate " );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ===========  1. set toolbar as actionbar
        Toolbar myToolBar = (Toolbar)  findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);

        //==============  2. Get String arrays:
        mLandmarkArray = getResources().getStringArray(R.array.land_names);
        mWebsiteArray = getResources().getStringArray(R.array.websites);


        //============= 3. Get references to the two frame layouts
        mLandListFrameLayout = findViewById(R.id.land_fragment_container);
        mWebViewFrameLayout = findViewById(R.id.webview_fragment_container);


        //4. Initialize Fragment manager
        mFragmentManager = getSupportFragmentManager();


        //5. Fragment Transaction : Add LandList Fragment
        // Check if instance already exists , retrieve using TAG
        mlandlistfragment = (landListFragment) mFragmentManager.findFragmentByTag("listfragment");

        // if instances does not exist, create and add using transactions
        if (mlandlistfragment == null) {
            mlandlistfragment = new landListFragment();

            final FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.land_fragment_container, mlandlistfragment, "listfragment");
            fragmentTransaction.commit();
        }


        // ========== 6. Set up OnBackstackChange Listener
        // Has to set the layout correctly when backstack changes
        mFragmentManager.addOnBackStackChangedListener(

                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });


        // ====== 7. Set up observer

        // get viewmodel
        mModel = new ViewModelProvider(this).get(LandListViewModel.class) ;

        // implement .observe() change
        mModel.getSelectedItem().observe(this, item -> {
            Log.i(TAG, "Executing observed, pos:  " + item);

            // Try to get instance using tag
            if (mwebViewfragment == null) {
                Log.i("webViewFragment","WEB FRAGMENT EXISTS");
                mwebViewfragment = (webViewFragment) mFragmentManager.findFragmentByTag("webfragment");
            }

            // If instance does not exist, create a new one
            if (mwebViewfragment==null) {
                Log.i("webViewFragment","NEW INSTANCE CREATED");
                mwebViewfragment = new webViewFragment(); }

          // If fragment is not added, add it using transactions
            if (!mwebViewfragment.isAdded())  {
              FragmentTransaction fragmentTransaction2 = mFragmentManager.beginTransaction();
              // add quote fragment to display with a tag
            fragmentTransaction2.add(R.id.webview_fragment_container, mwebViewfragment, "webfragment");

            // Add this FragmentTransaction to the backstack (so that back button works)
            fragmentTransaction2.addToBackStack(null);
            // commit and execute pending
            fragmentTransaction2.commit();
            mFragmentManager.executePendingTransactions();
            }

            Log.i(TAG, "Executing Transactions:  " + item);

            // executes everytime change is observed by viewmodel
            setLayout() ;
        });



        //8. set layout (executes the first time activity created
        setLayout() ;

    }

    //9. Implement a SetLayout method:
    private void setLayout() {

        Log.i(TAG, "Executing SETLAYOUT " );
        // Default setting when no webView fragment exists
        if (mwebViewfragment == null){
            // Make the TitleFragment occupy the entire layout
            mLandListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            mWebViewFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));

            return;

        }


        //Determine whether the webView has been added
        if (!mwebViewfragment.isAdded()) {

            // Make the TitleFragment occupy the entire layout
            mLandListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            mWebViewFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
        } else {

            // Find orientation and make changes based on orientation
            int orientation = getResources().getConfiguration().orientation;

            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                // Make the WebView layout full screen
                mLandListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        0, MATCH_PARENT));
                mWebViewFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                        MATCH_PARENT));
            }
            else {

                // Make the LandList Layout take 1/3 of the layout's width
                mLandListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));

                // Make the WEbView layout take 2/3's of the layout's width
                mWebViewFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
                }
        }
    }



    // ==================Implementing Options Menu=====================
    // Step 1: create options menu ,
    // Step2: Implement method for item selected
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_exit) {
            // Handle exit action
            finish(); // Finish the activity
            return true;
        } else if (id == R.id.action_launch_A2) {
            // Handle launching A2
            String packageName = "uic.cs478.jaykumarkakkad.myapplication.app2_landmark_img";
            String className = "uic.cs478.jaykumarkakkad.myapplication.app2_landmark_img.MainActivity";

            Intent intent = new Intent();
            intent.setComponent(new ComponentName(packageName, className));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}