package fr.neocraft.main.Proxy.Serveur.Entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Serveur.Entity.ai.EntityAIFollowOwner;
import fr.neocraft.main.Proxy.Serveur.Stage.Pet.PetRegister;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityPet extends EntityTameable implements IMob {

	// Magma only
	public float squishAmount;
    public float squishFactor;
    public float prevSquishFactor;
    private int slimeJumpDelay;
    // Magma only
    
    
	private EntityPlayer owner;
	private String pseudo = "";
	private int lvl =0;
	private int curentxp=0;
	private int PetId = 0;
	private int count = 500;
	
	private static final int Index_Lvl = 22;
	private static final int Index_Exp = 23;
	private static final int Index_Id = 24;
	private static final int Index_Pseudo = 25;
	
	public int LastChange = 0;
	
	private EntityAIFollowOwner AI = new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F);
	
	public int getMustExpLvl(int lvl)
	{
		return lvl*lvl+ 1 ;
	}
	
	public EntityPet(World p_i1681_1_) {
		
		super(p_i1681_1_);
		this.setSize(0.65F, 1F);
		this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, AI);
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityLivingBase.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.setTamed(false);
		this.isImmuneToFire = true;

		this.slimeJumpDelay = this.rand.nextInt(20) + 10;
	}
	
	 // Magma only
	public int getSlimeSize()
    {
        return this.getLvl() < 25 ? 1 : 2;
    }
	
	 protected String getSlimeParticle()
	    {
	        return "slime";
	    }
	 
	 protected String getJumpSound()
	 {

	        return this.getId() == 3 ? "mob.slime.small" : null;
	 }
	   
	
	 
	 protected void updateEntityActionState()
	    {
		 if(this.getId() == 3)
		 {
			 EntityPlayer entitylivingbase = (EntityPlayer) this.getOwner();

		        
		        if (entitylivingbase == null)
		        {
		            return;
		        }


		       if (getDistanceSqToEntity(entitylivingbase) < 10)
		        {
		            return;
		        }
		        else if (getDistanceSqToEntity(entitylivingbase) > 150)
		        {
		        	if(this.worldObj.getBlock((int)entitylivingbase.posX,(int)entitylivingbase.posY-1, (int)entitylivingbase.posZ) != Blocks.air)
		        	{
		        		this.setPositionAndUpdate(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ);
		        	}
		        	return;
		        }

	        
	          this.faceEntity(entitylivingbase, 10.0F, 20.0F);
	        

	        if (this.onGround && this.slimeJumpDelay-- <= 0)
	        {
	            this.slimeJumpDelay = this.getJumpDelay();

	           
	                this.slimeJumpDelay /= 3;
	            

	            this.isJumping = true;

	         
	                this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
	           

	            this.moveStrafing = 1.0F - this.rand.nextFloat() * 2.0F;
	            this.moveForward = (float)(1 * this.getSlimeSize());
	        }
	        else
	        {
	            this.isJumping = false;

	            if (this.onGround)
	            {
	                this.moveStrafing = this.moveForward = 0.0F;
	            }
	        }
		 }
	    }

	    protected void alterSquishAmount()
	    {
	        this.squishAmount *= 0.6F;
	    }

	    /**
	     * Gets the amount of time the slime needs to wait between jumps.
	     */
	    protected int getJumpDelay()
	    {
	        return this.rand.nextInt(20) + 10;
	    }
	    
	   
	 // Magma only
	protected void entityInit()
    {
		super.entityInit();
		
			this.dataWatcher.addObject(Index_Lvl, Integer.valueOf(this.lvl));
			this.dataWatcher.addObject(Index_Exp, Integer.valueOf(this.curentxp));
			this.dataWatcher.addObject(Index_Id, Integer.valueOf(this.PetId));
			if(this.owner != null)
			{
				this.dataWatcher.addObject(Index_Pseudo, String.valueOf(owner.getCommandSenderName()));
			} else {
				this.dataWatcher.addObject(Index_Pseudo, String.valueOf(""));
			}

		
	
    }
	
	
	public String getPseudo() {
		return this.dataWatcher.getWatchableObjectString(Index_Pseudo);
	}
	
	public boolean getIsPlayerPet() {
		return this.isTamed();
	}
	
	
	public int getLvl() {
		return this.dataWatcher.getWatchableObjectInt(Index_Lvl);
	}
	
	
	public int getExp() {
		return this.dataWatcher.getWatchableObjectInt(Index_Exp);
	}
	
	
	public int getId() {
		return this.dataWatcher.getWatchableObjectInt(Index_Id);
	}
	
	
	public boolean IsChild()
	{
		if(getLvl() > 24)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.35000001192092896D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
    }
	

	protected boolean isAIEnabled()
    {
        return true;
    }
	
	

	 public void onUpdate()
	 {
		 if(getId() == 3 ) // slime
		 {
			 this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
		        this.prevSquishFactor = this.squishFactor;
		        boolean flag = this.onGround;
		        super.onUpdate();
		        int i;

		        if (this.onGround && !flag)
		        {
		            i = this.getSlimeSize();

		            for (int j = 0; j < i * 8; ++j)
		            {
		                float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
		                float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
		                float f2 = MathHelper.sin(f) * (float)i * 0.5F * f1;
		                float f3 = MathHelper.cos(f) * (float)i * 0.5F * f1;
		                this.worldObj.spawnParticle(this.getSlimeParticle(), this.posX + (double)f2, this.boundingBox.minY, this.posZ + (double)f3, 0.0D, 0.0D, 0.0D);
		            }

		           
		                this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
		            

		            this.squishAmount = -0.5F;
		        }
		        else if (!this.onGround && flag)
		        {
		            this.squishAmount = 1.0F;
		        }

		        this.alterSquishAmount();
		 }
		 else {
			 super.onUpdate();
		 }
		 if(LastChange > 0)
		 {
			 LastChange--;
		 }
		 if(!this.worldObj.isRemote)
		 {
			 if(this.isTamed())
			 {
				 if(this.owner == null)
				 {
					 this.setDead();
				 }
				 else if(this.owner.isDead)
				 {
					this.setDead();
				 } else if(this.owner.worldObj.provider.dimensionId != this.worldObj.provider.dimensionId)
			    {
					 PlayerStats.get(this.owner).updatePet(this.owner, true);
			    }
			 }
			 else {
				 this.setDead();
			 }
			
		 }
	 }
	

	 
	 public boolean interact(EntityPlayer player) {
	    	ItemStack playerItem = player.inventory.getCurrentItem();
	    	LastChange = 60;
	    	if (playerItem != null && playerItem.getItem() == Items.spawn_egg) {
	            return super.interact(player);
	        } else if(playerItem != null) {
	        	if(this.isTamed() && PetRegister.getPetFoodsSever(this.PetId).isFood(playerItem.getItem()) && lvl < 50)
	        	{
	        		int am = PetRegister.getPetFoodsSever(this.PetId).getFoodAmount(playerItem.getItem());
		        	if(player.isSneaking())
		        	{
		        		int count = playerItem.stackSize;
		        		int count2 = 0;
		        		for(int i = 0; i <= count; i++)
		        		{
		        			curentxp += am;
		        			count2++;
			        		if(getMustExpLvl(lvl) <= curentxp)
			        		{
			        			lvl++;
			        			curentxp=0;
			        		}
		        		}
		        		player.inventory.getCurrentItem().splitStack(count2);
		        	} else {
		        		player.inventory.getCurrentItem().splitStack(1);
		        		curentxp += am;
		        		if(getMustExpLvl(lvl) <= curentxp)
		        		{
		        			lvl++;
		        			curentxp=0;
		        		}
		        		
		        	}
		        	if(!this.worldObj.isRemote)
	        		{
	        			PlayerStats stat = PlayerStats.get(player);
	        			stat.updatePet(this.owner.getCommandSenderName(), curentxp, lvl);
	        			stat.updatePet(this.owner, false);
	        			this.dataWatcher.updateObject(Index_Exp, curentxp);
	        			this.dataWatcher.updateObject(Index_Lvl, lvl);

	        		}else {
	        			this.curentxp = this.getExp();
	        			this.lvl = this.getLvl();
	        		}
		        	return true;
	        	} else if(playerItem.getItem() == Items.stick ) {
	        		if(!this.worldObj.isRemote)
	        		{
	        			if(PlayerStats.get(player).AdminStage)
	        			{
	        				if(this.isTamed())
	        				{
	        					PlayerStats stat = PlayerStats.get(this.owner);
	        					stat.PetId = -1;
	        					stat.updatePet(this.owner, true);
	        					
	        				}else {
	        					if(count != 500)
	        					{
	        						count++;
	        					}
	        					else {
	        						this.setDead();
	        					}
	        				}
	        			}
	        		}
	        	}
	        }
	       
	    	return false;
	 }
	


	@Override
	public EntityLivingBase getOwner() {
		// TODO Auto-generated method stub
		return this.owner;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@SideOnly(Side.CLIENT)
    public boolean canRenderOnFire()
    {
        return false;
    }
	
	@Override
	public boolean doesEntityNotTriggerPressurePlate()
    {
        return true;
    }
	
	@Override
	public boolean canAttackWithItem()
    {
        return false;
    }
	
	@Override
	public boolean hitByEntity(Entity p_85031_1_)
    {
        return false;
    }
	
	@Override
	public void writeToNBT(NBTTagCompound c)
    {
		super.writeToNBT(c);
		this.dataWatcher.updateObject(Index_Lvl, Integer.valueOf(this.lvl));
		this.dataWatcher.updateObject(Index_Exp, Integer.valueOf(this.curentxp));
		this.dataWatcher.updateObject(Index_Id, Integer.valueOf(this.PetId));
	
		if(this.owner != null)
		{
			this.dataWatcher.updateObject(Index_Pseudo, String.valueOf(owner.getCommandSenderName()));
		} else {
			this.dataWatcher.updateObject(Index_Pseudo, String.valueOf(""));
		}
    }
	
	@Override
	public void readFromNBT(NBTTagCompound c)
    {
		super.readFromNBT(c);
	
    }
	
	@Override
    protected boolean canDespawn() {
        return false;
    }
	
	 @Override
	    protected float getSoundVolume() {
	        return 0.5F;
	    }

	@Override
	public String func_152113_b() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPet(EntityPlayer p, int petId2, int petLvl, int petExp) {
		this.owner = p;
		this.setTamed(true);
		
		this.pseudo = p.getCommandSenderName();
		this.PetId = petId2;

		this.lvl = petLvl;
		this.curentxp = petExp;
		this.dataWatcher.updateObject(Index_Exp, Integer.valueOf(petExp));
		this.dataWatcher.updateObject(Index_Id, Integer.valueOf(petId2));
		this.dataWatcher.updateObject(Index_Lvl, Integer.valueOf(petLvl));
		this.dataWatcher.updateObject(Index_Pseudo, String.valueOf(this.pseudo));
	}
	    

}
