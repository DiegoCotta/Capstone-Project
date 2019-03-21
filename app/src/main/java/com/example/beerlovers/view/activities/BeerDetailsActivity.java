package com.example.beerlovers.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.beerlovers.R;
import com.example.beerlovers.databinding.ActivityBeerBinding;
import com.example.beerlovers.model.Beer;
import com.example.beerlovers.model.DBBeer;
import com.example.beerlovers.viewmodel.BeerDetailsViewModel;
import com.squareup.picasso.Picasso;

public class BeerDetailsActivity extends AppCompatActivity {

    public static final String BEER_KEY = "beer-key";
    public static final String BEER_FROM_DB_KEY = "beer-from-db-key";
    private Beer mBeer;
    private DBBeer mDBBeer;
    private ActivityBeerBinding mBinding;
    private BeerDetailsViewModel mViewModel;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_beer);
        if (getIntent() != null && getIntent().hasExtra(BEER_KEY))
            mBeer = getIntent().getParcelableExtra(BEER_KEY);
        if (getIntent() != null && getIntent().hasExtra(BEER_FROM_DB_KEY))
            mDBBeer = getIntent().getParcelableExtra(BEER_FROM_DB_KEY);
        setSupportActionBar(mBinding.toolbar);
        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.changeTasted();
            }
        });


        Picasso.get().load(R.mipmap.ic_launcher).fit().into(mBinding.photo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBeer != null) {
            if (mBeer.getLabels() != null)
                if (mBeer.getLabels().getIcon() != null) {
                    Picasso.get().load(mBeer.getLabels().getLarge()).into(mBinding.photo);
                } else if (mBeer.getLabels().getMedium() != null) {
                    Picasso.get().load(mBeer.getLabels().getMedium()).fit().into(mBinding.photo);
                }
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(mBeer.getNameDisplay());

            if (mBeer.getIbu() != null)
                mBinding.content.tvIbu.setText(mBeer.getIbu());
            else if (mBeer.getStyle() != null && mBeer.getStyle().getIbuMin() != null) {
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
            if (mBeer.getIngredients() != null) {
                mBinding.content.tvIngredients.setText(mBeer.getIngredients().toString());
            } else {
                mBinding.content.tvLabelIngredients.setVisibility(View.GONE);
                mBinding.content.tvIngredients.setVisibility(View.GONE);
            }
            if (mBeer.getBreweries() != null) {
                mBinding.content.tvBreweries.setText(mBeer.getBreweriesString());
            } else {
                mBinding.content.tvBreweries.setVisibility(View.GONE);
                mBinding.content.tvLabelBreweries.setVisibility(View.GONE);
            }

            if (mBeer.getDescription() != null) {
                mBinding.content.tvDescription.setText(mBeer.getDescription());
            }

        } else if (mDBBeer != null) {

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        this.menu = menu;
        inflater.inflate(R.menu.details_menu, menu);
        setupViewModel();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.favorite:
                mViewModel.changeFavorite();
                break;
            default:
                break;
        }

        return true;
    }

    private void setupViewModel() {
        mViewModel = ViewModelProviders.of(this).get(BeerDetailsViewModel.class);
        mViewModel.getBeer(mBeer.getId());
        mViewModel.setBeer(mBeer);
        mViewModel.getDbBeer().observe(this, new Observer<DBBeer>() {
            @Override
            public void onChanged(@Nullable DBBeer beer) {
                if (beer != null) {
                    if (beer.isTasted()) {
                        mBinding.fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(BeerDetailsActivity.this, R.color.colorTastedBackground)));
                        mBinding.fab.setImageResource(R.drawable.ic_checked);
                    } else {
                        mBinding.fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(BeerDetailsActivity.this, R.color.colorAccent)));
                        mBinding.fab.setImageResource(R.drawable.ic_add);
                    }
                    if (beer.isFavorite()) {
                        menu.getItem(0).setIcon(ContextCompat.getDrawable(BeerDetailsActivity.this, R.drawable.ic_favorite_full));
                    } else {
                        menu.getItem(0).setIcon(ContextCompat.getDrawable(BeerDetailsActivity.this, R.drawable.ic_favorite_empty));
                    }
                }

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewModel.checkBeer();
    }
}
