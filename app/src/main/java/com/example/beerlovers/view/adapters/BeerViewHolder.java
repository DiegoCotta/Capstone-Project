package com.example.beerlovers.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.beerlovers.R;
import com.example.beerlovers.databinding.ItemListBeerBinding;
import com.example.beerlovers.model.Beer;
import com.example.beerlovers.model.DBBeer;
import com.example.beerlovers.utils.CircleTransform;
import com.squareup.picasso.Picasso;

public class BeerViewHolder extends RecyclerView.ViewHolder {
    ItemListBeerBinding binding;
    BeersAdapterListener listener;

    public BeerViewHolder(ItemListBeerBinding binding, BeersAdapterListener listener) {
        super(binding.getRoot());
        this.listener = listener;
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
            else if (beer.getLabels().getMedium() != null)
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

    public void bind(final DBBeer beer) {
        Picasso.get().load(R.mipmap.ic_launcher).fit().
                transform(new CircleTransform()).into(binding.ivBeer);
        binding.tvTitle.setText(beer.getName());

        if (!beer.getIcon().equals(""))
            Picasso.get().load(beer.getIcon()).fit().
                    transform(new CircleTransform()).into(binding.ivBeer);
        else if (!beer.getImage().equals(""))
            Picasso.get().load(beer.getImage()).fit().
                    transform(new CircleTransform()).into(binding.ivBeer);

        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(beer);
            }
        });
        if (!beer.getIbu().equals(""))
            binding.tvIbu.setText(beer.getIbu());
        else {
            binding.tvIbu.setVisibility(View.GONE);
            binding.tvLabelIbu.setVisibility(View.GONE);
        }
        if (!beer.getAbv().equals(""))
            binding.tvAbv.setText(String.format("%s%%", beer.getAbv()));
        else {
            binding.tvAbv.setVisibility(View.GONE);
            binding.tvAbv.setVisibility(View.GONE);
        }

        if (beer.getStyle() != null) {
            if (beer.getStyle() != null)
                binding.tvStyle.setText(beer.getStyle());
            else {
                binding.tvStyle.setVisibility(View.GONE);
                binding.tvLabelStyle.setVisibility(View.GONE);
            }
        } else {
            binding.tvStyle.setVisibility(View.GONE);
            binding.tvLabelStyle.setVisibility(View.GONE);
        }

    }

    public interface BeersAdapterListener {
        void onItemClick(Beer beer);

        void onItemClick(DBBeer beer);
    }
}


