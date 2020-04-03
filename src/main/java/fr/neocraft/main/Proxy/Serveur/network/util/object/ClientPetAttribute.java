package fr.neocraft.main.Proxy.Serveur.network.util.object;

import java.io.Serializable;
import java.util.UUID;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Serveur.network.util.T;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;

public class ClientPetAttribute extends T implements Serializable{
	private  static  final  long serialVersionUID =  5464867684657468768L;
	private int lvl;
	private int id;
	
	public ClientPetAttribute(int lvl, int id)
	{
		this.lvl = lvl;
		this.id=id;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void A() {
		System.out.println("EntityLiving Player Attribute CLIENT");
		
		PutAttribute(Minecraft.getMinecraft().thePlayer,id, lvl, true);
	}
	

	
	public void PutAttribute(EntityPlayer p, int id, int lvl, boolean side)
	{
		System.out.println("EntityLiving Player Attribute Changed SERVER");
		UUID Damage = UUID.fromString("7dc53df5-703e-49b3-8670-b1c468f47f1f");
		UUID Speed = UUID.fromString("7dc53df5-703e-49b3-8670-b1c468f47f2f");
		UUID Health = UUID.fromString("7dc53df5-703e-49b3-8670-b1c468f47f3f");
		switch(id)
		{
			case -1:
				IAttributeInstance AttaqueAttrib3 = p.getEntityAttribute(SharedMonsterAttributes.attackDamage);
				IAttributeInstance HealthAttrib3 = p.getEntityAttribute(SharedMonsterAttributes.maxHealth);
				IAttributeInstance MovementAttrib3 = p.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
				if(side)
				{
					p.capabilities.setPlayerWalkSpeed(0.1F);
					
				}
				
				
					
					AttributeModifier Attaque3 = AttaqueAttrib3.getModifier(Damage);
					if(Attaque3 != null)
					{
						AttaqueAttrib3.removeModifier(Attaque3);
					}
					

					AttributeModifier Healt3 = HealthAttrib3.getModifier(Health);
					if(Healt3 != null)
					{
						HealthAttrib3.removeModifier(Healt3);
					}
					
					AttributeModifier Movement3 = MovementAttrib3.getModifier(Speed);
					if(Movement3 != null)
					{
						MovementAttrib3.removeModifier(Movement3);
					}
				
				break;
			case 0:
				
				IAttributeInstance AttaqueAttrib = p.getEntityAttribute(SharedMonsterAttributes.attackDamage);
				AttributeModifier Attaque = AttaqueAttrib.getModifier(Damage);
				if(Attaque != null)
				{
					AttaqueAttrib.removeModifier(Attaque);
				}
				AttaqueAttrib.applyModifier(new AttributeModifier(Damage,"NeoCraft Pet Attribute Damage", (lvl/10), 0).setSaved(true));
				
				
				IAttributeInstance HealthAttrib = p.getEntityAttribute(SharedMonsterAttributes.maxHealth);
				AttributeModifier Healt = HealthAttrib.getModifier(Health);
				if(Healt != null)
				{
					HealthAttrib.removeModifier(Healt);
				}
				HealthAttrib.applyModifier(new AttributeModifier(Health,"NeoCraft Pet Attribute Health", -(0.85 * (lvl/10)), 0).setSaved(true));
				
				break;
			case 1:

				//resitance
				
				IAttributeInstance MovementAttrib = p.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
				AttributeModifier Movement = MovementAttrib.getModifier(Speed);
				if(Movement != null)
				{
					 MovementAttrib.removeModifier(Movement);
				}
				MovementAttrib.applyModifier(new AttributeModifier(Speed,"NeoCraft Pet Attribute Speed", -(0.0005 * lvl), 0).setSaved(true));
				if(side)
				{
					p.capabilities.setPlayerWalkSpeed((float) (0.1F-(0.0005 * lvl)));
				}
				break;
			case 2:
				
				//-resistance
				IAttributeInstance MovementAttrib2 = p.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
				AttributeModifier Movement2 = MovementAttrib2.getModifier(Speed);
				if(Movement2 != null)
				{
					MovementAttrib2 .removeModifier(Movement2);
				}
				MovementAttrib2.applyModifier(new AttributeModifier(Speed,"NeoCraft Pet Attribute Speed", (lvl/100), 0).setSaved(true));
				if(side)
				{
					p.capabilities.setPlayerWalkSpeed(0.1F+(lvl/100));
				}
				break;
			case 3:
				
				IAttributeInstance AttaqueAttrib2 = p.getEntityAttribute(SharedMonsterAttributes.attackDamage);
				AttributeModifier Attaque2 = AttaqueAttrib2.getModifier(Damage);
				if(Attaque2 != null)
				{
					AttaqueAttrib2.removeModifier(Attaque2);
				}
				AttaqueAttrib2.applyModifier(new AttributeModifier(Damage,"NeoCraft Pet Attribute Damage", -((lvl/10)/1.4), 0).setSaved(true));
				
				
				IAttributeInstance HealthAttrib2 = p.getEntityAttribute(SharedMonsterAttributes.maxHealth);
				AttributeModifier Healt2 = HealthAttrib2.getModifier(Health);
				if(Healt2 != null)
				{
					HealthAttrib2 .removeModifier(Healt2);
				}
				HealthAttrib2.applyModifier(new AttributeModifier(Health,"NeoCraft Pet Attribute Health", (1 * (lvl/3)), 0).setSaved(true));
				
				
				
				break;

		} 
	}
}
