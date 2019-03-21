package com.example.beerlovers.view.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.beerlovers.R;
import com.example.beerlovers.databinding.ActivityMainBinding;
import com.example.beerlovers.model.ListType;
import com.example.beerlovers.view.fragments.ListBeerFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_favorite:
                    fragment = ListBeerFragment.newInstance(ListType.FAVORITE);
                    break;
                case R.id.navigation_tasted:
                    fragment = ListBeerFragment.newInstance(ListType.TASTED);
                    break;
                default:
                    fragment = ListBeerFragment.newInstance(ListType.NETWORK);
                    break;
            }
            return loadFragment(fragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(ListBeerFragment.newInstance(ListType.NETWORK));

    }


    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}


