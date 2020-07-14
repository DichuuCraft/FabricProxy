package one.oktw.mixin.bungee;

import net.minecraft.network.packet.c2s.handshake.HandshakeC2SPacket;
import net.minecraft.util.PacketByteBuf;
import one.oktw.FabricProxy;
import one.oktw.interfaces.IHandshakeC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HandshakeC2SPacket.class)
public abstract class HandshakeC2SPacketMixin implements IHandshakeC2SPacket {
    @Shadow
    private String address;

    // @Redirect(method = "read", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/PacketByteBuf;readString(I)Ljava/lang/String;"))
    // private String onReadPacketData(PacketByteBuf buf, int int_1) {
    //     if (!FabricProxy.config.getBungeeCord()) {
    //         return buf.readString(255);
    //     }

    //     return buf.readString(Short.MAX_VALUE);
    // }

    @ModifyConstant(method = "read", constant = @Constant(intValue = 255))
    private int getBufLen(int len){
        if (!FabricProxy.config.getBungeeCord()) {
            return 255;
        }

        return Short.MAX_VALUE;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }
}
