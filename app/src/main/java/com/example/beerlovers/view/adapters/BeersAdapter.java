package com.example.beerlovers.view.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.beerlovers.databinding.ItemListBeerBinding;
import com.example.beerlovers.model.DBBeer;

import java.util.List;

public class BeersAdapter extends RecyclerView.Adapter<BeerViewHolder> {

    BeerViewHolder.BeersAdapterListener listener;
    List<DBBeer> beers;

    public BeersAdapter(BeerViewHolder.BeersAdapterListener listener, List<DBBeer> beers) {
        this.listener = listener;
        this.beers = beers;
    }

    @NonNull
    @Override
    public BeerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemListBeerBinding itemBinding = ItemListBeerBinding.inflate(layoutInflater, viewGroup, false);
        BeerViewHolder viewHolder = new BeerViewHolder(itemBinding, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BeerViewHolder beerViewHolder, int i) {
         beerViewHolder.bind(beers.get(i));
    }

    @Override
    public int getItemCount() {
        return beers.size();
    }
}
