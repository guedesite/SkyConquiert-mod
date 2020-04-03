package fr.neocraft.main.Proxy.Serveur.Entity;

import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;


public class EntityShopperPnj extends EntityAnimal
{
	private boolean isShopper = false;
	private double x=0,y=0,z=0;
	
    public EntityShopperPnj(World p_i1738_1_)
    {
    	super(p_i1738_1_);
    	 this.setSize(1F, 2F);
	        this.getNavigator().setAvoidsWater(true);
	        this.tasks.addTask(0, new EntityAIWander(this, 1.0D));
	        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
	        this.tasks.addTask(2, new EntityAILookIdle(this));
    }
    public EntityShopperPnj(World p_i1738_1_, ItemStack[] stack)
    {
    	super(p_i1738_1_);
    	 this.setSize(1F, 2F);
	        this.getNavigator().setAvoidsWater(true);
	        this.tasks.addTask(0, new EntityAIWander(this, 1.0D));
	        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
	        this.tasks.addTask(2, new EntityAILookIdle(this));
    }
    public void writeEntityToNBT(NBTTagCompound c)
    {
        super.writeEntityToNBT(c);
        c.setBoolean("isShopper", isShopper);
        if(isShopper)
        {
        	c.setDouble("neox", x);
        	c.setDouble("neoy", y);
        	c.setDouble("neoz", z);
        }
      
    }
   
    public void onLivingUpdate()
    { 
    	super.onLivingUpdate();
    	if(isShopper)
    	{
    		this.setPosition(x, y, z);
    	}
    	
    }
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */

    public void readEntityFromNBT(NBTTagCompound c)
    {
        super.readEntityFromNBT(c);
        isShopper = c.getBoolean("isShopper");
        if(isShopper)
        {
        	x = c.getDouble("neox");
        	y = c.getDouble("neoy");
        	z = c.getDouble("neoz");
        }
      
    }
    public void setIsShopper(boolean i)
    {
    	this.isShopper = i;
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
    }

    protected String getLivingSound()
    {
        return "mob.villager.haggle";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */

    protected String getHurtSound()
    {
        return "mob.villager.hit";
    }

    /**
     * Returns the sound this mob makes on death.
     */

    protected String getDeathSound()
    {
        return "mob.villager.death";
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
    	else if(isShopper & !player.worldObj.isRemote)
    	{
    		player.openGui(main.instance, 6, this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ);
    		PlayerStats.get(player).QuestEvent("interact_pnj", player, 1);
    	}
		return true;

    	
    }

	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		return null;
	}
	protected float getSoundVolume()
    {
        return 0.4F;
    }

}