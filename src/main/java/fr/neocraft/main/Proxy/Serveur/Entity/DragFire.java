package fr.neocraft.main.Proxy.Serveur.Entity;


import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import info.ata4.minecraft.dragon.server.entity.EntityTameableDragon;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class DragFire extends Entity{

    public int Age = 0, MaxAge = 1;
    public String keke = "";
    private int nope = 0;
    public DragFire(World p_i1215_1_, double p_i1215_7_, double p_i1215_8_, double p_i1215_9_, String s)
    {
    	super(p_i1215_1_);
    
        this.motionX = this.motionX * 0.009999999776482582D + p_i1215_7_;
        this.motionY = p_i1215_8_;
        this.motionZ = this.motionZ * 0.009999999776482582D + p_i1215_9_;
        this.MaxAge = (int)(25.0D / (Math.random() * 0.8D + 0.2D));
        this.noClip = false;
        this.keke=s;
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    public DragFire(World p_i1586_1_)
    {
        super(p_i1586_1_);
        this.setSize(0.25F, 0.25F);
        this.yOffset = this.height / 2.0F;
    }

    protected void entityInit() {}

    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float p_70070_1_)
    {
        float f1 = ((float)this.Age + p_70070_1_) / (float)this.MaxAge;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }

        int i = super.getBrightnessForRender(p_70070_1_);
        short short1 = 240;
        int j = i >> 16 & 255;
        return short1 | j << 16;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float p_70013_1_)
    {
        return 1.0F;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if(!this.worldObj.isRemote)
        {
        
	        AxisAlignedBB box = boundingBox.expand(0.4D, 0.4D, 0.4D);
	
	        if(nope == 15)
	        {
	
		        List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, box);
		
		        if (list != null && !list.isEmpty())
		        {
		            for (int k = 0; k < list.size(); ++k)
		            {
		                Entity entity = (Entity)list.get(k);
		                if(!(entity instanceof DragFire))
		                {
		                	boolean flag = true;
		                	if(entity instanceof EntityPlayer)
		                	{
		                		if(this.worldObj.provider.dimensionId != 201)
		                		{
		                			flag = false;
		                		}
		                	}
		                	else if(entity instanceof EntityTameableDragon)
		                	{
		                		if(((EntityTameableDragon)entity).getOwner() != null && ((EntityTameableDragon)entity).getOwner().getCommandSenderName().equals(this.keke))
		                		{
		                			flag = false;
		                		}
		                	}
		                	if(flag)
		                	{
			                	entity.attackEntityFrom(DamageSource.lava, 1F);
			                	entity.setFire(20);
			                	this.setDead();
		                	}
		                }
		            }
		        }
	        } else {
	        	nope++;
	        }
	        
        }
	        if (this.Age++ >= this.MaxAge)
	        {
	            this.setDead();
	        }
	      float f = (float)this.Age / (float)this.MaxAge;

        if (this.rand.nextFloat() > f)
        {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ);
        }
       
        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }else {
        	 this.motionX *= 0.9999900128746033D;
 	        this.motionY *= 0.9999900128746033D;
 	       this.motionY -= 0.01D;
        }
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        
    }

    /**
     * Returns if this entity is in water and will end up adding the waters velocity to the entity
     */
    public boolean handleWaterMovement()
    {
        return this.worldObj.handleMaterialAcceleration(this.boundingBox, Material.water, this);
    }

    /**
     * Will deal the specified amount of damage to the entity if the entity isn't immune to fire damage. Args:
     * amountDamage
     */
    protected void dealFireDamage(int p_70081_1_)
    {
        this.attackEntityFrom(DamageSource.inFire, (float)p_70081_1_);
    }

 

    public boolean canAttackWithItem()
    {
        return false;
    }

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
	}

	



}
