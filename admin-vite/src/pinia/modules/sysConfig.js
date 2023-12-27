import { defineStore } from 'pinia'
import {getConfigList} from '@/api/api'
import {ref} from "@vue/runtime-core";

export const sysConfig = defineStore('sysConfig',()=>{
    const configDict = ref([]);
    async function getDictList(){
      // const {data} = await getConfigList({})
       configDict.value = {};
    }
    return {
        configDict,
        getDictList
    }
})