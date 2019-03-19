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
import com.example.beerlovers.databinding.LastViewItemBinding;
import com.example.beerlovers.model.Beer;
import com.example.beerlovers.model.NetworkState;
import com.example.beerlovers.utils.CircleTransform;
import com.squareup.picasso.Picasso;

public class BeersAdapter extends PagedListAdapter<Beer, RecyclerView.ViewHolder> {

    private BeersAdapterListener listener;

    public BeersAdapter(BeersAdapterListener listener) {
        super(Beer.DIFF_CALLBACK);
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        ItemListBeerBinding itemBinding = ItemListBeerBinding.inflate(layoutInflater, viewGroup, false);
        BeerViewHolder viewHolder = new BeerViewHolder(itemBinding);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((BeerViewHolder) viewHolder).bind(getItem(i));
    }


    public class BeerViewHolder extends RecyclerView.ViewHolder {
        ItemListBeerBinding binding;


        public BeerViewHolder(ItemListBeerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final Beer beer) {
            binding.tvDescription.setText(beer.getDescription());
            binding.tvTitle.setText(beer.getName());
            if (beer.getLabels() != null)
                Picasso.get().load(beer.getLabels().getIcon()).fit().
                        transform(new CircleTransform()).into(binding.ivBeer);
            else {
                Picasso.get().load(R.mipmap.ic_launcher).fit().
                        transform(new CircleTransform()).into(binding.ivBeer);
            }

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(beer);
                }
            });
        }

    }


    public interface BeersAdapterListener {
        void onItemClick(Beer beer);
    }
}
