package com.wipro.wipro.getfacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wipro.wipro.R;
import com.wipro.wipro.data.FactDetails;

import java.util.List;

public class FactsListAdapter extends RecyclerView.Adapter<FactsListItemViewHolder> {


    private Context mContext;
    private List<FactDetails> mListFactDetails;
    private FactsListContract.Presenter mListener;

    private FactsListAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * Create adapter & returns it.
     *
     * @return : Return the adapter reference
     */
    public static FactsListAdapter create(Context context) {
        return new FactsListAdapter(context);
    }

    /**
     * Set Data of adapter.
     *
     * @param listFactDetails : List of random facts
     * @return : Return the adapter reference
     */
    public FactsListAdapter withData(List<FactDetails> listFactDetails) {
        this.mListFactDetails = listFactDetails;
        return this;
    }

    /**
     * Set Listener in adapter for view action.
     *
     * @param listener : Callback listener to communicate with presenter
     * @return : Return the adapter reference
     */
    public FactsListAdapter withListener(FactsListContract.Presenter listener) {
        this.mListener = listener;
        return this;
    }

    @NonNull
    @Override
    public FactsListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_facts_item, parent, false);
        return new FactsListItemViewHolder(mContext, v);
    }

    @Override
    public void onBindViewHolder(@NonNull final FactsListItemViewHolder holder, int position) {
        if (mListFactDetails == null || mListFactDetails.size() <= position) {
            return;
        }
        FactDetails factDetails = mListFactDetails.get(position);
        holder.setValues(factDetails);
        holder.setClickListener(new FactsListItemViewHolder.IRecyclerViewListClickListener() {

            @Override
            public void onClick(View v, int position) {
                mListener.onListItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListFactDetails.size();
    }
}