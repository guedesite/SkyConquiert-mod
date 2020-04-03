package fr.neocraft.main.Proxy.Serveur.TileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFarmLand extends TileEntity
{

	    private int meta = 0;
	    
	    public int getmeta()
	    {
	    	return meta;
	    }
	    public void setmeta(int i)
	    {
	    	this.meta = i;
	    }
	    @Override
	    public void readFromNBT(NBTTagCompound c)
	    {
	        super.readFromNBT(c);
	        this.meta = c.getInteger("meta");
	        
	    }
	    
	    @Override
	    public void writeToNBT(NBTTagCompound c)
	    {
	        super.writeToNBT(c);
	        c.setInteger("meta", this.meta);
	    }

	    
	    @Override
	    public Packet getDescriptionPacket()
	    {
	        NBTTagCompound compound = new NBTTagCompound();
	        this.writeToNBT(compound);
	        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, compound);
	    }
	    
	    @Override
	    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	    {
	        this.readFromNBT(pkt.func_148857_g());
	        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
	    }
	
}