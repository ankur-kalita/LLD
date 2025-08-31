public class TryIt {
    public static void main(String[] args) {
        ProfileService svc = new ProfileService();
        UserProfile p = svc.createMinimal("u1", "a@b.com");
        System.out.println("Before: " + p.getEmail());
        // p.setEmail("evil@example.com"); // demonstrates mutability problem // now will not
        System.out.println("After:  " + p.getEmail());
        System.out.println("=> In the solution, this setter disappears and object becomes immutable.");

        UserProfile pp = new UserProfile.Builder()
                .id("u1")
                .email("a@b.com")
                .displayName("Bob")
                .marketingOptIn(true)
                .build();
        System.out.println(pp.getId() + " " + pp.getEmail() + " " + pp.getDisplayName());
        System.out.println("No setters available; object is immutable.");
    }
}
