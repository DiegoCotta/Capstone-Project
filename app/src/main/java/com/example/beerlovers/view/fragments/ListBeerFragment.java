package com.example.beerlovers.view.fragments;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beerlovers.R;
import com.example.beerlovers.databinding.ListBeerFragmentBinding;
import com.example.beerlovers.model.Beer;
import com.example.beerlovers.model.DBBeer;
import com.example.beerlovers.model.ListType;
import com.example.beerlovers.model.NetworkState;
import com.example.beerlovers.utils.Utils;
import com.example.beerlovers.view.activities.BeerDetailsActivity;
import com.example.beerlovers.view.adapters.BeerViewHolder;
import com.example.beerlovers.view.adapters.BeersAdapter;
import com.example.beerlovers.view.adapters.BeersPagedListAdapter;
import com.example.beerlovers.viewmodel.ListBeerViewModel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

import br.com.zup.multistatelayout.MultiStateLayout;


public class ListBeerFragment extends Fragment implements BeerViewHolder.BeersAdapterListener {


    public static final String LIST_TYPE = "list-type";
    private ListBeerFragmentBinding binding;
    private ListBeerViewModel mViewModel;
    private BeersPagedListAdapter beersPagedListAdapter;
    private BeersAdapter beersAdapter;

    private InterstitialAd mInterstitialAd;
    private FirebaseAnalytics mFirebaseAnalytics;

    private String querySearch = "";
    ListType type;
    private boolean isSearch;


    public static ListBeerFragment newInstance(ListType type) {
        Bundle args = new Bundle();
        args.putSerializable(LIST_TYPE, type);
        ListBeerFragment fragment = new ListBeerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.list_beer_fragment, container, false);
        View view = binding.getRoot();
        setHasOptionsMenu(true);
        if (getArguments() != null && getArguments().get(LIST_TYPE) != null)
            type = (ListType) getArguments().getSerializable(LIST_TYPE);

        if (type != ListType.NETWORK)
            setHasOptionsMenu(false);

        mInterstitialAd = new InterstitialAd(requireContext());
        mInterstitialAd.setAdUnitId(getString(R.string.AD_KEY));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
                super.onAdLoaded();

            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mViewModel.searchBeer(querySearch);
                Bundle bundle = new Bundle();
                bundle.putString("query", querySearch);
                mFirebaseAnalytics.logEvent("Search", bundle);
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                mViewModel.searchBeer(querySearch);
                Bundle bundle = new Bundle();
                bundle.putString("query", querySearch);
                mFirebaseAnalytics.logEvent("Search", bundle);
            }
        });
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(requireContext());
        Bundle bundle = new Bundle();
        bundle.putString("name", type.toString());
        mFirebaseAnalytics.logEvent("screen", bundle);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ListBeerViewModel.class);

        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext()));

        if (type == ListType.NETWORK) {
            mViewModel.initPagedList();
            beersPagedListAdapter = new BeersPagedListAdapter(ListBeerFragment.this);

            mViewModel.getPagedListBeersLiveData().observe(ListBeerFragment.this, new Observer<PagedList<Beer>>() {
                @Override
                public void onChanged(@Nullable PagedList<Beer> pagedList) {
                    beersPagedListAdapter.submitList(pagedList);
                }
            });
            binding.rvList.setAdapter(beersPagedListAdapter);
        } else {
            if (type == ListType.FAVORITE) {
                mViewModel.getFavoritesBeers().observe(this, new Observer<List<DBBeer>>() {
                    @Override
                    public void onChanged(@Nullable List<DBBeer> dbBeers) {
                        beersAdapter = new BeersAdapter(ListBeerFragment.this, dbBeers);
                        binding.rvList.setAdapter(beersAdapter);
                    }
                });
            } else if (type == ListType.TASTED) {
                mViewModel.getTastedBeers().observe(this, new Observer<List<DBBeer>>() {
                    @Override
                    public void onChanged(@Nullable List<DBBeer> dbBeers) {
                        beersAdapter = new BeersAdapter(ListBeerFragment.this, dbBeers);
                        binding.rvList.setAdapter(beersAdapter);
                    }
                });
            }

        }

        mViewModel.getNetworkState().observe(this, new Observer<NetworkState>() {
            @Override
            public void onChanged(@Nullable NetworkState networkState) {
                if (networkState == NetworkState.LOADING) {
                    binding.mslView.setState(MultiStateLayout.State.LOADING);
                } else if (networkState == NetworkState.LOADED) {
                    if (beersPagedListAdapter.getItemCount() == 0) {
                        binding.mslView.setState(MultiStateLayout.State.EMPTY);
                    } else {
                        binding.mslView.setState(MultiStateLayout.State.CONTENT);
                    }
                } else if (networkState != null && networkState.status == NetworkState.Status.FAILED) {
                    binding.mslView.setState(MultiStateLayout.State.ERROR);
                    if (!Utils.isOnline(requireContext())) {
                        ((TextView) binding.mslView.getView(MultiStateLayout.State.ERROR).findViewById(R.id.tv_error)).setText(getString(R.string.no_connection));
                    } else {
                        ((TextView) binding.mslView.getView(MultiStateLayout.State.ERROR).findViewById(R.id.tv_error)).setText(networkState.message);
                    }

                    (binding.mslView.getView(MultiStateLayout.State.ERROR).findViewById(R.id.bt_retry)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadData();
                        }
                    });
                    binding.mslView.getView(MultiStateLayout.State.ERROR);
                }
            }
        });


    }

    public void loadData() {
        binding.mslView.setState(MultiStateLayout.State.LOADING);
        mViewModel.getPagedListBeersLiveData().getValue().getDataSource().invalidate();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu, menu);

        final SearchManager searchManager =
                (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setQueryHint(getString(R.string.search_hint));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                return false;
            }

            public boolean onQueryTextSubmit(String query) {
                querySearch = query;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    mViewModel.searchBeer(querySearch);
                    Bundle bundle = new Bundle();
                    bundle.putString("query", querySearch);
                    mFirebaseAnalytics.logEvent("Search", bundle);
                }

                isSearch = true;
                return false;
            }
        });
        MenuItem item = menu.findItem(R.id.search);
        item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                isSearch = false;
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                if (isSearch) {
                    mViewModel.searchBeer("");
                    querySearch = "";

                }
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onItemClick(Beer beer) {
        Intent intent = new Intent(getActivity(), BeerDetailsActivity.class);
        intent.putExtra(BeerDetailsActivity.BEER_KEY, beer);
        startActivity(intent);
    }

    @Override
    public void onItemClick(DBBeer beer) {
        Intent intent = new Intent(getActivity(), BeerDetailsActivity.class);
        intent.putExtra(BeerDetailsActivity.BEER_FROM_DB_KEY, beer);
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(final DBBeer beer) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(String.format(getString(R.string.dialog_delete), beer.getName(), type.toString()));
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (type == ListType.TASTED) {
                    beer.setTasted(false);
                    mViewModel.updateBeer(beer);
                } else if (type == ListType.FAVORITE) {
                    beer.setFavorite(false);
                    mViewModel.updateBeer(beer);
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
