package dev.night.ecraft.block;

import dev.night.ecraft.Ecraft;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class EBlocks {
    public static final Block MAGIC_LAMP = registerBlock("magic_lamp",
            new MagicLampBlock(
                    AbstractBlock.Settings.create()
                            .luminance(state ->  state.get(Properties.LIT) ? 15 : 2)
                            .strength(.5f)
                            .sounds(BlockSoundGroup.GLASS)
                            .allowsSpawning(Blocks::always)
                            .mapColor(MapColor.LAPIS_BLUE)
            )
    );

    public static final Block MAGIC_RAIL = registerBlock("magic_rail",
            new RailBlock(
                    AbstractBlock.Settings.create()
                            .noCollision()
                            .strength(0.7f)
                            .sounds(BlockSoundGroup.METAL)
            )
    );

    //region Chicken Blocks
    public static final Block RAW_CHICKEN_BLOCK = registerBlock("raw_chicken_block",
            new ChickenBlock(
                    AbstractBlock.Settings.create()
                            .solid()
                            .strength(.5f)
                            .sounds(BlockSoundGroup.SLIME),
                    true
            )
    );

    public static final Block COOKED_CHICKEN_BLOCK = registerBlock("cooked_chicken_block",
            new ChickenBlock(
                    AbstractBlock.Settings.create()
                            .solid()
                            .strength(.5f)
                            .sounds(BlockSoundGroup.SLIME),
                    false
            )
    );
    //endregion

    //region Explosive Blocks
    public static final Block CHARGE = registerBlock("charge",
            new ExplosiveChargeBlock(
                    AbstractBlock.Settings.copy(Blocks.TNT),
                    4
            )
    );

    public static final Block DETONIUM_CHARGE = registerFireproofBlock("detonium_charge",
            new ExplosiveChargeBlock(
                AbstractBlock.Settings.copy(Blocks.TNT)
                        .mapColor(MapColor.YELLOW),
                    10
            ),
            Rarity.RARE
    );

    public static final Block VERDANT_CHARGE = registerFireproofBlock("verdant_charge",
            new ExplosiveChargeBlock(
                    AbstractBlock.Settings.copy(DETONIUM_CHARGE)
                            .mapColor(MapColor.EMERALD_GREEN),
                    10,
                    Text.translatable("block.ecraft.verdant_charge.tooltip").withColor(0x79d71c),
                    (blockState -> {
                        return blockState.isIn(ConventionalBlockTags.ORES) || blockState.isIn(ConventionalBlockTags.STORAGE_BLOCKS) || blockState.isIn(ConventionalBlockTags.CHESTS);
                    })
            ),
            Rarity.RARE
    );

    public static final Block DETONIUM_NUKE = registerFireproofBlock("detonium_nuke",
            new ExplosiveChargeBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.BRIGHT_RED)
                            .breakInstantly()
                            .sounds(BlockSoundGroup.METAL),
                    30
            ),
            Rarity.RARE
    );
    //endregion

    //region Ore Blocks
    public static final Block MAGIC_ORE = registerBlock("magic_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(3, 6),
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.STONE_GRAY)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .strength(3f, 3f)
            )
    );

    public static final Block DIRT_MAGIC_ORE = registerBlock("dirt_magic_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(3, 6),
                    AbstractBlock.Settings.copy(MAGIC_ORE)
                            .mapColor(MapColor.DIRT_BROWN)
                            .strength(1f, 1f)
                            .sounds(BlockSoundGroup.SUSPICIOUS_GRAVEL)
            )
    );

    public static final Block GRANITE_MAGIC_ORE = registerBlock("granite_magic_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(3, 6),
                    AbstractBlock.Settings.copy(MAGIC_ORE)
                            .mapColor(MapColor.DIRT_BROWN)
            )
    );

    public static final Block ANDESITE_MAGIC_ORE = registerBlock("andesite_magic_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(3, 6),
                    AbstractBlock.Settings.copy(MAGIC_ORE)
            )
    );

    public static final Block DIORITE_MAGIC_ORE = registerBlock("diorite_magic_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(3, 6),
                    AbstractBlock.Settings.copy(MAGIC_ORE)
                            .mapColor(MapColor.OFF_WHITE)
            )
    );

    public static final Block TUFF_MAGIC_ORE = registerBlock("tuff_magic_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(3, 6),
                    AbstractBlock.Settings.copy(MAGIC_ORE)
                            .mapColor(MapColor.TERRACOTTA_GRAY)
            )
    );

    public static final Block DEEPSLATE_MAGIC_ORE = registerBlock("deepslate_magic_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(3, 6),
                    AbstractBlock.Settings.copy(MAGIC_ORE)
                            .mapColor(MapColor.DEEPSLATE_GRAY)
                            .strength(4.5f, 3f)
                            .sounds(BlockSoundGroup.DEEPSLATE)
            )
    );

    public static final Block DEMONITE_ORE = registerBlock("demonite_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(4, 6),
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.STONE_GRAY)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .strength(3f, 3f)
            )
    );

    public static final Block DEEPSLATE_DEMONITE_ORE = registerBlock("deepslate_demonite_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(4, 6),
                    AbstractBlock.Settings.copy(DEMONITE_ORE)
                            .mapColor(MapColor.DEEPSLATE_GRAY)
                            .strength(4.5f, 3f)
                            .sounds(BlockSoundGroup.DEEPSLATE)
            )
    );

    public static final Block TUNGSTEN_ORE = registerBlock("tungsten_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(2, 3),
                    AbstractBlock.Settings.copy(Blocks.IRON_ORE)
            )
    );

    public static final Block DEEPSLATE_TUNGSTEN_ORE = registerBlock("deepslate_tungsten_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(2, 3),
                    AbstractBlock.Settings.copy(TUNGSTEN_ORE)
                            .mapColor(MapColor.DEEPSLATE_GRAY)
                            .strength(4.5f, 3f)
                            .sounds(BlockSoundGroup.DEEPSLATE)
            )
    );

    public static final Block HELLSTONE_ORE = registerFireproofBlock("hellstone_ore",
            new MagmaBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DARK_CRIMSON)
                            .requiresTool()
                            .luminance(state -> 2)
                            .strength(5f, 50f)
                            .allowsSpawning((state, world, pos, type) -> type.isFireImmune())
                            .postProcess(Blocks::always)
                            .emissiveLighting(Blocks::always)
                            .sounds(BlockSoundGroup.ANCIENT_DEBRIS)
            )
    );

    public static final Block COBALT_ORE = registerFireproofBlock("cobalt_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(6, 10),
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.STONE_GRAY)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .strength(4f, 4f)
            ),
            Rarity.UNCOMMON
    );

    public static final Block DEEPSLATE_COBALT_ORE = registerFireproofBlock("deepslate_cobalt_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(6, 10),
                    AbstractBlock.Settings.copy(COBALT_ORE)
                            .mapColor(MapColor.DEEPSLATE_GRAY)
                            .strength(5f, 4f)
            ),
            Rarity.UNCOMMON
    );

    public static final Block ORICHALCUM_ORE = registerFireproofBlock("orichalcum_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(7, 10),
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.STONE_GRAY)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .strength(4f, 4f)
            ),
            Rarity.UNCOMMON
    );

    public static final Block DEEPSLATE_ORICHALCUM_ORE = registerFireproofBlock("deepslate_orichalcum_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(7, 10),
                    AbstractBlock.Settings.copy(ORICHALCUM_ORE)
                            .mapColor(MapColor.DEEPSLATE_GRAY)
                            .strength(5f, 4f)
            ),
            Rarity.UNCOMMON
    );

    public static final Block TITANIUM_ORE = registerFireproofBlock("titanium_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(7, 11),
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.STONE_GRAY)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .strength(4f, 4f)
            ),
            Rarity.UNCOMMON
    );

    public static final Block DEEPSLATE_TITANIUM_ORE = registerFireproofBlock("deepslate_titanium_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(7, 11),
                    AbstractBlock.Settings.copy(TITANIUM_ORE)
                            .mapColor(MapColor.DEEPSLATE_GRAY)
                            .strength(5f, 4f)
            ),
            Rarity.UNCOMMON
    );

    public static final Block VERDANT_ORE = registerFireproofBlock("verdant_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(8, 11),
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.STONE_GRAY)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .strength(4f, 4f)
            ),
            Rarity.RARE
    );

    public static final Block DEEPSLATE_VERDANT_ORE = registerFireproofBlock("deepslate_verdant_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(8, 11),
                    AbstractBlock.Settings.copy(VERDANT_ORE)
                            .mapColor(MapColor.DEEPSLATE_GRAY)
                            .strength(5f, 4f)
            ),
            Rarity.RARE
    );

    public static final Block GRIMSTONE_VERDANT_ORE = registerFireproofBlock("grimstone_verdant_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(8, 11),
                    AbstractBlock.Settings.copy(VERDANT_ORE)
            ),
            Rarity.RARE
    );

    public static final Block VOIDSTONE_VERDANT_ORE = registerFireproofBlock("voidstone_verdant_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(8, 11),
                    AbstractBlock.Settings.copy(DEEPSLATE_VERDANT_ORE)
            ),
            Rarity.RARE
    );

    public static final Block ETERNAL_ICE_VERDANT_ORE = registerFireproofBlock("eternal_ice_verdant_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(8, 11),
                    AbstractBlock.Settings.copy(VERDANT_ORE)
                            .mapColor(MapColor.PALE_PURPLE)
                            .sounds(BlockSoundGroup.GLASS)
            ),
            Rarity.RARE
    );

    public static final Block HAZE_ICE_VERDANT_ORE = registerFireproofBlock("haze_ice_verdant_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(8, 11),
                    AbstractBlock.Settings.copy(DEEPSLATE_VERDANT_ORE)
                            .mapColor(MapColor.PALE_PURPLE)
                            .sounds(BlockSoundGroup.GLASS)
            ),
            Rarity.RARE
    );

    public static final Block DETONIUM_ORE = registerFireproofBlock("detonium_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(8, 11),
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.STONE_GRAY)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .strength(4f, 4f)
            ),
            Rarity.RARE
    );

    public static final Block DEEPSLATE_DETONIUM_ORE = registerFireproofBlock("deepslate_detonium_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(8, 11),
                    AbstractBlock.Settings.copy(DETONIUM_ORE)
                            .mapColor(MapColor.DEEPSLATE_GRAY)
                            .strength(5f, 4f)
            ),
            Rarity.RARE
    );

    public static final Block GRIMSTONE_DETONIUM_ORE = registerFireproofBlock("grimstone_detonium_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(8, 11),
                    AbstractBlock.Settings.copy(DETONIUM_ORE)
            ),
            Rarity.RARE
    );

    public static final Block VOIDSTONE_DETONIUM_ORE = registerFireproofBlock("voidstone_detonium_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(8, 11),
                    AbstractBlock.Settings.copy(DEEPSLATE_DETONIUM_ORE)
            ),
            Rarity.RARE
    );

    public static final Block ETERNAL_ICE_DETONIUM_ORE = registerFireproofBlock("eternal_ice_detonium_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(8, 11),
                    AbstractBlock.Settings.copy(DETONIUM_ORE)
                            .mapColor(MapColor.PALE_PURPLE)
                            .sounds(BlockSoundGroup.GLASS)
            ),
            Rarity.RARE
    );

    public static final Block HAZE_ICE_DETONIUM_ORE = registerFireproofBlock("haze_ice_detonium_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(8, 11),
                    AbstractBlock.Settings.copy(DEEPSLATE_DETONIUM_ORE)
                            .mapColor(MapColor.PALE_PURPLE)
                            .sounds(BlockSoundGroup.GLASS)
            ),
            Rarity.RARE
    );

    public static final Block BASALT_AETHERIUM_ORE = registerFireproofBlock("basalt_aetherium_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(8, 11),
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.BLACK)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .sounds(BlockSoundGroup.BASALT)
                            .strength(4f, 4f)
            ),
            Rarity.EPIC
    );

    public static final Block BLACKSTONE_AETHERIUM_ORE = registerFireproofBlock("blackstone_aetherium_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(8, 11),
                    AbstractBlock.Settings.copy(BASALT_AETHERIUM_ORE)
                            .sounds(BlockSoundGroup.GILDED_BLACKSTONE)
            ),
            Rarity.EPIC
    );

    public static final Block DEEPSLATE_AETHERIUM_ORE = registerFireproofBlock("deepslate_aetherium_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(8, 11),
                    AbstractBlock.Settings.copy(BASALT_AETHERIUM_ORE)
                            .mapColor(MapColor.DEEPSLATE_GRAY)
                            .sounds(BlockSoundGroup.DEEPSLATE)
            ),
            Rarity.EPIC
    );


    public static final Block END_AETHERIUM_ORE = registerFireproofBlock("end_aetherium_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(8, 11),
                    AbstractBlock.Settings.copy(BASALT_AETHERIUM_ORE)
                            .mapColor(MapColor.PALE_YELLOW)
                            .sounds(BlockSoundGroup.GILDED_BLACKSTONE)
            ),
            Rarity.EPIC
    );
    //endregion

    //region Raw Ore Blocks
    public static final Block RAW_MAGIC_BLOCK = registerBlock("raw_magic_block",
            new MagicBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.BLUE)
                            .requiresTool()
                            .strength(5f, 6f)
                            .sounds(BlockSoundGroup.METAL)
                            .solidBlock(Blocks::never)
            )
    );

    public static final Block RAW_DIAMOND_BLOCK = registerBlock("raw_diamond_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DIAMOND_BLUE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .strength(5f, 6f)
            )
    );

    public static final Block RAW_DEMONITE_BLOCK = registerBlock("raw_demonite_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.PALE_PURPLE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .strength(5f, 6f)
            )
    );

    public static final Block RAW_TUNGSTEN_BLOCK = registerBlock("raw_tungsten_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.PALE_GREEN)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .strength(5f, 6f)
            )
    );

    public static final Block RAW_HELLSTONE_BLOCK = registerFireproofBlock("raw_hellstone_block",
            new MagmaBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DARK_RED)
                            .requiresTool()
                            .luminance(state -> 2)
                            .strength(5f, 50f)
                            .allowsSpawning((state, world, pos, type) -> type.isFireImmune())
                            .postProcess(Blocks::always)
                            .emissiveLighting(Blocks::always)
                            .sounds(BlockSoundGroup.ANCIENT_DEBRIS)
            )
    );

    public static final Block RAW_COBALT_BLOCK = registerFireproofBlock("raw_cobalt_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.LAPIS_BLUE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .strength(6f, 7f)
            ),
            Rarity.UNCOMMON
    );

    public static final Block RAW_ORICHALCUM_BLOCK = registerFireproofBlock("raw_orichalcum_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.PINK)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .strength(6f, 7f)
            ),
            Rarity.UNCOMMON
    );

    public static final Block RAW_TITANIUM_BLOCK = registerFireproofBlock("raw_titanium_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.IRON_GRAY)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .strength(6f, 7f)
            ),
            Rarity.UNCOMMON
    );

    public static final Block RAW_VERDANT_BLOCK = registerFireproofBlock("raw_verdant_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.EMERALD_GREEN)
                            .instrument(NoteBlockInstrument.CHIME)
                            .requiresTool()
                            .strength(6f, 7f)
            ),
            Rarity.RARE
    );

    public static final Block RAW_DETONIUM_BLOCK = registerFireproofBlock("raw_detonium_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BRIGHT_RED)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresTool()
                    .strength(6f, 7f)
            ),
            Rarity.RARE
    );
    //endregion

    //region Processed Ore Blocks
    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            new MagicBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.BLUE)
                            .requiresTool()
                            .strength(5f, 6f)
                            .sounds(BlockSoundGroup.METAL)
                            .solidBlock(Blocks::never)
            )
    );

    public static final Block TUNGSTEN_BLOCK = registerBlock("tungsten_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.PALE_GREEN)
                            .instrument(NoteBlockInstrument.XYLOPHONE)
                            .requiresTool()
                            .strength(5f, 6f)
                            .sounds(BlockSoundGroup.METAL)
            )
    );

    public static final Block DEMONITE_BLOCK = registerBlock("demonite_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.PALE_PURPLE)
                            .instrument(NoteBlockInstrument.DRAGON)
                            .requiresTool()
                            .strength(5f, 6f)
                            .sounds(BlockSoundGroup.METAL)
            )
    );

    public static final Block HELLSTONE_BLOCK = registerFireproofBlock("hellstone_block",
            new MagmaBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DARK_RED)
                            .requiresTool()
                            .luminance(state -> 2)
                            .strength(5f, 50f)
                            .allowsSpawning((state, world, pos, type) -> type.isFireImmune())
                            .postProcess(Blocks::always)
                            .sounds(BlockSoundGroup.ANCIENT_DEBRIS)
            )
    );

    public static final Block HELLSTONE_BRICKS = registerFireproofBlock("hellstone_bricks",
            new MagmaBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DARK_RED)
                            .requiresTool()
                            .luminance(state -> 2)
                            .strength(5f, 50f)
                            .allowsSpawning((state, world, pos, type) -> type.isFireImmune())
                            .postProcess(Blocks::always)
                            .sounds(BlockSoundGroup.ANCIENT_DEBRIS)
            )
    );

    public static final Block COBALT_BLOCK = registerFireproofBlock("cobalt_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.LAPIS_BLUE)
                            .instrument(NoteBlockInstrument.GUITAR)
                            .requiresTool()
                            .strength(6f, 7f)
                            .sounds(BlockSoundGroup.GILDED_BLACKSTONE)
            ),
            Rarity.UNCOMMON
    );

    public static final Block ORICHALCUM_BLOCK = registerFireproofBlock("orichalcum_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.PINK)
                            .instrument(NoteBlockInstrument.SNARE)
                            .requiresTool()
                            .strength(6f, 7f)
                            .sounds(BlockSoundGroup.GILDED_BLACKSTONE)
            ),
            Rarity.UNCOMMON
    );

    public static final Block TITANIUM_BLOCK = registerFireproofBlock("titanium_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.IRON_GRAY)
                            .instrument(NoteBlockInstrument.SNARE)
                            .requiresTool()
                            .strength(6f, 7f)
                            .sounds(BlockSoundGroup.GILDED_BLACKSTONE)
            ),
            Rarity.UNCOMMON
    );

    /*public static final Block TITANIUM_ANVIL = registerFireproofBlock("titanium_anvil",
            new IndestructibleAnvilBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
                            .strength(6.5F, 1200.0F)
                            .sounds(BlockSoundGroup.ANVIL)
                            .pistonBehavior(PistonBehavior.BLOCK)
            ),
            Rarity.UNCOMMON
    );*/

    public static final Block HALLOWED_BLOCK = registerFireproofBlock("hallowed_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.PALE_YELLOW)
                            .instrument(NoteBlockInstrument.FLUTE)
                            .requiresTool()
                            .strength(6f, 7f)
                            .sounds(BlockSoundGroup.GILDED_BLACKSTONE)
            ),
            Rarity.UNCOMMON
    );

    public static final Block VERDANT_BLOCK = registerFireproofBlock("verdant_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.EMERALD_GREEN)
                            .instrument(NoteBlockInstrument.CHIME)
                            .requiresTool()
                            .strength(6f, 7f)
                            .sounds(BlockSoundGroup.GILDED_BLACKSTONE)
            ),
            Rarity.RARE
    );

    public static final Block DETONIUM_BLOCK = registerFireproofBlock("detonium_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.BRIGHT_RED)
                            .instrument(NoteBlockInstrument.FLUTE)
                            .requiresTool()
                            .strength(6f, 7f)
                            .sounds(BlockSoundGroup.GILDED_BLACKSTONE)
            ),
            Rarity.RARE
    );

    public static final Block REFINED_AETHERIUM_BLOCK = registerFireproofBlock("refined_aetherium_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.PURPLE)
                            .instrument(NoteBlockInstrument.PLING)
                            .requiresTool()
                            .strength(6f, 7f)
                            .sounds(BlockSoundGroup.GILDED_BLACKSTONE)
            ),
            Rarity.EPIC
    );

    public static final Block AETHERIUM_BRICKS = registerFireproofBlock("aetherium_bricks",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.PURPLE)
                            .instrument(NoteBlockInstrument.PLING)
                            .requiresTool()
                            .strength(6f, 7f)
                            .sounds(BlockSoundGroup.DEEPSLATE_BRICKS)
            ),
            Rarity.EPIC
    );

    public static final Block CINCINNASITE_DIAMOND_BLOCK = registerBlock("cincinnasite_diamond_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DIAMOND_BLUE)
                            .requiresTool()
                            .strength(5.5f, 6.5f)
                            .sounds(BlockSoundGroup.METAL)
            )
    );

    public static final Block TERRASTEEL_BLOCK = registerFireproofBlock("terrasteel_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .requiresTool()
                            .strength(5f, 5f)
                            .sounds(BlockSoundGroup.NETHERITE)
            )
    );
    //endregion

    public static Block registerBlock(String name, Block block, boolean shouldRegister, boolean isFireProof, Rarity rarity, String spoofID) {
        Identifier id = Identifier.of(spoofID, name);

        if (shouldRegister && isFireProof)
            Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings().fireproof().rarity(rarity)));
        else if (shouldRegister)
            Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static Block registerBlock(String name, Block block, boolean shouldRegister, boolean isFireProof, String spoofID) {
        return registerBlock(name, block, shouldRegister, isFireProof, Rarity.COMMON, spoofID);
    }

    public static Block registerFireproofBlock(String name, Block block, boolean shouldRegister) {
        return registerBlock(name, block, shouldRegister, true, Ecraft.MOD_ID);
    }

    public static Block registerBlock(String name, Block block, boolean shouldRegister) {
        return registerBlock(name, block, shouldRegister, false, Ecraft.MOD_ID);
    }

    public static Block registerFireproofBlock(String name, Block block, Rarity rarity) {
        return registerBlock(name, block, true, true, rarity, Ecraft.MOD_ID);
    }

    public static Block registerFireproofBlock(String name, Block block) {
        return registerBlock(name, block, true, true, Ecraft.MOD_ID);
    }

    public static Block registerBlock(String name, Block block) {
        return registerBlock(name, block, true, false, Ecraft.MOD_ID);
    }

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " registering blocks");
    }
}
