package fr.neocraft.main.Item;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Init.BlockMod;
import fr.neocraft.main.Init.ItemMod;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemSpawner extends Item {
	
	private String[] mob = {"Zombie", "Blaze", "Skeleton", "Spider"};
	private Random rand = new Random();
	
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
	{
		System.out.println("1");
		MovingObjectPosition mop = Minecraft.getMinecraft().renderViewEntity.rayTrace(200, 1.0F);
		if(mop != null)
		{
			int blockHitSide = mop.sideHit;
			Block blockLookingAt = p_77659_2_.getBlock(mop.blockX, mop.blockY, mop.blockZ) ;
			System.out.println("3");
			if(blockLookingAt == Blocks.mob_spawner)
			{
				String ranmod = mob[rand.nextInt(3)];
				System.out.println("4 " + mop.blockX+" "+mop.blockY+" "+mop.blockZ+" "+ranmod);

				
				TileEntityMobSpawner tileEntityMobSpawner = new TileEntityMobSpawner();//(TileEntityMobSpawner) world.getTileEntity(blockX,  blockY,  blockZ);
				   tileEntityMobSpawner.xCoord = mop.blockX;
				   tileEntityMobSpawner.yCoord = mop.blockY;
				   tileEntityMobSpawner.zCoord = mop.blockZ;
				   tileEntityMobSpawner.setWorldObj(p_77659_2_);
				   tileEntityMobSpawner.func_145881_a().setEntityName(mob[rand.nextInt(3)]);
				   tileEntityMobSpawner.func_145881_a().func_98281_h();
				   tileEntityMobSpawner.func_145881_a().spawnDelay = 3;
				   //tileEntityMobSpawner.func_145881_a().updateSpawner();
				   //tileEntityMobSpawner.updateEntity();
				   //world.setBlock(blockX, blockY,blockZ,Blocks.mob_spawner);
				   tileEntityMobSpawner.markDirty();
				   p_77659_2_.setTileEntity(mop.blockX, mop.blockY, mop.blockZ,tileEntityMobSpawner);
				   p_77659_2_.markBlockForUpdate(mop.blockX, mop.blockY, mop.blockZ);
				   
				p_77659_1_.damageItem(1, p_77659_3_);
				if(getDamage(p_77659_1_) == 0)
				{
					p_77659_3_.inventory.setInventorySlotContents(p_77659_3_.inventory.currentItem, (ItemStack)null);
				}
			}
		}
		else
		{
			System.out.println("2");
		}
		return p_77659_1_;
	}
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack p_77636_1_)
    {
        return true;
    }
  
  	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_)
    {
        super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        
        p_77624_3_.add(EnumChatFormatting.BOLD + "Click droit sur un spawner.");
    }
}
