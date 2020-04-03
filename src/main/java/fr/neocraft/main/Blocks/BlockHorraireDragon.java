package fr.neocraft.main.Blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class BlockHorraireDragon extends Block
{

    public BlockHorraireDragon()
    {
        super(Material.air);
    }

    
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }
    public boolean isOpaqueCube()
    {
        return false;
    }
    @SideOnly(Side.CLIENT)
        public float getAmbientOcclusionLightValue()
        {
            return 1.0F;
        }
 
    public int quantityDropped(Random p_149745_1_)
    {
        return 0;
    }

    public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity e)
    {
    	
    	/*if(!w.isRemote && e instanceof EntityPlayer)
        {
    		if(main.getHourlyQuest() != 2) { return; }
        	Chunk c = w.getChunkFromBlockCoords(x, z);
        	PlayerStats stat = PlayerStats.get((EntityPlayer)e);
        	if(!main.getHorraire().isPlayerDo(((EntityPlayer)e).getCommandSenderName()) && !Exist(c.xPosition, stat) )
        	{
        		if(stat.DragHorX[14] == -1)
        		{
	        		for(int i = 0; i < 15; i++)
	        		{
	        			if(stat.DragHorX[i] != -1) {
	        				stat.DragHorX[i] = c.xPosition;
	        				stat.DragHorZ[i] = c.zPosition;
	        				main.getNetWorkQuest().sendTo(new NetWorkManagerClientHorraire("dataHorraire"), (EntityPlayerMP)e);
	        				return;
	        			}
	        		}
        		} else {
        			main.getNetWorkQuest().sendTo(new NetWorkManagerClientHorraire("dataHorraire"), (EntityPlayerMP)e);
        			main.getNetWorkQuest().sendTo(new NetWorkManagerClientHorraire("dataEndHorraire"), (EntityPlayerMP)e);
        			main.getHorraire().addPlayerDo(((EntityPlayer)e).getCommandSenderName());
        			QuestManager.Quest.get(2).GetRecompense((EntityPlayer)e);

        		}
        		
        	
        	}
        	
        } */
    }
    public boolean Exist(int x, PlayerStats stat)
    {
    	for(int o:stat.DragHorX)
    	{
    		if(x == o)
    		{ return true;}
    	}
    	return false;
    }
    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
}