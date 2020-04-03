package fr.neocraft.main.Proxy.Serveur.TileEntity;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.Init.BlockMod;
import fr.neocraft.main.Init.ItemMod;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkClient;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ClientTotemStat;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.chunk.Chunk;

public class DivinTotemTileEntity extends TileEntity{

	private float ToReach = 0.00400F;
	public float rot = 0.0000F;
	
	private int res = 0;
	
	private boolean isActive = false;
	
	public CheckAllTrue check = new CheckAllTrue();
	
	public int cube = 0;
	
	public void readFromNBT(NBTTagCompound c)
    {
       ToReach = c.getFloat("ToReach");
       rot = c.getFloat("rot");
       res = c.getInteger("res");
       isActive = c.getBoolean("isActive");
       cube = c.getInteger("cube");
       check.diamond = c.getBoolean("check.diamond");
       check.gold = c.getBoolean("check.gold");
       check.iron = c.getBoolean("check.iron");
       check.mythril = c.getBoolean("check.mythril");
       check.onyx = c.getBoolean("check.onyx");
       check.silithium = c.getBoolean("check.silithium");
       check.titane = c.getBoolean("check.titane");
       check.onyxR = c.getBoolean("check.onyxR");
       check.neodium = c.getBoolean("check.neodium");
       
    }

    public void writeToNBT(NBTTagCompound c)
    {
    	c.setFloat("ToReach", ToReach);
    	c.setFloat("rot", rot);
    	c.setInteger("res", res);
    	c.setBoolean("isActive", isActive);
    	c.setInteger("cube",cube);
    	c.setBoolean("check.diamond", check.diamond);
    	c.setBoolean("check.gold", check.gold);
    	c.setBoolean("check.iron", check.iron);
    	c.setBoolean("check.mythril", check.mythril);
    	c.setBoolean("check.onyx", check.onyx);
    	c.setBoolean("check.silithium", check.silithium);
    	c.setBoolean("check.titane", check.titane);
    	c.setBoolean("check.onyxR", check.onyxR);
    	c.setBoolean("check.neodium", check.neodium);
    	
    	
    }
	
