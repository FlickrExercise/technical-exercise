
package com.tigerspike.yetanotherimageflickr.network;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tigerspike.yetanotherimageflickr.models.FlickrPhotoResponse;
import com.tigerspike.yetanotherimageflickr.models.ImageDetail;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Class that calls the Flickr APIs, handles request, response and listener callbacks to activity
 */
public class AsyncFlickrClient {

    private AsyncHttpClient asyncHttpClient;
    private FlickrResponseListener flickrResponseListener;
    public final String TAG = AsyncFlickrClient.class.getSimpleName();

    // Secret: 84505d492797daaf
    private final String FLICKR_API_KEY = "814d627fa6f5a0e65f0013a3d35cfe4d";

    private final String FLICKR_URL = "https://api.flickr.com/services/rest/";

    /**
     * Constructor
     */
    public AsyncFlickrClient() {
        this.asyncHttpClient = new AsyncHttpClient();
    }

    /**
     * Sets request parameters and calls the flickr.photos.search API
     *
     * @param flickrResponseListener parse response from Flickr, return to activity
     */
    public void fetchFlickrImages(final FlickrResponseListener flickrResponseListener) {

        this.flickrResponseListener = flickrResponseListener;

        RequestParams params = new RequestParams();
        params.put("method", "flickr.photos.getRecent");
        params.put("api_key", FLICKR_API_KEY);
        params.put("privacy_filter", 1);
        params.put("per_page", 39);
        params.put("format", "json");
        params.put("nojsoncallback", 1);

        asyncHttpClient.get(FLICKR_URL, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int code, Header[] headers, JSONObject body) {

                if (body != null) {
                    try {
                        if (body.has("photos")) {
                            FlickrPhotoResponse flickrPhotoResponse = new Gson().fromJson(body.getJSONObject("photos").toString(), FlickrPhotoResponse.class);
                            flickrResponseListener.flickrResponse(flickrPhotoResponse.getImageDetailArrayList(), null);
                        } else {
                            flickrResponseListener.flickrResponse(null, "reponse body has no photos object");
                        }
                    } catch (Exception e) {
                        flickrResponseListener.flickrResponse(null, "caught Exception in parsing: " + e.toString());
                    }
                } else {
                    flickrResponseListener.flickrResponse(null, "reponse body is null");
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                flickrResponseListener.flickrResponse(null, "asyncClient onFailure, most likely cause: internet issues");
            }
        });
    }

    public interface FlickrResponseListener {

        void flickrResponse(List<ImageDetail> imageList, String errorMessage);

    }
}
