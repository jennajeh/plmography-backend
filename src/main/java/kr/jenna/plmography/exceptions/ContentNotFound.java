package kr.jenna.plmography.exceptions;

public class ContentNotFound extends RuntimeException {
    public ContentNotFound() {
        super("Content not found");
    }

    public ContentNotFound(Long contentId) {
        super("Content not found : " + contentId);
    }
}
