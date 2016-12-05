
package com.tigerspike.yetanotherimageflickr.models;

public class ImageDetail {

    private String id;
    private String owner;
    private String secret;
    private int server;
    private int farm;
    private String title;
    private String photoUrl;

    public static final String LOG_TAG = ImageDetail.class.getSimpleName();

    /**
     * Getter method for photo id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter method for photo owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Setter method for photo owner
     *
     * @param owner photo owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Getter method for photo secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     * Getter method for photo title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter method for photo owner
     *
     * @param title photo title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter method for photo Flickr URL
     */
    public String getPhotoUrl() {
        return "https://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + "_c.jpg";
    }

    @Override
    public String toString() {
        return "ImageDetail{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", secret='" + secret + '\'' +
                ", server=" + server +
                ", farm=" + farm +
                ", title='" + title + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
