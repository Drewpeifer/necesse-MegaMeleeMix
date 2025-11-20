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

        // // Register our items
        ItemRegistry.registerItem("exsanguinatorsword", new ExsanguinatorSword(), 20, true);
        ItemRegistry.registerItem("cauterizergreatsword", new CauterizerGreatsword(), 20, true);
        ItemRegistry.registerItem("tranquilizerhammer", new TranquilizerHammer(), 20, true);
        ItemRegistry.registerItem("boomywhackerspear", new BoomyWhackerSpear(), 20, true);
        ItemRegistry.registerItem("warlordspauldron", new WarlordsPauldron(), 400F, true);

        // // Register our buff
        BuffRegistry.registerBuff("stun", new Stun());
        BuffRegistry.registerBuff("warlords_pauldron_buff", new WarlordsPauldronBuff());

        // register event
        LevelEventRegistry.registerEvent("boomy_explosion_event", BoomyExplosion.class);

    }

    public void initResources() {
        // Sometimes your textures will have a black or other outline unintended under rotation or scaling
        // This is caused by alpha blending between transparent pixels and the edge
        // To fix this, run the preAntialiasTextures gradle task
        // It will process your textures and save them again with a fixed alpha edge color

        // ExampleMob.texture = GameTexture.fromFile("mobs/examplemob");
    }

    public void postInit() {
        // Add recipes
        // Example item recipe, crafted in inventory for 2 iron bars
        // Recipes.registerModRecipe(new Recipe(
        //         "exampleitem",
        //         1,
        //         RecipeTechRegistry.NONE,
        //         new Ingredient[]{
        //                 new Ingredient("ironbar", 2)
        //         }
        // ).showAfter("woodboat")); // Show recipe after wood boat recipe
        Recipes.registerModRecipe(new Recipe(
                "exsanguinatorsword",
                1,
                RecipeTechRegistry.IRON_ANVIL,
                new Ingredient[]{
                        // new Ingredient("anylog", 1),
                        new Ingredient("tungstenbar", 15),
                }
        ));
        Recipes.registerModRecipe(new Recipe(
                "cauterizergreatsword",
                1,
                RecipeTechRegistry.IRON_ANVIL,
                new Ingredient[]{
                        // new Ingredient("anylog", 1),
                        new Ingredient("tungstenbar", 35),
                }
        ));
        Recipes.registerModRecipe(new Recipe(
                "tranquilizerhammer",
                1,
                RecipeTechRegistry.IRON_ANVIL,
                new Ingredient[]{
                        // new Ingredient("anylog", 1),
                        new Ingredient("myceliumbar", 25),
                }
        ));
        Recipes.registerModRecipe(new Recipe(
                "boomywhackerspear",
                1,
                RecipeTechRegistry.IRON_ANVIL,
                new Ingredient[]{
                        // new Ingredient("anylog", 1),
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
