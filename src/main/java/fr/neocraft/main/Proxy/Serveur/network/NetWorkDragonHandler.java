package fr.neocraft.main.Proxy.Serveur.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import info.ata4.minecraft.dragon.server.entity.EntityTameableDragon;
import io.netty.channel.ChannelHandler.Sharable;
import net.minecraft.entity.player.EntityPlayerMP;

@Sharable
public class NetWorkDragonHandler implements IMessageHandler<NetWorkDragon, IMessage> {


    @Override
    public IMessage onMessage(NetWorkDragon message, MessageContext ctx) {
        // check if the server is messing with the protocol
        if (ctx.side == Side.CLIENT) {
          
            return null;
        }
        
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        
        if (player.ridingEntity instanceof EntityTameableDragon) {
            EntityTameableDragon dragon = (EntityTameableDragon) player.ridingEntity;
            dragon.setControlFlags(message.getFlags());
        }
        
        // receive only
        return null;
    }
}
