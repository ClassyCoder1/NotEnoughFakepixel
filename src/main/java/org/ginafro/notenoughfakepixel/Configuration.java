package org.ginafro.notenoughfakepixel;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.core.OneColor;
import cc.polyfrost.oneconfig.config.data.*;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import org.ginafro.notenoughfakepixel.config.pages.MiningOverlayPage;
import org.ginafro.notenoughfakepixel.config.pages.SecretOverlayPage;
import org.ginafro.notenoughfakepixel.features.skyblock.dungeons.SecretOverlay;
import org.ginafro.notenoughfakepixel.features.skyblock.mining.MiningOverlay;
import org.ginafro.notenoughfakepixel.features.skyblock.mining.RemoveGhostInvis;

public class Configuration extends Config {

    // Categories
    private transient static final String DUNGEONS = "Dungeons";
    private transient static final String MINING = "Mining";
    private transient static final String FISHING = "Fishing";
    private transient static final String QUALITY_OF_LIFE = "Quality of Life";
    private transient static final String SLAYER = "Slayer";

    public Configuration(){
        super(new Mod("NotEnoughFakepixel", ModType.UTIL_QOL, "assets/notenoughfakepixel/logo.png"), "config.json");
        initialize();

        //this.addDependency("debug", "Cant be enabled with debug2", () -> !debug2);
        //this.addDependency("debug2", "Cant be enabled with debug", () -> !debug);

        this.addListener("dmgFormatter", () -> {
            if (dmgFormatter) {
                dmgCommas = true;
            }
        });

        this.addListener("showGhosts", () -> {
            if (!showGhosts) {
                RemoveGhostInvis.resetGhostInvis();
            }
        });

        this.addListener("disableRain", () -> {
            if (disableRain) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(
                        new ChatComponentText("[\u00a7eNEF\u00a7r]\u00a7c You might need to change lobby for the rain to disappear.")
                );
            }
        });

        this.addListener("_debug", () -> _debug2 = false);
        this.addListener("_debug2", () -> _debug = false);

