package com.alex.mysticalagriculture;

import com.alex.cucumber.util.Utils;
import com.alex.mysticalagriculture.client.ModelHandler;
import com.alex.mysticalagriculture.client.blockentity.*;
import com.alex.mysticalagriculture.client.handler.ColorHandler;
import com.alex.mysticalagriculture.client.handler.GuiOverlayHandler;
import com.alex.mysticalagriculture.client.screen.HarvesterScreen;
import com.alex.mysticalagriculture.client.screen.ReprocessorScreen;
import com.alex.mysticalagriculture.client.screen.SoulExtractorScreen;
import com.alex.mysticalagriculture.client.screen.TinkeringTableScreen;
import com.alex.mysticalagriculture.init.BlockEntities;
import com.alex.mysticalagriculture.init.Blocks;
import com.alex.mysticalagriculture.init.Items;
import com.alex.mysticalagriculture.init.ScreenHandlerTypes;
import com.alex.mysticalagriculture.items.ExperienceCapsuleItem;
import com.alex.mysticalagriculture.items.SoulJarItem;
import com.alex.mysticalagriculture.items.tool.EssenceBowItem;
import com.alex.mysticalagriculture.items.tool.EssenceCrossbowItem;
import com.alex.mysticalagriculture.items.tool.EssenceFishingRodItem;
import com.alex.mysticalagriculture.util.RecipeIngredientCache;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alex.mysticalagriculture.handler.ExperienceCapsuleHandler.EXPERIENCE_CAPSULE_PICKUP;
import static com.alex.mysticalagriculture.util.RecipeIngredientCache.RELOAD_INGREDIENT_CACHE;

