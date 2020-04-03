package fr.neocraft.main.Blocks;

import java.util.Random;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBoost extends BlockBasic {
	
	@SideOnly(Side.CLIENT)
    private IIcon top;
	
	private int idNeo = 0;
	
	public BlockBoost(int id)
	{
	    super(Material.wood, 1);
	    this.idNeo = id;
	    this.setCreativeTab(main.neocraft_portal);
	    this.setHardness(2.0F);
        this.setStepSound(soundTypeWood);
	}
	 public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	    {
	        return null;
	    }
	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
	{
	    return true;
	}
	


	
		 public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_)
		    {
		           	if(p_149670_5_ instanceof EntityPlayer)
		           	{
		           		if (p_149670_5_.ridingEntity == null && p_149670_5_.riddenByEntity == null)
		                 {
			           			if(FMLCommonHandler.instance().getSide().equals(Side.CLIENT))
			           			{
			           				try 
			           				{
	    		    	       			EntityClientPlayerMP playerMP = (EntityClientPlayerMP) p_149670_5_; 
	    		    					ByteArrayDataOutput out = ByteStreams.newDataOutput();
	    		    					out.writeUTF("boostback");
	    		    					C17PacketCustomPayload packet = new C17PacketCustomPayload("NeoPlatePress", out.toByteArray());
	    		    					playerMP.sendQueue.addToSendQueue(packet);
			           				} catch(Exception e) { }
			           			}
		                 }
		           	}
		    }
	

	
	
	
			@SideOnly(Side.CLIENT)
			@Override
		    public void registerBlockIcons(IIconRegister iiconRegister)
		    {
		        this.blockIcon = iiconRegister.registerIcon("planks_spruce");
		        this.top = iiconRegister.registerIcon(neoreference.MOD_ID + ":boost_"+this.idNeo);
		    }
			@SideOnly(Side.CLIENT)
			@Override
		    public IIcon getIcon(int side, int metadata)
		    {
		        if(side == 1)
		        {
		            return this.top;
		        }
		        return this.blockIcon;

		    }
}