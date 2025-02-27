package party.lemons.biomemakeover.level.feature.mansion;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;

import java.util.List;
import java.util.Random;

public enum RoomType
{
    CORRIDOR(true, true, true, false, null),
    ROOM(false, true, true, false, MansionFeature.ROOMS),
    ROOM_BIG(false, false, true, false, MansionFeature.ROOMS),
    ROOM_BIG_DUMMY(false, false, true, false, MansionFeature.ROOMS),
    STAIRS_UP(false, false, true, true, MansionFeature.STAIR_UP),
    STAIRS_DOWN(false, false, true, true, MansionFeature.STAIR_DOWN),
    ROOF(false, false, false, false, null),
    GARDEN(true, true, true, false, MansionFeature.GARDEN),
    TOWER_BASE(false, false, true, true, MansionFeature.TOWER_BASE),
    TOWER_MID(false, false, false, true, MansionFeature.TOWER_MID),
    TOWER_TOP(false, false, false, true, MansionFeature.TOWER_TOP),
    DUNGEON_STAIRS_TOP(false, false, true, true, MansionFeature.DUNGEON_STAIR_TOP),
    DUNGEON_STAIRS_MID(false, false, false, true, MansionFeature.DUNGEON_STAIR_MID),
    DUNGEON_STAIRS_BOTTOM(false, false, true, true, MansionFeature.DUNGEON_STAIR_BOTTOM),
    DUNGEON_ROOM(false, true, true, true, MansionFeature.DUNGEON_ROOM),
    BOSS(true, false, false, false, MansionFeature.BOSS_ROOM),
    ENTRANCE(true, false, true, false, MansionFeature.ENTRANCE);

    public final boolean doorRequired;
    private final boolean isReplaceable, hasWalls, columnRotation;
    private final List<ResourceLocation> templates;

    RoomType(boolean doorRequired, boolean isReplaceable, boolean hasWalls, boolean columnRotation, List<ResourceLocation> templates)
    {
        this.doorRequired = doorRequired;
        this.isReplaceable = isReplaceable;
        this.hasWalls = hasWalls;
        this.columnRotation = columnRotation;
        this.templates = templates;
    }

    public ResourceLocation getRandomTemplate(BlockPos pos, RandomSource random)
    {
        if(columnRotation)
        {
            int index = Math.abs((pos.getX() + pos.getZ()) % templates.size());
            return templates.get(index);
        }

        return templates.get(random.nextInt(templates.size()));
    }

    public boolean isReplaceable()
    {
        return isReplaceable;
    }

    public boolean hasWalls()
    {
        return hasWalls;
    }

    public boolean hasWindows()
    {
        //TODO: better
        return this == RoomType.ROOM || this == RoomType.ROOM_BIG || this == RoomType.ROOM_BIG_DUMMY || this == STAIRS_DOWN || this == STAIRS_UP;
    }

    public boolean hasColumnRotation()
    {
        return columnRotation;
    }

    public boolean isDungeon()
    {
        //TODO: better
        //also this is unused?
        return this == DUNGEON_ROOM || this == DUNGEON_STAIRS_MID || this == DUNGEON_STAIRS_BOTTOM;
    }
}