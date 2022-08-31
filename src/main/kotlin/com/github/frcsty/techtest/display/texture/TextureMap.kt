package com.github.frcsty.techtest.display.texture

// Not the cleanest solution, however only reasonable one I've found
enum class TextureMap(private val type: String, val first: Int, val second: Int) {

    AXOLOTL("axolotl", 16499171, 10890612),
    BAT("bat", 4996656, 986895),
    BEE("bee", 15582019, 4400155),
    BLAZE("blaze", 16167425, 16775294),
    CAT("cat", 15714446, 9794134),
    CAVE_SPIDER("cave_spider", 803406, 11013646),
    CHICKEN("chicken", 10592673, 16711680),
    COD("cod", 12691306, 15058059),
    COW("cow", 4470310, 10592673),
    CREEPER("creeper", 894731, 0),
    DOLPHIN("dolphin", 2243405, 16382457),
    DONKEY("donkey", 5457209, 8811878),
    DROWNED("drowned", 9433559, 7969893),
    ELDER_GUARDIAN("elder_guardian", 13552826, 7632531),
    ENDERMAN("enderman", 1447446, 0),
    ENDERMITE("endermite", 1447446, 7237230),
    EVOKER("evoker", 9804699, 1973274),
    FOX("fox", 14005919, 13396256),
    GHAST("ghast", 16382457, 12369084),
    GLOW_SQUID("glow_squid", 611926, 8778172),
    GOAT("goat", 10851452, 5589310),
    GUARDIAN("guardian", 5931634, 15826224),
    HOGLIN("hoglin", 13004373, 6251620),
    HORSE("horse", 12623485, 15656192),
    HUSK("husk", 7958625, 15125652),
    LLAMA("llama", 12623485, 10051392),
    MAGMA_CUBE("magma_cube", 3407872, 16579584),
    MOOSHROOM("mooshroom", 10489616, 12040119),
    MULE("mule", 1769984, 5321501),
    OCELOT("ocelot", 15720061, 5653556),
    PANDA("panda", 15198183, 1776418),
    PARROT("parrot", 894731, 16711680),
    PHANTOM("phantom", 4411786, 8978176),
    PIG("pig", 15771042, 14377823),
    PIGLIN("piglin", 10051392, 16380836),
    PIGLIN_BRUTE("piglin_brute", 5843472, 16380836),
    PILLAGER("pillager", 5451574, 9804699),
    POLAR_BEAR("polar_bear", 15921906, 9803152),
    PUFFERFISH("pufferfish", 16167425, 3654642),
    RABBIT("rabbit", 10051392, 7555121),
    RAVAGER("ravager", 7697520, 5984329),
    SALMON("salmon", 10489616, 951412),
    SHEEP("sheep", 15198183, 16758197),
    SHULKER("shulker", 9725844, 5060690),
    SILVERFISH("silverfish", 7237230, 3158064),
    SKELETON("skeleton", 12698049, 4802889),
    SKELETON_HORSE("skeleton_horse", 6842447, 15066584),
    SLIME("slime", 5349438, 8306542),
    SPIDER("spider", 3419431, 11013646),
    SQUID("squid", 2243405, 7375001),
    STRAY("stray", 6387319, 14543594),
    STRIDER("strider", 10236982, 5065037),
    TRADER_LLAMA("trader_llama", 15377456, 4547222),
    TROPICAL_FISH("tropical_fish", 15690005, 16775663),
    TURTLE("turtle", 15198183, 44975),
    VEX("vex", 8032420, 15265265),
    VILLAGER("villager", 5651507, 12422002),
    VINDICATOR("vindicator", 9804699, 2580065),
    WANDERING_TRADER("wandering_trader", 4547222, 15377456),
    WITCH("witch", 3407872, 5349438),
    WITHER_SKELETON("wither_skeleton", 1315860, 4672845),
    WOLF("wolf", 14144467, 13545366),
    ZOGLIN("zoglin", 13004373, 15132390),
    ZOMBIE("zombie", 44975, 7969893),
    ZOMBIE_HORSE("zombie_horse", 3232308, 9945732),
    ZOMBIE_VILLAGER("zombie_villager", 5651507, 7969893),
    ZOMBIFIED_PIGLIN("zombified_piglin", 15373203, 5009705)
    ;

    companion object {
        @JvmStatic
        fun forType(type: String): TextureMap? {
            var map: TextureMap? = null

            for (value in values()) {
                if (value.type.equals(type, ignoreCase = true).not()) {
                    continue
                }

                map = value
                break
            }

            return map
        }
    }

}