import {getItem, setItem} from '@/utils/storage'
import {defineStore} from 'pinia'

const FONTSIZE = 'fontSize'
export const FontSize = defineStore('FontSize', {
  state: () => ({
    piniaSize: getItem('fontSize') || 'default',
  }),
  actions: {
    /**
     *
     * @param {string} 整体大小  'large'| 'default'| 'small'
     */
    editSize(size) {
      this.piniaSize = size
      setItem(FONTSIZE, size)
      location.reload();
    },
  },
})
