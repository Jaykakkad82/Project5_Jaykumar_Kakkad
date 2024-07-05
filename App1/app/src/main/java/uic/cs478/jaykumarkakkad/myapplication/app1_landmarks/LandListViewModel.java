package uic.cs478.jaykumarkakkad.myapplication.app1_landmarks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LandListViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private final MutableLiveData<Integer> selectedItem = new MutableLiveData<Integer>();
    public void selectItem(Integer item) {
        selectedItem.setValue(item);
    }
    public LiveData<Integer> getSelectedItem() {
        return selectedItem;
    }
    // .getsetlectedItem() can be used to implement .observe()
}