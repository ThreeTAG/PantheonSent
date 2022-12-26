package net.threetag.pantheonsent.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.threetag.pantheonsent.entity.Khonshu;
import net.threetag.pantheonsent.item.PSItems;

public class TotemHolderBlock extends Block {

    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public TotemHolderBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, false));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);

        if (!state.getValue(ACTIVE) && stack.getItem() == PSItems.LUNAR_TOTEM.get() && level.isNight()) {
            boolean foundSky = false;

            for (Direction direction : Direction.values()) {
                if (level.canSeeSky(pos.relative(direction))) {
                    foundSky = true;
                    break;
                }
            }

            if (foundSky) {
                level.setBlock(pos, state.setValue(ACTIVE, true), 2);
                stack.shrink(1);

                if (!level.isClientSide) {
                    Khonshu khonshu = new Khonshu(level, player, Khonshu.Mode.RECRUITING);
                    var kPos = Khonshu.findRandomPos(player.getOnPos(), khonshu, player, level, 3, 7, 7);
                    khonshu.setPos(new Vec3(kPos.x(), kPos.y(), kPos.z()));
                    level.addFreshEntity(khonshu);
                    level.scheduleTick(pos, this, 60);
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlock(pos, state.setValue(ACTIVE, false), 2);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

}
