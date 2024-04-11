package com.Daniel.YtManagerBackend.model;

import jakarta.persistence.*;

import java.sql.Blob;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String link;
    private String duration;
    private String thumbnailUrl;
    private String bookmark;
    @Lob
    private String note;
    @ElementCollection
    @CollectionTable(name = "timestamps", joinColumns = @JoinColumn(name = "video_id"))
    @MapKeyColumn(name = "timestamp_index")
    @AttributeOverrides({
            @AttributeOverride(name = "description", column = @Column(name = "description")),
            @AttributeOverride(name = "startTime", column = @Column(name = "start_time")),
            @AttributeOverride(name = "endTime", column = @Column(name = "end_time"))
    })
    private Map<Integer, TimestampInfo> timestamps = new HashMap<>(); // Store timestamps as a map

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Map<Integer, TimestampInfo> getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(Map<Integer, TimestampInfo> timestamps) {
        this.timestamps = timestamps;
    }

}