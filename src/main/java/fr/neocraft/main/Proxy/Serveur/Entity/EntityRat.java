package fr.neocraft.main.Proxy.Serveur.Entity;

import fr.neocraft.main.neoreference;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class EntityRat extends EntityMob{

	    public EntityRat(World p_i1743_1_)
	    {
	        super(p_i1743_1_);
	        this.setSize(0.5F, 0.3F);
	    }

	    protected void entityInit()
	    {
	        super.entityInit();
	        this.dataWatcher.addObject(16, new Byte((byte)0));
	    }

	    /**
	     * Called to update the entity's position/logic.
	     */
	    public void onUpdate()
	    {
	        super.onUpdate();

	    }

	    protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0D);
	        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.800000011920929D);
	    }

	    protected String getLivingSound()
	    {
	        return neoreference.MOD_ID+":mob.rat.grunt";
	    }

	    /**
	     * Returns the sound this mob makes when it is hurt.
	     */
	    protected String getHurtSound()
	    {
	    	return neoreference.MOD_ID+":mob.rat.hurt";
	    }

	    /**
	     * Returns the sound this mob makes on death.
	     */
	    protected String getDeathSound()
	    {
	    	return neoreference.MOD_ID+":mob.rat.dying";
	    }


	    

	   

}
