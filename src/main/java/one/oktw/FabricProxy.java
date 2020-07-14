package one.oktw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

public class FabricProxy implements ModInitializer {
    private static final Logger LOGGER = LogManager.getLogger();
    public static ModConfig config;

    public static void loadConfig() {
        try {
            File cfg = new File("config", "FabricProxy.json");
            if (cfg.exists()){
                try (Reader reader = new InputStreamReader(new FileInputStream(cfg), StandardCharsets.UTF_8)){
                    config = ModConfig.GSON.fromJson(reader, ModConfig.class);
                }
            }
            else {
                config = new ModConfig();
            }
            try (Writer writer = new OutputStreamWriter(new FileOutputStream(cfg), StandardCharsets.UTF_8)){
                writer.write(ModConfig.GSON.toJson(config));
            }
        }
        catch(Throwable e){
            LOGGER.error("Failed to load config", e);
        }
    }

    @Override
    public void onInitialize() {
        loadConfig();
    }
}
