import { registerPlugin } from '@capacitor/core';
const ImageCropper = registerPlugin('ImageCropper', {
    web: () => import('./web').then((m) => new m.ImageCropperWeb()),
});
export * from './definitions';
export { ImageCropper };
//# sourceMappingURL=index.js.map