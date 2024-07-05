package uic.cs478.jaykumarkakkad.myapplication.app1_landmarks;

import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class landListFragment extends ListFragment {

    //private static final String TAG = "landListFragment";

    private LandListViewModel mViewModel;

//    public static landListFragment newInstance() {
//        return new landListFragment();
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("landlistfragment","-------onCreate()");
        // to avoid new instance during config change
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i("landlistfragment","-------onCreateView()");

        //return inflater.inflate(R.layout.fragment_land_list, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedState) {
        super.onViewCreated(view, savedState);
        Log.i("landlistfragment","-------onViewCreated()");

        mViewModel = new ViewModelProvider(requireActivity()).get(LandListViewModel.class);
        // TODO: Use the ViewModel
        // Set the list adapter for the ListView
        // Discussed in more detail in the user interface classes lesson
        setListAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.fragment_land_list, MainActivity.mLandmarkArray));

        // Set the list choice mode to allow only one selection at a time
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int pos, long id) {

        // Indicates the selected item has been checked
        getListView().setItemChecked(pos, true);

//        TextView t = (TextView) v;
//        t.setTextColor(getResources().getColor(R.color.teal_700));

        // Inform the ModelView that the selection may have changed
        mViewModel.selectItem(pos);

    }

}