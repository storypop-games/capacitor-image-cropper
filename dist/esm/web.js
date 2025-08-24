import { WebPlugin } from '@capacitor/core';
export class ImageCropperWeb extends WebPlugin {
    async crop(options) {
        return {
            path: options.source,
        };
    }
}
//# sourceMappingURL=web.js.map