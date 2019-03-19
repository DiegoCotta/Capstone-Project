package com.example.beerlovers.view.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.beerlovers.R;
import com.example.beerlovers.databinding.ActivityBeerBinding;
import com.example.beerlovers.model.Beer;
import com.example.beerlovers.model.Breweries;
import com.example.beerlovers.utils.CircleTransform;
import com.squareup.picasso.Picasso;

public class BeerActivity extends AppCompatActivity {

    public static final String BEER_KEY = "beer-key";
    private Beer mBeer;
    private ActivityBeerBinding mBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_beer);

        setSupportActionBar(mBinding.toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (getIntent() != null && getIntent().hasExtra(BEER_KEY))
            mBeer = getIntent().getParcelableExtra(BEER_KEY);

        Picasso.get().load(R.mipmap.ic_launcher).fit().into(mBinding.photo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBeer.getLabels() != null)
            if (mBeer.getLabels().getLarge() != null) {
                Picasso.get().load(mBeer.getLabels().getLarge()).into(mBinding.photo);
            } else if (mBeer.getLabels().getMedium() != null) {
                Picasso.get().load(mBeer.getLabels().getMedium()).fit().into(mBinding.photo);
            }
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(mBeer.getNameDisplay());

        if (mBeer.getIbu() != null)
            mBinding.content.tvIbu.setText(mBeer.getIbu());
        else if (mBeer.getStyle() != null) {
            mBinding.content.tvIbu.setText(mBeer.getStyle().getIbuMin());
        } else {
            mBinding.content.tvIbu.setVisibility(View.GONE);
            mBinding.content.tvLabelIbu.setVisibility(View.GONE);
        }
        if (mBeer.getAbv() != null)
            mBinding.content.tvAbv.setText(String.format("%s%%", mBeer.getAbv()));
        else if (mBeer.getStyle().getAbvMin() != null) {
            mBinding.content.tvAbv.setText(String.format("%s%%", mBeer.getStyle().getAbvMin()));
        } else {
            mBinding.content.tvAbv.setVisibility(View.GONE);
            mBinding.content.tvAbv.setVisibility(View.GONE);
        }
        if (mBeer.getIsRetired().equalsIgnoreCase("N"))
            mBinding.content.tvRetired.setVisibility(View.GONE);
        if (mBeer.getIsOrganic().equalsIgnoreCase("N"))
            mBinding.content.tvOrganic.setVisibility(View.GONE);

        if (mBeer.getStyle() != null) {
            if (mBeer.getStyle().getShortName() != null)
                mBinding.content.tvStyle.setText(mBeer.getStyle().getShortName());
            else if (mBeer.getStyle().getName() != null)
                mBinding.content.tvStyle.setText(mBeer.getStyle().getName());
            else {
                mBinding.content.tvStyle.setVisibility(View.GONE);
                mBinding.content.tvLabelStyle.setVisibility(View.GONE);
            }
        } else {
            mBinding.content.tvStyle.setVisibility(View.GONE);
            mBinding.content.tvLabelStyle.setVisibility(View.GONE);
        }
        if (mBeer.getGlass() != null) {
            mBinding.content.tvGlass.setText(mBeer.getGlass().getName());
        } else {
            mBinding.content.tvGlass.setVisibility(View.GONE);
            mBinding.content.tvLabelGlass.setVisibility(View.GONE);
        }
        if (mBeer.getFoodPairings() != null) {
            mBinding.content.tvFoodPairings.setText(mBeer.getFoodPairings());
        } else {
            mBinding.content.tvFoodPairings.setVisibility(View.GONE);
            mBinding.content.tvLabelFoodPairings.setVisibility(View.GONE);
        }
        if (mBeer.getBreweries() != null) {
            StringBuilder breweries = new StringBuilder();
            for (Breweries brs : mBeer.getBreweries()) {
                breweries.append(brs.getNameShortDisplay()).append(",");
            }
            breweries = new StringBuilder(breweries.toString().replaceFirst(".$", ""));
            mBinding.content.tvBreweries.setText(breweries.toString());
        } else {
            mBinding.content.tvBreweries.setVisibility(View.GONE);
            mBinding.content.tvLabelBreweries.setVisibility(View.GONE);
        }
        if (mBeer.getDescription() != null) {
            mBinding.content.tvDescription.setText(mBeer.getDescription());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.favorite:
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        return false;
                    }
                });

                break;
            default:
                break;
        }

        return true;
    }

}
