package kr.jenna.plmography.services.content;

import kr.jenna.plmography.dtos.content.ContentDto;
import kr.jenna.plmography.dtos.content.ContentsDto;
import kr.jenna.plmography.dtos.content.UserProfileContentDto;
import kr.jenna.plmography.dtos.content.UserProfileContentsDto;
import kr.jenna.plmography.dtos.page.PagesDto;
import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.ContentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import kr.jenna.plmography.specification.ContentSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetContentsService {
    private ContentRepository contentRepository;
    private UserRepository userRepository;

    public GetContentsService(ContentRepository contentRepository,
                              UserRepository userRepository) {
        this.contentRepository = contentRepository;
        this.userRepository = userRepository;
    }

    public ContentsDto topRated() {
        List<Content> contents = contentRepository
                .findAllByPopularityGreaterThanOrderByPopularityDesc(2400);

        List<ContentDto> contentsDtos = contents.stream()
                .map(content -> content.toContentDto())
                .collect(Collectors.toList());

        return new ContentsDto(contentsDtos);
    }

    public ContentsDto filter(String platform, String type, String genreId,
                              Integer releaseDate, String searchTitle, String sort,
                              Integer page, Integer size) {
        Sort sortBy = Sort.by("createdAt").descending();

        if (sort.equals("releaseDate")) {
            sortBy = Sort.by("releaseDate").descending();
        }

        if (sort.equals("popularity")) {
            sortBy = Sort.by("popularity").descending();
        }

        if (sort.equals("korTitle")) {
            sortBy = Sort.by("korTitle").ascending();
        }

        Pageable pageable = PageRequest.of(page - 1, size, sortBy);

        Specification<Content> spec = (root, query, criteriaBuilder) -> null;

        if (platform != null) {
            spec = spec.and(ContentSpecification.likePlatform(platform));
        }

        if (type != null) {
            spec = spec.and(ContentSpecification.equalType(type));
        }

        if (genreId != null) {
            spec = spec.and(ContentSpecification.likeTmdbGenreId(genreId));
        }

        if (releaseDate != null) {
            spec = spec.and(ContentSpecification.betweenReleaseDate(releaseDate));
        }

        if (searchTitle != null) {
            spec = spec.and(ContentSpecification.likeKorTitle(searchTitle))
                    .or(ContentSpecification.likeEngTitle(searchTitle));
        }

        Page<Content> contents = contentRepository.findAll(spec, pageable);

        List<ContentDto> contentDtos = contents.stream()
                .map(Content::toContentDto)
                .collect(Collectors.toList());

        PagesDto pagesDto = new PagesDto(contents.getTotalPages());

        return new ContentsDto(contentDtos, pagesDto);
    }

    public UserProfileContentsDto favoriteContents(Long userId, String favoriteContentId) {
        String[] contentIds = favoriteContentId.split(",");

        List<UserProfileContentDto> contentDtos = Arrays.stream(contentIds)
                .map(contentId -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new UserNotFound());

                    Content content = contentRepository.findByTmdbId(Long.parseLong(contentId))
                            .orElseThrow(() -> new ContentNotFound());

                    return new UserProfileContentDto(
                            user.getId(),
                            content.getTmdbId(),
                            content.getImageUrl(),
                            content.getKorTitle());
                }).toList();

        return new UserProfileContentsDto(contentDtos);
    }

    public UserProfileContentsDto watchedContents(Long userId, String watchedContentId) {
        String[] contentIds = watchedContentId.split(",");

        List<UserProfileContentDto> contentDtos = Arrays.stream(contentIds)
                .map(contentId -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new UserNotFound());

                    Content content = contentRepository.findByTmdbId(Long.parseLong(contentId))
                            .orElseThrow(() -> new ContentNotFound());

                    return new UserProfileContentDto(
                            user.getId(),
                            content.getTmdbId(),
                            content.getImageUrl(),
                            content.getKorTitle());
                }).toList();

        return new UserProfileContentsDto(contentDtos);
    }

    public UserProfileContentsDto wishContents(Long userId, String wishContentId) {
        String[] contentIds = wishContentId.split(",");

        List<UserProfileContentDto> contentDtos = Arrays.stream(contentIds)
                .map(contentId -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new UserNotFound());

                    Content content = contentRepository.findByTmdbId(Long.parseLong(contentId))
                            .orElseThrow(() -> new ContentNotFound());

                    return new UserProfileContentDto(
                            user.getId(),
                            content.getTmdbId(),
                            content.getImageUrl(),
                            content.getKorTitle());
                }).toList();

        return new UserProfileContentsDto(contentDtos);
    }
}
