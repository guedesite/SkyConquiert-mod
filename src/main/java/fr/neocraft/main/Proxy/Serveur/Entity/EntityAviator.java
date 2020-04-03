package fr.neocraft.main.Proxy.Serveur.Entity;

import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkClient;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ClientGuiAviator;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityAviator extends EntityAnimal
{
	private boolean isAviator = false;
	private double x=0,y=0,z=0;
	private int t = 0;
	
    public EntityAviator(World p_i1738_1_)
    {
    	super(p_i1738_1_);
    	 this.setSize(1F, 2F);
	        this.getNavigator().setAvoidsWater(true);
	        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
	        this.tasks.addTask(2, new EntityAILookIdle(this));
    }
 
    public void writeEntityToNBT(NBTTagCompound c)
    {
        super.writeEntityToNBT(c);
        c.setBoolean("isAviator", this.isAviator);
        if(this.isAviator)
        {
        	c.setDouble("neox", this.x);
        	c.setDouble("neoy", this.y);
        	c.setDouble("neoz", this.z);
        	c.setInteger("temps", this.t);
        }
      
    }
   
    public void onLivingUpdate()
    { 
    	super.onLivingUpdate();
    	if(isAviator)
    	{
    		if(main.getHourlyQuest() != 2)
    		{
    			if(this.t < 50)
    			{
    				this.t++;
	    			double d0;
	    	        double d1;
	    	        double d2;
	    	        double d3;
	    	        double d4;
	    	        double d5;
	    	        for(int i = 0; i <= 20; i++)
	    	        {
	    	        	 d0 = (double)((float)this.x + this.worldObj.rand.nextFloat());
	    	             d1 = (double)((float)this.y + 0 + this.worldObj.rand.nextFloat());
	    	             d2 = (double)((float)this.z + this.worldObj.rand.nextFloat());
	    	             d3 = 0.0D;
	    	             d4 = 0.0D;
	    	             d5 = 0.0D;
	    	             this.worldObj.spawnParticle("smoke", d0, d1, d2, d3, d4, d5);
	    	        }
	    	        for(int i = 0; i <= 20; i++)
	    	        {
	    	        	 d0 = (double)((float)this.x + this.worldObj.rand.nextFloat());
	    	             d1 = (double)((float)this.y + 1 + this.worldObj.rand.nextFloat());
	    	             d2 = (double)((float)this.z + this.worldObj.rand.nextFloat());
	    	             d3 = 0.0D;
	    	             d4 = 0.0D;
	    	             d5 = 0.0D;
	    	             this.worldObj.spawnParticle("smoke", d0, d1, d2, d3, d4, d5);
	    	        }
    			}
    			else
    			{
	    			this.setPosition(x, y+200, z);
	    			this.t = 0;
	    			this.setDead();
	    			this.setPosition(x, y+200, z);
    			}
    		}
    		this.setPosition(x, y, z);
    	}
    	
    }
   
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */

    public void readEntityFromNBT(NBTTagCompound c)
    {
        super.readEntityFromNBT(c);
        this.isAviator = c.getBoolean("isAviator");
        if(this.isAviator)
        {
        	this.x = c.getDouble("neox");
        	this.y = c.getDouble("neoy");
        	this.z = c.getDouble("neoz");
        	this.t = c.getInteger("temps");
        }
      
    }
    public void setisAviator(boolean i)
    {
    	this.isAviator = i;
    }
    public void setPos(double[] i)
    {
    	x = i[0];
    	y = i[1];
    	z = i[2];
    }

    protected Item getDropItem()
    {
        return null;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
    }

    protected boolean isAIEnabled()
    {
        return true;
    }

    public boolean interact(EntityPlayer player) {
    	ItemStack playerItem = player.inventory.getCurrentItem();
    	if (playerItem != null && playerItem.getItem() == Items.spawn_egg) {
            return super.interact(player);
        }
    	else if(isAviator & !player.worldObj.isRemote )
    	{
    		PlayerStats stat =PlayerStats.get(player);
    		if(!stat.IsDoingHorraire && !stat.IsFinishHorraire)
    		{
    			int i = 0;
    			if(2 < stat.lvlGuiDrag[0] )
    			{
    				i++;
    			}
    			main.getNetWorkClient().sendTo(new NetWorkClient(new ClientGuiAviator(i)), (EntityPlayerMP) player);
    		
    		}
    	}
		return true;

    	
    }

	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		return null;
	}
	protected float getSoundVolume()
    {
        return 0F;
    }
}
