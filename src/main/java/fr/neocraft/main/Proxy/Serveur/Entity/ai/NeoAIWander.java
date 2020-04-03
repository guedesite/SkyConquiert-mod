package fr.neocraft.main.Proxy.Serveur.Entity.ai;

import fr.neocraft.main.Proxy.Serveur.Entity.EntityGardien;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class NeoAIWander extends EntityAIBase
{
    private EntityCreature entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;
    private static final String __OBFID = "CL_00001608";

    public NeoAIWander(EntityCreature p_i1648_1_, double p_i1648_2_)
    {
        this.entity = p_i1648_1_;
        this.speed = p_i1648_2_;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
       if (this.entity.getRNG().nextInt(6) != 0)
        {
            return false;
          
        }
        else
        {
            Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.entity, ((EntityGardien) this.entity).getTargetDistance(), 10);

            if (vec3 == null)
            {
                return false;
            }
            else if(((EntityGardien) this.entity).CanMove())
            {
            	int i = 0;
            	while(true)
            	{
            		i++;
	            	if(((EntityGardien) this.entity).CanWanderHere((int)vec3.xCoord, (int)vec3.zCoord))
	            	{
		                this.xPosition = vec3.xCoord;
		                this.yPosition = vec3.yCoord;
		                this.zPosition = vec3.zCoord;
		                return true;
	            	}
	            	else {
	            	}
	            	if(i > 8)
	            	{
	
	            		return false;
	            	}
            	}
            }
            else
            {
            	return false;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.entity.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
    }
}