	 public void updateEntity() {
		 
		 
		 cube ++;
		 
		 
		 if(cube < 500)
		 {
		
			 
			 
		 } else {
			 
			 if(rot  < ToReach)
			 {
				 rot += 0.0005F;
			 }
			 else if(rot  > ToReach)
			 {
				 rot -= 0.0005F;
			 }
			 
			 if(!this.worldObj.isRemote)
			 {
				 if(!isActive)
				 {
					 
					 if(!check.isAllTrue())
					 {
						 
						 AxisAlignedBB box = BlockMod.BlockDivinTotem.getCollisionBoundingBoxFromPool(this.worldObj, this.xCoord, this.yCoord, this.zCoord).expand(0.2D, 1D, 0.2D);
					        List list = this.worldObj.getEntitiesWithinAABB(EntityItem.class, box);
					
					        boolean flag = false;
					        
					        if (list != null && !list.isEmpty())
					        {
					            for (int k = 0; k < list.size(); ++k)
					            {
					                Entity entity = (Entity)list.get(k);
					                if(entity instanceof EntityItem)
					                {
					                
					                	EntityItem ei = (EntityItem) entity;
										if(ei.getEntityItem().getItem() == Items.iron_ingot && !check.iron)
										{
											flag = true;
											ei.getEntityItem().splitStack(1);
											 check.iron = true;
										} else if(ei.getEntityItem().getItem() == Items.gold_ingot &&  !check.gold )
										{
											flag = true;
											ei.getEntityItem().splitStack(1);
											 check.gold = true;
										}else if(ei.getEntityItem().getItem() == Items.diamond && !check.diamond)
										{
											flag = true;
											ei.getEntityItem().splitStack(1);
											 check.diamond = true;
										}else if(ei.getEntityItem().getItem() == ItemMod.Mythrile_Ingot && !check.mythril)
										{
											flag = true;
											ei.getEntityItem().splitStack(1);
											 check.mythril = true;
										}else if(ei.getEntityItem().getItem() == ItemMod.Titane_Ingot && !check.titane)
										{
											flag = true;
											ei.getEntityItem().splitStack(1);
											 check.titane = true;
										}else if(ei.getEntityItem().getItem() == ItemMod.Onyx_Ingot && !check.onyx)
										{
											flag = true;
											ei.getEntityItem().splitStack(1);
											 check.onyx = true;
										}else if(ei.getEntityItem().getItem() == ItemMod.Silithium_Ingot && !check.silithium)
										{
											flag = true;
											ei.getEntityItem().splitStack(1);
											 check.silithium = true;
										}else if(ei.getEntityItem().getItem() == ItemMod.NeoDium_Ingot && !check.neodium)
										{
											flag = true;
											ei.getEntityItem().splitStack(1);
											 check.neodium = true;
										}else if(ei.getEntityItem().getItem() == ItemMod.OnyxR_Ingot && !check.onyxR)
										{
											flag = true;
											ei.getEntityItem().splitStack(1);
											 check.onyxR = true;
										}
					                }
					            }
					        }
						 
						
							if(flag)
							{
								this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.xCoord, this.yCoord+1.2, this.zCoord));
							}
					 }		
					
					 else
					{
						
						
							 check.iron =false;
							 check.gold = false;
							 check.diamond = false;
							 check.mythril = false;
							 check.titane = false;
							 check.onyx = false;
							 check.silithium = false;
							 check.onyxR = false;
							 check.neodium = false;
							 ToReach = 0.5000F;
							 isActive = true;
							 res = 0;
							 this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 1,2);
							 sendDataClient(new ClientTotemStat(0, this.xCoord, this.yCoord, this.zCoord));
					}
				 }else {
					 res ++;
					 if(res > 2700)
					 {
						
						 if(res == 2770)
						 {
							 this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.xCoord, this.yCoord+1.2, this.zCoord));
						 } else if(res == 2800)
						 {
							 this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.xCoord, this.yCoord+1.2, this.zCoord));
						 }else if(res == 2830)
						 {
							 this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.xCoord, this.yCoord+1.2, this.zCoord));
						 } else if(res == 2860)
						 {
							 this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.xCoord, this.yCoord+1.2, this.zCoord));
						 }
						 else if(res == 2870)
						 {
							 this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.xCoord, this.yCoord+1.2, this.zCoord));
						 }
						 else if(res == 3300)
						 {
							 res = 0;
							 isActive = false;
							 ToReach = 0.004000F;
							 EntityItem item = new EntityItem(this.worldObj, this.xCoord +0.5, this.yCoord+2, this.zCoord+0.5, new ItemStack(ItemMod.Divin_Soulth,1 ));
							 item.delayBeforeCanPickup = 10;
							
							 this.worldObj.spawnEntityInWorld(item);
							 this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 0,2);
							 sendDataClient(new ClientTotemStat(1, this.xCoord, this.yCoord, this.zCoord));
							 
						        
						 }
					 }
				
					 
			
				 }
			 }
		 }
			
	 }
	 
	public void sendDataClient(ClientTotemStat T) {
	
		AxisAlignedBB box = BlockMod.BlockDivinTotem.getCollisionBoundingBoxFromPool(worldObj, this.xCoord, this.yCoord, this.zCoord).expand(24D, 24D, 24D);
        List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, box);

        if (list != null && !list.isEmpty())
        {
            for (int k = 0; k < list.size(); ++k)
            {
                Entity entity = (Entity)list.get(k);
                if(entity instanceof EntityPlayer)
                {
                	main.getNetWorkClient().sendTo(new NetWorkClient(T), (EntityPlayerMP) entity);
        
                }
            }
        }
		 
	}
	 
	 @SideOnly(Side.CLIENT)
	 public void startClient() {
		 ToReach = 0.50000F;
		 isActive = true;
	
	 }
	 @SideOnly(Side.CLIENT)
	 public void stopClient()
	 {
		 isActive = false;
		 ToReach = 0.004000F;
		 SoundManager.PlaySound(EnumSound.NeoEnderDrag.getSound());
	 }
	 public class CheckAllTrue {
		 
		 public boolean iron, gold, diamond, mythril, titane, onyx, silithium, neodium, onyxR; 
		 
		 public boolean isAllTrue() {
			
			 return iron && gold && diamond && mythril && titane && onyx && silithium && neodium && onyxR;
		 }
	 }
	
}
