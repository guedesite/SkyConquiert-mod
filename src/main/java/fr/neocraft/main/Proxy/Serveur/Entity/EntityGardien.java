package fr.neocraft.main.Proxy.Serveur.Entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Serveur.Entity.ai.NeoAIAttack;
import fr.neocraft.main.Proxy.Serveur.Entity.ai.NeoAIWander;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import fr.neocraft.main.Proxy.Serveur.player.teleport;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class EntityGardien extends EntityMob
{
	private boolean isHostile = false;
	private boolean canMove = false;
	private int StageId = -1;
	private int attackTimer;
	private boolean HoldW = false;
	public boolean CanMove()
	{
		return this.canMove;
	}
	public boolean IsHostile()
	{
		return this.isHostile;
	}
	@Override
	protected boolean canDespawn()
    {
        return false;
    }
    public EntityGardien(World p_i1738_1_)
    {
        super(p_i1738_1_);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityMob.class, 1.0D, false));
        this.tasks.addTask(2, new NeoAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityMob.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new NeoAIAttack(this, EntityMob.class,0, true));
        this.setSize(1.2F, 1.8F);
       
    }
    
    public void onLivingUpdate()
    { 
    	super.onLivingUpdate();
    	this.HoldW = this.getHeldItem() != null;
    	
    	if (this.attackTimer > 0)
        {
            --this.attackTimer;
        }
    	if(this.posY < 0)
    	{
    		if(!this.worldObj.isRemote)
    		{
		    	Stage stage = RegisterStage.getStageWithId(this.getStageId());
				if(stage != null)
				{
		    		 this.fallDistance = 0;
		    		teleport.entity(this,stage.getX(), stage.getY(), stage.getZ(), 0);
		    		this.fallDistance = 0;
				}
    		}
    	}
   

        if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7D && this.rand.nextInt(5) == 0)
        {
            int i = MathHelper.floor_double(this.posX);
            int j = MathHelper.floor_double(this.posY - 0.20000000298023224D - (double)this.yOffset);
            int k = MathHelper.floor_double(this.posZ);
            Block block = this.worldObj.getBlock(i, j, k);

            if (block.getMaterial() != Material.air)
            {
                this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(block) + "_" + this.worldObj.getBlockMetadata(i, j, k), this.posX + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, this.boundingBox.minY + 0.1D, this.posZ + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, 4.0D * ((double)this.rand.nextFloat() - 0.5D), 0.5D, ((double)this.rand.nextFloat() - 0.5D) * 4.0D);
            }
        }
    	
    }
    public boolean interact(EntityPlayer player) {
    	ItemStack playerItem = player.inventory.getCurrentItem();
    	if (playerItem != null && playerItem.getItem() == Items.spawn_egg) {
            return super.interact(player);
        }
    	else if(playerItem == null)
    	{
    		if(player.worldObj.isRemote) { return true;}
    		Stage stage = RegisterStage.getStageWithId(this.StageId);
    		if(PlayerStats.get(player).AdminStage)
    		{
    			int b = 1;
    	    	if(stage != null)
    	    	{
    	    		b = RegisterStage.getChunkLongeurByStage(stage.getIdStage());
    	    	}
    			player.addChatMessage(new ChatComponentTranslation("id:"+this.StageId+" move:"+this.canMove+" hostile:"+this.isHostile +" distance:"+b));
    		}
    		else if(stage != null && stage.isIn(player.getCommandSenderName()))
    		{
	    		if(player.isSneaking())
	    		{
	    			if(this.canMove)
	    			{
	    				this.canMove = false;
	    				player.addChatMessage(new ChatComponentTranslation("neo.no.guardien.move"));
	    			
	    			}else {
	    				this.canMove = true;
	    				player.addChatMessage(new ChatComponentTranslation("neo.yes.guardien.move"));
	    				
	    			}
	    		} else {
	    			if(this.isHostile)
	    			{
	    				this.isHostile = false;
	    				player.addChatMessage(new ChatComponentTranslation("neo.no.guardien.hostile"));
	    			
	    			}else {
	    				this.isHostile = true;
	    				player.addChatMessage(new ChatComponentTranslation("neo.yes.guardien.hostile"));
	    			
	    			}
	    			
	    			return true;
	    		}
    		}
    	}
    	else {
    		if(player.worldObj.isRemote) { return true;}
    		Stage stage = RegisterStage.getStageWithId(this.StageId);
    		if(stage != null)
    		{
    			if(stage.isIn(player.getCommandSenderName()))
    			{
		    		if(player.isSneaking() && this.getHeldItem() != null)
		    		{
		    			this.entityDropItem(this.getHeldItem(), 1.0F);
		    			this.setCurrentItemOrArmor(0, null);
		    			this.HoldW = false;
		    		}
		    		else if(playerItem.getItem() instanceof ItemSword)
		    		{
		    			if(this.getHeldItem() != null)
		    			{
		    				this.entityDropItem(this.getHeldItem(), 1.0F);
		    			}
		    			this.HoldW = true;
		    			this.setCurrentItemOrArmor(0, playerItem);
		    			playerItem.splitStack(1);
		    		}
    			}
    		}
    	}
		return true;

    }
    protected String getHurtSound()
    {
        return "mob.irongolem.hit";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.irongolem.death";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound("mob.irongolem.walk", 1.0F, 1.0F);
    }
    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
        this.attackTimer = 10;
        this.worldObj.setEntityState(this, (byte)4);
        boolean flag = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(7 + this.rand.nextInt(15)));

        if (flag)
        {
            p_70652_1_.motionY += 0.4000000059604645D;
        }

        this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
        return flag;
    }

    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte p_70103_1_)
    {
        if (p_70103_1_ == 4)
        {
            this.attackTimer = 10;
            this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
        }
       
        else
        {
            super.handleHealthUpdate(p_70103_1_);
        }
    }


    @SideOnly(Side.CLIENT)
    public int getAttackTimer()
    {
        return this.attackTimer;
    }
    protected int getExperiencePoints(EntityPlayer p_70693_1_)
    {
        if (this.isChild())
        {
            this.experienceValue = (int)((float)this.experienceValue * 20F);
        }

        return super.getExperiencePoints(p_70693_1_);
    }
    public void writeEntityToNBT(NBTTagCompound c)
    {
        super.writeEntityToNBT(c);
        c.setInteger("StageId", this.StageId);
        c.setBoolean("isHostile", this.isHostile);
        c.setBoolean("canMove", this.canMove);
        c.setBoolean("HoldW", this.HoldW);
      
    }
    public void readEntityFromNBT(NBTTagCompound c)
    {
        super.readEntityFromNBT(c);
       this.StageId = c.getInteger("StageId");
       this.isHostile = c.getBoolean("isHostile");
       this.canMove = c.getBoolean("canMove");
       this.HoldW = c.getBoolean("HoldW");
    }
    public boolean getHold()
    {
    	return this.HoldW;
    }
    public boolean isEnnemy(Entity entity)
    {
    	
    	if(!this.isHostile) { System.out.println("try ennemy false"); return false;  }
    	if(entity instanceof EntityPlayer || entity instanceof EntityMob) {
    		String name = entity.getCommandSenderName();
    		
    		Stage s2 = RegisterStage.getStageAtXY(entity.chunkCoordX, entity.chunkCoordZ, entity.worldObj);
	    	Stage s = RegisterStage.getStageWithId(this.StageId);
	    	if(s != null && s2 != null && s2.getId() == s.getId())
	    	{
	    	
	    		if(entity instanceof EntityMob) { return true; } 
	    		
	    		else if(!s.isOwner(name) && !s.isSubOwner(name) && !s.isAlly(name))
	    		{
	    			return true;
	    		}
	    		
	    	}
    	}
  
    	return false; 
    }
    public boolean CanWanderHere(int x, int z)
    {
    	Chunk c = this.worldObj.getChunkFromBlockCoords(x, z);
    	Stage s = RegisterStage.getStageAtXY(c.xPosition, c.zPosition, this.worldObj);
    	if(s != null && s.getId() == this.StageId)
    	{
    		return true;
    	} 
    	return false;
    }
    
    public int getTargetDistance()
    {
    	if(!this.isHostile) { return 1; }
    	Stage s = RegisterStage.getStageWithId(this.StageId);
    	if(s != null)
    	{
    		return RegisterStage.getChunkLongeurByStage(s.getIdStage());
    	}return 16;
    }
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0D);//
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(500.0D);
    }
    
    public ItemStack getHoldItem()
    {
    	return this.getHeldItem();
    }
    @Override
    protected boolean isAIEnabled()
    {
       return true;
    }
    public int getStageId()
    {
    	return this.StageId;
    }
    public void setStageId(int id)
    {
    	this.StageId = id;
    }
}