package com.cricketcraft.chisel.block.other;

import com.cricketcraft.chisel.block.BlockCarvable;
import com.cricketcraft.chisel.init.ChiselProperties;
import com.cricketcraft.chisel.init.ChiselTabs;
import com.cricketcraft.chisel.util.BlockVariant;
import com.cricketcraft.chisel.util.IChiselBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ChiselBlockDirt extends BlockCarvable implements IChiselBlock {
    public ChiselBlockDirt() {
        super(Material.GROUND);
        setCreativeTab(ChiselTabs.tabOtherChiselBlocks);
        setDefaultState(this.getBlockState().getBaseState().withProperty(ChiselProperties.DIRT_VARIANTS, ChiselProperties.DIRT_VARIANTS.fromMeta(0)));
    }

    @Override
    public boolean isToolEffective(String type, IBlockState state) {
        return type.equals("shovel");
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (BlockVariant variant : ChiselProperties.DIRT_VARIANTS.getAllowedValues()) {
            list.add(new ItemStack(itemIn, 1, variant.getMeta()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(ChiselProperties.DIRT_VARIANTS, ChiselProperties.DIRT_VARIANTS.fromMeta(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((BlockVariant) state.getValue(ChiselProperties.DIRT_VARIANTS)).getMeta();
    }

    @Override
    public String getSubtypeUnlocalizedName(ItemStack stack) {
        return ChiselProperties.DIRT_VARIANTS.fromMeta(stack.getMetadata()).getName();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, ChiselProperties.DIRT_VARIANTS);
    }
}