        save();
    }

    @Override
    public boolean supportsProfiles() {
        return true;
    }

    @Header(text = "General" , size = 2)
    public boolean _general = true;
    @Dropdown(name = "Theme" , size = 2 , options = {"Default" , "Dark" , "Ocean"})
    public static int theme = 1;

    // Quality Of Life

    @Header(text = "General" , category = QUALITY_OF_LIFE, size = 2)
    public boolean _qol = true;
    @Switch(name = "Disable Hyperion Explosion" , category = QUALITY_OF_LIFE)
    public static boolean disableHyperionExplosions = true;
    @Switch(name = "Disable Thunderlord Bolt" , category = QUALITY_OF_LIFE)
    public static boolean disableThunderlordBolt = true;
    @Switch(name = "Fullbright" , category = QUALITY_OF_LIFE)
    public static boolean fullbright = true;
    @Switch(name = "No Hurt Camera" , category = QUALITY_OF_LIFE)
    public static boolean noHurtCam = true;
    @Switch(name = "1.12 Crops height" , category = QUALITY_OF_LIFE)
    public static boolean cropsHeight = false;
    @Switch(name = "Disable Potion Effects in Inventory" , category = QUALITY_OF_LIFE)
    public static boolean disablePotionEffects = true;
    @Switch(name = "Disable rain" , category = QUALITY_OF_LIFE, description = "Disables rain rendering")
    public static boolean disableRain = true;

    @Switch(name = "Show best upgrade" , category = QUALITY_OF_LIFE, subcategory = "Chocolate Factory")
    public static boolean showBestUpgrade = true;

    @Switch(name = "Show pet equipped" , category = QUALITY_OF_LIFE, subcategory = "Pets")
    public static boolean showPetEquipped = true;
    @Color(name = "Pet Equipped Color", category = QUALITY_OF_LIFE, subcategory = "Pets")
    public static OneColor petEquippedColor = new OneColor(190, 255, 190);

    @Switch(name = "Disable Watchdog & Info messages" , category = QUALITY_OF_LIFE, subcategory = "Chat")
    public static boolean disableWatchdogInfo = false;
    @Switch(name = "Disable Friend > joined/left message" , category = QUALITY_OF_LIFE, subcategory = "Chat")
    public static boolean disableFriendJoin = false;
    //@Switch(name = "Chat Cleaner" , category = "Quality of Life", subcategory = "Chat")
    public static boolean chatCleaner = false;
    @Switch(name = "Disable 'Selling ranks' messages" , category = QUALITY_OF_LIFE, subcategory = "Chat")
    public static boolean disableSellingRanks = false;
    @Switch(name = "Scrollable tooltips" , category = QUALITY_OF_LIFE, subcategory = "Items", description = "Scroll through the item lore")
    public static boolean scrollableTooltips = true;
  
    @Switch(name = "Disable Jerry-chine Gun sounds" , category = "Quality of Life", subcategory = "Sounds", description = "Disable Jerry-chine gun sounds.")
    public static boolean disableJerryChineGunSounds = true;
    @Switch(name = "Disable AOTE teleport sounds" , category = "Quality of Life", subcategory = "Sounds", description = "Disable Aspect of the End teleport sounds.")
    public static boolean disableAoteSounds = false;

    @Switch(name = "Damage Commas" , category = QUALITY_OF_LIFE, subcategory = "Damage Formatter")
    public static boolean dmgCommas = false;
    @Switch(name = "Damage Formatter" , category = QUALITY_OF_LIFE, subcategory = "Damage Formatter" , description = "Formats the damage. (ie. 167k instead of 167000)")
    public static boolean dmgFormatter = false;

    // Dungeons

    @Header(text = "Dungeons", category = DUNGEONS, size = 2)
    public boolean _dungeons = true;

    @Switch(name = "Auto Ready Dungeon" , category = DUNGEONS, subcategory = "QOL", description = "Automatically ready up in dungeons.")
    public static boolean autoReadyDungeon = true;
    @Text(name = "Auto Ready Name (if nicked)" , category = DUNGEONS, subcategory = "QOL", description = "The name this will search for when you are nicked")
    public static String autoReadyName = "your nicked name";
    @Switch(name = "Auto close chests", category = DUNGEONS, subcategory = "QOL", description = "Automatically closes chests in dungeons.")
    public static boolean autoCloseChests = true;
    @Switch(name = "Auto drop useless items", category = DUNGEONS, subcategory = "QOL", description = "Automatically drop training weights and defuse kits from dungeon secrets.")
    public static boolean autoDropItems = true;

    @Switch(name = "Starred Mobs Helper" , category = DUNGEONS, subcategory = "Starred Mobs")
    public static boolean starredMobs = true;
    @Color(name = "Starred Mobs Color", category = DUNGEONS, subcategory = "Starred Mobs")
    public static OneColor starredBoxColor = new OneColor(92, 154, 255);
    @Switch(name = "Bat Mobs Display" , category = DUNGEONS, subcategory = "Starred Mobs")
    public static boolean batMobs = true;
    @Color(name = "Bat Mob Color", category = DUNGEONS, subcategory = "Starred Mobs")
    public static OneColor batColor = new OneColor(92, 154, 255);
    @Switch(name = "Fel Mobs Display" , category = DUNGEONS, subcategory = "Starred Mobs")
    public static boolean felMob = true;
    @Color(name = "Fel Mob Color", category = DUNGEONS, subcategory = "Starred Mobs")
    public static OneColor felColor = new OneColor(92, 154, 255);

    @Switch(name = "Three Weirdos Solver" , category = DUNGEONS, subcategory = "Puzzles")
    public static boolean threeWeirdos = true;

    @Switch(name = "Dungeons Map" , category = DUNGEONS, subcategory = "Dungeon Map")
    public static boolean dungeonsMap = true;
    @Slider(name = "Dungeons Map Scale" ,category = DUNGEONS , subcategory = "Dungeon Map" , min = 0.1f,max=10f)
    public static float dungeonMapScale = 1.0f;

    @Switch(name = "Starts With Solver" , category = DUNGEONS , subcategory = "Floor 7")
    public static boolean startsWith = true;
    //@Switch(name = "Click In Order Solver" , category = DUNGEONS , subcategory = "Floor 7")
    public static boolean clickInOrder = true;
    @Switch(name = "Select colors Solver" , category = DUNGEONS , subcategory = "Floor 7")
    public static boolean selectColors = true;
    @Switch(name = "Middle click on terminals and Enchanting" , category = QUALITY_OF_LIFE, description = "Middle clicks on terminals and enchanting gui.")
    public static boolean middleClickChests = true;

    @Color(name = "Terminal Overlay Color", category = DUNGEONS , subcategory = "Floor 7")
    public static OneColor terminalColor = new OneColor(0,255,0);

    @Switch(name = "Secret % Overlay" , category = DUNGEONS, subcategory = "Secrets")
    public static boolean secretOverlay = true;
    @Switch(name = "S+ Reminder" , category = DUNGEONS, subcategory = "Secrets", description = "Shows an S+ in screen when you have 100% secrets and 100% cleared.")
    public static boolean sPlusReminder = true;
    @Page(name = "Secret Overlay Options" , category = DUNGEONS, subcategory = "Secrets", location = PageLocation.BOTTOM)
    public static SecretOverlayPage secretOverlayPage = new SecretOverlayPage();

    // Fishing

    @Header(text = FISHING , category = FISHING , size = 2)
    public boolean _fishing = true;
    @Switch(name = "Notify Legendary Creatures" , category = FISHING, description = "Notifies you when a legendary creature is catched.")
    public static boolean legendaryCreatures = true;
    //@Switch(name = "Notify on Great Catch (not working?)" , category = FISHING)
    public static boolean greatCatch = true;
    @Switch(name = "Notify on Trophy Fish" , category = FISHING)
    public static boolean trophyFish = true;

    // Diana
    @Header(text = "Diana" , category = "Diana" , size = 2)
    public boolean _diana = true;
    @Switch(name = "Show Waypoints on Burrows" , category = "Diana", subcategory = "Waypoints")
    public static boolean dianaShowWaypointsBurrows = true;
    @Color(name = "Empty Burrow Color", category = "Diana", subcategory = "Waypoints")
    public static OneColor emptyBurrowColor = new OneColor(0, 0, 255);
    @Color(name = "Mob Burrow Color", category = "Diana", subcategory = "Waypoints")
    public static OneColor mobBurrowColor = new OneColor(255, 255, 255);
    @Color(name = "Treasure Burrow Color", category = "Diana", subcategory = "Waypoints")
    public static OneColor treasureBurrowColor = new OneColor(255, 0, 0);
    @Switch(name = "Track Gaia Hits" , category = "Diana", subcategory = "Mobs", description = "Turn off/on hitbox that represents when Gaia Construct can be damaged.")
    public static boolean dianaGaiaConstruct = true;
    @Color(name = "Gaia Hittable Color", category = "Diana", subcategory = "Mobs")
    public static OneColor gaiaHittableColor = new OneColor(0, 255, 0);
    @Switch(name = "Show Hittable Siamese" , category = "Diana", subcategory = "Mobs", description = "Turn off/on hitbox that represents which siamese can be damaged.")
    public static boolean dianaSiamese = true;
    @Color(name = "Gaia Un-hittable Color", category = "Diana", subcategory = "Mobs")
    public static OneColor gaiaUnhittableColor = new OneColor(255, 0, 0);
    // Minos inquisitor /pc feature
    @Info(text = "Minos Inquisitor feature soon", type = InfoType.INFO , category = "Diana", subcategory = "Mobs")
    public static boolean _info2 = true;
    @Color(name = "Siamese Hittable Color", category = "Diana", subcategory = "Mobs")
    public static OneColor siameseHittableColor = new OneColor(0, 255, 0);
    @Switch(name = "Waypoint Sounds" , category = "Diana", subcategory = "Sounds", description = "Turn off/on waypoints creation sounds.")
    public static boolean dianaWaypointSounds = true;
    @Switch(name = "Disable Explosion Sounds" , category = "Diana", subcategory = "Sounds", description = "Disable explosion sounds generated by digging a burrow.")
    public static boolean disableDianaExplosionSounds = false;
    @Switch(name = "Auto-equip Ancestral Spade" , category = "Diana", subcategory = "Misc", description = "When clicking burrow, equips Ancestral Spade automatically if its in hotbar")
    public static boolean dianaAutoEquipAncestralSpade = true;

    //@Switch(name = "Disable harp sounds" , category = "Diana", subcategory = "Sounds", description = "Turn off/on harp sounds.")
    //public static boolean disableDianaHarpSounds = true;




    // Slayer

    @Header(text = SLAYER , category = SLAYER , size = 2)
    public boolean _slayer = true;

    @Switch(name = "Slayer Minibosses Display" , category = SLAYER , subcategory = "Slayer Mobs", description = "Draws a box around slayer minibosses.")
    public static boolean slayerMinibosses = true;
    @Color(name = "Slayer Minibosses Color", category = SLAYER , subcategory = "Slayer Mobs", description = "Color of the slayer minibosses.")
    public static OneColor slayerColor = new OneColor(92, 154, 255);
    @Switch(name = "Slayer Bosses Display" , category = SLAYER , subcategory = "Slayer Mobs", description = "Draws a box around slayer bosses.")
    public static boolean slayerBosses = true;
    @Color(name = "Slayer Bosses Color", category = SLAYER , subcategory = "Slayer Mobs", description = "Color of the slayer bosses.")
    public static OneColor slayerBossColor = new OneColor(92, 154, 255);

    // Enchanting

    @Header(text = "Enchanting" , category = "Enchanting" , size = 2)
    public boolean _enchanting = true;

    @Header(text = "Coming soon..." , category = "Enchanting" , size = 2, subcategory = "")

    //@Switch(name = "Ultra Sequencer" , category = "Enchanting" , subcategory = "Enchanting")
    public static boolean ultraSequencerSolver = true;
    //@Switch(name = "Chronomatron Solver" , category = "Enchanting" , subcategory = "Enchanting")
    public static boolean chronomatronSolver = true;

    // Mining

    @Header(text = MINING , category = MINING , size = 2)
    public boolean _mining = true;
    @Switch(name = "Enable mining ability Notifier" , category = MINING, subcategory = "", description = "Notifies you when your mining ability is ready.")
    public static boolean miningAbilityNotifier = true;
    @Switch(name = "Disable Don Espresso messages" , category = MINING, subcategory = "", description = "Disables Don Espresso event messages.")
    public static boolean disableDonEspresso = true;
    @Switch(name = "Fix Drill Animation Reset" , category = MINING, subcategory = "" , description = "Fixes drill animation resetting when the fuel updates.")
    public static boolean drillFix = true;
    @Switch(name = "Puzzler solver" , category = MINING, subcategory = "" , description = "Solves the Puzzler block.")
    public static boolean puzzlerSolver = true;
    @Switch(name = "Remove Ghosts invisibility" , category = MINING, subcategory = "" , description = "Removes the invisibility of the ghosts")
    public static boolean showGhosts = true;

    @Switch(name = "Drill Fuel Overlay" , category = MINING, subcategory = "Mining Overlay", description = "Shows the drill fuel in overlay.")
    public static boolean drillFuel = true;
    @Switch(name = "Mithril Powder Overlay" , category = MINING, subcategory = "Mining Overlay", description = "Shows the mithril powder in overlay.")
    public static boolean mithrilPowder = true;
    @Switch(name = "Ability Cooldown Overlay" , category = MINING, subcategory = "Mining Overlay", description = "Shows the ability cooldown in overlay.")
    public static boolean abilityCooldown = true;
    @Switch(name = "Commission overlay" , category = MINING, subcategory = "Mining Overlay", description = "Shows the commission in overlay.")
    public static boolean miningOverlayEnabled = true;
    @Page(name = "Mining Overlay Options" , category = MINING, subcategory = "Mining Overlay", location = PageLocation.BOTTOM)
    public static MiningOverlayPage miningOverlayPage = new MiningOverlayPage();

    // SKYBLOCK

//    @Header(text = "Skyblock" , category = "Skyblock" , size = 2)
//    public boolean skyblock = true;
//    @Switch(name = "Jacob/Auction Timer" , category = "Skyblock")
    public static boolean jATimer = true;

    //@Switch(name = "Storage GUI Overlay" , category = "Skyblock" , subcategory = "GUI Overlays")
    public static boolean storageOverlay = false;

    // DEBUG

    @Header(text = "Debug" , category = "Debug" , size = 2)
    public boolean _debugHead = false;
    @Info(text = "Ignore this if you don't know what you are doing." , type = InfoType.WARNING , category = "Debug")
    public boolean _debugInfo = false;

    @Switch(name = "one" , category = "Debug")
    public static boolean _debug = false;
    @Switch(name = "two" , category = "Debug")
    public static boolean _debug2 = false;
    @Info(text = "info test \u00a7cTEST", type = InfoType.INFO, category = "Debug", subcategory = "Info")
    public static boolean _info = true;
    @Switch(name = "Enable logs" , category = "Debug", subcategory = "Logs")
    public static boolean logs = false;

}
