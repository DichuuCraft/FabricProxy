package one.oktw.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.server.MinecraftServer;
import one.oktw.FabricProxy;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
    @Inject(method = "reload", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/server/PlayerManager;saveAllPlayerData()V",
        shift = At.Shift.BEFORE
    ))
    private void onReload(){
        FabricProxy.loadConfig();
    }
}