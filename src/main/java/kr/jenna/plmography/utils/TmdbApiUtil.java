package kr.jenna.plmography.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TmdbApiUtil {
    @Value("${tmdb.api-key}")
    private String apiKey;
    
}

