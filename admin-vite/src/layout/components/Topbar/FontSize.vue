<template>
    <el-dropdown trigger="hover">
        <div class="change-lang">

            <svg-icon name="fontSize" class="icon" ></svg-icon>
        </div>
        <template #dropdown>
            <el-dropdown-menu>
                <el-dropdown-item
                    @click="editSize(item.value)"
                    v-for="item in fontlist"
                    :key="item.value"
                >
                    {{ item.name }}
                </el-dropdown-item>
            </el-dropdown-menu>
        </template>
    </el-dropdown>
</template>
<script setup>
import {FontSize} from '@/pinia/modules/fontSize'

const fontlist = [
    {
        name: '大号',
        value: 'large',
    },
    {
        name: '中号',
        value: 'default',
    },
    {
        name: '小号',
        value: 'small',
    },
]
const editSize = (e) => {
    FontSize().editSize(e)
}
</script>

<style lang="scss" scoped>
.change-lang {
    color: $topHeaderTextColor;
    padding: 0 16px;
    height: 48px;
    cursor: pointer;
    display: flex;
    align-items: center;

    .icon {
        font-size: 18px;
    }
}
</style>
  