public class MysticalAgricultureClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(GuiOverlayHandler::setAltarOverlay);
        HudRenderCallback.EVENT.register(GuiOverlayHandler::setEssenceVesselOverlay);

        ClientPlayNetworking.registerGlobalReceiver(EXPERIENCE_CAPSULE_PICKUP, (client, handler, buffer, responseSender) -> client.execute(() -> {
            var player = client.player;

            if (player != null) {
                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.1F, (Utils.RANDOM.nextFloat() - Utils.RANDOM.nextFloat()) * 0.35F + 0.9F);
            }
        }));

        ClientPlayNetworking.registerGlobalReceiver(RELOAD_INGREDIENT_CACHE, (client, handler, buffer, responseSender) -> {
            var caches = new HashMap<RecipeType<?>, Map<Item, List<Ingredient>>>();
            var types = buffer.readVarInt();

            for (var i = 0; i < types; i++) {
                var type = Registry.RECIPE_TYPE.get(buffer.readResourceLocation());
                var items = buffer.readVarInt();

                caches.put(type, new HashMap<>());

                for (var j = 0; j < items; j++) {
                    var item = Registry.ITEM.get(buffer.readResourceLocation());
                    var ingredients = buffer.readVarInt();

                    for (var k = 0; k < ingredients; k++) {
                        var cache = caches.get(type).computeIfAbsent(item, l -> new ArrayList<>());

                        cache.add(Ingredient.fromNetwork(buffer));
                    }
                }
            }

            client.execute(() -> {
                RecipeIngredientCache.INSTANCE.setCaches(caches);
            });
        });

        ModelHandler.onRegisterAdditionalModels();

        BlockEntityRenderers.register(BlockEntities.INFUSION_PEDESTAL, InfusionPedestalRenderer::new);
        BlockEntityRenderers.register(BlockEntities.INFUSION_ALTAR, InfusionAltarRenderer::new);
        BlockEntityRenderers.register(BlockEntities.TINKERING_TABLE, TinkeringTableRenderer::new);
        BlockEntityRenderers.register(BlockEntities.AWAKENING_PEDESTAL, AwakeningPedestalRenderer::new);
        BlockEntityRenderers.register(BlockEntities.AWAKENING_ALTAR, AwakeningAltarRenderer::new);
        BlockEntityRenderers.register(BlockEntities.ESSENCE_VESSEL, EssenceVesselRenderer::new);

        ItemProperties.register(Items.EXPERIENCE_CAPSULE, new ResourceLocation("fill"), ExperienceCapsuleItem.getFillPropertyGetter());
        ItemProperties.register(Items.SOUL_JAR, new ResourceLocation("fill"), SoulJarItem.getFillPropertyGetter());
        ItemProperties.register(Items.INFERIUM_BOW, new ResourceLocation("pull"), EssenceBowItem.getPullPropertyGetter());
        ItemProperties.register(Items.INFERIUM_BOW, new ResourceLocation("pulling"), EssenceBowItem.getPullingPropertyGetter());
        ItemProperties.register(Items.INFERIUM_CROSSBOW, new ResourceLocation("pull"), EssenceCrossbowItem.getPullPropertyGetter());
        ItemProperties.register(Items.INFERIUM_CROSSBOW, new ResourceLocation("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
        ItemProperties.register(Items.INFERIUM_CROSSBOW, new ResourceLocation("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
        ItemProperties.register(Items.INFERIUM_CROSSBOW, new ResourceLocation("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());
        ItemProperties.register(Items.INFERIUM_FISHING_ROD, new ResourceLocation("cast"), EssenceFishingRodItem.getCastPropertyGetter());
        ItemProperties.register(Items.PRUDENTIUM_BOW, new ResourceLocation("pull"), EssenceBowItem.getPullPropertyGetter());
        ItemProperties.register(Items.PRUDENTIUM_BOW, new ResourceLocation("pulling"), EssenceBowItem.getPullingPropertyGetter());
        ItemProperties.register(Items.PRUDENTIUM_CROSSBOW, new ResourceLocation("pull"), EssenceCrossbowItem.getPullPropertyGetter());
        ItemProperties.register(Items.PRUDENTIUM_CROSSBOW, new ResourceLocation("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
        ItemProperties.register(Items.PRUDENTIUM_CROSSBOW, new ResourceLocation("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
        ItemProperties.register(Items.PRUDENTIUM_CROSSBOW, new ResourceLocation("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());
        ItemProperties.register(Items.PRUDENTIUM_FISHING_ROD, new ResourceLocation("cast"), EssenceFishingRodItem.getCastPropertyGetter());
        ItemProperties.register(Items.TERTIUM_BOW, new ResourceLocation("pull"), EssenceBowItem.getPullPropertyGetter());
        ItemProperties.register(Items.TERTIUM_BOW, new ResourceLocation("pulling"), EssenceBowItem.getPullingPropertyGetter());
        ItemProperties.register(Items.TERTIUM_CROSSBOW, new ResourceLocation("pull"), EssenceCrossbowItem.getPullPropertyGetter());
        ItemProperties.register(Items.TERTIUM_CROSSBOW, new ResourceLocation("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
        ItemProperties.register(Items.TERTIUM_CROSSBOW, new ResourceLocation("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
        ItemProperties.register(Items.TERTIUM_CROSSBOW, new ResourceLocation("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());
        ItemProperties.register(Items.TERTIUM_FISHING_ROD, new ResourceLocation("cast"), EssenceFishingRodItem.getCastPropertyGetter());
        ItemProperties.register(Items.IMPERIUM_BOW, new ResourceLocation("pull"), EssenceBowItem.getPullPropertyGetter());
        ItemProperties.register(Items.IMPERIUM_BOW, new ResourceLocation("pulling"), EssenceBowItem.getPullingPropertyGetter());
        ItemProperties.register(Items.IMPERIUM_CROSSBOW, new ResourceLocation("pull"), EssenceCrossbowItem.getPullPropertyGetter());
        ItemProperties.register(Items.IMPERIUM_CROSSBOW, new ResourceLocation("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
        ItemProperties.register(Items.IMPERIUM_CROSSBOW, new ResourceLocation("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
        ItemProperties.register(Items.IMPERIUM_CROSSBOW, new ResourceLocation("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());
        ItemProperties.register(Items.IMPERIUM_FISHING_ROD, new ResourceLocation("cast"), EssenceFishingRodItem.getCastPropertyGetter());
        ItemProperties.register(Items.SUPREMIUM_BOW, new ResourceLocation("pull"), EssenceBowItem.getPullPropertyGetter());
        ItemProperties.register(Items.SUPREMIUM_BOW, new ResourceLocation("pulling"), EssenceBowItem.getPullingPropertyGetter());
        ItemProperties.register(Items.SUPREMIUM_CROSSBOW, new ResourceLocation("pull"), EssenceCrossbowItem.getPullPropertyGetter());
        ItemProperties.register(Items.SUPREMIUM_CROSSBOW, new ResourceLocation("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
        ItemProperties.register(Items.SUPREMIUM_CROSSBOW, new ResourceLocation("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
        ItemProperties.register(Items.SUPREMIUM_CROSSBOW, new ResourceLocation("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());
        ItemProperties.register(Items.SUPREMIUM_FISHING_ROD, new ResourceLocation("cast"), EssenceFishingRodItem.getCastPropertyGetter());
        ItemProperties.register(Items.AWAKENED_SUPREMIUM_BOW, new ResourceLocation("pull"), EssenceBowItem.getPullPropertyGetter());
        ItemProperties.register(Items.AWAKENED_SUPREMIUM_BOW, new ResourceLocation("pulling"), EssenceBowItem.getPullingPropertyGetter());
        ItemProperties.register(Items.AWAKENED_SUPREMIUM_CROSSBOW, new ResourceLocation("pull"), EssenceCrossbowItem.getPullPropertyGetter());
        ItemProperties.register(Items.AWAKENED_SUPREMIUM_CROSSBOW, new ResourceLocation("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
        ItemProperties.register(Items.AWAKENED_SUPREMIUM_CROSSBOW, new ResourceLocation("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
        ItemProperties.register(Items.AWAKENED_SUPREMIUM_CROSSBOW, new ResourceLocation("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());
        ItemProperties.register(Items.AWAKENED_SUPREMIUM_FISHING_ROD, new ResourceLocation("cast"), EssenceFishingRodItem.getCastPropertyGetter());

        MenuScreens.register(ScreenHandlerTypes.REPROCESSOR, ReprocessorScreen::new);
        MenuScreens.register(ScreenHandlerTypes.TINKERING_TABLE, TinkeringTableScreen::new);
        MenuScreens.register(ScreenHandlerTypes.SOUL_EXTRACTOR, SoulExtractorScreen::new);
        MenuScreens.register(ScreenHandlerTypes.HARVESTER, HarvesterScreen::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutoutMipped(), Blocks.INFERIUM_ORE, Blocks.DEEPSLATE_INFERIUM_ORE, Blocks.PROSPERITY_ORE, Blocks.DEEPSLATE_PROSPERITY_ORE, Blocks.SOULIUM_ORE);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(), Blocks.SOUL_GLASS, Blocks.WITHERPROOF_GLASS, Blocks.ESSENCE_VESSEL);

        ColorHandler.onBlockColors();
        ColorHandler.onItemColors();
    }
}
