
package com.tigerspike.yetanotherimageflickr.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tigerspike.yetanotherimageflickr.models.ImageDetail;
import com.tigerspike.yetanotherimageflickr.R;

import java.util.ArrayList;
import java.util.List;


public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.ViewHolder> {

    public static final String LOG_TAG = ImageGalleryAdapter.class.getSimpleName();
    private ArrayList<ImageDetail> imageDetailList;
    private Context context;
    private static OnItemClickListener listener;

    /**
     * Constructor
     *
     * @param imageDetailArray Arraylist of images
     */
    public ImageGalleryAdapter(ArrayList<ImageDetail> imageDetailArray) {
        imageDetailList = imageDetailArray;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View photoView = inflater.inflate(R.layout.adapter_image_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(context, photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ImageDetail imageDetail = imageDetailList.get(position);

        // Set item views based on the data model
        ImageView photoImage = viewHolder.ivImage;
        TextView tvTitle = viewHolder.tvTitle;

        Picasso.with(context)
                .load(imageDetail.getPhotoUrl())
                .resize(300, 300)
                .centerCrop()
                .into(photoImage);

        tvTitle.setText(imageDetail.getTitle());
    }

    @Override
    public int getItemCount() {
        return imageDetailList.size();
    }


    /**
     * Get single image from list
     *
     * @param position photo position
     */
    public ImageDetail getItem(int position) {
        return imageDetailList.get(position);
    }

    /**
     * add imageDetails to list
     *
     * @param imageDetails list of imageDetails to be added
     */
    public void addImages(List<ImageDetail> imageDetails) {
        imageDetailList.addAll(imageDetails);
    }

    /**
     * Declaring listener interface along with methods
     */
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    /**
     * Setting listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * ViewHolder class
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvTitle;
        private Context context;

        ViewHolder(Context context, final View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            this.context = context;
            // Attach a click listener to the entire row view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }
    }
}
