package net.prosavage.savagerpg.utils.merchants;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SMerchant {

    public MerchantRecipe recipe;

    public MerchantRecipe createRecipe(ItemStack result, ItemStack requirement1, ItemStack requirement2){
        recipe = new MerchantRecipe(result, 999999);
        recipe.setVillagerExperience(0);
        recipe.setPriceMultiplier(1);
        recipe.addIngredient(requirement1);
        recipe.addIngredient(requirement2);
        return recipe;
    }

    public void addOneToMerchant(Merchant merchant, MerchantRecipe recipe){
        List<MerchantRecipe> recipes = new ArrayList<>();
        recipes.add(recipe);
        merchant.setRecipes(recipes);
    }

    public void addMultipleToMerchant(Merchant merchant, MerchantRecipe[] recipes){
        merchant.setRecipes(Arrays.asList(recipes));
    }

    public void clearRecipes(Merchant merchant){
        List<MerchantRecipe> recipes = new ArrayList<>();
        merchant.setRecipes(recipes);
    }

    public void removeFromMerchant(Merchant merchant,MerchantRecipe recipe){
        List<org.bukkit.inventory.MerchantRecipe> recipes = merchant.getRecipes();
        int removalInt = 0;
        for (Object recipeRemoval : recipes){
            if (recipeRemoval == recipe){
                recipes.remove(removalInt);
                removalInt++;
            }
        }
        merchant.setRecipes(recipes);
    }
}
