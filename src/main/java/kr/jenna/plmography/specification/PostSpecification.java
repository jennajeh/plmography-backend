package kr.jenna.plmography.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.vo.UserId;
import org.springframework.data.jpa.domain.Specification;

public class PostSpecification {
    public static Specification<Post> likeTitleOrBody(String keyword) {
        return new Specification<Post>() {
            @Override
            public Predicate toPredicate(Root<Post> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("postBody").get("value")), "%" + keyword.toUpperCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("title").get("value")), "%" + keyword.toUpperCase() + "%"));
            }
        };
    }

    public static Specification<Post> equalUserId(UserId userId) {
        return new Specification<Post>() {
            @Override
            public Predicate toPredicate(
                    Root<Post> root,
                    CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("userId"), userId);
            }
        };
    }
}
