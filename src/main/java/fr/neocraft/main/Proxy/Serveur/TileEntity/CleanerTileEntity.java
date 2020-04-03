package fr.neocraft.main.Proxy.Serveur.TileEntity;

import fr.neocraft.main.main;
import fr.neocraft.main.Init.BlockMod;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import oortcloud.hungryanimals.blocks.ModBlocks;

public class CleanerTileEntity extends TileEntity {
	public int PosX = -50;
	public int PosZ = -50;
	public int PosY = -50;
	
	public void readFromNBT(NBTTagCompound c)
    {
        super.readFromNBT(c);
    }

    public void writeToNBT(NBTTagCompound c)
    {
        super.writeToNBT(c);
    }
    
    
    @Override
    public void updateEntity() {
    	updateClean();
    	updateClean();
    	updateClean();
    }
    
    public void updateClean() {
    	int nb = 0;
    	if(this.worldObj.getBlock(this.xCoord+PosX, this.yCoord+PosY, this.zCoord+PosZ) == ModBlocks.blockExcreta) {
    		this.worldObj.setBlockToAir(this.xCoord+PosX, this.yCoord+PosY, this.zCoord+PosZ);
    		nb ++;
    	}
    	if(nb != 0 && this.worldObj.getBlock(this.xCoord, this.yCoord-1, this.zCoord) == Blocks.hopper)
    	{
    		TileEntityHopper tile = (TileEntityHopper) this.worldObj.getTileEntity(this.xCoord, this.yCoord-1, this.zCoord);
    		 for (int i = 0; i < 5 && 0 < nb ; ++i)
    	     {
    			 if(tile.getStackInSlot(i) != null )
    			 {	
    				 if(tile.getStackInSlot(i).getItem() == Items.dye && tile.getStackInSlot(i).getItemDamage() == 15)
    				 {
    					 int ii =64 - tile.getStackInSlot(i).stackSize;
    					 if( 0 < ii )
    					 {
    						 if(ii < nb )
    						 {
    							 nb -= ii;
    							 tile.getStackInSlot(i).stackSize +=ii;
    						 } else {
    							
    							 tile.getStackInSlot(i).stackSize +=nb;
    							 nb = 0;
    						 }
    					 }
    				 }
    			 } else {
    				 if(64 < nb )
    				 {
    					 nb -= 64;
    					 tile.setInventorySlotContents(i, new ItemStack(Items.dye, 64, 15));
    				 } else {
    					 tile.setInventorySlotContents(i, new ItemStack(Items.dye, nb, 15));
    					 nb = 0;
    				 }
    			 }
    	     }
    		 
    		// this.worldObj.notifyBlockOfNeighborChange(this.xCoord, this.yCoord-1, this.zCoord, BlockMod.BlockCleaner);
    	}
    	PosX++;
    	if(PosX == 51)
    	{
    		PosX = -50;
    		PosY++;
    		if(PosY == 51)
    		{
    			PosY = -50;
    			PosZ++;
    			if(PosZ == 51)
    			{
    				PosZ = -50;
    			}
    		}
    	}
    	if(main.getbdd().IsDebug)
    	{
    		System.out.println(PosX+"-"+PosY+"-"+PosZ);
    	}
    }
    
}
