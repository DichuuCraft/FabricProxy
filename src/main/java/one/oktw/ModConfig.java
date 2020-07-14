package one.oktw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ModConfig {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private boolean BungeeCord = false;
    private boolean Velocity = false;
    private String secret = "";

    public Boolean getVelocity() {
        String env = System.getenv("FABRIC_PROXY_VELOCITY");
        if (env == null) {
            return Velocity;
        } else {
            return env.equalsIgnoreCase("true");
        }
    }

    public Boolean getBungeeCord() {
        String env = System.getenv("FABRIC_PROXY_BUNGEECORD");
        if (env == null) {
            return BungeeCord;
        } else {
            return env.equalsIgnoreCase("true");
        }
    }

    public String getSecret() {
        String env = System.getenv("FABRIC_PROXY_SECRET");
        if (env == null) {
            return secret;
        } else {
            return env;
        }
    }
}
