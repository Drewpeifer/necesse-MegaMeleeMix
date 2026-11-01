package megameleemix;

import megameleemix.buffs.Stun;
import megameleemix.buffs.WarlordsPauldronBuff;
import megameleemix.events.BoomyExplosion;
import megameleemix.items.BoomyWhackerSpear;
import megameleemix.items.CauterizerGreatsword;
import megameleemix.items.ExsanguinatorSword;
import megameleemix.items.TranquilizerHammer;
import megameleemix.items.WarlordsPauldron;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.BuffRegistry;
import necesse.engine.registries.ItemRegistry;
import necesse.engine.registries.LevelEventRegistry;
import necesse.engine.registries.RecipeTechRegistry;
import necesse.inventory.recipe.Ingredient;
import necesse.inventory.recipe.Recipe;
import necesse.inventory.recipe.Recipes;

@ModEntry
public class MegaMeleeMix {

    public void init() {
        // Items
        ItemRegistry.registerItem("exsanguinatorsword", new ExsanguinatorSword(), 20, true);
        ItemRegistry.registerItem("cauterizergreatsword", new CauterizerGreatsword(), 20, true);
        ItemRegistry.registerItem("tranquilizerhammer", new TranquilizerHammer(), 20, true);
        ItemRegistry.registerItem("boomywhackerspear", new BoomyWhackerSpear(), 20, true);
        ItemRegistry.registerItem("warlordspauldron", new WarlordsPauldron(), 400F, true);

        // Buffs
        BuffRegistry.registerBuff("stun", new Stun());
        BuffRegistry.registerBuff("warlords_pauldron_buff", new WarlordsPauldronBuff());

        // Level events
        LevelEventRegistry.registerEvent("boomy_explosion_event", BoomyExplosion.class);
    }

    public void initResources() {
        // Item and weapon textures load automatically from resources/items and resources/player/weapons
    }

    public void postInit() {
        Recipes.registerModRecipe(new Recipe(
                "exsanguinatorsword",
                1,
                RecipeTechRegistry.IRON_ANVIL,
                new Ingredient[]{
                        new Ingredient("tungstenbar", 15),
                }
        ));
        Recipes.registerModRecipe(new Recipe(
                "cauterizergreatsword",
                1,
                RecipeTechRegistry.IRON_ANVIL,
                new Ingredient[]{
                        new Ingredient("tungstenbar", 35),
                }
        ));
        Recipes.registerModRecipe(new Recipe(
                "tranquilizerhammer",
                1,
                RecipeTechRegistry.IRON_ANVIL,
                new Ingredient[]{
                        new Ingredient("myceliumbar", 25),
                }
        ));
        Recipes.registerModRecipe(new Recipe(
                "boomywhackerspear",
                1,
                RecipeTechRegistry.IRON_ANVIL,
                new Ingredient[]{
                        new Ingredient("anylog", 20),
                        new Ingredient("copperbar", 20),
                        new Ingredient("ironbar", 20),
                        new Ingredient("goldbar", 20),
                        new Ingredient("tungstenbar", 20),
                        new Ingredient("dynamitestick", 1),
                }
        ));
        Recipes.registerModRecipe(new Recipe(
                "warlordspauldron",
                1,
                RecipeTechRegistry.IRON_ANVIL,
                new Ingredient[]{
                        new Ingredient("challengerspauldron", 5),
                }
        ));
    }

}
