package com.example.android.pjpopmovies1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * {@link MovieListRecyclerAdapter} exposes a list of movie items to a
 * {@link android.support.v7.widget.RecyclerView}
 */
public class MovieListRecyclerAdapter extends
        RecyclerView.Adapter<MovieListRecyclerAdapter.MovieViewHolder> {

    //TAG for log statements
    private static final String TAG = MovieListRecyclerAdapter.class.getSimpleName();
    // Create a final private ListItemClickListener called mOnClickListener
    /*
     * An on-click handler defined for an Activity to interface with the RecyclerView
     */
    private final ListItemClickListener mOnClickListener;
    private String[] mMovieData;
    //    private static int viewHolderCount;
    private int mNumberItems;

    /**
     * Constructor for MovieListRecyclerAdapter that accepts
     * the specification for the ListItemClickListener.
     * <p>
     * //     * @param numberOfItems Number of items to display in list
     *
     * @param listener Listener for list item clicks
     */
    public MovieListRecyclerAdapter(ListItemClickListener listener) {
//        mNumberItems = numberOfItems;
        mOnClickListener = listener;
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new MovieViewHolder that holds the View for each list item
     */
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MovieViewHolder viewHolder = new MovieViewHolder(view);

        return viewHolder;
    }

    // Implement OnClickListener in the MovieViewHolder class

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the correct
     * indices in the list for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        String movieItem = mMovieData[position];
        Log.d(TAG, "#" + position);
//        holder.bind(position);
        holder.mMovieTextView.setText(movieItem);
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available
     */
    @Override
    public int getItemCount() {
        if (null == mMovieData) return 0;
        return mNumberItems;
    }

    /**
     * @param movieData Position of the item in the list
     */
    public void setMovieData(String[] movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }

    /**
     * The interface that receives onClick messages.
     */
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    /**
     * Cache of the children views for a list item.
     */
    public class MovieViewHolder extends RecyclerView.ViewHolder
            implements OnClickListener {

        public final TextView mMovieTextView;

        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
         * onClick method below.
         *
         * @param itemView The View that you inflated in
         *                 {@link MovieListRecyclerAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public MovieViewHolder(View itemView) {
            super(itemView);

            mMovieTextView = itemView.findViewById(R.id.tv_movie_title);
//            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_movie_title);
//            viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_view_holder_instance);
            // Call setOnClickListener on the View passed into the constructor (use 'this' as the OnClickListener)
            itemView.setOnClickListener(this);
        }

        // Override onClick, passing the clicked item's position (getAdapterPosition())
        // to mOnClickListener via its onListItemClick method

        /**
         * Called whenever a user clicks on an item in the list.
         *
         * @param v The View that was clicked
         */
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
//            String movieInfo = mMovieData[clickedPosition];
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }

}