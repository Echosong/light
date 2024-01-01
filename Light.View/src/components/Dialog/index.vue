<script setup >
import { ElDialog, ElScrollbar } from 'element-plus'
import { computed, useAttrs, ref, unref, useSlots, watch, nextTick } from 'vue'

const slots = useSlots()

const props = defineProps({
    modelValue: {type:Boolean, default: false},
    title: {type:String, default:'Dialog'} ,
    fullscreen: {type:Boolean, default: true},
    maxHeight: {type: [String, Number], default: '400px'},
    maxWidth: {type: [String, Number], default: '800px'}
})

const getBindValue = computed(() => {
    const delArr = ['fullscreen', 'title', 'maxHeight']
    const attrs = useAttrs()
    const obj = { ...attrs, ...props }
    for (const key in obj) {
        if (delArr.indexOf(key) !== -1) {
            delete obj[key]
        }
    }
    return obj
})

const isFullscreen = ref(false)

const toggleFull = () => {
    isFullscreen.value = !unref(isFullscreen)
}

const dialogHeight = ref(typeof props.maxHeight === 'number'  ? `${props.maxHeight}px` : props.maxHeight)

watch(
    () => isFullscreen.value,
    async (val) => {
        await nextTick()
        if (val) {
            const windowHeight = document.documentElement.offsetHeight
            dialogHeight.value = `${windowHeight - 55 - 60 - (slots.footer ? 63 : 0)}px`
        } else {
            dialogHeight.value = typeof props.maxHeight === 'number' ? `${props.maxHeight}px` : props.maxHeight
        }
    },
    {
        immediate: true
    }
)

const dialogStyle = computed(() => {
    return {
        height: unref(dialogHeight)
    }
})
</script>

<template>
    <ElDialog
        v-bind="getBindValue"
        :fullscreen="isFullscreen"
        destroy-on-close
        lock-scroll
        draggable
        top="0"
        :close-on-click-modal="false"
        :show-close="false"
        :width="maxWidth"
    >
        <template #header="{ close }">
            <div class="es-header">
                <slot name="title">
                    {{ title }}
                </slot>
                <div class="es-icon">

                    <el-icon  v-if="isFullscreen" @click="toggleFull"  style=" margin-right: 10px; " class="es-full">
                        <CopyDocument />
                    </el-icon>
                    <el-icon   v-else @click="toggleFull"  style=" margin-right: 10px;" class="es-full">
                        <FullScreen />
                    </el-icon>

                    <el-icon  @click="close"   class="es-full">
                        <Close />
                    </el-icon>

                </div>
            </div>
        </template>

        <ElScrollbar :style="dialogStyle">
            <slot></slot>
        </ElScrollbar>

        <template v-if="slots.footer" #footer>
            <slot name="footer"></slot>
        </template>
    </ElDialog>
</template>

<style lang="scss">
.es-header{
   display:  flex;
   justify-content: space-between;
    align-items: center;
    height:54px;
    padding-left: 15px;
    padding-right: 15px;
    position: relative;
    font-size: 16px;
    color: #333 !important;
}
.es-icon{
    font-size: 14px;
}
.es-full{
    cursor: pointer;
    &:hover{
        color: #409EFC;
    }
    height: 54px;
}

.el-overlay-dialog {
    display: flex;
    justify-content: center;
    align-items: center;
}

.el-dialog {
    margin: 0!important;

    &__header {
        height: 54px;
        padding: 0;
        margin-right: 0!important;
    }
}

.el-overlay-dialog {
    display: flex;
    justify-content: center;
    align-items: center;
}

.el-dialog {
    margin: 0 !important;

    &__header {
        height: 54px;
        padding: 0;
        margin-right: 0 !important;
        border-bottom: 1px solid var(--el-border-color);
    }

    &__body {
        padding: 15px !important;
    }

    &__footer {
        border-top: 1px solid var(--el-border-color);
    }

    &__headerbtn {
        top: 0;
    }
}
</style>
