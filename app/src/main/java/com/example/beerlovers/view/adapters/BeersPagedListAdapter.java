package com.example.beerlovers.view.adapters;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beerlovers.R;
import com.example.beerlovers.databinding.ItemListBeerBinding;
import com.example.beerlovers.model.Beer;
import com.example.beerlovers.model.NetworkState;
import com.example.beerlovers.utils.CircleTransform;
import com.squareup.picasso.Picasso;

public class BeersPagedListAdapter extends PagedListAdapter<Beer, RecyclerView.ViewHolder> {

    private BeerViewHolder.BeersAdapterListener listener;

    public BeersPagedListAdapter(BeerViewHolder.BeersAdapterListener listener) {
        super(Beer.DIFF_CALLBACK);
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        ItemListBeerBinding itemBinding = ItemListBeerBinding.inflate(layoutInflater, viewGroup, false);
        BeerViewHolder viewHolder = new BeerViewHolder(itemBinding, listener);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((BeerViewHolder) viewHolder).bind(getItem(i));
    }

}
