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
            Picasso.get().load(R.mipmap.ic_launcher).fit().
                    transform(new CircleTransform()).into(binding.ivBeer);
            binding.tvTitle.setText(beer.getName());
            if (beer.getLabels() != null)
                if (beer.getLabels().getIcon() != null)
                    Picasso.get().load(beer.getLabels().getIcon()).fit().
                            transform(new CircleTransform()).into(binding.ivBeer);
                else if(beer.getLabels().getMedium() != null)
                    Picasso.get().load(beer.getLabels().getMedium()).fit().
                            transform(new CircleTransform()).into(binding.ivBeer);

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(beer);
                }
            });
            if (beer.getIbu() != null)
                binding.tvIbu.setText(beer.getIbu());
            else if (beer.getStyle() != null && beer.getStyle().getIbuMin() != null) {
                binding.tvIbu.setText(beer.getStyle().getIbuMin());
            } else {
                binding.tvIbu.setVisibility(View.GONE);
                binding.tvLabelIbu.setVisibility(View.GONE);
            }
            if (beer.getAbv() != null)
                binding.tvAbv.setText(String.format("%s%%", beer.getAbv()));
            else if (beer.getStyle().getAbvMin() != null) {
                binding.tvAbv.setText(String.format("%s%%", beer.getStyle().getAbvMin()));
            } else {
                binding.tvAbv.setVisibility(View.GONE);
                binding.tvAbv.setVisibility(View.GONE);
            }

            if (beer.getStyle() != null) {
                if (beer.getStyle().getShortName() != null)
                    binding.tvStyle.setText(beer.getStyle().getShortName());
                else if (beer.getStyle().getName() != null)
                    binding.tvStyle.setText(beer.getStyle().getName());
                else {
                    binding.tvStyle.setVisibility(View.GONE);
                    binding.tvLabelStyle.setVisibility(View.GONE);
                }
            } else {
                binding.tvStyle.setVisibility(View.GONE);
                binding.tvLabelStyle.setVisibility(View.GONE);
            }
        }

    }


    public interface BeersAdapterListener {
        void onItemClick(Beer beer);
    }
}
