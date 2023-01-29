package kr.jenna.plmography.models.VO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class ArticleBody {
    @Column(name = "article_body", length = 4000)
    private String value;

    public ArticleBody() {
    }

    public ArticleBody(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        ArticleBody otherArticleBody = (ArticleBody) other;

        return Objects.equals(value, otherArticleBody.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ArticleBody{" +
                "value='" + value + '\'' +
                '}';
    }
}
