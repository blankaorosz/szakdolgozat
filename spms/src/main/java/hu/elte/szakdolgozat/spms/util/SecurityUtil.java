package hu.elte.szakdolgozat.spms.util;

import hu.elte.szakdolgozat.spms.model.entity.spms.User;
import hu.elte.szakdolgozat.spms.security.SpmsUserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    /**
     * Get the User object of the authenticated user.
     *
     * @return null if there is no authenticated user, the {@link User} object otherwise
     */
    public static User getLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof SpmsUserPrincipal) {
            return ((SpmsUserPrincipal) principal).getUser();
        }

        return null;
    }
}
