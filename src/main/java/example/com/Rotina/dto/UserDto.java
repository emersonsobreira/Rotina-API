package example.com.Rotina.dto;

public class UserDto {
    private String name;
    private String email;
    private boolean enabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public UserDto(String name, String email, boolean enabled) {
        this.name = name;
        this.email = email;
        this.enabled = enabled;

    }

}
