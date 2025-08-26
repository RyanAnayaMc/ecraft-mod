package dev.night.ecraft.item;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.item.tools.*;
import dev.night.ecraft.item.tools.weapons.*;
import dev.night.ecraft.tags.ETags;
import dev.night.ecraft.util.ItemList;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.item.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Rarity;

public class EWeapons {
    protected static ItemList<Item> lances = new ItemList<Item>();
    protected static ItemList<Item> gunlances = new ItemList<Item>();
    protected static ItemList<Item> picksaws = new ItemList<Item>();
    protected static ItemList<Item> staves = new ItemList<Item>();

    public static final int SWORD_BASE_DAMAGE = 3;
    public static final float SWORD_BASE_SPEED = -2.4f;
    public static final float SHOVEL_BASE_DAMAGE = 1.5f;
    public static final float SHOVEL_BASE_SPEED = -3f;
    public static final float PICKAXE_BASE_DAMAGE = 1f;
    public static final float PICKAXE_BASE_SPEED = -2.8f;
    public static final float AXE_BASE_DAMAGE = 5f;
    public static final float AXE_BASE_SPEED = -3f;
    public static final float HOE_BASE_ATTACK = -3f;
    public static final float HOE_BASE_SPEED = 0f;
    public static final float STAFF_BASE_ATTACK = -1f;
    public static final float STAFF_BASE_SPEED = -2.2f;
    public static final float STAFF_BASE_MAGIC_DAMAGE = 3.5f;
    public static final float STAFF_UPGRADED_BASE_ATTACK = 3f;
    public static final float STAFF_UPGRADED_BASE_SPEED = -3f;

    public static final float BATTLEAXE_BASE_DAMAGE = 5.5f;
    public static final float BATTLEAXE_BASE_SPEED = -3.1f;
    public static final float SCYTHE_BASE_ATTACK = 6f;
    public static final float SCYTHE_BASE_SPEED = -3f;
    public static final float PICKSAW_BASE_DAMAGE = 3.5f;
    public static final float PICKSAW_BASE_SPEED = -3.5f;
    public static final int LANCE_BASE_DAMAGE = 4;
    public static final float LANCE_BASE_SPEED = -2.8f;
    public static final int GUNLANCE_BASE_DAMAGE = 5;
    public static final float GUNLANCE_BASE_SPEED = -3f;

    public static final Item MEGA_BUSTER = EItems.registerItem("mega_buster", new MegaBusterItem(new Item.Settings()));
    public static final Item X_BUSTER = EItems.registerItem("x_buster", new XBusterItem(new Item.Settings()));

    public static final Item MURAMASA = EItems.registerItem("muramasa", new SpecialtyWeapon.Muramasa());
    public static final Item VOLCANO = EItems.registerItem("volcano", new SpecialtyWeapon.Volcano());
    public static final Item BLADE_OF_GRASS = EItems.registerItem("blade_of_grass", new SpecialtyWeapon.BladeOfGrass());
    public static final Item NIGHTS_EDGE = EItems.registerItem("nights_edge", new SpecialtyWeapon.NightsEdge());
    public static final Item TRUE_NIGHTS_EDGE = EItems.registerItem("true_nights_edge", new SpecialtyWeapon.TrueNightsEdge());

    public static final Item BASTARD_SWORD = EItems.registerItem("bastard_sword", new BastardSword.BaseSword());
    public static final Item CALIBURN = EItems.registerItem("caliburn", new BastardSword.Caliburn());
    public static final Item SOLAIS = EItems.registerItem("solais", new BastardSword.Solais());
    public static final Item DURENDAL = EItems.registerItem("durendal", new BastardSword.Durendal());

    public static final Item WIND_STAFF = EItems.registerItem(
            "staff_of_wind",
            new OriginsStaff.Wind(
                    EToolMaterials.HALLOWED,
                    new Item.Settings()
                            .rarity(Rarity.RARE)
                            .fireproof()
                            .attributeModifiers(OriginsStaff.createAttributeModifiers(EToolMaterials.HALLOWED, STAFF_BASE_ATTACK, STAFF_BASE_SPEED)),
                    55, 100, 1.25F, 5, 20
            )
    );

