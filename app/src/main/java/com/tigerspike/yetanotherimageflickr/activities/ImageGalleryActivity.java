
package com.tigerspike.yetanotherimageflickr.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.tigerspike.yetanotherimageflickr.models.ImageDetail;
import com.tigerspike.yetanotherimageflickr.R;
import com.tigerspike.yetanotherimageflickr.adapters.ImageGalleryAdapter;
import com.tigerspike.yetanotherimageflickr.network.AsyncFlickrClient;

import java.util.ArrayList;
import java.util.List;

public class ImageGalleryActivity extends AppCompatActivity implements AsyncFlickrClient.FlickrResponseListener {

    private AsyncFlickrClient asyncFlickrClient;
    private RecyclerView imageRecyclerView;
    private ImageGalleryAdapter imageGalleryAdapter;

    ProgressDialog progressDialog;
    public static final String LOG_TAG = ImageGalleryActivity.class.getSimpleName();

    @Override
    public void flickrResponse(List<ImageDetail> imageList, String errorMessage) {

        if (imageList != null) {
            imageGalleryAdapter.addImages(imageList);
            imageGalleryAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Custom error message replacing " + errorMessage, Toast.LENGTH_SHORT).show();
        }

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);

        initialiseScreen();

    }

    private void initialiseScreen() {

        imageRecyclerView = (RecyclerView) findViewById(R.id.image_recycler_view);

        ArrayList<ImageDetail> imageDetailList = new ArrayList<>();
        imageGalleryAdapter = new ImageGalleryAdapter(imageDetailList);

        asyncFlickrClient = new AsyncFlickrClient();
        asyncFlickrClient.fetchFlickrImages(this);

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
//                progressDialog.setMessage("loading");
                progressDialog.show();
        }
        configureRecyclerView();

    }

    private void configureRecyclerView() {

        imageRecyclerView.setAdapter(imageGalleryAdapter);
        imageRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        imageRecyclerView.setHasFixedSize(true);

        // Open image in system browser
        imageGalleryAdapter.setOnItemClickListener(new ImageGalleryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                ImageDetail imageDetail = imageGalleryAdapter.getItem(position);

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(imageDetail.getPhotoUrl()));
                startActivity(browserIntent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}

