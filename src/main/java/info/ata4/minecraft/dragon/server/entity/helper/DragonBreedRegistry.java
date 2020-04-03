/*
 ** 2013 October 25
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package info.ata4.minecraft.dragon.server.entity.helper;

import info.ata4.minecraft.dragon.server.entity.breeds.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Central dragon breed registry.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class DragonBreedRegistry {
    
    private static DragonBreedRegistry instance;
    
    public static DragonBreedRegistry getInstance() {
        if (instance == null) {
            instance = new DragonBreedRegistry();
        }
        
        return instance;
    }
    
    private Map<String, DragonBreed> breeds = new HashMap<String, DragonBreed>();
    private Map<Integer, DragonBreed> breedsId = new HashMap<Integer, DragonBreed>();
    
    
    public Map<Integer, DragonBreed> getMapId() {
    	return breedsId;
    }
    
    
    public DragonBreed getBreedById(int i) {
    	return breedsId.get(i);
    }
    
    private DragonBreedRegistry() {
        add(new DragonBreedAir()); // 1
        add(new DragonBreedEnd()); //0
        add(new DragonBreedFire()); // 2
        add(new DragonBreedIce()); // 3
        add(new DragonBreedGhost()); // 4
        add(new DragonBreedWater()); // 5 
        add(new DragonBreedNether()); //6
        add(new DragonBreedForest()); //7
        add(new DragonBreedNeant()); //8
        add(new DragonBreedGold()); //9
    }
    
    private void add(DragonBreed breed) {
        breeds.put(breed.getName(), breed);
        breedsId.put(breed.getId(), breed);
    }
    
    public List<DragonBreed> getBreeds() {
        return new ArrayList<DragonBreed>(breeds.values());
    }
    
    public DragonBreed getBreedByName(String name) {
        return breeds.get(name);
    }
}