    public static final Item WIND_STAFF_UPGRADED = EItems.registerItem(
            "staff_of_wind_upgraded",
            new OriginsStaff.Wind(
                    EToolMaterials.FLAWLESS_AETHERIUM,
                    new Item.Settings()
                            .component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))
                            .rarity(Rarity.EPIC)
                            .fireproof()
                            .attributeModifiers(OriginsStaff.createAttributeModifiers(EToolMaterials.FLAWLESS_AETHERIUM, STAFF_UPGRADED_BASE_ATTACK, STAFF_UPGRADED_BASE_SPEED)),
                    40, 40, 1.75f, 8, 30
            )
    );

    public static final Item LIGHTNING_STAFF = EItems.registerItem(
            "staff_of_lightning",
            new OriginsStaff.Lightning(
                    EToolMaterials.HALLOWED,
                    new Item.Settings()
                            .rarity(Rarity.RARE)
                            .fireproof()
                            .attributeModifiers(OriginsStaff.createAttributeModifiers(EToolMaterials.HALLOWED, STAFF_BASE_ATTACK, STAFF_BASE_SPEED)),
                    30, 30, 0.2f, 30, 1.5f, 17.5f
            )
    );

    public static final Item LIGHTNING_STAFF_UPGRADED = EItems.registerItem(
            "staff_of_lightning_upgraded",
            new OriginsStaff.Lightning(
                    EToolMaterials.FLAWLESS_AETHERIUM,
                    new Item.Settings()
                            .component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))
                            .rarity(Rarity.EPIC)
                            .fireproof()
                            .attributeModifiers(OriginsStaff.createAttributeModifiers(EToolMaterials.FLAWLESS_AETHERIUM, STAFF_UPGRADED_BASE_ATTACK, STAFF_UPGRADED_BASE_SPEED)),
                    20, 10, 0.3f, 30, 1.75f, 22.5f
            )
    );

    public static final Item FIRE_STAFF = EItems.registerItem(
            "staff_of_fire",
            new OriginsStaff.Fire(
                    EToolMaterials.HALLOWED,
                    new Item.Settings()
                            .rarity(Rarity.RARE)
                            .fireproof()
                            .attributeModifiers(OriginsStaff.createAttributeModifiers(EToolMaterials.HALLOWED, STAFF_BASE_ATTACK, STAFF_BASE_SPEED)),
                    35, 25, 4f, 15f
            )
    );

    public static final Item FIRE_STAFF_UPGRADED = EItems.registerItem(
            "staff_of_fire_upgraded",
            new OriginsStaff.Fire(
                    EToolMaterials.FLAWLESS_AETHERIUM,
                    new Item.Settings()
                            .component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))
                            .rarity(Rarity.EPIC)
                            .fireproof()
                            .attributeModifiers(OriginsStaff.createAttributeModifiers(EToolMaterials.FLAWLESS_AETHERIUM, STAFF_UPGRADED_BASE_ATTACK, STAFF_UPGRADED_BASE_SPEED)),
                    25, 25, 4.5f, 20f
            )
    );

    public static final Item ICE_STAFF = EItems.registerItem(
            "staff_of_ice",
            new OriginsStaff.Ice(
                    EToolMaterials.HALLOWED,
                    new Item.Settings()
                            .rarity(Rarity.RARE)
                            .fireproof()
                            .attributeModifiers(OriginsStaff.createAttributeModifiers(EToolMaterials.HALLOWED, STAFF_BASE_ATTACK, STAFF_BASE_SPEED)),
                    20, 50, 80, 1, 8, 8
            )
    );

    public static final Item ICE_STAFF_UPGRADED = EItems.registerItem(
            "staff_of_ice_upgraded",
            new OriginsStaff.Ice(
                    EToolMaterials.FLAWLESS_AETHERIUM,
                    new Item.Settings()
                            .component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))
                            .rarity(Rarity.EPIC)
                            .fireproof()
                            .attributeModifiers(OriginsStaff.createAttributeModifiers(EToolMaterials.FLAWLESS_AETHERIUM, STAFF_UPGRADED_BASE_ATTACK, STAFF_UPGRADED_BASE_SPEED)),
                    15, 20, 80, 2, 12, 14
            )
    );

    public static final Item MAGIC_LASER_DRILL = EItems.registerItem(
            "magic_laser_drill",
            new MagicDrillTool()
    );

    public static final Item AETHERIUM_LASER_DRILL = EItems.registerItem(
            "aetherium_laser_drill",
            new AetheriumDrillTool()
    );

    //region Base Picksaws
    public static final Item WOODEN_PICKSAW = picksaws.put(EItems.registerItem("wooden_picksaw", createPicksaw(ToolMaterials.WOOD)));
    public static final Item STONE_PICKSAW = picksaws.put(EItems.registerItem("stone_picksaw", createPicksaw(ToolMaterials.STONE)));
    public static final Item IRON_PICKSAW = picksaws.put(EItems.registerItem("iron_picksaw", createPicksaw(ToolMaterials.IRON)));
    public static final Item GOLDEN_PICKSAW = picksaws.put(EItems.registerItem("golden_picksaw", createPicksaw(ToolMaterials.GOLD)));
    public static final Item DIAMOND_PICKSAW = picksaws.put(EItems.registerItem("diamond_picksaw", createPicksaw(ToolMaterials.DIAMOND)));
    public static final Item CINCINNASITE_DIAMOND_PICKSAW = picksaws.put(EItems.registerItem("cincinnasite_diamond_picksaw", createPicksaw(EToolMaterials.CINCINNASITE_DIAMOND)));
    public static final Item NETHERITE_PICKSAW = picksaws.put(EItems.registerItem("netherite_picksaw", createFireproofPicksaw(ToolMaterials.NETHERITE, Rarity.COMMON)));

    //region Base Lances
    public static final Item WOODEN_LANCE = lances.put(EItems.registerItem("wooden_lance", createLance(ToolMaterials.WOOD)));
    public static final Item STONE_LANCE = lances.put(EItems.registerItem("stone_lance", createLance(ToolMaterials.STONE)));
    public static final Item IRON_LANCE = lances.put(EItems.registerItem("iron_lance", createLance(ToolMaterials.IRON)));
    public static final Item GOLDEN_LANCE = lances.put(EItems.registerItem("golden_lance", createLance(ToolMaterials.GOLD)));
    public static final Item DIAMOND_LANCE = lances.put(EItems.registerItem("diamond_lance", createLance(ToolMaterials.DIAMOND)));
    public static final Item CINCINNASITE_DIAMOND_LANCE = lances.put(EItems.registerItem("cincinnasite_diamond_lance", createLance(EToolMaterials.CINCINNASITE_DIAMOND)));
    public static final Item NETHERITE_LANCE = lances.put(EItems.registerItem("netherite_lance", createFireproofLance(ToolMaterials.NETHERITE, Rarity.COMMON)));
    //endregion

    // Base Staves
    public static final Item IRON_STAFF =
            staves.put(
                EItems.registerItem(
                    "iron_staff",
                    createStaff(
                        ToolMaterials.IRON,
                        ConventionalItemTags.LAPIS_GEMS,
                        EItems.IRON_STAFF_PROJECTILE,
                        5
                    )
                )
            );
    public static final Item GOLDEN_STAFF =
            staves.put(
                    EItems.registerItem(
                            "golden_staff",
                            new StaffItem(
                                    ToolMaterials.GOLD,
                                    ETags.Items.GEMS_RUBY,
                                    new Item.Settings()
                                            .attributeModifiers(
                                                    StaffItem.createAttributeModifiers(
                                                            ToolMaterials.GOLD,
                                                            STAFF_BASE_ATTACK, STAFF_BASE_SPEED, STAFF_BASE_MAGIC_DAMAGE + 3f
                                                    )
                                            ),
                                    EItems.GOLDEN_STAFF_PROJECTILE,
                                    7
                            )
                    )
            );

    //region Gunlances
    public static final Item CINCINNASITE_DIAMOND_GUNLANCE = gunlances.put(EItems.registerItem("cincinnasite_diamond_gunlance",
            new GunlanceItem(
                    EToolMaterials.CINCINNASITE_DIAMOND,
                    new Item.Settings()
                            .attributeModifiers(
                                    LanceItem.createAttributeModifiers(
                                            EToolMaterials.CINCINNASITE_DIAMOND,
                                            GUNLANCE_BASE_DAMAGE, GUNLANCE_BASE_SPEED
                                    )
                            )
            )
    ));

    public static final Item HELLSTONE_GUNLANCE = gunlances.put(EItems.registerItem("hellstone_gunlance",
            new GunlanceItem(
                    EToolMaterials.HELLSTONE,
                    new Item.Settings()
                            .attributeModifiers(
                                    LanceItem.createAttributeModifiers(
                                            EToolMaterials.HELLSTONE,
                                            GUNLANCE_BASE_DAMAGE, GUNLANCE_BASE_SPEED
                                    )
                            ),
                    6
            ) {
                @Override
                public float getShellDamage(ItemStack stack) {
                    return getWeaponDamage(stack) * 1.375f;
                }
            }
    ));

    public static final Item NETHERITE_GUNLANCE = gunlances.put(EItems.registerItem("netherite_gunlance",
            new GunlanceItem(
                    ToolMaterials.NETHERITE,
                    new Item.Settings()
                            .attributeModifiers(
                                    LanceItem.createAttributeModifiers(
                                            ToolMaterials.NETHERITE,
                                            GUNLANCE_BASE_DAMAGE, GUNLANCE_BASE_SPEED
                                    )
                            )
            )
    ));

    public static final Item DETONIUM_GUNLANCE = gunlances.put(EItems.registerItem("detonium_gunlance",
            new GunlanceItem(
                    EToolMaterials.DETONIUM,
                    new Item.Settings()
                            .rarity(Rarity.RARE)
                            .attributeModifiers(
                                    LanceItem.createAttributeModifiers(
                                            EToolMaterials.DETONIUM,
                                            GUNLANCE_BASE_DAMAGE, GUNLANCE_BASE_SPEED
                                    )
                            ),
                    4
            ) {
                @Override
                public float getShellDamage(ItemStack stack) {
                    return getWeaponDamage(stack) * 2f;
                }
            }
    ));
    //endregion

    //region Standard Tools
    public static final Item COPPER_SWORD = EItems.registerItem("copper_sword", createSword(EToolMaterials.COPPER));
    public static final Item COPPER_SHOVEL = EItems.registerItem("copper_shovel", createShovel(EToolMaterials.COPPER));
    public static final Item COPPER_PICKAXE = EItems.registerItem("copper_pickaxe", createPickaxe(EToolMaterials.COPPER));
    public static final Item COPPER_AXE = EItems.registerItem("copper_axe", createAxe(EToolMaterials.COPPER));
    public static final Item COPPER_HOE = EItems.registerItem("copper_hoe", createHoe(EToolMaterials.COPPER));
    public static final Item COPPER_PICKSAW = picksaws.put(EItems.registerItem("copper_picksaw", createPicksaw(EToolMaterials.COPPER)));
    public static final Item COPPER_LANCE = lances.put(EItems.registerItem("copper_lance", createLance(EToolMaterials.COPPER)));
    public static final Item COPPER_STAFF = staves.put(EItems.registerItem("copper_staff", createStaff(EToolMaterials.COPPER, ConventionalItemTags.AMETHYST_GEMS, EItems.COPPER_STAFF_PROJECTILE, 5)));

    public static final Item TUNGSTEN_SWORD = EItems.registerItem("tungsten_sword", createSword(EToolMaterials.TUNGSTEN));
    public static final Item TUNGSTEN_SHOVEL = EItems.registerItem("tungsten_shovel", createShovel(EToolMaterials.TUNGSTEN));
    public static final Item TUNGSTEN_PICKAXE = EItems.registerItem("tungsten_pickaxe", createPickaxe(EToolMaterials.TUNGSTEN));
    public static final Item TUNGSTEN_AXE = EItems.registerItem("tungsten_axe", createAxe(EToolMaterials.TUNGSTEN));
    public static final Item TUNGSTEN_HOE = EItems.registerItem("tungsten_hoe", createHoe(EToolMaterials.TUNGSTEN));
    public static final Item TUNGSTEN_PICKSAW = picksaws.put(EItems.registerItem("tungsten_picksaw", createPicksaw(EToolMaterials.TUNGSTEN)));
    public static final Item TUNGSTEN_LANCE = lances.put(EItems.registerItem("tungsten_lance", createLance(EToolMaterials.TUNGSTEN)));
    public static final Item TUNGSTEN_STAFF = staves.put(EItems.registerItem("tungsten_staff", createStaff(EToolMaterials.TUNGSTEN, ConventionalItemTags.EMERALD_GEMS, EItems.TUNGSTEN_STAFF_PROJECTILE, 4)));

    public static final Item DEMONITE_SWORD = EItems.registerItem("demonite_sword", createSword(EToolMaterials.DEMONITE));
    public static final Item DEMONITE_SHOVEL = EItems.registerItem("demonite_shovel", createShovel(EToolMaterials.DEMONITE));
    public static final Item DEMONITE_PICKAXE = EItems.registerItem("demonite_pickaxe", createPickaxe(EToolMaterials.DEMONITE));
    public static final Item DEMONITE_AXE = EItems.registerItem("demonite_axe", createAxe(EToolMaterials.DEMONITE));
    public static final Item DEMONITE_HOE = EItems.registerItem("demonite_hoe", createHoe(EToolMaterials.DEMONITE));
    public static final Item DEMONITE_PICKSAW = picksaws.put(EItems.registerItem("demonite_picksaw", createPicksaw(EToolMaterials.DEMONITE)));
    public static final Item DEMONITE_LANCE = lances.put(EItems.registerItem("demonite_lance", createLance(EToolMaterials.DEMONITE)));
    public static final Item DEMONITE_STAFF = staves.put(EItems.registerItem("demonite_staff", createStaff(EToolMaterials.DEMONITE, ConventionalItemTags.DIAMOND_GEMS, EItems.DEMONITE_STAFF_PROJECTILE, 6)));

    public static final Item HELLSTONE_SWORD = EItems.registerItem("hellstone_sword",
            new HellstoneTools.Sword(
                    EToolMaterials.HELLSTONE,
                    new Item.Settings()
                            .fireproof()
                            .attributeModifiers(
                                    HellstoneTools.Sword.createAttributeModifiers(
                                            EToolMaterials.HELLSTONE,
                                            SWORD_BASE_DAMAGE, SWORD_BASE_SPEED
                                    )
                            )
            )
    );
    public static final Item HELLSTONE_SHOVEL = EItems.registerItem("hellstone_shovel",
            new HellstoneTools.Shovel(
                    EToolMaterials.HELLSTONE,
                    new Item.Settings()
                            .fireproof()
                            .attributeModifiers(
                                    HellstoneTools.Shovel.createAttributeModifiers(
                                            EToolMaterials.HELLSTONE,
                                            SHOVEL_BASE_DAMAGE, SHOVEL_BASE_SPEED
                                    )
                            )
            )
    );
    public static final Item HELLSTONE_PICKAXE = EItems.registerItem("hellstone_pickaxe",
            new HellstoneTools.Pickaxe(
                    EToolMaterials.HELLSTONE,
                    new Item.Settings()
                            .fireproof()
                            .attributeModifiers(
                                    PickaxeItem.createAttributeModifiers(
                                            EToolMaterials.HELLSTONE,
                                            PICKAXE_BASE_DAMAGE, PICKAXE_BASE_SPEED
                                    )
                            )
            )
    );
    public static final Item HELLSTONE_AXE = EItems.registerItem("hellstone_axe",
            new HellstoneTools.Axe(
                    EToolMaterials.HELLSTONE,
                    new Item.Settings()
                            .fireproof()
                            .attributeModifiers(
                                    HellstoneTools.Axe.createAttributeModifiers(
                                            EToolMaterials.HELLSTONE,
                                            AXE_BASE_DAMAGE, AXE_BASE_SPEED
                                    )
                            )
            )
    );
    public static final Item HELLSTONE_HOE = EItems.registerItem("hellstone_hoe",
            new HellstoneTools.Hoe(
                    EToolMaterials.HELLSTONE,
                    new Item.Settings()
                            .fireproof()
                            .attributeModifiers(
                                    HellstoneTools.Hoe.createAttributeModifiers(
                                            EToolMaterials.HELLSTONE,
                                            HOE_BASE_ATTACK, HOE_BASE_SPEED
                                    )
                            )
            )
    );
    public static final Item HELLSTONE_LANCE = lances.put(EItems.registerItem("hellstone_lance",
            new HellstoneTools.Lance(
                    EToolMaterials.HELLSTONE,
                    new Item.Settings()
                            .fireproof()
                            .attributeModifiers(
                                    HellstoneTools.Lance.createAttributeModifiers(
                                            EToolMaterials.HELLSTONE,
                                            LANCE_BASE_DAMAGE, LANCE_BASE_SPEED
                                    )
                            )
            )
    ));
    public static final Item HELLSTONE_PICKSAW = picksaws.put(EItems.registerItem("hellstone_picksaw",
            new HellstoneTools.Picksaw(
                    EToolMaterials.HELLSTONE,
                    new Item.Settings()
                            .fireproof()
                            .attributeModifiers(
                                    HellstoneTools.Picksaw.createAttributeModifiers(
                                            EToolMaterials.HELLSTONE,
                                            PICKSAW_BASE_DAMAGE, PICKSAW_BASE_SPEED
                                    )
                            )
            )
    ));

    public static final Item HELLSTONE_STAFF = staves.put(EItems.registerItem("hellstone_staff",
            new HellstoneTools.Staff(
                    EToolMaterials.HELLSTONE,
                    ConventionalItemTags.QUARTZ_GEMS,
                    new Item.Settings()
                            .fireproof()
                            .attributeModifiers(
                                    HellstoneTools.Staff.createAttributeModifiers(
                                            EToolMaterials.HELLSTONE,
                                            STAFF_BASE_ATTACK, STAFF_BASE_SPEED, STAFF_BASE_MAGIC_DAMAGE
                                    )
                            ),
                    EItems.HELLSTONE_STAFF_PROJECTILE,
                    7
            )
    ));

    public static final Item COBALT_SWORD = EItems.registerItem("cobalt_sword", createFireproofSword(EToolMaterials.COBALT, Rarity.UNCOMMON));
    public static final Item COBALT_SHOVEL = EItems.registerItem("cobalt_shovel", createFireproofShovel(EToolMaterials.COBALT, Rarity.UNCOMMON));
    public static final Item COBALT_PICKAXE = EItems.registerItem("cobalt_pickaxe", createFireproofPickaxe(EToolMaterials.COBALT, Rarity.UNCOMMON));
    public static final Item COBALT_BATTLEAXE = EItems.registerItem("cobalt_battleaxe", createFireproofBattleaxe(EToolMaterials.COBALT, Rarity.UNCOMMON));
    public static final Item COBALT_SCYTHE = EItems.registerItem("cobalt_scythe", createFireproofScythe(EToolMaterials.COBALT, Rarity.UNCOMMON));
    public static final Item COBALT_LANCE = lances.put(EItems.registerItem("cobalt_lance", createFireproofLance(EToolMaterials.COBALT, Rarity.UNCOMMON)));
    public static final Item COBALT_PICKSAW = picksaws.put(EItems.registerItem("cobalt_picksaw", createFireproofPicksaw(EToolMaterials.COBALT, Rarity.UNCOMMON)));

    public static final Item ORICHALCUM_SWORD = EItems.registerItem("orichalcum_sword", createFireproofSword(EToolMaterials.ORICHALCUM, Rarity.UNCOMMON));
    public static final Item ORICHALCUM_SHOVEL = EItems.registerItem("orichalcum_shovel", createFireproofShovel(EToolMaterials.ORICHALCUM, Rarity.UNCOMMON));
    public static final Item ORICHALCUM_PICKAXE = EItems.registerItem("orichalcum_pickaxe", createFireproofPickaxe(EToolMaterials.ORICHALCUM, Rarity.UNCOMMON));
    public static final Item ORICHALCUM_BATTLEAXE = EItems.registerItem("orichalcum_battleaxe", createFireproofBattleaxe(EToolMaterials.ORICHALCUM, Rarity.UNCOMMON));
    public static final Item ORICHALCUM_SCYTHE = EItems.registerItem("orichalcum_scythe", createFireproofScythe(EToolMaterials.ORICHALCUM, Rarity.UNCOMMON));
    public static final Item ORICHALCUM_LANCE = lances.put(EItems.registerItem("orichalcum_lance", createFireproofLance(EToolMaterials.ORICHALCUM, Rarity.UNCOMMON)));
    public static final Item ORICHALCUM_PICKSAW = picksaws.put(EItems.registerItem("orichalcum_picksaw", createFireproofPicksaw(EToolMaterials.ORICHALCUM, Rarity.UNCOMMON)));

    public static final Item TITANIUM_SWORD = EItems.registerItem("titanium_sword", createFireproofSword(EToolMaterials.TITANIUM, Rarity.UNCOMMON));
    public static final Item TITANIUM_SHOVEL = EItems.registerItem("titanium_shovel", createFireproofShovel(EToolMaterials.TITANIUM, Rarity.UNCOMMON));
    public static final Item TITANIUM_PICKAXE = EItems.registerItem("titanium_pickaxe", createFireproofPickaxe(EToolMaterials.TITANIUM, Rarity.UNCOMMON));
    public static final Item TITANIUM_BATTLEAXE = EItems.registerItem("titanium_battleaxe", createFireproofBattleaxe(EToolMaterials.TITANIUM, Rarity.UNCOMMON));
    public static final Item TITANIUM_SCYTHE = EItems.registerItem("titanium_scythe", createFireproofScythe(EToolMaterials.TITANIUM, Rarity.UNCOMMON));
    public static final Item TITANIUM_LANCE = lances.put(EItems.registerItem("titanium_lance", createFireproofLance(EToolMaterials.TITANIUM, Rarity.UNCOMMON)));
    public static final Item TITANIUM_PICKSAW = picksaws.put(EItems.registerItem("titanium_picksaw", createFireproofPicksaw(EToolMaterials.TITANIUM, Rarity.UNCOMMON)));

    public static final Item HALLOWED_SWORD = EItems.registerItem("hallowed_sword", createFireproofSword(EToolMaterials.HALLOWED, Rarity.UNCOMMON));
    public static final Item HALLOWED_SHOVEL = EItems.registerItem("hallowed_shovel", createFireproofShovel(EToolMaterials.HALLOWED, Rarity.UNCOMMON));
    public static final Item HALLOWED_PICKAXE = EItems.registerItem("hallowed_pickaxe", createFireproofPickaxe(EToolMaterials.HALLOWED, Rarity.UNCOMMON));
    public static final Item HALLOWED_BATTLEAXE = EItems.registerItem("hallowed_battleaxe", createFireproofBattleaxe(EToolMaterials.HALLOWED, Rarity.UNCOMMON));
    public static final Item HALLOWED_SCYTHE = EItems.registerItem("hallowed_scythe", createFireproofScythe(EToolMaterials.HALLOWED, Rarity.UNCOMMON));
    public static final Item HALLOWED_LANCE = lances.put(EItems.registerItem("hallowed_lance", createFireproofLance(EToolMaterials.HALLOWED, Rarity.UNCOMMON)));
    public static final Item HALLOWED_PICKSAW = picksaws.put(EItems.registerItem("hallowed_picksaw", createFireproofPicksaw(EToolMaterials.HALLOWED, Rarity.UNCOMMON)));

    public static final Item VERDANT_SWORD = EItems.registerItem("verdant_sword", createFireproofSword(EToolMaterials.VERDANT, Rarity.RARE));
    public static final Item VERDANT_SHOVEL = EItems.registerItem("verdant_shovel", createFireproofShovel(EToolMaterials.VERDANT, Rarity.RARE));
    public static final Item VERDANT_PICKAXE = EItems.registerItem("verdant_pickaxe", createFireproofPickaxe(EToolMaterials.VERDANT, Rarity.RARE));
    public static final Item VERDANT_BATTLEAXE = EItems.registerItem("verdant_battleaxe", createFireproofBattleaxe(EToolMaterials.VERDANT, Rarity.RARE));
    public static final Item VERDANT_SCYTHE = EItems.registerItem("verdant_scythe", createFireproofScythe(EToolMaterials.VERDANT, Rarity.RARE));
    public static final Item VERDANT_LANCE = lances.put(EItems.registerItem("verdant_lance", createFireproofLance(EToolMaterials.VERDANT, Rarity.RARE)));
    public static final Item VERDANT_PICKSAW = picksaws.put(EItems.registerItem("verdant_picksaw", createFireproofPicksaw(EToolMaterials.VERDANT, Rarity.RARE)));

    public static final Item DETONIUM_BLASTAXE = EItems.registerItem("detonium_blastaxe",
            new DetoniumTools.Blastaxe(
                    EToolMaterials.DETONIUM,
                    new Item.Settings()
                            .fireproof()
                            .rarity(Rarity.RARE)
                            .attributeModifiers(
                                    DetoniumTools.Blastaxe.createAttributeModifiers(
                                            EToolMaterials.DETONIUM,
                                            PICKSAW_BASE_DAMAGE, PICKSAW_BASE_SPEED
                                    )
                            )
            )
    );

    public static final Item DETONIUM_SWORD = EItems.registerItem("detonium_sword",
            new DetoniumTools.Sword(
                    EToolMaterials.DETONIUM,
                    new Item.Settings()
                            .fireproof()
                            .rarity(Rarity.RARE)
                            .attributeModifiers(
                                    DetoniumTools.Sword.createAttributeModifiers(
                                            EToolMaterials.DETONIUM,
                                            SWORD_BASE_DAMAGE, SWORD_BASE_SPEED
                                    )
                            )
            )
    );
    public static final Item DETONIUM_SHOVEL = EItems.registerItem("detonium_shovel",
            new DetoniumTools.Shovel(
                    EToolMaterials.DETONIUM,
                    new Item.Settings()
                            .fireproof()
                            .rarity(Rarity.RARE)
                            .attributeModifiers(
                                    DetoniumTools.Shovel.createAttributeModifiers(
                                            EToolMaterials.DETONIUM,
                                            SHOVEL_BASE_DAMAGE, SHOVEL_BASE_SPEED
                                    )
                            )
            )
    );
    public static final Item DETONIUM_PICKAXE = EItems.registerItem("detonium_pickaxe",
            new DetoniumTools.Pickaxe(
                    EToolMaterials.DETONIUM,
                    new Item.Settings()
                            .fireproof()
                            .rarity(Rarity.RARE)
                            .attributeModifiers(
                                    PickaxeItem.createAttributeModifiers(
                                            EToolMaterials.DETONIUM,
                                            PICKAXE_BASE_DAMAGE, PICKAXE_BASE_SPEED
                                    )
                            )
            )
    );
    public static final Item DETONIUM_BATTLEAXE = EItems.registerItem("detonium_battleaxe",
            new DetoniumTools.Battleaxe(
                    EToolMaterials.DETONIUM,
                    new Item.Settings()
                            .fireproof()
                            .rarity(Rarity.RARE)
                            .attributeModifiers(
                                    DetoniumTools.Battleaxe.createAttributeModifiers(
                                            EToolMaterials.DETONIUM,
                                            BATTLEAXE_BASE_DAMAGE, BATTLEAXE_BASE_SPEED
                                    )
                            )
            )
    );
    public static final Item DETONIUM_SCYTHE = EItems.registerItem("detonium_scythe",
            new DetoniumTools.Scythe(
                    EToolMaterials.DETONIUM,
                    new Item.Settings()
                            .fireproof()
                            .rarity(Rarity.RARE)
                            .attributeModifiers(
                                    DetoniumTools.Scythe.createAttributeModifiers(
                                            EToolMaterials.DETONIUM,
                                            SCYTHE_BASE_ATTACK, SCYTHE_BASE_SPEED
                                    )
                            )
            )
    );
    public static final Item DETONIUM_LANCE = lances.put(EItems.registerItem("detonium_lance",
            new DetoniumTools.Lance(
                    EToolMaterials.DETONIUM,
                    new Item.Settings()
                            .fireproof()
                            .rarity(Rarity.RARE)
                            .attributeModifiers(
                                    DetoniumTools.Lance.createAttributeModifiers(
                                            EToolMaterials.DETONIUM,
                                            LANCE_BASE_DAMAGE, LANCE_BASE_SPEED
                                    )
                            )
            )
    ));
    public static final Item DETONIUM_PICKSAW = picksaws.put(EItems.registerItem("detonium_picksaw",
            new DetoniumTools.Picksaw(
                    EToolMaterials.DETONIUM,
                    new Item.Settings()
                            .fireproof()
                            .rarity(Rarity.RARE)
                            .attributeModifiers(
                                    DetoniumTools.Picksaw.createAttributeModifiers(
                                            EToolMaterials.DETONIUM,
                                            PICKSAW_BASE_DAMAGE, PICKSAW_BASE_SPEED
                                    )
                            )
            )
    ));

    /* Removed Terrasteel Gear
    public static final Item TERRASTEEL_SWORD = EItems.registerItem("terrasteel_sword", createSword(EToolMaterials.TERRASTEEL));
    public static final Item TERRASTEEL_SHOVEL = EItems.registerItem("terrasteel_shovel", createShovel(EToolMaterials.TERRASTEEL));
    public static final Item TERRASTEEL_PICKAXE = EItems.registerItem("terrasteel_pickaxe", createPickaxe(EToolMaterials.TERRASTEEL));
    public static final Item TERRASTEEL_AXE = EItems.registerItem("terrasteel_axe", createAxe(EToolMaterials.TERRASTEEL));
    public static final Item TERRASTEEL_HOE = EItems.registerItem("terrasteel_hoe", createHoe(EToolMaterials.TERRASTEEL));
     */
    //endregion

    //region Creator Methods
    public static PicksawItem createPicksaw(ToolMaterial material) {
        return new PicksawItem(
                material,
                new Item.Settings()
                        .attributeModifiers(
                                PicksawItem.createAttributeModifiers(material,
                                        PICKSAW_BASE_DAMAGE, PICKSAW_BASE_SPEED
                                )
                        )
        );
    }

    public static LanceItem createLance(ToolMaterial material) {
        return new LanceItem(
                material,
                new Item.Settings()
                        .attributeModifiers(
                                LanceItem.createAttributeModifiers(
                                        material,
                                        LANCE_BASE_DAMAGE, LANCE_BASE_SPEED
                                )
                        )
        );
    }

    public static StaffItem createStaff(ToolMaterial material, TagKey<Item> gemItem, Item projectileItem, int mana) {
        return new StaffItem(
                material,
                gemItem,
                new Item.Settings()
                        .attributeModifiers(
                                StaffItem.createAttributeModifiers(
                                        material,
                                        STAFF_BASE_ATTACK, STAFF_BASE_SPEED, STAFF_BASE_MAGIC_DAMAGE
                                )
                        ),
                projectileItem,
                mana
        );
    }

    public static SwordItem createSword(ToolMaterial material) {
        return new SwordItem(
                material,
                new Item.Settings()
                        .attributeModifiers(
                                SwordItem.createAttributeModifiers(
                                        material,
                                        SWORD_BASE_DAMAGE, SWORD_BASE_SPEED
                                )
                        )
        );
    }

    public static ShovelItem createShovel(ToolMaterial material) {
        return new ShovelItem(
                material,
                new Item.Settings()
                        .attributeModifiers(
                                ShovelItem.createAttributeModifiers(
                                        material,
                                        SHOVEL_BASE_DAMAGE, SHOVEL_BASE_SPEED
                                )
                        )
        );
    }

    public static PickaxeItem createPickaxe(ToolMaterial material) {
        return new PickaxeItem(
                material,
                new Item.Settings()
                        .attributeModifiers(
                                PickaxeItem.createAttributeModifiers(
                                        material,
                                        PICKAXE_BASE_DAMAGE, PICKAXE_BASE_SPEED
                                )
                        )
        );
    }

    public static AxeItem createAxe(ToolMaterial material) {
        return new AxeItem(
                material,
                new Item.Settings()
                        .attributeModifiers(
                                AxeItem.createAttributeModifiers(
                                        material,
                                        AXE_BASE_DAMAGE, AXE_BASE_SPEED
                                )
                        )
        );
    }

    public static HoeItem createHoe(ToolMaterial material) {
        return new HoeItem(
                material,
                new Item.Settings()
                        .attributeModifiers(
                                HoeItem.createAttributeModifiers(
                                        material,
                                        HOE_BASE_ATTACK, HOE_BASE_SPEED
                                )
                        )
        );
    }

    public static BattleaxeItem createBattleaxe(ToolMaterial material) {
        return new BattleaxeItem(
                material,
                new Item.Settings()
                        .attributeModifiers(
                                BattleaxeItem.createAttributeModifiers(
                                        material,
                                        BATTLEAXE_BASE_DAMAGE, BATTLEAXE_BASE_SPEED
                                )
                        )
        );
    }

    public static ScytheItem createScythe(ToolMaterial material) {
        return new ScytheItem(
                material,
                new Item.Settings()
                        .attributeModifiers(
                                ScytheItem.createAttributeModifiers(
                                        material,
                                        SCYTHE_BASE_ATTACK, SCYTHE_BASE_SPEED
                                )
                        )
        );
    }

    public static PicksawItem createFireproofPicksaw(ToolMaterial material, Rarity rarity) {
        return new PicksawItem(
                material,
                new Item.Settings()
                        .fireproof()
                        .rarity(rarity)
                        .attributeModifiers(
                                PicksawItem.createAttributeModifiers(material,
                                        PICKSAW_BASE_DAMAGE, PICKSAW_BASE_SPEED
                                )
                        )
        );
    }

    public static LanceItem createFireproofLance(ToolMaterial material, Rarity rarity) {
        return new LanceItem(
                material,
                new Item.Settings()
                        .fireproof()
                        .rarity(rarity)
                        .attributeModifiers(
                                LanceItem.createAttributeModifiers(
                                        material,
                                        LANCE_BASE_DAMAGE, LANCE_BASE_SPEED
                                )
                        )
        );
    }

    public static SwordItem createFireproofSword(ToolMaterial material, Rarity rarity) {
        return new SwordItem(
                material,
                new Item.Settings()
                        .fireproof()
                        .rarity(rarity)
                        .attributeModifiers(
                                SwordItem.createAttributeModifiers(
                                        material,
                                        SWORD_BASE_DAMAGE, SWORD_BASE_SPEED
                                )
                        )
        );
    }

    public static ShovelItem createFireproofShovel(ToolMaterial material, Rarity rarity) {
        return new ShovelItem(
                material,
                new Item.Settings()
                        .fireproof()
                        .rarity(rarity)
                        .attributeModifiers(
                                ShovelItem.createAttributeModifiers(
                                        material,
                                        SHOVEL_BASE_DAMAGE, SHOVEL_BASE_SPEED
                                )
                        )
        );
    }

    public static PickaxeItem createFireproofPickaxe(ToolMaterial material, Rarity rarity) {
        return new PickaxeItem(
                material,
                new Item.Settings()
                        .fireproof()
                        .rarity(rarity)
                        .attributeModifiers(
                                PickaxeItem.createAttributeModifiers(
                                        material,
                                        PICKAXE_BASE_DAMAGE, PICKAXE_BASE_SPEED
                                )
                        )
        );
    }

    public static AxeItem createFireproofAxe(ToolMaterial material, Rarity rarity) {
        return new AxeItem(
                material,
                new Item.Settings()
                        .fireproof()
                        .rarity(rarity)
                        .attributeModifiers(
                                AxeItem.createAttributeModifiers(
                                        material,
                                        AXE_BASE_DAMAGE, AXE_BASE_SPEED
                                )
                        )
        );
    }

    public static HoeItem createFireproofHoe(ToolMaterial material, Rarity rarity) {
        return new HoeItem(
                material,
                new Item.Settings()
                        .attributeModifiers(
                                HoeItem.createAttributeModifiers(
                                        material,
                                        HOE_BASE_ATTACK, HOE_BASE_SPEED
                                )
                        )
        );
    }

    public static BattleaxeItem createFireproofBattleaxe(ToolMaterial material, Rarity rarity) {
        return new BattleaxeItem(
                material,
                new Item.Settings()
                        .fireproof()
                        .rarity(rarity)
                        .attributeModifiers(
                                BattleaxeItem.createAttributeModifiers(
                                        material,
                                        BATTLEAXE_BASE_DAMAGE, BATTLEAXE_BASE_SPEED
                                )
                        )
        );
    }

    public static ScytheItem createFireproofScythe(ToolMaterial material, Rarity rarity) {
        return new ScytheItem(
                material,
                new Item.Settings()
                        .fireproof()
                        .rarity(rarity)
                        .attributeModifiers(
                                ScytheItem.createAttributeModifiers(
                                        material,
                                        SCYTHE_BASE_ATTACK, SCYTHE_BASE_SPEED
                                )
                        )
        );
    }
    //endregion

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " initializing weapons");
    }
}
