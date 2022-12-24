package kr.jenna.plmography.exceptions;

public class ContentNotFound extends RuntimeException {
    public ContentNotFound(Long contentId) {
        super("Content not found : " + contentId);
    }
}
