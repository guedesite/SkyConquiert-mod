package fr.neocraft.main.Blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Serveur.player.teleport;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAntiFallSpawn extends Block
{

    public BlockAntiFallSpawn(Material p_i45394_1_, int level)
    {
        super(p_i45394_1_);
        this.setHarvestLevel("pickaxe", level);
    }
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    @Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
        public float getAmbientOcclusionLightValue()
        {
            return 1.0F;
        }
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }
    @Override
    public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_)
    {
    	if(!p_149670_1_.isRemote && !(p_149670_5_ instanceof EntityItem))
    	{
    		//5000 81 5000
    		 p_149670_5_.fallDistance = 0;
    		 if(p_149670_5_ instanceof EntityPlayer)
    		 {
    			 MinecraftServer.getServer().getCommandManager().executeCommand(MinecraftServer.getServer(), "forcespawn "+p_149670_5_.getCommandSenderName());
    		 }
    		 else
    		 {
    			 teleport.entity(0, p_149670_5_,5000, 81, 5000, 0);
    		 }
    		p_149670_5_.fallDistance = 0;
    	}
    }
}
