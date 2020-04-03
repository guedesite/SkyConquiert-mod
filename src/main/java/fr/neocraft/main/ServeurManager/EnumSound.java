package fr.neocraft.main.ServeurManager;

import fr.neocraft.main.neoreference;

public enum EnumSound {
	NeoMChange(neoreference.MOD_ID + ":menu.change"),
	NeoMClick(neoreference.MOD_ID + ":menu.click"),
	NeoMClose(neoreference.MOD_ID + ":menu.close"),
	NeoMLvl1(neoreference.MOD_ID + ":menu.lvl1"),
	NeoMLvl2(neoreference.MOD_ID + ":menu.lvl2"),
	NeoMLvl3(neoreference.MOD_ID + ":menu.lvl3"),
	NeoMLvl4(neoreference.MOD_ID + ":menu.lvl4"),
	NeoMNope(neoreference.MOD_ID + ":menu.nope"),
	NeoMOpen(neoreference.MOD_ID + ":menu.open"),
	NeoILoot(neoreference.MOD_ID + ":item.loot"),
	NeoEHorraire(neoreference.MOD_ID + ":horraire"),
	FuturError(neoreference.MOD_ID + ":futur.error"), 
	FuturSuccess(neoreference.MOD_ID + ":futur.success"), 
	FuturAction(neoreference.MOD_ID + ":futur.action"), 
	NeoEnderDrag("mob.enderdragon.end");
 
    private String SOUND;
 
    EnumSound(String envUrl) {
        this.SOUND = envUrl;
    }
 
    public String getSound() {
        return SOUND;
    }
}
