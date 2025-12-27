package fpt.kiennt169.springboot.specifications;

import fpt.kiennt169.springboot.entities.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.UUID;

public class UserSpecification {

    private UserSpecification() {}

    public static Specification<User> hasFullName(String fullName) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(fullName)) return null;
            return cb.like(cb.lower(root.get("fullName")), "%" + fullName.toLowerCase() + "%");
        };
    }

    public static Specification<User> hasEmail(String email) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(email)) return null;
            return cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
        };
    }

    public static Specification<User> isActive(Boolean active) {
        return (root, query, cb) -> active == null ? null : cb.equal(root.get("active"), active);
    }

    public static Specification<User> hasRole(UUID roleId) {
        return (root, query, cb) -> {
            if (roleId == null) return null;
            query.distinct(true);
            return cb.equal(root.join("roles").get("id"), roleId);
        };
    }

    public static Specification<User> isActiveOnly() {
        return isActive(true);
    }

    public static Specification<User> isInactiveOnly() {
        return isActive(false);
    }
}
