package kr.jenna.plmography.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kr.jenna.plmography.models.Content;
import org.springframework.data.jpa.domain.Specification;

public class ContentSpecification {
    public static Specification<Content> likePlatform(String platform) {
        return new Specification<Content>() {
            @Override
            public Predicate toPredicate(Root<Content> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("platform"), "%" + platform + "%");
            }
        };
    }

    public static Specification<Content> equalType(String type) {
        return new Specification<Content>() {
            @Override
            public Predicate toPredicate(Root<Content> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("type"), type);
            }
        };
    }

    public static Specification<Content> likeTmdbGenreId(String tmdbGenreId) {
        return new Specification<Content>() {
            @Override
            public Predicate toPredicate(Root<Content> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("tmdbGenreId"), "%" + tmdbGenreId + "%");
            }
        };
    }

    public static Specification<Content> likeKorTitle(String korTitle) {
        return new Specification<Content>() {
            @Override
            public Predicate toPredicate(Root<Content> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("korTitle"), "%" + korTitle + "%");
            }
        };
    }

    public static Specification<Content> likeEngTitle(String engTitle) {
        return new Specification<Content>() {
            @Override
            public Predicate toPredicate(Root<Content> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("engTitle"), "%" + engTitle + "%");
            }
        };
    }

    public static Specification<Content> betweenReleaseDate(Integer releaseDate) {
        return new Specification<Content>() {
            @Override
            public Predicate toPredicate(Root<Content> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.between(root.get("releaseDate"),
                        releaseDate, releaseDate + 9);
            }
        };
    }
}
