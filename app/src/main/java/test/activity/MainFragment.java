package test.activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fragment.BaseFragment;

/**
 * Created by amit on 14/2/18.
 */

public class MainFragment extends BaseFragment {
    View view;

    @Override
    protected View initUI(LayoutInflater inflater, ViewGroup container) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main, null);
            view.findViewById(R.id.button).setOnClickListener(this);
        }
        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button) {
            //setExitTransition(new Fade());
        } else {
            Log.i(getTag(), "No clickHandled");
        }
    }

    @Override
    public void onConnectivityChange(boolean isConnectivity) {
        super.onConnectivityChange(isConnectivity);
        Log.i(getTag(), "isConnectivity = " + isConnectivity);
    }
}
