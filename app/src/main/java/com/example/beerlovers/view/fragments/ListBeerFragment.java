package com.example.beerlovers.view.fragments;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beerlovers.R;
import com.example.beerlovers.databinding.ListBeerFragmentBinding;
import com.example.beerlovers.model.Beer;
import com.example.beerlovers.model.FragmentType;
import com.example.beerlovers.model.NetworkState;
import com.example.beerlovers.utils.Utils;
import com.example.beerlovers.view.activities.BeerActivity;
import com.example.beerlovers.view.adapters.BeersAdapter;
import com.example.beerlovers.viewmodel.ListBeerViewModel;

import br.com.zup.multistatelayout.MultiStateLayout;


public class ListBeerFragment extends Fragment implements BeersAdapter.BeersAdapterListener {


    public static final String LIST_TYPE = "list-type";
    private ListBeerFragmentBinding binding;
    private ListBeerViewModel mViewModel;
    private BeersAdapter adapter;

    FragmentType type;

    public static ListBeerFragment newInstance(FragmentType type) {
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
            type = (FragmentType) getArguments().getSerializable(LIST_TYPE);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ListBeerViewModel.class);
        mViewModel.init(type);
        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BeersAdapter(ListBeerFragment.this);

        mViewModel.getBeersLiveData().observe(ListBeerFragment.this, new Observer<PagedList<Beer>>() {
            @Override
            public void onChanged(@Nullable PagedList<Beer> pagedList) {
                adapter.submitList(pagedList);
            }
        });

        mViewModel.getNetworkState().observe(this, new Observer<NetworkState>() {
            @Override
            public void onChanged(@Nullable NetworkState networkState) {
                if (networkState == NetworkState.LOADING) {
                    binding.mslView.setState(MultiStateLayout.State.LOADING);
                } else if (networkState == NetworkState.LOADED) {
                    if (adapter.getItemCount() == 0) {
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

        binding.rvList.setAdapter(adapter);


    }

    public void loadData() {
        binding.mslView.setState(MultiStateLayout.State.LOADING);
        mViewModel.getBeersLiveData().getValue().getDataSource().invalidate();
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
                mViewModel.searchBeer(query);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onItemClick(Beer beer) {
        Intent intent = new Intent(getActivity(), BeerActivity.class);
        intent.putExtra(BeerActivity.BEER_KEY, beer);
        startActivity(intent);
    }
}
