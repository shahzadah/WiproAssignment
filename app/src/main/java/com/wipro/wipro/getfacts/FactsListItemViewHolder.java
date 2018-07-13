package com.wipro.wipro.getfacts;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.wipro.wipro.R;
import com.wipro.wipro.data.FactDetails;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FactsListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.image_fact)
    ImageView ivFactRep;

    @BindView(R.id.textview_title)
    TextView tvTitle;

    @BindView(R.id.textview_desc)
    TextView tvDescription;

    @BindView(R.id.progress_image_load)
    ProgressBar pbImageLoad;

    @BindDrawable(R.drawable.ic_no_image)
    Drawable mNoImageDrawable;

    private final Context mContext;
    private IRecyclerViewListClickListener clickListener;

    FactsListItemViewHolder(Context context, View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mContext = context;
        itemView.setOnClickListener(this);
    }

    public void setValues(FactDetails factDetails) {
        tvTitle.setText(factDetails.getTitle());
        tvDescription.setText(factDetails.getDescription());
        ivFactRep.setImageDrawable(mNoImageDrawable);
        pbImageLoad.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(factDetails.getImageUrl())) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop();
            Glide.with(mContext)
                    .load(factDetails.getImageUrl())
                    .apply(requestOptions)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            pbImageLoad.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            pbImageLoad.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(ivFactRep);
        } else {
            pbImageLoad.setVisibility(View.GONE);
        }
    }

    /**
     * Setter for listener.
     */
    public void setClickListener(IRecyclerViewListClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onClick(View v) {
        clickListener.onClick(v, getAdapterPosition());
    }

    interface IRecyclerViewListClickListener {

        /**
         * Called when the view is clicked.
         *
         * @param v        view that is clicked
         * @param position of the clicked item
         */
        void onClick(View v, int position);
    }
}
