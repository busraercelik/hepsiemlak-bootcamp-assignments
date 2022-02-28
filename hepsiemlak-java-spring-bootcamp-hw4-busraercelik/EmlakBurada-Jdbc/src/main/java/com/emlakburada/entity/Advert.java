package com.emlakburada.entity;

public class Advert {
    private long id;
    private int adNo;
    private String description;
    private int duration;
    private boolean shouldHighlighted = false;
    private boolean isReviewed = false;
    private boolean isActive;

    public Advert() {
    }

    public Advert(int adNo, String description, boolean isActive) {
        this.adNo = adNo;
        this.description = description;
        this.isActive = isActive;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAdNo() {
        return adNo;
    }

    public void setAdNo(int adNo) {
        this.adNo = adNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isShouldHighlighted() {
        return shouldHighlighted;
    }

    public void setShouldHighlighted(boolean shouldHighlighted) {
        this.shouldHighlighted = shouldHighlighted;
    }

    public boolean isReviewed() {
        return isReviewed;
    }

    public void setReviewed(boolean reviewed) {
        isReviewed = reviewed;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", adNo=" + adNo +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", shouldHighlighted=" + shouldHighlighted +
                ", isReviewed=" + isReviewed +
                ", isActive=" + isActive +
                '}';
    }
}
