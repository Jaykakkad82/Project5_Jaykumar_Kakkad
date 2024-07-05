package uic.cs478.jaykumarkakkad.myapplication.app1_landmarks;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

public class webViewFragment extends Fragment {

    private static final String TAG = "webViewFragment";

    private WebView mWebView = null;
    private int mCurrIdx = -1;
    private int mWebsitesArrayLen;
    private LandListViewModel mModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        setRetainInstance(true);

        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_view, container, false);
    }


    @Override
    public void onViewCreated (View view, Bundle savedInstanceState){
        Log.i(TAG, getClass().getSimpleName() + ":entered onViewCreated()");
        super.onViewCreated(view,savedInstanceState);

        mModel = new ViewModelProvider(requireActivity()).get(LandListViewModel.class);
        mWebView = (WebView) view.findViewById(R.id.webViews_frag);
        mWebsitesArrayLen = MainActivity.mWebsiteArray.length;

        // retains last quote shown on config change
        mModel.getSelectedItem().observe(getViewLifecycleOwner(), item -> {
            // UB: 2/19/2022 -- No need to update UI if same item reselected in TitlesFragment

//            Log.i("Ugo says", "Entered QuoteFragment observe()") ;
            if (item < 0 || item >= mWebsitesArrayLen)
                return;

            // Otherwise, make sure quotes fragment is visible

            // Update UI
            mCurrIdx = item;
            mWebView.loadUrl(MainActivity.mWebsiteArray[mCurrIdx]);
            //mQuoteView.setText(QuoteViewerActivity.mQuoteArray[mCurrIdx]);
        });


    }


}

