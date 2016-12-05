package com.tigerspike.yetanotherimageflickr.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FlickrPhotoResponse {

    @SerializedName("photo")
    ArrayList<ImageDetail> imageDetailArrayList;

    public ArrayList<ImageDetail> getImageDetailArrayList() {
        return imageDetailArrayList;
    }

    public void setImageDetailArrayList(ArrayList<ImageDetail> imageDetailArrayList) {
        this.imageDetailArrayList = imageDetailArrayList;
    }

    @Override
    public String toString() {
        return "FlickrPhotoResponse{" +
                "imageDetailArrayList=" + imageDetailArrayList +
                '}';
    }
}
