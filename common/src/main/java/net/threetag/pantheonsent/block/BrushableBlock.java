package net.threetag.pantheonsent.block;

import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.threetag.pantheonsent.block.entity.BrushableBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

@SuppressWarnings({"NullableProblems", "deprecation"})
public class BrushableBlock extends BaseEntityBlock {

    public static IntegerProperty STAGE = IntegerProperty.create("stage", 0, 3);
    public static IntObjectMap<VoxelShape> SHAPES = new IntObjectHashMap<>();

    static {
        for (int i = 0; i <= 3; i++) {
            SHAPES.put(i, Block.box(0, 0, 0, 16, getHeightPerStage(i), 16));
        }
    }

    public static float getHeightPerStage(int stage) {
        return 16F - stage * 4F;
    }

    public final Block base;
    public final BlockState baseState;
    public final ResourceLocation lootTable;

    public BrushableBlock(BlockState baseState, ResourceLocation lootTable, Properties properties) {
        super(properties);
        this.baseState = baseState;
        this.base = baseState.getBlock();
        this.lootTable = lootTable;
        this.registerDefaultState(this.defaultBlockState().setValue(STAGE, 0));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(STAGE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STAGE);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, Random random) {
        this.base.animateTick(state, level, pos, random);
    }

    @Override
    public void attack(BlockState state, Level level, BlockPos pos, Player player) {
        this.baseState.attack(level, pos, player);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        this.base.destroy(level, pos, state);
    }

    @Override
    public float getExplosionResistance() {
        return this.base.getExplosionResistance();
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        this.base.onPlace(this.baseState, level, pos, oldState, isMoving);

        if(state.getValue(STAGE) == 0 && level.getBlockEntity(pos) instanceof BrushableBlockEntity blockEntity) {
            blockEntity.setLootTable(this.lootTable, new Random().nextInt(9999));
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            this.baseState.onRemove(level, pos, newState, isMoving);
        }
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        this.base.stepOn(level, pos, state, entity);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return this.base.isRandomlyTicking(state);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        this.base.randomTick(state, level, pos, random);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        this.base.tick(state, level, pos, random);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return this.baseState.use(level, player, hand, hit);
    }

    @Override
    public void wasExploded(Level level, BlockPos pos, Explosion explosion) {
        this.base.wasExploded(level, pos, explosion);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BrushableBlockEntity(pos, state);
    }

}
