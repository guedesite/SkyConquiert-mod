package fr.neocraft.main.Item;


import java.awt.Event;

import fr.neocraft.main.Init.BlockMod;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class HammerData extends ItemPickaxe
{

    public HammerData(ToolMaterial p_i45347_1_)
    {
        super(p_i45347_1_);
        // TODO Auto-generated constructor stub
    }
    
    public boolean cando(World world, Block block, int x, int y, int z)
    {
    	Chunk c = world.getChunkFromBlockCoords(x, z);
    	Stage s = RegisterStage.getStageAtXY(c.xPosition, c.zPosition, world);
    	if(s != null && s.getIdStage() == 5)
    	{
    		return false;
    	}
    	if(block.getHarvestLevel(world.getBlockMetadata(x, y, z)) <= this.toolMaterial.getHarvestLevel() & world.getBlock(x, y, z).isNormalCube() & !world.getBlock(x, y, z).getMaterial().isLiquid() & block.getHarvestTool(world.getBlockMetadata(x, y, z)) == "pickaxe" & world.getBlock(x, y, z).getBlockHardness(world, x, y, z) >= 0.0F & Blocks.wool != block)
    	{
    		return true;
    	}
    	return false;
    }
    
    
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living ) {
        if(living instanceof EntityPlayer && !world.isRemote) 
        {
        	EntityPlayer player = (EntityPlayer)living;
        	if(cando(world, block, x, y, z))
        	{
        		switch(determineOrientation(world, x, y, z, living))
        		{
        			case 0:
        				for(int x1 = -1; x1 < 2; x1++) 
        				{
        					for(int z1 = -1; z1 < 2; z1++) 
        					{
        						if(cando(world, block, x + x1, y, z + z1))
        						{
        								world.getBlock(x + x1, y, z + z1).harvestBlock(world, (EntityPlayer)living, x + x1, y, z + z1,     
                                           world.getBlockMetadata(x + x1, y, z + z1));
        								world.setBlockToAir(x + x1, y, z + z1);
       							}
        					}
        				}
        				break;
        			case 1:
        				for(int y1 = -1; y1 < 2; y1++) 
        				{
       						for(int z1 = -1; z1 < 2; z1++) 
       						{
       							if(cando(world, block, x, y + y1, z + z1))
        						{
        							world.getBlock(x, y + y1, z + z1).harvestBlock(world, (EntityPlayer)living, x, y + y1, z + z1,
                                     world.getBlockMetadata(x, y + y1, z + z1));
        							world.setBlockToAir(x, y + y1, z + z1);
        						}
        					}
        				}
                       	break;
        			case 2:
       					for(int x1 = -1; x1 < 2; x1++)
        				{
        					for(int y1 = -1; y1 < 2; y1++) 
        					{
        						if(cando(world, block, x + x1, y + y1, z))
        						{
        							world.getBlock(x + x1, y + y1, z).harvestBlock(world, (EntityPlayer)living, x + x1, y + y1, z, world.getBlockMetadata(x + x1, y + y1, z));
        							world.setBlockToAir(x + x1, y + y1, z);
        						}
       						}
       					}
        				break;
        			}
        		}
        	}
        	return super.onBlockDestroyed(stack, world, block, x, y, z, living);
 }

 public int determineOrientation(World world, int x, int y, int z, EntityLivingBase living) {
     if(MathHelper.abs((float)living.posX - x) < 2.0F && MathHelper.abs((float)living.posZ - z) < 2.0F) {
         double d0 = living.posY + 1.82D - (double)living.yOffset;

         if(d0 - y > 2.0D || y - d0 > 0.0D) {
             return 0;
         }
     }
     float rotation = MathHelper.abs(living.rotationYaw);
     return (rotation > 45F && rotation < 135F) || (rotation > 225F && rotation < 315F) ? 1 : 2;
     }
    
   
}