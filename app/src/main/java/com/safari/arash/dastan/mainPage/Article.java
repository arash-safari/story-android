package com.safari.arash.dastan.mainPage;

public class Article {

    int likeCount,
            viewCount,
            commentCount;
    String name,
            title,
            src,
            author,
            authorProfile,
            rightSpeaker,
            leftSpeaker;

    public Article(String name,String title, int likeCount, int viewCount, int commentCount, String src, String author, String authorProfile,String rightSpeaker,String leftSpeaker) {
        this.name = name;
        this.title = title;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.src = src;
        this.author = author;
        this.authorProfile = authorProfile;
        this.rightSpeaker = rightSpeaker;
        this.leftSpeaker = leftSpeaker;

    }

    public String getTitle() {
        return title;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public String getSrc() {
        return src;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorProfile() {
        return authorProfile;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAuthorProfile(String authorProfile) {
        this.authorProfile = authorProfile;
    }

    public String getName() {
        return name;
    }

    public String getRightSpeaker() {
        return rightSpeaker;
    }

    public String getLeftSpeaker() {
        return leftSpeaker;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRightSpeaker(String rightSpeaker) {
        this.rightSpeaker = rightSpeaker;
    }

    public void setLeftSpeaker(String leftSpeaker) {
        this.leftSpeaker = leftSpeaker;
    }
}
