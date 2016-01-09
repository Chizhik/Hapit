package kr.ac.kaist.sprite.hapit;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.widget.Toast;

/**
 * Created by pika on 2015-12-08.
 */
public class TabListener<T extends Fragment> implements ActionBar.TabListener {

    private final Activity mActivity;
    private final String mTag;
    private final Class<T> mClass;
    private Fragment mFragment;

    public TabListener(Activity activity, String tag, Class<T> clz) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;

        mFragment = mActivity.getFragmentManager().findFragmentByTag(mTag);

        if (mFragment!=null && !mFragment.isDetached()) {
            FragmentTransaction fragmentTransaction = mActivity
                    .getFragmentManager().beginTransaction();
            fragmentTransaction.detach(mFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (mFragment == null) {
            mFragment = Fragment.instantiate(mActivity, mClass.getName(), null);
            ft.add(android.R.id.content, mFragment, mTag);
        } else {
            ft.attach(mFragment);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (mFragment != null) {
            ft.detach(mFragment);
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        Toast.makeText(mActivity, "onTabReselected", Toast.LENGTH_SHORT).show();
    }
}
