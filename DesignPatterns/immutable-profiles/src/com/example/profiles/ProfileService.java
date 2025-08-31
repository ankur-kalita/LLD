import java.util.Objects;

/**
 * Assembles profiles with scattered, inconsistent validation.
 */
public class ProfileService {

    // returns a fully built profile but mutates it afterwards (bug-friendly)
    public UserProfile createMinimal(String id, String email) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("bad id");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("bad email");

        return new UserProfile.Builder().id(id).email(email).build();
    }

    public UserProfile updateDisplayName(UserProfile p, String displayName) {
        Objects.requireNonNull(p, "profile");
        if (displayName != null && displayName.length() > 100) {
            // silently trim (inconsistent policy)
            displayName = displayName.substring(0, 100);
        }
        return new UserProfile.Builder()
                .id(p.getId())
                .email(p.getEmail())
                .phone(p.getPhone())
                .displayName(displayName)
                .address(p.getAddress())
                .marketingOptIn(p.isMarketingOptIn())
                .twitter(p.getTwitter())
                .github(p.getGithub())
                .build();
    }
}
