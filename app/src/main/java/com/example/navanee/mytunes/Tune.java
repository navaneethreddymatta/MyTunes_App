package com.example.navanee.mytunes;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by navanee on 03-10-2016.
 */
public class Tune implements Serializable{
    private String title;
    private String summary;
    private Date releaseDate;
    private String thumbnail;
    private String image;
    private Date lastUpdated;
    private boolean isMatched;

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Tune{" +
                "title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", releaseDate=" + releaseDate +
                ", thumbnail='" + thumbnail + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public void setImage(String image) {
        this.image = image;
    }
}
